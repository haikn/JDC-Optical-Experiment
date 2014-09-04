/*
 * @(#)NewProjectDialog.java
 *
 * This program is version 2.0 of JDC Education Kit for Optical Experiments.
 * Copyright (C) Jasper Display Corporation 2013.
 * This program is free software; you can redistribute it
 * and/or modify it under the terms of the GNU General Public
 * License version 2.0 as published by the Free Software Foundation. 

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA. 
 * You may contact Jasper Display Corporation by email at info@jasperdisplay.com
 * or by telephone at +1-408-855-6640.
 * More information is provided at http://www.jasperdisplay.com/.
 */
package com.jasper.ui.widget;

import com.jasper.model.Macro;
import com.jasper.model.Project;
import com.jasper.ui.EduControllerPattern;
import com.jasper.utils.Utils;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author trancongly
 */
public class NewProjectDialog extends JDialog implements ActionListener {

    private JTextField txtProjectName;
    private JTextField txtDescription;
    private JTextField txtGraphic;
    private JTextField txtMacro;
    private JButton btnCreateProject, btnCancel, btnLoadDescription, btnLoadGraphic, btnLoadMacro;
    private JFrame parentFrame = new JFrame();
    private JFileChooser fc;
    private JTextArea testDesc;
    private JComboBox cmbLanguage;
    private EduControllerPattern macroPanel;

    public NewProjectDialog() {
        fc = new JFileChooser();
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 3, 5, 5));

        txtProjectName = new JTextField();
        txtDescription = new JTextField();
        txtGraphic = new JTextField();
        txtMacro = new JTextField();
        inputPanel.add(new JLabel("Project Name:"));
        inputPanel.add(txtProjectName);
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel("Macro File"));
        inputPanel.add(txtMacro);
        btnLoadMacro = new JButton("Browser");
        btnLoadMacro.addActionListener(this);
        inputPanel.add(btnLoadMacro);

        inputPanel.add(new JLabel("Diagram File:"));
        inputPanel.add(txtGraphic);
        btnLoadGraphic = new JButton("Browser");
        btnLoadGraphic.addActionListener(this);
        inputPanel.add(btnLoadGraphic);

        inputPanel.add(new JLabel("Language of the description:"));
        String[] language = {"English", "Chinese(Traditional)", "Chinese"};
        cmbLanguage = new JComboBox(language);
        inputPanel.add(cmbLanguage);
        inputPanel.add(new JLabel());

        inputPanel.add(new JLabel("Description File:"));
        inputPanel.add(txtDescription);
        btnLoadDescription = new JButton("Browser");
        btnLoadDescription.addActionListener(this);
        inputPanel.add(btnLoadDescription);

        inputPanel.add(new JLabel("Description"));
        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel(""));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        btnCreateProject = new JButton("Create Project");
        btnCreateProject.addActionListener(this);
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);

        buttonPanel.add(btnCreateProject);
        buttonPanel.add(btnCancel);

        parentFrame.setTitle("New Project");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        parentFrame.getContentPane().add(mainPanel);

        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(inputPanel);
        testDesc = new JTextArea();
        testDesc.setColumns(20);
        testDesc.setRows(10);
        testDesc.setFont(new Font("Courier New", Font.PLAIN, 12));
        testDesc.setLineWrap(true);
        testDesc.setWrapStyleWord(true);
        testDesc.setEditable(false);
        testDesc.setOpaque(true);
        testDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(testDesc);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(buttonPanel);
        parentFrame.setSize(100, 200);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        parentFrame.pack();
    }

    public void setMacroPanel(EduControllerPattern panel) {
        macroPanel = panel;
    }
    
    public void setVisible() {
        parentFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoadDescription) {
            FileFilter filter = new FileNameExtensionFilter("Text file", "txt");
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();                
                //This is where a real application would open the file.
                //log.append("Opening: " + file.getName() + "." + newline);
                txtDescription.setText(file.getAbsolutePath());
                try {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String inputFile = "";
                    String textFieldReadable;

                    while ((textFieldReadable = bufferedReader.readLine()) != null) {
                        inputFile += textFieldReadable;
                    }

                    testDesc.setText(inputFile);

                } catch (FileNotFoundException ex) {
                    System.out.println("no such file exists");
                } catch (IOException ex) {
                    System.out.println("unkownerror");
                }
                return;
            }
        } else if (e.getSource() == btnLoadGraphic) {
            FileFilter filter = new FileNameExtensionFilter("Image file", "jpg");
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();                
                txtGraphic.setText(file.getAbsolutePath());
                return;
            }
        } else if (e.getSource() == btnLoadMacro) {
            FileFilter filter = new FileNameExtensionFilter("Text file", "txt");
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();                
                txtMacro.setText(file.getAbsolutePath());
                return;
            }
        } else if (e.getSource() == btnCreateProject) {
            if (txtProjectName.getText().length() == 0) {
                txtProjectName.setFocusable(true);
                return;
            } else if (txtMacro.getText().length() == 0) {
                txtMacro.setFocusable(true);
                return;
            } else {
                Project newProject = new Project(txtProjectName.getText(), txtMacro.getText(), txtGraphic.getText(), cmbLanguage.getSelectedItem().toString(), txtDescription.getText());
                newProject.writeToFile();
            }
            Macro macro = new Macro(txtMacro.getText());            
            macroPanel.showProjects(macro.getParam().size(), txtProjectName.getText(), txtMacro.getText(), txtDescription.getText(), txtGraphic.getText());
            parentFrame.dispose();            
        } else if (e.getSource() == btnCancel) {
            parentFrame.dispose();
        }
    }
}

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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private JButton btnSave, btnCancel, btnHelp, btnLoadDescription, btnLoadGraphic, btnLoadMacro;
    private JFrame parentFrame = new JFrame();
    private JFileChooser fc;
    
    public NewProjectDialog() {
        fc = new JFileChooser();
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0,3));
        
        txtProjectName = new JTextField();
        txtDescription = new JTextField();
        txtGraphic = new JTextField();
        txtMacro = new JTextField();
        inputPanel.add(new JLabel("Project Name"));
        inputPanel.add(txtProjectName);
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel("File Description"));
        inputPanel.add(txtDescription);
        btnLoadDescription = new JButton("Browser");
        btnLoadDescription.addActionListener(this);
        inputPanel.add(btnLoadDescription);
        
        inputPanel.add(new JLabel("File Graphic"));
        inputPanel.add(txtGraphic);
        btnLoadGraphic = new JButton("Browser");
        btnLoadGraphic.addActionListener(this);
        inputPanel.add(btnLoadGraphic);
        
        inputPanel.add(new JLabel("File Macro"));
        inputPanel.add(txtMacro);
        btnLoadMacro = new JButton("Browser");
        btnLoadMacro.addActionListener(this);
        inputPanel.add(btnLoadMacro);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        btnHelp = new JButton("Help");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnHelp);
        
        parentFrame.setTitle("New Project");
        parentFrame.add(inputPanel, BorderLayout.CENTER);
        parentFrame.add(buttonPanel, BorderLayout.SOUTH);
        parentFrame.setSize(100, 200);
        parentFrame.pack();
    }
    
    public void setVisible() {
        parentFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoadDescription) {
            FileFilter filter = new FileNameExtensionFilter("Text file","txt");
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                //log.append("Opening: " + file.getName() + "." + newline);
                return;
            } 
        } else if (e.getSource() == btnLoadGraphic) {
            FileFilter filter = new FileNameExtensionFilter("Image file","jpg");
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                //log.append("Opening: " + file.getName() + "." + newline);
                return;
            } 
        } else if (e.getSource() == btnLoadMacro) {
            FileFilter filter = new FileNameExtensionFilter("Text file","txt");
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                //log.append("Opening: " + file.getName() + "." + newline);
                return;
            } 
        } else if (e.getSource() == btnHelp) {
            return;
        } else if (e.getSource() == btnSave) {
            return; 
        } else if (e.getSource() == btnCancel) {
            return; 
        }
    }
}

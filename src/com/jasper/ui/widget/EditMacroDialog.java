/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jasper.ui.widget;

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
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author trancongly
 */
public class EditMacroDialog extends JDialog implements ActionListener {

    private JTextField txtMacro;
    private JButton btnSave, btnCancel, btnLoadMacro;
    private JFrame parentFrame = new JFrame();
    private JFileChooser fc;
    private JTextArea textMacro;
    private String projectName;
    private String macroFile;

    public EditMacroDialog(String projectName, String macroFile) {
        this.projectName = projectName;
        this.macroFile = macroFile;
        fc = new JFileChooser();
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 3, 5, 5));

        txtMacro = new JTextField();
        inputPanel.add(new JLabel("Project Name:"));
        inputPanel.add(new JLabel(projectName));
        inputPanel.add(new JLabel());

        inputPanel.add(new JLabel("Macro File:"));
        inputPanel.add(txtMacro);
        btnLoadMacro = new JButton("Browser");
        btnLoadMacro.addActionListener(this);
        inputPanel.add(btnLoadMacro);

        inputPanel.add(new JLabel("Macro"));
        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel(""));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        btnSave = new JButton("Save");
        btnSave.addActionListener(this);
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        parentFrame.setTitle("Edit Macro");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        parentFrame.getContentPane().add(mainPanel);

        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(inputPanel);
        textMacro = new JTextArea();
        textMacro.setColumns(20);
        textMacro.setRows(10);
        textMacro.setFont(new Font("Courier New", Font.PLAIN, 12));
        textMacro.setLineWrap(true);
        textMacro.setWrapStyleWord(true);
        textMacro.setEditable(true);
        textMacro.setOpaque(true);
        textMacro.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane sp = new JScrollPane(textMacro);
        mainPanel.add(sp);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(buttonPanel);
        parentFrame.setSize(100, 200);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        parentFrame.pack();

        txtMacro.setText(macroFile);
        try {
            FileReader fileReader = new FileReader(new File(macroFile));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String textFieldReadable;

            while ((textFieldReadable = bufferedReader.readLine()) != null) {
                textMacro.append(textFieldReadable + "\n");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("no such file exists");
        } catch (IOException ex) {
            System.out.println("unkownerror");
        }
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setVisible() {
        parentFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoadMacro) {
            FileFilter filter = new FileNameExtensionFilter("Text file", "txt");
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                txtMacro.setText(file.getAbsolutePath());
                try {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String textFieldReadable;

                    while ((textFieldReadable = bufferedReader.readLine()) != null) {
                        textMacro.append(textFieldReadable + "\n");
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("no such file exists");
                } catch (IOException ex) {
                    System.out.println("unkownerror");
                }
                return;
            }
        } else if (e.getSource() == btnCancel) {
            parentFrame.dispose();
        } else if (e.getSource() == btnSave) {
            editFile(macroFile);
            parentFrame.dispose();
        }
    }

    private void editFile(String filename) {
        try {
            File f1 = new File(filename);
            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(textMacro.getText());
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

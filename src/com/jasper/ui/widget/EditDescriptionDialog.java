/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jasper.ui.widget;

import com.jasper.model.Project;
import com.jasper.utils.Utils;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EditDescriptionDialog extends JDialog implements ActionListener {

    private JTextField txtDescription;
    private JButton btnSave, btnCancel, btnLoadDescription;
    private JFrame parentFrame = new JFrame();
    private JFileChooser fc;
    private JTextArea textDesc;
    private JComboBox cmbLanguage;
    private String projectName;
    private String projectPath;
    private String descFile;

    public EditDescriptionDialog(String projectName, String projectPath, String descFile) {
        this.projectName = projectName;
        this.descFile = descFile;
        this.projectPath = projectPath;
        
        fc = new JFileChooser();
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 3, 5, 5));

        txtDescription = new JTextField();
        inputPanel.add(new JLabel("Project Name:"));
        inputPanel.add(new JLabel(projectName));
        inputPanel.add(new JLabel());

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
        btnSave = new JButton("Save");
        btnSave.addActionListener(this);
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        parentFrame.setTitle("Edit Project Description");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        parentFrame.getContentPane().add(mainPanel);

        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(inputPanel);
        textDesc = new JTextArea();
        textDesc.setColumns(20);
        textDesc.setRows(10);      
        Font font = Utils.getFont();
        textDesc.setFont(font);
        textDesc.setLocale(new Locale("zh","TW"));
        textDesc.setLineWrap(true);
        textDesc.setWrapStyleWord(true);
        textDesc.setEditable(true);
        textDesc.setOpaque(true);
        textDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane sp = new JScrollPane(textDesc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(sp);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(buttonPanel);
        parentFrame.setSize(100, 200);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        parentFrame.pack();
        txtDescription.setText(descFile);
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(descFile), "UTF-8"));

            String inputFile = "";
            String textFieldReadable;

            while ((textFieldReadable = bufferedReader.readLine()) != null) {
                inputFile += textFieldReadable.replaceAll("\\p{C}", "") + "\n";
            }

            textDesc.setText(inputFile);

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
    
    public void setDescriptionFile(String descriptionFile) {
        this.descFile = descriptionFile;
    }
    
    public String getDescriptionFile() {
        return this.descFile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoadDescription) {
            FileFilter filter = new FileNameExtensionFilter("Text file", "txt");
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                
                File file = fc.getSelectedFile();
                if(descFile == null || descFile.trim().equals("")) {
                    descFile = file.toString();
                }
                //This is where a real application would open the file.
                //log.append("Opening: " + file.getName() + "." + newline);
                txtDescription.setText(file.getAbsolutePath());
                try {
                    //FileReader fileReader = new FileReader(file);
                    //BufferedReader bufferedReader = new BufferedReader(fileReader);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

                    String inputFile = "";
                    String textFieldReadable;

                    while ((textFieldReadable = bufferedReader.readLine()) != null) {
                        inputFile += textFieldReadable.replaceAll("\\p{C}", "") + "\n";
                    }

                    textDesc.setText(inputFile);

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
            Project prj = new Project(projectPath);
            editFile(descFile);
            updateProjectFile(projectPath, prj.getDescriptionAttribute(), descFile);
            parentFrame.dispose();
        }
    }

    private void editFile(String filename) {
        try {
            File f1 = new File(filename);
            //FileWriter fw = new FileWriter(f1);
            Writer out = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(f1), "UTF8"));
            //BufferedWriter out = new BufferedWriter(fw,);
            out.write(textDesc.getText());
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void updateProjectFile(String filename, String attribute, String newValue) {
        Properties prop = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            prop.load(fileInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            for(Map.Entry entry: prop.entrySet()) {
                if(entry.getKey().toString().equals(attribute)) {
                    prop.setProperty(attribute, newValue);
                } else {
                    prop.setProperty(entry.getKey().toString(), entry.getValue().toString());
                }
            }
            prop.store(fileOutputStream, filename);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

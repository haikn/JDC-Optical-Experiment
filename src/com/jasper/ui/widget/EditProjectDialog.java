/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jasper.ui.widget;

import com.jasper.model.Macro;
import com.jasper.model.Project;
import com.jasper.ui.EduControllerPattern;
import com.jasper.ui.EduPatternShowOn;
import com.jasper.utils.Utils;
import java.awt.BorderLayout;
import java.awt.Toolkit;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author trancongly
 */
public class EditProjectDialog extends JDialog implements ActionListener {

    private JFrame parentFrame = new JFrame();
    private JPanel topPanel;
    private JTable table;
    private JScrollPane scrollPane;
    private EduControllerPattern macroPanel;
    private JFileChooser fc;
    private static final String NAME = "Project";
    private static final String MACRO = "Macro";
    private static final String GRAPHIC = "Graphic";
    private static final String LANGUAGE = "Language";
    private static final String DESCRIPTION = "Description";

    public EditProjectDialog() {
        fc = new JFileChooser();
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        parentFrame.getContentPane().add(topPanel);
        parentFrame.setSize(600, 200);

        String columnNames[] = {"Project Name", "Location", "Macro", "Diagram", "Description", "Run", "Delete"};
        Vector<String> columns = new Vector<String>();
        columns.add("Project Name");
        columns.add("Location");
        columns.add("Macro");
        columns.add("Diagram");
        columns.add("Description");
        columns.add("Run");
        columns.add("Delete");
        URL macroUrl = ClassLoader.getSystemResource("resources/macro.png");
        URL diagramUrl = ClassLoader.getSystemResource("resources/diagram.png");
        URL runUrl = ClassLoader.getSystemResource("resources/run.png");
        URL deleteUrl = ClassLoader.getSystemResource("resources/delete.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        ImageIcon macroIcon = new ImageIcon(kit.createImage(macroUrl));
        ImageIcon diagramIcon = new ImageIcon(kit.createImage(diagramUrl));
        ImageIcon runIcon = new ImageIcon(kit.createImage(runUrl));
        ImageIcon deleteIcon = new ImageIcon(kit.createImage(deleteUrl));
        
        File[] prjFiles = Utils.getAllProjectFiles();
        
        final Vector<Vector> prjValues = new Vector<Vector>();
        for (File prjFile : prjFiles) {
            Vector row = new Vector();
            row.add(prjFile.getName());
            row.add(prjFile.getPath());
            row.add(macroIcon);
            row.add(diagramIcon);
            
            Project prj = new Project(prjFile.getPath());
            String prjDescriptionFile = prj.getDescription();
            if(prjDescriptionFile != null) {
                try {
                    FileReader fileReader = new FileReader(new File(prjDescriptionFile));
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String inputFile = "";
                    String textFieldReadable;

                    while ((textFieldReadable = bufferedReader.readLine()) != null) {
                        inputFile += textFieldReadable;
                    }              
                    row.add(inputFile.substring(0, Math.min(inputFile.length(), 20)));

                } catch (FileNotFoundException ex) {
                    System.out.println("no such file exists");
                    row.add("");
                } catch (IOException ex) {
                    System.out.println("unkownerror");
                }
            } else {
                row.add("Add description");
            }
            
            //row.add("");
            row.add(runIcon);
            row.add(deleteIcon);
            prjValues.add(row);
        }

        //DefaultTableModel model = new DefaultTableModel(dataValues, columnNames);
        final DefaultTableModel model = new DefaultTableModel(prjValues, columns);
        // Create a new table instance
        table = new JTable(model) {

            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        table.setRowHeight(30);

        // Add the table to a scrolling pane
        scrollPane = new JScrollPane(table);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        table.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    if (col == 0) {                     
                        String newPrjName = (String)JOptionPane.showInputDialog(null,
                                    "New project name:\n",
                                    "Edit project name",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    null,
                                    null);
                        if(newPrjName == null || newPrjName.trim().equals("")) {
                        } else {
                            String prjFileName = table.getModel().getValueAt(row, 1).toString();
                            Project prj = new Project(prjFileName);                            
                            if (!prj.getName().equals(newPrjName)) {
                                editFile(prjFileName, prj.getProjectAttribute(), newPrjName);
                                File oldF = new File(prjFileName);
                                String newN = newPrjName.replaceAll("\\s+","") + ".prj";
                                File newF = new File(Utils.getCurrentLocation() + newN);
                                oldF.renameTo(newF);
                                model.setValueAt(newN, row, 0);
                            }
                        }
                    } else if (col == 1) {                        
                        //int returnVal = fc.showOpenDialog(topPanel);

                        //if (returnVal == JFileChooser.APPROVE_OPTION) {
                            //File file = fc.getSelectedFile();                
                            //txtMacro.setText(file.getName());
                            //return;
                        //}
                    } else if (col == 2) {
                        String prjFileName = table.getModel().getValueAt(row, 1).toString();
                        Project prj = new Project(prjFileName);
                        EditMacroDialog editMacroDialog = new EditMacroDialog(table.getModel().getValueAt(row, 0).toString(), prj.getMacro());
                        editMacroDialog.setVisible();
                    } else if (col == 3) {
                        FileFilter filter = new FileNameExtensionFilter("Image file", "jpg");
                        fc.setFileFilter(filter);
                        int returnVal = fc.showOpenDialog(topPanel);

                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fc.getSelectedFile();
                            String prjFileName = table.getModel().getValueAt(row, 1).toString();
                            Project prj = new Project(prjFileName);
                            if (!prj.getDiagram().equals(file.getAbsolutePath())) {
                                System.out.println("prjFileName = " + prjFileName);
                                System.out.println("prj.getDiagramAttribute() = " + prj.getDiagramAttribute());
                                System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
                                editFile(prjFileName, prj.getDiagramAttribute(), file.getAbsolutePath());
                                //model.setValueAt(file.getName(), row, 3);
                            }
                            
                            return;
                        }
                    } else if (col == 4) {
                        String prjFileName = table.getModel().getValueAt(row, 1).toString();
                        Project prj = new Project(prjFileName);
                        EditDescriptionDialog editDescriptionDialog = new EditDescriptionDialog(table.getModel().getValueAt(row, 0).toString(), prjFileName, prj.getDescription());
                        editDescriptionDialog.setVisible();
                    } else if (col == 5) {
                        try {
                            String prjFileName = table.getModel().getValueAt(row, 1).toString();
                            Project prj = new Project(prjFileName);
                            //Macro macro = new Macro(prj.getMacro());
                            //macroPanel.showProjects(macro.getParam().size(), prj.getName(), prj.getMacro(), prj.getDescription(), prj.getGraphic());                        
                            macroPanel.showProjects(prj.getName(), prj.getMacro(), prj.getDescription(), prj.getDiagram());
                            parentFrame.dispose();
                        } catch (IOException ex) {
                            Logger.getLogger(EditProjectDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (col == 6) {
                        int dialogResult = JOptionPane.showConfirmDialog(null,
                            "Do you want to delete this project",
                            "Warning",
                            JOptionPane.YES_NO_OPTION);
                        if(dialogResult == JOptionPane.YES_OPTION) {
                            String prjFileName = table.getModel().getValueAt(row, 1).toString();
                            //Project delPrj = new Project(prjFileName);
                            File prjFile = new File(prjFileName);
                            prjFile.delete();
                        
                            //File macroFile = new File(delPrj.getMacro());
                            //macroFile.delete();
                            //prjValues.remove(row);
                            model.removeRow(row);
                        }
                    } 
                }
            }
        });
     
    }

    public void setVisible() {
        parentFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void setMacroPanel(EduControllerPattern panel) {
        macroPanel = panel;
    }
    
    private void editFile(String filename, String attribute, String newValue) {
        Properties prop = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            prop.load(fileInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            for(Entry entry: prop.entrySet()) {
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

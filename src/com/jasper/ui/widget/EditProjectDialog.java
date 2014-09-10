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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;
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
                try {
                    FileReader fileReader = new FileReader(new File(prj.getDescription()));
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String inputFile = "";
                    String textFieldReadable;

                    while ((textFieldReadable = bufferedReader.readLine()) != null) {
                        inputFile += textFieldReadable;
                    }              
                    row.add(inputFile.substring(0, Math.min(inputFile.length(), 20)));

                } catch (FileNotFoundException ex) {
                    System.out.println("no such file exists");
                } catch (IOException ex) {
                    System.out.println("unkownerror");
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

            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

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
                        String prjFileName = table.getModel().getValueAt(row, 1).toString();
                        Project prj = new Project(prjFileName);                            
                            if (!prj.getName().equals(newPrjName)) {
                                editFile(prjFileName, prj.getName(), newPrjName);
                                File oldF = new File(prjFileName);
                                String newN = newPrjName.replaceAll("\\s+","") + ".prj";
                                File newF = new File(Utils.getCurrentLocation() + newN);
                                oldF.renameTo(newF);
                                model.setValueAt(newN, row, 0);
                            }
                    } else if (col == 1) {                        
                        int returnVal = fc.showOpenDialog(topPanel);

                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            //File file = fc.getSelectedFile();                
                            //txtMacro.setText(file.getName());
                            return;
                        }
                    } else if (col == 2) {
                        String prjFileName = table.getModel().getValueAt(row, 1).toString();
                        Project prj = new Project(prjFileName);
                        EditMacroDialog editDialog = new EditMacroDialog(table.getModel().getValueAt(row, 0).toString(), prj.getMacro());
                        editDialog.setVisible();
                    } else if (col == 3) {
                        FileFilter filter = new FileNameExtensionFilter("Image file", "jpg");
                        fc.setFileFilter(filter);
                        int returnVal = fc.showOpenDialog(topPanel);

                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fc.getSelectedFile();
                            String prjFileName = table.getModel().getValueAt(row, 1).toString();
                            Project prj = new Project(prjFileName);                            
                            if (!prj.getGraphic().equals(file.getAbsolutePath())) {
                                editFile(prjFileName, prj.getGraphic(), file.getAbsolutePath());
                                //model.setValueAt(file.getName(), row, 3);
                            }
                            
                            return;
                        }
                    } else if (col == 4) {
                        String prjFileName = table.getModel().getValueAt(row, 1).toString();
                        Project prj = new Project(prjFileName);
                        EditDescriptionDialog editDialog = new EditDescriptionDialog(table.getModel().getValueAt(row, 0).toString(), prj.getDescription());
                        editDialog.setVisible();
                    } else if (col == 5) {
                        String prjFileName = table.getModel().getValueAt(row, 1).toString();
                        Project prj = new Project(prjFileName);
                        Macro macro = new Macro(prj.getMacro());                        
                        macroPanel.showProjects(macro.getParam().size(), prj.getName(), prj.getMacro(), prj.getDescription(), prj.getGraphic());
                        parentFrame.dispose();                        
                    } else if (col == 6) {
                        int dialogResult = JOptionPane.showConfirmDialog(null,
                            "Do you want to delete this project",
                            "Warning",
                            JOptionPane.YES_NO_OPTION);
                        if(dialogResult == JOptionPane.YES_OPTION) {
                            String prjFileName = table.getModel().getValueAt(row, 1).toString();
                            Project delPrj = new Project(prjFileName);
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
    
    private void editFile(String filename, String oldValue, String newValue) {
         try
        {
            ArrayList<String> lines = new ArrayList<String>();
            String line = null;
            File f1 = new File(filename);
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null)
            {
                if (line.contains(oldValue))
                    line = line.replace(oldValue, newValue);
                lines.add(line);                
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : lines) {
                out.write(s);      
                out.newLine();
            }
            out.flush();
            out.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

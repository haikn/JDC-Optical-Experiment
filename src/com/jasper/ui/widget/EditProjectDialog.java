/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jasper.ui.widget;

import com.jasper.model.Project;
import com.jasper.ui.EduPatternShowOn;
import com.jasper.utils.Utils;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

    public EditProjectDialog() {
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
        
        Vector<Vector> prjValues = new Vector<Vector>();
        for (File prjFile : prjFiles) {
            Vector row = new Vector();
            row.add(prjFile.getName());
            row.add(prjFile.getPath());
            row.add(macroIcon);
            row.add(diagramIcon);
            row.add("");
            row.add(runIcon);
            row.add(deleteIcon);
            prjValues.add(row);
        }

       
        //DefaultTableModel model = new DefaultTableModel(dataValues, columnNames);
        //DefaultTableModel model = new DefaultTableModel(prjValues, columns);
        // Create a new table instance
        table = new JTable(prjValues, columns) {

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
                    if (col % 6 == 0) {
                        String prjFileName = table.getModel().getValueAt(row, 1).toString();
                        Project delPrj = new Project(prjFileName);
                        File prjFile = new File(prjFileName);
                        prjFile.delete();
                        
                        //File macroFile = new File(delPrj.getMacro());
                        //macroFile.delete();
                        DefaultTableModel model = (DefaultTableModel)table.getModel();
                        model.fireTableDataChanged();
                    } else if (col % 5 == 0) {
                        System.out.println("run at: " + row);
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
}

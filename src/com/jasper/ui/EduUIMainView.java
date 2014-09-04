/*
 * @(#)EduUIMainView.java
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
package com.jasper.ui;

import com.jasper.core.OpticsPane;
import com.jasper.core.PatternImage;
import com.jasper.model.Macro;
import com.jasper.model.Project;
import com.jasper.ui.widget.EditProjectDialog;
import com.jasper.ui.widget.NewProjectDialog;
import com.jasper.utils.Utils;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JMenuItem;

public class EduUIMainView extends javax.swing.JFrame {

    /**
     * Creates new form EduUIMainView
     */
    public EduUIMainView() throws IOException{
        labels = ResourceBundle.getBundle("resources/Text", supportedLocales[locale]);
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws IOException {
        jTabbedPaneOptics = new javax.swing.JPanel();

        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPaneApp = new javax.swing.JScrollPane();
        jTextAreaLog = new javax.swing.JTextArea();
        jTextAreaDesc = new javax.swing.JTextArea();
        jMenuBarMain = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuItemExperiments = new javax.swing.JMenuItem();
        jMenuProjects = new javax.swing.JMenu();
        jMenuItemEditProjects = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();
        panelOptic =  new EduControllerPattern(locale);
        
        // add optics panes to panelist
        panelist = new ArrayList<OpticsPane>();

        setTabPanelEnable(jTabbedPaneOptics, true);

        jTextAreaDesc.setColumns(20);
        jTextAreaDesc.setRows(5);
        jTextAreaDesc.setFont(Utils.getFont());

        jTextAreaLog.setColumns(20);
        jTextAreaLog.setRows(5);
        jTextAreaLog.setFont(Utils.getFont());
        jScrollPane2.setViewportView(jTextAreaLog);

        jMenuFile.setText(labels.getString("mnuFile"));
                
        jMenuItemExperiments.setText(labels.getString("mnuExperiments"));
        jMenuItemExperiments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panelOptic.showExperiments();
            }
        });
        jMenuFile.add(jMenuItemExperiments);
        
        jMenuProjects.setText(labels.getString("mnuProjects"));
        jMenuFile.add(jMenuProjects);          
        
        JMenuItem newPrj = new JMenuItem(labels.getString("mnuNewProjects"));
        newPrj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewProjectDialog dialog = new NewProjectDialog();
                dialog.setLocationRelativeTo(jTabbedPaneOptics);
                dialog.setMacroPanel(panelOptic);
                dialog.setVisible();
            }
        });
        jMenuProjects.add(newPrj);
        
        jMenuItemEditProjects.setText(labels.getString("mnuEditProject"));
        jMenuItemEditProjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditProjectDialog dialog = new EditProjectDialog();
                dialog.setMacroPanel(panelOptic);
                dialog.setVisible();
            }
        });
        jMenuProjects.add(jMenuItemEditProjects);
        jMenuProjects.addSeparator();
        
        File[] prjFiles = Utils.getAllProjectFiles();
        
        for (final File prjFile : prjFiles) {
            JMenuItem prjItem = new JMenuItem();
            prjItem.setText(prjFile.getName());
            prjItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                    
                    Project prj = new Project(Utils.getCurrentLocation() + "/" + prjFile.getName());
                    Macro macro = new Macro(prj.getMacro());
                    panelOptic.showProjects(macro.getParam().size(), prj.getName(), prj.getMacro(), prj.getDescription(), prj.getGraphic());                    
                }
            });
            jMenuProjects.add(prjItem);
        }
                
        jMenuItemExit.setText(labels.getString("mnuExit"));
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        jMenuFile.add(jMenuItemExit);
        jMenuBarMain.add(jMenuFile);
        
        jMenuHelp.setText(labels.getString("mnuHelp"));
        jMenuItemAbout.setText(labels.getString("mnuAbout"));
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);
        jMenuBarMain.add(jMenuHelp);

        setJMenuBar(jMenuBarMain);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jScrollPaneApp.getViewport().setView(panelOptic);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jScrollPaneApp))
                .addGap(10, 10, 10)
                ));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPaneApp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                ));

        pack();

        setTitle(eduKitTitle + " - Trial version");
        EduDescription.initDescription();

    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        AboutView about = new AboutView(null);
        about.setLocationRelativeTo(null);
        about.setVisible(true);
    }//GEN-LAST:event_jMenuAboutActionPerformed
    /*
     * Log String
     */

    public void logString(String msg) {
        jTextAreaLog.append(msg + System.getProperty("line.separator"));
        jTextAreaLog.setCaretPosition(jTextAreaLog.getText().length() - 1);

        // start logging if user chose to
        if (EduPatternShowOn.logging == 1) {
            try {
                BufferedWriter logFileOut = new BufferedWriter(new FileWriter("JDCedukit_ui.log"));
                jTextAreaLog.write(logFileOut);
                logFileOut.flush();
            } catch (Exception e) {
            }
        }
    }
    /*
     * Desc
     */

    public void getDesc(String msg) {
        jTextAreaDesc.append(msg + System.getProperty("line.separator"));
        jTextAreaDesc.setCaretPosition(jTextAreaLog.getText().length() - 1);
            try {
                BufferedWriter logFileOut = new BufferedWriter(new FileWriter("JDCedukit_ui.log"));
                jTextAreaDesc.write(logFileOut);
                logFileOut.flush();
            } catch (Exception e) {
            }
    }

    public void getDiagram(String msg) {
        jTextAreaLog.append(msg + System.getProperty("line.separator"));
        jTextAreaLog.setCaretPosition(jTextAreaLog.getText().length() - 1);
        // start logging if user chose to
        if (EduPatternShowOn.logging == 1) {
            try {
                BufferedWriter logFileOut = new BufferedWriter(new FileWriter("JDCedukit_ui.log"));
                jTextAreaLog.write(logFileOut);
                logFileOut.flush();
            } catch (Exception e) {
            }
        }
    }

    public void updatePattern(PatternImage pimage1) {
    }

    private void setTabPanelEnable(Container c, boolean enabled) {
        for (Component component : c.getComponents()) {
            component.setEnabled(enabled);
        }
        c.setEnabled(enabled);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBarMain;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemExperiments;
    private javax.swing.JMenu jMenuProjects;
    private javax.swing.JMenuItem jMenuItemEditProjects;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneApp;
    private javax.swing.JPanel jTabbedPaneOptics;
    private javax.swing.JTextArea jTextAreaLog;
    private javax.swing.JTextArea jTextAreaDesc;
    
    private EduControllerPattern panelOptic;
    // End of variables declaration   
    
    final private String eduKitTitle = "JDC Education Kit";
    private ArrayList<OpticsPane> panelist;
    static String mapLoadErrorMessage = "Grayscale map table file load error: ";
    
    static Locale[] supportedLocales = {
         Locale.ENGLISH,
         Locale.SIMPLIFIED_CHINESE,
         Locale.TRADITIONAL_CHINESE
      };
    
    int locale = 0;
    ResourceBundle labels;
}

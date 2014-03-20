/*
 * @(#)EduUIMainView.java
 *
 * Copyright (c) 2013 JASPER DISPLAY, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * JASPER DISPLAY PROPRIETARY:  Distribution of this source code
 * without permission from the copyright holder is strictly forbidden.
 */
package com.jasper.ui;

import com.jasper.core.OpticsPane;
import com.jasper.core.PatternImage;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

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
        jMenuLanguage = new javax.swing.JMenu();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuItemImport = new javax.swing.JMenuItem();
        jMenuItemImportExperiment = new javax.swing.JMenuItem();
        jMenuItemLanguageEnglish = new javax.swing.JMenuItem();
        jMenuItemLanguageChineseTraditional = new javax.swing.JMenuItem();
        jMenuItemLanguageChineseSimplified = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();
        panelOptic =  new EduControllerPattern(locale);

        // add optics panes to panelist
        panelist = new ArrayList<OpticsPane>();

        setTabPanelEnable(jTabbedPaneOptics, true);

        jTextAreaDesc.setColumns(20);
        jTextAreaDesc.setRows(5);
        jTextAreaDesc.setFont(new Font("Courier New", Font.PLAIN, 12));

        jTextAreaLog.setColumns(20);
        jTextAreaLog.setRows(5);
        jTextAreaLog.setFont(new Font("Courier New", Font.PLAIN, 12));
        jScrollPane2.setViewportView(jTextAreaLog);

        jMenuFile.setText(labels.getString("mnuFile"));
        jMenuItemImportExperiment.setText(labels.getString("mnuImportExperiment"));
        jMenuItemImportExperiment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemImportExperiment);
        jMenuItemImport.setText(labels.getString("mnuFileImport"));
        jMenuItemImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemImport);

        jMenuItemExit.setText(labels.getString("mnuExit"));
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBarMain.add(jMenuFile);

        jMenuLanguage.setText(labels.getString("mnuLanguage"));
        jMenuItemLanguageEnglish.setText(labels.getString("mnuItemLanguageEnglish"));
        jMenuItemLanguageEnglish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locale = 0;
            }
        });
        jMenuItemLanguageChineseTraditional.setText(labels.getString("mnuItemLanguageChineseTraditional"));
        jMenuItemLanguageChineseTraditional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locale = 1;
            }
        });
        
        jMenuItemLanguageChineseSimplified.setText(labels.getString("mnuItemLanguageChineseSimplified"));
        jMenuItemLanguageChineseSimplified.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locale = 2;
            }
        });

        jMenuLanguage.add(jMenuItemLanguageEnglish);
        jMenuLanguage.add(jMenuItemLanguageChineseTraditional);
        jMenuLanguage.add(jMenuItemLanguageChineseSimplified);
        jMenuBarMain.add(jMenuLanguage);

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
        //jScrollPaneApp.setViewportView(panelOptic);
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

    public void changeLabTitle(String msg) {
        logString("Optical experiment " + msg);
        this.setTitle(eduKitTitle + " (" + msg + ")");
        EduPatternShowOn.disablePatternFrame();
    }
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

    public void updateRegenerate() {
        for (OpticsPane op : panelist) {
            op.updateRegenerate();
            op.repaint();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBarMain;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenu jMenuLanguage;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemImport;
    private javax.swing.JMenuItem jMenuItemImportExperiment;
    private javax.swing.JMenuItem jMenuItemLanguageEnglish;
    private javax.swing.JMenuItem jMenuItemLanguageChineseTraditional;
    private javax.swing.JMenuItem jMenuItemLanguageChineseSimplified;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneApp;
    private javax.swing.JPanel jTabbedPaneOptics;
    private javax.swing.JTextArea jTextAreaLog;
    private javax.swing.JTextArea jTextAreaDesc;
    
    private javax.swing.JPanel panelOptic;
    // End of variables declaration   
    
    final private String eduKitTitle = "JDC Education Kit";
    private ArrayList<OpticsPane> panelist;
    static String mapLoadErrorMessage = "Grayscale map table file load error: ";
    
    static Locale[] supportedLocales = {
         Locale.ENGLISH,
         Locale.CHINA,
         Locale.TAIWAN
      };
    
    int locale = 0;
    ResourceBundle labels;
}

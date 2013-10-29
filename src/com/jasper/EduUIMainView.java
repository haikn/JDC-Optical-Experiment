/*
 * @(#)EduUIMainView.java
 *
 * Copyright (c) 2013 JASPER DISPLAY, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * JASPER DISPLAY PROPRIETARY:  Distribution of this source code
 * without permission from the copyright holder is strictly forbidden.
 */
package com.jasper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class EduUIMainView extends javax.swing.JFrame {

    /**
     * Creates new form EduUIMainView
     */
    public EduUIMainView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneOptics = new javax.swing.JPanel();
        tabbedGaneral = new javax.swing.JTabbedPane();
        jPanelGeneral = new javax.swing.JPanel();
        tabbedDesLog = new javax.swing.JTabbedPane();
        tabbedDiagram = new javax.swing.JTabbedPane();
        tabbedDes = new javax.swing.JTabbedPane();
        tabbedLog = new javax.swing.JTabbedPane();
        jPanelLensPattern = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelOptic = new EduControlerPattern();

       // panelSignalProcessing = new com.jasper.SignalProcessing();
        jPanelExperimentDefault = new ExperimentDefault();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollDesc = new javax.swing.JScrollPane();
        jTextAreaLog = new javax.swing.JTextArea();
        jTextAreaDesc = new javax.swing.JTextArea();
        jMenuBarMain = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();

        // add optics panes to panelist
        panelist = new ArrayList<OpticsPane>();
        //Todo


        //panelist.add((OpticsPane) jPanelLens1);
        // panelist.add((OpticsPane)jPanelLens2);
        panelist.add((OpticsPane) jPanelLens3);
        panelist.add((OpticsPane) jPanelLens4);
        panelist.add((OpticsPane) jPanelPhaseRetarder);
        panelist.add((OpticsPane) jPanelCylindrical2);
        // Microscope
        panelist.add((OpticsPane) jPanelMicroscope1);
        panelist.add((OpticsPane) jPanelMicroscope2);

        panelist.add((OpticsPane) jPanelMirror1);
        panelist.add((OpticsPane) jPanelMirror2);
        panelist.add((OpticsPane) jPanelMirror3);
        panelist.add((OpticsPane) jPanelMirror4);
        // Aber
        panelist.add((OpticsPane) jPanelAber1);
        // Mach
        panelist.add((OpticsPane) jPanelMach1);
        // Michelson
        panelist.add((OpticsPane) jPanelMich1);
        panelist.add((OpticsPane) jPanelMich2);
        panelist.add((OpticsPane) jPanelMich3);
        panelist.add((OpticsPane) jPanelMich4);
        // Diffraction
        panelist.add((OpticsPane) jPanelDiffraction1);
        // Spectrometer
        panelist.add((OpticsPane) jPanelSpectrometer1);
        // Signal Processing
        panelist.add((OpticsPane) jPanelSignal1);
        // Phase
        panelist.add((OpticsPane) jPanelPhase1);
        // Tabot
        panelist.add((OpticsPane) jPanelTalbot1);
        // Wavefront
        panelist.add((OpticsPane) jPanelWavefront1);
        //Wavelength
        panelist.add((OpticsPane) jPanelWavelength1);

        panelist.add((OpticsPane) jPanelTest);

        panelist.add((OpticsPane) jPanelDescriptionLens);
        panelist.add((OpticsPane) jPanelCalibration);
        panelPattern = new EduPatternJPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // BEGIN paint boot screen, a lens pattern
        ((ExperimentDefault) jPanelExperimentDefault).bootScreen();

        jTabbedPaneOptics.add(panelOptic);
       
        setTabPanelEnable(jTabbedPaneOptics, true);

        jTextAreaDesc.setColumns(20);
        jTextAreaDesc.setRows(5);
        jTextAreaDesc.setFont(new Font("Courier New", Font.PLAIN, 12));
        tabbedDesLog.addTab("Description", jScrollDesc);

        jTextAreaLog.setColumns(20);
        jTextAreaLog.setRows(5);
        jTextAreaLog.setFont(new Font("Courier New", Font.PLAIN, 12));
        jScrollPane2.setViewportView(jTextAreaLog);
        tabbedDesLog.addTab("Log", jScrollPane2);

        tabbedDiagram.addTab("Diagram", null);

        jLabel1 = new javax.swing.JLabel("Select experiment:");
        jLabel1.setForeground(Color.red);

        jMenuFile.setText("File");
        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jMenuItemExitActionPerformed(evt);
                System.exit(0);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBarMain.add(jMenuFile);

        jMenuHelp.setText("Help");

        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jMenuBarMain.add(jMenuHelp);

        setJMenuBar(jMenuBarMain);

        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();


        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanelCalibration = new javax.swing.JPanel();
       // jPanelSignalProcessing = new SignalProcessing();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jPanelPhaseRetarder = new javax.swing.JPanel();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        panelist.add((OpticsPane) jPanelSlit);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        layoutDescription = new javax.swing.JLayeredPane();
        layoutControl = new javax.swing.JLayeredPane();
        layoutDiagram = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        layoutDescription.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        layoutDiagram.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(panelOptic)
                //.addGroup(layout.createSequentialGroup()
                //.addComponent(tabbedDesLog, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                //.addGap(11, 11, 11)
                //.addComponent(tabbedDiagram, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                //)
                )
                .addGap(5, 5, 5)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelOptic, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                //.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                //.addComponent(tabbedDesLog, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                //.addComponent(tabbedDiagram)
                //)
                .addGap(5, 5, 5)));

        pack();

//        jTabbedPaneOptics.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent e) {
////                System.out.println("stateChanged " + jTabbedPaneOptics.getSelectedComponent());
//                OpticsPane comp = (OpticsPane) (jTabbedPaneOptics.getSelectedComponent());
//                if (comp != null) {
//                    comp.updatePatternScreen();
//                }
//            }
//        });


        setTitle(eduKitTitle);

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
    /*
     * Diagram
     */
    /*
     * Desc
     */

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EduUIMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EduUIMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EduUIMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EduUIMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EduUIMainView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBarMain;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JPanel jPanelPhaseRetarder;
    private javax.swing.JPanel jPanelCylindrical2;
    private javax.swing.JPanel jPanelLensPattern;
    private javax.swing.JPanel panelOptic;
    private javax.swing.JPanel jPanelLens2;
    private javax.swing.JPanel jPanelLens3;
    private javax.swing.JPanel jPanelLens4;
    private javax.swing.JPanel jPanelMirror1;
    private javax.swing.JPanel jPanelMicroscope1;
    private javax.swing.JPanel jPanelMicroscope2;
    private javax.swing.JPanel jPanelMirror2;
    private javax.swing.JPanel jPanelMirror3;
    private javax.swing.JPanel jPanelMirror4;
    private javax.swing.JPanel jPanelAber1;
    private javax.swing.JPanel jPanelMach1;
    private javax.swing.JPanel jPanelMich1;
    private javax.swing.JPanel jPanelMich2;
    private javax.swing.JPanel jPanelMich3;
    private javax.swing.JPanel jPanelMich4;
    private javax.swing.JPanel jPanelDiffraction1;
    private javax.swing.JPanel jPanelSpectrometer1;
    private javax.swing.JPanel jPanelSignal1;
    private javax.swing.JPanel jPanelPhase1;
    private javax.swing.JPanel jPanelTalbot1;
    private javax.swing.JPanel jPanelWavefront1;
    private javax.swing.JPanel jPanelWavelength1;
    private javax.swing.JPanel jPanelTest;
    private javax.swing.JPanel panelSignalProcessing;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollDesc;
    private javax.swing.JPanel jTabbedPaneOptics;
    private javax.swing.JTextArea jTextAreaLog;
    private javax.swing.JTextArea jTextAreaDesc;
    private javax.swing.JPanel jPanelDescriptionLens;
    private javax.swing.JPanel jPanelDescriptionMicroscope;
    private javax.swing.JPanel jPanelDescriptionAberration;
    private javax.swing.JPanel jPanelDescriptionMicheson;
    private javax.swing.JPanel jPanelExperimentDefault;
    private javax.swing.JPanel jPanelCalibration;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    // Variables declaration - do not modify                     
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JPanel jPanelSlit;
    private javax.swing.JPanel jPanelGeneral;
    private javax.swing.JTabbedPane tabbedDes;
    private javax.swing.JTabbedPane tabbedLog;
    private javax.swing.JTabbedPane tabbedDesLog;
    private javax.swing.JTabbedPane tabbedDiagram;
    private javax.swing.JPanel jPanelSignalProcessing;
    private javax.swing.JTabbedPane tabbedGaneral;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    // Variables declaration - do not modify                     
    private javax.swing.JLayeredPane layoutControl;
    private javax.swing.JLayeredPane layoutDescription;
    private javax.swing.JLayeredPane layoutDiagram;
    private javax.swing.JLayeredPane layoutPattern;
    private javax.swing.JPanel panelCalebration;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JPanel panelPhase;
    private javax.swing.JPanel panelSignal;
    private javax.swing.JPanel panelSlit;
    private javax.swing.JTabbedPane tabbedControl;
    private javax.swing.JPanel panelPattern;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration   
    final private String labTitleTele = " 1. Telephoto Lens";
    final private String labTitleMicr = " 2. Microscope";
    final private String labTitleAber = " 3. Aberration (Foucault test)";
    final private String labTitleMich = " 4. Michelson Interferometer";
    final private String labTitleDiff = " 5. Diffraction and Interference";
    final private String labTitleSpec = " 6. Spectrometer";
    final private String labTitleSign = " 7. Signal Processing (4-f system)";
    final private String labTitlePhas = " 8. Phase Shifting Digital Holography";
    final private String labTitleTalb = " 9. Talbot Images";
    final private String labTitleFron = " 10. Wavefront Modulation";
    final private String labTitleLeng = " 11. Wavelength Selective Switch";
    final private String eduKitTitle = "JDC Education Kit";
    private ArrayList<OpticsPane> panelist;
    static String mapLoadErrorMessage = "Grayscale map table file load error: ";
}

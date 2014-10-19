/*
 * @(#)EduControllerPattern.java
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

import static com.jasper.ui.EduPatternShowOn.patternFrameDoubleClick;
import com.jasper.core.OpticsPane;
import com.jasper.core.PatternImage;
import com.jasper.ui.panel.amplitude.AmplitudePanel;
import com.jasper.ui.panel.phasemodulation.BeamSteere;
import com.jasper.ui.panel.BeamShiftingPanel;
import com.jasper.ui.panel.DynamicPanel;
import com.jasper.ui.panel.StaticPanel;
import com.jasper.ui.panel.michelson.CyllindricalMichelsonPanel;
import com.jasper.ui.panel.wavefront.CyllindricalWavefrontPanel;
import com.jasper.ui.panel.diffraction.DoubleSlitPanel;
import com.jasper.ui.panel.cgh.CGH10Panel;
import com.jasper.ui.panel.cgh.CGH1Panel;
import com.jasper.ui.panel.cgh.CGH3Panel;
import com.jasper.ui.panel.cgh.CGH4Panel;
import com.jasper.ui.panel.cgh.CGH5Panel;
import com.jasper.ui.panel.cgh.CGH6Panel;
import com.jasper.ui.panel.cgh.CGH8Panel;
import com.jasper.ui.panel.michelson.LensMichelsonPanel;
import com.jasper.ui.panel.wavefront.LensWavefrontPanel;
import com.jasper.ui.panel.michelson.MirrorMichelsonPanel;
import com.jasper.ui.panel.wavefront.MirrorWavefrontPanel;
import com.jasper.ui.panel.slmbasic.SLMBasicPanel;
import com.jasper.ui.panel.phaseshifting.PhaseRetarderPanel;
import com.jasper.ui.panel.signalprocessing.SignalPanel;
import com.jasper.ui.panel.signalprocessing.SignalPhotoPanel;
import com.jasper.ui.panel.diffraction.SingleSlitPanel;
import com.jasper.ui.panel.spectrometer.SpectremeterPanel;
import com.jasper.ui.panel.talbot.TalbotPanel;
import com.jasper.ui.panel.talbot.TalbotPhotoPanel;
import com.jasper.utils.Utils;
import com.jasper.ui.widget.DoubleJSlider;
import com.jasper.ui.widget.paramsPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

/**
 *
 * @author sonnv
 */
public class EduControllerPattern extends OpticsPane {
    /**
    * Static variables
    */
    static int patternWidth = 563;
    static int patternHeight = 368;
    
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    
     // Variables declaration
    public javax.swing.JPanel panelPattern;
    public javax.swing.JPanel panelPatternFullScreen;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    private javax.swing.JComboBox comboBoxExperiments;
    private javax.swing.JTabbedPane jTabbedControler;
    private javax.swing.JLayeredPane layoutControl;
    private javax.swing.JPanel panelGeneral;
    
    private javax.swing.JTabbedPane tabbedControl;
    private javax.swing.JPanel jPanelPattern;
    private javax.swing.JLabel lblSelectExperiment;
    private javax.swing.JTabbedPane tabbedPaneOptics;
    private javax.swing.JPanel layoutDiagram;
    private javax.swing.JPanel layoutDiagramFull;
    private byte layoutDiagramFullOpen = 0;
    private byte layoutDescriptionFullOpen = 0;
    
    DoubleJSlider slider;
    //
    private javax.swing.JTabbedPane tabbedDesLog;
    private javax.swing.JPanel tabbedDiagram;
    private javax.swing.JTextArea jTextAreaLog;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollDes;
    private javax.swing.JPanel diagramLens;
    private javax.swing.JPanel diagramLensFull;
    private javax.swing.JLabel lblDiagram;
    private javax.swing.JLabel lblDiagramFull;
    private JFrame magFrameLenon;
    private JFrame diagramFullScreen;
    private JFrame descriptionFullScreen;
    // Description
    private javax.swing.JTextArea desFullScreen;
    private javax.swing.JLabel desNoSelect = new JLabel("");
    private javax.swing.JTextArea desSLM = EduDescription.desSLM;
    private javax.swing.JTextArea desAmplitude = EduDescription.desAmplitude;
    private javax.swing.JTextArea desPhaseModulation = EduDescription.desPhaseModulation;
    private javax.swing.JTextArea desMichelson = EduDescription.desMichelson;
    private javax.swing.JTextArea desDiffaction = EduDescription.desDiffaction;
    private javax.swing.JTextArea desSpectrometer = EduDescription.desSpectrometer;
    private javax.swing.JTextArea desSignalProcessing = EduDescription.desSignalProcessing;
    private javax.swing.JTextArea desPhaseRetarder = EduDescription.desPhaseRetarder;
    private javax.swing.JTextArea desTalbot = EduDescription.desTalbot;
    private javax.swing.JTextArea desWavefront = EduDescription.desWavefront;
    private javax.swing.JTextArea desBeamShifting = EduDescription.desBeamShifting;
    private javax.swing.JTextArea  desImportFormula = EduDescription.desImportFormula;
    JPopupMenu menu = new JPopupMenu("Popup");
    
    // Temp
    private byte tmpSelected = 0;
    
    private javax.swing.JPanel buttonPanel;
    
    // Experiment 1
    private SLMBasicPanel slmBasicPanel;
    // Experiment 1
    private AmplitudePanel amplitudePanel;
    // Experiment 3
    private BeamSteere beamSteerePanel;
    // Experiment 4
    private MirrorMichelsonPanel mirrorMichelsonPanel;
    private LensMichelsonPanel lensMichelsonPanel;
    private CyllindricalMichelsonPanel cyllindricalMichelsonPanel;
    // Experiment 5
    private SingleSlitPanel singleSlitPanel;
    private DoubleSlitPanel doubleSlitPanel;
    // Experiment 6
    private SpectremeterPanel spectremeterPanel;
    // Experiment 7
    private SignalPhotoPanel signalPhotoPanel;
    private SignalPanel signalPanel;
    // Experiment 8
    private PhaseRetarderPanel phaseRetarderPanel;
    // Experiment 9
    private TalbotPanel talbotPanel;
    private TalbotPhotoPanel talbotPhotoPanel;
    // Experiment 10
    private MirrorWavefrontPanel mirrorWavefrontPanel;
    private LensWavefrontPanel lensWavefrontPanel;
    private CyllindricalWavefrontPanel cyllindricalWavefrontPanel;
    // CGH 1
    private CGH1Panel cgh1Panel;
    // CGH 3
    private CGH3Panel cgh3Panel;
    // CGH 4
    private CGH4Panel cgh4Panel;
    // CGH 5
    private CGH5Panel cgh5Panel;
    // CGH 6
    private CGH6Panel cgh6Panel;
    // CGH 8
    private CGH8Panel cgh8Panel;
    // CGH 10
    private CGH10Panel cgh10Panel;
    
    // Beam Shifting tab
    private BeamShiftingPanel beamShiftingPanel;
    // Dynamic tab
    private DynamicPanel dynamicPanel;
    // Dynamic tab
    private StaticPanel staticPanel;
    // Import formula
    //private ImportFormulaPanel importFormulaPanel;
    
    public PatternImage pimg;
    private JPanel projectPanel = new JPanel(new CardLayout());
    
    private paramsPanel paramsPrjPanel;

    public EduControllerPattern(int locale) {
        labels = ResourceBundle.getBundle("resources/Text", EduUIMainView.supportedLocales[locale]);
        if (!Utils.isMac()) {
            patternWidth = 563;
            patternHeight = 368;
        } else {
            patternWidth = 568;
            patternHeight = 431;
        }
        initComponents();
        image1 = ((EduPatternJPanel) panelPattern).pimage;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        
        tabbedDesLog = new javax.swing.JTabbedPane();
        tabbedDiagram = new javax.swing.JPanel();
        jTextAreaLog = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollDes = new javax.swing.JScrollPane();
        diagramLensFull = new javax.swing.JPanel();
        diagramLens = new javax.swing.JPanel();
        lblDiagram = new javax.swing.JLabel();
        lblDiagramFull = new javax.swing.JLabel();

        panelPattern = new EduPatternJPanel();
        tabbedPaneOptics = new javax.swing.JTabbedPane();
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        layoutControl = new javax.swing.JLayeredPane();
        layoutDiagram = new javax.swing.JPanel();
        layoutDiagramFull = new javax.swing.JPanel();
        tabbedControl = new javax.swing.JTabbedPane();
        panelGeneral = new javax.swing.JPanel();
        lblSelectExperiment = new javax.swing.JLabel();
        comboBoxExperiments = new javax.swing.JComboBox();
        jTabbedControler = new javax.swing.JTabbedPane();
        
        jPanelPattern = new javax.swing.JPanel();
        
        // create CGH log file
        Utils.createDirectoryLogFileCGH();

        tabbedControl.setAlignmentX(100);
        // Boot screen
        PatternImage imageBoot = ((EduPatternJPanel) panelPattern).pimage;
        imageBoot.paintDefault();
        
        lblSelectExperiment.setText(labels.getString("lblSelectExperiment"));
        lblSelectExperiment.setForeground(Color.red);

        comboBoxExperiments.setModel(new javax.swing.DefaultComboBoxModel(new String[]{labels.getString("itemSelectExperiment"), labels.getString("itemSLM"), labels.getString("itemAmplitude"),
        labels.getString("itemPhaseModulation"), labels.getString("itemMichelson"), labels.getString("itemDiffraction"),
        labels.getString("itemSpectrometer"), labels.getString("itemSignalProcessing"), labels.getString("itemPhaseShifting"), 
        labels.getString("itemTalbot"), labels.getString("itemWavefront")}));
        comboBoxExperiments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox comboBox = (JComboBox) evt.getSource();

                Object selected = comboBox.getSelectedItem();
                if (selected.equals(labels.getString("itemSelectExperiment"))) {
                    menuItemNoSelectActionPerformed(evt);
                    tmpSelected = 0;
                    generateActionPerformedDefault(evt);
                } else if (selected.equals(labels.getString("itemSLM"))) {
                    menuItemSLMBasicActionPerformed(evt);
                    tmpSelected = 1;
                } else if (selected.equals(labels.getString("itemAmplitude"))) {
                    menuItemAmplitudeActionPerformed(evt);
                    tmpSelected = 2;
                } else if (selected.equals(labels.getString("itemPhaseModulation"))) {
                    menuItemPhaseModulationActionPerformed(evt);
                    tmpSelected = 3;
                } else if (selected.equals(labels.getString("itemMichelson"))) {
                    menuItemMichelsonActionPerformed(evt);
                    tmpSelected = 4;
                } else if (selected.equals(labels.getString("itemDiffraction"))) {
                    menuItemDiffractionActionPerformed(evt);
                    tmpSelected = 5;
                } else if (selected.equals(labels.getString("itemSpectrometer"))) {
                    menuItemSpectrometerActionPerformed(evt);
                    tmpSelected = 6;
                } else if (selected.equals(labels.getString("itemSignalProcessing"))) {
                    menuItemSignalProcessingActionPerformed(evt);
                    tmpSelected = 7;
                } else if (selected.equals(labels.getString("itemPhaseShifting"))) {
                    menuItemPhaseShiftingActionPerformed(evt);
                    tmpSelected = 8;
                } else if (selected.equals(labels.getString("itemTalbot"))) {
                    menuItemTalbotActionPerformed(evt);
                    tmpSelected = 9;
                } else if (selected.equals(labels.getString("itemWavefront"))) {
                    menuItemWavefrontActionPerformed(evt);
                    tmpSelected = 10;
                }
            }
        });
        
        // General tab
        // Experiment 1
        slmBasicPanel = new SLMBasicPanel(labels, bindingGroup, panelPattern);
        // Experiment 2
        amplitudePanel = new AmplitudePanel(labels, bindingGroup, panelPattern);
        // Experiment 3
        beamSteerePanel = new BeamSteere(labels, bindingGroup, panelPattern, jTabbedControler);
        // Experiment 4
        //jTabbedControler.addTab(labels.getString("tabLens"), jPanelLens);
        lensMichelsonPanel = new LensMichelsonPanel(labels, bindingGroup, panelPattern);
        cyllindricalMichelsonPanel = new CyllindricalMichelsonPanel(labels, bindingGroup, panelPattern);
        mirrorMichelsonPanel = new MirrorMichelsonPanel(labels, bindingGroup, panelPattern);
        // Experiment 5
        singleSlitPanel = new SingleSlitPanel(labels, bindingGroup, panelPattern);
        doubleSlitPanel = new DoubleSlitPanel(labels, bindingGroup, panelPattern);
        // Experiment 6
        spectremeterPanel = new SpectremeterPanel(labels, bindingGroup, panelPattern);
        // Experiment 7
        signalPanel = new SignalPanel(labels, bindingGroup, panelPattern);
        signalPhotoPanel = new SignalPhotoPanel(labels, bindingGroup, panelPattern);
        // Experiment 8
        phaseRetarderPanel = new PhaseRetarderPanel(labels, bindingGroup, panelPattern);
        // Experiment 9
        talbotPanel = new TalbotPanel(labels, bindingGroup, panelPattern);
        talbotPhotoPanel = new TalbotPhotoPanel(labels, bindingGroup, panelPattern);
        // Experiment 10
        lensWavefrontPanel = new LensWavefrontPanel(labels, bindingGroup, panelPattern);
        cyllindricalWavefrontPanel = new CyllindricalWavefrontPanel(labels, bindingGroup, panelPattern, jTabbedControler);
        mirrorWavefrontPanel = new MirrorWavefrontPanel(labels, bindingGroup, panelPattern, jTabbedControler);
      
        // CGH 1
        cgh1Panel = new CGH1Panel(labels, bindingGroup, panelPattern);
        // CGH 3
        cgh3Panel = new CGH3Panel(labels, bindingGroup, panelPattern);
        // CGH 4
        cgh4Panel = new CGH4Panel(labels, bindingGroup, panelPattern);
        // CGH 5
        cgh5Panel = new CGH5Panel(labels, bindingGroup, panelPattern);
        // CGH 6
        cgh6Panel = new CGH6Panel(labels, bindingGroup, panelPattern);
        // CGH 8
        cgh8Panel = new CGH8Panel(labels, bindingGroup, panelPattern);
        // CGH 10
        cgh10Panel = new CGH10Panel(labels, bindingGroup, panelPattern);
        
        // Beam Shifting tab
        beamShiftingPanel = new BeamShiftingPanel(labels, bindingGroup, panelPattern);
        // Dynamic tab
        dynamicPanel = new DynamicPanel(labels, bindingGroup, panelPattern);
        // Static tab
        staticPanel = new StaticPanel(labels, bindingGroup, panelPattern);
        // Import formula
        //importFormulaPanel = new ImportFormulaPanel(labels, bindingGroup, panelPattern, tabbedControl);
        
        // layout frame
        buttonPanel = new javax.swing.JPanel();
        tabbedPaneOptics.hide();
        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        if (!Utils.isMac()) {
            panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabbedPaneOptics, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelGeneralLayout.createSequentialGroup()
                                .addComponent(lblSelectExperiment, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxExperiments, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                )
            );
            panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectExperiment)
                    .addComponent(comboBoxExperiments, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabbedPaneOptics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            );
        } else {
            panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabbedPaneOptics, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelGeneralLayout.createSequentialGroup()
                                .addComponent(lblSelectExperiment, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxExperiments, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                )
            );
            panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectExperiment)
                    .addComponent(comboBoxExperiments, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(tabbedPaneOptics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            );  
        }
        
        tabbedControl.setPreferredSize(new Dimension(655,600));
        // General tab
        tabbedControl.addTab(labels.getString("tabGeneral"), panelGeneral);
        // BeamShifting tab
        tabbedControl.addTab(labels.getString("tabBeamShifting"), beamShiftingPanel.getPanel());
        // Static tab
        //tabbedControl.addTab(labels.getString("tabStatic"), staticPanel.getPanel());
        // Dynamic tab
        //tabbedControl.addTab(labels.getString("tabDynamic"), dynamicPanel.getPanel());
        // Dynamic tab
        //tabbedControl.addTab(labels.getString("tabImportFormula"), importFormulaPanel.getPanel());
        
        tabbedControl.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index == 1) {
                    //generateActionPerformedDefault(null);
                    layoutDiagram.removeAll();
                    diagramLens.removeAll();
                    tabbedDesLog.removeAll();
                    
                    jScrollDes.getViewport();
                    tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                    
                    jScrollPane2.setViewportView(beamShiftingPanel.getLogArea());
                    tabbedDesLog.addTab(labels.getString("tabLog"), jScrollPane2);

                    lblDiagram.setIcon(lblDiagram.getIcon());
                    lblDiagram.setText(lblDiagram.getText());
                    diagramLens.add(lblDiagram);
                    layoutDiagram.add(diagramLens);
                    
                } else if (index == 0) {
                    layoutDiagram.removeAll();
                    diagramLens.removeAll();
                    tabbedDesLog.removeAll();
                    // check selected
                    if (tmpSelected == 0) {
                        //jScrollDes.setViewportView(desSLM);
                        tabbedDesLog.addTab(labels.getString("tabDescription"), desNoSelect);
                        jTextAreaLog.setColumns(20);
                        jTextAreaLog.setRows(5);
                        jScrollPane2.setViewportView(jTextAreaLog);
                        tabbedDesLog.addTab(labels.getString("tabLog"), jScrollPane2);
                        
                        lblDiagram.setIcon(null);
                        lblDiagram.setText(null);
                        diagramLens.add(lblDiagram);
                        layoutDiagram.add(diagramLens);
                    } else if (tmpSelected == 1) {
                        jScrollDes.setViewportView(desSLM);
                        tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                        
                        jScrollPane2.setViewportView(slmBasicPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);

                        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp_1_2_3.png")));
                        lblDiagram.setText(null);
                        diagramLens.add(lblDiagram);
                        layoutDiagram.add(diagramLens);
                    } else if (tmpSelected == 2) {
                        jScrollDes.setViewportView(desAmplitude);
                        tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                        
                        //jScrollPane2.setViewportView(amplitudePanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);

                        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp_1_2_3.png")));
                        lblDiagram.setText(null);
                        diagramLens.add(lblDiagram);
                        layoutDiagram.add(diagramLens);
                    } else if (tmpSelected == 3) {
                        jScrollDes.setViewportView(desPhaseModulation);
                        tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                        
                        jScrollPane2.setViewportView(beamSteerePanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);

                        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp_1_2_3.png")));
                        lblDiagram.setText(null);
                        diagramLens.add(lblDiagram);
                        layoutDiagram.add(diagramLens);
                    } else if (tmpSelected == 4) {
                        jScrollDes.setViewportView(desMichelson);
                        tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                        jScrollPane2.setViewportView(lensMichelsonPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);

                        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp4michelson.png")));
                        lblDiagram.setText(null);
                        diagramLens.add(lblDiagram);
                        layoutDiagram.add(diagramLens);
                    } else if (tmpSelected == 5) {
                        jScrollDes.setViewportView(desDiffaction);
                        tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                        
                        jScrollPane2.setViewportView(singleSlitPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);

                        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp5diffraction.png")));
                        lblDiagram.setText(null);
                        diagramLens.add(lblDiagram);
                        layoutDiagram.add(diagramLens);
                    } else if (tmpSelected == 6) {
                        jScrollDes.setViewportView(desSpectrometer);
                        tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                        
                        jScrollPane2.setViewportView(spectremeterPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);

                        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp6spectrometer.png")));
                        lblDiagram.setText(null);
                        diagramLens.add(lblDiagram);
                        layoutDiagram.add(diagramLens);
                    } else if (tmpSelected == 7) {
                        jScrollDes.setViewportView(desSignalProcessing);
                        tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                        
                        jScrollPane2.setViewportView(signalPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);

                        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp7signalprocessing.png")));
                        lblDiagram.setText(null);
                        diagramLens.add(lblDiagram);
                        layoutDiagram.add(diagramLens);
                    } else if (tmpSelected == 8) {
                        jScrollDes.setViewportView(desPhaseRetarder);
                        tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                        
                        jScrollPane2.setViewportView(phaseRetarderPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);

                        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp8phaseshifting.png")));
                        lblDiagram.setText(null);
                        diagramLens.add(lblDiagram);
                        layoutDiagram.add(diagramLens);
                    } else if (tmpSelected == 9) {
                        jScrollDes.setViewportView(desTalbot);
                        tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                        
                        jScrollPane2.setViewportView(talbotPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);

                        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp9talbot.png")));
                        lblDiagram.setText(null);
                        diagramLens.add(lblDiagram);
                        layoutDiagram.add(diagramLens);
                    } else if (tmpSelected == 10) {
                        jScrollDes.setViewportView(desWavefront);
                        tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                        
                        jScrollPane2.setViewportView(lensWavefrontPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);

                        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp10wavefrontmodulation.png")));
                        lblDiagram.setText(null);
                        diagramLens.add(lblDiagram);
                        layoutDiagram.add(diagramLens);
                    }
                    
                } else if (index == 2) {
                    layoutDiagram.removeAll();
                    diagramLens.removeAll();
                    tabbedDesLog.removeAll();
                    
                    jScrollDes.getViewport();
                    tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                    
                    jScrollPane2.setViewportView(staticPanel.getLogArea());
                    tabbedDesLog.addTab(labels.getString("tabLog"), jScrollPane2);

                    lblDiagram.setIcon(lblDiagram.getIcon());
                    lblDiagram.setText(lblDiagram.getText());
                    diagramLens.add(lblDiagram);
                    layoutDiagram.add(diagramLens);
                    
                } else if (index == 3) {
                    layoutDiagram.removeAll();
                    diagramLens.removeAll();
                    tabbedDesLog.removeAll();
                    
                    jScrollDes.getViewport();
                    tabbedDesLog.addTab(labels.getString("tabDescription"), jScrollDes);
                    
                    //jScrollPane2.setViewportView(beamShiftingPanel.getLogArea());
                    tabbedDesLog.addTab(labels.getString("tabLog"), jScrollPane2);

                    lblDiagram.setIcon(lblDiagram.getIcon());
                    lblDiagram.setText(lblDiagram.getText());
                    diagramLens.add(lblDiagram);
                    layoutDiagram.add(diagramLens);
                    
                }
            }
        });
        
        if (!Utils.isMac()) {
            tabbedControl.setBounds(580, 0, 665, 370);
        } else {
            tabbedControl.setBounds(578, 0, 678, 442);
        }
        
        
        JPanel experimentsPanel = new JPanel();
        //experimentsPanel.setPreferredSize(new Dimension(655, 455));
        experimentsPanel.add(tabbedControl);
        JScrollPane jsp = new JScrollPane(tabbedControl);
        jsp.setPreferredSize(new Dimension(655, 665));
        experimentsPanel.add(jsp);
        experimentsPanel.setBounds(580, 0, 665, 370);
        //experimentsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //tabbedControl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        projectPanel.setBounds(580, 0, 665, 370);
        projectPanel.add(experimentsPanel, labels.getString("mnuExperiments"));
        
                        
        layoutControl.add(projectPanel, javax.swing.JLayeredPane.DEFAULT_LAYER); 

        javax.swing.GroupLayout jPanelPatternLayout = new javax.swing.GroupLayout(jPanelPattern);
        jPanelPattern.setLayout(jPanelPatternLayout);
        jPanelPatternLayout.setHorizontalGroup(
                jPanelPatternLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 560, Short.MAX_VALUE));
        jPanelPatternLayout.setVerticalGroup(
                jPanelPatternLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 290, Short.MAX_VALUE));
        
        panelPattern.setBounds(8, 5, patternWidth, patternHeight);
        panelPattern.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 1));
        
        //  BEGIN show full screen
        layoutControl.add(panelPattern, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layoutControl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
              if (evt.getClickCount() == 2) {
                  Utils.setDevice();
                  patternFrameDoubleClick.show();
              }
            }
        });
        //layoutControl.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        //  END show full screen
        
        tabbedDesLog.addTab("Description", null);
        jTextAreaLog.setColumns(20);
        jTextAreaLog.setRows(5);
        jTextAreaLog.setFont(Utils.getFont());
        jScrollPane2.setViewportView(jTextAreaLog);
        tabbedDesLog.addTab("Log", jScrollPane2);
        
        popupMenuLanguage();
        desSLM.addMouseListener(new PopupTriggerListener());
        desAmplitude.addMouseListener(new PopupTriggerListener());
        desPhaseModulation.addMouseListener(new PopupTriggerListener());
        desMichelson.addMouseListener(new PopupTriggerListener());
        desDiffaction.addMouseListener(new PopupTriggerListener());
        desSpectrometer.addMouseListener(new PopupTriggerListener());
        desSignalProcessing.addMouseListener(new PopupTriggerListener());
        desPhaseRetarder.addMouseListener(new PopupTriggerListener());
        desTalbot.addMouseListener(new PopupTriggerListener());
        desWavefront.addMouseListener(new PopupTriggerListener());
        
        desBeamShifting.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
              if (evt.getClickCount() == 2) {
                  desFullScreen = new javax.swing.JTextArea();
                  desFullScreen.setLineWrap(true);
                  desFullScreen.setWrapStyleWord(true);
                  desFullScreen.setEditable(false);
                  desFullScreen.setOpaque(false);
                  descriptionFullScreen = new JFrame("JDC Education Kit - Description full screen");
                
                if(layoutDescriptionFullOpen ==  0){
                    desFullScreen.setText(desBeamShifting.getText());
                
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
                    Toolkit kit = Toolkit.getDefaultToolkit();
                    Image img = kit.createImage(url);
                    descriptionFullScreen.setIconImage(img);
                    descriptionFullScreen.getContentPane().add(desFullScreen);
                    descriptionFullScreen.pack();
                    layoutDescriptionFullOpen++;

                    descriptionFullScreen.setBounds(0,0,screenSize.width, screenSize.height);
                    descriptionFullScreen.setVisible(true);
                   
                    descriptionFullScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    descriptionFullScreen.setAlwaysOnTop(true);
                    descriptionFullScreen.setResizable(true);
                    descriptionFullScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                            layoutDescriptionFullOpen = 0;
                            descriptionFullScreen.dispose();
                    }
                    });
                   descriptionFullScreen.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                          if (evt.getClickCount() == 2) {
                            layoutDescriptionFullOpen = 0;
                            descriptionFullScreen.dispose();
                          }
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "This window is already open");
                }
              }
            }
        });
        //  END show full screen for desBeamShifting
        // BEGIN show full screen for desImportFormula
        desImportFormula.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
              if (evt.getClickCount() == 2) {
                desFullScreen = new javax.swing.JTextArea();
                desFullScreen.setLineWrap(true);
                desFullScreen.setWrapStyleWord(true);
                desFullScreen.setEditable(false);
                desFullScreen.setOpaque(false);
                
                descriptionFullScreen = new JFrame("JDC Education Kit - Description full screen");
                
                if(layoutDescriptionFullOpen ==  0){
                    desFullScreen.setText(desImportFormula.getText());
                
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
                    Toolkit kit = Toolkit.getDefaultToolkit();
                    Image img = kit.createImage(url);
                    descriptionFullScreen.setIconImage(img);
                    descriptionFullScreen.getContentPane().add(desFullScreen);
                    descriptionFullScreen.pack();
                    layoutDescriptionFullOpen++;

                    descriptionFullScreen.setBounds(0,0,screenSize.width, screenSize.height);
                    descriptionFullScreen.setVisible(true);
                   
                    descriptionFullScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    descriptionFullScreen.setAlwaysOnTop(true);
                    descriptionFullScreen.setResizable(true);
                    descriptionFullScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                            layoutDescriptionFullOpen = 0;
                            descriptionFullScreen.dispose();
                    }
                    });
                    descriptionFullScreen.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                          if (evt.getClickCount() == 2) {
                             layoutDescriptionFullOpen = 0;
                            descriptionFullScreen.dispose();
                          }
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "This window is already open");
                }
              }
            }
        });
        //  END show full screen for desImportFormula

        //tabbedDiagram.addTab("Diagram", null);
        layoutDiagram.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tabbedDiagram.setBounds(580, 580, 665, 335);
        layoutDiagram.add(tabbedDiagram, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        // BEGIN show full screen for layoutDiagram
        layoutDiagram.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
              if (evt.getClickCount() == 2) {
                  diagramFullScreen = new JFrame("JDC Education Kit - Diagram full screen");
                
                if(layoutDiagramFullOpen ==  0){
                    lblDiagramFull.setIcon(lblDiagram.getIcon());
                    lblDiagramFull.setText(lblDiagram.getText());
                    diagramLensFull.add(lblDiagramFull);
                    layoutDiagramFull.add(diagramLensFull);

                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
                    Toolkit kit = Toolkit.getDefaultToolkit();
                    Image img = kit.createImage(url);
                    diagramFullScreen.setIconImage(img);
                    diagramFullScreen.getContentPane().add(layoutDiagramFull);
                    diagramFullScreen.pack();
                    layoutDiagramFullOpen++;

                    diagramFullScreen.setBounds(0,0,screenSize.width, screenSize.height);
                    diagramFullScreen.setVisible(true);

                    diagramFullScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    diagramFullScreen.setAlwaysOnTop(true);
                    diagramFullScreen.setResizable(true);
                    diagramFullScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                            layoutDiagramFullOpen = 0;
                            diagramFullScreen.dispose();
                    }
                    });
                    diagramFullScreen.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                          if (evt.getClickCount() == 2) {
                            layoutDiagramFullOpen = 0;
                            diagramFullScreen.dispose();
                          }
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "This window is already open");
                }
              }
            }
        });
        //  END show full screen for layoutDiagram

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        if (!Utils.isMac()) {
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(layoutControl, javax.swing.GroupLayout.DEFAULT_SIZE, 1245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(tabbedDesLog, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(layoutDiagram, javax.swing.GroupLayout.DEFAULT_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)));
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(layoutControl, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(tabbedDesLog, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(layoutDiagram, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))) //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                ));
        } else {
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(layoutControl, javax.swing.GroupLayout.DEFAULT_SIZE, 1251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(tabbedDesLog, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(layoutDiagram, javax.swing.GroupLayout.DEFAULT_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)));
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(layoutControl, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(tabbedDesLog, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(layoutDiagram, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))) //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                ));
        }

        bindingGroup.bind();
    }
    // BEGIN Action Performed
    public void menuItemNoSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNoSelectActionPerformed
        tabbedPaneOptics.removeAll();
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        lblDiagram.removeAll();
        buttonPanel.removeAll();
        tabbedPaneOptics.hide();
        
        lblDiagram.setIcon(null);
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);

        tabbedDesLog.addTab("Description", desNoSelect);
        jTextAreaLog.setColumns(20);
        jTextAreaLog.setRows(5);
        jTextAreaLog.setFont(Utils.getFont());
        jScrollPane2.setViewportView(jTextAreaLog);
        tabbedDesLog.addTab("Log", jScrollPane2);
        generateActionPerformedDefault(evt);

    }

    public void menuItemSLMBasicActionPerformed(java.awt.event.ActionEvent evt) {
        tabbedPaneOptics.removeAll();
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        tabbedPaneOptics.show();
        
        buttonPanel.removeAll();
        buttonPanel.add(slmBasicPanel.getPanelPhaseButton());
        
        tabbedPaneOptics.addTab("Gray Level", slmBasicPanel.getPanelPhase());
        tabbedPaneOptics.addTab("CGH Pattern Import", cgh1Panel.getPanel());
        tabbedPaneOptics.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index >= 0) {
                    String value = sourceTabbedPane.getTitleAt(index);
                    if (value.equals("Gray Level")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(slmBasicPanel.getPanelPhaseButton());
                        // log tab
                        jScrollPane2.setViewportView(slmBasicPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals("CGH Pattern Import")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(cgh1Panel.getPanelButton());
                    }
                }
                if (magFrameLenon != null) {
                    magFrameLenon.dispose();
                }
            }
            
        });
        
        jScrollDes.setViewportView(desSLM);
        tabbedDesLog.addTab("Description", jScrollDes);
        
        jScrollPane2.setViewportView(slmBasicPanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp_1_2_3.png")));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
        generateActionPerformedDefault(evt);

    }//GEN-LAST:event_menuItemSLMBasicActionPerformed

    public void menuItemAmplitudeActionPerformed(java.awt.event.ActionEvent evt) {
        tabbedPaneOptics.removeAll();
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        tabbedPaneOptics.show();

        buttonPanel.removeAll();
        tabbedPaneOptics.addTab("Photo ", amplitudePanel.getPanel());
        buttonPanel.add(amplitudePanel.getPanelButton());

        //tabbedDesLog.addTab("Description", desAmplitude);
        jScrollDes.setViewportView(desAmplitude);
        tabbedDesLog.addTab("Description", jScrollDes);
        
        //jScrollPane2.setViewportView(amplitudePanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp_1_2_3.png")));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
        generateActionPerformedDefault(evt);
    }

    public void menuItemPhaseModulationActionPerformed(java.awt.event.ActionEvent evt) {
        tabbedPaneOptics.removeAll();
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        tabbedPaneOptics.show();
        
        buttonPanel.removeAll();
        buttonPanel.add(beamSteerePanel.getJPanelButtonMirror());

        tabbedPaneOptics.addTab("Beam Steerer", beamSteerePanel.getJPanelMirror());
        tabbedPaneOptics.addTab(" CGH Pattern Import  ", cgh3Panel.getPanel());
        tabbedPaneOptics.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index >= 0) {
                    String value = sourceTabbedPane.getTitleAt(index);
                    if (value.equals("Beam Steerer")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(beamSteerePanel.getJPanelButtonMirror());
                        // log tab
                        jScrollPane2.setViewportView(beamSteerePanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals(" CGH Pattern Import  ")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(cgh3Panel.getPanelButton());
                    }
                }
                
            }
        });
        
        jScrollDes.setViewportView(desPhaseModulation);
        tabbedDesLog.addTab("Description", jScrollDes);
        jScrollPane2.setViewportView(beamSteerePanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp_1_2_3.png")));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
    }

    public void menuItemMichelsonActionPerformed(java.awt.event.ActionEvent evt) {
        tabbedPaneOptics.removeAll();
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        tabbedPaneOptics.show();

        buttonPanel.removeAll();
        buttonPanel.add(lensMichelsonPanel.getJPanelButton());

        tabbedPaneOptics.addTab("Lens", lensMichelsonPanel.getJPanel());
        tabbedPaneOptics.addTab("Cylindrical ", cyllindricalMichelsonPanel.getJPanelCyllindrical());
        tabbedPaneOptics.addTab("Mirror", mirrorMichelsonPanel.getJPanelMirror());
        tabbedPaneOptics.addTab("CGH Pattern Import    ", cgh4Panel.getPanel());
        tabbedPaneOptics.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index >= 0) {
                    String value = sourceTabbedPane.getTitleAt(index);
                    if (value.equals("Lens")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(lensMichelsonPanel.getJPanelButton());
                        // log tab
                        jScrollPane2.setViewportView(lensMichelsonPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals("Cylindrical ")){
                        buttonPanel.removeAll();
                        buttonPanel.add(cyllindricalMichelsonPanel.getJPanelButton());
                        // log tab
                        jScrollPane2.setViewportView(cyllindricalMichelsonPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals("Mirror")){
                        buttonPanel.removeAll();
                        buttonPanel.add(mirrorMichelsonPanel.getJPanelButtonMirror());
                        // log tab
                        jScrollPane2.setViewportView(mirrorMichelsonPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals("CGH Pattern Import    ")){
                        buttonPanel.removeAll();
                        buttonPanel.add(cgh4Panel.getPanelButton());
                    } if (value.equals("Gray Level")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(slmBasicPanel.getPanelPhaseButton());
                        // log tab
                        jScrollPane2.setViewportView(slmBasicPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals("CGH Pattern Import")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(cgh1Panel.getPanelButton());
                    } if (value.equals("Photo ")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(amplitudePanel.getPanelButton());
                        // log tab
                        //jScrollPane2.setViewportView(amplitudePanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    }
                }
                
            }
        });

        jScrollDes.setViewportView(desMichelson);
        tabbedDesLog.addTab("Description", jScrollDes);
        
        jScrollPane2.setViewportView(lensMichelsonPanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp4michelson.png")));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
        generateActionPerformedDefault(evt);
    }

    public void menuItemDiffractionActionPerformed(java.awt.event.ActionEvent evt) {
        tabbedPaneOptics.removeAll();
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        tabbedPaneOptics.show();
        
        buttonPanel.removeAll();
        buttonPanel.add(singleSlitPanel.getPanelButton());

        tabbedPaneOptics.addTab("Single Slit", singleSlitPanel.getPanel());
        tabbedPaneOptics.addTab("Double Slit", doubleSlitPanel.getPanel());
        tabbedPaneOptics.addTab("   CGH Pattern Import  ", cgh5Panel.getPanel());
        tabbedPaneOptics.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index >= 0) {
                    String value = sourceTabbedPane.getTitleAt(index);
                    if (value.equals("Single Slit")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(singleSlitPanel.getPanelButton());
                        // log tab
                        jScrollPane2.setViewportView(singleSlitPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals("Double Slit")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(doubleSlitPanel.getPanelButton());
                        // log tab
                        jScrollPane2.setViewportView(doubleSlitPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals("   CGH Pattern Import  ")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(cgh5Panel.getPanelButton());
                    }
                }
                
            }
        });

        jScrollDes.setViewportView(desDiffaction);
        tabbedDesLog.addTab("Description", jScrollDes);
        
        jScrollPane2.setViewportView(singleSlitPanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp5diffraction.png")));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
        generateActionPerformedDefault(evt);
    }

    public void menuItemSpectrometerActionPerformed(java.awt.event.ActionEvent evt) {
        tabbedPaneOptics.removeAll();
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        tabbedPaneOptics.show();
        
        buttonPanel.removeAll();
        buttonPanel.add(spectremeterPanel.getPanelButton());
        
        tabbedPaneOptics.addTab("Diffraction Pattern", spectremeterPanel.getPanel());
        tabbedPaneOptics.addTab("  CGH  Pattern  Import  ", cgh6Panel.getPanel());
        tabbedPaneOptics.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index >= 0) {
                    String value = sourceTabbedPane.getTitleAt(index);
                    if (value.equals("Diffraction Pattern")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(spectremeterPanel.getPanelButton());
                        // log tab
                        jScrollPane2.setViewportView(spectremeterPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals("  CGH  Pattern  Import  ")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(cgh6Panel.getPanelButton());
                    }
                }
                
            }
        });

        jScrollDes.setViewportView(desSpectrometer);
        tabbedDesLog.addTab("Description", jScrollDes);
        
        jScrollPane2.setViewportView(spectremeterPanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp6spectrometer.png")));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
        generateActionPerformedDefault(evt);
    }

    public void menuItemSignalProcessingActionPerformed(java.awt.event.ActionEvent evt) {
        tabbedPaneOptics.removeAll();
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        tabbedPaneOptics.show();
        
        buttonPanel.removeAll();
        buttonPanel.add(signalPanel.getPanelButton());
        
        tabbedPaneOptics.addTab("Signal processing", signalPanel.getPanel());
        tabbedPaneOptics.addTab(" Photo", signalPhotoPanel.getPanel());
        tabbedPaneOptics.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index >= 0) {
                    String value = sourceTabbedPane.getTitleAt(index);
                    if (value.equals("Signal processing")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(signalPanel.getPanelButton());
                        // log tab
                        jScrollPane2.setViewportView(signalPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals(" Photo")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(signalPhotoPanel.getPanelButton());
                        // log tab
                        //jScrollPane2.setViewportView(signalPhotoPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    }
                }
                
            }
        });
        jScrollDes.setViewportView(desSignalProcessing);
        tabbedDesLog.addTab("Description", jScrollDes);
        
        jScrollPane2.setViewportView(signalPanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp7signalprocessing.png")));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
        generateActionPerformedDefault(evt);
    }

    public void menuItemPhaseShiftingActionPerformed(java.awt.event.ActionEvent evt) {
        tabbedPaneOptics.removeAll();
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        tabbedPaneOptics.show();
        
        buttonPanel.removeAll();
        buttonPanel.add(phaseRetarderPanel.getPanelPhaseButton());

        tabbedPaneOptics.addTab("Phase retarder", phaseRetarderPanel.getPanelPhase());
        tabbedPaneOptics.addTab("  CGH   Pattern   Import  ", cgh8Panel.getPanel());
        tabbedPaneOptics.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index >= 0) {
                    String value = sourceTabbedPane.getTitleAt(index);
                    if (value.equals("Phase retarder")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(phaseRetarderPanel.getPanelPhaseButton());
                        // log tab
                        jScrollPane2.setViewportView(phaseRetarderPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals("  CGH   Pattern   Import  ")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(cgh8Panel.getPanelButton());
                    }
                }
                
            }
        });

        jScrollDes.setViewportView(desPhaseRetarder);
        tabbedDesLog.addTab("Description", jScrollDes);
        jScrollPane2.setViewportView(phaseRetarderPanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp8phaseshifting.png")));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
        generateActionPerformedDefault(evt);
    }

    public void menuItemTalbotActionPerformed(java.awt.event.ActionEvent evt) {
        tabbedPaneOptics.removeAll();
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        tabbedPaneOptics.show();

        buttonPanel.removeAll();
        buttonPanel.add(talbotPanel.getPanelButton());
         // im here1
        tabbedPaneOptics.addTab("Talbot", talbotPanel.getPanel());
        tabbedPaneOptics.addTab("Talbot photo", talbotPhotoPanel.getPanel());
        tabbedPaneOptics.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index >= 0) {
                    String value = sourceTabbedPane.getTitleAt(index);
                    if (value.equals("Talbot")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(talbotPanel.getPanelButton());
                        // log tab
                        jScrollPane2.setViewportView(talbotPanel.getLogArea());
                    } if (value.equals("Talbot photo")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(talbotPhotoPanel.getPanelButton());
                        // log tab
                        //jScrollPane2.setViewportView(talbotPhotoPanel.getLogArea());
                    }
                }
                
            }
        });

        jScrollDes.setViewportView(desTalbot);
        tabbedDesLog.addTab("Description", jScrollDes);
        
        jScrollPane2.setViewportView(talbotPanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp9talbot.png")));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
        generateActionPerformedDefault(evt);
    }

    public void menuItemWavefrontActionPerformed(java.awt.event.ActionEvent evt) {
        tabbedPaneOptics.removeAll();
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        tabbedPaneOptics.show();
        
        buttonPanel.removeAll();
        buttonPanel.add(lensWavefrontPanel.getPanelButton());

        tabbedPaneOptics.addTab(" Lens", lensWavefrontPanel.getPanel());
        tabbedPaneOptics.addTab(" Cylindrical ", cyllindricalWavefrontPanel.getPanel());
        tabbedPaneOptics.addTab(" Mirror", mirrorWavefrontPanel.getPanel());
        tabbedPaneOptics.addTab("CGH Pattern  Import", cgh10Panel.getPanel());
        tabbedPaneOptics.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index >= 0) {
                    String value = sourceTabbedPane.getTitleAt(index);
                    if (value.equals(" Lens")) {
                        buttonPanel.removeAll();
                        buttonPanel.add(lensWavefrontPanel.getPanelButton());
                        // log tab
                        jScrollPane2.setViewportView(lensWavefrontPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals(" Cylindrical ")){
                        buttonPanel.removeAll();
                        buttonPanel.add(cyllindricalWavefrontPanel.getPanelButton());
                        // log tab
                        jScrollPane2.setViewportView(cyllindricalWavefrontPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals(" Mirror")){
                        buttonPanel.removeAll();
                        buttonPanel.add(mirrorWavefrontPanel.getPanelButton());
                        // log tab
                        jScrollPane2.setViewportView(mirrorWavefrontPanel.getLogArea());
                        tabbedDesLog.addTab("Log", jScrollPane2);
                    } if (value.equals("CGH Pattern  Import")){
                        buttonPanel.removeAll();
                        buttonPanel.add(cgh10Panel.getPanelButton());
                    }
                }
                
            }
        });
        
        jScrollDes.setViewportView(desWavefront);
        tabbedDesLog.addTab("Description", jScrollDes);
        jScrollPane2.setViewportView(lensWavefrontPanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(getClass().getResource("/resources/diagram/exp10wavefrontmodulation.png")));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
        generateActionPerformedDefault(evt);
    }
    
    // selected default
    private void generateActionPerformedDefault(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondActionPerformedCGH10
        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        image.paintDefault();
        EduPatternShowOn.updatePattern(image, "");
        imageGenerated = true;
    }
    // End Action Performed
    
    public void showExperiments() {
        CardLayout cardlayout = (CardLayout)(projectPanel.getLayout());
        cardlayout.show(projectPanel, labels.getString("mnuExperiments"));
    }
    
    public void showProjects(String projectName, String macroName, String desc, String diagram) throws IOException {
        
        CardLayout cardlayout = (CardLayout) (projectPanel.getLayout());
        JPanel params = new JPanel();
        
        paramsPrjPanel = new paramsPanel(labels, bindingGroup, panelPattern);
        
        paramsPrjPanel.setProject(projectName);
        
        paramsPrjPanel.initComponents(labels, bindingGroup, panelPattern);
        paramsPrjPanel.initParams();
        paramsPrjPanel.setProjectName(projectName);
        paramsPrjPanel.setMacroName(macroName);
        
        
        params.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        params.add(paramsPrjPanel.getPanel());
        
        projectPanel.setBounds(580, 0, 665, 370);
        projectPanel.add(params, labels.getString("mnuProjectsParams")); 
        
        cardlayout.show(projectPanel, labels.getString("mnuProjectsParams"));
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        
        
        JTextArea txtDesc = new JTextArea();
        Font font = Utils.getFont();
        txtDesc.setFont(font);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setEditable(false);
        txtDesc.setOpaque(false);
        
        try {
            FileReader fileReader = new FileReader(new File(desc));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String inputFile = "";
            String textFieldReadable;

            while ((textFieldReadable = bufferedReader.readLine()) != null) {
                inputFile += textFieldReadable;
            }

            txtDesc.setText(inputFile);

        } catch (FileNotFoundException ex) {
            System.out.println("no such file exists");
        } catch (IOException ex) {
            System.out.println("unkownerror");
        }
        jScrollDes.setViewportView(txtDesc);
        tabbedDesLog.addTab("Description", jScrollDes);
        jScrollPane2.setViewportView(paramsPrjPanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(diagram));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
    }
    
    /*
    public void showProjects_bak(int num, String projectName, String macroName, String desc, String diagram) throws IOException {
        oneParamPrjPanel.setProject(projectName);
        oneParamPrjPanel.setMacro(macroName);
        CardLayout cardlayout = (CardLayout)(projectPanel.getLayout());
        if (num == 1) {
            oneParamPrjPanel.setProject(projectName);
            oneParamPrjPanel.setMacro(macroName);
            cardlayout.show(projectPanel, labels.getString("mnuProjectsOneParam"));
        } else if (num == 2) {
            twoParamPrjPanel.setProject(projectName);
            twoParamPrjPanel.setMacro(macroName);
            cardlayout.show(projectPanel, labels.getString("mnuProjectsTwoParam"));
        } else if (num == 3) {
            threeParamPrjPanel.setProject(projectName);
            threeParamPrjPanel.setMacro(macroName);
            cardlayout.show(projectPanel, labels.getString("mnuProjectsThreeParam"));
        } else if (num == 4) {
            fourParamPrjPanel.setProject(projectName);
            fourParamPrjPanel.setMacro(macroName);
            cardlayout.show(projectPanel, labels.getString("mnuProjectsFourParam"));
        }
        
        layoutDiagram.removeAll();
        diagramLens.removeAll();
        tabbedDesLog.removeAll();
        
        JTextArea txtDesc = new JTextArea();
        Font font = Utils.getFont();
        txtDesc.setFont(font);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setEditable(false);
        txtDesc.setOpaque(false);
        try {
            FileReader fileReader = new FileReader(new File(desc));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String inputFile = "";
            String textFieldReadable;

            while ((textFieldReadable = bufferedReader.readLine()) != null) {
                inputFile += textFieldReadable;
            }

            txtDesc.setText(inputFile);

        } catch (FileNotFoundException ex) {
            System.out.println("no such file exists");
        } catch (IOException ex) {
            System.out.println("unkownerror");
        }
        jScrollDes.setViewportView(txtDesc);
        tabbedDesLog.addTab("Description", jScrollDes);
        jScrollPane2.setViewportView(oneParamPrjPanel.getLogArea());
        tabbedDesLog.addTab("Log", jScrollPane2);

        lblDiagram.setIcon(new ImageIcon(diagram));
        lblDiagram.setText(null);
        diagramLens.add(lblDiagram);
        layoutDiagram.add(diagramLens);
    }
    */
        
    class PopupTriggerListener extends MouseAdapter {

        public void mousePressed(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                menu.show(ev.getComponent(), ev.getX(), ev.getY());
            }
        }

        public void mouseReleased(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                menu.show(ev.getComponent(), ev.getX(), ev.getY());
            }
        }

        public void mouseClicked(MouseEvent evt) {
              if (evt.getClickCount() == 2) {
                desFullScreen = new javax.swing.JTextArea();
                desFullScreen.setLineWrap(true);
                desFullScreen.setWrapStyleWord(true);
                desFullScreen.setEditable(false);
                desFullScreen.setOpaque(false);
                
                descriptionFullScreen = new JFrame("JDC Education Kit - Description full screen");
                ResourceBundle labels = ResourceBundle
                .getBundle("resources/description", EduUIMainView.supportedLocales[0]);
        
                desBeamShifting.setText(labels.getString("desBeamShifting"));
                desImportFormula.setText(labels.getString("desImportFormula"));
                
                if(layoutDescriptionFullOpen ==  0){
                    if (tmpSelected == 1) {
                        desFullScreen.setText(desSLM.getText());
                    } else if (tmpSelected == 2) {
                        desFullScreen.setText(desAmplitude.getText());
                    } else if (tmpSelected == 3) {
                        desFullScreen.setText(desPhaseModulation.getText());
                    } else if (tmpSelected == 4) {
                        desFullScreen.setText(desMichelson.getText());
                    } else if (tmpSelected == 5) {
                        desFullScreen.setText(desDiffaction.getText());
                    } else if (tmpSelected == 6) {
                        desFullScreen.setText(desSpectrometer.getText());
                    } else if (tmpSelected == 7) {
                        desFullScreen.setText(desSignalProcessing.getText());
                    } else if (tmpSelected == 8) {
                        desFullScreen.setText(desPhaseRetarder.getText());
                    } else if (tmpSelected == 9) {
                        desFullScreen.setText(desTalbot.getText());
                    } else if (tmpSelected == 10) {
                        desFullScreen.setText(desWavefront.getText());
                    }
                
                    descriptionFullScreen.setBackground(Color.WHITE);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
                    Toolkit kit = Toolkit.getDefaultToolkit();
                    Image img = kit.createImage(url);
                    descriptionFullScreen.setIconImage(img);
                    descriptionFullScreen.getContentPane().add(desFullScreen);
                    descriptionFullScreen.pack();
                    layoutDescriptionFullOpen++;

                    descriptionFullScreen.setBounds(0,0,screenSize.width, screenSize.height);
                    descriptionFullScreen.setVisible(true);
                   
                    descriptionFullScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    descriptionFullScreen.setAlwaysOnTop(true);
                    descriptionFullScreen.setResizable(true);
                    descriptionFullScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                            layoutDescriptionFullOpen = 0;
                            descriptionFullScreen.dispose();
                    }
                    });
                    descriptionFullScreen.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                          if (evt.getClickCount() == 2) {
                             layoutDescriptionFullOpen = 0;
                            descriptionFullScreen.dispose();
                          }
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "This window is already open");
                }
              }
            }
    }
    
    private void popupMenuLanguage() {
        ResourceBundle res = ResourceBundle.getBundle("resources/Text", EduUIMainView.supportedLocales[0]);
        JMenuItem item = new JMenuItem(res.getString("mnuItemLanguageEnglish"));
        item.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ResourceBundle labels = ResourceBundle.getBundle("resources/description", EduUIMainView.supportedLocales[0]);

                desBeamShifting.setText(labels.getString("desBeamShifting"));
                desImportFormula.setText(labels.getString("desImportFormula"));

                if (tmpSelected == 1) {
                    desSLM.setText(labels.getString("desSLM"));
                } else if (tmpSelected == 2) {
                    desAmplitude.setText(labels.getString("desAmplitude"));
                } else if (tmpSelected == 3) {
                    desPhaseModulation.setText(labels.getString("desPhaseModulation"));
                } else if (tmpSelected == 4) {
                    desMichelson.setText(labels.getString("desMichelson"));
                } else if (tmpSelected == 5) {
                    desDiffaction.setText(labels.getString("desDiffaction"));
                } else if (tmpSelected == 6) {
                    desSpectrometer.setText(labels.getString("desSpectrometer"));
                } else if (tmpSelected == 7) {
                    desSignalProcessing.setText(labels.getString("desSignalProcessing"));
                } else if (tmpSelected == 8) {
                    desPhaseRetarder.setText(labels.getString("desPhaseRetarder"));
                } else if (tmpSelected == 9) {
                    desTalbot.setText(labels.getString("desTalbot"));
                } else if (tmpSelected == 10) {
                    desWavefront.setText(labels.getString("desWavefront"));
                }

            }
        });
        menu.add(item);

        item = new JMenuItem(res.getString("mnuItemLanguageChineseSimplified"));
        item.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ResourceBundle labels = ResourceBundle.getBundle("resources/description", EduUIMainView.supportedLocales[1]);

                desBeamShifting.setText(labels.getString("desBeamShifting"));
                desImportFormula.setText(labels.getString("desImportFormula"));

                if (tmpSelected == 1) {
                    desSLM.setText(labels.getString("desSLM"));
                } else if (tmpSelected == 2) {
                    desAmplitude.setText(labels.getString("desAmplitude"));
                } else if (tmpSelected == 3) {
                    desPhaseModulation.setText(labels.getString("desPhaseModulation"));
                } else if (tmpSelected == 4) {
                    desMichelson.setText(labels.getString("desMichelson"));
                } else if (tmpSelected == 5) {
                    desDiffaction.setText(labels.getString("desDiffaction"));
                } else if (tmpSelected == 6) {
                    desSpectrometer.setText(labels.getString("desSpectrometer"));
                } else if (tmpSelected == 7) {
                    desSignalProcessing.setText(labels.getString("desSignalProcessing"));
                } else if (tmpSelected == 8) {
                    desPhaseRetarder.setText(labels.getString("desPhaseRetarder"));
                } else if (tmpSelected == 9) {
                    desTalbot.setText(labels.getString("desTalbot"));
                } else if (tmpSelected == 10) {
                    desWavefront.setText(labels.getString("desWavefront"));
                }

            }
        });
        menu.add(item);
        
        item = new JMenuItem(res.getString("mnuItemLanguageChineseTraditional"));
        item.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ResourceBundle labels = ResourceBundle.getBundle("resources/description", EduUIMainView.supportedLocales[2]);

                desBeamShifting.setText(labels.getString("desBeamShifting"));
                desImportFormula.setText(labels.getString("desImportFormula"));

                if (tmpSelected == 1) {
                    desSLM.setText(labels.getString("desSLM"));
                } else if (tmpSelected == 2) {
                    desAmplitude.setText(labels.getString("desAmplitude"));
                } else if (tmpSelected == 3) {
                    desPhaseModulation.setText(labels.getString("desPhaseModulation"));
                } else if (tmpSelected == 4) {
                    desMichelson.setText(labels.getString("desMichelson"));
                } else if (tmpSelected == 5) {
                    desDiffaction.setText(labels.getString("desDiffaction"));
                } else if (tmpSelected == 6) {
                    desSpectrometer.setText(labels.getString("desSpectrometer"));
                } else if (tmpSelected == 7) {
                    desSignalProcessing.setText(labels.getString("desSignalProcessing"));
                } else if (tmpSelected == 8) {
                    desPhaseRetarder.setText(labels.getString("desPhaseRetarder"));
                } else if (tmpSelected == 9) {
                    desTalbot.setText(labels.getString("desTalbot"));
                } else if (tmpSelected == 10) {
                    desWavefront.setText(labels.getString("desWavefront"));
                }

            }
        });
        menu.add(item);
    }
}

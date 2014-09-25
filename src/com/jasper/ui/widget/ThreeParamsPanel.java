/*
 * @(#)NewProjectPanel.java
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

import com.jasper.core.OpticsPane;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JOptionPane;
import com.jasper.core.PatternImage;
import com.jasper.model.Macro;
import com.jasper.model.Param;
import com.jasper.model.Project;
import com.jasper.ui.EduLensOn11;
import com.jasper.ui.EduPatternJPanel;
import com.jasper.ui.EduPatternShowOn;
import com.jasper.utils.Utils;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;

import static com.jasper.ui.EduPatternShowOn.patternFrameDoubleClick;
import static com.jasper.ui.EduPatternShowOn.patternFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;

/**
 *
 * @author trancongly
 */
public class ThreeParamsPanel extends OpticsPane {

    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private javax.swing.JSlider sliderFocal;
    private javax.swing.JTextField textFocal;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    private javax.swing.JLabel lblParam1;
    private javax.swing.JLabel lblParam2;
    private javax.swing.JLabel lblParam3;
    private javax.swing.JSlider jSliderParam1;
    private JSlider jSliderParam2;
    private JSlider jSliderParam3;
    private javax.swing.JTextField txtParam1;
    private javax.swing.JTextField txtParam2;
    private javax.swing.JTextField txtParam3;
    private javax.swing.JButton btnLensOn;
    private javax.swing.JButton btnSecondDisplay;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JTextArea txtAreaLog;
    static String logmessageNewProject = "New Project: First param=%s Second param=%s third params=%s";
    private double dParam1 = 0.0, dParam2 = 0.0, dParam3 = 0.0;
    private int countSecondDisplayMichelson = 1;
    private int countLenOnMichelson = 1;
    private javax.swing.JPanel panelLensMichelson;
    private javax.swing.JPanel panelMichelsonButton;
    private javax.swing.JPanel panelNewProject;
    private JLabel lblMacro, lblProject;
    private String prjName, desc, diagram;
    private int panelType = 1;
    private Macro macro;
    
    public ThreeParamsPanel(ResourceBundle labels, BindingGroup bindingGroup, JPanel panelPattern) {
        this.labels = labels;
        this.txtAreaLog = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        initComponents(bindingGroup);
    }
        
    public void setProject(String project) {
        prjName = project;        
        lblProject.setText("Project Name: " + project);        
        initParams();
    }
    
    public void setMacro(String macro) {
        lblMacro.setText("Macro Name: " + macro);
    }
    
    public void setDescription(String desc) {
        this.desc = desc;
    }
    
    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }
    
    public int getPanelType() {
        return this.panelType;
    }
    
    public void initParams() {
        Project prj = new Project(Utils.getCurrentLocation() + prjName.trim() + ".prj");
        macro = new Macro(prj.getMacro());
        
        lblParam1.setText(((Param)macro.getParam().get(0)).getSubName());
        lblParam2.setText(((Param)macro.getParam().get(1)).getSubName());        
        lblParam3.setText(((Param)macro.getParam().get(2)).getSubName());
        
        double max = (((Param)macro.getParam().get(0)).getMax() * ((Param)macro.getParam().get(0)).getStep());
        double min = (((Param)macro.getParam().get(0)).getMin() * ((Param)macro.getParam().get(0)).getStep());
        jSliderParam1.setMaximum((int)max);
        jSliderParam1.setMinimum((int)min);
        
        double max1 = (((Param)macro.getParam().get(1)).getMax() * ((Param)macro.getParam().get(1)).getStep());
        double min1 = (((Param)macro.getParam().get(1)).getMin() * ((Param)macro.getParam().get(1)).getStep());
        jSliderParam2.setMaximum((int)max1);
        jSliderParam2.setMinimum((int)min1);
        
        double max2 = (((Param)macro.getParam().get(2)).getMax() * ((Param)macro.getParam().get(2)).getStep());
        double min2 = (((Param)macro.getParam().get(2)).getMin() * ((Param)macro.getParam().get(2)).getStep());
        jSliderParam3.setMaximum((int)max2);
        jSliderParam3.setMinimum((int)min2);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        textFocal = new javax.swing.JTextField();
        sliderFocal = new javax.swing.JSlider();
        
        panelLensMichelson = new javax.swing.JPanel();
        lblParam1 = new javax.swing.JLabel();
        jSliderParam1 = new javax.swing.JSlider();
        txtParam1 = new javax.swing.JTextField();
        lblParam2 = new javax.swing.JLabel();
        txtParam2 = new javax.swing.JTextField();
        lblParam3 = new javax.swing.JLabel();
        txtParam3 = new javax.swing.JTextField();
        btnGenerate = new javax.swing.JButton();
        btnLensOn = new javax.swing.JButton();
        btnSecondDisplay = new javax.swing.JButton();
        panelMichelsonButton = new javax.swing.JPanel();
        
        panelNewProject = new javax.swing.JPanel(new BorderLayout(10, 30));
        
        JPanel headerPanel = new JPanel(new BorderLayout(30, 30));
        lblMacro = new JLabel("Macro Name: ");
        lblProject = new JLabel("Project Name: ");
        headerPanel.add(lblProject, BorderLayout.LINE_START);
        headerPanel.add(lblMacro, BorderLayout.LINE_END);
        panelNewProject.add(headerPanel, BorderLayout.PAGE_START);
        panelNewProject.add(panelLensMichelson, BorderLayout.CENTER);
        panelNewProject.add(panelMichelsonButton, BorderLayout.PAGE_END);
               
        Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jSliderParam1, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtParam1, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
                        
        btnGenerate.setText(labels.getString("btnGenerate"));
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformed(evt);
            }
        });
        
        btnLensOn.setEnabled(false);
        btnLensOn.setText(labels.getString("btnLensOn"));
        btnLensOn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnActionPerformed(evt);
                countLenOnMichelson++;
                if (countLenOnMichelson % 2 == 0) {
                    btnLensOn.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {

                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                    });
                } else {
                    btnLensOn.setText(labels.getString("btnLensOn"));
                }
            }
        });
        
        btnSecondDisplay.setEnabled(false);
        btnSecondDisplay.setText(labels.getString("btnSecondDisplayOn"));
        btnSecondDisplay.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformed(evt);
                countSecondDisplayMichelson++;
                if (countSecondDisplayMichelson % 2 == 0) {
                    btnSecondDisplay.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    btnSecondDisplay.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });
        
        jSliderParam2 = new JSlider();
        jSliderParam2.setValue(0);
        Binding binding2 = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, jSliderParam2, ELProperty.create("${value}"), txtParam2, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding2);
        txtParam2.setText(String.valueOf(jSliderParam2.getValue()));
        
        jSliderParam2.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedLens(evt);
//                DecimalFormat df = new DecimalFormat("0.####");
//                txtParam2.setText(df.format(jSliderParam2.getScaledValue()));
            }
        });
        
        jSliderParam3 = new JSlider();
        jSliderParam3.setValue(0);
        Binding binding3 = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, jSliderParam3, ELProperty.create("${value}"), txtParam3, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding3);
        txtParam3.setText(String.valueOf(jSliderParam3.getValue()));
        
        jSliderParam3.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedLens(evt);
//                DecimalFormat df = new DecimalFormat("0.####");
//                txtParam3.setText(df.format(jSliderParam3.getScaledValue()));
            }
        });
                
        jSliderParam1.setValue(0);
        jSliderParam1.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedLens(evt);
                
            }
        });
        
        txtParam1.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent ke) {
                keyenventGenerateActionPerformedLens(ke);
                if (txtParam1.getText() == null || txtParam1.getText().equals("")) {
                    lblParam1.setForeground(Color.red);
                } else {
                    lblParam1.setForeground(Color.black);
                }
            }
        });
        
        txtParam2.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent ke) {
                keyenventGenerateActionPerformedLens(ke);
                if (txtParam2.getText() == null || txtParam2.getText().equals("")) {
                    lblParam2.setForeground(Color.red);
                } else {
                    lblParam2.setForeground(Color.black);
                }
            }
        });
        
        txtParam3.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent ke) {
                keyenventGenerateActionPerformedLens(ke);
                if (txtParam3.getText() == null || txtParam3.getText().equals("")) {
                    lblParam3.setForeground(Color.red);
                } else {
                    lblParam3.setForeground(Color.black);
                }
            }
        });
        
        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelMichelsonButton);
        panelMichelsonButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
                panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelButtonLayout.createSequentialGroup().addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelButtonLayout.createSequentialGroup().addGap(0, 0, 0).addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(20, 20, 20).addComponent(btnLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(20, 20, 20).addComponent(btnSecondDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))));
        panelButtonLayout.setVerticalGroup(
                panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelButtonLayout.createSequentialGroup().addGap(105, 105, 105).addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false).addComponent(btnSecondDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(btnLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))));
        
        javax.swing.GroupLayout jPanelParamLayout = new javax.swing.GroupLayout(panelLensMichelson);
        panelLensMichelson.setLayout(jPanelParamLayout);
        jPanelParamLayout.setHorizontalGroup(
                jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelParamLayout.createSequentialGroup()
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelParamLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(15, 15, 15)
                    .addComponent(lblParam3)
                    .addGroup(jPanelParamLayout.createSequentialGroup()
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblParam2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblParam1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtParam3)
                    .addComponent(txtParam1, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(txtParam2))))
                    .addGap(5, 5, 5)
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSliderParam1, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(jSliderParam2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSliderParam3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))));
        jPanelParamLayout.setVerticalGroup(
                jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelParamLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSliderParam1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblParam1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtParam1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(21, 21, 21)
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblParam2)
                    .addComponent(txtParam2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSliderParam2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblParam3).addComponent(txtParam3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSliderParam3, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)));
    }
    
    public JPanel getPanel() {
        return panelNewProject;
    }
    
    public JTextArea getLogArea() {
        return txtAreaLog;
    }
    
    private void buttonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedMichelSon
        if (parseArguments()) {
            btnLensOn.setEnabled(true);
            btnSecondDisplay.setEnabled(true);
            
            callFunction(1);
        }
        
    }
    
    private void button11LensOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnMichelsonActionPerformed
        if (parseArguments()) {
            callFunction(1);
            
            if (countLenOnMichelson % 2 == 0) {
                magFrameLenon.dispose();
                panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {

                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        patternFrameDoubleClick.show();
                    }
                });
            } else {
                magFrameLenon = new JFrame("1:1 Lens On");
                URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
                Toolkit kit = Toolkit.getDefaultToolkit();
                Image img = kit.createImage(url);
                magFrameLenon.setIconImage(img);
                
                EduLensOn11 mag = new EduLensOn11(panelPattern, new Dimension(120, 120));
                magFrameLenon.getContentPane().add(mag);
                magFrameLenon.pack();
                magFrameLenon.setLocation(new Point(568, 450));
                magFrameLenon.setResizable(false);
                magFrameLenon.setVisible(true);
                magFrameLenon.setAlwaysOnTop(true);
                magFrameLenon.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        countLenOnMichelson--;
                        btnLensOn.setText(labels.getString("btnLensOn"));
                        magFrameLenon.dispose();
                    }
                });
            }
            
        }
        
    }
    
    private void buttonSecondGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedMichelSon
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplayMichelson--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                callFunction(2);
                if (countSecondDisplayMichelson % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }
    
    private void sliderGenerateActionPerformedLens(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedLensMichelSon
        if (parseArguments()) {
            btnLensOn.setEnabled(true);
            btnSecondDisplay.setEnabled(true);
            
            callFunction(1);
        }
    }
    
    private void keyenventGenerateActionPerformedLens(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyenventGenerateActionPerformedLensMichelSon
        if (parseArguments()) {
            btnLensOn.setEnabled(true);
            btnSecondDisplay.setEnabled(true);
            
            callFunction(1);
        }
    }
    
    private void callFunction(int displayNumber) {
        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        if (macro.getFunctionName().equals("LensMichelson")) {
            image.updateLensMichelsonParameter(dParam1, dParam2, dParam3);
            image.paintLensMichelson();
        } else if (macro.getFunctionName().equals("CyllindricalMichelson")) {            
            image.updateCyllindricalParameter(dParam1, dParam2, dParam3);
            image.paintCylindrical();            
        } else if (macro.getFunctionName().equals("MirrorMichelson")) {
            image.updateMirrorParameter(dParam1, dParam2);
            image.paintMirror();
        } else if (macro.getFunctionName().equals("PhaseRetarder")) {
            image.updatePhaseRetarderParameter(dParam1);
            image.paintPhaseRetarder();
        } else if (macro.getFunctionName().equals("Spectrometer")) {
            image.updateMirrorSpectometerParameter(dParam1, dParam2);
            image.paintMirrorSpectrometer();
        } else if (macro.getFunctionName().equals("CyllindricalWavefront")) {
            image.updateCyllindricalParameter(dParam1, dParam2, dParam3);
            image.paintCylindrical();
        } else if (macro.getFunctionName().equals("LensWavefront")) {
            image.updateLensMichelsonParameter(dParam1, dParam2, dParam3);
            image.paintLensMichelson();
        } else if (macro.getFunctionName().equals("MirrorWavefront")) {
            image.updateMirrorParameter(dParam1, dParam2);
            image.paintMirror();
        }
        
        if (displayNumber == 1)
            EduPatternShowOn.updatePattern(image, genLog());
        else EduPatternShowOn.updatePatternSecondDisplay(image, genLog());
        setLog(genLog());
        imageGenerated = true;
    }
    
    private String genLog() {
        return String.format(logmessageNewProject, Double.toString(dParam1), Double.toString(dParam2), Double.toString(dParam3));
    }
    
    private boolean parseArguments() {
        boolean ret = false;
        try {            
            
            double xoffMi = Double.valueOf(txtParam2.getText());
            double yoffMi = Double.valueOf(txtParam3.getText());
            double focalMi = Double.valueOf(txtParam1.getText());
            this.dParam1 = xoffMi / ((Param)macro.getParam().get(0)).getStep();
            this.dParam2 = yoffMi / ((Param)macro.getParam().get(1)).getStep();
            this.dParam3 = focalMi / ((Param)macro.getParam().get(2)).getStep();
            ret = true;
            
        } catch (Exception e) {
            textFocal.setText(String.valueOf(this.focal));
        }
        return ret;
    }
    
    public void setLog(String msg) {
        txtAreaLog.append(msg + System.getProperty("line.separator"));
    }
}

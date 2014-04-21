/*
 * @(#)ImportFormulaPanel.java
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
package com.jasper.ui.panel;

import com.jasper.core.OpticsPane;
import javax.swing.JOptionPane;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import com.jasper.core.PatternImage;
import com.jasper.ui.EduLensOn11;
import com.jasper.ui.EduPatternJPanel;
import com.jasper.ui.EduPatternShowOn;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;

import static com.jasper.ui.EduPatternShowOn.patternFrameDoubleClick;
import static com.jasper.ui.EduPatternShowOn.patternFrame;
import javax.swing.JTextArea;

/**
 *
 * @author sonnv
 */
public class ImportFormulaPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private JPanel panelPattern;
    //private javax.swing.JTabbedPane jTabbedControler;
    private javax.swing.JTabbedPane tabbedControl;
    private JFrame magFrameLenon;
    
    private javax.swing.JTextField textFocal; // =>NOTE LAP
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;// =>NOTE LAP
    
    private javax.swing.JPanel panel;
    private javax.swing.JTextArea txtAreaLog;
    private javax.swing.JButton buttonOpenFileImportFile;
    private javax.swing.JLabel lblPleaseSelectImportFile;
    private javax.swing.JButton buttonSecondImportFile;
    private javax.swing.JButton button11LensOnImportFile;
    private javax.swing.JButton buttonGeneralImportFile;
    private javax.swing.JComboBox jComboBoxImportFile;
    private javax.swing.JLabel lblSelectExper_Importfile;
    private javax.swing.JLabel lblSelectFile_Importfile;
    private javax.swing.JCheckBox checkboxUseImage;
    
    private javax.swing.JButton buttonGennerateImportFile;
    private javax.swing.JButton buttonSecondDisplayImportFile;
    private javax.swing.JButton buttong11LensOnImportFile;
    private javax.swing.JLabel jLabelSpacingImportFile;
    private javax.swing.JLabel lblGrayImportFile;
    private javax.swing.JLabel lblHeightImportFile;
    private javax.swing.JLabel lblPosImportFile;
    //private javax.swing.JLabel lblRotationImportFile;
    private javax.swing.JLabel lblWidthImportFile;
    private javax.swing.JSlider s_single_grayImportFile;
    private javax.swing.JSlider s_single_heightImportFile;
    private javax.swing.JSlider s_single_positionImportFile;
    private javax.swing.JSlider s_single_rotationImportFile;
    private javax.swing.JSlider s_single_spacingImportFile;
    private javax.swing.JSlider s_single_widthImportFile;
    private javax.swing.JTextField text_single_grayImportFile;
    private javax.swing.JTextField text_single_heightImportFile;
    private javax.swing.JTextField text_single_positionImportFile;
    private javax.swing.JTextField text_single_rotationImportFile;
    private javax.swing.JTextField text_single_spacingImportFile;
    private javax.swing.JTextField text_single_widthImportFile;
    
    private javax.swing.JButton buttonGenerate;
    private javax.swing.JButton buttonLensON;
    private javax.swing.JButton buttonSecond;
    private javax.swing.JLabel lblE1;
    private javax.swing.JLabel lblGrayLevelImportFile;
    private javax.swing.JLabel lblHeaderImportFile;
    private javax.swing.JLabel lblK;
    private javax.swing.JLabel lblKR;
    private javax.swing.JLabel lblPositionImportFile;
    private javax.swing.JLabel lblR;
    private javax.swing.JLabel lblRotationImportFile;
    private javax.swing.JLabel lbltWidthImportFile;
//    private DoubleJSlider sliderGrayLevelImportFile;
//    private DoubleJSlider sliderPositionImportFile;
    private javax.swing.JSlider sliderGrayLevelImportFile;
    private javax.swing.JSlider sliderPositionImportFile;
    private javax.swing.JSlider sliderRotationImportFile;
    private javax.swing.JSlider sliderWidthImportFile;
    private javax.swing.JTextField txtE1ImportFile;
    private javax.swing.JTextField txtGrayLevelImportFile;
    private javax.swing.JTextField txtK1RImportFile;
    private javax.swing.JTextField txtKImportFile;
    private javax.swing.JTextField txtPositionImportFile;
    private javax.swing.JTextField txtRImportFile;
    private javax.swing.JTextField txtRotationImportFile;
    private javax.swing.JTextField txtWidthImportFile;
    private javax.swing.JTextField txtFormula;
    
    private double k = 0, r = 0, e = 0, kr = 0, width_importFile = 0, rotation_importFile = 0, position_importFile = 0, grayLevel_importFile = 0;
    private String formula = "";
    private int countLenOnImportFile = 1;
    private int countSecondDisplayImportFile = 1;
    
    public ImportFormulaPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern, JTabbedPane tabbedControl) {
        this.labels = labels;
        this.txtAreaLog = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        this.tabbedControl = tabbedControl;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panel = new javax.swing.JPanel();
        
        lblHeaderImportFile = new javax.swing.JLabel();
        lblK = new javax.swing.JLabel();
        txtKImportFile = new javax.swing.JTextField();
        lblR = new javax.swing.JLabel();
        txtRImportFile = new javax.swing.JTextField();
        lblE1 = new javax.swing.JLabel();
        txtE1ImportFile = new javax.swing.JTextField();
        lblKR = new javax.swing.JLabel();
        txtK1RImportFile = new javax.swing.JTextField();
        lbltWidthImportFile = new javax.swing.JLabel();
        txtWidthImportFile = new javax.swing.JTextField();
        sliderWidthImportFile = new javax.swing.JSlider();
        lblRotationImportFile = new javax.swing.JLabel();
        txtRotationImportFile = new javax.swing.JTextField();
        sliderRotationImportFile = new javax.swing.JSlider();
        lblPositionImportFile = new javax.swing.JLabel();
        txtPositionImportFile = new javax.swing.JTextField();
        sliderPositionImportFile = new javax.swing.JSlider();
        lblGrayLevelImportFile = new javax.swing.JLabel();
        txtGrayLevelImportFile = new javax.swing.JTextField();
        sliderGrayLevelImportFile = new javax.swing.JSlider();
        buttonGenerate = new javax.swing.JButton();
        buttonLensON = new javax.swing.JButton();
        buttonSecond = new javax.swing.JButton();
        txtFormula = new javax.swing.JTextField();

        lblHeaderImportFile.setText("U1 = ");
        lblK.setText("K = ");

        txtKImportFile.setText("0.5");

        lblR.setText("R = ");

        txtRImportFile.setText("24");

        lblE1.setText("E1 = ");

        txtE1ImportFile.setText("1.42");

        lblKR.setText("K1.R = ");

        txtK1RImportFile.setText("12");

        lbltWidthImportFile.setText(labels.getString("paramWidth"));

        txtWidthImportFile.setText("100");

        lblRotationImportFile.setText(labels.getString("paramRotation"));

        txtRotationImportFile.setText("0");

        lblPositionImportFile.setText(labels.getString("paramPositions"));

        txtPositionImportFile.setText("0");

        lblGrayLevelImportFile.setText(labels.getString("paramGrayLevel"));

        txtGrayLevelImportFile.setText("255");

        buttonGenerate.setText(labels.getString("btnGenerate"));

        buttonLensON.setText(labels.getString("btnLensOn"));

        buttonSecond.setText(labels.getString("btnSecondDisplayOn"));
        
        Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sliderWidthImportFile, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtWidthImportFile, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sliderRotationImportFile, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtRotationImportFile, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sliderPositionImportFile, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtPositionImportFile, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sliderGrayLevelImportFile, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtGrayLevelImportFile, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        
        sliderWidthImportFile.setMaximum(180);
        sliderWidthImportFile.setMinimum(-180);
        sliderWidthImportFile.setValue(0);
        sliderWidthImportFile.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedImportFile(evt);
            }
        });
        sliderRotationImportFile.setMaximum(180);
        sliderRotationImportFile.setMinimum(-180);
        sliderRotationImportFile.setValue(0);
        sliderRotationImportFile.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedImportFile(evt);
            }
        });
        sliderPositionImportFile.setMaximum(image1.getBounds().height / 2);
        sliderPositionImportFile.setMinimum(-(image1.getBounds().height / 2));
        sliderPositionImportFile.setValue(0);
        sliderPositionImportFile.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedImportFile(evt);
            }
        });
        sliderGrayLevelImportFile.setMaximum(255);
        sliderGrayLevelImportFile.setMinimum(0);
        sliderGrayLevelImportFile.setValue(255);
        sliderGrayLevelImportFile.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedImportFile(evt);
            }
        });
        
        buttonGenerate.setText(labels.getString("btnGenerate"));
        buttonGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformedImportFile(evt);
            }
        });

        buttonLensON.setEnabled(false);
        buttonLensON.setText(labels.getString("btnLensOn"));
        buttonLensON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnImportFileActionPerformed(evt);
                countLenOnImportFile++;
                if (countLenOnImportFile % 2 == 0) {
                    buttonLensON.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                } else {
                    buttonLensON.setText(labels.getString("btnLensOn"));
                }
            }
        });

        buttonSecond.setEnabled(false);
        buttonSecond.setText(labels.getString("btnSecondDisplayOn"));
        buttonSecond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedImportFile(evt);
                countSecondDisplayImportFile++;
                if (countSecondDisplayImportFile % 2 == 0) {
                    buttonSecond.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    buttonSecond.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });

        javax.swing.GroupLayout layoutImportFile = new javax.swing.GroupLayout(panel);
        panel.setLayout(layoutImportFile);
        layoutImportFile.setHorizontalGroup(
            layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutImportFile.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layoutImportFile.createSequentialGroup()
                        .addComponent(buttonGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(buttonLensON, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(buttonSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 155, Short.MAX_VALUE))
                    .addGroup(layoutImportFile.createSequentialGroup()
                        .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblKR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblE1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtK1RImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtKImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtE1ImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtRImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)
                        .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbltWidthImportFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRotationImportFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPositionImportFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGrayLevelImportFile, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPositionImportFile, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRotationImportFile, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWidthImportFile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                            .addComponent(txtGrayLevelImportFile))
                        .addGap(18, 18, 18)
                        .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sliderWidthImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderGrayLevelImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderPositionImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderRotationImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36))
            .addGroup(layoutImportFile.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(lblHeaderImportFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layoutImportFile.setVerticalGroup(
            layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutImportFile.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHeaderImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtKImportFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblK)
                            .addComponent(lbltWidthImportFile)
                            .addComponent(txtWidthImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(sliderWidthImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblR)
                        .addComponent(txtRImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRotationImportFile)
                        .addComponent(txtRotationImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sliderRotationImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblE1)
                        .addComponent(txtE1ImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPositionImportFile)
                        .addComponent(txtPositionImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sliderPositionImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliderGrayLevelImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblKR)
                        .addComponent(txtK1RImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGrayLevelImportFile)
                        .addComponent(txtGrayLevelImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(114, 114, 114)
                .addGroup(layoutImportFile.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLensON, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                )
        );
    }
    
    public JPanel getPanel() {
        return panel;
    }
    
    public JTextArea getLogArea() {
        return txtAreaLog;
    }
    
    private void sliderGenerateActionPerformedImportFile(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedImportFile
        actionTag = "ImportFile";
        if (parseArguments()) {
            buttonLensON.setEnabled(true);
            buttonSecond.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateParameterImportFile(k, r, e, kr, width_importFile, position_importFile, rotation_importFile, grayLevel_importFile, formula);
            image.paintImportFormula();
            EduPatternShowOn.updateLensPatternPattern(image, "");
            setLog("");
            imageGenerated = true;
        }
    }//GEN-LAST:event_sliderGenerateActionPerformedImportFile
    
    private void buttonGenerateActionPerformedImportFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedImportFile
        actionTag = "ImportFile";
        if (parseArguments()) {
            buttonLensON.setEnabled(true);
            buttonSecond.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateParameterImportFile(k, r, e, kr, width_importFile, position_importFile, rotation_importFile, grayLevel_importFile, formula);
            image.paintImportFormula();
            EduPatternShowOn.updateLensPatternPattern(image, "");
            setLog("");
            imageGenerated = true;
        }

    }

    private void button11LensOnImportFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnImportFileActionPerformed
        actionTag = "ImportFile";
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateParameterImportFile(k, r, e, kr, width_importFile, position_importFile, rotation_importFile, grayLevel_importFile, formula);
            image.paintImportFormula();
            EduPatternShowOn.updateLensPatternPattern(image, "");
            setLog("");
            imageGenerated = true;

            if (countLenOnImportFile % 2 == 0) {
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
                magFrameLenon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                magFrameLenon.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                            countLenOnImportFile--;
                            buttonLensON.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }

        }

    }//GEN-LAST:event_button11LensOnImportFileActionPerformed

    private void buttonSecondGenerateActionPerformedImportFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedImportFile
        actionTag = "ImportFile";
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplayImportFile--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateParameterImportFile(k, r, e, kr, width_importFile, position_importFile, rotation_importFile, grayLevel_importFile, formula);
                image.paintImportFormula();
                EduPatternShowOn.updatePatternSecondDisplay(image, "");
                setLog("");
                imageGenerated = true;
                if (countSecondDisplayImportFile % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }
    
   public void setLog(String msg) {
        txtAreaLog.append(msg + System.getProperty("line.separator"));
    }

    @Override
    public void updatePatternScreen() {
        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        if (!imageGenerated) {
            image.updateLensParameter(xoff, yoff, focal);
            image.paintLens();
            imageGenerated = true;
        }
        EduPatternShowOn.updatePatternScreen(image, "");
    }

    @Override
    public void updateRegenerate() {
         PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        if (imageGenerated) {
            image.updateLensParameter(xoff, yoff, focal);
            image.paintLens();
        }
    }
    
    private boolean parseArguments() {
        boolean ret = false;
        try {          
            // Import file
            String uImportFile = txtFormula.getText();
            double kImportFile = Double.valueOf(txtKImportFile.getText());
            double rImportFile= Double.valueOf(txtRImportFile.getText());
            double eImportFile = Double.valueOf(txtE1ImportFile.getText());
            double krImportFile= Double.valueOf(txtK1RImportFile.getText());
            double widthImportFile = Double.valueOf(txtWidthImportFile.getText());
            double positionImportFile = Double.valueOf(txtPositionImportFile.getText());
            double rotationImportFile= Double.valueOf(txtRotationImportFile.getText());
            double grayImportFile = Double.valueOf(txtGrayLevelImportFile.getText());
            
            this.formula = uImportFile;
            this.k = kImportFile;
            this.r = rImportFile;
            this.e = eImportFile;
            this.kr = krImportFile;
            this.width_importFile = widthImportFile;
            this.position_importFile = positionImportFile;
            this.rotation_importFile = rotationImportFile;
            this.position_importFile = grayImportFile;
            ret = true;
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, warnings);
            //textXpos.setText(String.valueOf(this.yoff));
            //textYpos.setText(String.valueOf(this.yoff));
            textFocal.setText(String.valueOf(this.focal));
        }
        return ret;
    }
}

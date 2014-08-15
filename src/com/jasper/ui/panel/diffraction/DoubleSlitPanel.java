/*
 * @(#)DoubleSlitPanel.java
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
package com.jasper.ui.panel.diffraction;

import com.jasper.core.OpticsPane;
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
import com.jasper.ui.EduLensOn11;
import com.jasper.ui.EduPatternJPanel;
import com.jasper.ui.EduPatternShowOn;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;

import static com.jasper.ui.EduPatternShowOn.patternFrameDoubleClick;
import static com.jasper.ui.EduPatternShowOn.patternFrame;
import com.jasper.utils.Constant;
import com.jasper.utils.Utils;
import java.awt.Robot;
import javax.swing.JTextArea;
/**
 *
 * @author sonnv
 */
public class DoubleSlitPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    
    private javax.swing.JButton buttonGennerateDoubleSlit;
    private javax.swing.JButton buttonSecondDisplayDoubleSlit;
    private javax.swing.JButton buttong11LensOnDoubleSlit;
    private javax.swing.JLabel jLabelSpacingDoubleSlit;
    private javax.swing.JLabel lblGrayDoubleSlit;
    private javax.swing.JLabel lblHeightDoubleSlit;
    private javax.swing.JLabel lblPosDoubleSlit;
    private javax.swing.JLabel lblRotationDoubleSlit;
    private javax.swing.JLabel lblWidthDoubleSlit;
    private javax.swing.JSlider s_single_grayDoubleSlit;
    private javax.swing.JSlider s_single_heightDoubleSlit;
    private javax.swing.JSlider s_single_positionDoubleSlit;
    private javax.swing.JSlider s_single_rotationDoubleSlit;
    private javax.swing.JSlider s_single_spacingDoubleSlit;
    private javax.swing.JSlider s_single_widthDoubleSlit;
    private javax.swing.JTextField text_single_grayDoubleSlit;
    private javax.swing.JTextField text_single_heightDoubleSlit;
    private javax.swing.JTextField text_single_positionDoubleSlit;
    private javax.swing.JTextField text_single_rotationDoubleSlit;
    private javax.swing.JTextField text_single_spacingDoubleSlit;
    private javax.swing.JTextField text_single_widthDoubleSlit;
    private int slit = 2;
    
    static String logmessageDoubleSlit = "Double Slit: w=%s r=%s p=%s g=%s s=%s";
    private double d_widthX_double = Double.valueOf(image1.getBounds().width), d_heightX_double = 100, d_rotation_double = 0, d_postionX_double = 0, d_grayLevel_double = 255, d_spacing_double = 400;
    private int countSecondDisplayDoubleSlit = 1;
    private int countLenOnDoubleSlit = 1;
    private javax.swing.JPanel panelDoubleSlit;
    private javax.swing.JTextArea txtAreaLog;
    private javax.swing.JPanel panelButton;
    
      public DoubleSlitPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        this.txtAreaLog = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panelButton = new javax.swing.JPanel();
        panelDoubleSlit = new javax.swing.JPanel();
        lblWidthDoubleSlit = new javax.swing.JLabel();
        lblHeightDoubleSlit = new javax.swing.JLabel();
        lblRotationDoubleSlit = new javax.swing.JLabel();
        lblPosDoubleSlit = new javax.swing.JLabel();
        lblGrayDoubleSlit = new javax.swing.JLabel();
        text_single_widthDoubleSlit = new javax.swing.JTextField();
        text_single_heightDoubleSlit = new javax.swing.JTextField();
        text_single_rotationDoubleSlit = new javax.swing.JTextField();
        text_single_positionDoubleSlit = new javax.swing.JTextField();
        text_single_grayDoubleSlit = new javax.swing.JTextField();
        s_single_widthDoubleSlit = new javax.swing.JSlider();
        s_single_heightDoubleSlit = new javax.swing.JSlider();
        s_single_rotationDoubleSlit = new javax.swing.JSlider();
        s_single_positionDoubleSlit = new javax.swing.JSlider();
        s_single_grayDoubleSlit = new javax.swing.JSlider();
        jLabelSpacingDoubleSlit = new javax.swing.JLabel();
        text_single_spacingDoubleSlit = new javax.swing.JTextField();
        s_single_spacingDoubleSlit = new javax.swing.JSlider();
        buttonGennerateDoubleSlit = new javax.swing.JButton();
        buttong11LensOnDoubleSlit = new javax.swing.JButton();
        buttonSecondDisplayDoubleSlit = new javax.swing.JButton();

        
        lblWidthDoubleSlit.setText(labels.getString("paramWidth"));

        lblHeightDoubleSlit.setText(labels.getString("paramWidth"));

        lblRotationDoubleSlit.setText(labels.getString("paramRotationNoDegree"));

        lblPosDoubleSlit.setText(labels.getString("paramPositions"));

        lblGrayDoubleSlit.setText(labels.getString("paramGrayLevel"));

        Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_widthDoubleSlit, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_widthDoubleSlit, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_heightDoubleSlit, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_heightDoubleSlit, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_rotationDoubleSlit, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_rotationDoubleSlit, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_positionDoubleSlit, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_positionDoubleSlit, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_grayDoubleSlit, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_grayDoubleSlit, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabelSpacingDoubleSlit.setText(labels.getString("paramSpacing"));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_spacingDoubleSlit, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_spacingDoubleSlit, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        s_single_widthDoubleSlit.setMaximum(3866);
        s_single_widthDoubleSlit.setValue(3866);
        s_single_widthDoubleSlit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedDoubleSlit(evt);
            }
        });
        s_single_heightDoubleSlit.setMaximum(image1.getBounds().height);
        s_single_heightDoubleSlit.setValue(100);
        s_single_heightDoubleSlit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedDoubleSlit(evt);
            }
        });
        s_single_rotationDoubleSlit.setMaximum(180);
        s_single_rotationDoubleSlit.setMinimum(-180);
        s_single_rotationDoubleSlit.setValue(0);
        s_single_rotationDoubleSlit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedDoubleSlit(evt);
            }
        });
        s_single_positionDoubleSlit.setMaximum(image1.getBounds().height / 2);
        s_single_positionDoubleSlit.setMinimum(-(image1.getBounds().height / 2));
        s_single_positionDoubleSlit.setValue(0);
        s_single_positionDoubleSlit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedDoubleSlit(evt);
            }
        });
        s_single_grayDoubleSlit.setMajorTickSpacing(255);
        s_single_grayDoubleSlit.setPaintLabels(true);
        s_single_grayDoubleSlit.setMaximum(255);
        s_single_grayDoubleSlit.setValue(255);
        s_single_grayDoubleSlit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedDoubleSlit(evt);
            }
        });
        s_single_spacingDoubleSlit.setMaximum(image1.getBounds().height);
        s_single_spacingDoubleSlit.setValue(150);
        s_single_spacingDoubleSlit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedDoubleSlit(evt);
            }
        });
        
        text_single_heightDoubleSlit.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedDoubleSlit(ke);
                if(text_single_heightDoubleSlit.getText() == null || text_single_heightDoubleSlit.getText().equals("")){
                    lblHeightDoubleSlit.setForeground(Color.red);
                } else {
                    lblHeightDoubleSlit.setForeground(Color.black);
                }
            }
        });
        
        text_single_rotationDoubleSlit.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedDoubleSlit(ke);
                if(text_single_rotationDoubleSlit.getText() == null || text_single_rotationDoubleSlit.getText().equals("")){
                    lblRotationDoubleSlit.setForeground(Color.red);
                } else {
                    lblRotationDoubleSlit.setForeground(Color.black);
                }
            }
        });
        
        text_single_positionDoubleSlit.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedDoubleSlit(ke);
                if(text_single_positionDoubleSlit.getText() == null || text_single_positionDoubleSlit.getText().equals("")){
                    lblPosDoubleSlit.setForeground(Color.red);
                } else {
                    lblPosDoubleSlit.setForeground(Color.black);
                }
            }
        });
        
        text_single_grayDoubleSlit.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedDoubleSlit(ke);
                if(text_single_grayDoubleSlit.getText() == null || text_single_grayDoubleSlit.getText().equals("")){
                    lblGrayDoubleSlit.setForeground(Color.red);
                } else {
                    lblGrayDoubleSlit.setForeground(Color.black);
                }
            }
        });
        
        text_single_spacingDoubleSlit.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedDoubleSlit(ke);
                if(text_single_spacingDoubleSlit.getText() == null || text_single_spacingDoubleSlit.getText().equals("")){
                    jLabelSpacingDoubleSlit.setForeground(Color.red);
                } else {
                    jLabelSpacingDoubleSlit.setForeground(Color.black);
                }
            }
        });

        buttonGennerateDoubleSlit.setText(labels.getString("btnGenerate"));
        buttonGennerateDoubleSlit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformedDoubleSlit(evt);
            }
        });
        buttong11LensOnDoubleSlit.setEnabled(false);
        buttong11LensOnDoubleSlit.setText(labels.getString("btnLensOn"));
        buttong11LensOnDoubleSlit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnDoubleSlitActionPerformed(evt);
                countLenOnDoubleSlit++;
                if (countLenOnDoubleSlit % 2 == 0) {
                    buttong11LensOnDoubleSlit.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                } else {
                    buttong11LensOnDoubleSlit.setText(labels.getString("btnLensOn"));
                }
            }
        });
        buttonSecondDisplayDoubleSlit.setEnabled(false);
        buttonSecondDisplayDoubleSlit.setText(labels.getString("btnSecondDisplayOn"));
        buttonSecondDisplayDoubleSlit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedDoubleSlit(evt);
                countSecondDisplayDoubleSlit++;
                if (countSecondDisplayDoubleSlit % 2 == 0) {
                    buttonSecondDisplayDoubleSlit.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    buttonSecondDisplayDoubleSlit.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });
        
        javax.swing.GroupLayout panelButtonDoubleSlitLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonDoubleSlitLayout);
        panelButtonDoubleSlitLayout.setHorizontalGroup(
                panelButtonDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonDoubleSlitLayout.createSequentialGroup()
                .addGroup(panelButtonDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonDoubleSlitLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonGennerateDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttong11LensOnDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonSecondDisplayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                )));
        if (!Utils.isMac()) {
        panelButtonDoubleSlitLayout.setVerticalGroup(
                panelButtonDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonDoubleSlitLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(panelButtonDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonSecondDisplayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttong11LensOnDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonGennerateDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        } else {
            panelButtonDoubleSlitLayout.setVerticalGroup(
                panelButtonDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonDoubleSlitLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panelButtonDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonSecondDisplayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttong11LensOnDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonGennerateDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        }
        javax.swing.GroupLayout panelDoubleSlitLayout = new javax.swing.GroupLayout(panelDoubleSlit);
        panelDoubleSlit.setLayout(panelDoubleSlitLayout);
        
        if (!Utils.isMac()) {
            panelDoubleSlitLayout.setHorizontalGroup(
            panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDoubleSlitLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDoubleSlitLayout.createSequentialGroup()
                        .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblRotationDoubleSlit, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(lblHeightDoubleSlit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPosDoubleSlit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(text_single_heightDoubleSlit, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(text_single_rotationDoubleSlit)
                            .addComponent(text_single_positionDoubleSlit))
                        .addGap(15, 15, 15)
                        .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_single_heightDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s_single_rotationDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s_single_positionDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDoubleSlitLayout.createSequentialGroup()
                        .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblGrayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSpacingDoubleSlit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDoubleSlitLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(text_single_spacingDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(s_single_spacingDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDoubleSlitLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(text_single_grayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(s_single_grayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                                ))))
                )
        );
        panelDoubleSlitLayout.setVerticalGroup(
            panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoubleSlitLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_single_heightDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHeightDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text_single_heightDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_single_rotationDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRotationDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(s_single_rotationDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPosDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(s_single_positionDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text_single_positionDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSpacingDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text_single_spacingDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(s_single_spacingDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDoubleSlitLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(s_single_grayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                    .addGroup(panelDoubleSlitLayout.createSequentialGroup()
                        .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGrayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_single_grayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        )))
        );
        } else {
            panelDoubleSlitLayout.setVerticalGroup(
            panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoubleSlitLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_single_heightDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHeightDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text_single_heightDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_single_rotationDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRotationDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(s_single_rotationDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPosDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(s_single_positionDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text_single_positionDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSpacingDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text_single_spacingDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(s_single_spacingDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDoubleSlitLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(s_single_grayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                    .addGroup(panelDoubleSlitLayout.createSequentialGroup()
                        .addGroup(panelDoubleSlitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGrayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_single_grayDoubleSlit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        )))
        );
        }
        
    }
    
    public JPanel getPanel() {
        return panelDoubleSlit;
    }
    
    public JPanel getPanelButton() {
        return panelButton;
    }
    
    public JTextArea getLogArea() {
        return txtAreaLog;
    }
    
     private void buttonGenerateActionPerformedDoubleSlit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedDoubleSlit
        if (parseArguments()) {
            buttong11LensOnDoubleSlit.setEnabled(true);
            buttonSecondDisplayDoubleSlit.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensParameterDrawSlit(2, d_widthX_double, d_heightX_double, d_postionX_double, d_rotation_double, d_grayLevel_double, d_spacing_double);
            image.paintSlit(slit);
            EduPatternShowOn.updatePattern(image, genLogDoubleSlit());
            setLog(genLogDoubleSlit());
            imageGenerated = true;
        }

    }//GEN-LAST:event_buttonGenerateActionPerformedDoubleSlit

    private void button11LensOnDoubleSlitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnDoubleSlitActionPerformed
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensParameterDrawSlit(2, d_widthX_double, d_heightX_double, d_postionX_double, d_rotation_double, d_grayLevel_double, d_spacing_double);
            image.paintSlit(slit);
            EduPatternShowOn.updatePattern(image, genLogDoubleSlit());
            setLog(genLogDoubleSlit());
            imageGenerated = true;

            if (countLenOnDoubleSlit % 2 == 0) {
                magFrameLenon.dispose();
                panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        patternFrameDoubleClick.show();
                    }
                });
            } else {
                if (!Utils.isMac()) {
                    magFrameLenon = new JFrame("1:1 Lens On");
                    URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
                    Toolkit kit = Toolkit.getDefaultToolkit();
                    Image img = kit.createImage(url);
                    magFrameLenon.setIconImage(img);
                    magFrameLenon.setResizable(false);

                    EduLensOn11 mag = new EduLensOn11(panelPattern, new Dimension(120, 120));
                    magFrameLenon.getContentPane().add(mag);
                    magFrameLenon.pack();
                    magFrameLenon.setLocation(new Point(568, 450));
                    magFrameLenon.setResizable(false);
                    magFrameLenon.setVisible(true);
                    magFrameLenon.setAlwaysOnTop(true);
                } else {
                    Robot robot;
                    try {
                        robot = new Robot();
                        robot.mouseMove(Constant.LENS_ON_MOUSE_X, Constant.LENS_ON_MOUSE_Y);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    magFrameLenon = new JFrame(labels.getString("btnLensOn"));
                    URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
                    Toolkit kit = Toolkit.getDefaultToolkit();
                    Image img = kit.createImage(url);
                    magFrameLenon.setIconImage(img);

                    EduLensOn11 mag = new EduLensOn11(panelPattern,
                            new Dimension(Constant.LENS_ON_PANEL_WIDTH, Constant.LENS_ON_PANEL_HEIGHT));
                    magFrameLenon.getContentPane().add(mag);
                    magFrameLenon.pack();
                    magFrameLenon.setLocation(new Point(Constant.LENS_ON_LOCAL_X, Constant.LENS_ON_LOCAL_Y));
                    magFrameLenon.setResizable(false);
                    magFrameLenon.setVisible(true);
                    magFrameLenon.setAlwaysOnTop(true);
                    magFrameLenon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
                magFrameLenon.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                            countLenOnDoubleSlit--;
                            buttong11LensOnDoubleSlit.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }

        }

    }//GEN-LAST:event_button11LensOnDoubleSlitActionPerformed

    private void buttonSecondGenerateActionPerformedDoubleSlit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedDoubleSlit
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplayDoubleSlit--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateLensParameterDrawSlit(2, d_widthX_double, d_heightX_double, d_postionX_double, d_rotation_double, d_grayLevel_double, d_spacing_double);
                image.paintSlit(slit);
                EduPatternShowOn.updatePatternSecondDisplay(image, genLogDoubleSlit());
                setLog(genLogDoubleSlit());
                imageGenerated = true;
                if (countSecondDisplayDoubleSlit % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }//GEN-LAST:event_buttonSecondGenerateActionPerformedDoubleSlit

    private void sliderGenerateActionPerformedDoubleSlit(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedDoubleSlit
        if (parseArguments()) {
            buttong11LensOnDoubleSlit.setEnabled(true);
            buttonSecondDisplayDoubleSlit.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensParameterDrawSlit(2, d_widthX_double, d_heightX_double, d_postionX_double, d_rotation_double, d_grayLevel_double, d_spacing_double);
            image.paintSlit(slit);
            EduPatternShowOn.updatePattern(image, genLogDoubleSlit());
            setLog(genLogDoubleSlit());
            imageGenerated = true;
        }
    }//GEN-LAST:event_sliderGenerateActionPerformedDoubleSlit
    private void keyeventGenerateActionPerformedDoubleSlit(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyeventGenerateActionPerformedDoubleSlit
        if (parseArguments()) {
            buttong11LensOnDoubleSlit.setEnabled(true);
            buttonSecondDisplayDoubleSlit.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensParameterDrawSlit(2, d_widthX_double, d_heightX_double, d_postionX_double, d_rotation_double, d_grayLevel_double, d_spacing_double);
            image.paintSlit(slit);
            EduPatternShowOn.updatePattern(image, genLogDoubleSlit());
            setLog(genLogDoubleSlit());
            imageGenerated = true;
        }
    }
 
    private String genLogDoubleSlit() {
        return String.format(logmessageDoubleSlit, Double.toString(d_heightX_double), Double.toString(d_rotation_double), Double.toString(d_postionX_double), Double.toString(d_grayLevel_double), Double.toString(d_spacing_double));
    }
    
    private boolean parseArguments() {
        boolean ret = false;
        try {          
           
             double widthDoubleSlit = Double.valueOf(s_single_widthDoubleSlit.getValue());
            double heightDoubleSlit = Double.valueOf(s_single_heightDoubleSlit.getValue());
            double postionDoubleSlit = Double.valueOf(s_single_positionDoubleSlit.getValue());
            double rotationDoubleSlit = Double.valueOf(s_single_rotationDoubleSlit.getValue());
            double grayLevelDoubleSlit = Double.valueOf(s_single_grayDoubleSlit.getValue());
            double spacingDoubleSlit = Double.valueOf(s_single_spacingDoubleSlit.getValue());
             this.d_widthX_double = widthDoubleSlit;
            this.d_heightX_double = heightDoubleSlit;
            this.d_postionX_double = postionDoubleSlit;
            this.d_rotation_double = rotationDoubleSlit;
            this.d_grayLevel_double = grayLevelDoubleSlit;
            this.d_spacing_double = spacingDoubleSlit;
            ret = true;
            
        } catch (Exception e) {
        }
        return ret;
    }
    
     public void setLog(String msg) {
        txtAreaLog.append(msg + System.getProperty("line.separator"));
    }
}

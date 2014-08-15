/*
 * @(#)SignalPanel.java
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
package com.jasper.ui.panel.signalprocessing;

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
public class SignalPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    
     private javax.swing.JButton button11LensOnProcessing;
    private javax.swing.JButton buttonGennerateProcessing;
    private javax.swing.JButton buttonSecondDisplayProcessing;
    private javax.swing.JLabel jLabelGrayProcessing;
    private javax.swing.JLabel lblHeightY;
    private javax.swing.JLabel lblHeightX;
    private javax.swing.JLabel lblPosX;
    private javax.swing.JLabel lblPosY;
    private javax.swing.JLabel lblRotationProcessing;
    private javax.swing.JLabel lblSpacingProcessing;
    private javax.swing.JSlider s_processing_gray;
    private javax.swing.JSlider s_processing_height_x;
    private javax.swing.JSlider s_processing_height_y;
    private javax.swing.JSlider s_processing_pos_y;
    private javax.swing.JSlider s_processing_positionx;
    private javax.swing.JSlider s_processing_rotation;
    private javax.swing.JSlider s_processing_width_x;
    private javax.swing.JSlider s_processing_width_y;
    private javax.swing.JSlider s_processing_spacing;
    private javax.swing.JTextField text_height_processing_x;
    private javax.swing.JTextField text_height_processing_y;
    private javax.swing.JTextField text_position_processing_x;
    private javax.swing.JTextField text_position_processing_y;
    private javax.swing.JTextField text_processing_gray;
    private javax.swing.JTextField text_rotation_processing;
    private javax.swing.JTextField text_width_processing_x;
    private javax.swing.JTextField text_width_processing_y;
    private javax.swing.JTextField text_processing_spacing;
    
    static String logmessageProcessing = "Signal processing: w_x=%s w_y=%s r=%s p_x=%s p_y=%s g=%s spac=%s";
    private double processing_widthX = Double.valueOf(image1.getBounds().width), processing_widthY = 100, processing_heightX = 100, processing_heightY = Double.valueOf(image1.getBounds().height), processing_rotation = 0, processing_positionX = 0, processing_positionY = 0, processing_grayLevel = 255, processing_spacing = 400;
    private int countSecondDisplayProcessing = 1;
    private int countLenOnProcessing = 1;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panel;
    private javax.swing.JTextArea txtLogArea;
    
    public SignalPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        this.txtLogArea = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panelButton = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        lblHeightY = new javax.swing.JLabel();
        lblRotationProcessing = new javax.swing.JLabel();
        lblPosX = new javax.swing.JLabel();
        lblPosY = new javax.swing.JLabel();
        lblSpacingProcessing = new javax.swing.JLabel();
        text_width_processing_y = new javax.swing.JTextField();
        text_height_processing_y = new javax.swing.JTextField();
        text_rotation_processing = new javax.swing.JTextField();
        text_position_processing_x = new javax.swing.JTextField();
        text_position_processing_y = new javax.swing.JTextField();
        text_processing_spacing = new javax.swing.JTextField();
        s_processing_width_y = new javax.swing.JSlider();
        s_processing_height_y = new javax.swing.JSlider();
        s_processing_rotation = new javax.swing.JSlider();
        s_processing_positionx = new javax.swing.JSlider();
        s_processing_pos_y = new javax.swing.JSlider();
        s_processing_spacing = new javax.swing.JSlider();
        jLabelGrayProcessing = new javax.swing.JLabel();
        text_processing_gray = new javax.swing.JTextField();
        s_processing_gray = new javax.swing.JSlider();
        buttonGennerateProcessing = new javax.swing.JButton();
        button11LensOnProcessing = new javax.swing.JButton();
        buttonSecondDisplayProcessing = new javax.swing.JButton();
        text_width_processing_x = new javax.swing.JTextField();
        s_processing_width_x = new javax.swing.JSlider();
        lblHeightX = new javax.swing.JLabel();
        text_height_processing_x = new javax.swing.JTextField();
        s_processing_height_x = new javax.swing.JSlider();
        // lblWidthY.setText("Width Y");
        lblHeightY.setText("Width Y");

        lblRotationProcessing.setText(labels.getString("paramRotationNoDegree"));

        lblPosX.setText("Position X");

        lblPosY.setText("Position Y");

        Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_processing_width_y, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_width_processing_y, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_processing_width_y.setMaximum(image1.getBounds().width);
        s_processing_width_y.setValue(100);
        s_processing_width_y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedProcessing(evt);
            }
        });
        text_width_processing_y.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedProcessing(ke);
                if(text_width_processing_y.getText() == null || text_width_processing_y.getText().equals("")){
                    lblHeightY.setForeground(Color.red);
                } else {
                    lblHeightY.setForeground(Color.black);
                }
                
            }
        });
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_processing_height_y, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_height_processing_y, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_processing_height_y.setMaximum(9999);
        s_processing_height_y.setValue(9999);
        s_processing_height_y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedProcessing(evt);
            }
        });
        
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_processing_rotation, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_rotation_processing, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_processing_rotation.setMaximum(180);
        s_processing_rotation.setMinimum(-180);
        s_processing_rotation.setValue(0);
        s_processing_rotation.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedProcessing(evt);
            }
        });
        text_rotation_processing.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedProcessing(ke);
                if(text_rotation_processing.getText() == null || text_rotation_processing.getText().equals("")){
                    lblRotationProcessing.setForeground(Color.red);
                } else {
                    lblRotationProcessing.setForeground(Color.black);
                }
            }
        });
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_processing_positionx, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_position_processing_x, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_processing_positionx.setMaximum(image1.getBounds().height / 2);
        s_processing_positionx.setMinimum(-(image1.getBounds().height / 2));
        s_processing_positionx.setValue(100);
        s_processing_positionx.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedProcessing(evt);
            }
        });
        text_position_processing_x.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedProcessing(ke);
                if(text_position_processing_x.getText() == null || text_position_processing_x.getText().equals("")){
                    lblPosX.setForeground(Color.red);
                } else {
                    lblPosX.setForeground(Color.black);
                }
            }
        });
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_processing_pos_y, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_position_processing_y, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_processing_pos_y.setMaximum(image1.getBounds().width / 2);
        s_processing_pos_y.setMinimum(-(image1.getBounds().width / 2));
        s_processing_pos_y.setValue(0);
        s_processing_pos_y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedProcessing(evt);
            }
        });
        text_position_processing_y.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedProcessing(ke);
                if(text_position_processing_y.getText() == null || text_position_processing_y.getText().equals("")){
                    lblPosY.setForeground(Color.red);
                } else {
                    lblPosY.setForeground(Color.black);
                }
            }
        });
        bindingGroup.addBinding(binding);

        jLabelGrayProcessing.setText(labels.getString("paramGrayLevel"));
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_processing_gray, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_processing_gray, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_processing_gray.setMajorTickSpacing(255);
        s_processing_gray.setPaintLabels(true);
        s_processing_gray.setMaximum(255);
        s_processing_gray.setValue(255);
        s_processing_gray.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedProcessing(evt);
            }
        });
        text_processing_gray.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedProcessing(ke);
                if(text_processing_gray.getText() == null || text_processing_gray.getText().equals("")){
                    jLabelGrayProcessing.setForeground(Color.red);
                } else {
                    jLabelGrayProcessing.setForeground(Color.black);
                }
            }
        });
        bindingGroup.addBinding(binding);

        lblSpacingProcessing.setText(labels.getString("paramSpacing"));
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_processing_spacing, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_processing_spacing, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_processing_spacing.setMaximum(image1.getBounds().height);
        s_processing_spacing.setMinimum(-(image1.getBounds().height));
        s_processing_spacing.setValue(0);
        s_processing_spacing.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedProcessing(evt);
            }
        });
        text_processing_spacing.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedProcessing(ke);
                if(text_processing_spacing.getText() == null || text_processing_spacing.getText().equals("")){
                    lblSpacingProcessing.setForeground(Color.red);
                } else {
                    lblSpacingProcessing.setForeground(Color.black);
                }
            }
        });
        bindingGroup.addBinding(binding);

        buttonGennerateProcessing.setText(labels.getString("btnGenerate"));
        buttonGennerateProcessing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformedProcessing(evt);
            }
        });
        button11LensOnProcessing.setEnabled(false);
        button11LensOnProcessing.setText(labels.getString("btnLensOn"));
        button11LensOnProcessing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnProcessingActionPerformed(evt);
                //   boolean_checkLen = false;
                countLenOnProcessing++;
                if (countLenOnProcessing % 2 == 0) {
                    button11LensOnProcessing.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                } else {
                    button11LensOnProcessing.setText(labels.getString("btnLensOn"));
                }
            }
        });
        buttonSecondDisplayProcessing.setEnabled(false);
        buttonSecondDisplayProcessing.setText(labels.getString("btnSecondDisplayOn"));
        buttonSecondDisplayProcessing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedProcessing(evt);
                countSecondDisplayProcessing++;
                if (countSecondDisplayProcessing % 2 == 0) {
                    buttonSecondDisplayProcessing.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    buttonSecondDisplayProcessing.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_processing_width_x, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_width_processing_x, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_processing_width_x.setMaximum(3866);
        s_processing_width_x.setValue(3866);
        s_processing_width_x.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedProcessing(evt);
            }
        });
        bindingGroup.addBinding(binding);

        lblHeightX.setText("Width X");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_processing_height_x, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_height_processing_x, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_processing_height_x.setMaximum(image1.getBounds().height);
        s_processing_height_x.setValue(100);
        s_processing_height_x.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedProcessing(evt);
            }
        });
        text_height_processing_x.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedProcessing(ke);
                if(text_height_processing_x.getText() == null || text_height_processing_x.getText().equals("")){
                    lblHeightX.setForeground(Color.red);
                } else {
                    lblHeightX.setForeground(Color.black);
                }
            }
        });
        bindingGroup.addBinding(binding);
        
        javax.swing.GroupLayout panelButtonSignalLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonSignalLayout);
        
        if (!Utils.isMac()) {
            panelButtonSignalLayout.setHorizontalGroup(
                panelButtonSignalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonSignalLayout.createSequentialGroup()
                .addGroup(panelButtonSignalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonSignalLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonGennerateProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button11LensOnProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonSecondDisplayProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)                
                )
                )));
            panelButtonSignalLayout.setVerticalGroup(
                panelButtonSignalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonSignalLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(panelButtonSignalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonSecondDisplayProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(button11LensOnProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonGennerateProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        } else {
            panelButtonSignalLayout.setHorizontalGroup(
                panelButtonSignalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonSignalLayout.createSequentialGroup()
                .addGroup(panelButtonSignalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonSignalLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonGennerateProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button11LensOnProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonSecondDisplayProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)                
                )
                )));
            panelButtonSignalLayout.setVerticalGroup(
                panelButtonSignalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonSignalLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelButtonSignalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonSecondDisplayProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(button11LensOnProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonGennerateProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        }
        
        javax.swing.GroupLayout jPanelProcessingLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanelProcessingLayout);
        if (!Utils.isMac()) {
        jPanelProcessingLayout.setHorizontalGroup(
                jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addContainerGap()
                .addGap(15, 15, 15)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProcessingLayout.createSequentialGroup()
                .addComponent(lblPosX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(text_position_processing_x, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblPosY)
                .addComponent(lblSpacingProcessing))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(text_processing_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(text_position_processing_y))))
                .addGap(17, 17, 17)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(s_processing_pos_y, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(s_processing_positionx, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(s_processing_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)))
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblHeightY)
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addComponent(lblHeightX)
                .addGap(33, 33, 33)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(text_width_processing_y, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(text_height_processing_x, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(s_processing_height_x, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addComponent(s_processing_width_y, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addComponent(lblRotationProcessing)
                .addGap(27, 27, 27)
                .addComponent(text_rotation_processing, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(s_processing_rotation, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addComponent(jLabelGrayProcessing)
                .addGap(17, 17, 17)
                .addComponent(text_processing_gray, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(s_processing_gray, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))))
                )));
        jPanelProcessingLayout.setVerticalGroup(
                jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProcessingLayout.createSequentialGroup()
                //.addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addComponent(s_processing_height_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(text_height_processing_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblHeightX))
                .addGap(6, 6, 6)))
                .addGap(2, 2, 2)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(text_width_processing_y, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblHeightY))
                .addComponent(s_processing_width_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(text_rotation_processing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblRotationProcessing))
                .addComponent(s_processing_rotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(s_processing_positionx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(text_position_processing_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblPosX)))
                .addGap(10, 10, 10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(text_position_processing_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblPosY)
                .addComponent(s_processing_pos_y, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(text_processing_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(s_processing_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSpacingProcessing))
                .addGap(8, 8, 8)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(text_processing_gray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(s_processing_gray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelGrayProcessing)).addGap(0, 0, 0)));
        } else {
            jPanelProcessingLayout.setHorizontalGroup(
                jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addContainerGap()
                .addGap(15, 15, 15)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProcessingLayout.createSequentialGroup()
                .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                  .addComponent(lblHeightX, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_height_processing_x, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_processing_height_x, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(jPanelProcessingLayout.createSequentialGroup()

                    .addComponent(lblHeightY, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_width_processing_y, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_processing_width_y, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                    .addComponent(lblRotationProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_rotation_processing, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_processing_rotation, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addComponent(lblPosX, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_position_processing_x, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_processing_positionx, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addComponent(lblPosY, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_position_processing_y, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_processing_pos_y, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                )

                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addComponent(lblSpacingProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_processing_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_processing_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)

                )

                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                .addComponent(jLabelGrayProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_processing_gray, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_processing_gray, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)

                )
                )))))))));
        jPanelProcessingLayout.setVerticalGroup(
                jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProcessingLayout.createSequentialGroup()
                .addGap(18, 18, 18))
                .addGroup(jPanelProcessingLayout.createSequentialGroup()
                 .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGap(35, 35, 35)
                       .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHeightX)
                        .addComponent(text_height_processing_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_processing_height_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHeightY)
                        .addComponent(text_width_processing_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_processing_width_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRotationProcessing)
                        .addComponent(text_rotation_processing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_processing_rotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPosX)
                        .addComponent(text_position_processing_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_processing_positionx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPosY)
                        .addComponent(text_position_processing_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_processing_pos_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSpacingProcessing)
                        .addComponent(text_processing_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_processing_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(jPanelProcessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelGrayProcessing)
                        .addComponent(text_processing_gray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_processing_gray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                )
                );

        }
    }
    
    public JPanel getPanel() {
        return panel;
    }
    
    public JPanel getPanelButton() {
        return panelButton;
    }
    
    public JTextArea getLogArea() {
        return txtLogArea;
    }
    
     private void sliderGenerateActionPerformedProcessing(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedCyllin
        actionTag = "Processing";
        if (parseArguments()) {
            button11LensOnProcessing.setEnabled(true);
            buttonSecondDisplayProcessing.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateParameterDrawSignalProcessing(processing_widthX, processing_widthY, processing_heightX, processing_heightY, processing_positionX, processing_positionY, processing_rotation, processing_grayLevel, processing_spacing);
            image.paintSignalProcessing();
            EduPatternShowOn.updatePattern(image, genLogProcessing());
            setLog(genLogProcessing());
            imageGenerated = true;
        }
    }
    private void keyeventGenerateActionPerformedProcessing(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyeventGenerateActionPerformedProcessing
        actionTag = "Processing";
        if (parseArguments()) {
            button11LensOnProcessing.setEnabled(true);
            buttonSecondDisplayProcessing.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateParameterDrawSignalProcessing(processing_widthX, processing_widthY, processing_heightX, processing_heightY, processing_positionX, processing_positionY, processing_rotation, processing_grayLevel, processing_spacing);
            image.paintSignalProcessing();
            EduPatternShowOn.updatePattern(image, genLogProcessing());
            setLog(genLogProcessing());
            imageGenerated = true;
        }
    }
    
      private void buttonGenerateActionPerformedProcessing(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedCyllin
        actionTag = "Processing";
        if (parseArguments()) {
            button11LensOnProcessing.setEnabled(true);
            buttonSecondDisplayProcessing.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateParameterDrawSignalProcessing(processing_widthX, processing_widthY, processing_heightX, processing_heightY, processing_positionX, processing_positionY, processing_rotation, processing_grayLevel, processing_spacing);
            image.paintSignalProcessing();
            EduPatternShowOn.updatePattern(image, genLogProcessing());
            setLog(genLogProcessing());
            imageGenerated = true;
        }

    }//GEN-LAST:event_buttonGenerateActionPerformedCyllin

    private void button11LensOnProcessingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnProcessingActionPerformed
        actionTag = "Processing";
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateParameterDrawSignalProcessing(processing_widthX, processing_widthY, processing_heightX, processing_heightY, processing_positionX, processing_positionY, processing_rotation, processing_grayLevel, processing_spacing);
            image.paintSignalProcessing();
            EduPatternShowOn.updatePattern(image, genLogProcessing());
            setLog(genLogProcessing());
            imageGenerated = true;
            if (countLenOnProcessing % 2 == 0) {
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
                    magFrameLenon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                            countLenOnProcessing--;
                            button11LensOnProcessing.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }
        }
    }//GEN-LAST:event_button11LensOnProcessingActionPerformed
    
    

    private void buttonSecondGenerateActionPerformedProcessing(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedCyllin
        actionTag = "Processing";
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplayProcessing--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateParameterDrawSignalProcessing(processing_widthX, processing_widthY, processing_heightX, processing_heightY, processing_positionX, processing_positionY, processing_rotation, processing_grayLevel, processing_spacing);
                image.paintSignalProcessing();
                EduPatternShowOn.updatePatternSecondDisplay(image, genLogProcessing());
                setLog(genLogProcessing());
                imageGenerated = true;
                if (countSecondDisplayProcessing % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }

    private String genLogProcessing() {
        return String.format(logmessageProcessing, Double.toString(processing_heightX), Double.toString(processing_widthY), Double.toString(processing_rotation), Double.toString(processing_positionX), Double.toString(processing_positionY), Double.toString(processing_grayLevel), Double.toString(processing_spacing));
    }
    
    private boolean parseArguments() {
        boolean ret = false;
        try {          
           
            processing_widthX = Double.valueOf(s_processing_width_x.getValue());
            processing_widthY = Double.valueOf(s_processing_width_y.getValue());
            processing_heightX = Double.valueOf(s_processing_height_x.getValue());
            processing_heightY = Double.valueOf(s_processing_height_y.getValue());
            processing_rotation = Double.valueOf(s_processing_rotation.getValue());
            processing_positionX = Double.valueOf(s_processing_positionx.getValue());
            processing_positionY = Double.valueOf(s_processing_pos_y.getValue());
            processing_grayLevel = Double.valueOf(s_processing_gray.getValue());
            processing_spacing = Double.valueOf(s_processing_spacing.getValue());

            ret = true;
            
        } catch (Exception e) {
        }
        return ret;
    }
    
     public void setLog(String msg) {
        txtLogArea.append(msg + System.getProperty("line.separator"));
    }
}

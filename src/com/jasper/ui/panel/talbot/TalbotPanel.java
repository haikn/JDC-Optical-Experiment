/*
 * @(#)TalbotPanel.java
 *
 * Copyright (c) 2013 JASPER DISPLAY, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * JASPER DISPLAY PROPRIETARY:  Distribution of this source code
 * without permission from the copyright holder is strictly forbidden.
 */
package com.jasper.ui.panel.talbot;

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
import java.awt.Robot;
import javax.swing.JTextArea;
/**
 *
 * @author sonnv
 */
public class TalbotPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private javax.swing.JSlider sliderFocal;
    private javax.swing.JTextField textFocal;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    
     private javax.swing.JButton button11LensOntalbot;
    private javax.swing.JButton buttonGenneratetalbot;
    private javax.swing.JButton buttonSecondDisplaytalbot;
    private javax.swing.JLabel jLabelGraytalbot;
    private javax.swing.JLabel lblHeightYTalbot;
    private javax.swing.JLabel lblHeightXTalbot;
    private javax.swing.JLabel lblPosXTalbot;
    private javax.swing.JLabel lblPosYTalbot;
    private javax.swing.JLabel lblRotationtalbot;
    private javax.swing.JLabel lblSpacingtalbot;
    private javax.swing.JLabel lblWidthYTalbot;
    private javax.swing.JLabel lblWidthXTalbot;
    private javax.swing.JSlider s_talbot_gray;
    private javax.swing.JSlider s_talbot_height_x;
    private javax.swing.JSlider s_talbot_height_y;
    private javax.swing.JSlider s_talbot_pos_y;
    private javax.swing.JSlider s_talbot_positionx;
    private javax.swing.JSlider s_talbot_rotation;
    private javax.swing.JSlider s_talbot_width_x;
    private javax.swing.JSlider s_talbot_width_y;
    private javax.swing.JSlider s_talbot_spacing;
    private javax.swing.JTextField text_height_talbot_x;
    private javax.swing.JTextField text_height_talbot_y;
    private javax.swing.JTextField text_position_talbot_x;
    private javax.swing.JTextField text_position_talbot_y;
    private javax.swing.JTextField text_talbot_gray;
    private javax.swing.JTextField text_rotation_talbot;
    private javax.swing.JTextField text_width_talbot_x;
    private javax.swing.JTextField text_width_talbot_y;
    private javax.swing.JTextField text_talbot_spacing;
    
    
    static String logmessageTalbot = "Talbot images: w_x=%s w_y=%s r=%s p_x=%s p_y=%s g=%s spac=%s";
    private int countSecondDisplayTalbot = 1;
    private int countLenOnTalbot = 1;
    private double talbot_widthX = Double.valueOf(image1.getBounds().width), talbot_widthY = 100, talbot_heightX = 100, talbot_heightY = Double.valueOf(image1.getBounds().height), talbot_rotation = 0, talbot_positionX = 0, talbot_positionY = 0, talbot_grayLevel = 255, talbot_spacing = 400;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelButton;
    private javax.swing.JTextArea txtLogArea;
    
    public TalbotPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        this.txtLogArea = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panel = new javax.swing.JPanel();
        panelButton = new javax.swing.JPanel();
        
        lblWidthYTalbot = new javax.swing.JLabel();
        lblHeightYTalbot = new javax.swing.JLabel();
        lblRotationtalbot = new javax.swing.JLabel();
        lblPosXTalbot = new javax.swing.JLabel();
        lblPosYTalbot = new javax.swing.JLabel();
        lblSpacingtalbot = new javax.swing.JLabel();
        text_width_talbot_y = new javax.swing.JTextField();
        text_height_talbot_y = new javax.swing.JTextField();
        text_rotation_talbot = new javax.swing.JTextField();
        text_position_talbot_x = new javax.swing.JTextField();
        text_position_talbot_y = new javax.swing.JTextField();
        text_talbot_spacing = new javax.swing.JTextField();
        s_talbot_width_y = new javax.swing.JSlider();
        s_talbot_height_y = new javax.swing.JSlider();
        s_talbot_rotation = new javax.swing.JSlider();
        s_talbot_positionx = new javax.swing.JSlider();
        s_talbot_pos_y = new javax.swing.JSlider();
        s_talbot_spacing = new javax.swing.JSlider();
        jLabelGraytalbot = new javax.swing.JLabel();
        text_talbot_gray = new javax.swing.JTextField();
        s_talbot_gray = new javax.swing.JSlider();
        buttonGenneratetalbot = new javax.swing.JButton();
        button11LensOntalbot = new javax.swing.JButton();
        buttonSecondDisplaytalbot = new javax.swing.JButton();
        lblWidthXTalbot = new javax.swing.JLabel();
        text_width_talbot_x = new javax.swing.JTextField();
        s_talbot_width_x = new javax.swing.JSlider();
        lblHeightXTalbot = new javax.swing.JLabel();
        text_height_talbot_x = new javax.swing.JTextField();
        s_talbot_height_x = new javax.swing.JSlider();
        lblHeightYTalbot.setText("Width Y");

        lblRotationtalbot.setText(labels.getString("paramRotationNoDegree"));

        lblPosXTalbot.setText("Position X");

        lblPosYTalbot.setText("Position Y");

        Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_talbot_width_y, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_width_talbot_y, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_talbot_width_y.setMaximum(image1.getBounds().width);
        s_talbot_width_y.setValue(100);
        s_talbot_width_y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedTalbot(evt);
            }
        });
        text_width_talbot_y.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedTalbot(ke);
                if(text_width_talbot_y.getText() == null || text_width_talbot_y.getText().equals("")){
                    lblHeightYTalbot.setForeground(Color.red);
                } else {
                    lblHeightYTalbot.setForeground(Color.black);
                }
            }
        });
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_talbot_height_y, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_height_talbot_y, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_talbot_height_y.setMaximum(9999);
        s_talbot_height_y.setValue(9999);
        s_talbot_height_y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedTalbot(evt);
            }
        });
        text_height_talbot_y.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedTalbot(ke);
                if(text_height_talbot_y.getText() == null || text_height_talbot_y.getText().equals("")){
                    lblHeightXTalbot.setForeground(Color.red);
                } else {
                    lblHeightXTalbot.setForeground(Color.black);
                }
                
            }
        });
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_talbot_rotation, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_rotation_talbot, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_talbot_rotation.setMaximum(180);
        s_talbot_rotation.setMinimum(-180);
        s_talbot_rotation.setValue(0);
        s_talbot_rotation.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedTalbot(evt);
            }
        });
        text_rotation_talbot.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedTalbot(ke);
                if(text_rotation_talbot.getText() == null || text_rotation_talbot.getText().equals("")){
                    lblRotationtalbot.setForeground(Color.red);
                } else {
                    lblRotationtalbot.setForeground(Color.black);
                }
                
            }
        });
        
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_talbot_positionx, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_position_talbot_x, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_talbot_positionx.setMaximum(image1.getBounds().height / 2);
        s_talbot_positionx.setMinimum(-(image1.getBounds().height / 2));
        s_talbot_positionx.setValue(0);
        s_talbot_positionx.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedTalbot(evt);
            }
        });
        text_position_talbot_x.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedTalbot(ke);
                if(text_position_talbot_x.getText() == null || text_position_talbot_x.getText().equals("")){
                    lblPosXTalbot.setForeground(Color.red);
                } else {
                    lblPosXTalbot.setForeground(Color.black);
                }
                
            }
        });
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_talbot_pos_y, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_position_talbot_y, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_talbot_pos_y.setMaximum(image1.getBounds().width / 2);
        s_talbot_pos_y.setMinimum(-(image1.getBounds().width / 2));
        s_talbot_pos_y.setValue(0);
        s_talbot_pos_y.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedTalbot(evt);
            }
        });
        text_position_talbot_y.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedTalbot(ke);
                if(text_position_talbot_y.getText() == null || text_position_talbot_y.getText().equals("")){
                    lblPosYTalbot.setForeground(Color.red);
                } else {
                    lblPosYTalbot.setForeground(Color.black);
                }
            }
        });
        bindingGroup.addBinding(binding);

        jLabelGraytalbot.setText(labels.getString("paramGrayLevel"));
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_talbot_gray, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_talbot_gray, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_talbot_gray.setMajorTickSpacing(255);
        s_talbot_gray.setPaintLabels(true);
        s_talbot_gray.setMaximum(255);
        s_talbot_gray.setValue(255);
        s_talbot_gray.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedTalbot(evt);
            }
        });
        text_talbot_gray.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedTalbot(ke);
                if(text_talbot_gray.getText() == null || text_talbot_gray.getText().equals("")){
                    jLabelGraytalbot.setForeground(Color.red);
                } else {
                    jLabelGraytalbot.setForeground(Color.black);
                }
            }
        });
        bindingGroup.addBinding(binding);

        lblSpacingtalbot.setText(labels.getString("paramSpacing"));
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_talbot_spacing, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_talbot_spacing, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_talbot_spacing.setMaximum(image1.getBounds().height);
         s_talbot_spacing.setMinimum(-(image1.getBounds().height));
        s_talbot_spacing.setValue(0);
        s_talbot_spacing.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedTalbot(evt);
            }
        });
        text_talbot_spacing.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedTalbot(ke);
                if(text_talbot_spacing.getText() == null || text_talbot_spacing.getText().equals("")){
                    lblSpacingtalbot.setForeground(Color.red);
                } else {
                    lblSpacingtalbot.setForeground(Color.black);
                }
            }
        });
        bindingGroup.addBinding(binding);
        
        lblHeightXTalbot.setText("Width X");
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_talbot_width_x, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_width_talbot_x, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_talbot_width_x.setMaximum(3866);
        s_talbot_width_x.setValue(3866);
        s_talbot_width_x.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedTalbot(evt);
            }
        });
        
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_talbot_height_x, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_height_talbot_x, org.jdesktop.beansbinding.BeanProperty.create("text"));
        s_talbot_height_x.setMaximum(image1.getBounds().height);
        s_talbot_height_x.setValue(100);
        s_talbot_height_x.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedTalbot(evt);
            }
        });
        text_height_talbot_x.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedTalbot(ke);
                if(text_height_talbot_x.getText() == null || text_height_talbot_x.getText().equals("")){
                    lblHeightXTalbot.setForeground(Color.red);
                } else {
                    lblHeightXTalbot.setForeground(Color.black);
                }
                
            }
        });
        bindingGroup.addBinding(binding);


        buttonGenneratetalbot.setText(labels.getString("btnGenerate"));
        buttonGenneratetalbot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformedTalbot(evt);
            }
        });
        button11LensOntalbot.setEnabled(false);
        button11LensOntalbot.setText(labels.getString("btnLensOn"));
        button11LensOntalbot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnTalbotActionPerformed(evt);
                //   boolean_checkLen = false;
                countLenOnTalbot++;
                if (countLenOnTalbot % 2 == 0) {
                    button11LensOntalbot.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                } else {
                    button11LensOntalbot.setText(labels.getString("btnLensOn"));
                }
            }
        });
        buttonSecondDisplaytalbot.setEnabled(false);
        buttonSecondDisplaytalbot.setText(labels.getString("btnSecondDisplayOn"));
        buttonSecondDisplaytalbot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedTalbot(evt);
                countSecondDisplayTalbot++;
                if (countSecondDisplayTalbot % 2 == 0) {
                    buttonSecondDisplaytalbot.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    buttonSecondDisplaytalbot.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });
        
        javax.swing.GroupLayout panelButtonTalbotLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonTalbotLayout);
        panelButtonTalbotLayout.setHorizontalGroup(
                panelButtonTalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonTalbotLayout.createSequentialGroup()
                .addGroup(panelButtonTalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonTalbotLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonGenneratetalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button11LensOntalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonSecondDisplaytalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE)
                )
                )));
        panelButtonTalbotLayout.setVerticalGroup(
                panelButtonTalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonTalbotLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelButtonTalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonSecondDisplaytalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(button11LensOntalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonGenneratetalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        
        javax.swing.GroupLayout jPaneltalbotLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPaneltalbotLayout);
        
        jPaneltalbotLayout.setHorizontalGroup(
                jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPaneltalbotLayout.createSequentialGroup()
                .addContainerGap()
                .addGap(15, 15, 15)
                .addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPaneltalbotLayout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPaneltalbotLayout.createSequentialGroup()
                .addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneltalbotLayout.createSequentialGroup()
                .addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPaneltalbotLayout.createSequentialGroup()
                    
                    .addComponent(lblHeightXTalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_height_talbot_x, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_talbot_height_x, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                    
                )
                .addGroup(jPaneltalbotLayout.createSequentialGroup()
                    
                    .addComponent(lblHeightYTalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_width_talbot_y, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_talbot_width_y, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(jPaneltalbotLayout.createSequentialGroup()
                    .addComponent(lblRotationtalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_rotation_talbot, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_talbot_rotation, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(jPaneltalbotLayout.createSequentialGroup()
                    .addComponent(lblPosXTalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_position_talbot_x, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_talbot_positionx, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(jPaneltalbotLayout.createSequentialGroup()
                    .addComponent(lblPosYTalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_position_talbot_y, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_talbot_pos_y, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(jPaneltalbotLayout.createSequentialGroup()
                    .addComponent(lblSpacingtalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_talbot_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_talbot_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)

                )
                .addGroup(jPaneltalbotLayout.createSequentialGroup()
                 
                    .addComponent(jLabelGraytalbot, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(text_talbot_gray, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(s_talbot_gray, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                 
                )
                )))))))));        

        jPaneltalbotLayout.setVerticalGroup(
                jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneltalbotLayout.createSequentialGroup()
                    .addGap(18, 18, 18))
                .addGroup(jPaneltalbotLayout.createSequentialGroup()
                    .addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    	.addGap(35, 35, 35)
                    	.addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHeightXTalbot)
                        .addComponent(text_height_talbot_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_talbot_height_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    	.addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)	
                        .addComponent(lblHeightYTalbot)
                        .addComponent(text_width_talbot_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_talbot_width_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    	.addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRotationtalbot)
                        .addComponent(text_rotation_talbot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_talbot_rotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    	.addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPosXTalbot)
                        .addComponent(text_position_talbot_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_talbot_positionx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    	.addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPosYTalbot)
                        .addComponent(text_position_talbot_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_talbot_pos_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    	.addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSpacingtalbot)
                        .addComponent(text_talbot_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_talbot_spacing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )
                    .addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    	.addGroup(jPaneltalbotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelGraytalbot)
                        .addComponent(text_talbot_gray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(s_talbot_gray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                    )));
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
    
    private void sliderGenerateActionPerformedTalbot(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedTalbot
        actionTag = "Talbot";
        if (parseArguments()) {
            button11LensOntalbot.setEnabled(true);
            buttonSecondDisplaytalbot.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateParameterDrawTalbot(talbot_widthX, talbot_widthY, talbot_heightX, talbot_heightY, talbot_positionX, talbot_positionY, talbot_rotation, talbot_grayLevel, talbot_spacing);
            image.paintTalbot();
            EduPatternShowOn.updateLensPatternPattern(image, genLogTalbot());
            setLog(genLogTalbot());
            imageGenerated = true;
        }
    }
    private void keyeventGenerateActionPerformedTalbot(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyeventGenerateActionPerformedTalbot
        actionTag = "Talbot";
        if (parseArguments()) {
            button11LensOntalbot.setEnabled(true);
            buttonSecondDisplaytalbot.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateParameterDrawTalbot(talbot_widthX, talbot_widthY, talbot_heightX, talbot_heightY, talbot_positionX, talbot_positionY, talbot_rotation, talbot_grayLevel, talbot_spacing);
            image.paintTalbot();
            EduPatternShowOn.updateLensPatternPattern(image, genLogTalbot());
            setLog(genLogTalbot());
            imageGenerated = true;
        }
    }
    
    private void buttonGenerateActionPerformedTalbot(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedTalbot
        actionTag = "Talbot";
        if (parseArguments()) {
            button11LensOntalbot.setEnabled(true);
            buttonSecondDisplaytalbot.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateParameterDrawTalbot(talbot_widthX, talbot_widthY, talbot_heightX, talbot_heightY, talbot_positionX, talbot_positionY, talbot_rotation, talbot_grayLevel, talbot_spacing);
            image.paintTalbot();
            EduPatternShowOn.updateLensPatternPattern(image, genLogTalbot());
            setLog(genLogTalbot());
            imageGenerated = true;
        }

    }//GEN-LAST:event_buttonGenerateActionPerformedTalbot

    private void button11LensOnTalbotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnTalbotActionPerformed
        actionTag = "Talbot";
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateParameterDrawTalbot(talbot_widthX, talbot_widthY, talbot_heightX, talbot_heightY, talbot_positionX, talbot_positionY, talbot_rotation, talbot_grayLevel, talbot_spacing);
            image.paintTalbot();
            EduPatternShowOn.updateLensPatternPattern(image, genLogTalbot());
            setLog(genLogTalbot());
            imageGenerated = true;
            if (countLenOnTalbot % 2 == 0) {
                magFrameLenon.dispose();
                panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        patternFrameDoubleClick.show();
                    }
                });
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
                magFrameLenon.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                            countLenOnTalbot--;
                            button11LensOntalbot.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }
        }
    }//GEN-LAST:event_button11LensOnTalbotActionPerformed

    private void buttonSecondGenerateActionPerformedTalbot(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedTalbot
        actionTag = "Talbot";
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplayTalbot--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateParameterDrawTalbot(talbot_widthX, talbot_widthY, talbot_heightX, talbot_heightY, talbot_positionX, talbot_positionY, talbot_rotation, talbot_grayLevel, talbot_spacing);
                image.paintTalbot();
                EduPatternShowOn.updatePatternSecondDisplay(image, genLogTalbot());
                setLog(genLogTalbot());
                imageGenerated = true;
                if (countSecondDisplayTalbot % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }//GEN-LAST:event_buttonSecondGenerateActionPerformedTalbot

    
    private boolean parseArguments() {
        boolean ret = false;
        try {          
           
            talbot_widthX = Double.valueOf(s_talbot_width_x.getValue());
            talbot_widthY = Double.valueOf(s_talbot_width_y.getValue());
            talbot_heightX = Double.valueOf(s_talbot_height_x.getValue());
            talbot_heightY = Double.valueOf(s_talbot_height_y.getValue());
            talbot_rotation = Double.valueOf(s_talbot_rotation.getValue());
            talbot_positionX = Double.valueOf(s_talbot_positionx.getValue());
            talbot_positionY = Double.valueOf(s_talbot_pos_y.getValue());
            talbot_grayLevel = Double.valueOf(s_talbot_gray.getValue());
            talbot_spacing = Double.valueOf(s_talbot_spacing.getValue());
            ret = true;
            
        } catch (Exception e) {
        }
        return ret;
    }
    
    public void setLog(String msg) {
        txtLogArea.append(msg + System.getProperty("line.separator"));
    }
    
     private String genLogTalbot() {
        return String.format(logmessageTalbot, Double.toString(talbot_heightX), Double.toString(talbot_widthY), Double.toString(talbot_rotation), Double.toString(talbot_positionX), Double.toString(talbot_positionY), Double.toString(talbot_grayLevel), Double.toString(talbot_spacing));
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
}

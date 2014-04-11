/*
 * @(#)SingleSlitPanel.java
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
import java.awt.Robot;
import javax.swing.JTextArea;
/**
 *
 * @author sonnv
 */
public class SingleSlitPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private javax.swing.JSlider sliderFocal;
    private javax.swing.JTextField textFocal;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    
     private javax.swing.JButton buttonGennerateSlit;
    private javax.swing.JButton buttonSecondDisplaySlit;
    private javax.swing.JButton buttong11LensOnSlit;
    private javax.swing.JComboBox comboBoxSlit;
    private javax.swing.JLabel jLabelSpacingSlit;
    private javax.swing.JLabel lblGraySlit;
    private javax.swing.JLabel lblHeightSlit;
    private javax.swing.JLabel lblPosSlit;
    private javax.swing.JLabel lblRotationSlit;
    private javax.swing.JLabel lblWidthSlit;
    private javax.swing.JSlider s_single_gray;
    private javax.swing.JSlider s_single_height;
    private javax.swing.JSlider s_single_position;
    private javax.swing.JSlider s_single_rotation;
    private javax.swing.JSlider s_single_spacing;
    private javax.swing.JSlider s_single_width;
    private javax.swing.JTextField text_single_gray;
    private javax.swing.JTextField text_single_height;
    private javax.swing.JTextField text_single_position;
    private javax.swing.JTextField text_single_rotation;
    private javax.swing.JTextField text_single_spacing;
    private javax.swing.JTextField text_single_width;
    private int slit = 1;
    private javax.swing.JTextArea txtAreaLog;
    private javax.swing.JPanel panelButton;
    
    static String logmessageSlit = "Single Slit: w=%s r=%s p=%s g=%s";
    private double d_widthX = Double.valueOf(image1.getBounds().width), d_heightX = 100, d_rotation = 0, d_postionX = 0, d_grayLevel = 255, d_spacing = 400;
    private int countSecondDisplaySlit = 1;
    private int countLenOnSlit = 1;
    private javax.swing.JPanel panelSlit;
    
     public SingleSlitPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        this.txtAreaLog = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panelButton = new javax.swing.JPanel();
        panelSlit = new javax.swing.JPanel();
        comboBoxSlit = new javax.swing.JComboBox();
        lblWidthSlit = new javax.swing.JLabel();
        lblHeightSlit = new javax.swing.JLabel();
        lblRotationSlit = new javax.swing.JLabel();
        lblPosSlit = new javax.swing.JLabel();
        lblGraySlit = new javax.swing.JLabel();
        text_single_width = new javax.swing.JTextField();
        text_single_height = new javax.swing.JTextField();
        text_single_rotation = new javax.swing.JTextField();
        text_single_position = new javax.swing.JTextField();
        text_single_gray = new javax.swing.JTextField();
        s_single_width = new javax.swing.JSlider();
        s_single_height = new javax.swing.JSlider();
        s_single_rotation = new javax.swing.JSlider();
        s_single_position = new javax.swing.JSlider();
        s_single_gray = new javax.swing.JSlider();
        jLabelSpacingSlit = new javax.swing.JLabel();
        text_single_spacing = new javax.swing.JTextField();
        s_single_spacing = new javax.swing.JSlider();
        buttonGennerateSlit = new javax.swing.JButton();
        buttong11LensOnSlit = new javax.swing.JButton();
        buttonSecondDisplaySlit = new javax.swing.JButton();

        lblWidthSlit.setText(labels.getString("paramWidth"));

        lblHeightSlit.setText(labels.getString("paramWidth"));

        lblRotationSlit.setText(labels.getString("paramRotationNoDegree"));

        lblPosSlit.setText(labels.getString("paramPositions"));

        lblGraySlit.setText(labels.getString("paramGrayLevel"));

        Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_width, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_width, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_height, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_height, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_rotation, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_rotation, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_position, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_position, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_gray, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_gray, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabelSpacingSlit.setText(labels.getString("paramSpacing"));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, s_single_spacing, org.jdesktop.beansbinding.ELProperty.create("${value}"), text_single_spacing, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        text_single_height.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if(text_single_height.getText() == null || text_single_height.getText().equals("")){
                    lblHeightSlit.setForeground(Color.red);
                } else {
                    lblHeightSlit.setForeground(Color.black);
                }
                keyeventGenerateActionPerformedSlit(ke);
            }
        });
        text_single_rotation.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if(text_single_rotation.getText() == null || text_single_rotation.getText().equals("")){
                    lblRotationSlit.setForeground(Color.red);
                } else {
                    lblRotationSlit.setForeground(Color.black);
                }
                keyeventGenerateActionPerformedSlit(ke);
            }
        });
        text_single_position.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if(text_single_position.getText() == null || text_single_position.getText().equals("")){
                    lblPosSlit.setForeground(Color.red);
                } else {
                    lblPosSlit.setForeground(Color.black);
                }
                keyeventGenerateActionPerformedSlit(ke);
            }
        });
        text_single_gray.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if(text_single_gray.getText() == null || text_single_gray.getText().equals("")){
                    lblGraySlit.setForeground(Color.red);
                } else {
                    lblGraySlit.setForeground(Color.black);
                }
                keyeventGenerateActionPerformedSlit(ke);
            }
        });
        text_single_gray.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if(text_single_gray.getText() == null || text_single_gray.getText().equals("")){
                    jLabelSpacingSlit.setForeground(Color.red);
                } else {
                    jLabelSpacingSlit.setForeground(Color.black);
                }
                keyeventGenerateActionPerformedSlit(ke);
            }
        });
        // disable when slit = 1
        jLabelSpacingSlit.hide();
        text_single_spacing.hide();
        s_single_spacing.hide();
        s_single_width.setMaximum(3866);
        s_single_width.setValue(3866);
        s_single_width.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedSlit(evt);
            }
        });
        s_single_height.setMaximum(image1.getBounds().height);
        s_single_height.setValue(100);
        s_single_height.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedSlit(evt);
            }
        });
        s_single_rotation.setMaximum(180);
        s_single_rotation.setMinimum(-180);
        s_single_rotation.setValue(0);
        s_single_rotation.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedSlit(evt);
            }
        });
        s_single_position.setMaximum(image1.getBounds().height / 2);
        s_single_position.setMinimum(-(image1.getBounds().height / 2));
        s_single_position.setValue(0);
        s_single_position.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedSlit(evt);
            }
        });
        s_single_gray.setMajorTickSpacing(255);
        s_single_gray.setPaintLabels(true);
        s_single_gray.setMaximum(255); 
        s_single_gray.setValue(255);
        s_single_gray.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedSlit(evt);
            }
        });
        
        s_single_spacing.setMaximum(image1.getBounds().height);
        s_single_spacing.setValue(150);
        s_single_spacing.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedSlit(evt);
            }
        });

        buttonGennerateSlit.setText(labels.getString("btnGenerate"));
        buttonGennerateSlit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformedSlit(evt);
            }
        });

        buttong11LensOnSlit.setEnabled(false);
        buttong11LensOnSlit.setText(labels.getString("btnLensOn"));
        buttong11LensOnSlit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnSlitActionPerformed(evt);
                countLenOnSlit++;
                if (countLenOnSlit % 2 == 0) {
                    buttong11LensOnSlit.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                } else {
                    buttong11LensOnSlit.setText(labels.getString("btnLensOn"));
                }
            }
        });

        buttonSecondDisplaySlit.setEnabled(false);
        buttonSecondDisplaySlit.setText(labels.getString("btnSecondDisplayOn"));
        buttonSecondDisplaySlit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedSlit(evt);
                countSecondDisplaySlit++;
                if (countSecondDisplaySlit % 2 == 0) {
                    buttonSecondDisplaySlit.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    buttonSecondDisplaySlit.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });
        comboBoxSlit.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Single Slit", "Double Slit"}));
        comboBoxSlit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Object selected = comboBoxSlit.getSelectedItem();
                if (selected.equals("Single Slit")) {
                    slit = 1;
                    jLabelSpacingSlit.hide();
                    text_single_spacing.hide();
                    s_single_spacing.hide();

                }
                if (selected.equals("Double Slit")) {
                    slit = 2;
                    jLabelSpacingSlit.show();
                    text_single_spacing.show();
                    s_single_spacing.show();
                }
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateLensParameterDrawSlit(slit, d_widthX, d_heightX, d_postionX, d_rotation, d_grayLevel, d_spacing);
                image.slit(slit);
                EduPatternShowOn.updateLensPatternPattern(image, genLogSlit());
                imageGenerated = true;
            }
        });
        // button panel
        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
                panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonLayout.createSequentialGroup()
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonGennerateSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttong11LensOnSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonSecondDisplaySlit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE)
                )
                )));
        panelButtonLayout.setVerticalGroup(
                panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonSecondDisplaySlit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttong11LensOnSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonGennerateSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(panelSlit);
        panelSlit.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblGraySlit, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(lblPosSlit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRotationSlit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHeightSlit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(text_single_height)
                    .addComponent(text_single_rotation)
                    .addComponent(text_single_position)
                    .addComponent(text_single_gray, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_single_height, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s_single_rotation, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s_single_position, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                         .addGap(18, 18, 18)
                        .addComponent(s_single_gray, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        )))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_single_height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHeightSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text_single_height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_single_rotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRotationSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(s_single_rotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPosSlit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(s_single_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text_single_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGraySlit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_single_gray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(s_single_gray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                )
        );
    }
    
    public JPanel getPanel() {
        return panelSlit;
    }
    
    public JPanel getPanelButton() {
        return panelButton;
    }
    
    public JTextArea getLogArea() {
        return txtAreaLog;
    }
    
     private void buttonGenerateActionPerformedSlit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedCyllin
        actionTag = "Slit";
        if (parseArguments()) {
            buttong11LensOnSlit.setEnabled(true);
            buttonSecondDisplaySlit.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensParameterDrawSlit(1, d_widthX, d_heightX, d_postionX, d_rotation, d_grayLevel, d_spacing);
            image.slit(slit);
            EduPatternShowOn.updateLensPatternPattern(image, genLogSlit());
            setLog(genLogSlit());
            imageGenerated = true;
        }

    }//GEN-LAST:event_buttonGenerateActionPerformedCyllin

    private void button11LensOnSlitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnSlitActionPerformed
        actionTag = "Slit";
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensParameterDrawSlit(1, d_widthX, d_heightX, d_postionX, d_rotation, d_grayLevel, d_spacing);
            image.slit(slit);
            EduPatternShowOn.updateLensPatternPattern(image, genLogSlit());
            setLog(genLogSlit());
            imageGenerated = true;

            if (countLenOnSlit % 2 == 0) {
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
                            countLenOnSlit--;
                            buttong11LensOnSlit.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }

        }

    }//GEN-LAST:event_button11LensOnSlitActionPerformed

    private void buttonSecondGenerateActionPerformedSlit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedCyllin
        actionTag = "Slit";
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplaySlit--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateLensParameterDrawSlit(slit, d_widthX, d_heightX, d_postionX, d_rotation, d_grayLevel, d_spacing);
                image.slit(slit);
                EduPatternShowOn.updatePatternSecondDisplay(image, genLogSlit());
                setLog(genLogSlit());
                //EduPatternTest.updateLensPatternPattern(image, genLog());
                imageGenerated = true;
                if (countSecondDisplaySlit % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }//GEN-LAST:event_buttonSecondGenerateActionPerformedCyllin

    private void sliderGenerateActionPerformedSlit(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedCyllin
        actionTag = "Slit";
        if (parseArguments()) {
            buttong11LensOnSlit.setEnabled(true);
            buttonSecondDisplaySlit.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensParameterDrawSlit(slit, d_widthX, d_heightX, d_postionX, d_rotation, d_grayLevel, d_spacing);
            image.slit(slit);
            EduPatternShowOn.updateLensPatternPattern(image, genLogSlit());
            setLog(genLogSlit());
            imageGenerated = true;
        }
    }//GEN-LAST:event_sliderGenerateActionPerformedCyllin
    
    private void keyeventGenerateActionPerformedSlit(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedCyllin
        actionTag = "Slit";
        if (parseArguments()) {
            buttong11LensOnSlit.setEnabled(true);
            buttonSecondDisplaySlit.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensParameterDrawSlit(slit, d_widthX, d_heightX, d_postionX, d_rotation, d_grayLevel, d_spacing);
            image.slit(slit);
            EduPatternShowOn.updateLensPatternPattern(image, genLogSlit());
            setLog(genLogSlit());
            imageGenerated = true;
        }
    }//GEN-LAST:event_keyeventGenerateActionPerformedSlit
    
    private String genLogSlit() {
        return String.format(logmessageSlit, Double.toString(d_heightX), Double.toString(d_rotation), Double.toString(d_postionX), Double.toString(d_grayLevel));
    }
    
    private boolean parseArguments() {
        boolean ret = false;
        try {          
           
            double widthSlit = Double.valueOf(s_single_width.getValue());
            double heightSlit = Double.valueOf(s_single_height.getValue());
            double postionSlit = Double.valueOf(s_single_position.getValue());
            double rotationSlit = Double.valueOf(s_single_rotation.getValue());
            double grayLevelSlit = Double.valueOf(s_single_gray.getValue());
            double spacingSlit = Double.valueOf(s_single_spacing.getValue());
            this.d_widthX = widthSlit;
            this.d_heightX = heightSlit;
            this.d_postionX = postionSlit;
            this.d_rotation = rotationSlit;
            this.d_grayLevel = grayLevelSlit;
            this.d_spacing = spacingSlit;
            ret = true;
            
        } catch (Exception e) {
        }
        return ret;
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
}

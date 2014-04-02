/*
 * @(#)BeamShiftingPanel.java
 *
 * Copyright (c) 2013 JASPER DISPLAY, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * JASPER DISPLAY PROPRIETARY:  Distribution of this source code
 * without permission from the copyright holder is strictly forbidden.
 */
package com.jasper.ui.panel;

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
import com.jasper.ui.widget.DoubleJSlider;
import com.jasper.ui.EduLensOn11;
import com.jasper.ui.EduPatternJPanel;
import com.jasper.ui.EduPatternShowOn;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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
public class BeamShiftingPanel extends OpticsPane{
     PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private javax.swing.JSlider sliderFocal;
    private javax.swing.JTextField textFocal;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    
    private javax.swing.JTabbedPane tabbedControl;
    public javax.swing.JLabel lblFocalCalibration;
    private javax.swing.JLabel lblXPosCalibration;
    private javax.swing.JLabel lblYPosCalibration;
    private DoubleJSlider jSliderFocalCalibration;
    private DoubleJSlider jSliderXPositionCalibration;
    private DoubleJSlider jSliderYPositionCalibration;
    private javax.swing.JTextField txtFocalCalibration;
    private javax.swing.JTextField txtXPositionCalibration;
    private javax.swing.JTextField txtYPositionCalibration;
    private javax.swing.JButton buttonCalibrationLensOn;
    private javax.swing.JButton buttonCalibrationDisplaySecondOn;
    private javax.swing.JButton buttonCalibrationGeneral;
    private javax.swing.JButton buttonCalibrationReset;
    
    static String logmessageCalibration = "Fine tuning: Phy=%s Theta=%s";
    private double xoffCalibration = 0, yoffCalibration = 50, focalCalibration = 522;
    private int countSecondDisplayCalibration = 1;
    private int countLenOnCalibration = 1;
    private javax.swing.JPanel panelCalebration;
    private javax.swing.JTextArea txtAreaLog;
    
    public BeamShiftingPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern, JTabbedPane tabbedControl) {
        this.labels = labels;
        this.txtAreaLog = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        this.tabbedControl = tabbedControl;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panelCalebration = new javax.swing.JPanel();
        
        lblFocalCalibration = new javax.swing.JLabel();
        lblXPosCalibration = new javax.swing.JLabel();
        lblYPosCalibration = new javax.swing.JLabel();
        txtXPositionCalibration = new javax.swing.JTextField();
        txtYPositionCalibration = new javax.swing.JTextField();
        buttonCalibrationLensOn = new javax.swing.JButton();
        buttonCalibrationDisplaySecondOn = new javax.swing.JButton();
        buttonCalibrationGeneral = new javax.swing.JButton();
        
        lblXPosCalibration.setText("Deviation angle (degree)");
        lblYPosCalibration.setText("Rotation (degree)");
        
        buttonCalibrationReset = new javax.swing.JButton();
        buttonCalibrationReset.setText("Default");
        buttonCalibrationReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtXPositionCalibration.setText("0");
                txtYPositionCalibration.setText("0");
                jSliderXPositionCalibration.setValue(0);
                jSliderYPositionCalibration.setValue(0);
                buttonGenerateActionPerformedCalibration(evt);
            }
        });
        
        buttonCalibrationGeneral.setText(labels.getString("btnGenerate"));
        buttonCalibrationGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformedCalibration(evt);
            }
        });

        buttonCalibrationLensOn.setEnabled(false);
        buttonCalibrationLensOn.setText(labels.getString("btnLensOn"));
        buttonCalibrationLensOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnCalibrationActionPerformed(evt);
                countLenOnCalibration++;
                if (countLenOnCalibration % 2 == 0) {
                    buttonCalibrationLensOn.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                } else {
                    buttonCalibrationLensOn.setText(labels.getString("btnLensOn"));
                }
            }
        });

        buttonCalibrationDisplaySecondOn.setEnabled(false);
        buttonCalibrationDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
        buttonCalibrationDisplaySecondOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedCalibration(evt);
                countSecondDisplayCalibration++;
                if (countSecondDisplayCalibration % 2 == 0) {
                    buttonCalibrationDisplaySecondOn.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    buttonCalibrationDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });
        
        txtXPositionCalibration.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if(txtXPositionCalibration.getText() == null || txtXPositionCalibration.getText().equals("")){
                    lblXPosCalibration.setForeground(Color.red);
                } else {
                    lblXPosCalibration.setForeground(Color.black);
                }
                keyEventGenerateActionPerformedCalibration(ke);
            }
        });

        jSliderXPositionCalibration = new DoubleJSlider(-28, 28, 1, 10);
        jSliderXPositionCalibration.setValue(0);
        txtXPositionCalibration.setText(String.valueOf(jSliderXPositionCalibration.getValue()));

        jSliderXPositionCalibration.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedCalibration(evt);
                DecimalFormat df = new DecimalFormat("0.####");
                txtXPositionCalibration.setText(df.format(jSliderXPositionCalibration.getScaledValue()));
            }
        });
        
        txtYPositionCalibration.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if(txtYPositionCalibration.getText() == null || txtYPositionCalibration.getText().equals("")){
                    lblYPosCalibration.setForeground(Color.red);
                } else {
                    lblYPosCalibration.setForeground(Color.black);
                }
                keyEventGenerateActionPerformedCalibration(ke);
            }
        });

        jSliderYPositionCalibration = new DoubleJSlider(-1800, 1800, 100, 10);
        jSliderYPositionCalibration.setValue(0);
        txtYPositionCalibration.setText(String.valueOf(jSliderYPositionCalibration.getValue()));

        jSliderYPositionCalibration.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedCalibration(evt);
                DecimalFormat df = new DecimalFormat("0.####");
                txtYPositionCalibration.setText(df.format(jSliderYPositionCalibration.getScaledValue()));
            }
        });

        javax.swing.GroupLayout jPanelCalibrationdricalLayout = new javax.swing.GroupLayout(panelCalebration);
        panelCalebration.setLayout(jPanelCalibrationdricalLayout);
        jPanelCalibrationdricalLayout.setHorizontalGroup(
            jPanelCalibrationdricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCalibrationdricalLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanelCalibrationdricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCalibrationdricalLayout.createSequentialGroup()
                        //.addComponent(buttonCalibrationReset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        //.addGap(18, 18, 18)
                        .addComponent(buttonCalibrationGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(buttonCalibrationLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(buttonCalibrationDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCalibrationdricalLayout.createSequentialGroup()
                        .addGroup(jPanelCalibrationdricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblYPosCalibration, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(lblXPosCalibration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanelCalibrationdricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtYPositionCalibration)
                            .addComponent(txtXPositionCalibration, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanelCalibrationdricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSliderXPositionCalibration, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSliderYPositionCalibration, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0))
        );
        jPanelCalibrationdricalLayout.setVerticalGroup(
            jPanelCalibrationdricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCalibrationdricalLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelCalibrationdricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSliderXPositionCalibration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCalibrationdricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblXPosCalibration)
                        .addComponent(txtXPositionCalibration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelCalibrationdricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCalibrationdricalLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanelCalibrationdricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYPosCalibration)
                            .addComponent(txtYPositionCalibration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelCalibrationdricalLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jSliderYPositionCalibration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(262, 262, 262)
                .addGroup(jPanelCalibrationdricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCalibrationGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCalibrationLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCalibrationDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
    }
    
    public JPanel getPanel() {
        return panelCalebration;
    }
    
    public JTextArea getLogArea() {
        return txtAreaLog;
    }
    
    private void buttonGenerateActionPerformedCalibration(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedCalibration
        if (parseArguments()) {
            actionTag = "Calibration";
            buttonCalibrationLensOn.setEnabled(true);
            buttonCalibrationDisplaySecondOn.setEnabled(true);
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateBeamShiftingParameter(xoffCalibration, yoffCalibration);
            image.paintBeamShifting();
            EduPatternShowOn.updateLensPatternPattern(image, genLogCalibration());
            imageGenerated = true;
        }
    }//GEN-LAST:event_buttonGenerateActionPerformedCalibration

    private void button11LensOnCalibrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnCalibrationActionPerformed
        if (parseArguments()) {
            actionTag = "Calibration";
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateBeamShiftingParameter(xoffCalibration, yoffCalibration);
            image.paintBeamShifting();
            EduPatternShowOn.updateLensPatternPattern(image, genLogCalibration());
            imageGenerated = true;

            if (countLenOnCalibration % 2 == 0) {
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
                            countLenOnCalibration--;
                            buttonCalibrationLensOn.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }
        }

    }//GEN-LAST:event_button11LensOnCalibrationActionPerformed
	
	private void buttonSecondGenerateActionPerformedCalibration(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedCyllin
            if (parseArguments()) {
                actionTag = "Calibration";
                GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice[] devices = env.getScreenDevices();
                if (devices.length == 1) {
                    countSecondDisplayCalibration--;
                    JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                    image.updateBeamShiftingParameter(xoffCalibration, yoffCalibration);
                    image.paintBeamShifting();
                    EduPatternShowOn.updatePatternSecondDisplay(image, genLogCalibration());
                    //EduPatternTest.updateLensPatternPattern(image, genLog());
                    imageGenerated = true;
                    if (countSecondDisplayCalibration % 2 == 0) {
                        patternFrameDoubleClick.dispose();
                        patternFrame.dispose();
                    }
                }
            }
    }//GEN-LAST:event_buttonSecondGenerateActionPerformedCyllin

    private void sliderGenerateActionPerformedCalibration(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedCyllin
        actionTag = "Calibration";
        if (parseArguments()) {
            buttonCalibrationLensOn.setEnabled(true);
            buttonCalibrationDisplaySecondOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateBeamShiftingParameter(xoffCalibration, yoffCalibration);
            image.paintBeamShifting();
            EduPatternShowOn.updateLensPatternPattern(image, genLogCalibration());
            setLog(genLogCalibration());
            imageGenerated = true;
        }
    }//GEN-LAST:event_sliderGenerateActionPerformedCyllin
    
    private void keyEventGenerateActionPerformedCalibration(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyEventGenerateActionPerformedCalibration
        actionTag = "Calibration";
        if (parseArguments()) {
            //buttonMirrorLensOn.setEnabled(true); => TCL comment
           // buttonMirrorDisplaySecondOn.setEnabled(true); => TCL comment

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateBeamShiftingParameter(xoffCalibration, yoffCalibration);
            image.paintBeamShifting();
            EduPatternShowOn.updateLensPatternPattern(image, genLogCalibration());
            setLog(genLogCalibration());
            imageGenerated = true;
        }
    }//GEN-LAST:event_keyEventGenerateActionPerformedCalibration
  
    private String genLogCalibration() {
        return String.format(logmessageCalibration, Double.toString(xoffCalibration), Double.toString(yoffCalibration));
    }
    
     private boolean parseArguments() {
        boolean ret = false;
        try {          
           double xoffCali = Double.valueOf(txtXPositionCalibration.getText());
            double yoffCali = Double.valueOf(txtYPositionCalibration.getText());
            this.xoffCalibration = xoffCali;
            this.yoffCalibration = yoffCali;
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
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

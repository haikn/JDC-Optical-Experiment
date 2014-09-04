/*
 * @(#)LensWavefrontPanel.java
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
package com.jasper.ui.panel.wavefront;

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
public class LensWavefrontPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private javax.swing.JSlider sliderFocal;
    private javax.swing.JTextField textFocal;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    
    private javax.swing.JLabel lblFocalLensMichelson;
    private javax.swing.JLabel lblXLensMichelson;
    private javax.swing.JLabel lblYLensMichelson;
    private javax.swing.JSlider jSliderFocalLensMichelson;
    private DoubleJSlider jSliderXPositionLensMichelson;
    private DoubleJSlider jSliderYPositionLensMichelson;
    private javax.swing.JTextField txtFocalLensMichelson;
    private javax.swing.JTextField txtXPositionLensMichelson;
    private javax.swing.JTextField txtYPositionLensMichelson;
    private javax.swing.JButton jButton11LensOnMichelson;
    private javax.swing.JButton jButtonDisplaySecondOnMichelson;
    private javax.swing.JButton jButtonLensMichelson;
    private javax.swing.JTextArea txtAreaLog;
    
    static String logmessageLensMichelson = "Michelson lens: Focal length=%s X Position=%s Y Position=%s";
    private double xoffMichelson = 0.0, yoffMichelson = 0.0, focalMichelson = 0.0;
    private int countSecondDisplayMichelson = 1;
    private int countLenOnMichelson = 1;
    private javax.swing.JPanel panelLensMichelson;
    private javax.swing.JPanel panelMichelsonButton;
    
     public LensWavefrontPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        this.txtAreaLog = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        textFocal = new javax.swing.JTextField();
        sliderFocal = new javax.swing.JSlider();
        
        panelLensMichelson = new javax.swing.JPanel();
        lblFocalLensMichelson = new javax.swing.JLabel();
        jSliderFocalLensMichelson = new javax.swing.JSlider();
        txtFocalLensMichelson = new javax.swing.JTextField();
        lblXLensMichelson = new javax.swing.JLabel();
        txtXPositionLensMichelson = new javax.swing.JTextField();
        lblYLensMichelson = new javax.swing.JLabel();
        txtYPositionLensMichelson = new javax.swing.JTextField();
        jButtonLensMichelson = new javax.swing.JButton();
        jButton11LensOnMichelson = new javax.swing.JButton();
        jButtonDisplaySecondOnMichelson = new javax.swing.JButton();
        
        lblFocalLensMichelson.setText("Focal length (mm)");
        Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jSliderFocalLensMichelson, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtFocalLensMichelson, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblXLensMichelson.setText("X Position (mm)");

        lblYLensMichelson.setText("Y Position (mm)");

        jButtonLensMichelson.setText(labels.getString("btnGenerate"));
        jButtonLensMichelson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformedMichelSon(evt);
            }
        });

        jButton11LensOnMichelson.setEnabled(false);
        jButton11LensOnMichelson.setText(labels.getString("btnLensOn"));
        jButton11LensOnMichelson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnMichelsonActionPerformed(evt);
                countLenOnMichelson++;
                if (countLenOnMichelson % 2 == 0) {
                    jButton11LensOnMichelson.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                } else {
                    jButton11LensOnMichelson.setText(labels.getString("btnLensOn"));
                }
            }
        });

        jButtonDisplaySecondOnMichelson.setEnabled(false);
        jButtonDisplaySecondOnMichelson.setText(labels.getString("btnSecondDisplayOn"));
        jButtonDisplaySecondOnMichelson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedMichelSon(evt);
                countSecondDisplayMichelson++;
                if (countSecondDisplayMichelson % 2 == 0) {
                    jButtonDisplaySecondOnMichelson.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    jButtonDisplaySecondOnMichelson.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });
        
        jSliderXPositionLensMichelson = new DoubleJSlider(-60, 60, 1, 10);
        jSliderXPositionLensMichelson.setValue(0);
        txtXPositionLensMichelson.setText(String.valueOf(jSliderXPositionLensMichelson.getValue()));

        jSliderXPositionLensMichelson.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedLensMichelSon(evt);
                DecimalFormat df = new DecimalFormat("0.####");
                txtXPositionLensMichelson.setText(df.format(jSliderXPositionLensMichelson.getScaledValue()));
            }
        });
        
        jSliderYPositionLensMichelson = new DoubleJSlider(-30, 30, 1, 10);
        jSliderYPositionLensMichelson.setValue(0);
        txtYPositionLensMichelson.setText(String.valueOf(jSliderYPositionLensMichelson.getValue()));

        jSliderYPositionLensMichelson.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedLensMichelSon(evt);
                DecimalFormat df = new DecimalFormat("0.####");
                txtYPositionLensMichelson.setText(df.format(jSliderYPositionLensMichelson.getScaledValue()));
            }
        });

        jSliderFocalLensMichelson.setMaximum(1000);
        jSliderFocalLensMichelson.setMinimum(-1000);
        jSliderFocalLensMichelson.setValue(0);
        jSliderFocalLensMichelson.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedLensMichelSon(evt);
                String tmp = txtFocalLensMichelson.getText();
                int value = Integer.parseInt(tmp);
                if (value >= -30 && value <= 30) {
                    lblFocalLensMichelson.setForeground(Color.red);
                } else {
                    lblFocalLensMichelson.setForeground(Color.BLACK);
                }
            }
        });
        
        txtFocalLensMichelson.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyenventGenerateActionPerformedLensMichelSon(ke);
                if(txtFocalLensMichelson.getText() == null || txtFocalLensMichelson.getText().equals("")){
                    lblFocalLensMichelson.setForeground(Color.red);
                } else {
                    lblFocalLensMichelson.setForeground(Color.black);
                }
            }
        });
        
        txtXPositionLensMichelson.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyenventGenerateActionPerformedLensMichelSon(ke);
                if(txtXPositionLensMichelson.getText() == null || txtXPositionLensMichelson.getText().equals("")){
                    lblXLensMichelson.setForeground(Color.red);
                } else {
                    lblXLensMichelson.setForeground(Color.black);
                }
            }
        });
        
        txtYPositionLensMichelson.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyenventGenerateActionPerformedLensMichelSon(ke);
                if(txtYPositionLensMichelson.getText() == null || txtYPositionLensMichelson.getText().equals("")){
                    lblYLensMichelson.setForeground(Color.red);
                } else {
                    lblYLensMichelson.setForeground(Color.black);
                }
            }
        });
        
        panelMichelsonButton = new javax.swing.JPanel();
        javax.swing.GroupLayout panelMichelsonLayout = new javax.swing.GroupLayout(panelMichelsonButton);
        panelMichelsonButton.setLayout(panelMichelsonLayout);
        panelMichelsonLayout.setHorizontalGroup(
                panelMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMichelsonLayout.createSequentialGroup()
                .addGroup(panelMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMichelsonLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButtonLensMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButton11LensOnMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonDisplaySecondOnMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                )));
        if (!Utils.isMac()) {
            panelMichelsonLayout.setVerticalGroup(
                    panelMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMichelsonLayout.createSequentialGroup()
                            .addGap(85, 85, 85)
                            .addGroup(panelMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                    .addComponent(jButtonDisplaySecondOnMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton11LensOnMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonLensMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    ));
        } else {
            panelMichelsonLayout.setVerticalGroup(
                    panelMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMichelsonLayout.createSequentialGroup()
                            .addGap(118, 118, 118)
                            .addGroup(panelMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                    .addComponent(jButtonDisplaySecondOnMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton11LensOnMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonLensMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    ));
        }
        javax.swing.GroupLayout jPanelLensMichelsonLayout = new javax.swing.GroupLayout(panelLensMichelson);
        panelLensMichelson.setLayout(jPanelLensMichelsonLayout);
        jPanelLensMichelsonLayout.setHorizontalGroup(
                jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLensMichelsonLayout.createSequentialGroup()
                .addGroup(jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLensMichelsonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(15, 15, 15)
                .addComponent(lblYLensMichelson)
                .addGroup(jPanelLensMichelsonLayout.createSequentialGroup()
                .addGroup(jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(lblXLensMichelson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFocalLensMichelson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtYPositionLensMichelson)
                .addComponent(txtFocalLensMichelson, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addComponent(txtXPositionLensMichelson))))
                .addGap(5, 5, 5)
                .addGroup(jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jSliderFocalLensMichelson, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addComponent(jSliderXPositionLensMichelson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSliderYPositionLensMichelson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                )));
        jPanelLensMichelsonLayout.setVerticalGroup(
                jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLensMichelsonLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jSliderFocalLensMichelson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblFocalLensMichelson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtFocalLensMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblXLensMichelson)
                .addComponent(txtXPositionLensMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSliderXPositionLensMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelLensMichelsonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblYLensMichelson)
                .addComponent(txtYPositionLensMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSliderYPositionLensMichelson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)));
    }
    
    public JPanel getPanel() {
        return panelLensMichelson;
    }
    
    public JPanel getPanelButton() {
        return panelMichelsonButton;
    }
    
    public JTextArea getLogArea() {
        return txtAreaLog;
    }
    
    private void buttonGenerateActionPerformedMichelSon(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedMichelSon
        actionTag = "LensMichelson";
        if (parseArguments()) {
            jButton11LensOnMichelson.setEnabled(true);
            jButtonDisplaySecondOnMichelson.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensMichelsonParameter(xoffMichelson, yoffMichelson, focalMichelson);
            image.paintLensMichelson();
            EduPatternShowOn.updatePattern(image, genLogLensMichelson());
            setLog(genLogLensMichelson());
            imageGenerated = true;
        }

    }//GEN-LAST:event_buttonGenerateActionPerformedMichelSon

    private void button11LensOnMichelsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnMichelsonActionPerformed
        actionTag = "LensMichelson";
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensMichelsonParameter(xoffMichelson, yoffMichelson, focalMichelson);
            image.paintLensMichelson();
            EduPatternShowOn.updatePattern(image, genLogLensMichelson());
            setLog(genLogLensMichelson());
            imageGenerated = true;

            if (countLenOnMichelson % 2 == 0) {
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
                            countLenOnMichelson--;
                            jButton11LensOnMichelson.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }

        }

    }//GEN-LAST:event_button11LensOnMichelsonActionPerformed

    private void buttonSecondGenerateActionPerformedMichelSon(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedMichelSon
        actionTag = "LensMichelson";
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplayMichelson--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateLensMichelsonParameter(xoffMichelson, yoffMichelson, focalMichelson);
                image.paintLensMichelson();
                EduPatternShowOn.updatePatternSecondDisplay(image, genLogLensMichelson());
                setLog(genLogLensMichelson());
                imageGenerated = true;
                if (countSecondDisplayMichelson % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }//GEN-LAST:event_buttonSecondGenerateActionPerformedMichelSon

    private void sliderGenerateActionPerformedLensMichelSon(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedLensMichelSon
        actionTag = "LensMichelson";
        if (parseArguments()) {
            jButton11LensOnMichelson.setEnabled(true);
            jButtonDisplaySecondOnMichelson.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensMichelsonParameter(xoffMichelson, yoffMichelson, focalMichelson);
            image.paintLensMichelson();
            EduPatternShowOn.updatePattern(image, genLogLensMichelson());
            setLog(genLogLensMichelson());
            imageGenerated = true;
        }
    }//GEN-LAST:event_sliderGenerateActionPerformedLensMichelSon
    
    private void keyenventGenerateActionPerformedLensMichelSon(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyenventGenerateActionPerformedLensMichelSon
        actionTag = "LensMichelson";
        if (parseArguments()) {
            jButton11LensOnMichelson.setEnabled(true);
            jButtonDisplaySecondOnMichelson.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateLensMichelsonParameter(xoffMichelson, yoffMichelson, focalMichelson);
            image.paintLensMichelson();
            EduPatternShowOn.updatePattern(image, genLogLensMichelson());
            setLog(genLogLensMichelson());
            imageGenerated = true;
        }
    }//GEN-LAST:event_keyenventGenerateActionPerformedLensMichelSon
    
    private String genLogLensMichelson() {
        return String.format(logmessageLensMichelson, Double.toString(focalMichelson), Double.toString(xoffMichelson), Double.toString(yoffMichelson));
    }

    
    private boolean parseArguments() {
        boolean ret = false;
        try {          
           
            double xoffMi = Double.valueOf(txtXPositionLensMichelson.getText());
            double yoffMi = Double.valueOf(txtYPositionLensMichelson.getText());
            double focalMi = Double.valueOf(txtFocalLensMichelson.getText());
            this.xoffMichelson = xoffMi / 1000;
            this.yoffMichelson = yoffMi / 1000;
            this.focalMichelson = focalMi / 1000;
            ret = true;
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, warnings);
            //textXpos.setText(String.valueOf(this.yoff));
            //textYpos.setText(String.valueOf(this.yoff));
            textFocal.setText(String.valueOf(this.focal));
        }
        return ret;
    }
    
    public void setLog(String msg) {
        txtAreaLog.append(msg + System.getProperty("line.separator"));
    }
}

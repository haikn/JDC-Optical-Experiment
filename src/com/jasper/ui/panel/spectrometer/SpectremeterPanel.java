/*
 * @(#)SpectremeterPanel.java
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
package com.jasper.ui.panel.spectrometer;

import com.jasper.core.OpticsPane;
import java.awt.GraphicsDevice;
import javax.swing.JOptionPane;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.awt.event.KeyAdapter;
import com.jasper.core.PatternImage;
import com.jasper.ui.widget.DoubleJSlider;
import com.jasper.ui.EduLensOn11;
import com.jasper.ui.EduPatternJPanel;
import com.jasper.ui.EduPatternShowOn;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
public class SpectremeterPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private javax.swing.JLabel lblPhySpectometer;
    private javax.swing.JLabel lblThetaMirrorSpectometer;
    private DoubleJSlider jSliderPhyMirrorSpectometer;
    private DoubleJSlider jSliderThetaMirrorSpectometer;
    private javax.swing.JTextField txtPhyMirrorSpectometer;
    private javax.swing.JTextField txtThetaMirrorSpectometer;
    private javax.swing.JButton buttonMirrorSpectometerLensOn;
    private javax.swing.JButton buttonMirrorSpectometerDisplaySecondOn;
    private javax.swing.JButton buttonMirrorSpectometerGeneral;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelButton;
    private javax.swing.JTextArea txtLogArea;
    
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    
    private int countLenOnSpectrometer = 1;
    private int countSecondDisplaySpectrometer = 1;
    
    static String logmessageMirrorSpectrometer = "Mirror Spectrometer : Phy=%s Theta=%s";
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    private double phySpectrometer = 522, thetaSpectrometer = 522;
    
    public SpectremeterPanel(ResourceBundle labels, BindingGroup bindingGroup, JPanel panelPattern) {
        this.labels = labels;
        this.txtLogArea = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panelButton = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        lblPhySpectometer = new javax.swing.JLabel();
        lblThetaMirrorSpectometer = new javax.swing.JLabel();
        txtPhyMirrorSpectometer = new javax.swing.JTextField();
        txtThetaMirrorSpectometer = new javax.swing.JTextField();
        buttonMirrorSpectometerLensOn = new javax.swing.JButton();
        buttonMirrorSpectometerDisplaySecondOn = new javax.swing.JButton();
        buttonMirrorSpectometerGeneral = new javax.swing.JButton();

        lblPhySpectometer.setText("Diffraction angle (degree)");

        lblThetaMirrorSpectometer.setText("Rotation (degree)");

        buttonMirrorSpectometerGeneral.setText(labels.getString("btnGenerate"));
        buttonMirrorSpectometerGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformedMirrorSpectometer(evt);
            }
        });
        

        buttonMirrorSpectometerLensOn.setEnabled(false);
        buttonMirrorSpectometerLensOn.setText(labels.getString("btnLensOn"));
        buttonMirrorSpectometerLensOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnMirrorSpectometerActionPerformed(evt);
                countLenOnSpectrometer++;
                if (countLenOnSpectrometer % 2 == 0) {
                    buttonMirrorSpectometerLensOn.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                } else {
                    buttonMirrorSpectometerLensOn.setText(labels.getString("btnLensOn"));
                }
            }
        });

        buttonMirrorSpectometerDisplaySecondOn.setEnabled(false);
        buttonMirrorSpectometerDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
        buttonMirrorSpectometerDisplaySecondOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedMirrorSpectometer(evt);
                countSecondDisplaySpectrometer++;
                if (countSecondDisplaySpectrometer % 2 == 0) {
                    buttonMirrorSpectometerDisplaySecondOn.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    buttonMirrorSpectometerDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });

        jSliderPhyMirrorSpectometer = new DoubleJSlider(-28, 28, 10, 10);
        jSliderPhyMirrorSpectometer.setValue(0);
        txtPhyMirrorSpectometer.setText(String.valueOf(jSliderPhyMirrorSpectometer.getValue()));

        jSliderPhyMirrorSpectometer.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedMirrorSpectometer(evt);
                DecimalFormat df = new DecimalFormat("0.####");
                txtPhyMirrorSpectometer.setText(df.format(jSliderPhyMirrorSpectometer.getScaledValue()));
            }
        });
        
        txtPhyMirrorSpectometer.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyEventGenerateActionPerformedMirrorSpectometer(ke);
                if(txtPhyMirrorSpectometer.getText() == null || txtPhyMirrorSpectometer.getText().equals("")){
                    lblPhySpectometer.setForeground(Color.red);
                } else {
                    lblPhySpectometer.setForeground(Color.black);
                }
            }
        });
        
        jSliderThetaMirrorSpectometer = new DoubleJSlider(-1800, 1800, 100, 10);
        jSliderThetaMirrorSpectometer.setValue(0);
        txtThetaMirrorSpectometer.setText(String.valueOf(jSliderThetaMirrorSpectometer.getValue()));

        jSliderThetaMirrorSpectometer.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedMirrorSpectometer(evt);
                DecimalFormat df = new DecimalFormat("0.####");
                txtThetaMirrorSpectometer.setText(df.format(jSliderThetaMirrorSpectometer.getScaledValue()));
            }
        });
        txtThetaMirrorSpectometer.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyEventGenerateActionPerformedMirrorSpectometer(ke);
                if(txtThetaMirrorSpectometer.getText() == null || txtThetaMirrorSpectometer.getText().equals("")){
                    lblThetaMirrorSpectometer.setForeground(Color.red);
                } else {
                    lblThetaMirrorSpectometer.setForeground(Color.black);
                }
                
            }
        });
        
        javax.swing.GroupLayout panelButtonExp6Layout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonExp6Layout);
        panelButtonExp6Layout.setHorizontalGroup(
                panelButtonExp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonExp6Layout.createSequentialGroup()
                .addGroup(panelButtonExp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonExp6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonMirrorSpectometerGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonMirrorSpectometerLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonMirrorSpectometerDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)                
                )
                )));
        if (!Utils.isMac()) {
            panelButtonExp6Layout.setVerticalGroup(
                panelButtonExp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonExp6Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(panelButtonExp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonMirrorSpectometerDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonMirrorSpectometerLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonMirrorSpectometerGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        } else {
            panelButtonExp6Layout.setVerticalGroup(
                panelButtonExp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonExp6Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(panelButtonExp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonMirrorSpectometerDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonMirrorSpectometerLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonMirrorSpectometerGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        }

        javax.swing.GroupLayout jPanelMirrorSpectometerlLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanelMirrorSpectometerlLayout);
        
        if (!Utils.isMac()) {
            jPanelMirrorSpectometerlLayout.setHorizontalGroup(
                jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMirrorSpectometerlLayout.createSequentialGroup()
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMirrorSpectometerlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(5, 5, 5)
                .addComponent(lblThetaMirrorSpectometer, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGroup(jPanelMirrorSpectometerlLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblPhySpectometer)
                .addGap(15, 15, 15)
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtThetaMirrorSpectometer, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addComponent(txtPhyMirrorSpectometer))))
                .addGap(5, 5, 5)
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jSliderPhyMirrorSpectometer, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addComponent(jSliderThetaMirrorSpectometer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                )));
            jPanelMirrorSpectometerlLayout.setVerticalGroup(
                    jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMirrorSpectometerlLayout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblPhySpectometer)
                                            .addComponent(txtPhyMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSliderPhyMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(15, 15, 15)
                            .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblThetaMirrorSpectometer)
                                            .addComponent(txtThetaMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSliderThetaMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(8, 8, 8)));
        } else {
            jPanelMirrorSpectometerlLayout.setHorizontalGroup(
                jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMirrorSpectometerlLayout.createSequentialGroup()
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMirrorSpectometerlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(5, 5, 5)
                .addComponent(lblThetaMirrorSpectometer, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGroup(jPanelMirrorSpectometerlLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblPhySpectometer)
                .addGap(8, 8, 8)
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtThetaMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPhyMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jSliderPhyMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jSliderThetaMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)))
                )));
            jPanelMirrorSpectometerlLayout.setVerticalGroup(
                jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMirrorSpectometerlLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblPhySpectometer)
                .addComponent(txtPhyMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSliderPhyMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelMirrorSpectometerlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblThetaMirrorSpectometer)
                .addComponent(txtThetaMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSliderThetaMirrorSpectometer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)));
        }
    }
    
    public JPanel getPanel(){
        return panel;
    }
    
    public JPanel getPanelButton(){
        return panelButton;
    }
    
    public JTextArea getLogArea(){
        return txtLogArea;
    }
                
    private void buttonGenerateActionPerformedMirrorSpectometer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedMirrorSpectometer
        actionTag = "MirrorSpectometer";
        if (parseArguments()) {
            buttonMirrorSpectometerLensOn.setEnabled(true);
            buttonMirrorSpectometerDisplaySecondOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateMirrorSpectometerParameter(phySpectrometer, thetaSpectrometer);
            image.paintMirrorSpectrometer();
            EduPatternShowOn.updatePattern(image, genLogMirrorSpectrometer());
            setLog(genLogMirrorSpectrometer());
            imageGenerated = true;
        }

    }//GEN-LAST:event_buttonGenerateActionPerformedMirrorSpectometer

    private void button11LensOnMirrorSpectometerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnMirrorSpectometerActionPerformed
        actionTag = "MirrorSpectometer";
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateMirrorSpectometerParameter(phySpectrometer, thetaSpectrometer);
            image.paintMirrorSpectrometer();
            EduPatternShowOn.updatePattern(image, genLogMirrorSpectrometer());
            setLog(genLogMirrorSpectrometer());
            imageGenerated = true;

            if (countLenOnSpectrometer % 2 == 0) {
                magFrameLenon.dispose();
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
                            countLenOnSpectrometer--;
                            buttonMirrorSpectometerLensOn.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }

        }

    }//GEN-LAST:event_button11LensOnMirrorSpectometerActionPerformed

    private void buttonSecondGenerateActionPerformedMirrorSpectometer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedMirrorSpectometer
        actionTag = "MirrorSpectometer";
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplaySpectrometer--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateMirrorSpectometerParameter(phySpectrometer, thetaSpectrometer);
                image.paintMirrorSpectrometer();
                EduPatternShowOn.updatePatternSecondDisplay(image, genLogMirrorSpectrometer());
                setLog(genLogMirrorSpectrometer());
                imageGenerated = true;
                if (countSecondDisplaySpectrometer % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }//GEN-LAST:event_buttonSecondGenerateActionPerformedMirrorSpectometer
    
    private void keyEventGenerateActionPerformedMirrorSpectometer(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyEventGenerateActionPerformedMirrorSpectometer
        actionTag = "MirrorSpectometer";
        if (parseArguments()) {
            buttonMirrorSpectometerLensOn.setEnabled(true);
            buttonMirrorSpectometerDisplaySecondOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateMirrorSpectometerParameter(phySpectrometer, thetaSpectrometer);
            image.paintMirrorSpectrometer();
            EduPatternShowOn.updatePattern(image, genLogMirrorSpectrometer());
            setLog(genLogMirrorSpectrometer());
            imageGenerated = true;
        }
    }

    private void sliderGenerateActionPerformedMirrorSpectometer(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedMirrorSpectometer
        actionTag = "MirrorSpectometer";
        if (parseArguments()) {
            buttonMirrorSpectometerLensOn.setEnabled(true);
            buttonMirrorSpectometerDisplaySecondOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateMirrorSpectometerParameter(phySpectrometer, thetaSpectrometer);
            image.paintMirrorSpectrometer();
            EduPatternShowOn.updatePattern(image, genLogMirrorSpectrometer());
            setLog(genLogMirrorSpectrometer());
            imageGenerated = true;
        }
    }//GEN-LAST:event_sliderGenerateActionPerformedMirrorSpectometer
    
    
    private boolean parseArguments() {
        boolean ret = false;
        try {          
           
            double phySpec = Double.valueOf(txtPhyMirrorSpectometer.getText());
            double thetaSpec = Double.valueOf(txtThetaMirrorSpectometer.getText());
            this.thetaSpectrometer = thetaSpec;
            this.phySpectrometer = phySpec;
            ret = true;
            
        } catch (Exception e) {
        }
        return ret;
    }
    
     private String genLogMirrorSpectrometer() {
        return String.format(logmessageMirrorSpectrometer, Double.toString(phySpectrometer), Double.toString(thetaSpectrometer));
    }
    
    public void setLog(String msg) {
        txtLogArea.append(msg + System.getProperty("line.separator"));
    }
}

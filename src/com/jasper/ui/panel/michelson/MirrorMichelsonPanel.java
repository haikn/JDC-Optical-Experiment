/*
 * @(#)MirrorMichelsonPanel.java
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
package com.jasper.ui.panel.michelson;

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
public class MirrorMichelsonPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private javax.swing.JSlider sliderFocal;
    private javax.swing.JTextField textFocal;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    private javax.swing.JTabbedPane jTabbedControler;
     private javax.swing.JLabel lblPhy;
    private javax.swing.JLabel lblThetaMirror;
    private DoubleJSlider jSliderPhyMirror;
    private DoubleJSlider jSliderThetaMirror;
    private javax.swing.JTextField txtPhyMirror;
    private javax.swing.JTextField txtThetaMirror;
    private javax.swing.JButton buttonMirrorLensOn;
    private javax.swing.JButton buttonMirrorDisplaySecondOn;
    private javax.swing.JButton buttonMirrorGeneral;
    private javax.swing.JPanel panelMirror;
    private javax.swing.JPanel panelButtonMirror;
    private javax.swing.JTextArea txtAreaLog;
    static String logmessageMirror = "Mirror : Phy=%s Theta=%s";
     private double phy = 1, theta = 1;
     private int countSecondDisplayMirror = 1;
     private int countLenOnMirror = 1;
       
    public MirrorMichelsonPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        this.txtAreaLog = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        //this.jTabbedControler = jTabbedControler;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        textFocal = new javax.swing.JTextField();
        sliderFocal = new javax.swing.JSlider();
        panelMirror = new javax.swing.JPanel();
        panelButtonMirror = new javax.swing.JPanel();
        lblPhy = new javax.swing.JLabel();
        lblThetaMirror = new javax.swing.JLabel();
        txtPhyMirror = new javax.swing.JTextField();
        txtThetaMirror = new javax.swing.JTextField();
        buttonMirrorLensOn = new javax.swing.JButton();
        buttonMirrorDisplaySecondOn = new javax.swing.JButton();
        buttonMirrorGeneral = new javax.swing.JButton();
        
        lblPhy.setText("Diffraction angle (degree)");
        lblThetaMirror.setText("Rotation (degree)");

        buttonMirrorGeneral.setText(labels.getString("btnGenerate"));
        buttonMirrorGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformedMirror(evt);
            }
        });

        buttonMirrorLensOn.setEnabled(false);
        buttonMirrorLensOn.setText(labels.getString("btnLensOn"));
        buttonMirrorLensOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnMirrorActionPerformed(evt);
                countLenOnMirror++;
                if (countLenOnMirror % 2 == 0) {
                    buttonMirrorLensOn.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                } else {
                    buttonMirrorLensOn.setText(labels.getString("btnLensOn"));
                }
            }
        });

        buttonMirrorDisplaySecondOn.setEnabled(false);
        buttonMirrorDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
        buttonMirrorDisplaySecondOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedMirror(evt);
                countSecondDisplayMirror++;
                if (countSecondDisplayMirror % 2 == 0) {
                    buttonMirrorDisplaySecondOn.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    buttonMirrorDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });

        jSliderPhyMirror = new DoubleJSlider(-28, 28, 10, 10);
        jSliderPhyMirror.setValue(0);
        txtPhyMirror.setText(String.valueOf(jSliderPhyMirror.getValue()));

        jSliderPhyMirror.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedMirror(evt);
                DecimalFormat df = new DecimalFormat("0.####");
                txtPhyMirror.setText(df.format(jSliderPhyMirror.getScaledValue()));
            }
        });
        
        txtPhyMirror.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyEventGenerateActionPerformedMirror(ke);
                if(txtPhyMirror.getText() == null || txtPhyMirror.getText().equals("")){
                    lblPhy.setForeground(Color.red);
                } else {
                    lblPhy.setForeground(Color.black);
                }
            }
        });
        
        txtThetaMirror.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if(txtThetaMirror.getText() == null || txtThetaMirror.getText().equals("")){
                    lblThetaMirror.setForeground(Color.red);
                } else {
                    lblThetaMirror.setForeground(Color.black);
                }
                keyEventGenerateActionPerformedMirror(ke);
            }
        });

        jSliderThetaMirror = new DoubleJSlider(-1800, 1800, 100, 10);
        jSliderThetaMirror.setValue(0);
        txtThetaMirror.setText(String.valueOf(jSliderThetaMirror.getValue()));

        jSliderThetaMirror.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedMirror(evt);                
                DecimalFormat df = new DecimalFormat("0.####");
                txtThetaMirror.setText(df.format(jSliderThetaMirror.getScaledValue()));
            }
        });
        //panelButtonMirror = new javax.swing.JPanel();
        javax.swing.GroupLayout panelMirrorLayout = new javax.swing.GroupLayout(panelButtonMirror);
        panelButtonMirror.setLayout(panelMirrorLayout);
        panelMirrorLayout.setHorizontalGroup(
                panelMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMirrorLayout.createSequentialGroup()
                .addGroup(panelMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMirrorLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonMirrorGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonMirrorLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonMirrorDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                )));
        if (!Utils.isMac()){
            panelMirrorLayout.setVerticalGroup(
                    panelMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMirrorLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(panelMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(buttonMirrorDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonMirrorLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonMirrorGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    ));
        } else {
            panelMirrorLayout.setVerticalGroup(
                panelMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMirrorLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(panelMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonMirrorDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonMirrorLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonMirrorGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        }
        javax.swing.GroupLayout jPanelMirrorlLayout = new javax.swing.GroupLayout(panelMirror);
        panelMirror.setLayout(jPanelMirrorlLayout);
        jPanelMirrorlLayout.setHorizontalGroup(
            jPanelMirrorlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMirrorlLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanelMirrorlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMirrorlLayout.createSequentialGroup()
                        .addGroup(jPanelMirrorlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblThetaMirror, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(lblPhy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanelMirrorlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtThetaMirror)
                            .addComponent(txtPhyMirror, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanelMirrorlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSliderThetaMirror, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                            .addComponent(jSliderPhyMirror, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, 0))
        );
        jPanelMirrorlLayout.setVerticalGroup(
            jPanelMirrorlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMirrorlLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelMirrorlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSliderPhyMirror, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMirrorlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPhy)
                        .addComponent(txtPhyMirror, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelMirrorlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMirrorlLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanelMirrorlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblThetaMirror)
                            .addComponent(txtThetaMirror, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelMirrorlLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jSliderThetaMirror, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, 0))
        );

        //jTabbedControler.addTab(labels.getString("tabMirror"), panelMirror);
    }
    
    public JPanel getJPanelMirror() {
        return panelMirror;
    }
    
    public JPanel getJPanelButtonMirror() {
        return panelButtonMirror;
    }
    
    public JTextArea getLogArea() {
        return txtAreaLog;
    }
    
    private void buttonGenerateActionPerformedMirror(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedMirror
        actionTag = "Mirror";
        if (parseArguments()) {
            buttonMirrorLensOn.setEnabled(true);
            buttonMirrorDisplaySecondOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateMirrorParameter(phy, theta);
            image.paintMirror();
            EduPatternShowOn.updatePattern(image, genLogMirror());
            setLog(genLogMirror());
            imageGenerated = true;
        }

    }//GEN-LAST:event_buttonGenerateActionPerformedMirror

    private void button11LensOnMirrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnMirrorActionPerformed
        actionTag = "Cyllin";
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateMirrorParameter(phy, theta);
            image.paintMirror();
            EduPatternShowOn.updatePattern(image, genLogMirror());
            setLog(genLogMirror());
            imageGenerated = true;

            if (countLenOnMirror % 2 == 0) {
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
                            countLenOnMirror--;
                            buttonMirrorLensOn.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }

        }

    }//GEN-LAST:event_button11LensOnMirrorActionPerformed

    private void buttonSecondGenerateActionPerformedMirror(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedCyllin
        actionTag = "Mirror";
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplayMirror--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateMirrorParameter(phy, theta);
                image.paintMirror();
                EduPatternShowOn.updatePatternSecondDisplay(image, genLogMirror());
                setLog(genLogMirror());
                //EduPatternTest.updateLensPatternPattern(image, genLog());
                imageGenerated = true;
                if (countSecondDisplayMirror % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }//GEN-LAST:event_buttonSecondGenerateActionPerformedCyllin

    private void sliderGenerateActionPerformedMirror(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedCyllin
        actionTag = "Mirror";
        if (parseArguments()) {
            buttonMirrorLensOn.setEnabled(true);
            buttonMirrorDisplaySecondOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateMirrorParameter(phy, theta);
            image.paintMirror();
            EduPatternShowOn.updatePattern(image, genLogMirror());
            setLog(genLogMirror());
            imageGenerated = true;
        }
    }//GEN-LAST:event_sliderGenerateActionPerformedCyllin
    private void keyEventGenerateActionPerformedMirror(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedCyllin
        actionTag = "Mirror";
        if (parseArguments()) {
            buttonMirrorLensOn.setEnabled(true);
            buttonMirrorDisplaySecondOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateMirrorParameter(phy, theta);
            image.paintMirror();
            EduPatternShowOn.updatePattern(image, genLogMirror());
            setLog(genLogMirror());
            imageGenerated = true;
        }
    }//GEN-LAST:event_sliderGenerateActionPerformedCyllin
    
    private String genLogMirror() {
        return String.format(logmessageMirror, Double.toString(phy), Double.toString(theta));
    }

    
    private boolean parseArguments() {
        boolean ret = false;
        try {          
            double phyoff = Double.valueOf(txtPhyMirror.getText());
            double thetaoff = Double.valueOf(txtThetaMirror.getText());
             this.phy = phyoff;
            this.theta = thetaoff;
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

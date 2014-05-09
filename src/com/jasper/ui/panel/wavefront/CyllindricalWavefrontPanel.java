/*
 * @(#)CyllindricalWavefrontPanel.java
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import org.jdesktop.beansbinding.Binding;
import java.awt.Color;

import javax.swing.JTabbedPane;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JOptionPane;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import com.jasper.core.PatternImage;
import com.jasper.ui.EduLensOn11;
import com.jasper.ui.EduPatternJPanel;
import com.jasper.ui.EduPatternShowOn;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import org.jdesktop.beansbinding.BindingGroup;
import static com.jasper.ui.EduPatternShowOn.patternFrameDoubleClick;
import static com.jasper.ui.EduPatternShowOn.patternFrame;
import javax.swing.JTextArea;

/**
 *
 * @author sonnv
 */
public class CyllindricalWavefrontPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private javax.swing.JTabbedPane jTabbedControler;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    
    public javax.swing.JLabel lblFocalCyllin;
    private javax.swing.JLabel lblXPosCyllin;
    private javax.swing.JLabel lblYPosCyllin;
    private javax.swing.JSlider jSliderFocalCyllin;
    private javax.swing.JSlider jSliderXPositionCyllin;
    private javax.swing.JSlider jSliderYPositionCyllin;
    private javax.swing.JTextField txtFocalCyllin;
    private javax.swing.JTextField txtXPositionCyllin;
    private javax.swing.JTextField txtYPositionCyllin;
    private javax.swing.JButton buttonCyllinLensOn;
    private javax.swing.JButton buttonCyllinDisplaySecondOn;
    private javax.swing.JButton buttonCyllinGeneral;
    private javax.swing.JSlider sliderFocal;
    private javax.swing.JTextField textFocal;
    private javax.swing.JPanel jPanelCyllindrical;
    private javax.swing.JPanel panelButton;
    private javax.swing.JTextArea textAreaLog;
    
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    private int countLenOnCyllin = 1;
    private int countSecondDisplayCyllin = 1;
    private double xoffCyllin = 0, yoffCyllin = 0, focalCyllin = 0.0;
    static String logmessageCyllin = "Cyllin : Focal length=%s X Position=%s Y Position=%s";
    
     public CyllindricalWavefrontPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern, JTabbedPane jTabbedControler) {
        this.labels = labels;
        this.textAreaLog = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        this.jTabbedControler = jTabbedControler;
        
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        initComponents(bindingGroup);
    }
     
     private void initComponents(BindingGroup bindingGroup) {
        textFocal = new javax.swing.JTextField();
        sliderFocal = new javax.swing.JSlider();
        
        jPanelCyllindrical = new javax.swing.JPanel();
        lblFocalCyllin = new javax.swing.JLabel();
        lblXPosCyllin = new javax.swing.JLabel();
        lblYPosCyllin = new javax.swing.JLabel();
        jSliderFocalCyllin = new javax.swing.JSlider();
        jSliderXPositionCyllin = new javax.swing.JSlider();
        jSliderYPositionCyllin = new javax.swing.JSlider();
        txtFocalCyllin = new javax.swing.JTextField();
        txtXPositionCyllin = new javax.swing.JTextField();
        txtYPositionCyllin = new javax.swing.JTextField();
        buttonCyllinLensOn = new javax.swing.JButton();
        buttonCyllinDisplaySecondOn = new javax.swing.JButton();
        buttonCyllinGeneral = new javax.swing.JButton();
        
        lblFocalCyllin.setText(labels.getString("lblFocalLength"));
        Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jSliderFocalCyllin, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtFocalCyllin, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblXPosCyllin.setText(labels.getString("paramPositions"));
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jSliderXPositionCyllin, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtXPositionCyllin, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblYPosCyllin.setText(labels.getString("paramRotation"));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jSliderYPositionCyllin, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtYPositionCyllin, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        txtFocalCyllin.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if(txtFocalCyllin.getText() == null || txtFocalCyllin.getText().equals("")){
                    lblFocalCyllin.setForeground(Color.red);
                } else {
                    lblFocalCyllin.setForeground(Color.black);
                }
                keyeventGenerateActionPerformedCyllin(ke);
            }
        });
        txtXPositionCyllin.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if(txtXPositionCyllin.getText() == null || txtXPositionCyllin.getText().equals("")){
                    lblXPosCyllin.setForeground(Color.red);
                } else {
                    lblXPosCyllin.setForeground(Color.black);
                }
                keyeventGenerateActionPerformedCyllin(ke);
            }
        });
        txtYPositionCyllin.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if(txtYPositionCyllin.getText() == null || txtYPositionCyllin.getText().equals("")){
                    lblYPosCyllin.setForeground(Color.red);
                } else {
                    lblYPosCyllin.setForeground(Color.black);
                }
                keyeventGenerateActionPerformedCyllin(ke);
            }
        });
        
        buttonCyllinGeneral.setText(labels.getString("btnGenerate"));
        buttonCyllinGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformedCyllin(evt);
            }
        });

        buttonCyllinLensOn.setEnabled(false);
        buttonCyllinLensOn.setText(labels.getString("btnLensOn"));
        buttonCyllinLensOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnCyllinActionPerformed(evt);
                countLenOnCyllin++;
                if (countLenOnCyllin % 2 == 0) {
                    buttonCyllinLensOn.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                } else {
                    buttonCyllinLensOn.setText(labels.getString("btnLensOn"));
                }
            }
        });

        buttonCyllinDisplaySecondOn.setEnabled(false);
        buttonCyllinDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
        buttonCyllinDisplaySecondOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedCyllin(evt);
                countSecondDisplayCyllin++;
                if (countSecondDisplayCyllin % 2 == 0) {
                    buttonCyllinDisplaySecondOn.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    buttonCyllinDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });

        jSliderFocalCyllin.setMaximum(1000);
        jSliderFocalCyllin.setMinimum(-1000);
        jSliderFocalCyllin.setValue(0);
        jSliderFocalCyllin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedCyllin(evt);
            }
        });

        jSliderXPositionCyllin.setMaximum(1000);
        jSliderXPositionCyllin.setMinimum(-1000);
        jSliderXPositionCyllin.setValue(0);
        jSliderXPositionCyllin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedCyllin(evt);
            }
        });

        jSliderYPositionCyllin.setMaximum(180);
        jSliderYPositionCyllin.setMinimum(-180);
        jSliderYPositionCyllin.setValue(0);
        jSliderYPositionCyllin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedCyllin(evt);
            }
        });
        panelButton = new javax.swing.JPanel();
        javax.swing.GroupLayout panelCyllinLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelCyllinLayout);
        panelCyllinLayout.setHorizontalGroup(
                panelCyllinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCyllinLayout.createSequentialGroup()
                .addGroup(panelCyllinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCyllinLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonCyllinGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonCyllinLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonCyllinDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                )));
        panelCyllinLayout.setVerticalGroup(
                panelCyllinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCyllinLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(panelCyllinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonCyllinDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonCyllinLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonCyllinGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));

        javax.swing.GroupLayout jPanelCyllindricalLayout = new javax.swing.GroupLayout(jPanelCyllindrical);
        jPanelCyllindrical.setLayout(jPanelCyllindricalLayout);
        jPanelCyllindricalLayout.setHorizontalGroup(
                jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelCyllindricalLayout.createSequentialGroup()
                .addGroup(jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelCyllindricalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblFocalCyllin)
                .addGroup(jPanelCyllindricalLayout.createSequentialGroup()
                .addGroup(jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(lblXPosCyllin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblYPosCyllin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtYPositionCyllin)
                .addComponent(txtFocalCyllin, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addComponent(txtXPositionCyllin))))
                .addGap(8, 8, 8)
                .addGroup(jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jSliderFocalCyllin, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addComponent(jSliderXPositionCyllin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSliderYPositionCyllin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                )
                .addContainerGap(5, Short.MAX_VALUE)));
        jPanelCyllindricalLayout.setVerticalGroup(
                jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelCyllindricalLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jSliderFocalCyllin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblFocalCyllin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtFocalCyllin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblXPosCyllin)
                .addComponent(txtXPositionCyllin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSliderXPositionCyllin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelCyllindricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblYPosCyllin)
                .addComponent(txtYPositionCyllin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSliderYPositionCyllin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                ));

        jTabbedControler.addTab(labels.getString("tabCyllindrical"), jPanelCyllindrical);
     }
     
    public JPanel getPanel() {
        return jPanelCyllindrical;
    }
     
    public JPanel getPanelButton() {
        return panelButton;
    }
     
    public JTextArea getLogArea() {
        return textAreaLog;
    }
    
    public void setLog(String msg) {
        textAreaLog.append(msg + System.getProperty("line.separator"));
    }
     
    private void keyeventGenerateActionPerformedCyllin(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedCyllin
        actionTag = "Cyllin";
        if (parseArguments()) {
            buttonCyllinLensOn.setEnabled(true);
            buttonCyllinDisplaySecondOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateCyllindricalParameter(xoffCyllin, yoffCyllin, focalCyllin);
            image.paintCylindrical();
            EduPatternShowOn.updatePattern(image, genLogCyllin());
            setLog(genLogCyllin());
            imageGenerated = true;
        }
    }//GEN-LAST:event_sliderGenerateActionPerformedCyllin
    
    private void buttonGenerateActionPerformedCyllin(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedCyllin
        actionTag = "Cyllin";
        if (parseArguments()) {
            buttonCyllinLensOn.setEnabled(true);
            buttonCyllinDisplaySecondOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateCyllindricalParameter(xoffCyllin, yoffCyllin, focalCyllin);
            image.paintCylindrical();
            EduPatternShowOn.updatePattern(image, genLogCyllin());
            setLog(genLogCyllin());
            imageGenerated = true;
        }

    }//GEN-LAST:event_buttonGenerateActionPerformedCyllin

    private void button11LensOnCyllinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnCyllinActionPerformed
        actionTag = "Cyllin";
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateCyllindricalParameter(xoffCyllin, yoffCyllin, focalCyllin);
            image.paintCylindrical();
            EduPatternShowOn.updatePattern(image, genLogCyllin());
            setLog(genLogCyllin());
            imageGenerated = true;

            if (countLenOnCyllin % 2 == 0) {
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
                            countLenOnCyllin--;
                            buttonCyllinLensOn.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }

        }

    }//GEN-LAST:event_button11LensOnCyllinActionPerformed

    private void buttonSecondGenerateActionPerformedCyllin(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedCyllin
        actionTag = "Cyllin";
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplayCyllin--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateCyllindricalParameter(xoffCyllin, yoffCyllin, focalCyllin);
                image.paintCylindrical();
                EduPatternShowOn.updatePatternSecondDisplay(image, genLogCyllin());
                setLog(genLogCyllin());
                //EduPatternTest.updateLensPatternPattern(image, genLog());
                imageGenerated = true;
                if (countSecondDisplayCyllin % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }//GEN-LAST:event_buttonSecondGenerateActionPerformedCyllin

    private void sliderGenerateActionPerformedCyllin(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedCyllin
        actionTag = "Cyllin";
        if (parseArguments()) {
            buttonCyllinLensOn.setEnabled(true);
            buttonCyllinDisplaySecondOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateCyllindricalParameter(xoffCyllin, yoffCyllin, focalCyllin);
            image.paintCylindrical();
            EduPatternShowOn.updatePattern(image, genLogCyllin());
            setLog(genLogCyllin());
            imageGenerated = true;
        }
    }//GEN-LAST:event_sliderGenerateActionPerformedCyllin
    
    private boolean parseArguments() {
        boolean ret = false;
        try {          
           
            double xoffCyllin = Double.valueOf(txtXPositionCyllin.getText());
            double yoffCyllin = Double.valueOf(txtYPositionCyllin.getText());
            double focalCyllin = Double.valueOf(txtFocalCyllin.getText());
            
            this.xoffCyllin = xoffCyllin;
            this.yoffCyllin = yoffCyllin;
            this.focalCyllin = focalCyllin/100;
            ret = true;
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, warnings);
            //textXpos.setText(String.valueOf(this.yoff));
            //textYpos.setText(String.valueOf(this.yoff));
            textFocal.setText(String.valueOf(this.focal));
        }
        return ret;
    }
    
    private String genLogCyllin() {
        return String.format(logmessageCyllin, Double.toString(focalCyllin), Double.toString(xoffCyllin), Double.toString(yoffCyllin));
    }
}

/*
 * @(#)PhaseRetarderPanel.java
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
package com.jasper.ui.panel.phaseshifting;

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
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JTextArea;

/**
 *
 * @author sonnv
 */
public class PhaseRetarderPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    
    private javax.swing.JButton button11LensOnPhase;
    private javax.swing.JButton buttonGeneralPhase;
    private javax.swing.JButton buttonOpenFile;
    private javax.swing.JButton buttonSecondPhase;
    private javax.swing.JSlider grayLevel;
    private javax.swing.JLabel lblGrayLevel;
    
    private javax.swing.JTextField txtGrayLevel;
    private int countSecondDisplayPhase = 1;
    private int countLenOnPhase = 1;
    private javax.swing.JPanel panelPhaseExp1;
    private javax.swing.JPanel panelButtonPhase;
    private javax.swing.JTextArea textAreaLog;
    
    static String logmessagePhase = "Phase retarder: gray=%s";
    private double grayLevelValue = 255;
    
    public PhaseRetarderPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        
        this.panelPattern = panelPattern;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        this.textAreaLog = new javax.swing.JTextArea();
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panelPhaseExp1 = new javax.swing.JPanel();
        panelButtonPhase = new javax.swing.JPanel();
        
        grayLevel = new javax.swing.JSlider();
        buttonOpenFile = new javax.swing.JButton();
        lblGrayLevel = new javax.swing.JLabel();
        buttonSecondPhase = new javax.swing.JButton();
        button11LensOnPhase = new javax.swing.JButton();
        buttonGeneralPhase = new javax.swing.JButton();
        txtGrayLevel = new javax.swing.JTextField();

        lblGrayLevel.setText(labels.getString("paramGrayLevel"));
        buttonOpenFile.hide();

        buttonSecondPhase.setEnabled(false);
        buttonSecondPhase.setText(labels.getString("btnSecondDisplayOn"));
        buttonSecondPhase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedPhase(evt);
                countSecondDisplayPhase++;
                if (countSecondDisplayPhase % 2 == 0) {
                    buttonSecondPhase.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    buttonSecondPhase.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });

        button11LensOnPhase.setEnabled(false);
        button11LensOnPhase.setText(labels.getString("btnLensOn"));
        button11LensOnPhase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnPhaseActionPerformed(evt);
                countLenOnPhase++;
                if (countLenOnPhase % 2 == 0) {
                    button11LensOnPhase.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                } else {
                    button11LensOnPhase.setText(labels.getString("btnLensOn"));
                }
            }
        });

        buttonGeneralPhase.setText(labels.getString("btnGenerate"));
        buttonGeneralPhase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformedPhase(evt);
            }
        });

        Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, grayLevel, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtGrayLevel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        grayLevel.setMaximum(255);
        grayLevel.setValue(0);
        grayLevel.setMajorTickSpacing(255);
        grayLevel.setPaintLabels(true);
        grayLevel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedPhase(evt);
            }
        });
        bindingGroup.addBinding(binding);
        
        txtGrayLevel.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                keyeventGenerateActionPerformedPhase(ke);
                if(txtGrayLevel.getText() == null || txtGrayLevel.getText().equals("")){
                    lblGrayLevel.setForeground(Color.red);
                } else {
                    lblGrayLevel.setForeground(Color.black);
                }
            }
        });

        javax.swing.GroupLayout panelButtonPhaseLayout = new javax.swing.GroupLayout(panelButtonPhase);
        panelButtonPhase.setLayout(panelButtonPhaseLayout);
        panelButtonPhaseLayout.setHorizontalGroup(
                panelButtonPhaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonPhaseLayout.createSequentialGroup()
                .addGroup(panelButtonPhaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonPhaseLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonGeneralPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button11LensOnPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonSecondPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE)
                )
                )));
        panelButtonPhaseLayout.setVerticalGroup(
                panelButtonPhaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonPhaseLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(panelButtonPhaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonSecondPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(button11LensOnPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonGeneralPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        
        javax.swing.GroupLayout layoutExp1 = new javax.swing.GroupLayout(panelPhaseExp1);
        panelPhaseExp1.setLayout(layoutExp1);
        layoutExp1.setHorizontalGroup(
            layoutExp1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutExp1.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGrayLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtGrayLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(grayLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layoutExp1.setVerticalGroup(
            layoutExp1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutExp1.createSequentialGroup()
                .addGroup(layoutExp1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layoutExp1.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(grayLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layoutExp1.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layoutExp1.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGrayLevel)
                            .addComponent(txtGrayLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );

    }
    
    public JPanel getPanelPhase() {
        return panelPhaseExp1;
    }
    
    public JPanel getPanelPhaseButton() {
        return panelButtonPhase;
    }
    
    public JTextArea getLogArea() {
        return textAreaLog;
    }
    
    public void setLog(String msg) {
        textAreaLog.append(msg + System.getProperty("line.separator"));
    }
    
    private void buttonGenerateActionPerformedPhase(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedCyllin
        actionTag = "Phase";
        if (parseArguments()) {
            buttonSecondPhase.setEnabled(true);
            button11LensOnPhase.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updatePhaseRetarderParameter(grayLevelValue);
            image.phaseRetarder();
            EduPatternShowOn.updateLensPatternPattern(image, genLogPhase());
            setLog(genLogPhase());
            imageGenerated = true;
        }
    }//GEN-LAST:event_buttonGenerateActionPerformedCyllin

    private void button11LensOnPhaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnPhaseActionPerformed
        actionTag = "Phase";
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updatePhaseRetarderParameter(grayLevelValue);
            image.phaseRetarder();
            EduPatternShowOn.updateLensPatternPattern(image, genLogPhase());
            setLog(genLogPhase());
            imageGenerated = true;

            if (countLenOnPhase % 2 == 0) {
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
                            countLenOnPhase--;
                            button11LensOnPhase.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }
        }

    }//GEN-LAST:event_button11LensOnPhaseActionPerformed
    
      private void buttonSecondGenerateActionPerformedPhase(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedCyllin
        actionTag = "Phase";
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplayPhase--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updatePhaseRetarderParameter(grayLevelValue);
                image.phaseRetarder();
                EduPatternShowOn.updatePatternSecondDisplay(image, genLogPhase());
                setLog(genLogPhase());
                imageGenerated = true;
                if (countSecondDisplayPhase % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }//GEN-LAST:event_buttonSecondGenerateActionPerformedCyllin
    
     private void sliderGenerateActionPerformedPhase(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedCyllin
        actionTag = "Phase";
        if (parseArguments()) {
            buttonSecondPhase.setEnabled(true);
            button11LensOnPhase.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updatePhaseRetarderParameter(grayLevelValue);
            image.phaseRetarder();
            EduPatternShowOn.updateLensPatternPattern(image, genLogPhase());
            setLog(genLogPhase());
            imageGenerated = true;
        }
    }
    private void keyeventGenerateActionPerformedPhase(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedCyllin
        actionTag = "Phase";
        if (parseArguments()) {
            buttonSecondPhase.setEnabled(true);
            button11LensOnPhase.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updatePhaseRetarderParameter(grayLevelValue);
            image.phaseRetarder();
            EduPatternShowOn.updateLensPatternPattern(image, genLogPhase());
            setLog(genLogPhase());
            imageGenerated = true;
        }
    }
    /*
     * Log String
     */
    public void logString(String msg) {
        textAreaLog.append(msg + System.getProperty("line.separator"));
        textAreaLog.setCaretPosition(textAreaLog.getText().length() - 1);
            try {
                BufferedWriter logFileOut = new BufferedWriter(new FileWriter("JDCedukit_ui.log"));
                textAreaLog.write(logFileOut);
                logFileOut.flush();
            } catch (Exception e) {
            }
    }
    

    private String genLogPhase() {
        return String.format(logmessagePhase, Double.toString(grayLevelValue));
    }
    
    private boolean parseArguments() {
        boolean ret = false;
        try {   
            grayLevelValue = Double.valueOf(grayLevel.getValue());
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
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

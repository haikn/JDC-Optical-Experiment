/*
 * @(#)BeamShiftingPanel.java
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
public class BeamShiftingPanel extends OpticsPane {

    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    private double xoff = 0.0, yoff = 0.0;
    private javax.swing.JLabel lblXOff;
    private javax.swing.JLabel lblYOff;
    private DoubleJSlider sliderXOff;
    private DoubleJSlider sliderYOff;
    private javax.swing.JTextField txtXOff;
    private javax.swing.JTextField txtYOff;
    private javax.swing.JButton buttonLensOn;
    private javax.swing.JButton buttonDisplaySecondOn;
    private javax.swing.JButton buttonGeneral;
    static String logmessage = "Beam Shifting: Phy=%s Theta=%s";
    private double deviation = 0, rotation = 0;
    private int countSecondDisplay = 1;
    private int countLenOn = 1;
    private javax.swing.JPanel panelBeamShifting;
    private javax.swing.JTextArea txtAreaLog;

    public BeamShiftingPanel(ResourceBundle labels, BindingGroup bindingGroup, JPanel panelPattern) {
        this.labels = labels;
        this.txtAreaLog = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        initComponents(bindingGroup);
    }

    private void initComponents(BindingGroup bindingGroup) {
        panelBeamShifting = new javax.swing.JPanel();

        lblXOff = new javax.swing.JLabel();
        lblYOff = new javax.swing.JLabel();
        txtXOff = new javax.swing.JTextField();
        txtYOff = new javax.swing.JTextField();
        buttonLensOn = new javax.swing.JButton();
        buttonDisplaySecondOn = new javax.swing.JButton();
        buttonGeneral = new javax.swing.JButton();

        lblXOff.setText(labels.getString("lblXOffBeamShifting"));
        lblYOff.setText(labels.getString("lblYOffBeamShifting"));

        buttonGeneral.setText(labels.getString("btnGenerate"));
        buttonGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformed(evt);
            }
        });

        buttonLensOn.setEnabled(false);
        buttonLensOn.setText(labels.getString("btnLensOn"));
        buttonLensOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnActionPerformed(evt);
                countLenOn++;
                if (countLenOn % 2 == 0) {
                    buttonLensOn.setText(labels.getString("btnLensOff"));
                    panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                    });
                } else {
                    buttonLensOn.setText(labels.getString("btnLensOn"));
                }
            }
        });

        buttonDisplaySecondOn.setEnabled(false);
        buttonDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
        buttonDisplaySecondOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformedCalibration(evt);
                countSecondDisplay++;
                if (countSecondDisplay % 2 == 0) {
                    buttonDisplaySecondOn.setText(labels.getString("btnSecondDisplayOff"));
                } else {
                    buttonDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
                }
            }
        });

        txtXOff.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                if (txtXOff.getText() == null || txtXOff.getText().equals("")) {
                    lblXOff.setForeground(Color.red);
                } else {
                    lblXOff.setForeground(Color.black);
                }
                keyEventGenerateActionPerformed(ke);
            }
        });

        sliderXOff = new DoubleJSlider(-28, 28, 1, 10);
        sliderXOff.setValue(0);
        txtXOff.setText(String.valueOf(sliderXOff.getValue()));

        sliderXOff.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedCalibration(evt);
                DecimalFormat df = new DecimalFormat("0.####");
                txtXOff.setText(df.format(sliderXOff.getScaledValue()));
            }
        });

        txtYOff.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                if (txtYOff.getText() == null || txtYOff.getText().equals("")) {
                    lblYOff.setForeground(Color.red);
                } else {
                    lblYOff.setForeground(Color.black);
                }
                keyEventGenerateActionPerformed(ke);
            }
        });

        sliderYOff = new DoubleJSlider(-1800, 1800, 100, 10);
        sliderYOff.setValue(0);
        txtYOff.setText(String.valueOf(sliderYOff.getValue()));

        sliderYOff.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGenerateActionPerformedCalibration(evt);
                DecimalFormat df = new DecimalFormat("0.####");
                txtYOff.setText(df.format(sliderYOff.getScaledValue()));
            }
        });

        javax.swing.GroupLayout layoutPanel = new javax.swing.GroupLayout(panelBeamShifting);
        panelBeamShifting.setLayout(layoutPanel);
        if (!Utils.isMac()) {
        layoutPanel.setHorizontalGroup(
                layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanel.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanel.createSequentialGroup()
                .addComponent(buttonGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layoutPanel.createSequentialGroup()
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(lblYOff, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addComponent(lblXOff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtYOff)
                .addComponent(txtXOff, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(sliderXOff, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addComponent(sliderYOff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, 0)));
        layoutPanel.setVerticalGroup(
                layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanel.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(sliderXOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblXOff)
                .addComponent(txtXOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanel.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblYOff)
                .addComponent(txtYOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layoutPanel.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(sliderYOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(232, 232, 232)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)));
        } else {
        layoutPanel.setHorizontalGroup(
                layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanel.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanel.createSequentialGroup()
                .addComponent(buttonGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layoutPanel.createSequentialGroup()
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(lblYOff, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addComponent(lblXOff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtYOff)
                .addComponent(txtXOff, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(sliderXOff, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(sliderYOff, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0)));
        layoutPanel.setVerticalGroup(
                layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanel.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(sliderXOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblXOff)
                .addComponent(txtXOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanel.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblYOff)
                .addComponent(txtYOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layoutPanel.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(sliderYOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(262, 262, 262)
                .addGroup(layoutPanel.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)));    
        }
    }

    public JPanel getPanel() {
        return panelBeamShifting;
    }

    public JTextArea getLogArea() {
        return txtAreaLog;
    }

    private void buttonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformed
        if (parseArguments()) {
            buttonLensOn.setEnabled(true);
            buttonDisplaySecondOn.setEnabled(true);
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateBeamShiftingParameter(deviation, rotation);
            image.paintBeamShifting();
            EduPatternShowOn.updatePattern(image, genLogs());
            imageGenerated = true;
        }
    }//GEN-LAST:event_buttonGenerateActionPerformed

    private void button11LensOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnActionPerformed
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateBeamShiftingParameter(deviation, rotation);
            image.paintBeamShifting();
            EduPatternShowOn.updatePattern(image, genLogs());
            imageGenerated = true;

            if (countLenOn % 2 == 0) {
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
                    magFrameLenon.setLocation(new Point(505, 420));
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
                        countLenOn--;
                        buttonLensOn.setText(labels.getString("btnLensOn"));
                        magFrameLenon.dispose();
                    }
                });
            }
        }

    }//GEN-LAST:event_button11LensOnActionPerformed

    private void buttonSecondGenerateActionPerformedCalibration(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedCyllin
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplay--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateBeamShiftingParameter(deviation, rotation);
                image.paintBeamShifting();
                EduPatternShowOn.updatePatternSecondDisplay(image, genLogs());
                //EduPatternTest.updateLensPatternPattern(image, genLog());
                imageGenerated = true;
                if (countSecondDisplay % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }//GEN-LAST:event_buttonSecondGenerateActionPerformedCyllin

    private void sliderGenerateActionPerformedCalibration(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGenerateActionPerformedCyllin
        if (parseArguments()) {
            buttonLensOn.setEnabled(true);
            buttonDisplaySecondOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateBeamShiftingParameter(deviation, rotation);
            image.paintBeamShifting();
            EduPatternShowOn.updatePattern(image, genLogs());
            setLog(genLogs());
            imageGenerated = true;
        }
    }//GEN-LAST:event_sliderGenerateActionPerformedCyllin

    private void keyEventGenerateActionPerformed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyEventGenerateActionPerformed
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateBeamShiftingParameter(deviation, rotation);
            image.paintBeamShifting();
            EduPatternShowOn.updatePattern(image, genLogs());
            setLog(genLogs());
            imageGenerated = true;
        }
    }//GEN-LAST:event_keyEventGenerateActionPerformed

    private String genLogs() {
        return String.format(logmessage, Double.toString(deviation), Double.toString(rotation));
    }

    private boolean parseArguments() {
        boolean ret = false;
        try {
            xoff = Double.valueOf(txtXOff.getText());
            yoff = Double.valueOf(txtYOff.getText());
            this.deviation = xoff;
            this.rotation = yoff;
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void setLog(String msg) {
        txtAreaLog.append(msg + System.getProperty("line.separator"));
    }
}

/*
 * @(#)AmplitudePanel.java
 *
 * Copyright (c) 2013 JASPER DISPLAY, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * JASPER DISPLAY PROPRIETARY:  Distribution of this source code
 * without permission from the copyright holder is strictly forbidden.
 */
package com.jasper.ui.panel.amplitude;

import com.jasper.core.OpticsPane;
import java.awt.GraphicsDevice;
import javax.swing.JOptionPane;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import com.jasper.core.PatternImage;
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
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;

/**
 *
 * @author sonnv
 */
public class AmplitudePanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    private javax.swing.JFileChooser openFile;
    private javax.swing.JButton buttonOpenFile;
    private javax.swing.JLabel lblPleaseSelect;
    
    private javax.swing.JButton buttonLensOn;
    private javax.swing.JButton buttonDisplaySecondOn;
    private javax.swing.JButton buttonGeneral;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelButton;
    private javax.swing.JTextArea txtLogArea;
    
    private static BufferedImage buffImages;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    
    private int countLenOn = 1;
    private int countSecondDisplay = 1;
    
    static String logMessage = "Amplitude : image=%s";
    
    public AmplitudePanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        this.txtLogArea = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panelButton = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        openFile = new javax.swing.JFileChooser();
        buttonOpenFile = new javax.swing.JButton();
        lblPleaseSelect = new javax.swing.JLabel();
        buttonDisplaySecondOn = new javax.swing.JButton();
        buttonLensOn = new javax.swing.JButton();
        buttonGeneral = new javax.swing.JButton();
        buttonOpenFile.setText("Browse...");
        buttonOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        
        lblPleaseSelect.setText(labels.getString("lblSelectToImport"));

        buttonDisplaySecondOn.setEnabled(false);
        buttonDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
        buttonDisplaySecondOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImages != null) {
                    buttonSecondGenerateActionPerformed(evt);
                    countSecondDisplay++;
                    if (countSecondDisplay % 2 == 0) {
                        buttonDisplaySecondOn.setText(labels.getString("btnSecondDisplayOff"));
                    } else {
                        buttonDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonLensOn.setEnabled(false);
        buttonLensOn.setText(labels.getString("btnLensOn"));
        buttonLensOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImages != null) {
                    buttonLensOnActionPerformed(evt);
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
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonGeneral.setText(labels.getString("btnGenerate"));
        buttonGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImages != null) {
                    buttonGenerateActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        javax.swing.GroupLayout panelButtonExp2Layout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonExp2Layout);
        panelButtonExp2Layout.setHorizontalGroup(
                panelButtonExp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonExp2Layout.createSequentialGroup()
                .addGroup(panelButtonExp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonExp2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE)
                )
                )));
        panelButtonExp2Layout.setVerticalGroup(
                panelButtonExp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonExp2Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addGroup(panelButtonExp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        
        javax.swing.GroupLayout exp2PhotoLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(exp2PhotoLayout);
        exp2PhotoLayout.setHorizontalGroup(
            exp2PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exp2PhotoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(exp2PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    //.addComponent(buttonGeneralExp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonOpenFile, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addGroup(exp2PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(exp2PhotoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblPleaseSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                )
                .addContainerGap(150, Short.MAX_VALUE))
        );
        exp2PhotoLayout.setVerticalGroup(
            exp2PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exp2PhotoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(exp2PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPleaseSelect))
                .addGap(12, 12, 12))
        );
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
    
    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {
        int returnVal = openFile.showOpenDialog(this);
        if (returnVal == openFile.APPROVE_OPTION) {
            File file = openFile.getSelectedFile();
            String ext = "";
            String extension = file.getName();
            extension = extension.toLowerCase();
            if (extension.contains("jpg")) {
                ext = ".jpg";
            }
            if (extension.contains("png")) {
                ext = ".png";
            }
            if (extension.contains("gif")) {
                ext = ".gif";
            }
            if (extension.contains("wbmp")) {
                ext = ".wbmp";
            }
            if (extension.contains("jpeg")) {
                ext = ".jpeg";
            }
            if (extension.contains("bmp")) {
                ext = ".bmp";
            }
            if (ext.equals("")) {
                JOptionPane.showMessageDialog(null, "Formats incorrect!", "Failure", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    buffImages = ImageIO.read(new File(file.getAbsolutePath()));
                    String fileName = file.getAbsolutePath();
                    PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                    image.signalPhoto(buffImages);
                    EduPatternShowOn.updateLensPatternPattern(image, fileName);
                    setLog(fileName);
                    imageGenerated = true;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    //System.out.println("problem accessing file" + file.getAbsolutePath());
                }
            }
        } else {
            //System.out.println("File access cancelled by user.");
        }

    }
                
    private void buttonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedCyllin
        buttonDisplaySecondOn.setEnabled(true);
        buttonLensOn.setEnabled(true);

        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        image.signalPhoto(buffImages);
        EduPatternShowOn.updateLensPatternPattern(image, "");
        setLog(genLog());
        imageGenerated = true;
    }

    private void buttonLensOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnProcessingPhotoActionPerformed
        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        image.signalPhoto(buffImages);
        EduPatternShowOn.updateLensPatternPattern(image, "");
        //setLog(genLog());
        imageGenerated = true;

        if (countLenOn % 2 == 0) {
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
                        countLenOn--;
                        buttonLensOn.setText(labels.getString("btnLensOn"));
                        magFrameLenon.dispose();
                }
            });
        }
    }//GEN-LAST:event_button11LensOnProcessingPhotoActionPerformed

    private void buttonSecondGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedCyllin
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = env.getScreenDevices();
        if (devices.length == 1) {
            countSecondDisplay--;
            JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            // image.updateParameterDrawSignalProcessing(processing_widthX, processing_widthY, processing_heightX, processing_heightY, processing_positionX, processing_positionY, processing_rotation, processing_grayLevel);
            image.signalPhoto(buffImages);
            EduPatternShowOn.updateLensPatternPattern(image, "");
            //setLog(genLog());
            //EduPatternTest.updateLensPatternPattern(image, genLog());
            imageGenerated = true;
            if (countSecondDisplay % 2 == 0) {
                patternFrameDoubleClick.dispose();
                patternFrame.dispose();
            }
        }
    }
    
     private String genLog() {
        return String.format(logMessage);
    }
    
    public void setLog(String msg) {
        txtLogArea.append(msg + System.getProperty("line.separator"));
    }

    @Override
    public void updatePatternScreen() {
    }

    @Override
    public void updateRegenerate() {
    }
}

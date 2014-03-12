/*
 * @(#)SignalPhotoPanel.java
 *
 * Copyright (c) 2013 JASPER DISPLAY, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * JASPER DISPLAY PROPRIETARY:  Distribution of this source code
 * without permission from the copyright holder is strictly forbidden.
 */
package com.jasper.ui.panel.signalprocessing;

import com.jasper.core.OpticsPane;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JOptionPane;
import com.jasper.core.PatternImage;
import com.jasper.ui.EduLensOn11;
import com.jasper.ui.EduPatternJPanel;
import com.jasper.ui.EduPatternShowOn;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jdesktop.beansbinding.BindingGroup;

import static com.jasper.ui.EduPatternShowOn.patternFrameDoubleClick;
import static com.jasper.ui.EduPatternShowOn.patternFrame;
import javax.swing.JTextArea;
/**
 *
 * @author sonnv
 */
public class SignalPhotoPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    
    private javax.swing.JButton button11LensOnPhoto;
    private javax.swing.JButton buttonGeneralPhoto;
    private javax.swing.JButton buttonSecondPhoto;
    private javax.swing.JLabel lblPleaseSelectPhoto;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelButton;
     
    private int countSecondDisplayPhoto = 1;
    private int countLenOnPhoto = 1;
    private static BufferedImage buffImages = null;
    private javax.swing.JFileChooser openFile;
    private javax.swing.JButton buttonOpenFile;
    private javax.swing.JTextArea txtLogArea;
    
    public SignalPhotoPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
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
        lblPleaseSelectPhoto = new javax.swing.JLabel();
        buttonSecondPhoto = new javax.swing.JButton();
        button11LensOnPhoto = new javax.swing.JButton();
        buttonGeneralPhoto = new javax.swing.JButton();
        buttonOpenFile.setText("Browse...");
        buttonOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        lblPleaseSelectPhoto.setText("Select the file to import.");

        buttonSecondPhoto.setEnabled(false);
        buttonSecondPhoto.setText(labels.getString("btnSecondDisplayOn"));
        buttonSecondPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImages != null) {
                    buttonSecondGenerateActionPerformedProcessingPhoto(evt);
                    countSecondDisplayPhoto++;
                    if (countSecondDisplayPhoto % 2 == 0) {
                        buttonSecondPhoto.setText(labels.getString("btnSecondDisplayOff"));
                    } else {
                        buttonSecondPhoto.setText(labels.getString("btnSecondDisplayOn"));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button11LensOnPhoto.setEnabled(false);
        button11LensOnPhoto.setText(labels.getString("btnLensOn"));
        button11LensOnPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImages != null) {
                    button11LensOnProcessingPhotoActionPerformed(evt);
                    countLenOnPhoto++;
                    if (countLenOnPhoto % 2 == 0) {
                        button11LensOnPhoto.setText(labels.getString("btnLensOff"));
                        panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                    } else {
                        button11LensOnPhoto.setText(labels.getString("btnLensOn"));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonGeneralPhoto.setText(labels.getString("btnGenerate"));
        buttonGeneralPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImages != null) {
                    buttonGenerateActionPerformedProcessingPhoto(evt);
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        javax.swing.GroupLayout panelButtonSignalPhotoLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonSignalPhotoLayout);
        panelButtonSignalPhotoLayout.setHorizontalGroup(
                panelButtonSignalPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonSignalPhotoLayout.createSequentialGroup()
                .addGroup(panelButtonSignalPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonSignalPhotoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonGeneralPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button11LensOnPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonSecondPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE)
                )
                )));
        panelButtonSignalPhotoLayout.setVerticalGroup(
                panelButtonSignalPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonSignalPhotoLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(panelButtonSignalPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonSecondPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(button11LensOnPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonGeneralPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        
        javax.swing.GroupLayout signalPhotoLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(signalPhotoLayout);
        signalPhotoLayout.setHorizontalGroup(
            signalPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signalPhotoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(signalPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonOpenFile, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addGroup(signalPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signalPhotoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblPleaseSelectPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                ))
        );
        signalPhotoLayout.setVerticalGroup(
            signalPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signalPhotoLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(signalPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPleaseSelectPhoto)).addGap(0, 0, 0))
        );

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
    
     private void buttonGenerateActionPerformedProcessingPhoto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedCyllin
        actionTag = "ProcessingPhoto";
        buttonSecondPhoto.setEnabled(true);
        button11LensOnPhoto.setEnabled(true);

        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        image.signalPhoto(buffImages);
        EduPatternShowOn.updateLensPatternPattern(image, "");
        //setLog(genLogSignalPhoto());
        imageGenerated = true;
    }

    private void button11LensOnProcessingPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnProcessingPhotoActionPerformed
        actionTag = "ProcessingPhoto";
        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        image.signalPhoto(buffImages);
        EduPatternShowOn.updateLensPatternPattern(image, "");
        //setLog(genLogSignalPhoto());
        imageGenerated = true;

        if (countLenOnPhoto % 2 == 0) {
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
            magFrameLenon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            magFrameLenon.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                        countLenOnPhoto--;
                        button11LensOnPhoto.setText(labels.getString("btnLensOn"));
                        magFrameLenon.dispose();
                }
            });
        }

    }//GEN-LAST:event_button11LensOnProcessingPhotoActionPerformed

    private void buttonSecondGenerateActionPerformedProcessingPhoto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedCyllin
        actionTag = "ProcessingPhoto";
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = env.getScreenDevices();
        if (devices.length == 1) {
            countSecondDisplayPhoto--;
            JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.signalPhoto(buffImages);
            EduPatternShowOn.updateLensPatternPattern(image, "");
            //setLog(genLogSignalPhoto());
            imageGenerated = true;
            if (countSecondDisplayPhoto % 2 == 0) {
                patternFrameDoubleClick.dispose();
                patternFrame.dispose();
            }
        }
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
                    String filePath = file.getAbsolutePath();
                    PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                    image.signalPhoto(buffImages);
                    EduPatternShowOn.updateLensPatternPattern(image, "");
                    setLog(filePath);
                    imageGenerated = true;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void setLog(String msg) {
        txtLogArea.append(msg + System.getProperty("line.separator"));
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

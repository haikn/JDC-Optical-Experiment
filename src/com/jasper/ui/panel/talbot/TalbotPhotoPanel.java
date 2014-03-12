/*
 * @(#)TalbotPhotoPanel.java
 *
 * Copyright (c) 2013 JASPER DISPLAY, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * JASPER DISPLAY PROPRIETARY:  Distribution of this source code
 * without permission from the copyright holder is strictly forbidden.
 */
package com.jasper.ui.panel.talbot;

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
import com.jasper.utils.Constant;
import java.awt.Robot;
import javax.swing.JTextArea;

/**
 *
 * @author sonnv
 */
public class TalbotPhotoPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    private double xoff = 0.0, yoff = 0.0, focal = 0.0;
    
    private javax.swing.JButton buttonOpenFileTalbotPhoto;
    private javax.swing.JLabel lblPleaseSelectTalbotPhoto;
    private javax.swing.JButton buttonSecondTalbotPhoto;
    private javax.swing.JButton button11LensOnTalbotPhoto;
    private javax.swing.JButton buttonGeneralTalbotPhoto;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelButton;
    private javax.swing.JTextArea txtLogArea;
    static String logmessageTalbotPhoto = "Talbot photo: widht=%s height=%s";
    private static BufferedImage buffImagesTalbotPhoto = null;

    private javax.swing.JFileChooser openFile;
    private int countLenOnTalbotPhoto = 1;
    private int countSecondDisplayTalbotPhoto = 1;
    
     public TalbotPhotoPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        this.txtLogArea = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panel = new javax.swing.JPanel();
        panelButton = new javax.swing.JPanel();
        
        openFile = new javax.swing.JFileChooser();
        buttonOpenFileTalbotPhoto = new javax.swing.JButton();
        lblPleaseSelectTalbotPhoto = new javax.swing.JLabel();
        buttonSecondTalbotPhoto = new javax.swing.JButton();
        button11LensOnTalbotPhoto = new javax.swing.JButton();
        buttonGeneralTalbotPhoto = new javax.swing.JButton();
        buttonOpenFileTalbotPhoto.setText("Browse...");
        buttonOpenFileTalbotPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_openFileActionPerformedTalbotPhoto(evt);
            }
        });
        lblPleaseSelectTalbotPhoto.setText("Select the file to import.");

        buttonSecondTalbotPhoto.setEnabled(false);
        buttonSecondTalbotPhoto.setText(labels.getString("btnSecondDisplayOn"));
        buttonSecondTalbotPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImagesTalbotPhoto != null) {
                    buttonSecondGenerateActionPerformedTalbotPhoto(evt);
                    countSecondDisplayTalbotPhoto++;
                    if (countSecondDisplayTalbotPhoto % 2 == 0) {
                        buttonSecondTalbotPhoto.setText(labels.getString("btnSecondDisplayOff"));
                    } else {
                        buttonSecondTalbotPhoto.setText(labels.getString("btnSecondDisplayOn"));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button11LensOnTalbotPhoto.setEnabled(false);
        button11LensOnTalbotPhoto.setText(labels.getString("btnLensOn"));
        button11LensOnTalbotPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImagesTalbotPhoto != null) {
                    button11LensOnTalbotPhotoActionPerformed(evt);
                    countLenOnTalbotPhoto++;
                    if (countLenOnTalbotPhoto % 2 == 0) {
                        button11LensOnTalbotPhoto.setText(labels.getString("btnLensOff"));
                        panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                        });
                    } else {
                        button11LensOnTalbotPhoto.setText(labels.getString("btnLensOn"));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", " Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonGeneralTalbotPhoto.setText(labels.getString("btnGenerate"));
        buttonGeneralTalbotPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImagesTalbotPhoto != null) {
                    buttonGenerateActionPerformedTalbotPhoto(evt);
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        javax.swing.GroupLayout panelButtonTalbotPhotoLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonTalbotPhotoLayout);
        panelButtonTalbotPhotoLayout.setHorizontalGroup(
                panelButtonTalbotPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonTalbotPhotoLayout.createSequentialGroup()
                .addGroup(panelButtonTalbotPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonTalbotPhotoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonGeneralTalbotPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button11LensOnTalbotPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonSecondTalbotPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE)
                )
                )));
        panelButtonTalbotPhotoLayout.setVerticalGroup(
                panelButtonTalbotPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonTalbotPhotoLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(panelButtonTalbotPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonSecondTalbotPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(button11LensOnTalbotPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonGeneralTalbotPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        
        javax.swing.GroupLayout signalTalbotPhotoLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(signalTalbotPhotoLayout);
        signalTalbotPhotoLayout.setHorizontalGroup(
                signalTalbotPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(signalTalbotPhotoLayout.createSequentialGroup()
                .addContainerGap()
                .addGap(10, 10, 10)
                .addGroup(signalTalbotPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(signalTalbotPhotoLayout.createSequentialGroup()
                .addComponent(buttonOpenFileTalbotPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(lblPleaseSelectTalbotPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288)
                ))));
        signalTalbotPhotoLayout.setVerticalGroup(
                signalTalbotPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signalTalbotPhotoLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(signalTalbotPhotoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(signalTalbotPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonOpenFileTalbotPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblPleaseSelectTalbotPhoto))
                .addGap(0, 0, 0)
                )));
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
    
     private void b_openFileActionPerformedTalbotPhoto(java.awt.event.ActionEvent evt) {
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
                    buffImagesTalbotPhoto = ImageIO.read(new File(file.getAbsolutePath()));
                    //String ext = File.probeContentType(file.getAbsolutePath());
                    PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                    //  image.updatePhaseRetarderParameter(zoom, grayLevel);
                    image.paintTalbotPhoto(buffImagesTalbotPhoto);
                    EduPatternShowOn.updateLensPatternPattern(image, genLogTalbotPhoto());
                    setLog(genLogTalbotPhoto());
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
     
      private void buttonSecondGenerateActionPerformedTalbotPhoto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedTalbotPhoto
        actionTag = "TalbotPhoto";
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplayTalbotPhoto--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                // image.updateParameterDrawSignalProcessing(processing_widthX, processing_widthY, processing_heightX, processing_heightY, processing_positionX, processing_positionY, processing_rotation, processing_grayLevel);
                image.paintTalbotPhoto(buffImagesTalbotPhoto);
                EduPatternShowOn.updateLensPatternPattern(image, genLogTalbotPhoto());
                setLog(genLogTalbotPhoto());
                //EduPatternTest.updateLensPatternPattern(image, genLog());
                imageGenerated = true;
                if (countSecondDisplayTalbotPhoto% 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }
      
      private void buttonGenerateActionPerformedTalbotPhoto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedTalbotPhoto
        actionTag = "TalbotPhoto";
        if (parseArguments()) {
             button11LensOnTalbotPhoto.setEnabled(true);
            buttonSecondTalbotPhoto.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.paintTalbotPhoto(buffImagesTalbotPhoto);
            EduPatternShowOn.updateLensPatternPattern(image, genLogTalbotPhoto());
            setLog(genLogTalbotPhoto());
            imageGenerated = true;
        }

    }

    private void button11LensOnTalbotPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnTalbotPhotoActionPerformed
        actionTag = "TalbotPhoto";
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.paintTalbotPhoto(buffImagesTalbotPhoto);
            EduPatternShowOn.updateLensPatternPattern(image, genLogTalbotPhoto());
            setLog(genLogTalbotPhoto());
            imageGenerated = true;

            if (countLenOnTalbotPhoto % 2 == 0) {
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
                            countLenOnTalbotPhoto--;
                            button11LensOnTalbotPhoto.setText(labels.getString("btnLensOn"));
                            magFrameLenon.dispose();
                    }
                });
            }

        }

    }//GEN-LAST:event_button11LensOnTalbotPhotoActionPerformed
    
     
     private String genLogTalbotPhoto() {
        return String.format(logmessageTalbotPhoto, Double.toString(buffImagesTalbotPhoto.getWidth()), Double.toString(buffImagesTalbotPhoto.getHeight()));
    }
    
     private boolean parseArguments() {
        boolean ret = false;
        try {          
           
           
            ret = true;
            
        } catch (Exception e) {
        }
        return ret;
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

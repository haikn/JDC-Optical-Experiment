/*
 * @(#)StaticPanel.java
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
import com.jasper.utils.Utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author sonnv
 */
public class DynamicPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    private javax.swing.JButton btnBrowse;
    private javax.swing.JFileChooser openFile;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton buttonLensOn;
    private javax.swing.JButton buttonDisplaySecondOn;
    private javax.swing.JLabel lblSelect;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel lblFilePath;
    private javax.swing.JTextArea txtBox;
    private String getText;
    
    private static BufferedImage buffImages;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    
    private int countLenOn = 1;
    private int countSecondDisplay = 1;
    
    static String logMessage = "Amplitude : image=%s";
    
    public DynamicPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        this.panelPattern = panelPattern;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panel = new javax.swing.JPanel();
        btnBrowse = new javax.swing.JButton();
        openFile = new javax.swing.JFileChooser();
        lblSelect = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JButton();
        buttonLensOn = new javax.swing.JButton();
        buttonDisplaySecondOn = new javax.swing.JButton();
        
        lblFilePath = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        txtBox = new javax.swing.JTextArea();
        getText = Utils.readFile(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_DYNAMIC);
        
        txtBox.setColumns(10);
        txtBox.setRows(4);
        txtBox.setText(getText);
        scrollPane.setViewportView(txtBox);
        lblFilePath.setText(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_DYNAMIC);
        lblFilePath.setForeground(Color.blue);
        lblFilePath.setFont(new Font("Arial", Font.PLAIN , 10));
        
        btnBrowse.setText("Browse...");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        
        lblSelect.setText(labels.getString("lblSelectToImport"));

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
        btnGenerate.setText(labels.getString("btnGenerate"));
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImages != null) {
                    buttonGenerateActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                    .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                )
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSelect)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFilePath)
                        .addGap(3, 3, 3)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }
    
    public JPanel getPanel(){
        return panel;
    }
    
    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {
        String args[] = {"", "" };
        DynamicHolography obj = new DynamicHolography();
        obj.main(args);
//        int returnVal = openFile.showOpenDialog(this);
//        if (returnVal == openFile.APPROVE_OPTION) {
//            File file = openFile.getSelectedFile();
//            String ext = "";
//            String extension = file.getName();
//            extension = extension.toLowerCase();
//            if (extension.contains("jpg")) {
//                ext = ".jpg";
//            }
//            if (extension.contains("png")) {
//                ext = ".png";
//            }
//            if (extension.contains("gif")) {
//                ext = ".gif";
//            }
//            if (extension.contains("wbmp")) {
//                ext = ".wbmp";
//            }
//            if (extension.contains("jpeg")) {
//                ext = ".jpeg";
//            }
//            if (extension.contains("bmp")) {
//                ext = ".bmp";
//            }
//            if (ext.equals("")) {
//                JOptionPane.showMessageDialog(null, "Formats incorrect!", "Failure", JOptionPane.ERROR_MESSAGE);
//            } else {
//                try {
//                    buffImages = ImageIO.read(new File(file.getAbsolutePath()));
//                    String fileName = file.getName();
//                    PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
//                    image.paintDynamic(buffImages);
//                    EduPatternShowOn.updateLensPatternPattern(image, "");
//                    setLog(Constant.TEXT_FORMAT_CGH + Constant.LOG_NAME + fileName + "\n"
//                            + Constant.LOG_DATE + Utils.dateNow() + "\n"
//                            + Constant.TEXT_FORMAT_CGH );
//                    imageGenerated = true;
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    //System.out.println("problem accessing file" + file.getAbsolutePath());
//                }
//            }
//        } else {
//            //System.out.println("File access cancelled by user.");
//        }

    }
                
    private void buttonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedCyllin
        buttonDisplaySecondOn.setEnabled(true);
        buttonLensOn.setEnabled(true);

        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        image.paintDynamic(buffImages);
        EduPatternShowOn.updateLensPatternPattern(image, "");
        setLog(genLog());
        imageGenerated = true;
    }

    private void buttonLensOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnProcessingPhotoActionPerformed
        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        image.paintDynamic(buffImages);
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
            magFrameLenon = new JFrame("1:1 Lens On");
            URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
            Toolkit kit = Toolkit.getDefaultToolkit();
            Image img = kit.createImage(url);
            magFrameLenon.setIconImage(img);

            //EduLensOn11 mag = new EduLensOn11(panelPattern, new Dimension(120, 120), 2.0);
            EduLensOn11 mag = new EduLensOn11(panelPattern, new Dimension(120, 120));
            magFrameLenon.getContentPane().add(mag);
            magFrameLenon.pack();
            magFrameLenon.setLocation(new Point(505, 420));
            magFrameLenon.setResizable(false);
            magFrameLenon.setVisible(true);
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
            image.paintDynamic(buffImages);
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
        //txtLogArea.append(msg + System.getProperty("line.separator"));
    }

    @Override
    public void updatePatternScreen() {
    }

    @Override
    public void updateRegenerate() {
    }
}

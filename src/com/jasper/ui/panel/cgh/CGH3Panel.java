/*
 * @(#)CGH3Panel.java
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
package com.jasper.ui.panel.cgh;

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

import static com.jasper.ui.EduPatternShowOn.patternFrame;
import static com.jasper.ui.EduPatternShowOn.patternFrameDoubleClick;
import com.jasper.utils.Constant;
import com.jasper.utils.Utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author sonnv
 */
public class CGH3Panel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    
    private javax.swing.JFileChooser openFile;
    private javax.swing.JButton buttonLensOn;
    private javax.swing.JButton buttonDisplaySecondOn;
    private javax.swing.JButton buttonGeneral;
    private javax.swing.JButton buttonOpenFile;
    private javax.swing.JLabel lblPleaseSelect;
    private javax.swing.JLabel lblFilePath;
    private File fileCGH;
    // Textbox CGH
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea txtCGH;
    private String getText;
    
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelButton;
    
    private static BufferedImage buffImages;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    
    private int countLenOn = 1;
    private int countSecondDisplay = 1;
    
    static String logMessage = "Amplitude : image=%s";
    
    public CGH3Panel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        this.panelPattern = panelPattern;
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panelButton = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        openFile = new javax.swing.JFileChooser();
        buttonOpenFile = new javax.swing.JButton();
        buttonGeneral = new javax.swing.JButton();
        buttonLensOn = new javax.swing.JButton();
        buttonDisplaySecondOn = new javax.swing.JButton();
        lblPleaseSelect = new javax.swing.JLabel();
        lblFilePath = new javax.swing.JLabel();
        
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
        buttonLensOn.setEnabled(false);
        buttonLensOn.setText(labels.getString("btnLensOn"));
        buttonLensOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImages != null) {
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
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonDisplaySecondOn.setEnabled(false);
        buttonDisplaySecondOn.setText(labels.getString("btnSecondDisplayOn"));
        buttonDisplaySecondOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (buffImages != null) {
                    buttonSecondActionPerformed(evt);
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
        
        buttonOpenFile.setText("Browse...");
        buttonOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        lblPleaseSelect.setText("Select the file to import.");
        
        lblFilePath.setText(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH3);
        lblFilePath.setForeground(Color.blue);
        lblFilePath.setFont(new Font("Arial", Font.PLAIN , 10));
        
        panelButton = new javax.swing.JPanel();
        javax.swing.GroupLayout panelButtonCGH3Layout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonCGH3Layout);
        panelButtonCGH3Layout.setHorizontalGroup(
                panelButtonCGH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonCGH3Layout.createSequentialGroup()
                .addGroup(panelButtonCGH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonCGH3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE)
                )
                )));
        panelButtonCGH3Layout.setVerticalGroup(
                panelButtonCGH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelButtonCGH3Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(panelButtonCGH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                ));
        
        scrollPane = new javax.swing.JScrollPane();
        txtCGH = new javax.swing.JTextArea();
        getText = Utils.readFile(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH3) ;
        
        txtCGH.setColumns(10);
        txtCGH.setRows(4);
        txtCGH.setText(getText);
        scrollPane.setViewportView(txtCGH);
        
        javax.swing.GroupLayout CGH3Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(CGH3Layout);
        CGH3Layout.setHorizontalGroup(
            CGH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CGH3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(buttonOpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblPleaseSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CGH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        CGH3Layout.setVerticalGroup(
            CGH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CGH3Layout.createSequentialGroup()
                .addGroup(CGH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CGH3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(CGH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonOpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPleaseSelect)))
                    .addGroup(CGH3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblFilePath)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10))))
                    );
    }
    
    public JPanel getPanel(){
        return panel;
    }
    
    public JPanel getPanelButton(){
        return panelButton;
    }
    
    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {
        int returnVal = openFile.showOpenDialog(this);
        if (returnVal == openFile.APPROVE_OPTION) {
            fileCGH = openFile.getSelectedFile();
            String ext = "";
            String extension = fileCGH.getName();
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
                    buffImages = ImageIO.read(new File(fileCGH.getAbsolutePath()));
                    String fileName = fileCGH.getName();
                    PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                    image.paintCGH1(buffImages, fileCGH);
                    EduPatternShowOn.updateLensPatternPattern(image, "");
                    setLog(Constant.TEXT_FORMAT_CGH + Constant.LOG_NAME + fileName + "\n"
                            + Constant.LOG_DATE + Utils.dateNow() + "\n"
                            + Constant.TEXT_FORMAT_CGH );
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
    
    private void buttonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondActionPerformed1
        buttonLensOn.setEnabled(true);
        buttonDisplaySecondOn.setEnabled(true);

        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        image.paintCGH1(buffImages, fileCGH);
        EduPatternShowOn.updateLensPatternPattern(image, "");
        imageGenerated = true;
    }

    private void button11LensOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondActionPerformed1
        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        image.paintCGH1(buffImages, fileCGH);
        EduPatternShowOn.updateLensPatternPattern(image, "");
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

    }//GEN-LAST:event_button11LensOnTalbotPhotoActionPerformed

    private void buttonSecondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondActionPerformed10
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = env.getScreenDevices();
        if (devices.length == 1) {
            countSecondDisplay--;
            JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.paintCGH1(buffImages, fileCGH);
            EduPatternShowOn.updatePatternSecondDisplay(image, "");
            imageGenerated = true;
            if (countSecondDisplay % 2 == 0) {
                patternFrameDoubleClick.dispose();
                patternFrame.dispose();
            }
        }
    }
    
    public void setLog(String msg) {
        String filePath;
        try {
            txtCGH.append(msg);
            txtCGH.setCaretPosition(txtCGH.getText().length() - 1);
            filePath = Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH3;
            // Check file logs exists
            if(Utils.checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH3)) {
                Utils.writeFile(filePath, msg, false);
            } else {
                Utils.createDirectory(File.separator + Constant.FILE_NAME_CGH3);
                Utils.writeFile(filePath, msg, false);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void updatePatternScreen() {
    }

    @Override
    public void updateRegenerate() {
    }
}

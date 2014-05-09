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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdesktop.beansbinding.Binding;

/**
 *
 * @author sonnv
 */
public class StaticPanel extends OpticsPane{
    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private String actionTag = "Len";
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton buttonLensOn;
    private javax.swing.JButton buttonDisplaySecondOn;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JLabel lblSelectFile;
    private javax.swing.JLabel lblXOff;
    private javax.swing.JLabel lblYOff;
    private javax.swing.JSlider sliderXOff;
    private javax.swing.JSlider sliderYOff;
    private javax.swing.JTextField txtXOff;
    private javax.swing.JTextField txtYOff;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel lblFilePath;
    private javax.swing.JTextArea txtBox;
    private String getText;
    private String filePath;
    private javax.swing.JTextArea txtLogArea;
    
    private static BufferedImage buffImages;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    
    private int countLenOn = 1;
    private int countSecondDisplay = 1;
    private double phy = 0.0, theta = 0.0;
    
    static String logMessage = "Static : Phy=%s Theta=%s";
    
    public StaticPanel(ResourceBundle labels, BindingGroup bindingGroup,JPanel panelPattern) {
        this.labels = labels;
        this.panelPattern = panelPattern;
        this.txtLogArea = new javax.swing.JTextArea();
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        
        initComponents(bindingGroup);
    }
    
    private void initComponents(BindingGroup bindingGroup) {
        panel = new javax.swing.JPanel();
        lblXOff = new javax.swing.JLabel();
        txtXOff = new javax.swing.JTextField();
        sliderXOff = new javax.swing.JSlider();
        lblYOff = new javax.swing.JLabel();
        txtYOff = new javax.swing.JTextField();
        sliderYOff = new javax.swing.JSlider();
        btnGenerate = new javax.swing.JButton();
        buttonLensOn = new javax.swing.JButton();
        buttonDisplaySecondOn = new javax.swing.JButton();
        btnBrowse = new javax.swing.JButton();
        lblSelectFile = new javax.swing.JLabel();
        
        lblFilePath = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        txtBox = new javax.swing.JTextArea();
        getText = Utils.readFile(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_STATIC);
        
        txtBox.setColumns(10);
        txtBox.setRows(4);
        txtBox.setText(getText);
        scrollPane.setViewportView(txtBox);
        lblFilePath.setText(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_STATIC);
        lblFilePath.setForeground(Color.blue);
        lblFilePath.setFont(new Font("Arial", Font.PLAIN , 10));
        
        lblYOff.setText("Theta");
        lblXOff.setText("Phy");
        Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sliderXOff, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtXOff, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sliderYOff, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtYOff, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sliderXOff.setMaximum(100);
        sliderXOff.setValue(0);
        sliderXOff.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                if (buffImages != null) {
                    sliderGenerateActionPerformedStatic(evt);
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        sliderYOff.setMaximum(100);
        sliderYOff.setValue(0);
        sliderYOff.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                if (buffImages != null) {
                    sliderGenerateActionPerformedStatic(evt);
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        txtXOff.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if (buffImages != null) {
                    keyEventGenerateActionPerformedStatic(ke);
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        txtYOff.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                if (buffImages != null) {
                    keyEventGenerateActionPerformedStatic(ke);
                } else {
                    JOptionPane.showMessageDialog(null, "Please import an images file!", "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnBrowse.setText("Browse...");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        lblSelectFile.setText(labels.getString("lblSelectToImport"));
        
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
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblYOff, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(lblXOff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtXOff)
                            .addComponent(txtYOff, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sliderXOff, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                            .addComponent(sliderYOff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelectFile))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliderXOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblXOff)
                        .addComponent(txtXOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblYOff)
                        .addComponent(txtYOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sliderYOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(130, 130, 130)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );
    }
    
    public JPanel getPanel(){
        return panel;
    }
    
    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {
        if (parseArguments()) {
            FileNameExtensionFilter filter = 
                    new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg", "wbmp", "bmp");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    String fileName = fileChooser.getSelectedFile().getName();
                    filePath = fileChooser.getSelectedFile().getPath();
                    String type = fileName.substring(fileName.length() - 4, fileName.length());
                    type = type.toLowerCase();
                    if (type.contains("jpg") || type.contains("png") || type.contains("gif") 
                            || type.contains("jpeg") || type.contains("wbmp") || type.contains("bmp")) {
                        buffImages = ImageIO.read(new File(filePath));
                        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                        image.updateStaticParameter(phy, theta);
                        image.paintStatic(filePath);
                        EduPatternShowOn.updatePattern(image, "");
                        setLog(genLog());
                        imageGenerated = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Formats incorrect!", "Failure", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
                
    private void buttonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedCyllin
        if (parseArguments()) {
            buttonDisplaySecondOn.setEnabled(true);
            buttonLensOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateStaticParameter(phy, theta);
            image.paintStatic(filePath);
            EduPatternShowOn.updatePattern(image, "");
            setLog(genLog());
            imageGenerated = true;
        }
    }

    private void buttonLensOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnProcessingPhotoActionPerformed
        if (parseArguments()) {
            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateStaticParameter(phy, theta);
            image.paintStatic(filePath);
            EduPatternShowOn.updatePattern(image, "");
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
                
                EduLensOn11 mag = new EduLensOn11(panelPattern, new Dimension(120, 120));
                magFrameLenon.getContentPane().add(mag);
                magFrameLenon.pack();
                magFrameLenon.setLocation(new Point(505, 420));
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
        }
    }//GEN-LAST:event_button11LensOnProcessingPhotoActionPerformed

    private void buttonSecondGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedCyllin
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplay--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
                image.updateStaticParameter(phy, theta);
                image.paintAmplitude(buffImages);
                EduPatternShowOn.updatePattern(image, "");
                //setLog(genLog());
                imageGenerated = true;
                if (countSecondDisplay % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }
    
    private void sliderGenerateActionPerformedStatic(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:sliderGenerateActionPerformedStatic
        if (parseArguments()) {
            buttonDisplaySecondOn.setEnabled(true);
            buttonLensOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateStaticParameter(phy, theta);
            image.paintStatic(filePath);
            EduPatternShowOn.updatePattern(image, "");
            setLog(genLog());
            imageGenerated = true;
        }
    }//GEN-LAST:sliderGenerateActionPerformedStatic
    
    private void keyEventGenerateActionPerformedStatic(java.awt.event.KeyEvent evt) {//GEN-FIRST:keyEventGenerateActionPerformedStatic
        if (parseArguments()) {
            buttonDisplaySecondOn.setEnabled(true);
            buttonLensOn.setEnabled(true);

            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
            image.updateStaticParameter(phy, theta);
            image.paintStatic(filePath);
            EduPatternShowOn.updatePattern(image, "");
            setLog(genLog());
            imageGenerated = true;
        }
    }//GEN-LAST:keyEventGenerateActionPerformedStatic
    
    private boolean parseArguments() {
        boolean ret = false;
        try {          
            double phyStatic = Double.valueOf(txtXOff.getText());
            double thetaStatic = Double.valueOf(txtYOff.getText());
            this.theta = thetaStatic;
            this.phy = phyStatic;
            ret = true;
            
        } catch (Exception e) {
        }
        return ret;
    }
    
    public JTextArea getLogArea() {
        return txtLogArea;
    }
    
    private String genLog() {
        return String.format(logMessage, Double.toString(phy), Double.toString(theta));
    }
    
    public void setLog(String msg) {
        txtLogArea.append(msg + System.getProperty("line.separator"));
    }
}

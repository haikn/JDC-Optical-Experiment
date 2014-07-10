/*
 * @(#)DynamicPanel.java
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

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import com.jasper.core.OpticsPane;
import javax.swing.JOptionPane;
import com.jasper.core.PatternImage;
import com.jasper.ui.EduPatternJPanel;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jdesktop.beansbinding.BindingGroup;

import static com.jasper.ui.EduPatternShowOn.patternFrameDoubleClick;
import com.jasper.utils.Constant;
import com.jasper.utils.Utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.jdesktop.beansbinding.Binding;

/**
 *
 * @author sonnv
 */
public class DynamicPanel extends OpticsPane implements WebcamImageTransformer{

    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel lblXOff;
    private javax.swing.JLabel lblYOff;
    private javax.swing.JSlider sliderXOff;
    private javax.swing.JSlider sliderYOff;
    private javax.swing.JTextField txtXOff;
    private javax.swing.JTextField txtYOff;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton buttonLensOn;
    private javax.swing.JButton buttonDisplaySecondOn;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel lblFilePath;
    private javax.swing.JTextArea txtBox;
    private String getText;
    private static BufferedImage buffImages;
    private JPanel panelPattern;
    private JFrame window;
    private Webcam webcam = null;
    private int countLenOn = 1;
    private int countSecondDisplay = 1;
    static String logMessage = "Amplitude : image=%s";
    BufferedImage grabbedImage;

    public DynamicPanel(ResourceBundle labels, BindingGroup bindingGroup, JPanel panelPattern) {
        this.labels = labels;
        this.panelPattern = panelPattern;
        image1 = ((EduPatternJPanel) panelPattern).pimage;

        initComponents(bindingGroup);
    }

    private void initComponents(BindingGroup bindingGroup) {
        panel = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        lblXOff = new javax.swing.JLabel();
        txtXOff = new javax.swing.JTextField();
        sliderXOff = new javax.swing.JSlider();
        lblYOff = new javax.swing.JLabel();
        txtYOff = new javax.swing.JTextField();
        sliderYOff = new javax.swing.JSlider();
        btnGenerate = new javax.swing.JButton();
        buttonLensOn = new javax.swing.JButton();
        buttonDisplaySecondOn = new javax.swing.JButton();
        
        lblYOff.setText("Theta");
        lblXOff.setText("Phy");

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
        lblFilePath.setFont(new Font("Arial", Font.PLAIN, 10));
        Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sliderXOff, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtXOff, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sliderYOff, org.jdesktop.beansbinding.ELProperty.create("${value}"), txtYOff, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        sliderXOff.setMaximum(100);
        sliderXOff.setValue(0);
        sliderXOff.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                //sliderGenerateActionPerformedDoubleSlit(evt);
            }
        });
        sliderYOff.setMaximum(100);
        sliderYOff.setValue(0);
        sliderYOff.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                //sliderGenerateActionPerformedDoubleSlit(evt);
            }
        });

        btnStart.setText("Start Video");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });

        btnStop.setText("Stop");
        btnStop.setEnabled(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(txtYOff, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sliderXOff, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                                    .addComponent(sliderYOff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
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
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(153, 153, 153)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDisplaySecondOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );
    }

    public JPanel getPanel() {
        return panel;
    }

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {
        try {
             btnStart.setEnabled(true);
             btnStop.setEnabled(false);
             window.dispose();
             if (webcam != null && webcam.isOpen()) webcam.close();
        } catch (Exception ex) {
            Logger.getLogger(DynamicPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            (new CameraSwingWorker()).execute();
            btnStop.setEnabled(true);
            btnStart.setEnabled(false);
            /*startCamera();*/
        } catch (Exception ex) {
            Logger.getLogger(DynamicPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void buttonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedCyllin
//        buttonDisplaySecondOn.setEnabled(true);
//        buttonLensOn.setEnabled(true);
//
//        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
//        //image.paintDynamic(buffImages);
//        EduPatternShowOn.updateLensPatternPattern(image, "");
//        setLog(genLog());
//        imageGenerated = true;
    }

    private void buttonLensOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnProcessingPhotoActionPerformed
//        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
//        //image.paintDynamic(buffImages);
//        EduPatternShowOn.updateLensPatternPattern(image, "");
//        //setLog(genLog());
//        imageGenerated = true;
//
//        if (countLenOn % 2 == 0) {
//            magFrameLenon.dispose();
//            panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {
//                public void mouseClicked(java.awt.event.MouseEvent evt) {
//                    patternFrameDoubleClick.show();
//                }
//            });
//
//        } else {
//            magFrameLenon = new JFrame("1:1 Lens On");
//            URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
//            Toolkit kit = Toolkit.getDefaultToolkit();
//            Image img = kit.createImage(url);
//            magFrameLenon.setIconImage(img);
//
//            //EduLensOn11 mag = new EduLensOn11(panelPattern, new Dimension(120, 120), 2.0);
//            EduLensOn11 mag = new EduLensOn11(panelPattern, new Dimension(120, 120));
//            magFrameLenon.getContentPane().add(mag);
//            magFrameLenon.pack();
//            magFrameLenon.setLocation(new Point(505, 420));
//            magFrameLenon.setResizable(false);
//            magFrameLenon.setVisible(true);
//            magFrameLenon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            magFrameLenon.addWindowListener(new java.awt.event.WindowAdapter() {
//                public void windowClosing(java.awt.event.WindowEvent e) {
//                        countLenOn--;
//                        buttonLensOn.setText(labels.getString("btnLensOn"));
//                        magFrameLenon.dispose();
//                }
//            });
//        }
    }//GEN-LAST:event_button11LensOnProcessingPhotoActionPerformed

    private void buttonSecondGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedCyllin
//        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice[] devices = env.getScreenDevices();
//        if (devices.length == 1) {
//            countSecondDisplay--;
//            JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
//            PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
//            // image.updateParameterDrawSignalProcessing(processing_widthX, processing_widthY, processing_heightX, processing_heightY, processing_positionX, processing_positionY, processing_rotation, processing_grayLevel);
//            //image.paintDynamic(buffImages);
//            EduPatternShowOn.updateLensPatternPattern(image, "");
//            //setLog(genLog());
//            //EduPatternTest.updateLensPatternPattern(image, genLog());
//            imageGenerated = true;
//            if (countSecondDisplay % 2 == 0) {
//                patternFrameDoubleClick.dispose();
//                patternFrame.dispose();
//            }
//        }
    }

    private String genLog() {
        return String.format(logMessage);
    }

    public void setLog(String msg) {
        //txtLogArea.append(msg + System.getProperty("line.separator"));
    }

    public void startCamera() throws Exception {
        if (webcam != null && webcam.isOpen()) webcam.close();
        webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.setImageTransformer(this);

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        window = new JFrame("Dynamic Holography");
        window.add(panel);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        window.setBounds(0, 0, 640, 480);
        window.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                window.dispose();
                if (webcam != null && webcam.isOpen()) webcam.close();
            }
        });
        window.setAlwaysOnTop(true);
     
    }
    
    @Override
    public BufferedImage transform(BufferedImage image) {
        WritableRaster raster = image.getRaster();
            
            int x = sliderXOff.getValue();
            int y = sliderYOff.getValue();
            double phy = Math.toRadians(x);
            double theta = Math.toRadians(y);
            double xm = Math.sin(phy) * Math.cos(theta);
            double ym = Math.sin(phy) * Math.sin(theta);
            double fixpart = 2.0 * Math.PI / 10;
            double pxsize = 100;
            double phase, xa, ya;

            for (int i = 0; i < image.getWidth(); i++) {
                xa = (double) (i - x / 2 + 1) * pxsize;
                xa = xm * xa;
                for (int j = 0; j < image.getHeight(); j++) {
                    ya = (double) (y / 2 - j + 1) * pxsize;
                    ya = ym * ya;
                    phase = fixpart * (xa + ya) + raster.getSample(i, j, 0);
                    raster.setSample(i, j, 0, phase);
                }
            }
            image.setData(raster);
            return image;
    }

    class CameraSwingWorker extends SwingWorker<String, Object> {

        @Override
        public String doInBackground() {
            try {
                startCamera();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "yeah";
        }

        @Override
        protected void done() {
            try {
            } catch (Exception ignore) {
            }
        }
    }
}

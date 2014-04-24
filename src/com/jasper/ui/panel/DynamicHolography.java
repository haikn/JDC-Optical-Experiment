/*
 * @(#)DynamicHolography.java
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

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Albert Nguyen
 */
public class DynamicHolography extends javax.swing.JFrame {

    private DaemonThread myThread = null;
    private SampleModel sampleModel;
    int count = 0;

    String filePath= "";
    private FrameGrabber grabber;
    protected BufferedImage image;
    private opencv_core.IplImage grabbed = null;
    protected double scale = 1.0;	

    class DaemonThread implements Runnable {
    protected volatile boolean runnable = false;

    @Override
    public  void run() {
        double phase, xa, ya;
        synchronized(this) {
            while(runnable) {
                    try {
                        grabbed = grabber.grab();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    image = grabbed.getBufferedImage();
                    Raster raster = image.getData();
                    sampleModel = raster.getSampleModel();
                    WritableRaster raster1 = Raster.createWritableRaster(sampleModel, new Point(0, 0));
                    grabber.getImageWidth();
                    int w = grabber.getImageWidth(), h = grabber.getImageHeight();
                    System.out.println("Native camera size " + w + "*" + h);
                    if (scale != 1) {
                        w = (int) (w * scale);
                        h = (int) (h * scale);
                        System.out.println("Scaled to " + w + "*" + h);
                    }
                    int x = sliderPhy.getValue();
                    int y = sliderTheta.getValue();
                    double phy = Math.toRadians(x);
                    double theta = Math.toRadians(y);
                    double xm = Math.sin(phy) * Math.cos(theta);
                    double ym = Math.sin(phy) * Math.sin(theta);
                    double fixpart = 2.0 * Math.PI / 10;
                    double pxsize = 100;

                    for (int i = 0; i < w; i++) {
                        xa = (double) (i - x / 2 + 1) * pxsize;
                        xa = xm * xa;
                        for (int j = 0; j < h; j++) {
                            ya = (double) (y / 2 - j + 1) * pxsize;
                            ya = ym * ya;
                            phase = fixpart * (xa + ya) + raster.getSample(i, j, 0);
                            raster1.setSample(i, j, 0, phase);
                        }
                    }

                    BufferedImage image1 = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);


                    Graphics g = lblVideoDynamic.getGraphics();
                    image1.setData(raster1);

                    if (g.drawImage(image1, 0, 0, lblVideoDynamic.getWidth(),
                            lblVideoDynamic.getHeight(), 0, 0, image.getWidth(), image.getHeight(), null)) {
                        if (runnable == false) {
                            System.out.println("Going to wait()");
                            
                            try {
                                this.wait();
                                grabber.stop();
                                grabber = null;
                                grabbed = null;
                            } catch (Exception ex) {
                                Logger.getLogger(DynamicHolography.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
            }
        }
    }
}


    /** Creates new form CamCap */
    public DynamicHolography() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        lblVideoDynamic = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        comboboxInputMethod = new javax.swing.JComboBox();
        lblBrowser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        sliderPhy = new javax.swing.JSlider();
        sliderTheta = new javax.swing.JSlider();
        lblPhy = new javax.swing.JLabel();
        lblTheta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Holography");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnStart.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnStop.setText("Stop");
        btnStop.setEnabled(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        lblVideoDynamic.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblVideoDynamic.setPreferredSize(new java.awt.Dimension(320, 240));

        javax.swing.GroupLayout lblVideoDynamicLayout = new javax.swing.GroupLayout(lblVideoDynamic);
        lblVideoDynamic.setLayout(lblVideoDynamicLayout);
        lblVideoDynamicLayout.setHorizontalGroup(
            lblVideoDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        lblVideoDynamicLayout.setVerticalGroup(
            lblVideoDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setAlignmentX(0.1F);
        jPanel2.setAlignmentY(0.1F);

        comboboxInputMethod.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        comboboxInputMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "From WebCam", "From File" }));
        comboboxInputMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxInputMethodActionPerformed(evt);
            }
        });

        lblBrowser.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblBrowser.setText("...");
        lblBrowser.setEnabled(false);
        lblBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblBrowserActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel1.setText("Input method:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboboxInputMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboboxInputMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBrowser))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        lblPhy.setText("Phy");

        lblTheta.setText("Theta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVideoDynamic, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTheta)
                            .addComponent(lblPhy))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sliderPhy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderTheta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblPhy)
                            .addComponent(sliderPhy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblTheta)
                            .addComponent(sliderTheta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblVideoDynamic, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnStop))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed

        if ( (btnStart.getText()).equals("Start") )
        {
            if (comboboxInputMethod.getSelectedItem().equals("From WebCam"))
            {
                try {
                    // Set up webcam
                    grabber = new OpenCVFrameGrabber(0);
                    grabber.start();
                }
                catch (FrameGrabber.Exception ex) {
                    Logger.getLogger(DynamicHolography.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //else
                //webSource =new VideoCapture(filePath);
            
            myThread = new DaemonThread();
            Thread t = new Thread(myThread);
            t.setDaemon(true);
            myThread.runnable = true;
            t.start();

            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
            comboboxInputMethod.setEnabled(false);
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed

        if ( (btnStop.getText()).equals("Stop") )
        {
            myThread.runnable = false;
            btnStop.setEnabled(false);
            btnStart.setEnabled(true);
            comboboxInputMethod.setEnabled(true);
            //webSource.release();
        }
    }//GEN-LAST:event_btnStopActionPerformed

    private void lblBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblBrowserActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("AVI","avi");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            filePath=chooser.getSelectedFile().getPath();
            btnStart.setEnabled(true);
        }
        else
            filePath="";
    }//GEN-LAST:event_lblBrowserActionPerformed

    private void comboboxInputMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxInputMethodActionPerformed
        // TODO add your handling code here:
        if(comboboxInputMethod.getSelectedItem().equals("From File"))
        {
            lblBrowser.setEnabled(true);
            btnStart.setEnabled(false);
        }
        else
        {
            lblBrowser.setEnabled(false);
            btnStart.setEnabled(true);
        }
    }//GEN-LAST:event_comboboxInputMethodActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
            

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (myThread == null) {
        }
        else
             if (myThread.runnable)
             {
                myThread.runnable = false;
                //webSource.release();
              }
    }//GEN-LAST:event_formWindowClosing

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
    	//System.loadLibrary("OpenCV");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                DynamicHolography.setDefaultLookAndFeelDecorated(true);
                try
                {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                }
                catch (Exception ex)
                {
                        System.out.println("Failed loading L&F: ");
                        System.out.println(ex);
                        System.out.println("Loading default Look & Feel Manager!");
                }

                new DynamicHolography().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JComboBox comboboxInputMethod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton lblBrowser;
    private javax.swing.JLabel lblPhy;
    private javax.swing.JLabel lblTheta;
    private javax.swing.JPanel lblVideoDynamic;
    private javax.swing.JSlider sliderPhy;
    private javax.swing.JSlider sliderTheta;
    // End of variables declaration//GEN-END:variables

}

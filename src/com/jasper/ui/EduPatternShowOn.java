/*
 * @(#)EduPatternShowOn.java
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
package com.jasper.ui;

import com.jasper.core.KeyReaderTrial;
import com.jasper.core.PatternImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EduPatternShowOn {

    static public GraphicsDevice device;
    static PatternImage pimage;
    static PatternImage pimage2;
    static EduPatternJPanel patternPanel;
    static EduPatternJPanel patternPanel2;
    public static JFrame patternFrame;
    public static JFrame patternFrameDoubleClick;
    static EduUIMainView controlFrame;
    // use 2nd display
    static boolean use2ndDisplay = true;
    static public double lambda = 5.32e-7;
    static public double lambda2 = 5.32e-7;
    static public String lambdaStr = "532";

    public static void initPatternFrame() {
        patternFrame = new JFrame();

        Rectangle bounds;
        // create pattern image buffer
        if (EduPatternShowOn.use2ndDisplay) {
            EduPatternShowOn.setDevice();
            GraphicsConfiguration gc = EduPatternShowOn.device
                    .getDefaultConfiguration();
            bounds = gc.getBounds();
        } else {
            bounds = new Rectangle(480, 270);
        }

        pimage = new PatternImage(bounds.width, bounds.height);
        pimage.init(lambda);
        
        // for debugging purpose, show wavelength
        // System.out.println("wavelength = " + pimage.getLambda());

        patternPanel = new EduPatternJPanel(pimage);

        patternFrame.getContentPane().add(patternPanel);

        patternFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patternFrame.setSize(bounds.width, bounds.height);
        patternFrame.setLocation(bounds.x, bounds.y);
        
        patternFrameDoubleClick = new JFrame("JDC Education Kit - Pattern full screen");
        URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        patternFrameDoubleClick.setIconImage(img);
        pimage2 = new PatternImage(bounds.width, bounds.height);
        pimage2.init(lambda2);
        patternPanel2 = new EduPatternJPanel(pimage2);
        patternFrameDoubleClick.getContentPane().add(patternPanel2);

        patternFrameDoubleClick.setSize(bounds.width, bounds.height);
        patternFrameDoubleClick.setLocation(bounds.x, bounds.y);
        patternFrameDoubleClick.addWindowListener(new java.awt.event.WindowAdapter() {
        public void windowClosing(java.awt.event.WindowEvent e) {
                patternFrameDoubleClick.dispose();
        }
        });

        patternFrameDoubleClick.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
              if (evt.getClickCount() == 2) {
                  patternFrameDoubleClick.dispose();
              }
            }
        });
        // full screen
        patternFrame.setUndecorated(true);

        // not run first
        patternFrame.setVisible(false);
        patternFrame.setForeground(Color.red);
    }

    public static void disablePatternFrame() {
        patternFrame.setVisible(false);
    }

    public static void disposePatternFrame() {
        patternFrame.dispose();
    }

    public static void updateRegenerate() {
        controlFrame.updateRegenerate();
        patternPanel.revalidate();
        patternFrame.repaint();
        controlFrame.repaint();
    }

    public static void updateLensPattern(PatternImage pimage, String log) {
        controlFrame.logString(log);
        patternPanel.setImage(pimage);
        patternPanel.revalidate();

        patternFrame.repaint();
        patternFrame.setVisible(true);
        patternFrameDoubleClick.repaint();
        patternFrameDoubleClick.setVisible(true);
        controlFrame.repaint();
    }

    public static void updateLensPatternPattern(PatternImage pimage, String log) {
        controlFrame.logString(log);
        controlFrame.updatePattern(pimage);
        //controlFrame.setImage(pimage);
        patternPanel.setImage(pimage);
        patternPanel.revalidate();
        patternPanel2.setImage(pimage);
        patternPanel2.revalidate();

        patternFrame.repaint();
        patternFrameDoubleClick.repaint();
        controlFrame.repaint();
    }
    
    public static void updatePatternSecondDisplay(PatternImage pimage, String log) {
        controlFrame.logString(log);
        controlFrame.updatePattern(pimage);
        //controlFrame.setImage(pimage);
        patternPanel.setImage(pimage);
        patternPanel.revalidate();

        patternFrame.repaint();
        patternFrame.setVisible(true);
        controlFrame.repaint();
    }

    public static void updateUiPatternPattern(PatternImage pimage, String log, String desc, String diagram) {
        controlFrame.logString(log);
        controlFrame.updatePattern(pimage);
        //controlFrame.setImage(pimage);
        patternPanel.setImage(pimage);
        patternPanel.revalidate();
        patternPanel2.setImage(pimage);
        patternPanel2.revalidate();

        patternFrame.repaint();
        //patternFrame.setVisible(true);
        controlFrame.repaint();
    }

    public static void updateCylindricalPattern(PatternImage pimage, String log) {
        controlFrame.logString(log);
        patternPanel.setImage(pimage);
        patternPanel.revalidate();
        patternFrame.repaint();
        patternFrame.setVisible(true);
        controlFrame.repaint();
    }

    public static void updateMirrorPattern(PatternImage pimage, String log) {
        controlFrame.logString(log);
        patternPanel.setImage(pimage);
        patternPanel.revalidate();
        patternFrame.repaint();
        patternFrame.setVisible(true);
        controlFrame.repaint();
    }

    public static void updatePatternScreen(PatternImage pimage, String log) {
        controlFrame.logString(log);
        patternPanel.setImage(pimage);
        patternPanel.revalidate();
        patternFrame.repaint();
        patternFrame.setVisible(false);
        controlFrame.repaint();
    }
    static int debugging = 0;
    static int logging = 0;

    public static void initControlFrame() throws IOException {
        controlFrame = new EduUIMainView();
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setLocation(0, 0);
        //controlFrame.setBounds(0,0,1300,760);
        controlFrame.setPreferredSize(new Dimension(1280, 803));

        controlFrame.pack();
        controlFrame.setVisible(true);
        controlFrame.setResizable(true);

        // following line is for debugging. it's very useful when using with one
        // display only
        if (debugging == 1) {
            controlFrame.setAlwaysOnTop(true);
        }

        // put out configuration of the selected display device
        Rectangle bounds = patternFrame.getBounds();
        controlFrame.logString("Panel width=" + bounds.width + ", height="
                + bounds.height);

        // log wavelength
        controlFrame.logString("Wavelength=" + pimage.getLambda());
    }

    enum main_opts {
        debug, pane, log, lambda;
    }

    static int parse_int(String token) throws Exception {
        try {
            int val = Integer.valueOf(token);
            return val;
        } catch (Exception e) {
            throw e;
        }
    }

    static void parse_opt(String arg) {
        String tokens[] = arg.split("=");
        try {
            main_opts opt = main_opts.valueOf(tokens[0]);
            switch (opt) {
                case debug:
                    debugging = parse_int(tokens[1]);
                    break;
                case log:
                    logging = parse_int(tokens[1]);
                    break;
                case pane:
                    if (parse_int(tokens[1]) == 1) {
                        use2ndDisplay = false;
                    }
                    break;
                case lambda:
                    lambda = Double.valueOf(tokens[1] + "e-9");
                    lambdaStr = tokens[1];
                    break;
            }
        } catch (Exception e) {
        }
    }

    public static void setDevice() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = env.getScreenDevices();

        // if only one device is detected, then user selection is not required
        if (devices.length == 1) {
            EduPatternShowOn.device = devices[0];
            return;
        }

        String shortenedlist[] = new String[devices.length];
        int cnt = 0;
        for (GraphicsDevice dev : devices) {
            shortenedlist[cnt++] = dev.getIDstring();
        }
        int suggested = devices.length - 1;
        int selected = suggested;
        URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
        ImageIcon icon = new ImageIcon(url, "help");
        ResourceBundle bundle = ResourceBundle.getBundle("resources/Text");
        String chosen = (String) JOptionPane.showInputDialog(null,
                bundle.getString("DISPLAY_SELECT_BODY"),
                bundle.getString("DISPLAY_SELECT_HEAD"),
                JOptionPane.INFORMATION_MESSAGE, icon, shortenedlist,
                shortenedlist[suggested]);
        if (chosen != null) {
            for (int i = 0; i < shortenedlist.length; i++) {
                if (shortenedlist[i].equals(chosen)) {
                    selected = i;
                    break;
                }
            }
        }
        EduPatternShowOn.device = devices[selected];
    }
    
    public static void main(String[] args) {
        try {
            KeyReaderTrial keyreader = new KeyReaderTrial();
            boolean key = keyreader.verifyKey();
            if(key) {
                // parse arguments
                for (String arg : args) {
                    parse_opt(arg);
                }
                EduPatternShowOn.initPatternFrame();
                EduPatternShowOn.initControlFrame();
                //EduPatternShowOn.initPatternFrameFullScreen();

                // set icon using JDC logo
                URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
                Toolkit kit = Toolkit.getDefaultToolkit();
                Image img = kit.createImage(url);
                controlFrame.setIconImage(img);
                patternFrame.setIconImage(img);
            } else {
                JOptionPane.showMessageDialog(null, "This trial has expired. Please contact us for further information!");
            }
        } catch (IOException ex) {
            Logger.getLogger(EduPatternShowOn.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}

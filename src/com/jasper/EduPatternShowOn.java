/*
 * @(#)EduPatternShowOn.java
 *
 * Copyright (c) 2013 JASPER DISPLAY, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * JASPER DISPLAY PROPRIETARY:  Distribution of this source code
 * without permission from the copyright holder is strictly forbidden.
 */
package com.jasper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
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
    static EduPatternJPanel patternPanel;
    static EduPatternJPanel patternPanel2;
    static JFrame patternFrame;
    static EduUIMainView controlFrame;
    // use 2nd display
    static boolean use2ndDisplay = true;
    static public double lambda = 5.32e-7;
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
        

        // full screen
        patternFrame.setUndecorated(true);
        patternFrame.addMouseListener(new ClickListener() {
            public void doubleClick(MouseEvent e) {
                patternFrame.dispose();
            }
        });

//                patternPanel.addMouseListener(new ClickListener() {
//                   public void doubleClick(MouseEvent e) {
//                       //patternFrame.setVisible(true);
//                       if (patternPanel.contains(e.getPoint())) {//check if mouse is clicked within shape
//                        System.out.println("Clicked a "+patternPanel.getClass().getName());
//                    }
//                       // log
//                       //System.out.println("double");
//                   }
//               });



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
        controlFrame.repaint();
    }

    public static void updateLensPatternPattern(PatternImage pimage, String log) {
        controlFrame.logString(log);
        controlFrame.updatePattern(pimage);
        //controlFrame.setImage(pimage);
        patternPanel.setImage(pimage);
        patternPanel.revalidate();

        patternFrame.repaint();
        //patternFrame.setVisible(true);
        controlFrame.repaint();
    }

    public static void updateUiPatternPattern(PatternImage pimage, String log, String desc, String diagram) {
        controlFrame.logString(log);
        controlFrame.updatePattern(pimage);
        //controlFrame.setImage(pimage);
        patternPanel.setImage(pimage);
        patternPanel.revalidate();

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

    public static void initControlFrame() {
        controlFrame = new EduUIMainView();
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setLocation(0, 0);
        //controlFrame.setBounds(0,0,1300,760);
        controlFrame.setPreferredSize(new Dimension(1286, 730));

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
            KeyReader keyreader = new KeyReader();
            boolean key = keyreader.verifyKey();
            if(key) {
                // parse arguments
                for (String arg : args) {
                    parse_opt(arg);
                }
                EduPatternShowOn.initPatternFrame();
                EduPatternShowOn.initControlFrame();

                // set icon using JDC logo
                URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
                Toolkit kit = Toolkit.getDefaultToolkit();
                Image img = kit.createImage(url);
                controlFrame.setIconImage(img);
                patternFrame.setIconImage(img);
            }
        } catch (IOException ex) {
            Logger.getLogger(EduPatternShowOn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

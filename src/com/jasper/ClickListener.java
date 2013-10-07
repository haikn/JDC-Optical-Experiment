/*
 * @(#)ClickListener.java
 *
 * Copyright (c) 2013 JASPER DISPLAY, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * JASPER DISPLAY PROPRIETARY:  Distribution of this source code
 * without permission from the copyright holder is strictly forbidden.
 */
package com.jasper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This ClickListener include the click even of application
 *
 * @version 1.0 28 Aug 2013
 *
 * @author Albert Nguyen
 *
 */
public class ClickListener extends MouseAdapter implements ActionListener {

    private final static int clickInterval = (Integer) Toolkit.getDefaultToolkit().
            getDesktopProperty("awt.multiClickInterval");
    MouseEvent lastEvent;
    Timer timer;

    public ClickListener() {
        this(clickInterval);
    }

    public ClickListener(int delay) {
        timer = new Timer(delay, this);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() > 2) {
            return;
        }

        lastEvent = e;

        if (timer.isRunning()) {
            timer.stop();
            doubleClick(lastEvent);
        } else {
            timer.restart();
        }
    }

    public void actionPerformed(ActionEvent e) {
        timer.stop();
        singleClick(lastEvent);
    }

    public void singleClick(MouseEvent e) {
    }

    public void doubleClick(MouseEvent e) {
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Double Click Test");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.addMouseListener(new ClickListener() {
//            public void singleClick(MouseEvent e) {
//                System.out.println("single");
//            }
//
//            public void doubleClick(MouseEvent e) {
//                System.out.println("double");
//            }
//        });
//        frame.setSize(200, 200);
//        frame.setVisible(true);
//    }
}

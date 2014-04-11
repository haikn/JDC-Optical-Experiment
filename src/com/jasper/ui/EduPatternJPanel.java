/*
 * @(#)EduPatternJPanel.java
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

import com.jasper.core.PatternImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * This EduPatternJPanel include the panel information of application
 *
 * @version 1.0 28 Aug 2013
 *
 * @author Albert Nguyen
 *
 */
public class EduPatternJPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    public BufferedImage capture = null;
    public PatternImage pimage;
    public PatternImage pimage1;
    private boolean isScreen = false;
    
    Font font = new Font("Courier New", Font.PLAIN, 12);

    public EduPatternJPanel() {
        pimage = new PatternImage();
        pimage.init(EduPatternShowOn.lambda);
        pimage1 = new PatternImage();
        pimage1.init(EduPatternShowOn.lambda);
        isScreen = false;
    }

    public EduPatternJPanel(PatternImage pimage) {
        this.pimage = pimage;
        isScreen = true;
    }

    public PatternImage getPimage() {
        return pimage;
    }

    public void setImage(PatternImage pimage) {
        this.pimage = pimage;
    }

    public void paint(Graphics g) {
        Rectangle bounds = getBounds();
        if (bounds.width != PatternImage.width && bounds.height != PatternImage.height) {
            g.drawImage(pimage.canvas, 0, 0, bounds.width, bounds.height, null);
        } else {
            g.drawImage(pimage.canvas, 0, 0, null);
        }
        if (pimage.action != 0) {
            capture = pimage.canvas.getSubimage(Math.max(0, pimage.rectLocale.x),
                    Math.max(0, pimage.rectLocale.y), pimage.rectSize.width, pimage.rectSize.height);
            int width2 = (int) (pimage.rectSize.width + pimage.rectSize.width * (pimage.zoom_layout / 300D));
            int height2 = (int) (pimage.rectSize.height + pimage.rectSize.height * (pimage.zoom_layout / 300D));
            int x2 = pimage.rectLocale.x - ((width2 - pimage.rectSize.width) / 2);
            int y2 = pimage.rectLocale.y - ((height2 - pimage.rectSize.height) / 2);
            Image scaledInstance = capture.getScaledInstance(
                    width2, height2, Image.SCALE_AREA_AVERAGING);
            g.drawImage(scaledInstance, x2, y2, null);
            g.drawRect(x2, y2, width2, height2);
        }
        if (isScreen) {
            // draw title string to the screen
            Graphics2D g2 = (Graphics2D) g;
            String title = "JDC EDUKIT " + PatternImage.width + "x" + bounds.height + " (" + EduPatternShowOn.lambdaStr + "nm)" + ": " + pimage.title;
            g2.setFont(font);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g2.setColor(Color.yellow);
            // calculate the bounding box the title string
            FontMetrics metrics = g.getFontMetrics(font);
            int hgt = metrics.getHeight();
            int adv = metrics.stringWidth(title);

            // g2.drawString(title, bounds.width - adv - 2, hgt + 2);
            g2.drawString(title, 2, hgt - 2);
        }
        g.dispose();
    }
}

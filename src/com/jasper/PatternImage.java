/*
 * @(#)PatternImage.java
 *
 * Copyright (c) 2013 NOMOVOK, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * NOMOVOK PROPRIETARY:  The information contained in or disclosed by this
 * document is considered proprietary by NOMOVOK, Inc.  This document and/or the
 * information contained therein shall not be duplicated nor disclosed in whole
 * or in part without the specific written permission of NOMOVOK, Inc.
 */
package com.jasper;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This PatternImage include the algorithms of application
 *
 * @version 1.0 28 Aug 2013
 *
 * @author Albert Nguyen
 *
 */
public class PatternImage {

    private double lambda;
    private double xoff;
    private double yoff;
    private double angle;
    private double pxsize;
    private double focal;
    private double mirrorTheta;
    private double mirrorPhy;
    // Draw
    private double d_widthX;
    private double d_widthY;
    private double d_heightX;
    private double d_heightY;
    private double d_postionX;
    private double d_postionY;
    private double d_rotation;
    private double d_grayLevel;
    private double d_zoom;
    private double d_spacing;
    //end
    public BufferedImage canvas;
    public static int width;
    public static int height;
    public static int gray2phase[];
    // title string
    public String title;

    public PatternImage(int w, int h) {
        width = w;
        height = h;
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        title = "";

        //Graphics2D g2 = canvas.createGraphics();
        //g2.setBackground(Color.RED);
        //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 0.0f));
    }

    public PatternImage(int w, int h, int a) {
        width = w;
        height = h;
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        title = "";

        Graphics2D g2 = canvas.createGraphics();
        g2.setBackground(Color.RED);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 0.0f));
    }

    public PatternImage() {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        //Graphics2D g2 = canvas.createGraphics();
        //g2.setBackground(Color.RED);
        //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 0.0f));

    }

    public void init(double lambda) {
        this.lambda = lambda;
        this.xoff = 0.0;
        this.yoff = 0.0;
        this.angle = 0.0;
        this.pxsize = 6.4e-6;
        this.focal = 1.0;
        gray2phase = new int[256];
        initGray2phase();
    }

    public double getLambda() {
        return lambda;
    }

    public Rectangle getBounds() {
        return new Rectangle(width, height);
    }

    void updateLensParameter(double xoff, double yoff, double focal) {
        this.xoff = xoff;
        this.yoff = yoff;
        this.focal = focal;
        title = "lens " + xoff + " " + yoff + " " + focal;
    }

    void updateLensParameterDrawSlit(int slit, double width, double height, double postion, double rotation, double grayLevel, double spacing) {
        this.d_widthX = width;
        this.d_heightX = height;
        this.d_postionX = postion;
        this.d_rotation = rotation;
        this.d_grayLevel = grayLevel;
        this.d_spacing = spacing;
        title = (slit < 2 ? "Draw Single Slit " : "Draw Double Slit ") + d_widthX + " " + d_heightX + " " + this.d_postionX + " " + this.d_rotation + " " + this.d_grayLevel;
    }

    void updateParameterDrawSignalProcessing(double widthX, double widthY, double heightX, double heightY, double postionX, double postionY, double rotation, double grayLevel) {
        this.d_widthX = widthX;
        this.d_widthY = widthY;
        this.d_heightX = heightX;
        this.d_heightY = heightY;
        this.d_postionX = postionX;
        this.d_postionY = postionY;
        this.d_rotation = rotation;
        this.d_grayLevel = grayLevel;
        title = "Draw Signal Processing " + d_widthX + " " + d_widthY + " " + this.d_heightX + " " + this.d_heightY + " " + this.d_postionX + " " + this.d_postionY + " " + this.d_rotation + " " + this.d_grayLevel;
    }

    void updatePhaseRetarderParameter(double zoom, double grayLevel) {
        this.d_zoom = zoom;
        this.d_grayLevel = grayLevel;
        title = "PhaseRetarder " + zoom + " " + grayLevel;
    }

    void updateCyllindricalParameter(double xoff, double angle, double focal) {
        this.xoff = xoff;
        this.angle = angle;
        this.focal = focal;
        title = "cylindrical " + xoff + " " + angle + " " + focal;
    }

    void updateMirrorParameter(double phy, double theta) {
        this.mirrorPhy = phy;
        this.mirrorTheta = theta;
        title = "mirror " + phy + " " + theta;
    }

    private void initGray2phase() {
        for (int i = 0; i < gray2phase.length; i++) {
            gray2phase[i] = i;
        }
    }

    private int phase2gray(double phase) {
        int scale = gray2phase.length;
        phase = phase / 2.0d / Math.PI;
        phase -= Math.floor(phase);
        int gray = Math.min((int) Math.round(phase * scale), scale - 1);
        //Math.getExponent(gray);
        return gray2phase[gray];
    }

    // Telephoto Lens algorithms
    public void paintLens() {
        WritableRaster raster = canvas.getRaster();
        int[] iArray = new int[1];
        double x2, y2, phase;
        double y1;
        double fixpart = Math.PI / lambda / (focal / 10000);

        // 2*pi/la*0.1*x*psize
        //double fixpart2 = 2.0 * Math.PI / lambda * 0.1; 

        // calculate phase of each pixel;
        for (int i = 0; i < height; i++) {
            x2 = (double) (i - height / 2 + 1) * pxsize;
            x2 -= (-yoff / 10000);
            //x2 -= 0.0;
            x2 = Math.pow(x2, 2.0);
            // Albert 2013/09/05
            Math.getExponent(x2);
            // 2*pi/la*0.1*x*psize
            double fixpart2 = 2.0 * Math.PI / lambda * x2 * 0.1;
            for (int j = 0; j < width; j++) {
                y2 = (double) (j - width / 2 + 1) * pxsize;
                y2 -= (xoff / 10000);
                //y2 -= 0.0;
                y1 = y2;
                y2 = Math.pow(y2, 2.0);

                // Albert 2013/09/05
                Math.getExponent(y2);
                phase = fixpart * (x2 + y2);
                //phase = fixpart * (22*x2 + 10*y2) ;
//                                phase=exp(i*pi/wavelength/focallength*(x.^2+y.^2));
//                                hologram=((angle(phase)+pi)/2/pi);

                //wave=exp(j*pi/wl*(xt.^2+yt.^2));
                // added fixpart2 from David's LensMaekr0402_2013

                // added fixpart2 from David's LensMaekr0916_2013
                // wave=exp(j*pi/wl/f*(xt.^2+yt.^2));

//                                if(xoff > 0) {
//                                    phase += fixpart2 * y1 / xoff ;
//                                } else {
                phase += fixpart2 * x2 * y2;
                //}
                //phase = (fixpart * (x2 + y2) + (fixpart2 * y1));
                //phase += phase/2;
                //phase += fixpart2 * y1 ;
                iArray[0] = phase2gray(phase);
                //raster.setPixel(j, i, iArray);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    // Microscope algorithms
    public void paintMicroscope() {
        WritableRaster raster = canvas.getRaster();
        int[] iArray = new int[1];
        double x2, y2, phase;
        double fixpart = Math.PI / lambda / (focal);

        // calculate phase of each pixel;
        for (int i = 0; i < height; i++) {
            x2 = (double) (i - height / 2 + 1) * pxsize;
            x2 -= (-yoff / 1000);
            x2 = Math.pow(x2, 2.0);
            Math.getExponent(x2);
            // 2*pi/la*0.1*x*psize
            double fixpart2 = 2.0 * Math.PI / lambda * x2 * 0.1;
            for (int j = 0; j < width; j++) {
                y2 = (double) (j - width / 2 + 1) * pxsize;
                y2 -= (xoff / 1000);
                y2 = Math.pow(y2, 2.0);

                Math.getExponent(y2);
                phase = fixpart * (x2 + y2);
                phase += fixpart2 * x2 * y2;
                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }
//        public void paintMicroscope() {
//		WritableRaster raster = canvas.getRaster();
//
//		int[] iArray = new int[1];
//		double x2, y2, phase;
//		double y1;
//
//		double fixpart = Math.PI / lambda / focal;
//
//		// 2*pi/la*0.1*x*psize
//		//double fixpart2 = 2.0 * Math.PI / lambda * 0.1; 
//
//		// calculate phase of each pixel;
//		for (int i = 0; i < height; i++) {
//			x2 = (double) (i - height/2 + 1) * pxsize;
//			//x2 -= xoff;
//                        x2 -= yoff;
//			x2 = Math.pow(x2, 2.0);
//                        // 2*pi/la*0.1*x*psize
//                        double fixpart2 = 2.0 * Math.PI / lambda * x2 * 0.1; 
//			for (int j = 0; j < width; j++) {
//				y2 = (double) (j - width/2 + 1) * pxsize;
//				//y2 -= yoff;
//                                y2 -= xoff;
//				y1 = y2;
//				y2 = Math.pow(y2, 2.0);
//				phase = (fixpart * (x2 + y2));
//                                
//				//phase += ((fixpart2 * y1)/200/Math.PI);
//                                //phase += fixpart2 * y1;
//				iArray[0] = phase2gray(phase);
//				raster.setPixel(j, i, iArray);
//			}
//		}
//	}

    // Cylindircal algorithms
    public void paintCylindircal() {
        WritableRaster raster = canvas.getRaster();

        int[] iArray = new int[1];
        double x1, y1, x2, phase;

        double fixpart2 = 2.0 * Math.PI / lambda * Math.cos(Math.toRadians(3.0));
        double fixpart = Math.PI / lambda / focal;

        double costheta = Math.cos(Math.toRadians(angle));
        double sintheta = Math.sin(Math.toRadians(angle));

// following statement is for debugging
//		System.out.println("paintCylindircal");

        for (int i = 0; i < height; i++) {
            x1 = (double) (i - height / 2 + 1) * pxsize;
            x1 -= xoff;
            for (int j = 0; j < width; j++) {
                y1 = (double) (j - width / 2 + 1) * pxsize;
                x2 = x1 * costheta - y1 * sintheta;
                x2 = Math.pow(x2, 2.0);
                phase = fixpart * x2 + fixpart2 * y1;

// following two statements are for debugging
//				phase = fixpart2 * y1;
//				System.out.println("i="+i+" j="+j+" phase="+phase);

                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    // Mirror algorithms
    public void paintMirror() {
        WritableRaster raster = canvas.getRaster();

        int[] iArray = new int[1];
        double phase, x, y;

        double phy = Math.toRadians(mirrorPhy);
        double theta = Math.toRadians(mirrorTheta);
//        double phy = Math.toRadians(mirrorPhy) + Math.PI/300;
//        double theta = Math.toRadians(mirrorTheta) + Math.PI/10;
//        double phy = Math.PI/300;
//        double theta = Math.PI/10;
        double focal = Math.toRadians(mirrorPhy);

// following statement is for debugging
//		pi = Math.toRadians(3.0);

        double xcomp = Math.sin(phy) * Math.cos(theta);
        double ycomp = Math.sin(phy) * Math.sin(theta);

        double fixpart = 2.0 * Math.PI / lambda;
//        [x,y]=meshgrid(-960*p:p:959*p,540*p:-p:-539*p);
//
//        xt=x*cos(theta)+y*sin(theta);
//
//        yt=-x*sin(theta)+y*cos(theta);
//
//        % wavefront and its phase
//        wave=exp(1i*2*pi/wl*sin(phi)*xt);

        for (int i = 0; i < height; i++) {
            x = (double) (i - height / 2 + 1) * pxsize;
            x = xcomp * x;
            for (int j = 0; j < width; j++) {
                y = (double) (j - width / 2 + 1) * pxsize;
                y = ycomp * y;
                phase = fixpart * (x + y);

// following statement is for debugging
//				System.out.println("i="+i+" j="+j+" phase="+phase);

                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    // Diffraction algorithms
    public void paintDiffraction() {
        WritableRaster raster = canvas.getRaster();

        int[] iArray = new int[1];
        double phase, x, y;

        double phy = Math.toRadians(mirrorPhy);
        double theta = Math.toRadians(mirrorTheta);
//        double phy = Math.toRadians(mirrorPhy) + Math.PI/300;
//        double theta = Math.toRadians(mirrorTheta) + Math.PI/10;
//        double phy = Math.PI/300;
//        double theta = Math.PI/10;
        double focal = Math.toRadians(mirrorPhy);

// following statement is for debugging
//		pi = Math.toRadians(3.0);

        double xcomp = Math.sin(phy) * Math.cos(theta);
        double ycomp = Math.sin(phy) * Math.sin(theta);

        double fixpart = 2.0 * Math.PI / lambda;
//        [x,y]=meshgrid(-960*p:p:959*p,540*p:-p:-539*p);
//
//        xt=x*cos(theta)+y*sin(theta);
//
//        yt=-x*sin(theta)+y*cos(theta);
//
//        % wavefront and its phase
//        wave=exp(1i*2*pi/wl*sin(phi)*xt);

        for (int i = 0; i < height; i++) {
            x = (double) (i - height / 2 + 1) * pxsize;
            x = xcomp * x;
            for (int j = 0; j < width; j++) {
                y = (double) (j - width / 2 + 1) * pxsize;
                y = ycomp * y;
                phase = fixpart * (x + y);

// following statement is for debugging
//				System.out.println("i="+i+" j="+j+" phase="+phase);

                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    // Phase algorithms
    public void paintPhase() {
        WritableRaster raster = canvas.getRaster();

        int[] iArray = new int[1];
        double phase, x, y;

        double phy = Math.toRadians(mirrorPhy);
        double theta = Math.toRadians(mirrorTheta);
//        double phy = Math.toRadians(mirrorPhy) + Math.PI/300;
//        double theta = Math.toRadians(mirrorTheta) + Math.PI/10;
//        double phy = Math.PI/300;
//        double theta = Math.PI/10;
        double focal = Math.toRadians(mirrorPhy);

// following statement is for debugging
//		pi = Math.toRadians(3.0);

        double xcomp = Math.sin(phy) * Math.cos(theta);
        double ycomp = Math.sin(phy) * Math.sin(theta);

        double fixpart = 2.0 * Math.PI / lambda;
//        [x,y]=meshgrid(-960*p:p:959*p,540*p:-p:-539*p);
//
//        xt=x*cos(theta)+y*sin(theta);
//
//        yt=-x*sin(theta)+y*cos(theta);
//
//        % wavefront and its phase
//        wave=exp(1i*2*pi/wl*sin(phi)*xt);

        for (int i = 0; i < height; i++) {
            x = (double) (i - height / 2 + 1) * pxsize;
            x = xcomp * x;
            for (int j = 0; j < width; j++) {
                y = (double) (j - width / 2 + 1) * pxsize;
                y = ycomp * y;
                phase = fixpart * (x + y);

// following statement is for debugging
//				System.out.println("i="+i+" j="+j+" phase="+phase);

                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    // Signal algorithms
    public void paintSignal() {
        WritableRaster raster = canvas.getRaster();

        int[] iArray = new int[1];
        double phase, x, y;

        double phy = Math.toRadians(mirrorPhy);
        double theta = Math.toRadians(mirrorTheta);
//        double phy = Math.toRadians(mirrorPhy) + Math.PI/300;
//        double theta = Math.toRadians(mirrorTheta) + Math.PI/10;
//        double phy = Math.PI/300;
//        double theta = Math.PI/10;
        double focal = Math.toRadians(mirrorPhy);

// following statement is for debugging
//		pi = Math.toRadians(3.0);

        double xcomp = Math.sin(phy) * Math.cos(theta);
        double ycomp = Math.sin(phy) * Math.sin(theta);

        double fixpart = 2.0 * Math.PI / lambda;
//        [x,y]=meshgrid(-960*p:p:959*p,540*p:-p:-539*p);
//
//        xt=x*cos(theta)+y*sin(theta);
//
//        yt=-x*sin(theta)+y*cos(theta);
//
//        % wavefront and its phase
//        wave=exp(1i*2*pi/wl*sin(phi)*xt);

        for (int i = 0; i < height; i++) {
            x = (double) (i - height / 2 + 1) * pxsize;
            x = xcomp * x;
            for (int j = 0; j < width; j++) {
                y = (double) (j - width / 2 + 1) * pxsize;
                y = ycomp * y;
                phase = fixpart * (x + y);

// following statement is for debugging
//				System.out.println("i="+i+" j="+j+" phase="+phase);

                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    // Spectrometer algorithms
    public void paintSpectrometer() {
        WritableRaster raster = canvas.getRaster();

        int[] iArray = new int[1];
        double phase, x, y;

        double phy = Math.toRadians(mirrorPhy);
        double theta = Math.toRadians(mirrorTheta);
//        double phy = Math.toRadians(mirrorPhy) + Math.PI/300;
//        double theta = Math.toRadians(mirrorTheta) + Math.PI/10;
//        double phy = Math.PI/300;
//        double theta = Math.PI/10;
        double focal = Math.toRadians(mirrorPhy);

// following statement is for debugging
//		pi = Math.toRadians(3.0);

        double xcomp = Math.sin(phy) * Math.cos(theta);
        double ycomp = Math.sin(phy) * Math.sin(theta);

        double fixpart = 2.0 * Math.PI / lambda;
//        [x,y]=meshgrid(-960*p:p:959*p,540*p:-p:-539*p);
//
//        xt=x*cos(theta)+y*sin(theta);
//
//        yt=-x*sin(theta)+y*cos(theta);
//
//        % wavefront and its phase
//        wave=exp(1i*2*pi/wl*sin(phi)*xt);

        for (int i = 0; i < height; i++) {
            x = (double) (i - height / 2 + 1) * pxsize;
            x = xcomp * x;
            for (int j = 0; j < width; j++) {
                y = (double) (j - width / 2 + 1) * pxsize;
                y = ycomp * y;
                phase = fixpart * (x + y);

// following statement is for debugging
//				System.out.println("i="+i+" j="+j+" phase="+phase);

                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    // Talbot algorithms
    public void paintTalbot() {
        WritableRaster raster = canvas.getRaster();

        int[] iArray = new int[1];
        double phase, x, y;

        double phy = Math.toRadians(mirrorPhy);
        double theta = Math.toRadians(mirrorTheta);
//        double phy = Math.toRadians(mirrorPhy) + Math.PI/300;
//        double theta = Math.toRadians(mirrorTheta) + Math.PI/10;
//        double phy = Math.PI/300;
//        double theta = Math.PI/10;
        double focal = Math.toRadians(mirrorPhy);

// following statement is for debugging
//		pi = Math.toRadians(3.0);

        double xcomp = Math.sin(phy) * Math.cos(theta);
        double ycomp = Math.sin(phy) * Math.sin(theta);

        double fixpart = 2.0 * Math.PI / lambda;
//        [x,y]=meshgrid(-960*p:p:959*p,540*p:-p:-539*p);
//
//        xt=x*cos(theta)+y*sin(theta);
//
//        yt=-x*sin(theta)+y*cos(theta);
//
//        % wavefront and its phase
//        wave=exp(1i*2*pi/wl*sin(phi)*xt);

        for (int i = 0; i < height; i++) {
            x = (double) (i - height / 2 + 1) * pxsize;
            x = xcomp * x;
            for (int j = 0; j < width; j++) {
                y = (double) (j - width / 2 + 1) * pxsize;
                y = ycomp * y;
                phase = fixpart * (x + y);

// following statement is for debugging
//				System.out.println("i="+i+" j="+j+" phase="+phase);

                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    // Wavefront algorithms
    public void paintWavefront() {
        WritableRaster raster = canvas.getRaster();

        int[] iArray = new int[1];
        double phase, x, y;

        double phy = Math.toRadians(mirrorPhy);
        double theta = Math.toRadians(mirrorTheta);
//        double phy = Math.toRadians(mirrorPhy) + Math.PI/300;
//        double theta = Math.toRadians(mirrorTheta) + Math.PI/10;
//        double phy = Math.PI/300;
//        double theta = Math.PI/10;
        double focal = Math.toRadians(mirrorPhy);

// following statement is for debugging
//		pi = Math.toRadians(3.0);

        double xcomp = Math.sin(phy) * Math.cos(theta);
        double ycomp = Math.sin(phy) * Math.sin(theta);

        double fixpart = 2.0 * Math.PI / lambda;
//        [x,y]=meshgrid(-960*p:p:959*p,540*p:-p:-539*p);
//
//        xt=x*cos(theta)+y*sin(theta);
//
//        yt=-x*sin(theta)+y*cos(theta);
//
//        % wavefront and its phase
//        wave=exp(1i*2*pi/wl*sin(phi)*xt);

        for (int i = 0; i < height; i++) {
            x = (double) (i - height / 2 + 1) * pxsize;
            x = xcomp * x;
            for (int j = 0; j < width; j++) {
                y = (double) (j - width / 2 + 1) * pxsize;
                y = ycomp * y;
                phase = fixpart * (x + y);

// following statement is for debugging
//				System.out.println("i="+i+" j="+j+" phase="+phase);

                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    // Wavelength algorithms
    public void paintWavelength() {
        WritableRaster raster = canvas.getRaster();

        int[] iArray = new int[1];
        double phase, x, y;

        double phy = Math.toRadians(mirrorPhy);
        double theta = Math.toRadians(mirrorTheta);
//        double phy = Math.toRadians(mirrorPhy) + Math.PI/300;
//        double theta = Math.toRadians(mirrorTheta) + Math.PI/10;
//        double phy = Math.PI/300;
//        double theta = Math.PI/10;
        double focal = Math.toRadians(mirrorPhy);

// following statement is for debugging
//		pi = Math.toRadians(3.0);

        double xcomp = Math.sin(phy) * Math.cos(theta);
        double ycomp = Math.sin(phy) * Math.sin(theta);

        double fixpart = 2.0 * Math.PI / lambda;
//        [x,y]=meshgrid(-960*p:p:959*p,540*p:-p:-539*p);
//
//        xt=x*cos(theta)+y*sin(theta);
//
//        yt=-x*sin(theta)+y*cos(theta);
//
//        % wavefront and its phase
//        wave=exp(1i*2*pi/wl*sin(phi)*xt);

        for (int i = 0; i < height; i++) {
            x = (double) (i - height / 2 + 1) * pxsize;
            x = xcomp * x;
            for (int j = 0; j < width; j++) {
                y = (double) (j - width / 2 + 1) * pxsize;
                y = ycomp * y;
                phase = fixpart * (x + y);

// following statement is for debugging
//				System.out.println("i="+i+" j="+j+" phase="+phase);

                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    // Aber algorithms
    public void paintAber() {
        WritableRaster raster = canvas.getRaster();

        int[] iArray = new int[1];
        double phase, x, y;

        double phy = Math.toRadians(mirrorPhy);
        double theta = Math.toRadians(mirrorTheta);
//        double phy = Math.toRadians(mirrorPhy) + Math.PI/300;
//        double theta = Math.toRadians(mirrorTheta) + Math.PI/10;
//        double phy = Math.PI/300;
//        double theta = Math.PI/10;
        double focal = Math.toRadians(mirrorPhy);

// following statement is for debugging
//		pi = Math.toRadians(3.0);

        double xcomp = Math.sin(phy) * Math.cos(theta);
        double ycomp = Math.sin(phy) * Math.sin(theta);

        double fixpart = 2.0 * Math.PI / lambda;
//        [x,y]=meshgrid(-960*p:p:959*p,540*p:-p:-539*p);
//
//        xt=x*cos(theta)+y*sin(theta);
//
//        yt=-x*sin(theta)+y*cos(theta);
//
//        % wavefront and its phase
//        wave=exp(1i*2*pi/wl*sin(phi)*xt);

        for (int i = 0; i < height; i++) {
            x = (double) (i - height / 2 + 1) * pxsize;
            x = xcomp * x;
            for (int j = 0; j < width; j++) {
                y = (double) (j - width / 2 + 1) * pxsize;
                y = ycomp * y;
                phase = fixpart * (x + y);

// following statement is for debugging
//				System.out.println("i="+i+" j="+j+" phase="+phase);

                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    private static int[] parseElement(String s) throws IOException {
        int[] ret = new int[gray2phase.length];
        int idx = 0;
        int scale = gray2phase.length;
        String elements[] = s.split(",");
        for (String element : elements) {
            if (element.contains(":")) {
                String ends[] = element.split(":");
                if (ends.length != 2) {
                    throw new IOException();
                }
                int front = Integer.valueOf(ends[0]);
                int rear = Integer.valueOf(ends[1]);
                if (front > scale - 1 || front < 0 || rear > scale - 1 || rear < 0) {
                    throw new IOException();
                }
                int offset = (rear - front) / Math.abs(rear - front);
                for (int val = front; true; val += offset) {
                    ret[idx++] = val;
                    if (val == rear) {
                        break;
                    }
                }
            } else {
                int p = Integer.valueOf(element);
                if (p > scale - 1 || p < 0) {
                    throw new IOException();
                }
                ret[idx++] = p;
            }
        }

        // prepare the return array
        int[] retVal = new int[idx];
        for (int i = 0; i < idx; i++) {
            retVal[i] = ret[i];
        }

        return retVal;
    }

    public static boolean openFile(String filename) {
        boolean retValue = false;
        BufferedReader br = null;

        // initialize new table with values in the old table
        int newTable[] = new int[gray2phase.length];
        for (int i = 0; i < gray2phase.length; i++) {
            newTable[i] = gray2phase[i];
        }
        try {
            br = new BufferedReader(new FileReader(filename));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                if (strLine.length() != 0 && strLine.charAt(0) != '#') {
                    String[] tokens = strLine.trim().split("[\t ]+");
                    for (String token : tokens) {
                        String lrhs[] = token.split("=");
                        if (lrhs.length != 2) {
                            throw new IOException();
                        }
                        int lhs[] = parseElement(lrhs[0]);
                        int rhs[] = parseElement(lrhs[1]);
                        if (lhs.length != rhs.length) {
                            throw new IOException();
                        }
                        for (int i = 0; i < lhs.length; i++) {
                            newTable[lhs[i]] = rhs[i];
                        }
                    }
                }
            }
            // update with the new table values
            for (int i = 0; i < gray2phase.length; i++) {
                gray2phase[i] = newTable[i];
            }
            retValue = true;
        } catch (Exception e) {
            // System.out.println("parse or IO error");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
            }
        }
        return retValue;
    }

    public void paintMichelson() {
        WritableRaster raster = canvas.getRaster();
        //System out to make sure the code run thru this function
        System.out.println("Enter the Michelson");
        int[] iArray = new int[1];
        double phase, x, y;

        double phy = Math.toRadians(mirrorPhy);
        double theta = Math.toRadians(mirrorTheta);


        double xcomp = Math.sin(phy) * Math.cos(theta);
        double ycomp = Math.sin(phy) * Math.sin(theta);

        //double fixpart = 2.0 * Math.PI / lambda;
        double fixpart = 5.0 * Math.PI / lambda;

        for (int i = 0; i < height; i++) {
            x = (double) (i - height / 2 + 1) * pxsize;
            x = xcomp * x;
            for (int j = 0; j < width; j++) {
                y = (double) (j - width / 2 + 1) * pxsize;
                y = ycomp * y;
                phase = fixpart * (x + y);

                iArray[0] = phase2gray(phase);
                raster.setPixel(j, i, iArray);
            }
        }
    }

    public void slit(int slit) {
        if (slit == 0) {
            slit = 1;
        }

        int lineWidth = (int) d_widthX;
        int lineHeight = (int) d_heightX;
        int lineRotation = (int) d_rotation;
        int linePostion = (int) d_postionX;
        int lineGray = (int) d_grayLevel;
        int spac = (int) d_spacing;
        Graphics2D g = (Graphics2D) canvas.getGraphics();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int NumRow = slit;
        int[] RowY;
        RowY = new int[NumRow];
        int DelY = canvas.getHeight() / NumRow;

        if (slit == 1) {
            for (int i = 0; i < RowY.length; i++) {
                // if (linePostion == 384) {
                RowY[i] = (linePostion - canvas.getHeight() / 2) + DelY / 2 + DelY * i;
//                } else {
//                    RowY[i] = DelY / 2 + DelY * i;
//                }
            }
        } else {

            for (int i = 0; i < RowY.length; i++) {

//                if (linePostion != 384) {
//                    System.out.println(">>>>>>>>>>>>>>>vaoooooooooo");
//                    RowY[i] = (linePostion - canvas.getHeight() / 2) + DelY / 2 + DelY * i;
//                } else {
                if (i == 0) {
                    RowY[i] = (linePostion - canvas.getHeight() / 2) + ((DelY) - spac / 2);
                } else {
                    RowY[i] = (linePostion - canvas.getHeight() / 2) + ((DelY) + spac / 2);
                }
            }
        }
        Rectangle rect2;
        for (int i = 0; i < NumRow; i++) {
            g = (Graphics2D) canvas.getGraphics();
            g.setColor(new Color(lineGray, lineGray, lineGray));
            rect2 = new Rectangle((canvas.getWidth() - lineWidth) / 2, RowY[i] - lineHeight / 2, lineWidth, lineHeight);
            g.rotate(Math.toRadians(lineRotation), rect2.x + rect2.width / 2, rect2.y + rect2.height / 2);
            g.draw(rect2);
            g.fill(rect2);
        }
    }

    public void signalProcessing() {

        int lineWidthX = (int) d_widthX;
        int lineHeightX = (int) d_heightX;
        int lineWidthY = (int) d_widthY;
        int lineHeightY = (int) d_heightY;
        int lineRotation = (int) d_rotation;
        int linePostionX = (int) d_postionX;
        int linePostionY = (int) d_postionY;
        int lineGray = (int) d_grayLevel;
        Graphics2D g = (Graphics2D) canvas.getGraphics();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int NumCol = 5;
        int NumRow = 2;
        int[] ColX;
        int[] RowY;
        ColX = new int[NumCol];
        int DelX = canvas.getWidth() / NumCol;
        for (int i = 0; i < ColX.length; i++) {
            //if (linePostionY > 0) {
            ColX[i] = (linePostionY - canvas.getWidth() / 2) + (DelX / 2 + DelX * i);
//            } else {
//                ColX[i] = (DelX / 2 + DelX * i);
//            }
        }
        RowY = new int[NumRow];
        int DelY = canvas.getHeight() / NumRow;
        for (int i = 0; i < RowY.length; i++) {
            // if (linePostionX > 0) {
            RowY[i] = (linePostionX - canvas.getHeight() / 2) + DelY / 2 + DelY * i;
//            } else {
//                RowY[i] = DelY / 2 + DelY * i;
//            }
        }
        Rectangle rect2;
        for (int i = 0; i < NumCol; i++) {
            g = (Graphics2D) canvas.getGraphics();
            g.setColor(new Color(lineGray, lineGray, lineGray));
            rect2 = new Rectangle(ColX[i] - lineWidthY / 2, (canvas.getHeight() - lineHeightY) / 2, lineWidthY, lineHeightY);
            g.rotate(Math.toRadians(lineRotation), rect2.x + rect2.width / 2, rect2.y + rect2.height / 2);
            g.draw(rect2);
            g.fill(rect2);
        }
        for (int i = 0; i < NumRow; i++) {
            g = (Graphics2D) canvas.getGraphics();
            g.setColor(new Color(lineGray, lineGray, lineGray));
            rect2 = new Rectangle((canvas.getWidth() - lineWidthX) / 2, RowY[i] - lineHeightX / 2, lineWidthX, lineHeightX);
            g.rotate(Math.toRadians(lineRotation), rect2.x + rect2.width / 2, rect2.y + rect2.height / 2);
            g.draw(rect2);
            g.fill(rect2);
        }
    }

    public void phaseRetarder(BufferedImage buffImg) {
        if (buffImg != null) {
//            try {
//                File file = new File("resources/jdclogo_150x155.png");
//                System.out.println(">>>>>>>>>>>>>>>>Path : " + file.getAbsolutePath());
//                buffImg = ImageIO.read(new File(file.getAbsolutePath()));
//                System.out.println("buff : "+buffImg.getWidth());
//            } catch (IOException ex) {
//                Logger.getLogger(PatternImage.class.getName()).log(Level.SEVERE, null, ex);
//            }

            double scale = 1.0;
            scale = d_zoom / 100.0D;
//        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//        ColorConvertOp op = new ColorConvertOp(cs, null);
//        BufferedImage gayBuff = op.filter(buffImg, null);

            Graphics2D g2 = (Graphics2D) canvas.getGraphics();
            g2.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            double canvasX = canvas.getWidth() / 2;
            double canvasY = canvas.getHeight() / 2;
            int imageWidth = buffImg.getWidth();
            int imageHeight = buffImg.getHeight();
            double x = (scale * imageWidth) / 2;
            double y = (scale * imageHeight) / 2;
            AffineTransform at = AffineTransform.getTranslateInstance(canvasX - x, canvasY - y);
            at.scale(scale, scale);
            g2.drawRenderedImage(buffImg, at);
        }
    }
}

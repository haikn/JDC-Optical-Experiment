/*
 * @(#)Utils.java
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
package com.jasper.utils;

import com.jasper.ui.EduPatternShowOn;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Utils class
 *
 * @version 1.0 28 Aug 2013
 *
 * @author sonnv
 *
 */
public class Utils {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String dateNow() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date).toString();
    }

    public static String readFile(String filePath) {
        String rs;
        FileInputStream fos;
        Reader reader;
        BufferedReader buferReader;
        StringBuilder text;
        try {
            fos = new FileInputStream(filePath);
            reader = new java.io.InputStreamReader(fos, "UTF-8");
            buferReader = new BufferedReader(reader);
            text = new StringBuilder();
            String line = null;
            while ((line = buferReader.readLine()) != null) {
                text.append(line + "\n");
            }
            reader.close();
            rs = text.toString();
        } catch (Exception ex) {
            rs = "";
            ex.printStackTrace();
        }
        return rs;
    }

    public static void writeFile(String path, String text, boolean orverWite) {
        try {
            String oldText = "";
            if (!orverWite) {
                oldText = readFile(path);
            }
            FileOutputStream fos = new FileOutputStream(path);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(oldText + text);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createDirectory(String fileName) {
        File file;
        File fileDirectory;
        try {
            fileDirectory = new File(Constant.FILE_PATH);
            fileDirectory.mkdir();
            file = new File(Constant.FILE_PATH, fileName);
            file.createNewFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setDevice() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = env.getScreenDevices();


        // if only one device is detected, then user selection is not required
        if (devices.length == 1) {
            EduPatternShowOn.device = devices[0];
            //JOptionPane.showMessageDialog(null, "Detect 1 screen " + devices[0].toString() + " -- isHeadlessInstance: " + env.isHeadlessInstance() + " -- isHeadless:" + GraphicsEnvironment.isHeadless());
            return;
        }
        EduPatternShowOn.device = devices[1];
        /*
        String shortenedlist[] = new String[devices.length];
        int cnt = 0;
        for (GraphicsDevice dev : devices) {
        JOptionPane.showMessageDialog(null, "Detect screen " + dev.toString() + " -- isHeadlessInstance: " + env.isHeadlessInstance() + " -- isHeadless: " + GraphicsEnvironment.isHeadless());
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
         */
        //JOptionPane.showMessageDialog(null, "Display on: " + EduPatternShowOn.device.toString());
    }

    public static Font getFont() {
        Font[] allfonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        String chinesesample = "\u4e00";
        for (int j = 0; j < allfonts.length; j++) {
            if (allfonts[j].canDisplayUpTo(chinesesample) == -1) {
                return new Font(allfonts[j].getFontName(), Font.PLAIN, 16);
            }
        }
        return new Font("Bitstream Cyberbit", Font.PLAIN, 16);
    }

    public static void createDirectoryLogFileCGH() {
        File fileCGH1;
        File fileCGH3;
        File fileCGH4;
        File fileCGH5;
        File fileCGH6;
        File fileCGH8;
        File fileCGH10;

        File fileAmplitude;
        File fileSignal;
        File fileTalbot;
        File fileStatic;
        File fileDynamic;

        File fileDirectory;
        try {
            fileDirectory = new File(Constant.FILE_PATH);
            fileDirectory.mkdir();

            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH1)) {
                fileCGH1 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH1);
                fileCGH1.createNewFile();
            }
            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH3)) {
                fileCGH3 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH3);
                fileCGH3.createNewFile();
            }
            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH4)) {
                fileCGH4 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH4);
                fileCGH4.createNewFile();
            }
            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH5)) {
                fileCGH5 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH5);
                fileCGH5.createNewFile();
            }
            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH6)) {
                fileCGH6 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH6);
                fileCGH6.createNewFile();
            }
            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH8)) {
                fileCGH8 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH8);
                fileCGH8.createNewFile();
            }
            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH10)) {
                fileCGH10 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH10);
                fileCGH10.createNewFile();
            }
            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_AMPLITUDE)) {
                fileAmplitude = new File(Constant.FILE_PATH, Constant.FILE_NAME_AMPLITUDE);
                fileAmplitude.createNewFile();
            }
            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_SIGNAL)) {
                fileSignal = new File(Constant.FILE_PATH, Constant.FILE_NAME_SIGNAL);
                fileSignal.createNewFile();
            }
            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_TALBOT)) {
                fileTalbot = new File(Constant.FILE_PATH, Constant.FILE_NAME_TALBOT);
                fileTalbot.createNewFile();
            }

            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_STATIC)) {
                fileStatic = new File(Constant.FILE_PATH, Constant.FILE_NAME_STATIC);
                fileStatic.createNewFile();
            }

            if (checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_DYNAMIC)) {
                fileDynamic = new File(Constant.FILE_PATH, Constant.FILE_NAME_DYNAMIC);
                fileDynamic.createNewFile();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean checkFileExists(String fileName) {
        boolean rs = false;
        File file;
        try {
            file = new File(Constant.FILE_PATH, fileName);
            if (!file.exists()) {
                rs = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") >= 0);
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

    public static String getCurrentLocation() {
        //return EduPatternShowOn.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (isWindows())
            return System.getProperty("user.dir") + "\\";
        else return System.getProperty("user.dir") + "/";
    }

    public static File[] getAllProjectFiles() {
        File classpathRoot = new File(getCurrentLocation());
        File[] prjFiles = classpathRoot.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".prj");
            }
        });
        return prjFiles;
    }
}

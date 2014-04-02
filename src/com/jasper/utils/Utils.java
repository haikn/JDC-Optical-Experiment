/*
 * @(#)Utils.java
 *
 * Copyright (c) 2013 JASPER DISPLAY, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * JASPER DISPLAY PROPRIETARY:  Distribution of this source code
 * without permission from the copyright holder is strictly forbidden.
 */

package com.jasper.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utils class
 *
 * @version 1.0 28 Aug 2013
 *
 * @author Albert Nguyen
 *
 */
public class Utils {
    
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
        
        File fileDirectory;
        try {
            fileDirectory = new File(Constant.FILE_PATH);
            fileDirectory.mkdir();
            
            if(checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH1)) {
                fileCGH1 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH1);
                fileCGH1.createNewFile();
            } 
            if(checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH3)) {
                fileCGH3 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH3);
                fileCGH3.createNewFile();
            }
            if(checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH4)) {
                fileCGH4 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH4);
                fileCGH4.createNewFile();
            }
            if(checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH5)) {
                fileCGH5 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH5);
                fileCGH5.createNewFile();
            }
            if(checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH6)) {
                fileCGH6 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH6);
                fileCGH6.createNewFile();
            }
            if(checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH8)) {
                fileCGH8 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH8);
                fileCGH8.createNewFile();
            }
            if(checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_CGH10)) {
                fileCGH10 = new File(Constant.FILE_PATH, Constant.FILE_NAME_CGH10);
                fileCGH10.createNewFile();
            }
            if(checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_AMPLITUDE)) {
                fileAmplitude = new File(Constant.FILE_PATH, Constant.FILE_NAME_AMPLITUDE);
                fileAmplitude.createNewFile();
            }
            if(checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_SIGNAL)) {
                fileSignal = new File(Constant.FILE_PATH, Constant.FILE_NAME_SIGNAL);
                fileSignal.createNewFile();
            }
            if(checkFileExists(Constant.FILE_PATH + File.separator + Constant.FILE_NAME_TALBOT)) {
                fileTalbot = new File(Constant.FILE_PATH, Constant.FILE_NAME_TALBOT);
                fileTalbot.createNewFile();
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
}

/*
 * @(#)Constant.java
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

import java.io.File;

/**
 * Constant class
 *
 * @version 1.0 28 Aug 2013
 *
 * @author Albert Nguyen
 *
 */
public class Constant {
    // path and file name of CGH log
    public static final String FILE_PATH                = System.getProperty("user.home") + File.separator + "JDCKit_Log";
    public static final String FILE_NAME_CGH1           = "CGH1_Log.txt";
    public static final String FILE_NAME_CGH3           = "CGH3_Log.txt";
    public static final String FILE_NAME_CGH4           = "CGH4_Log.txt";
    public static final String FILE_NAME_CGH5           = "CGH5_Log.txt";
    public static final String FILE_NAME_CGH6           = "CGH6_Log.txt";
    public static final String FILE_NAME_CGH8           = "CGH8_Log.txt";
    public static final String FILE_NAME_CGH10          = "CGH10_Log.txt";
    //  path and file name of Experiment log
    public static final String FILE_NAME_AMPLITUDE      = "AMPLITUDE_Log.txt";
    public static final String FILE_NAME_SIGNAL         = "SIGNAL_Log.txt";
    public static final String FILE_NAME_TALBOT         = "TALBOT_Log.txt";
    public static final String FILE_NAME_STATIC         = "STATIC_Log.txt";
    public static final String FILE_NAME_DYNAMIC        = "DYNAMIC_Log.txt";
    // format log file content CGH
    public static final String TEXT_FORMAT_CGH          = "+ \n";
    public static final String LOG_NAME                 = "|Name: ";
    public static final String LOG_DATE                 = "|Date: ";
    
    
    
}

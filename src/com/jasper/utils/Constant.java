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
    // Lens ON
    public static final int LENS_ON_PANEL_WIDTH             = 120;
    public static final int LENS_ON_PANEL_HEIGHT            = 120;
    public static final int LENS_ON_LOCAL_X                 = 545;
    public static final int LENS_ON_LOCAL_Y                 = 507;
    public static final int LENS_ON_MOUSE_X                 = 550;
    public static final int LENS_ON_MOUSE_Y                 = 510;
    
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
    // format log file content CGH
    public static final String TEXT_FORMAT_CGH          = "+ \n";
    public static final String LOG_NAME                 = "|Name: ";
    public static final String LOG_DATE                 = "|Date: ";
    
}

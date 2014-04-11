/*
 * @(#)KeyReaderTrial.java
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
package com.jasper.core;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 *
 * @author sonnv
 */
public class KeyReaderTrial {
    
    /**
     * @author sonnv
     * @Function: verifyKey()
     * @Param: no parameters
     * @return boolean: true if still in trial time. false if not
     * @throws IOException 
     */
    public boolean verifyKey() throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("resources/config");
        String key = bundle.getString("KEY");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String endDate = dateFormat.format(date).toString();
        String y = endDate.substring(0, 4);
        String d = endDate.substring(8, 10);
        String m = endDate.substring(5, 7);
        int dCompare = Integer.parseInt(d);
        if(key == null || key.equals("")) {
                return false;
        } else {
            if(key.equals("trialversion") && y.equals("2014")) {
                if(m.equals("02")) {
                    if( dCompare <= 28) {
                        return true;
                    }
                } 
                else if (m.equals("03")){
                    if( dCompare <= 31) {
                        return true;
                    }
                }
                else if (m.equals("04")){
                    if( dCompare <= 30) {
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        }
    }
}

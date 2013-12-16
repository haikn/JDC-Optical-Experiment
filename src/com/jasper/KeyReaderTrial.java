/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jasper;

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
            if(key.equals("trialversion")) {
                if(m.equals("12") && y.equals("2013")) {
                    if( dCompare >= 15) {
                        return true;
                    }
                } else if (m.equals("1") && y.equals("2014")){
                    if( dCompare <= 31) {
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

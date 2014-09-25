/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jasper.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author trancongly
 */
public class Macro {

    private double fixValue;
    private ArrayList<Param> params = new ArrayList<Param>();
    private double zoom;
    private double rotate;
    private String phase;
    private String xOff;
    private String yOff;
    private String functionName;
    private static String CONSTANT = "%constant";
    private static String PARAM = "%param";
    private static String MATRIX = "%matrix";
    private static String FUNCTION = "%function";

    public Macro() {
    }

    public Macro(String fileName) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "=";
        String commaSplit = ",";
        int type = 0;

        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                if (line.equals(CONSTANT)) {
                    type = 0;
                    continue;
                } else if (line.equals(PARAM)) {
                    type = 1;
                    continue;
                } else if (line.equals(MATRIX)) {
                    type = 2;
                    continue;
                } else if (line.equals(FUNCTION)) {
                    type = 3;
                    continue;
                }

                if (type == 0) {
                    String[] lineData = line.split(cvsSplitBy);
                    fixValue = Double.parseDouble(lineData[1]);
                } else if (type == 1) {
                    String[] lineData = line.split(cvsSplitBy);
                    Param param = new Param();
                    param.setName(lineData[0]);
                    
                    /*
                     * The regex means:

                        \\(: character (
                        (: start match group
                        [: one of these characters
                        ^: not the following character
                        ): with the previous ^, this means "every character except )"
                        +: one of more of the stuff from the [] set
                        ): stop match group
                        \\): literal closing paranthesis
                     */
                    Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(lineData[1]);
                    while (m.find()) {
                        String paramStr = m.group(1);
                        String[] subParam = paramStr.split(commaSplit);
                        param.setSubName(subParam[0].replace("\"", ""));
                        param.setMin(Double.parseDouble(subParam[1]));
                        param.setMax(Double.parseDouble(subParam[2]));
                        param.setStep(Double.parseDouble(subParam[3]));                        
                    }
                    params.add(param);
                } else if (type == 2) {
                    if (line.contains("meshgrid")) {
                        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(line);
                        while (m.find()) {
                            String paramStr = m.group(1);
                            String[] subParam = paramStr.split(commaSplit);
                            zoom = Double.parseDouble(subParam[0]);
                            rotate = Double.parseDouble(subParam[0]);
                        } 
                    } else if (line.contains("xt")) {
                        xOff = line;
                    } else if (line.contains("yt")) {
                        yOff = line;
                    }
                } else if (type == 3) {
                    functionName = line;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setFixValue(double fixValue) {
        this.fixValue = fixValue;
    }

    public double getFixValue() {
        return this.fixValue;
    }

    public void setParam(ArrayList<Param> param) {
        this.params = param;
    }

    public ArrayList<Param> getParam() {
        return this.params;
    }
    
    public void setZoom(double zoom) {
        this.zoom = zoom;
    }
    
    public double getZoom() {
        return this.zoom;
    }
    
    public void setRot(double rot) {
        this.rotate = rot;
    }
    
    public double getRot() {
        return this.rotate;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getPhase() {
        return this.phase;
    }
    
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return this.functionName;
    }
}

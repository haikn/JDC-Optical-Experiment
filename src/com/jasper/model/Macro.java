/*
 * @(#)Macro.java
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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jasper.model;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.mbertoli.jfep.ParseError;
import org.mbertoli.jfep.Parser;

/**
 *
 * @author trancongly
 */
public class Macro {

    private double fixValue;
    private ArrayList<Param> params = new ArrayList<Param>();
    private double zoom = 0;
    private double rotate = 0;
    private String phase;
    private String functionName;
    
    private Map<String, Double> variables = new HashMap<>();
    //private String meshgrid;
    //private ArrayList<Double> meshgridParam = new ArrayList<>();
    private ArrayList<String> meshgridParam = new ArrayList<>();
    //private Map<String, Double> matrix = new HashMap<>();
    private Map<String, String> matrix = new HashMap<>();
    private String wavefront;
    private static String CONSTANT = "%constant";
    private static String PARAM = "%param";
    private static String MESHGRID = "%meshgrid";
    private static String MATRIX = "%matrix";
    private static String FUNCTION = "%function";
    private static String REGEXPARAM = "(^[A-Za-z0-9\\+]+)\\(([^)]+)\\,([^)]+)\\,([^)]+)\\,([^)]+)\\,([^)]+)\\)";
    private static String REGEXMESHGRID = "(^[A-Za-z0-9\\+]+)\\(([^)]+)\\,([^)]+)\\)";
    private static String REGEXWAVEFRONT = "(^[A-Za-z0-9\\+]+)\\((.+?)\\)$";
    private static String MESHGRIDKEY = "meshgrid";
    private static String EXP = "exp";

    public Macro() {
    }

    public Macro(String fileName) throws FileNotFoundException, IOException {
        BufferedReader br = null;
        String line = "";
        String rotateVariable = "";
        String zoomVariable = "";
        String meshgridVariable = "";
        int type = 0;
        Properties props = new Properties();
        Map<String, String> preMatrix = new HashMap<>();
        
        //Read macro file
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Macro file is not available.");
            e.printStackTrace();
        }
        try {
            while ((line = br.readLine()) != null) {
                //Analyse macro file
                
                if (line.toLowerCase().equals(CONSTANT)) {
                    type = 0;
                    continue;
                } else if (line.toLowerCase().equals(PARAM)) {
                    type = 1;
                    continue;
                } else if(line.toLowerCase().equals(MESHGRID)) {
                    type = 2;
                    continue;
                } else if (line.toLowerCase().equals(MATRIX)) {
                    type = 3;
                    continue;
                } else if (line.toLowerCase().equals(FUNCTION)) {
                    type = 4;
                    continue;
                }

                if (type == 0) {
                    //Constant processing
                    props.clear();
                    props.load(new StringReader(line));
                    Enumeration<?> enumer = props.propertyNames();
                    while (enumer.hasMoreElements()) {
                        String constantKey = (String) enumer.nextElement();
                        String constantValueStr = props.getProperty(constantKey);
                        Parser constantParser = new Parser(constantValueStr);
                        Double constantValue;
                        try {
                            constantValue = constantParser.getValue();
                        } catch (ParseError exc) {
                            JOptionPane.showMessageDialog(new JFrame(), "One of constants is incorrect. Please check!");
                            throw new IllegalArgumentException();
                        }
                        //constants.put(constantKey, constantValue);
                        variables.put(constantKey, constantValue);
                    }
                } else if (type == 1) {
                    //Parameter processing
                    props.clear();
                    props.load(new StringReader(line));
                    Enumeration<?> enumer = props.propertyNames();
                    Param param = new Param();
                    
                    while (enumer.hasMoreElements()) {
                        String paramKey = (String) enumer.nextElement();
                        String paramValue = props.getProperty(paramKey);
                        param.setName(paramKey);
                        //Parse params
                        //Clean paramValue first
                        //paramValue = paramValue.replaceAll("\\s","");
                        
                        Pattern paramsPattern = Pattern.compile(REGEXPARAM);
                        Matcher paramsMatcher = paramsPattern.matcher(paramValue);
                        if(paramsMatcher.find()) {
                            param.setSubName(stringCapitalize(paramsMatcher.group(2).replaceAll("\"", "")));
                            param.setMin(Double.parseDouble(paramsMatcher.group(3).replaceAll("\\s", "")));
                            param.setMax(Double.parseDouble(paramsMatcher.group(4).replaceAll("\\s", "")));
                            param.setStep(Double.parseDouble(paramsMatcher.group(5)));
                            param.setCurrentValue(Double.parseDouble(paramsMatcher.group(6).replaceAll("\\s", "")));
                            
                            variables.put(paramKey, Double.parseDouble(paramsMatcher.group(6)));
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "One of paramters is incorrect. Please check!");
                            throw new IllegalArgumentException();
                        }
                        params.add(param);
                    } //End while
                    
                } else if (type == 2) {
                    //Meshgrid processing 
                    props.clear();
                    props.load(new StringReader(line));
                    Enumeration<?> enumer = props.propertyNames();
                    while (enumer.hasMoreElements()) {
                        String meshgridKey = (String) enumer.nextElement();
                        //remove ; at the end of the line if need
                        meshgridVariable = props.getProperty(meshgridKey);
                        meshgridVariable = meshgridVariable.replaceAll(";", "");
                        Pattern meshgridPattern = Pattern.compile(REGEXMESHGRID);
                        Matcher meshgridMatcher = meshgridPattern.matcher(meshgridVariable);
                        if(meshgridMatcher.find()) {
                            if(!meshgridMatcher.group(1).trim().equals(MESHGRIDKEY)) {
                                JOptionPane.showMessageDialog(new JFrame(), "Meshgrid is incorrect. Please check!");
                                throw new IllegalArgumentException();
                            }
                            zoomVariable = meshgridMatcher.group(2);
                            rotateVariable = meshgridMatcher.group(3);
                            meshgridParam.add(zoomVariable);
                            meshgridParam.add(rotateVariable);
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Meshgrid is incorrect. Please check!");
                            throw new IllegalArgumentException();
                        }
                    }
                    
                } else if (type == 3) {
                    //Matrix processing
                    props.clear();
                    props.load(new StringReader(line));
                    Enumeration<?> enumer = props.propertyNames();
                    while (enumer.hasMoreElements()) {
                        String matrixKey = (String) enumer.nextElement();
                        String matrixValue = props.getProperty(matrixKey);
                        matrixValue = matrixValue.replaceAll(";", "");
                        String[] parts = matrixValue.split("-");
                        if(parts[1] != null) {
                            //preMatrix.put(matrixKey, parts[1]);
                            matrix.put(matrixKey, parts[1]);
                        }
                        //matrix.put(matrixKey, matrixValue);
                    }
                    
                } else if (type == 4) {
                    //Function processing
                    props.clear();
                    props.load(new StringReader(line));
                    Enumeration<?> enumer = props.propertyNames();
                    while (enumer.hasMoreElements()) {
                        String wavefrontKey = (String) enumer.nextElement();
                        wavefront = props.getProperty(wavefrontKey);
                        //wavefront = wavefront.replaceAll(";", "");
                    }
                }
            } //End while read line
            
            if(params.size() > 10) {
                JOptionPane.showMessageDialog(new JFrame(), "The application just supports less than 10 paramerters");
                throw new IllegalArgumentException();
            }
            
            //Add more fixed variables
            variables.put("pi", Math.PI);
            variables.put("j", (double)1);
            variables.put("i", (double)1);
            variables.put("e", Math.E);
            
            
            //Check if rotation is defined
            if(variables.containsKey(rotateVariable)) {
                rotate = (double)variables.get(rotateVariable);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "One variable is undefined!");
                throw new IllegalArgumentException();
            }
            
            //Check if zoom is defined or constant
            if(zoomVariable.matches("-?\\d+(\\.\\d+)?")) {
                zoom = Double.parseDouble(zoomVariable);
            } else {
                if(variables.containsKey(zoomVariable)) {
                    zoom = variables.get(zoomVariable);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "One variable is undefined!");
                    throw new IllegalArgumentException();
                }
            }
            
            //Check if shifting is defined
            /*
            for(String matrixVal: preMatrix.keySet()) {
                if(variables.containsKey(preMatrix.get(matrixVal))) {
                    matrix.put(matrixVal, variables.get(preMatrix.get(matrixVal)));
                } else {
                    matrix.put(matrixVal, 0.0);
                }
            }
                    */
            
            //Wave front analyze
            Parser wavefrontParser = new Parser(wavefront);
            //Get the real formula of wavefront
            if(wavefrontParser == null) {
                JOptionPane.showMessageDialog(new JFrame(), "No wavefront found!");
                throw new IllegalArgumentException();
            }
            
            //Check wavefront function name
            Set<String> wavefrontFunctionName = new HashSet<>();
            try {
                wavefrontFunctionName = wavefrontParser.getParsedFunctions();
            } catch(ParseError exc) {
                JOptionPane.showMessageDialog(new JFrame(), exc.getMessage());
                throw new IllegalArgumentException();
            }
            
            if(!wavefrontFunctionName.contains(EXP)) {
                JOptionPane.showMessageDialog(new JFrame(), "Wavefront function name is incorrect!");
                throw new IllegalArgumentException();
            }
            
            //String wavefrontFormula = "";
            String wavefrontFormula;
            
            try {
                wavefrontFormula = wavefrontParser.getTree().getChildrenNodes()[0].toString();
            } catch (ParseError exc) {
                JOptionPane.showMessageDialog(new JFrame(), exc.getMessage());
                throw new IllegalArgumentException();
            }
            //Real wavefront formula
            wavefront = wavefrontFormula;
                                    
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error in reading macro file.");
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

    public void setVariables(Map<String, Double> variables) {
        this.variables = variables;
    }
    public void setVariable(String variableKey, Double variableValue) {
        this.variables.put(variableKey, variableValue);
    }
    
    public Map<String, Double> getVariables() {
        return this.variables;
    }
    
    public double getVariable(String variableKey) {
        return this.variables.get(variableKey);
    }
    
    public void setParam(ArrayList<Param> param) {
        this.params = param;
    }

    public ArrayList<Param> getParam() {
        return this.params;
    }
    
    public void setMeshgrid(ArrayList meshgrid) {
        this.meshgridParam = meshgrid;
    }
    
    public ArrayList getMeshgrid() {
        return this.meshgridParam;
    }
    
    public void setMatrix(Map<String, String> matrix) {
        this.matrix = matrix;
    }
    
    public Map<String, String> getMatrix() {
        return this.matrix;
    }
    
    public void setWavefront(String wavefront) {
        this.wavefront = wavefront;
    }
    
    public String getWavefront() {
        return this.wavefront;
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
    
    private String stringCapitalize(String string) {
        string = string.replaceAll("\\s+"," ");
        String[] split = string.split(" ");
        string="";
        for (int i = 0; i < split.length; i++) {
            split[i]=Character.toUpperCase(split[i].charAt(0))+split[i].substring(1);
            string+=split[i]+" ";
        }
        return string;
    }
}

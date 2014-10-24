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
import java.util.Arrays;
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
import net.objecthunter.exp4j.ExpressionBuilder;
import org.mbertoli.jfep.ParseError;
import org.mbertoli.jfep.Parser;

/**
 *
 * @author trancongly
 */
public class Macro {

    private double fixValue;
    private ArrayList<Param> params = new ArrayList<>();
    private double zoom = 0;
    private double rotate = 0;
    private String phase;
    private String functionName;
    
    private ArrayList<String> mathConstant = new ArrayList<>();
    private Map<String, Double> variables = new HashMap<>();
    //private String meshgrid;
    //private ArrayList<Double> meshgridParam = new ArrayList<>();
    private ArrayList<String> meshgridParam = new ArrayList<>();
    //private Map<String, Double> matrix = new HashMap<>();
    private Map<String, String> matrix = new HashMap<>();
    private Map<String, String> shifting = new HashMap<>();
    private Map<String, String> meshgridKeys = new HashMap<>();
    private Map<String, Double> spacingX = new HashMap<>();
    private Map<String, Double> spacingY = new HashMap<>();
    private ArrayList<String> X = new ArrayList<>();
    private ArrayList<String> Y = new ArrayList<>();
    private ArrayList<String> XY = new ArrayList<>();
    
    private String wavefront;
    private String slitpattern;
    private static String CONSTANT = "%constant";
    private static String PARAM = "%param";
    private static String MESHGRID = "%meshgrid";
    private static String MATRIX = "%matrix";
    private static String FUNCTION = "%function";
    private static String REGEXPARAM = "(^[A-Za-z0-9\\+]+)\\(([^)]+)\\,([^)]+)\\,([^)]+)\\,([^)]+)\\,([^)]+)\\)$";
    private static String REGEXMESHGRID = "(^[A-Za-z0-9\\+]+)\\(([^)]+)\\)";//"(^[A-Za-z0-9\\+]+)\\(([^)]+)\\,([^)]+)(,([^)]))?\\)";
    private static String REGEXWAVEFRONT = "(exp)\\((.+?)\\)$";
    private static String REGEXPATTERN = "((rect)\\((.+?)\\))";
    private static String REGEXMESHGRIDKEY = "^\\[([A-Za-z0-9_]+),([A-Za-z0-9_]+)\\]$";
    private static String REGEXPOWER = "(([A-Za-z0-9]+)\\^([0-9]+))";
    private static final Pattern DOUBLE_PATTERN = Pattern.compile(
    "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
    "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|" +
    "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
    "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");
    private static String MESHGRIDKEY = "meshgrid";
    

    public Macro() {
    }

    public Macro(String fileName) throws FileNotFoundException, IOException {
        BufferedReader br = null;
        String line = "";
        String meshgridVariable = "";
        String meshgridParams = "";
        String wavefrontDraft = "";
        String[] meshgridParamsList = null;
        ArrayList<String> leftParams = new ArrayList<>();
        ArrayList<String> rightParams = new ArrayList<>();
        int type = 0;
        Properties props = new Properties();
        
        mathConstant.add("pi");
        mathConstant.add("j");
        mathConstant.add("i");
        mathConstant.add("e");
        
        
        //Add more fixed variables
        variables.put("pi", Math.PI);
        variables.put("j", (double)1);
        variables.put("i", (double)1);
        variables.put("e", Math.E);
        
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
                        leftParams.add(constantKey.replaceAll("\\s", ""));
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
                            try {
                                param.setSubName(stringCapitalize(paramsMatcher.group(2).replaceAll("\"", "")));
                                param.setMin(Double.parseDouble(paramsMatcher.group(3).replaceAll("\\s", "")));
                                param.setMax(Double.parseDouble(paramsMatcher.group(4).replaceAll("\\s", "")));
                                param.setStep(Double.parseDouble(paramsMatcher.group(5)));
                                param.setCurrentValue(Double.parseDouble(paramsMatcher.group(6).replaceAll("\\s", "")));
                                variables.put(paramKey, Double.parseDouble(paramsMatcher.group(6)));
                            } catch (Exception exc) {
                                JOptionPane.showMessageDialog(new JFrame(), "One of paramters is incorrect. Please check!");
                                throw new IllegalArgumentException(exc);
                            }
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "One of paramters is incorrect. Please check!");
                            throw new IllegalArgumentException();
                        }
                        params.add(param);
                        leftParams.add(paramKey.replaceAll("\\s", ""));
                    } //End while
                    
                } else if (type == 2) {
                    //Meshgrid processing 
                    props.clear();
                    props.load(new StringReader(line));
                    Enumeration<?> enumer = props.propertyNames();
                    while (enumer.hasMoreElements()) {
                        String meshgridKey = (String) enumer.nextElement();
                        //Add meshgrid key in leftVariable
                        Pattern meshgridKeyPattern = Pattern.compile(REGEXMESHGRIDKEY);
                        Matcher meshgridKeyMatcher = meshgridKeyPattern.matcher(meshgridKey);
                        if(meshgridKeyMatcher.find()) {
                            leftParams.add(meshgridKeyMatcher.group(1).replaceAll("\\s", ""));
                            leftParams.add(meshgridKeyMatcher.group(2).replaceAll("\\s", ""));
                            meshgridKeys.put("x", meshgridKeyMatcher.group(1).replaceAll("\\s", ""));
                            meshgridKeys.put("y", meshgridKeyMatcher.group(2).replaceAll("\\s", ""));
                            //variables.put(meshgridKeyMatcher.group(1).replaceAll("\\s", ""), 0.0);
                            //variables.put(meshgridKeyMatcher.group(2).replaceAll("\\s", ""), 0.0);
                            //meshgridKeys.add(meshgridKeyMatcher.group(1).replaceAll("\\s", ""));
                            //meshgridKeys.add(meshgridKeyMatcher.group(2).replaceAll("\\s", ""));
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Meshgrid is incorrect. Please check!");
                            throw new IllegalArgumentException();
                        }
                        
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
                            
                            meshgridParams = meshgridMatcher.group(2);
                            meshgridParamsList = meshgridParams.split(",");

                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Meshgrid is incorrect. Please check!");
                            throw new IllegalArgumentException();
                        }
                        
                    }//End while
                    
                } else if (type == 3) {
                    //Matrix processing
                    props.clear();
                    props.load(new StringReader(line));
                    Enumeration<?> enumer = props.propertyNames();
                    while (enumer.hasMoreElements()) {
                        String matrixKey = (String) enumer.nextElement();
                        String matrixValue = props.getProperty(matrixKey);
                        Parser matrixParser = new Parser(matrixValue);
                        HashSet parsedVariables = matrixParser.getParsedVariables();
                        rightParams.addAll(parsedVariables);

                        leftParams.add(matrixKey.replaceAll("\\s", ""));
                        matrix.put(matrixKey, matrixValue.replace(";", ""));
                    }
                    
                } else if (type == 4) {
                    //Function processing
                    props.clear();
                    props.load(new StringReader(line));
                    Enumeration<?> enumer = props.propertyNames();
                    while (enumer.hasMoreElements()) {
                        String wavefrontKey = (String) enumer.nextElement();
                        wavefrontDraft = props.getProperty(wavefrontKey);
                        wavefrontDraft = wavefrontDraft.replaceAll(";", "");
                    }
                }
            } //End while read line
            
            
            if(params.size() > 10) {
                JOptionPane.showMessageDialog(new JFrame(), "The application just supports less than 10 paramerters");
                throw new IllegalArgumentException();
            }
            
            //Meshgrid analyze
            for(int i = 0; i < meshgridParamsList.length; i++) {
                if(!DOUBLE_PATTERN.matcher(meshgridParamsList[i]).matches()) {
                    rightParams.add(meshgridParamsList[i].replaceAll("\\s", ""));
                }
                meshgridParam.add(meshgridParamsList[i].replaceAll("\\s", ""));
            }
            
            //Matrix analyze
            if(matrix.size() > 0) {
                for(Entry entry: matrix.entrySet()) {
                    Parser matrixParser = new Parser(entry.getValue().toString());
                    HashSet<String> matrixParsedVariables = matrixParser.getParsedVariables();
                    if(matrixParsedVariables.contains(meshgridKeys.get("x"))) {
                        //shifting.put("px", matrixShiftingResult);
                        matrixParsedVariables.remove(meshgridKeys.get("x"));
                        shifting.put("x", entry.getKey().toString());
                        matrixParser.setVariable("x", 0);
                        for(String entryX: matrixParsedVariables) {
                            if(variables.keySet().contains(entryX)) {
                                if(entryX.equalsIgnoreCase("px")) {
                                    shifting.put("px", entryX);
                                    matrixParser.setVariable("px", 0);
                                } else {
                                    shifting.put("spacingx", entryX);
                                    matrixParser.setVariable("spacingx", variables.get("spacingx"));
                                }
                            }
                        }
                        spacingX.put(entry.getKey().toString(), matrixParser.getValue());
                        //XY[] = "x";
                        X.add(entry.getKey().toString());
                        
                    } else if (matrixParsedVariables.contains(meshgridKeys.get("y"))){
                        shifting.put("y", entry.getKey().toString());
                        matrixParser.setVariable("y", 0);
                        for(String entryY: matrixParsedVariables) {
                            if(variables.keySet().contains(entryY)) {
                                if(entryY.equalsIgnoreCase("py")) {
                                    shifting.put("py", entryY);
                                    matrixParser.setVariable("py", 0);
                                } else {
                                    shifting.put("spacingy", entryY);
                                    matrixParser.setVariable("spacingy", variables.get("spacingy"));
                                }
                            }
                        }
                        spacingY.put(entry.getKey().toString(), matrixParser.getValue());
                        Y.add(entry.getKey().toString());
                    }
                }
            }            
            
            //Wave front analyze
            String wavefrontFunctionString = "";
            Set<String> wavefrontVariableName = new HashSet<>();
            Pattern wavefrontPattern = Pattern.compile(REGEXWAVEFRONT);
            Pattern patternPattern = Pattern.compile(REGEXPATTERN);
            Matcher wavefrontMatcher = wavefrontPattern.matcher(wavefrontDraft);
            Matcher patternMatcher = patternPattern.matcher(wavefrontDraft);

            if(wavefrontMatcher.find()) {
                //For wavefront
                functionName = wavefrontMatcher.group(1);
                wavefrontFunctionString = wavefrontMatcher.group(2);
                Parser wavefrontParser;
                try {
                    wavefrontParser = new Parser(wavefrontFunctionString);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(new JFrame(), "Function not found!");
                    throw new IllegalArgumentException();
                }

                //Check wavefront function variables

                try {
                    wavefrontVariableName = wavefrontParser.getParsedVariables();
                    rightParams.addAll(wavefrontVariableName);
                } catch(ParseError exc) {
                    JOptionPane.showMessageDialog(new JFrame(), exc.getMessage());
                    throw new IllegalArgumentException();
                }

                //Real wavefront formula
                /*
                wavefront = wavefrontFunctionString.replaceAll(REGEXPOWER, "pow($2,$3)");
                for(Entry entry: matrix.entrySet()) {
                    wavefront = wavefront.replace(entry.getKey().toString(), entry.getValue().toString());
                }
                */
                wavefront = wavefrontFunctionString;
                
            } else if(patternMatcher.find()){
                functionName = patternMatcher.group(2);
                slitpattern = wavefrontDraft;
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Function is incorrect");
                throw new IllegalArgumentException();
            }
                                    
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
        
        leftParams.addAll(Arrays.asList("pi","e","j","i","pixel","mm","m","um"));
        
        if(!leftParams.containsAll(rightParams)) {
            JOptionPane.showMessageDialog(new JFrame(), "Undefined variable");
            throw new IllegalArgumentException();
        }
        
        //Refine slit pattern
        //Assign values from variables to slit
        /*
        if(slitpattern != null) {
            for(Entry variable: variables.entrySet()) {
                if(!mathConstant.contains(variable.getKey().toString())) {
                    slitpattern = slitpattern.replace(variable.getKey().toString(), variable.getValue().toString());
                }
            }
        }
        */
        
    }
    
    public void setSlitPattern(String slitpattern) {
        this.slitpattern = slitpattern;
    }
    
    public String getSlitPattern() {
        return this.slitpattern;
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
    
    public void setShifting(Map<String, String> shifting) {
        this.shifting = shifting;
    }
    
    public Map<String, String> getShifting() {
        return this.shifting;
    }
    
    public void setSpacingX(Map<String, Double> spacing) {
        this.spacingX = spacing;
    }
    
    public Map<String, Double> getSpacingX() {
        return this.spacingX;
    }
    
    public void setSpacingY(Map<String, Double> spacing) {
        this.spacingY = spacing;
    }
    
    public Map<String, Double> getSpacingY() {
        return this.spacingY;
    }
    
    public void setXY(ArrayList<String> xy) {
        this.XY = xy;
    }
    
    public ArrayList<String> getXY() {
        return this.XY;
    }
    
    public void setX(ArrayList<String> x) {
        this.X = x;
    }
    
    public ArrayList<String> getX() {
        return this.X;
    }
    
    public void setY(ArrayList<String> y) {
        this.Y = y;
    }
    
    public ArrayList<String> getY() {
        return this.Y;
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

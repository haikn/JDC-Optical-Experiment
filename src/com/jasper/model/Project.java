/*
 * @(#)Project.java
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

import com.jasper.utils.Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author trancongly
 */
public class Project {

    private String name;
    private String location;
    private String macro;
    private String diagram;
    private String language;
    private String description;
    private final String PROJECT = "Project";
    private final String MACRO = "Macro";
    private final String DIAGRAM = "Diagram";
    private final String LANGUAGE = "Language";
    private final String DESCRIPTION = "Description";

    public Project() {
    }

    public Project(String project) {

        Properties prop = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(project);
            prop.load(fileInputStream);
            //prop.list(System.out);
            name = prop.getProperty(PROJECT);
            macro = prop.getProperty(MACRO);
            diagram = prop.getProperty(DIAGRAM);
            description = prop.getProperty(DESCRIPTION);
            language = prop.getProperty(LANGUAGE);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public Project(String name, String location, String macro, String diagram, String language, String description) {
        this.name = name;
        this.location = location;
        this.macro = macro;
        this.diagram = diagram;
        this.language = language;
        this.description = description;
    }
    
    public Project(String name, String macro, String diagram, String language, String description) {
        this.name = name;
        this.macro = macro;
        this.diagram = diagram;
        this.language = language;
        this.description = description;
    }

    public void writeToFile() {
        Properties prop = new Properties();
        try {
            String fName = Utils.getCurrentLocation() + name + ".prj";
            //FileInputStream fileInputStream = new FileInputStream(fName);
            FileOutputStream fileOutputStream = new FileOutputStream(fName);
            prop.setProperty(PROJECT, name);
            prop.setProperty(MACRO, macro);
            prop.setProperty(DIAGRAM, diagram);
            prop.setProperty(LANGUAGE, language);
            prop.setProperty(DESCRIPTION, description);
            prop.store(fileOutputStream, fName);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setMacro(String macro) {
        this.macro = macro;
    }

    public String getMacro() {
        return this.macro;
    }

    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }

    public String getDiagram() {
        return this.diagram;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getDescription() {
        return this.description;
    }
    
    public String getProjectAttribute() {
        return this.PROJECT;
    }
    
    public String getMacroAttribute() {
        return this.MACRO;
    }
    
    public String getDiagramAttribute() {
        return this.DIAGRAM;
    }
    
    public String getDescriptionAttribute() {
        return this.DESCRIPTION;
    }
    
    public String getLanguageAttribute() {
        return this.LANGUAGE;
    }
}

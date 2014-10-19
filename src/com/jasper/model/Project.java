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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author trancongly
 */
public class Project {

    private String name;
    private String location;
    private String macro;
    private String graphic;
    private String language;
    private String descritpion;
    private static String NAME = "Project";
    private static String MACRO = "Macro";
    private static String GRAPHIC = "Graphic";
    private static String LANGUAGE = "Language";
    private static String DESCRIPTION = "Description";

    public Project() {
    }

    public Project(String project) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "=";

        try {
            br = new BufferedReader(new FileReader(project));
            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(cvsSplitBy);                
                if (lineData[0].trim().equals(NAME)) {                    
                    name = lineData[1];
                } else if (lineData[0].trim().equals(MACRO)) {                    
                    macro = lineData[1].trim();
                } else if (lineData[0].trim().equals(GRAPHIC)) {                    
                    graphic = lineData[1].trim();
                } else if (lineData[0].trim().equals(LANGUAGE)) {
                    language = lineData[1].trim();
                } else if (lineData[0].trim().equals(DESCRIPTION)) {
                    descritpion = lineData[1].trim();
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

    public Project(String name, String location, String macro, String graphic, String language, String description) {
        this.name = name;
        this.location = location;
        this.macro = macro;
        this.graphic = graphic;
        this.language = language;
        this.descritpion = description;
    }
    
    public Project(String name, String macro, String graphic, String language, String description) {
        this.name = name;
        this.macro = macro;
        this.graphic = graphic;
        this.language = language;
        this.descritpion = description;
    }

    public void writeToFile() {
        BufferedWriter writer = null;
        try {
            //create a temporary file
            String fName = Utils.getCurrentLocation() + name + ".prj";
            File prjFile = new File(fName);
            writer = new BufferedWriter(new FileWriter(prjFile));
            writer.write(NAME + " = " + name + "\n");
            writer.write(MACRO + " = " + macro + "\n");
            writer.write(GRAPHIC + " = " + graphic + "\n");
            writer.write(LANGUAGE + " = " + language + "\n");
            writer.write(DESCRIPTION + " = " + descritpion + "\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
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

    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    public String getGraphic() {
        return this.graphic;
    }

    public void setDescription(String description) {
        this.descritpion = description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getDescription() {
        return this.descritpion;
    }
}

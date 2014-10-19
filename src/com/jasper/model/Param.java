/*
 * @(#)Param.java
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

/**
 *
 * @author trancongly
 */
public class Param {
    private String name;
    private String subName;
    private double max;
    private double min;
    private double step;
    private double currentValue;
    
    public Param() {
        
    }
    
    public Param(String name, String subName, double max, double min, double currentValue, double step) {
        this.name = name;
        this.subName = subName;
        this.max = max;
        this.min = min;
        this.currentValue = currentValue;
        this.step = step;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setSubName(String subName) {
        this.subName = subName;
    }
    
    public String getSubName() {
        return this.subName;
    }
    
    public void setMax(double max) {
        this.max = max;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public void setMin(double min) {
        this.min = min;
    }
    
    public double getMin() {
        return this.min;
    }
    
    public void setStep(double step) {
        this.step = step;
    }
    
    public double getStep() {
        return this.step;
    }
    
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
    
    public double getCurrentValue() {
        return this.currentValue;
    }
}

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
    
    public Param(String name, String subName, double max, double min, double step) {
        this.name = name;
        this.subName = subName;
        this.max = max;
        this.min = min;
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

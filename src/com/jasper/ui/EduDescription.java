/*
 * @(#)EduDescription.java
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
package com.jasper.ui;

import java.awt.Font;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * This EduDescription include the description information of application
 *
 * @version 1.0 28 Aug 2013
 *
 * @author sonnv
 *
 */
public class EduDescription {
    public static javax.swing.JTextArea desSLM = new JTextArea();
    public static javax.swing.JTextArea desAmplitude = new JTextArea();
    public static javax.swing.JTextArea desPhaseModulation = new JTextArea();
    public static javax.swing.JTextArea desMichelson = new JTextArea();
    public static javax.swing.JTextArea desDiffaction = new JTextArea();
    public static javax.swing.JTextArea desSpectrometer = new JTextArea();
    public static javax.swing.JTextArea desSignalProcessing = new JTextArea();
    public static javax.swing.JTextArea desPhaseRetarder = new JTextArea();
    public static javax.swing.JTextArea desTalbot = new JTextArea();
    public static javax.swing.JTextArea desWavefront = new JTextArea();
    public static javax.swing.JTextArea desBeamShifting = new JTextArea();
    public static javax.swing.JTextArea desImportFormula = new JTextArea();
    
    public static void initDescription() {
        final ResourceBundle labels = ResourceBundle
                .getBundle("resources/description", EduUIMainView.supportedLocales[0]);
        
        desSLM.setText(labels.getString("desSLM"));
        desAmplitude.setText(labels.getString("desAmplitude"));
        desPhaseModulation.setText(labels.getString("desPhaseModulation"));
        desMichelson.setText(labels.getString("desMichelson"));
        desDiffaction.setText(labels.getString("desDiffaction"));
        desSpectrometer.setText(labels.getString("desSpectrometer"));
        desSignalProcessing.setText(labels.getString("desSignalProcessing"));
        desPhaseRetarder.setText(labels.getString("desPhaseRetarder"));
        desTalbot.setText(labels.getString("desTalbot"));
        desWavefront.setText(labels.getString("desWavefront"));
        desBeamShifting.setText(labels.getString("desBeamShifting"));
        desImportFormula.setText(labels.getString("desImportFormula"));
        
        Font font = new Font("Verdana", Font.PLAIN, 16);
        desSLM.setFont(font);
        desAmplitude.setFont(font);
        desPhaseModulation.setFont(font);
        desMichelson.setFont(font);
        desDiffaction.setFont(font);
        desSpectrometer.setFont(font);
        desSignalProcessing.setFont(font);
        desPhaseRetarder.setFont(font);
        desTalbot.setFont(font);
        desWavefront.setFont(font);
        desBeamShifting.setFont(font);
        desImportFormula.setFont(font);
        
        desSLM.setLineWrap(true);
        desSLM.setWrapStyleWord(true);
        desSLM.setEditable(false);
        desSLM.setOpaque(false);
        
        desAmplitude.setLineWrap(true);
        desAmplitude.setWrapStyleWord(true);
        desAmplitude.setEditable(false);
        desAmplitude.setOpaque(false);
        
        desPhaseModulation.setLineWrap(true);
        desPhaseModulation.setWrapStyleWord(true);
        desPhaseModulation.setEditable(false);
        desPhaseModulation.setOpaque(false);
        
        desMichelson.setLineWrap(true);
        desMichelson.setWrapStyleWord(true);
        desMichelson.setEditable(false);
        desMichelson.setOpaque(false);
        
        desDiffaction.setLineWrap(true);
        desDiffaction.setWrapStyleWord(true);
        desDiffaction.setEditable(false);
        desDiffaction.setOpaque(false);
        
        desSpectrometer.setLineWrap(true);
        desSpectrometer.setWrapStyleWord(true);
        desSpectrometer.setEditable(false);
        desSpectrometer.setOpaque(false);
        
        desSignalProcessing.setLineWrap(true);
        desSignalProcessing.setWrapStyleWord(true);
        desSignalProcessing.setEditable(false);
        desSignalProcessing.setOpaque(false);
        
        desPhaseRetarder.setLineWrap(true);
        desPhaseRetarder.setWrapStyleWord(true);
        desPhaseRetarder.setEditable(false);
        desPhaseRetarder.setOpaque(false);
        
        desTalbot.setLineWrap(true);
        desTalbot.setWrapStyleWord(true);
        desTalbot.setEditable(false);
        desTalbot.setOpaque(false);
        
        desWavefront.setLineWrap(true);
        desWavefront.setWrapStyleWord(true);
        desWavefront.setEditable(false);
        desWavefront.setOpaque(false);
        
        desBeamShifting.setLineWrap(true);
        desBeamShifting.setWrapStyleWord(true);
        desBeamShifting.setEditable(false);
        desBeamShifting.setOpaque(false);
        
        desImportFormula.setLineWrap(true);
        desImportFormula.setWrapStyleWord(true);
        desImportFormula.setEditable(false);
        desImportFormula.setOpaque(false);
    }
}

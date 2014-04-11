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

import java.util.ResourceBundle;
import javax.swing.JLabel;

/**
 * This EduDescription include the description information of application
 *
 * @version 1.0 28 Aug 2013
 *
 * @author sonnv
 *
 */
public class EduDescription {
    public static javax.swing.JLabel desSLM = new JLabel();
    public static javax.swing.JLabel desAmplitude = new JLabel();
    public static javax.swing.JLabel desPhaseModulation = new JLabel();
    public static javax.swing.JLabel desMichelson = new JLabel();
    public static javax.swing.JLabel desDiffaction = new JLabel();
    public static javax.swing.JLabel desSpectrometer = new JLabel();
    public static javax.swing.JLabel desSignalProcessing = new JLabel();
    public static javax.swing.JLabel desPhaseRetarder = new JLabel();
    public static javax.swing.JLabel desTalbot = new JLabel();
    public static javax.swing.JLabel desWavefront = new JLabel();
    public static javax.swing.JLabel desBeamShifting = new JLabel();
    public static javax.swing.JLabel desImportFormula = new JLabel();
    
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
    }
}

/*
 * @(#)EduDescription.java
 *
 * Copyright (c) 2013 JASPER DISPLAY, Inc.
 * An Unpublished Work.  All Rights Reserved.
 *
 * JASPER DISPLAY PROPRIETARY:  Distribution of this source code
 * without permission from the copyright holder is strictly forbidden.
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

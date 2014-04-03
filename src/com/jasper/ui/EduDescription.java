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
    
    static String strSLM = "<html>"
            + "<div style=\"padding-left:5px;padding-top:5px;margin: 0 0.07em 0 -0.13em;background-color:white;width:100%;word-spacing:30px;font-family:MS Mincho;font-size:11px;font-weight: normal;\">"
            + "&nbsp; &nbsp; &nbsp; LCoS-SLM (Liquid Crystal on Silicon-Spatial Light Modulator) can modulate<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "amplitude, phase, or polarization of light waves in space and time.<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The structure of LCoS SLM is like normal liquid crystal display, it consists of<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "many square shape pixels and form a matrix structure, when  voltage is <br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "applied every liquid crystal in each pixel will rotate with different angle and <br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "different rotating angle causes different index of refraction to achieve <br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "modulation.<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + " When the voltage is not applied, the basic structure of SLM will affect the light<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "including diffraction efficiency, and the change in polarization state, using<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "these characteristics we can calculate the basic SLM parameters such as: the<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "pixel size, aperture ratio, and with the angle.<br /></div></html>";
    static String strAmplitude = "<html><div style=\"padding-left:5px;padding-top:5px;margin: 0 0.07em 0 -0.13em;background-color:white;width:100%;word-spacing:30px;font-family:MS Mincho;font-size:11px;font-weight: normal;\">"
            + "&nbsp; &nbsp; &nbsp; SLM can be used as Phase modulator or Amplitude modulator "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "with the angle adjustment of polarizer and analyzer."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "In this experiment, the polarizer and the analyzer are arranged at an angle of"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "45 degree to the rubbing angle such that SLM acts as amplitude modulator. "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The whole set of optical projection is simple system and this can also be used"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "to understand the parameters of imaging system, such as the f-number,"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "aperture stop, entrance pupil and exit pupil and so on."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "<br /></div></html>";
    static String strPhaseModulation = "<html><div style=\"padding-left:5px;padding-top:5px;margin: 0 0.07em 0 -0.13em;background-color:white;width:100%;word-spacing:30px;font-family:MS Mincho;font-size:11px;font-weight: normal;\">"
            + "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; In the second experiment, the analyzer and polarized arranged at the"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "angle of 45 degrees to the rubbing angle."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "In this experiment, the polarizer and analyzer are arranged in parallel to "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "rubbing angle such that SLM can be used as phase modulator. Then we use "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "the tilted grating (Blazed Grating) which allows the phase modulator to do"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "simple beam control (Beam Steering) experiments to understand the limits "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "of diffraction angles. "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + ""
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "<br /></div></html>";
    static String strMichelson = "<html><div style=\"padding-left:5px;padding-top:5px;margin: 0 0.07em 0 -0.13em;background-color:white;width:100%;word-spacing:30px;font-family:MS Mincho;font-size:11px;font-weight: normal;\">"
            + "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; The Michelson interferometer is the most common configuration for"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "optical interferometry and was invented by Albert Abraham Michelson."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The basic operation of the interferometer is as follows. "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "Light from a light source is split into two parts."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "One part of the light travels a different path length than the other. "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "After traversing these different path lengths, the two parts of the light are"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "brought together and recombined to form an interference pattern."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "Interference will occur when the path length difference is less than the"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "coherence length of the light source."
            + "<br /></div></html>";
    static String strDiffaction = "<html><div style=\"padding-left:5px;padding-top:5px;margin: 0 0.07em 0 -0.13em;background-color:white;width:100%;word-spacing:30px;font-family:MS Mincho;font-size:11px;font-weight: normal;\">"
            + "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; The phenomenon of diffraction can be understood using Huygens ’"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "principle which states that every unobstructed point on a wavefront will act"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "as a source of secondary spherical waves."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "A long slit of infinitesimal width which is illuminated by light diffracts the light"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "into a series of circular waves and the wavefront which emerges from the slit"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "is a cylindrical wave of uniform intensity."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The light from one portion of the slit can interfere with light from another"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "portion and all the waves that originate at the slit are in phase."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "If the path difference is exactly half of a wavelength,the two waves cancel"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "each other and resulting in destructive interference."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The double-slit experiment, sometimes called Young's experiment (after"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "Young's interference experiment), is a demonstration that matter and energy"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "can display characteristics of both waves and particles, and demonstrates"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "the fundamentally probabilistic nature of quantum mechanical phenomena."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "A monochromatic light source is incident on the first screen which"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + " contains a slit. The emerging light then arrives at the second screen"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "which has two parallel slits which serve as the sources of coherent light."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The light waves emerging from the two slits of the second screen then"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "interfere and form an interference pattern on the viewing screen."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The bright bands (fringes) correspond to interference maxima, and the dark"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "bands correspond to interference minima."
            + "<br /></div></html>";
    
    static String strSpectrometer = "<html><div style=\"padding-left:5px;padding-top:5px;margin: 0 0.07em 0 -0.13em;background-color:white;width:100%;word-spacing:30px;font-family:MS Mincho;font-size:11px;font-weight: normal;\">"
            + "&nbsp; &nbsp; &nbsp; A spectrophotometer is employed to measure the amount of light that a"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "sample absorbs. The instrument operates by passing a beam of light through"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "a sample and measuring the intensity of light reaching a detector. "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The angles at which these bright fringes occur can be measured with a"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "device called a spectrometer."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "These angles, along with the diffraction grating line spacing can then be used"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "to calculate the wavelength of the light passing through the grating via the"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "grating equation"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "<div style=\\\"font-size:16px;font-weight:bold;\\\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; dsinθm = mλ, m = 1, 2, 3, …, </div>"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "where the order of the spectrum is given by the integer values of m. "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "Spectroscopy is often used in optical properties of various materials, because"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "the spectrometer can analyze and measure the spectral intensity of each"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "wavelength. "
            + "<br /></div></html>";
    
    static String strSignalProcessing = "<html><div style=\"padding-left:5px;padding-top:5px;margin: 0 0.07em 0 -0.13em;background-color:white;width:100%;word-spacing:30px;font-family:MS Mincho;font-size:11px;font-weight: normal;\">"
            + "&nbsp; &nbsp; &nbsp; 4f optical system on a common signal processing system consist lenses "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "and filter as the two main components. "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The input signal is applied to the front focal plane of first lens and will be"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "Fourier transformed by the first lens to obtain spectrum result in the back"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "focal plane."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "A second lens is behind the spectrum and the spectrum is in the front focal"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "plane of second lens and is Fourier transformed again by the second lens."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "If we want to filter out some signal, we can block any frequency in the"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "spectrum plane, and get the result at the back focal plane of second lens."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "<br /></div></html>";
    
    static String strPhaseRetarder = "<html><div style=\"padding-left:5px;padding-top:5px;margin: 0 0.07em 0 -0.13em;background-color:white;width:100%;word-spacing:30px;font-family:MS Mincho;font-size:11px;font-weight: normal;\">"
            + "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Holography is one popular choice among several techniques for the"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "imaging and display of three-dimensional (3D) objects."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "Storage of the hologram in a computer enables us to reduce the noise"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "through image processing techniques and numerically reconstruct the"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "object with arbitrary views."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "Phase shift digital holography uses the object beam and three to four"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "different sets of the initial phase of reference light interference fringes."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The in-line configuration makes use of the full pixel count in forming the"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "holographic image, but the zero-order and the twin image terms are"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "superposed on the image."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The complex field at the hologram is obtained by phase-shifting"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "interferometry."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "From the complex field at the hologram plane, including the amplitude and"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "phase information, the optical field at any other plane can be obtained by"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "one of the numerical diffraction methods."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "In four-step phase-shifting digital holography (PSDH), four holograms with"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "phase shifts ψ = 0, π/2, π, 3π/2 are acquired, which are then numerically"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "combined to extract the amplitude and phase profiles."
            + "<br /></div></html>";
    
    static String strTalbot = "<html><div style=\"padding-left:5px;padding-top:5px;margin: 0 0.07em 0 -0.13em;background-color:white;width:100%;word-spacing:30px;font-family:MS Mincho;font-size:11px;font-weight: normal;\">"
            + "&nbsp; &nbsp; The near field diffraction theory (Fresnel Diffraction) has a very complicated"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "integral formula."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "However, periodic diffraction grating has simpler and specific results from"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "imaging phenomenon."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "Periodic structures diffract light, and such diffracted light interferes in"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "the vicinity of the structure."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "Potentially the simplest example for a periodic structure is an amplitude"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "grating that diffracts the light into discrete directions."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "In the Fresnel diffraction regime, these diffraction orders interfere and"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "cause a self-imaging phenomenon of the grating."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "This phenomenon is known as the Talbot effect. "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + ""
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "<br /></div></html>";
    
    static String strWavefront = "<html><div style=\"padding-left:5px;padding-top:5px;margin: 0 0.07em 0 -0.13em;background-color:white;width:100%;word-spacing:30px;font-family:MS Mincho;font-size:11px;font-weight: normal;\">"
            + "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; A Spatial Light Modulator (SLM) is a device that is used to modulate"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "amplitude, phase, or polarization of light waves in space and time."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "Arbitrary complex field modulation is produced by the use of the pixels of an"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "SLM."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "The phase modulation capability of an SLM spatially encodes the complex"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "data on pixels and these data can produce many kinds of Diffractive Optical"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "Elements (DOE)."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "By the flexibility of spatial light modulation, we can even apply some function"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "to these DOE. "
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "For example, a blazed grating can change the orientation of light propagation"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "a zone plate can change the imaging plane backward or forward and other"
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + "functions can apply to different application."
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + ""
            + "<br /><div style=\\\"width:100%;height:3px;\\\"></div>"
            + ""
            + "<br /></div></html>";

    public static void initDescription() {
        final ResourceBundle labels = ResourceBundle
                .getBundle("resources/description", EduUIMainView.supportedLocales[0]);

        //desSLM.setText(labels.getString("desSLM"));
//        desAmplitude.setText(labels.getString("desAmplitude"));
//        desPhaseModulation.setText(labels.getString("desPhaseModulation"));
//        desMichelson.setText(labels.getString("desMichelson"));
//        desDiffaction.setText(labels.getString("desDiffaction"));
//        desSpectrometer.setText(labels.getString("desSpectrometer"));
//        desSignalProcessing.setText(labels.getString("desSignalProcessing"));
//        desPhaseRetarder.setText(labels.getString("desPhaseRetarder"));
//        desTalbot.setText(labels.getString("desTalbot"));
//        desWavefront.setText(labels.getString("desWavefront"));
//        desWavelength.setText(labels.getString("desWavelength"));
//        desBeamShifting.setText(labels.getString("desBeamShifting"));
//        desImportFormula.setText(labels.getString("desImportFormula"));
        desSLM.setText(strSLM);
        desAmplitude.setText(strAmplitude);
        desPhaseModulation.setText(strPhaseModulation);
        desMichelson.setText(strMichelson);
        desDiffaction.setText(strDiffaction);
        desSpectrometer.setText(strSpectrometer);
        desSignalProcessing.setText(strSignalProcessing);
        desPhaseRetarder.setText(strPhaseRetarder);
        desTalbot.setText(strTalbot);
        desWavefront.setText(strWavefront);
        desBeamShifting.setText(labels.getString("desBeamShifting"));
        desImportFormula.setText(labels.getString("desImportFormula"));

    }
}

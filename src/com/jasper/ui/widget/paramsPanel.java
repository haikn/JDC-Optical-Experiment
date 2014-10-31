/*
 * @(#)NewProjectPanel.java
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
package com.jasper.ui.widget;

import com.jasper.core.OpticsPane;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JOptionPane;
import com.jasper.core.PatternImage;
import com.jasper.model.Macro;
import com.jasper.model.Param;
import com.jasper.model.Project;
import com.jasper.ui.EduLensOn11;
import com.jasper.ui.EduPatternJPanel;
import com.jasper.ui.EduPatternShowOn;
import com.jasper.utils.Utils;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;

import static com.jasper.ui.EduPatternShowOn.patternFrameDoubleClick;
import static com.jasper.ui.EduPatternShowOn.patternFrame;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Khuc Nam Hai
 */
public class paramsPanel extends OpticsPane {

    PatternImage image1 = new PatternImage();
    ResourceBundle labels;
    private JPanel panelPattern;
    private JFrame magFrameLenon;
    
    private Map<String, JLabel> lblParametersArray = new HashMap<String, JLabel>();
    private Map<String, DoubleJSlider> sliderParametersArray = new HashMap<String, DoubleJSlider>();
    private Map<String, JTextField> txtParametersArray = new HashMap<String, JTextField>();
    private Map<String, Binding> bindParametersArray = new HashMap<String, Binding>();
    
    private javax.swing.JButton btnLensOn;
    private javax.swing.JButton btnSecondDisplay;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JTextArea txtAreaLog;
    static String logmessageNewProject = "New Project: ";
    private int countSecondDisplayManual = 1;
    private int countLenOnManual = 1;
    private javax.swing.JPanel panelParam;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelNewProject;
    private JLabel lblMacro, lblProject;
    private String prjName, desc, diagram;
    final private int panelType = 1;
    private Macro macro;
    final private int PROJECTNAMELENGTH = 15;
    final private int MACROPATHLENGTH = 45;
    
    public paramsPanel(ResourceBundle labels, BindingGroup bindingGroup, JPanel panelPattern) {
        
        //initComponents(labels, bindingGroup, panelPattern);
    }
        
    public void setProject(String project) {
        this.prjName = project;        
    }
    
    public void setProjectName(String project) {
        String projectName;
        if(project.length() > PROJECTNAMELENGTH) {
            projectName = project.substring(0, PROJECTNAMELENGTH);
        } else {
            projectName = project;
        }
        lblProject.setText("Project Name: " + projectName);
        lblProject.setToolTipText(project);
    }
    
    public String getProject() {
        return this.prjName;
    }
    
    public void setMacro(String macro) {
        //lblMacro.setText("Macro Name: " + macro);
    }
    
    public void setMacroName(String macro) {
        String macroPath;
        if(macro.length() > MACROPATHLENGTH) {
            macroPath = macro.substring(0, MACROPATHLENGTH);
        } else {
            macroPath = macro;
        }
        lblMacro.setText("Macro Path: " + macroPath);
        lblMacro.setToolTipText(macro);
    }
    
    public void setDescription(String desc) {
        this.desc = desc;
    }
    
    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }
    
    public int getPanelType() {
        return this.panelType;
    }
    
    public void initParams() throws IOException {
        
        //Project prj = new Project(Utils.getCurrentLocation() + this.prjName.trim() + ".prj");
        //macro = new Macro(prj.getMacro());
        
        
        ArrayList<Param> listParams = macro.getParam();
        for(int i = 0; i < listParams.size(); i++) {
            lblParametersArray.get("lblParameter"+i).setText((listParams.get(i)).getSubName());
            //sliderParametersArray.get("sliderParameter"+i).setMaximum((int)((listParams.get(i)).getMax() / (listParams.get(i)).getStep()));
            //sliderParametersArray.get("sliderParameter"+i).setMinimum((int)((listParams.get(i)).getMin() / (listParams.get(i)).getStep()));
            //sliderParametersArray.get("sliderParameter"+i).scale((int)listParams.get(i).getStep());
            //sliderParametersArray.get("sliderParameter"+i).
        }
    }
    
    public void initComponents(ResourceBundle labels, BindingGroup bindingGroup, JPanel panelPattern) throws IOException {
        
        final ResourceBundle initLabel = labels;
        final JPanel initPanelPattern = panelPattern;
        
        
        Project prj = new Project(Utils.getCurrentLocation() + this.prjName.trim() + ".prj");
        macro = new Macro(prj.getMacro());
        
        ArrayList<Param> listParams = macro.getParam();
        
        //Init component first
        for(int k = 0; k < listParams.size(); k++) {
            double sliderMax = listParams.get(k).getMax();
            double sliderMin = listParams.get(k).getMin();
            double sliderScale = listParams.get(k).getStep();
            double sliderValue = listParams.get(k).getCurrentValue();
            lblParametersArray.put("lblParameter"+k, new JLabel());
            //sliderParametersArray.put("sliderParameter"+k, new DoubleJSlider((int)((listParams.get(k)).getMin()/ (listParams.get(k)).getStep()), (int)((listParams.get(k)).getMax() / (listParams.get(k)).getStep()), (int)(listParams.get(k)).getCurrentValue(), 10));
            sliderParametersArray.put("sliderParameter"+k, new DoubleJSlider((int)(sliderMin/sliderScale),(int)(sliderMax/sliderScale),(int)sliderValue,(int)(1/sliderScale)));
            txtParametersArray.put("txtParameter"+k, new JTextField());
        }
        
        //Then make binding for each component just in case
        for(int k = 0; k < listParams.size(); k++) {
            bindParametersArray.put("binding"+k, org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sliderParametersArray.get("sliderParameter"+k), org.jdesktop.beansbinding.ELProperty.create("${value}"), txtParametersArray.get("txtParameter"+k), org.jdesktop.beansbinding.BeanProperty.create("text")));
        }
        
        this.labels = labels;
        this.txtAreaLog = new javax.swing.JTextArea();
        this.panelPattern = panelPattern;
        
        image1 = ((EduPatternJPanel) panelPattern).pimage;
        
        
        panelParam = new javax.swing.JPanel();
       
        btnGenerate = new javax.swing.JButton();
        btnLensOn = new javax.swing.JButton();
        btnSecondDisplay = new javax.swing.JButton();
        panelButton = new javax.swing.JPanel();
        
        panelNewProject = new javax.swing.JPanel(new BorderLayout(30, 30));
                
        JPanel headerPanel = new JPanel(new BorderLayout(30, 30));
        lblMacro = new JLabel("Macro Path: ");
        lblProject = new JLabel("Project Name: ");
        headerPanel.add(lblProject, BorderLayout.LINE_START);
        headerPanel.add(lblMacro, BorderLayout.LINE_END);
        panelNewProject.add(headerPanel, BorderLayout.PAGE_START);
        panelNewProject.add(panelParam, BorderLayout.CENTER);
        panelNewProject.add(panelButton, BorderLayout.PAGE_END);
               
        btnGenerate.setText(initLabel.getString("btnGenerate"));
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateActionPerformed(evt);
            }
        });
        
        btnLensOn.setEnabled(true);
        btnLensOn.setText(initLabel.getString("btnLensOn"));
        btnLensOn.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11LensOnActionPerformed(evt);
                countLenOnManual++;
                if (countLenOnManual % 2 == 0) {
                    btnLensOn.setText(initLabel.getString("btnLensOff"));
                    initPanelPattern.addMouseListener(new java.awt.event.MouseAdapter() {

                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            patternFrameDoubleClick.show();
                        }
                    });
                } else {
                    btnLensOn.setText(initLabel.getString("btnLensOn"));
                }
            }
        });
        
        btnSecondDisplay.setEnabled(true);
        btnSecondDisplay.setText(initLabel.getString("btnSecondDisplayOn"));
        btnSecondDisplay.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSecondGenerateActionPerformed(evt);
                countSecondDisplayManual++;
                if (countSecondDisplayManual % 2 == 0) {
                    btnSecondDisplay.setText(initLabel.getString("btnSecondDisplayOff"));
                } else {
                    btnSecondDisplay.setText(initLabel.getString("btnSecondDisplayOn"));
                }
            }
        });      
        
        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
                panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelButtonLayout.createSequentialGroup().addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelButtonLayout.createSequentialGroup().addGap(0, 0, 0).addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(20, 20, 20).addComponent(btnLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(20, 20, 20).addComponent(btnSecondDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))));
        panelButtonLayout.setVerticalGroup(
                panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelButtonLayout.createSequentialGroup().addGap(105, 105, 105).addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false).addComponent(btnSecondDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(btnLensOn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))));
        
        javax.swing.GroupLayout jPanelParamLayout = new javax.swing.GroupLayout(panelParam);
        panelParam.setLayout(jPanelParamLayout);
        
        GroupLayout.SequentialGroup horizontalGroup = jPanelParamLayout.createSequentialGroup();
        GroupLayout.ParallelGroup horizontalLblParallel = jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.ParallelGroup horizontalTxtParallel = jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.ParallelGroup horizontalSliderParallel = jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
        
        GroupLayout.SequentialGroup verticalGroup = jPanelParamLayout.createSequentialGroup();
        
        for(int i = 0; i < listParams.size(); i++) {
            final JTextField currentTxtParameter = txtParametersArray.get("txtParameter"+i);
            final JLabel currentLblParameter = lblParametersArray.get("lblParameter"+i);
            final DoubleJSlider currentSliderParameter = sliderParametersArray.get("sliderParameter"+i);

            currentSliderParameter.setName(""+listParams.get(i).getName());
            //Set default value for each slider
            currentSliderParameter.setValue((int)listParams.get(i).getCurrentValue());
            
            bindingGroup.addBinding(bindParametersArray.get("binding"+i));
            currentTxtParameter.setName(""+listParams.get(i).getName());
            currentTxtParameter.setText(String.valueOf(currentSliderParameter.getValue()));
                        
            horizontalLblParallel.addComponent(currentLblParameter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            horizontalTxtParallel.addComponent(currentTxtParameter, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE);
            horizontalSliderParallel.addComponent(currentSliderParameter, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE);
            
            verticalGroup.addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(currentTxtParameter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentLblParameter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelParamLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(currentSliderParameter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            //Slider listener
            
            currentSliderParameter.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent ce) {
                    //JSlider source = (JSlider)ce.getSource();
                    DoubleJSlider source = (DoubleJSlider)ce.getSource();
                    if(source.getValueIsAdjusting()) {
                        currentTxtParameter.setText(""+source.getScaledValue());
                        //currentTxtParameter.setText(""+source.getValue());
                        sliderGenerateActionPerformed(source);
                    }
                }
            });
            
            currentTxtParameter.addKeyListener(new KeyAdapter() {
                
                @Override
                public void keyReleased(KeyEvent ke) {
                    JTextField source = (JTextField)ke.getSource();
                    if(source.getText().matches("-?\\d+(\\.\\d+)?")) {
                        currentSliderParameter.setValue((int) Double.parseDouble(source.getText()));
                    }
                    keyenventGenerateActionPerformed(source);
                    if (source.getText() == null || source.getText().equals("")) {
                        currentLblParameter.setForeground(Color.red);
                    } else {
                        currentLblParameter.setForeground(Color.black);
                    }
                }
            });
        }
        
        //Draw paramters
        
        //Set horizontal group
        horizontalGroup.addGroup(horizontalLblParallel);
        horizontalGroup.addGroup(horizontalTxtParallel);
        horizontalGroup.addGroup(horizontalSliderParallel);
        jPanelParamLayout.setHorizontalGroup(horizontalGroup);
        
        //Set verical group
        
        jPanelParamLayout.setVerticalGroup(verticalGroup);
        callFunction(1);
    }
    
    public JPanel getPanel() {
        return panelNewProject;
    }
    
    public JTextArea getLogArea() {
        return txtAreaLog;
    }
    
    private void buttonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateActionPerformedMichelSon
        if (parseArguments()) {
            btnLensOn.setEnabled(true);
            btnSecondDisplay.setEnabled(true);
            
            callFunction(1);
        }
        
    }
    
    private void button11LensOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11LensOnMichelsonActionPerformed
        if (parseArguments()) {
            callFunction(1);
            
            if (countLenOnManual % 2 == 0) {
                magFrameLenon.dispose();
                panelPattern.addMouseListener(new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        patternFrameDoubleClick.show();
                    }
                });
            } else {
                magFrameLenon = new JFrame("1:1 Lens On");
                URL url = ClassLoader.getSystemResource("resources/jdclogo_48x48.png");
                Toolkit kit = Toolkit.getDefaultToolkit();
                Image img = kit.createImage(url);
                magFrameLenon.setIconImage(img);
                
                EduLensOn11 mag = new EduLensOn11(panelPattern, new Dimension(120, 120));
                magFrameLenon.getContentPane().add(mag);
                magFrameLenon.pack();
                magFrameLenon.setLocation(new Point(568, 450));
                magFrameLenon.setResizable(false);
                magFrameLenon.setVisible(true);
                magFrameLenon.setAlwaysOnTop(true);
                magFrameLenon.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        countLenOnManual--;
                        btnLensOn.setText(labels.getString("btnLensOn"));
                        magFrameLenon.dispose();
                    }
                });
            }
            
        }
        
    }
    
    private void buttonSecondGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSecondGenerateActionPerformedMichelSon
        if (parseArguments()) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();
            if (devices.length == 1) {
                countSecondDisplayManual--;
                JOptionPane.showMessageDialog(null, "No second display is found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                callFunction(2);
                if (countSecondDisplayManual % 2 == 0) {
                    patternFrameDoubleClick.dispose();
                    patternFrame.dispose();
                }
            }
        }
    }
    
    private void sliderGenerateActionPerformed(DoubleJSlider source) {
        double currentSliderValue = source.getScaledValue();
        macro.setVariable(source.getName(), currentSliderValue);
        btnLensOn.setEnabled(true);
        btnSecondDisplay.setEnabled(true);

        callFunction(1);
    }
    
    private void keyenventGenerateActionPerformed(JTextField source) {
        
        try {
            String currentTextValue = source.getText();
            if(currentTextValue == null || currentTextValue.trim().isEmpty()) {
                macro.setVariable(source.getName(), 0.0);
            } else {
                macro.setVariable(source.getName(), Double.parseDouble(source.getText()));
            }
            btnLensOn.setEnabled(true);
            btnSecondDisplay.setEnabled(true);
            
            callFunction(1);
        } catch (Exception exc) {
            exc.printStackTrace();
        }        
    }
    
    private void callFunction(int displayNumber) {
        PatternImage image = ((EduPatternJPanel) panelPattern).pimage;
        if(macro.getFunctionName().equalsIgnoreCase("exp")) {
            image.paintManualMacro(macro.getVariables(), macro.getMeshgrid(), macro.getShifting(), macro.getWavefront(), macro.getWaveFrontVariables());
        } else {
            image.paintManualSlit(macro.getVariables(), macro.getMeshgrid(), macro.getMatrix(), macro.getShifting(), macro.getSpacingX(), macro.getSpacingY(), macro.getSlitPattern(), macro.getX(), macro.getY());
        }    
        if (displayNumber == 1)
            EduPatternShowOn.updatePattern(image, genLog());
        else EduPatternShowOn.updatePatternSecondDisplay(image, genLog());
        setLog(genLog());
        imageGenerated = true;
    }
    
    private String genLog() {
        String logString = logmessageNewProject;
        ArrayList<Param> params = macro.getParam();
        for(int i = 0; i < params.size(); i++) {
            logString += "\t" + params.get(i).getName() + " = " + macro.getVariable(params.get(i).getName());
        }
        return logString;
    }
    
    private boolean parseArguments() {
        return true;
    }
    
    public void setLog(String msg) {
        txtAreaLog.append(msg + System.getProperty("line.separator"));
    }
}
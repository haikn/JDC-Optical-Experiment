/*
 * @(#)AboutView.java
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

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.*;

/**
 * This AboutView include the version, customer information of application
 *
 * @version 1.0 28 Aug 2013
 *
 * @author Albert Nguyen
 *
 */
public class AboutView extends JDialog {

    private static final long serialVersionUID = 1L;

    public AboutView(Frame parent) {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(btnClose);
    }

    private void initComponents() {
        lblIcon = new JLabel();
        btnClose = new JButton();
        lblAppVersion = new JLabel();
        lblAppName = new JLabel();
        lblPublicLincense = new JLabel();
        lblAppFree = new JLabel();
        lblCopyRight = new JLabel();
        lblBuildDate = new JLabel();
        lblIcon.setHorizontalAlignment(0);
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/resources/jdclogo_150x155.png")));
        btnClose.setText("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        ResourceBundle bundle = ResourceBundle.getBundle("resources/Text_en");
        lblAppVersion.setHorizontalAlignment(0);
        lblAppVersion.setText(bundle.getString("VERSION"));
        lblAppName.setHorizontalAlignment(0);
        lblAppName.setText(bundle.getString("APP.NAME.JDC"));
        lblPublicLincense.setText(bundle.getString("APP.NAME.PUBLIC.LINCENSE"));
        lblAppFree.setText(bundle.getString("APP.NAME.FREE"));
        lblCopyRight.setText(bundle.getString("APP.COPYRIGHT"));
        lblBuildDate.setFont(new Font("Dialog", 0, 12));
        lblBuildDate.setText(bundle.getString("BUILDDATE"));
        lblBuildDate.setForeground(Color.RED);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(btnClose)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addComponent(lblBuildDate))
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblCopyRight)
                .addComponent(lblPublicLincense)
                .addComponent(lblAppFree)
                .addComponent(lblAppVersion)
                .addComponent(lblAppName))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(lblIcon, GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblAppVersion)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCopyRight)
                .addGap(6, 6, 6)
                .addComponent(lblAppName)
                .addComponent(lblPublicLincense)
                .addComponent(lblAppFree)
                .addGap(24, 24, 24)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btnClose)))
                .addContainerGap(-1, Short.MAX_VALUE))
                .addComponent(lblBuildDate, GroupLayout.Alignment.TRAILING))
                .addGap(5, 5, 5)));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("aboutBox");
        setTitle("About");
        setResizable(false);
        pack();
    }

    private void btnCloseActionPerformed(ActionEvent evt) {
        dispose();
    }
    private JButton btnClose;
    private JLabel lblCopyRight;
    private JLabel lblBuildDate;
    private JLabel lblAppName;
    private JLabel lblAppVersion;
    private JLabel lblPublicLincense;
    private JLabel lblAppFree;
    private JLabel lblIcon;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milkdairy.admin;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MilkDairyManagementJPanel extends JPanel {
	private JPanel collectionsModuleTab;
	public JPanel getFormerModuleTab() {
		return formerModuleTab;
	}

	public void setFormerModuleTab(JPanel formerModuleTab) {
		this.formerModuleTab = formerModuleTab;
	}
	private JPanel formerModuleTab;
        public JPanel getCollectionsModuleTab() {
		return collectionsModuleTab;
	}

	public void setCollectionsModuleTab(JPanel collectionsModuleTab) {
		this.collectionsModuleTab = collectionsModuleTab;
	}

		public MilkDairyManagementJPanel() {
                        
        }
      
        public void init(){
        	this.setLayout(new GridLayout(1,1));
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Collections Module", collectionsModuleTab);
            this.collectionsModuleTab.setPreferredSize(new Dimension(600, 400));
            tabbedPane.addTab("Former Module", formerModuleTab);
            add(tabbedPane);
            tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);   
        }
        protected JPanel makeTextPanel (String text) {
                JPanel panel = new JPanel(false);
                JLabel filler = new JLabel(text);
                filler.setHorizontalAlignment(JLabel.CENTER);
                panel.setLayout (new GridLayout(1,1));
                panel.add(filler);
                return panel;
        }

       
}

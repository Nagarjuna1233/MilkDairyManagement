/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milkdairy.collectionsmodule;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author nagarjuna
 */
public class CollectionsModuleJPanel extends JPanel {
	
	private JPanel collectionsStoredTab;
	private JPanel collectionsUpdateTab;
	private  JTabbedPane tabbedPane;
    public JPanel getCollectionsStoredTab() {
		return collectionsStoredTab;
	}
	public void setCollectionsStoredTab(JPanel collectionsStoredTab) {
		this.collectionsStoredTab = collectionsStoredTab;
	}
	public JPanel getCollectionsUpdateTab() {
		return collectionsUpdateTab;
	}
	public void setCollectionsUpdateTab(JPanel collectionsUpdateTab) {
		this.collectionsUpdateTab = collectionsUpdateTab;
	}
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	public CollectionsModuleJPanel(){
               
    }
    public void init(){
    	 this.setLayout (new GridLayout(1,1));
    	 tabbedPane= new JTabbedPane();
         collectionsStoredTab.setPreferredSize(new Dimension(600, 400));
         collectionsUpdateTab.setPreferredSize(new Dimension(600, 400));
         tabbedPane.addTab("Collections Stored", this.collectionsStoredTab);
         tabbedPane.addTab("Collections Update", this.collectionsUpdateTab);
         add(tabbedPane);
         tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);           

    }
    
}

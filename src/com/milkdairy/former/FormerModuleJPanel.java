package com.milkdairy.former;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FormerModuleJPanel extends JPanel {

	private JPanel formerCreateTab;
	private JPanel formerUpdateTab;
	public void setFormerCreateTab(JPanel formerCreateTab) {
		this.formerCreateTab = formerCreateTab;
	}

	public void setFormerUpdateTab(JPanel formerUpdateTab) {
		this.formerUpdateTab = formerUpdateTab;
	}

	public void setFormerReportsTab(JPanel formerReportsTab) {
		this.formerReportsTab = formerReportsTab;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	private JPanel formerReportsTab;
	private  JTabbedPane tabbedPane;

	public void init(){
   	 this.setLayout (new GridLayout(1,1));
   	 tabbedPane= new JTabbedPane();
        formerCreateTab.setPreferredSize(new Dimension(600, 400));
        formerUpdateTab.setPreferredSize(new Dimension(600, 400));
        tabbedPane.addTab("Former Creation", this.formerCreateTab);
        tabbedPane.addTab("Former Update", this.formerUpdateTab);
       // tabbedPane.addTab("Collections Update", this.formerReportsTab);
        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);           

   }

}

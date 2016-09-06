package com.milkdairy.former;

import javax.swing.JPanel;

import com.milkdairy.services.MilkManagementSystemService;

public class FormerCreateJPanel extends JPanel {

private JPanel header;
private MilkManagementSystemService milkManagementSystemService;
private JPanel formerCreateFormJPanel; 

public void setHeader(JPanel header) {
	this.header = header;
}

public void setMilkManagementSystemService(
		MilkManagementSystemService milkManagementSystemService) {
	this.milkManagementSystemService = milkManagementSystemService;
}

public void init(){
	this.setLayout(null);
	this.formerCreateFormJPanel.setBounds(0, this.header.getHeight(),milkManagementSystemService.SCREEN_WIDTH, milkManagementSystemService.SCREEN_HEIGHT);
	this.add(this.header);
	this.add(formerCreateFormJPanel);
}

public void setFormerCreateFormJPanel(JPanel formerCreateFormJPanel) {
	this.formerCreateFormJPanel = formerCreateFormJPanel;
}
}

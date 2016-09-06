package com.milkdairy.former;

import javax.swing.JPanel;

import com.milkdairy.services.MilkManagementSystemService;

public class FormerReportsJPanel extends JPanel {
	private JPanel header;
	private MilkManagementSystemService milkManagementSystemService;

	public void setHeader(JPanel header) {
		this.header = header;
	}

	public void setMilkManagementSystemService(
			MilkManagementSystemService milkManagementSystemService) {
		this.milkManagementSystemService = milkManagementSystemService;
	}

	public void init(){
		this.setLayout(null);
		this.add(this.header);
	}
}

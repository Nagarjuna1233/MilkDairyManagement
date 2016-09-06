/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milkdairy.collectionsmodule;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.milkdairy.services.TabsFont;

/**
 *
 * @author nareshkalyan
 */
public class CollectionsUpdateJPanel extends JPanel{
	private JPanel storedFormJPanel;
	private JPanel header;
	private int screenWidth;
	public JPanel getStoredFormJPanel() {
		return storedFormJPanel;
	}
	public void setStoredFormJPanel(JPanel storedFormJPanel) {
		this.storedFormJPanel = storedFormJPanel;
	}
	public JPanel getHeader() {
		return header;
	}
	public void setHeader(JPanel header) {
		this.header = header;
	}
	public int getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	public int getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	private int screenHeight;
    public CollectionsUpdateJPanel(){
    	                  
    }
    public void init(){
    this.setLayout(null);
    this.screenWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
    this.screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
    this.header.setBounds(0, 0, this.screenWidth, header.getHeight());
    this.setFont(new TabsFont("Serif",2,15));
    //this.setLayout (new GridLayout(6,1));
    this.setBackground(Color.CYAN);
    this.storedFormJPanel.setBounds(0,this.header.getHeight(),this.screenWidth,this.screenHeight -this.header.getHeight());
    this.add(this.header);
    this.add(this.storedFormJPanel);
    }
}

package com.milkdairy.services;

import java.awt.Toolkit;


public class MilkManagementSystemService{
	
	public  int xPoint;
	public  int yPoint;
	public  int lblWidth;
	public  int lblHeight;
	public  int tfWidth;
	public  int tflHeight;
	public  int menuBarHeight;
	public  final int SCREEN_WIDTH=Toolkit.getDefaultToolkit().getScreenSize().width;
	public  final int SCREEN_HEIGHT=Toolkit.getDefaultToolkit().getScreenSize().width;
	
	
    public  int getxPoint() {
		return xPoint;
	}


	public  void setxPoint(int xPoint) {
		this.xPoint = xPoint;
	}


	public  int getyPoint() {
		return yPoint;
	}


	public  void setyPoint(int yPoint) {
		this.yPoint = yPoint;
	}


	public  int getLblWidth() {
		return lblWidth;
	}


	public  void setLblWidth(int lblWidth) {
		this.lblWidth = lblWidth;
	}


	public  int getLblHeight() {
		return lblHeight;
	}


	public  void setLblHeight(int lblHeight) {
		this.lblHeight = lblHeight;
	}


	public  int getTfWidth() {
		return tfWidth;
	}


	public  void setTfWidth(int tfWidth) {
		this.tfWidth = tfWidth;
	}


	public  int getTflHeight() {
		return tflHeight;
	}


	public  void setTflHeight(int tflHeight) {
		this.tflHeight = tflHeight;
	}


	public MilkManagementSystemService(){
               
              
    }

}

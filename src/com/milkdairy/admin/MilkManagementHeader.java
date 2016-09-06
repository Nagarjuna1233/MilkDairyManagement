package com.milkdairy.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.CORBA.OMGVMCID;

public class MilkManagementHeader extends JPanel {
	private  String backGroundcolor;
	private  String foreGroundcolor;
	private  String fontName;
	private  int fontStyle;
	private String imageUrl;
	private BufferedImage image;
	public  String getBackGroundcolor() {
		return backGroundcolor;
	}

	public  void setBackGroundcolor(String backGroundcolor) {
		this.backGroundcolor = backGroundcolor;
	}

	public  String getForeGroundcolor() {
		return foreGroundcolor;
	}

	public  void setForeGroundcolor(String foreGroundcolor) {
		this.foreGroundcolor = foreGroundcolor;
	}

	public  String getFontName() {
		return fontName;
	}

	public  void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public  int getFontStyle() {
		return fontStyle;
	}

	public  void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public  int getFontSize() {
		return fontSize;
	}

	public  void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public  void setHeight(int height) {
		this.height = height;
	}

	private  int fontSize;
	public  void setAppName(String appName) {
		this.appName = appName;
	}

	//private  String fontColor;
	private  int height;
	private  String appName;
	
	public void init(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.setProperty("headerColor",this.backGroundcolor);
		System.setProperty("headerFontColor",this.foreGroundcolor);
		Font myFont = new Font(this.fontName, this.fontStyle, this.fontSize);
		JLabel header=new JLabel(this.appName);
		header.setFont(myFont);
		header.setForeground(Color.getColor("headerFontColor"));
		this.add(header);
		this.setSize(screenSize.width, this.height);
		this.setBackground(Color.getColor("headerColor"));	
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0,Toolkit.getDefaultToolkit().getScreenSize().width,this.height, null);
        // see javadoc for more info on the parameters            
    }
	
//	public MilkManagementHeader(String appName,String backGroundcolor,String foreGroundcolor,String fontName,int fontStyle,int fontSize,int height){
//		
//		MilkManagementHeader.backGroundcolor=backGroundcolor;
//		MilkManagementHeader.foreGroundcolor=foreGroundcolor;
//		MilkManagementHeader.fontName=fontName;
//		MilkManagementHeader.fontStyle=fontStyle;
//		MilkManagementHeader.fontSize=fontSize;
//		MilkManagementHeader.appName=appName;
//		MilkManagementHeader.height=height;
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		System.setProperty("headerColor",MilkManagementHeader.backGroundcolor);
//		System.setProperty("headerFontColor",MilkManagementHeader.foreGroundcolor);
//		
//		Font myFont = new Font(MilkManagementHeader.fontName, MilkManagementHeader.fontStyle, MilkManagementHeader.fontSize);
//		JLabel header=new JLabel(MilkManagementHeader.appName);
//		header.setFont(myFont);
//		header.setForeground(Color.getColor("headerFontColor"));
//		this.add(header);
//		
//		this.setSize(screenSize.width, this.height);
//		this.setBackground(Color.getColor("headerColor"));
//	   
//	}
	public MilkManagementHeader(){
		super();
		imageUrl="C:/Users/User/Desktop/images.jpg";
		 try {                
	          image = ImageIO.read(new File(imageUrl));
	       } catch (IOException ex) {
	            System.out.println(ex.getMessage());
	       }
	}

}

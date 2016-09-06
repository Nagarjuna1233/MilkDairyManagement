package com.milkdairy.services;

import java.awt.Font;

public class TabsFont extends Font {
	
	
    public static String fontName;
    public static int fontStyle;
    public static int fontSize;
	
	public TabsFont(String fontName, int fontStyle, int fontSize) {
		super(fontName,fontStyle, fontSize);
		TabsFont.fontName=fontName;
		TabsFont.fontStyle=fontStyle;
		TabsFont.fontSize=fontSize;
		
		// TODO Auto-generated constructor stub
	}

	public String getFontName() {
		return fontName;
	}

	public static void setFontName(String fontName) {
		TabsFont.fontName = fontName;
	}

	public static int getFontStyle() {
		return fontStyle;
	}

	public static void setFontStyle(int fontStyle) {
		TabsFont.fontStyle = fontStyle;
	}

	public static int getFontSize() {
		return fontSize;
	}

	public static void setFontSize(int fontSize) {
		TabsFont.fontSize = fontSize;
	}

}

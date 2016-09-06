package com.milkdairy.fileservice;

public class MilkFilePathProvider {

	private String homePath;
	
	
	public static final String CSV=".csv";
	
	public String getHomePath() {
		return homePath;
	}
	public void setHomePath(String homePath) {
		this.homePath = homePath;
	}
	public String getMilkFilePath(String pk) {
		return homePath+"/Milk/june/"+pk+"/pm/MilkRecord"+CSV;
	}
	
}

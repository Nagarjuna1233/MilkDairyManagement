package com.milkdairy.fileservice;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class MilkDairyFileUtiles {

	private MilkFilePathProvider filePathProvider;

	public void setFilePathProvider(MilkFilePathProvider filePathProvider) {
		this.filePathProvider = filePathProvider;
	}

	public boolean createRow(String pk,String fileType, String value, String header) {

		File targetFile = null;
		try {

			if (fileType.equalsIgnoreCase("MILK")) {
				targetFile = new File(filePathProvider.getMilkFilePath(pk));
			}
			if (targetFile.exists()) {
				writeData(targetFile, value);
				return true;
			} else {
				if (targetFile.getParentFile().exists()) {
					if (targetFile.createNewFile()) {
						writeData(targetFile, header.concat("\n").concat(value));
						return true;
					}
					else {
						System.out.println("Error while creating new file : path "+targetFile.getAbsolutePath());
						return false;
					}
				} else if (targetFile.getParentFile().mkdir()) {
					if (targetFile.createNewFile()) {
						writeData(targetFile, header.concat("\n").concat(value));
						return true;
					}
					else{
						System.out.println("Error while creating new file : path "+targetFile.getAbsolutePath());
						return false;	
					}
				}
				else{
					System.out.println("Error while creating mkdir : path "+targetFile.getAbsolutePath());
					return false;
				}
			}

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
			return false;
		}

	}

	public boolean updateRow(String pk,String fileType,String data){
		
		File targetFile=null;
		try {

			if (fileType.equalsIgnoreCase("MILK")) {
				targetFile = new File(filePathProvider.getMilkFilePath(data));
			}
			if (targetFile.exists()) {
				 List<String> lines = FileUtils.readLines(targetFile);
//				 List<String> updatedLines = lines.stream().filter(s -> !s.contains(pk)).collect(Collectors.toList());
//				 FileUtils.writeLines(targetFile,updatedLines, false);
				 writeData(targetFile,data);
				return true;
				
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
			
		}
		return false;
	}
	
public boolean deletRow(String pk,String fileType){
		
		File targetFile=null;
		try {

			if (fileType.equalsIgnoreCase("MILK")) {
				targetFile = new File(filePathProvider.getMilkFilePath(pk));
			}
			if (targetFile.exists()) {
				 List<String> lines = FileUtils.readLines(targetFile);
				// List<String> updatedLines = lines.stream().filter(s -> !s.contains(pk)).collect(Collectors.toList());
				// FileUtils.writeLines(targetFile,updatedLines, false);
				return true;
				
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
			
		}
		return false;
	}
	
	public void writeData(File targetFile, String Data) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(targetFile, true);
			fileWriter.append(Data + "\n");
			fileWriter.flush();
		} catch (IOException e) {
			System.out.println("Error while flushing fileWriter !!!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("closing fileWriter !!!");
				e.printStackTrace();
			}

		}
		// Write the CSV file header
		System.out.println("CSV file was created successfully !!!");

	}
}

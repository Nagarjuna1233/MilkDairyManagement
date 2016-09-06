package com.milkdairy.services;

import java.io.FileWriter;
import java.io.IOException;

public class GenerateCsv
{
   public static void main(String [] args)
   {
	   generateCsvFile("c:\\test.csv"); 
   }
   
   private static void generateCsvFile(String sFileName)
   {
	try
	{
	    FileWriter writer = new FileWriter(sFileName);
		 
	    writer.append("DisplayName");
	    writer.append(',');
	    writer.append("Age");
	    writer.append('\n');

	    writer.append("MKYONG");
	    writer.append(',');
	    writer.append("26");
            writer.append('\n');
			
	    writer.append("YOUR NAME");
	    writer.append(',');
	    writer.append("29");
	    writer.append('\n');
			
	    //generate whatever data you want
			
	    writer.flush();
	    writer.close();
	}
	catch(IOException e)
	{
	     e.printStackTrace();
	} 
    }
   /*radApple.addItemListener(new ItemListener() {
       public void itemStateChanged(ItemEvent e) {         
          statusLabel.setText("Apple RadioButton: " 
          + (e.getStateChange()==1?"checked":"unchecked"));
       }           
    });*/
}
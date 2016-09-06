/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milkdairy.collectionsmodule;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.springframework.util.CollectionUtils;

import com.milkdairy.fileservice.MilkDairyPersistenceManager;
import com.milkdairy.managedobjects.Collection;
import com.milkdairy.managedobjects.Milk;
import com.milkdairy.services.CustomInitialSelectionComboBox;
import com.milkdairy.services.DateLabelFormatter;
import com.milkdairy.services.DateTimeUtil;
import com.milkdairy.services.MilkManagementSystemService;


/**
 *
 * @author nagarjuna
 */
public class CollectionsUpdateFormJPanel extends JPanel {

private final static String ERROR_FORM="Cant't be empty";
private String color;
private JTextField formerNameTF;
private JTextField formerIDTF;
private JTextField milkPadTF;
private JTextField milkValueTF;
private JTextField milkPriceTF;

private JLabel formerNameL;
private JLabel formerIDL;
private JLabel milkPadL;
private JLabel milkValueL;
private JLabel milkPriceL;


private JLabel formerIDErrorL;
private JLabel formerNameErrorL;
private JLabel milkPadErrorL;
private JLabel milkValueErrorL;
private JLabel milkPriceErrorL;

private JButton saveBtn;
private JButton resetBtn;

private JComboBox<String> formerIDCombo;
private JComboBox<String> formerNameCombo;
private JButton search;

private JDatePickerImpl datePicker;
private JRadioButton am;
private JRadioButton pm;

private JPanel  collectionMonthDetailsJP;

private MilkManagementSystemService milkManagementSystemService; 

public void setMilkManagementSystemService(
		MilkManagementSystemService milkManagementSystemService) {
	this.milkManagementSystemService = milkManagementSystemService;
}
private MilkDairyPersistenceManager persistenceManager;


public void setPersistenceManager(MilkDairyPersistenceManager persistenceManager) {
	this.persistenceManager = persistenceManager;
}
public void setColor(String color) {
	this.color = color;
}
private String ids[]=new String[]{};
private String names[]=new String[]{};
public void init(){
	    this.setLayout(null);
	    System.setProperty("collectionsStoredFormColor",this.color);
        this.setBackground(Color.getColor("collectionsStoredFormColor"));
        
        
           
	     this.formerIDL=this.addComponent(formerIDL,milkManagementSystemService.xPoint, milkManagementSystemService.yPoint, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
	     this.formerIDL.setText("Former ID");
	     formerIDTF= this.addComponent(formerIDTF,milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth+20, milkManagementSystemService.yPoint,milkManagementSystemService.tfWidth,milkManagementSystemService.tflHeight);
	     formerIDTF.setEditable(false);
	     
	     this.formerIDErrorL=this.addComponent(formerIDErrorL,milkManagementSystemService.xPoint
					+ milkManagementSystemService.lblWidth+milkManagementSystemService.tfWidth+40
					, milkManagementSystemService.yPoint, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
	     this.formerIDErrorL.setForeground(Color.RED);
	    
	     this.formerNameL=this.addComponent(formerNameL,milkManagementSystemService.xPoint, milkManagementSystemService.yPoint+40, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
	     this.formerNameL.setText("Former Name");
	     formerNameTF=this.addComponent(formerNameTF,milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth+20, milkManagementSystemService.yPoint+40,milkManagementSystemService.tfWidth,milkManagementSystemService.tflHeight);
	     formerNameTF.setEditable(false);
	     this.formerNameErrorL=this.addComponent(formerNameErrorL,milkManagementSystemService.xPoint
					+ milkManagementSystemService.lblWidth+milkManagementSystemService.tfWidth+40
					, milkManagementSystemService.yPoint+40, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
	     this.formerNameErrorL.setForeground(Color.RED);
	    
	     
        
        this.milkPadL=this.addComponent(milkPadL,milkManagementSystemService.xPoint, milkManagementSystemService.yPoint+80, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkPadL.setText("Milk Pad Value");
        milkPadTF=this.addComponent(milkPadTF,milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth+20, milkManagementSystemService.yPoint+80,milkManagementSystemService.tfWidth,milkManagementSystemService.tflHeight);
        this.milkPadErrorL=this.addComponent(milkPadErrorL,milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth+milkManagementSystemService.tfWidth+40
				, milkManagementSystemService.yPoint+80, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkPadErrorL.setForeground(Color.RED);
      
        
        this.milkValueL=this.addComponent(milkValueL,milkManagementSystemService.xPoint, milkManagementSystemService.yPoint+120, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkValueL.setText("Milk Quantity");
        milkValueTF=this.addComponent(milkValueTF,milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth+20, milkManagementSystemService.yPoint+120,milkManagementSystemService.tfWidth,milkManagementSystemService.tflHeight);
        this.milkValueErrorL=this.addComponent(milkValueErrorL,milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth+milkManagementSystemService.tfWidth+40
				, milkManagementSystemService.yPoint+120, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkValueErrorL.setForeground(Color.RED);
      
        this.milkPriceL=this.addComponent(milkPriceL,milkManagementSystemService.xPoint, milkManagementSystemService.yPoint+160, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkPriceL.setText("Milk Price");
        milkPriceTF=this.addComponent(milkPriceTF,milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth+20, milkManagementSystemService.yPoint+160,milkManagementSystemService.tfWidth,milkManagementSystemService.tflHeight);
        this.milkPriceErrorL=this.addComponent(milkPriceErrorL,milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth+milkManagementSystemService.tfWidth+40
				, milkManagementSystemService.yPoint+160, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkPriceErrorL.setForeground(Color.RED);
        
        
        this.saveBtn = this.addComponent(this.saveBtn,milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
		milkManagementSystemService.yPoint + 200, 80, 30);
		this.saveBtn.setText("Save");
		this.saveBtn.setForeground(Color.BLUE);
		this.add(this.saveBtn);

		this.saveBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				milkPriceErrorL.setText("");
				milkPadErrorL.setText("");
				milkValueErrorL.setText("");
				milkPriceErrorL.setText("");
				formerNameErrorL.setText("");
				formerIDErrorL.setText("");
				if("".equals(formerIDTF.getText())){
					formerIDErrorL.setText(ERROR_FORM);
				}
				else if ("".equals(formerNameTF.getText())) {
					formerNameErrorL.setText(ERROR_FORM);
				} else if ("".equals(milkPadTF.getText())) {
					milkPadErrorL.setText(ERROR_FORM);
				} else if ("".equals(milkValueTF.getText())) {
					milkValueErrorL.setText(ERROR_FORM);
				} else if ("".equals(milkPriceTF.getText())) {
					milkPriceErrorL.setText(ERROR_FORM);
				}
//				else if (formerIDCombo.getSelectedItem() == null) {
//					System.out.println("Former id selection Error");
//			}
//					else if (startdateTimePicker == null) {
//					System.out.println("date selection Error");
//				}
				else{
				Milk milk=new Milk();
				milk.setFormerID(formerIDCombo.getSelectedItem().toString());
				milk.setName(formerNameTF.getText());
				milk.setPadValue(milkPadTF.getText());
				milk.setQuantity(milkValueTF.getText());
				milk.setRate(milkPriceTF.getText());
				milk.setDate("dsad");
				System.out
						.println("Im in Former create and save submit action "+milk.toString());
				//filePersistenceManager.save(milk);
				}
				System.out
						.println("Im in Former create and update submit action");
			}
		});
		
		this.resetBtn = this.addComponent(this.resetBtn,milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth+140
				,
				milkManagementSystemService.yPoint + 200, 80, 30);
		this.resetBtn.setText("Reset");
		this.resetBtn.setForeground(Color.BLUE);
		this.add(this.resetBtn);

		this.resetBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				milkPriceTF.setText("");
				milkPadTF.setText("");
				milkValueTF.setText("");
				milkPriceTF.setText("");
				System.out
						.println("Im in Former create and update submit action");
			}
		});
      
		collectionMonthDetailsJP=new CollectionMonthDetailsJPanel();
		collectionMonthDetailsJP.setBounds(milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth*2+milkManagementSystemService.tfWidth*2, milkManagementSystemService.yPoint, (milkManagementSystemService.SCREEN_WIDTH/2)-milkManagementSystemService.tfWidth/5, 400);
		this.add(collectionMonthDetailsJP);
		
		List<String> formerIds=persistenceManager.getFormerValues("id"); 
		if(!CollectionUtils.isEmpty(formerIds)){
			ids=new String[formerIds.size()];
			formerIds.toArray(ids);
		}
		
		formerIDCombo = new JComboBox(ids);
	      // has to be editable
		formerIDCombo.setEditable(true);
	      
	      // change the editor's document
	     new CustomInitialSelectionComboBox(formerIDCombo);
	     
		formerIDCombo.setBounds(milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth*2
				+ milkManagementSystemService.tfWidth + 40,
		milkManagementSystemService.yPoint,
		milkManagementSystemService.lblWidth+20,
		milkManagementSystemService.lblHeight+10);
		formerIDCombo.setMaximumRowCount(5);
		this.add(formerIDCombo);
		
		List<String> formerNames=persistenceManager.getFormerValues("name"); 
		if(!CollectionUtils.isEmpty(formerNames)){
			names=new String[formerNames.size()];
			formerNames.toArray(names);
		}
		System.out.println("Names1 "+formerNames);
		formerNameCombo = new JComboBox(names);
	      // has to be editable
		formerNameCombo.setEditable(true);
	      
	      // change the editor's document
	     new CustomInitialSelectionComboBox(formerNameCombo);
	     
		//customerIDListScrollPane = new JScrollPane(customerIDCombo);
	     formerNameCombo.setBounds(milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth*2
				+ milkManagementSystemService.tfWidth + 40,
		milkManagementSystemService.yPoint+milkManagementSystemService.lblHeight+20,
		milkManagementSystemService.lblWidth+20,
		milkManagementSystemService.lblHeight+10);
	     formerNameCombo.setMaximumRowCount(5);
		this.add(formerNameCombo);
     
		UtilDateModel model = new UtilDateModel();
		model.setDate(2016, 04, 16);
		model.setSelected(true);
				
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth*2
				+ milkManagementSystemService.tfWidth + 40,
		milkManagementSystemService.yPoint+milkManagementSystemService.lblHeight+60,
		milkManagementSystemService.lblWidth+20,
		milkManagementSystemService.lblHeight+5);
		this.add(datePicker);
		
		 am = new JRadioButton("AM", true);
	     pm = new JRadioButton("PM");
	     //Group the radio buttons.
	      ButtonGroup amPmgroup = new ButtonGroup();
	      amPmgroup.add(am);
	      amPmgroup.add(pm);
	      am.setBounds(milkManagementSystemService.xPoint
					+ milkManagementSystemService.lblWidth*2
					+ milkManagementSystemService.tfWidth + 40,
			milkManagementSystemService.yPoint+milkManagementSystemService.lblHeight+100,
			milkManagementSystemService.lblWidth/2,
			milkManagementSystemService.lblHeight+5);
	      this.add(am);
	      pm.setBounds(milkManagementSystemService.xPoint
					+ milkManagementSystemService.lblWidth*2
					+ milkManagementSystemService.tfWidth + 40+milkManagementSystemService.lblWidth/2,
			milkManagementSystemService.yPoint+milkManagementSystemService.lblHeight+100,
			milkManagementSystemService.lblWidth/2,
			milkManagementSystemService.lblHeight+5);
	      this.add(pm);
		
		
		this.search = this.addComponent(this.search,milkManagementSystemService.xPoint
				+ (milkManagementSystemService.lblWidth*2)+milkManagementSystemService.tfWidth+60,
		milkManagementSystemService.yPoint + 200, 80, 30);
		this.search.setText("Search");
		this.search.setForeground(Color.BLUE);
		this.add(this.search);

		this.search.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out
						.println("Im in Former create and update submit action");
				System.out.println("Former id"+(String)formerIDCombo.getSelectedItem());
				System.out.println("Former name"+(String)formerNameCombo.getSelectedItem());
				System.out.println("Am   "+am.isSelected());
				System.out.println("pm   "+pm.isSelected());
				
				String forID=(String)formerIDCombo.getSelectedItem();
				String forName=(String)formerNameCombo.getSelectedItem();
				String amPMStr=am.isSelected()?"AM":"PM";
				String dateTime=DateTimeUtil.convertdateToStringeWithTime((Date) datePicker.getModel().getValue());
				System.out.println("Seleted date "+dateTime);
			List<Collection> collectionList=persistenceManager.getCollectionsBy(forID, forName, amPMStr, dateTime);
				if(!CollectionUtils.isEmpty(collectionList)){
					Collection searchCollection=collectionList.get(0);
					 formerNameTF.setText(searchCollection.getFormerName());;
					 formerIDTF.setText(searchCollection.getFormerID());;
					 milkPadTF.setText(searchCollection.getMilkPad());;
					 milkValueTF.setText(searchCollection.getMilkQuantity());;
					 milkPriceTF.setText(searchCollection.getMilkPrice());;
				}
			}
		});	
		
//		UtilDateModel model = new UtilDateModel();
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
//		 
//		this.add(datePicker);
//		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
//		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
//		timeSpinner.setEditor(timeEditor);
//		timeSpinner.setValue(new Date()); 
//		timeSpinner.setBounds();
//		this.add(timeSpinner);
		
//		 DateTimePicker dateTimePicker = new DateTimePicker();
//	     dateTimePicker.setFormats( DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.MEDIUM ) );
//	     dateTimePicker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );
//	     dateTimePicker.setDate(new Date());
//	     dateTimePicker.setBounds(milkManagementSystemService.xPoint
//	 			+ (milkManagementSystemService.lblWidth*2)+milkManagementSystemService.tfWidth+60,
//	 			milkManagementSystemService.yPoint + 200, 80, 30);
//	     this.add(dateTimePicker);
		
		

		

   }
public JTextField addComponent(JTextField comp, int xx, int yy, int width,
		int height) {
	comp = new JTextField();
	comp.setBounds(xx, yy, width, height);
	// comp.setOpaque(true);
	this.add(comp);
	return comp;
}

public JLabel addComponent(JLabel comp, int xx, int yy, int width,
		int height) {
	comp = new JLabel();
	comp.setBounds(xx, yy, width, height);
	// comp.setOpaque(true);
	this.add(comp);
	return comp;
}

public JTextArea addComponent(JTextArea comp, int xx, int yy, int width,
		int height) {
	comp = new JTextArea(30, 10);
	comp.setBounds(xx, yy, width, height);
	// comp.setOpaque(true);
	this.add(comp);
	return comp;
}

public JButton addComponent(JButton comp, int xx, int yy, int width,
		int height) {
	comp = new JButton();
	comp.setBounds(xx, yy, width, height);
	// comp.setOpaque(true);
	this.add(comp);
	return comp;
}
public void initCompenents(){
	
}

}

package com.milkdairy.collectionsmodule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.StringUtils;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.springframework.util.CollectionUtils;

import com.milkdairy.fileservice.MilkDairyPersistenceManager;
import com.milkdairy.managedobjects.Collection;
import com.milkdairy.managedobjects.Former;
import com.milkdairy.managedobjects.Milk;
import com.milkdairy.managedobjects.PadValue;
import com.milkdairy.services.CustomInitialSelectionComboBox;
import com.milkdairy.services.DateLabelFormatter;
import com.milkdairy.services.DateTimePicker;
import com.milkdairy.services.DateTimeUtil;
import com.milkdairy.services.FloatField;
import com.milkdairy.services.IntergerField;
import com.milkdairy.services.MilkManagementSystemService;

/**
 *
 * @author nagarjuna
 */
public class CollectionsStoredFormJPanel extends JPanel {

private MilkDairyPersistenceManager persistenceManager;


public void setPersistenceManager(MilkDairyPersistenceManager persistenceManager) {
	this.persistenceManager = persistenceManager;
}
private final static String ERROR_FORM="Cant't be empty";
private String color;
private JTextField formerNameTF;
private JComboBox formerIDCombo;
private FloatField milkPadTF;
private FloatField milkValueTF;
private IntergerField milkPriceTF;

private JLabel formerNameL;
private JLabel formerIDL;
private JLabel milkPadL;
private JLabel milkValueL;
private JLabel milkPriceL;
private JLabel startDateL;


private JLabel formerIDErrorL;

private JLabel milkPadErrorL;
private JLabel milkValueErrorL;
private JLabel milkPriceErrorL;
private JLabel formerNameErrorL;

private JButton saveBtn;
private JButton resetBtn;

//private JPanel  collectionMonthDetailsJP;

private MilkManagementSystemService milkManagementSystemService; 

private DateTimePicker startdateTimePicker;

private String ids[]=new String[]{};

public void setMilkManagementSystemService(
		MilkManagementSystemService milkManagementSystemService) {
	this.milkManagementSystemService = milkManagementSystemService;
}

public void setColor(String color) {
	this.color = color;
}
public void init(){
	    this.setLayout(null);
	    System.setProperty("collectionsStoredFormColor",this.color);
        this.setBackground(Color.getColor("collectionsStoredFormColor"));
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
	     
	     this.formerIDL=this.addComponent(formerIDL,milkManagementSystemService.xPoint, milkManagementSystemService.yPoint, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
	        this.formerIDL.setText("Former ID");
	     formerIDCombo.setBounds(milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth+20, milkManagementSystemService.yPoint,milkManagementSystemService.tfWidth,milkManagementSystemService.tflHeight);
	     this.add(formerIDCombo);
	     formerIDCombo.setSelectedIndex(-1);
	     
	     this.formerIDErrorL=this.addComponent(formerIDErrorL,milkManagementSystemService.xPoint
					+ milkManagementSystemService.lblWidth+milkManagementSystemService.tfWidth+40
					, milkManagementSystemService.yPoint, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
	    this.formerIDErrorL.setForeground(Color.RED); 
	    
	     this.formerNameL=this.addComponent(formerNameL,milkManagementSystemService.xPoint, milkManagementSystemService.yPoint+40, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
	     this.formerNameL.setText("Former Name");
	     this.formerNameTF=this.addComponent(formerNameTF,milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth+20, milkManagementSystemService.yPoint+40,milkManagementSystemService.tfWidth,milkManagementSystemService.tflHeight);
	     //this.formerNameTF.setEditable(false);
	     this.formerNameErrorL=this.addComponent(formerNameErrorL,milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth+milkManagementSystemService.tfWidth+40
				, milkManagementSystemService.yPoint+40, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
	     formerNameErrorL.setForeground(Color.RED);
        
        this.milkPadL=this.addComponent(milkPadL,milkManagementSystemService.xPoint, milkManagementSystemService.yPoint+80, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkPadL.setText("Milk Pad Value");
        milkPadTF=new FloatField();
        milkPadTF.setBounds(milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth+20, milkManagementSystemService.yPoint+80,milkManagementSystemService.tfWidth,milkManagementSystemService.tflHeight);
        this.add(milkPadTF);
        this.milkPadErrorL=this.addComponent(milkPadErrorL,milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth+milkManagementSystemService.tfWidth+40
				, milkManagementSystemService.yPoint+80, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkPadErrorL.setForeground(Color.RED);
      
        
        this.milkValueL=this.addComponent(milkValueL,milkManagementSystemService.xPoint, milkManagementSystemService.yPoint+120, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkValueL.setText("Milk Quantity");
        milkValueTF=new FloatField();
        milkValueTF.setBounds(milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth+20, milkManagementSystemService.yPoint+120,milkManagementSystemService.tfWidth,milkManagementSystemService.tflHeight);
        this.add(milkValueTF);
        this.milkValueErrorL=this.addComponent(milkValueErrorL,milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth+milkManagementSystemService.tfWidth+40
				, milkManagementSystemService.yPoint+120, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkValueErrorL.setForeground(Color.RED);
      
        this.milkPriceL=this.addComponent(milkPriceL,milkManagementSystemService.xPoint, milkManagementSystemService.yPoint+160, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkPriceL.setText("Milk Price");
        milkPriceTF=new IntergerField();
        milkPriceTF.setBounds(milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth+20, milkManagementSystemService.yPoint+160,milkManagementSystemService.tfWidth,milkManagementSystemService.tflHeight);
        this.add(milkPriceTF);
        this.milkPriceErrorL=this.addComponent(milkPriceErrorL,milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth+milkManagementSystemService.tfWidth+40
				, milkManagementSystemService.yPoint+160, milkManagementSystemService.lblWidth, milkManagementSystemService.lblHeight);
        this.milkPriceErrorL.setForeground(Color.RED);
        
        
        this.saveBtn = this.addComponent(this.saveBtn,milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
		milkManagementSystemService.yPoint + 240, 80, 30);
		this.saveBtn.setText("Save");
		this.saveBtn.setForeground(Color.BLUE);
		this.add(this.saveBtn);

		
		
		this.resetBtn = this.addComponent(this.resetBtn,milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth+140
				,
				milkManagementSystemService.yPoint + 240, 80, 30);
		this.resetBtn.setText("Reset");
		this.resetBtn.setForeground(Color.BLUE);
		this.add(this.resetBtn);

		
      
		this.startDateL=this.addComponent(startDateL, milkManagementSystemService.xPoint
				,
				milkManagementSystemService.yPoint + 200, milkManagementSystemService.tfWidth, 30);
	     this.startDateL.setText("Date");
		
		startdateTimePicker = new DateTimePicker();
		startdateTimePicker.setFormats( DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.MEDIUM ) );
		startdateTimePicker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );
		startdateTimePicker.setDate(new Date());
		startdateTimePicker.setBounds(milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth+20
				,
				milkManagementSystemService.yPoint + 200, milkManagementSystemService.tfWidth, 30);
	     this.add(startdateTimePicker);
	     
	    displyMilkTable();
		milkDisplayBaseJP.setBounds(milkManagementSystemService.xPoint+milkManagementSystemService.lblWidth*2+milkManagementSystemService.tfWidth+50, milkManagementSystemService.yPoint, milkManagementSystemService.SCREEN_WIDTH/2+50, 400);
		this.add(milkDisplayBaseJP);
		// save button
		this.saveBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				milkPriceErrorL.setText("");
				milkPadErrorL.setText("");
				milkValueErrorL.setText("");
				milkPriceErrorL.setText("");
				formerNameErrorL.setText("");
				if("".equals(formerNameTF.getText())){
					formerNameErrorL.setText(ERROR_FORM);
				}
				else if ("".equals(milkPriceTF.getText())) {
					milkPriceErrorL.setText(ERROR_FORM);
				} else if ("".equals(milkPadTF.getText())) {
					milkPadErrorL.setText(ERROR_FORM);
				} else if ("".equals(milkValueTF.getText())) {
					milkValueErrorL.setText(ERROR_FORM);
				} else if ("".equals(milkPriceTF.getText())) {
					milkPriceErrorL.setText(ERROR_FORM);
				} else if (formerIDCombo.getSelectedItem() == null) {
					System.out.println("Former id selection Error");
				} else if (startdateTimePicker == null) {
					System.out.println("date selection Error");
				}
				else{
				Collection collection=new Collection();
				collection.setFormerID(formerIDCombo.getSelectedItem().toString());
				collection.setFormerName(formerNameTF.getText());
				collection.setPadValue(milkPadTF.getText());
				collection.setMilkQuantity(milkValueTF.getText());
				collection.setMilkPrice(milkPriceTF.getText());
				collection.setTimeStemp(DateTimeUtil.convertdateToStringeWithTime(startdateTimePicker.getDate()));
				System.out
						.println("Im in Former create and save submit action "+collection.toString());
				persistenceManager.save(collection);
//				PadValue padVal=new PadValue();
//				padVal.setCreationTime(new Date());
//				padVal.setValue(10.34);
//				persistenceManager.save(padVal);
				doFormEmpty();
				}
			}
		});
		
		this.resetBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				doFormEmpty();
				System.out
						.println("Im in Former create and reset submit action");
			}
		});
		//this.formerIDCombo.add
		this.formerIDCombo.addActionListener(new ActionListener(){
                 public void actionPerformed(java.awt.event.ActionEvent evt) {
                	 String name=(String)formerIDCombo.getSelectedItem();
                	 if(StringUtils.isNotEmpty(name)){
                	 Former searchResult=persistenceManager.getFormerNameBY(name.trim(),""); 
                	 if(null!=searchResult){
				formerNameTF.setText(searchResult.getName());
                	 }
                	 }
		}});
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
public void doFormEmpty(){
	formerIDCombo.setSelectedIndex(-1);
	formerNameTF.setText("");
	milkPriceTF.setText("");
	milkPadTF.setText("");
	milkValueTF.setText("");
	milkPriceTF.setText("");
}
Object[][] rowData={{}};
JTable collectionDisplayJTab;
String milkColumnNames[] = { "FormerID", "Name", "PadVale", "Qunty", "Price","Date" };
JComboBox<String> amPMComboBox;
JComboBox<String> idComboBox;
JPanel milkDisplayBaseJP;
JPanel ampmJP;
private JDatePickerImpl datePicker;
private final static String AM_PM[]={"AM","PM"};
public void displyMilkTable() {
	
	GridLayout milkDisplaySearchDL=new GridLayout(4, 0);
	milkDisplaySearchDL.setHgap(2);
	milkDisplaySearchDL.setVgap(2);
	milkDisplayBaseJP = new JPanel();
    ampmJP=new JPanel();
    List<Collection> colArray=persistenceManager.getCollectionsBy("1","","","");
    try {
		persistenceManager.showCollectionRow("SELECT * FROM Collection WHERE formerID='1'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    if(!CollectionUtils.isEmpty(colArray)){
    	System.out.println("List size "+colArray.size());
    	rowData=new Object[colArray.size()][6];
    	for(int rowCount=0;rowCount<rowData.length;rowCount++){
    		rowData[rowCount][0]=colArray.get(rowCount).getFormerID();
    		rowData[rowCount][1]=colArray.get(rowCount).getFormerName();
    		rowData[rowCount][2]=colArray.get(rowCount).getMilkPad();
    		rowData[rowCount][3]=colArray.get(rowCount).getMilkQuantity();
    		rowData[rowCount][4]=colArray.get(rowCount).getMilkPrice();
    		rowData[rowCount][5]=colArray.get(rowCount).getTimeStemp();
    		
    	}
    }
//	for (int i = 0; i < rowData.length; i++) {
//
//		for (int j = 0; j < rowData[i].length; j++) {
//
//			rowData[i][j] = "ewfde";
//		}
//
//	}

	UtilDateModel model = new UtilDateModel();
	//model.setDate(2016, 04, 16);
	model.setValue(new Date());
	model.setSelected(true);
			
	Properties p = new Properties();
	p.put("text.today", "Today");
	p.put("text.month", "Month");
	p.put("text.year", "Year");
	JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
	
	datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	collectionDisplayJTab = new JTable(new MilkTableModel(rowData, milkColumnNames));
	JScrollPane scrollPane = new JScrollPane(collectionDisplayJTab);


	
	amPMComboBox = new JComboBox<String>(AM_PM);
	idComboBox=new JComboBox<String>(ids);
	collectionDisplayJTab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	//amPMComboBox.setSelectedIndex(defaultMode);
	amPMComboBox.addItemListener(new AMPMComboBoxItemLister());
	idComboBox.addItemListener(new IDComboBoxItemListener());
    ampmJP.add(idComboBox);
	ampmJP.add(amPMComboBox);
	ampmJP.add(datePicker);
	milkDisplayBaseJP.add(ampmJP,BorderLayout.EAST);
	milkDisplayBaseJP.add(scrollPane,BorderLayout.CENTER);
}

class AMPMComboBoxItemLister implements ItemListener {
  public void itemStateChanged(ItemEvent e) {
	  List<Collection> colArray=persistenceManager.getCollectionsBy("","",(String)amPMComboBox.getSelectedItem(),"");
	    
	    if(!CollectionUtils.isEmpty(colArray)){
	    	System.out.println("List size "+colArray.size());
	    	rowData=new Object[colArray.size()][6];
	    	for(int rowCount=0;rowCount<rowData.length;rowCount++){
	    		rowData[rowCount][0]=colArray.get(rowCount).getFormerID();
	    		rowData[rowCount][1]=colArray.get(rowCount).getFormerName();
	    		rowData[rowCount][2]=colArray.get(rowCount).getMilkPad();
	    		rowData[rowCount][3]=colArray.get(rowCount).getMilkQuantity();
	    		rowData[rowCount][4]=colArray.get(rowCount).getMilkPrice();
	    		rowData[rowCount][5]=colArray.get(rowCount).getTimeStemp();
	    		
	    	}
	    	collectionDisplayJTab = new JTable(new MilkTableModel(rowData, milkColumnNames));
	    }
                                            }
};
class IDComboBoxItemListener implements ItemListener {
	  public void itemStateChanged(ItemEvent e) {
		  List<Collection> colArray=persistenceManager.getCollectionsBy((String)idComboBox.getSelectedItem(),"","","");
		    
		    if(!CollectionUtils.isEmpty(colArray)){
		    	System.out.println("List size "+colArray.size());
		    	rowData=new Object[colArray.size()][6];
		    	for(int rowCount=0;rowCount<rowData.length;rowCount++){
		    		rowData[rowCount][0]=colArray.get(rowCount).getFormerID();
		    		rowData[rowCount][1]=colArray.get(rowCount).getFormerName();
		    		rowData[rowCount][2]=colArray.get(rowCount).getMilkPad();
		    		rowData[rowCount][3]=colArray.get(rowCount).getMilkQuantity();
		    		rowData[rowCount][4]=colArray.get(rowCount).getMilkPrice();
		    		rowData[rowCount][5]=colArray.get(rowCount).getTimeStemp();
		    		
		    	}
		    	collectionDisplayJTab = new JTable(new MilkTableModel(rowData, milkColumnNames));
		    }
	                                            }
	};
class MilkTableModel extends DefaultTableModel {
	public MilkTableModel(Object[][] rowData, String[] columnNames) {
		// TODO Auto-generated constructor stub
		super(rowData,columnNames);
	}

	@Override
    public boolean isCellEditable(int row, int column){  
        return false;  
    }
	
	
	

}
}

package com.milkdairy.collectionsmodule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.springframework.util.CollectionUtils;

import com.milkdairy.fileservice.MilkDairyPersistenceManager;
import com.milkdairy.managedobjects.Collection;
import com.milkdairy.managedobjects.Former;
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
	
	public CollectionsStoredFormJPanel(){
		super();
	}

	private MilkDairyPersistenceManager persistenceManager;
	private static final Logger LOG = Logger
			.getLogger(CollectionsStoredFormJPanel.class);
	private final static int FORM_TABLE_COL_COUNT = 6;

	public void setPersistenceManager(
			MilkDairyPersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}

	private final static String ERROR_FORM = "Cant't be empty";
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

	// private JPanel collectionMonthDetailsJP;

	private MilkManagementSystemService milkManagementSystemService;

	private DateTimePicker startdateTimePicker;

	private String ids[] = new String[] {};

	Object[][] rowData = { {} };
	JTable collectionDisplayJTab;
	String milkColumnNames[] = { "FormerID", "Name", "PadVale", "Qunty",
			"Price", "Date" };
	JComboBox<String> searchAMPMComboBox;
	JComboBox<String> searchIdComboBox;
	JPanel milkDisplayBaseJP;
	JPanel colSearchItemsJP;
	private JDatePickerImpl searchDatePicker;
	private JButton searchResetBtn;
	private final static String AM_PM[] = { "AM", "PM" };
	private final static String PAD_VALUES[] = { "1", "2","3","4","5","6", "7","8","9","10",
		                                         "11", "12","13","14","15","16", "17","18","19","20",
		                                         "21", "22","23","24","25","26", "27","28","29","30"
	                                           };
	JScrollPane scrollPane;
	
	JComboBox<String> padValuComboBox;
	JLabel padValueL;

	public void setMilkManagementSystemService(
			MilkManagementSystemService milkManagementSystemService) {
		this.milkManagementSystemService = milkManagementSystemService;
	}

	public void setColor(String color) {
		this.color = color;
	}
    @SuppressWarnings("unchecked")
	public void addItemToIdCombox(String item){
    	formerIDCombo.addItem(item);
    	searchIdComboBox.addItem(item);
    }
	public void init() {
		this.setLayout(null);
		System.setProperty("collectionsStoredFormColor", this.color);
		this.setBackground(Color.getColor("collectionsStoredFormColor"));
		List<String> formerIds = persistenceManager.getFormerByProperty("id");
		
		padValuComboBox=new JComboBox(PAD_VALUES);
		padValuComboBox.setEditable(true);
		new CustomInitialSelectionComboBox(padValuComboBox);
		this.padValueL = this.addComponent(padValueL,
				10,
				10,
				70,
				milkManagementSystemService.lblHeight);
		this.padValueL.setText("Pad Value");
		padValueL.setForeground(Color.RED);

		padValuComboBox.setBounds(padValueL.getX()+padValueL.getWidth(),
				10,
				50,
				milkManagementSystemService.tflHeight);
		this.add(padValuComboBox);
		PadValue padValue=persistenceManager.getPadValue();
		if(null!=padValue){
		padValuComboBox.setSelectedIndex((int)padValue.getValue());
		}else{
			padValue=new PadValue();
			padValue.setCreationTime(DateTimeUtil.convertdateToStringeWithTime(new Date()));
			padValue.setValue((float) 7.0);
			persistenceManager.createPadValueRow(padValue);
			padValuComboBox.setSelectedIndex((int)padValue.getValue());
		}

		
		if (!CollectionUtils.isEmpty(formerIds)) {
			ids = new String[formerIds.size()];
			formerIds.toArray(ids);
		}

		formerIDCombo = new JComboBox(ids);
		// has to be editable
		formerIDCombo.setEditable(true);

		// change the editor's document
		new CustomInitialSelectionComboBox(formerIDCombo);

		this.formerIDL = this.addComponent(formerIDL,
				milkManagementSystemService.xPoint,
				milkManagementSystemService.yPoint,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.formerIDL.setText("Former ID");
		formerIDCombo.setBounds(milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint,
				milkManagementSystemService.tfWidth,
				milkManagementSystemService.tflHeight);
		this.add(formerIDCombo);
		formerIDCombo.setSelectedIndex(-1);

		this.formerIDErrorL = this.addComponent(formerIDErrorL,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth
						+ milkManagementSystemService.tfWidth + 40,
				milkManagementSystemService.yPoint,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.formerIDErrorL.setForeground(Color.RED);

		this.formerNameL = this.addComponent(formerNameL,
				milkManagementSystemService.xPoint,
				milkManagementSystemService.yPoint + 40,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.formerNameL.setText("Former Name");
		this.formerNameTF = this.addComponent(formerNameTF,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint + 40,
				milkManagementSystemService.tfWidth,
				milkManagementSystemService.tflHeight);
		// this.formerNameTF.setEditable(false);
		this.formerNameErrorL = this.addComponent(formerNameErrorL,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth
						+ milkManagementSystemService.tfWidth + 40,
				milkManagementSystemService.yPoint + 40,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		formerNameErrorL.setForeground(Color.RED);

		this.milkPadL = this.addComponent(milkPadL,
				milkManagementSystemService.xPoint,
				milkManagementSystemService.yPoint + 80,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.milkPadL.setText("Milk Pad Value");
		milkPadTF = new FloatField();
		milkPadTF.setBounds(milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint + 80,
				milkManagementSystemService.tfWidth,
				milkManagementSystemService.tflHeight);
		this.add(milkPadTF);
		this.milkPadErrorL = this.addComponent(milkPadErrorL,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth
						+ milkManagementSystemService.tfWidth + 40,
				milkManagementSystemService.yPoint + 80,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.milkPadErrorL.setForeground(Color.RED);

		this.milkValueL = this.addComponent(milkValueL,
				milkManagementSystemService.xPoint,
				milkManagementSystemService.yPoint + 120,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.milkValueL.setText("Milk Quantity");
		milkValueTF = new FloatField();
		milkValueTF.setBounds(milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint + 120,
				milkManagementSystemService.tfWidth,
				milkManagementSystemService.tflHeight);
		this.add(milkValueTF);
		this.milkValueErrorL = this.addComponent(milkValueErrorL,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth
						+ milkManagementSystemService.tfWidth + 40,
				milkManagementSystemService.yPoint + 120,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.milkValueErrorL.setForeground(Color.RED);

		this.milkPriceL = this.addComponent(milkPriceL,
				milkManagementSystemService.xPoint,
				milkManagementSystemService.yPoint + 160,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.milkPriceL.setText("Milk Price");
		milkPriceTF = new IntergerField();
		milkPriceTF.setBounds(milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint + 160,
				milkManagementSystemService.tfWidth,
				milkManagementSystemService.tflHeight);
		this.add(milkPriceTF);
		this.milkPriceErrorL = this.addComponent(milkPriceErrorL,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth
						+ milkManagementSystemService.tfWidth + 40,
				milkManagementSystemService.yPoint + 160,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.milkPriceErrorL.setForeground(Color.RED);

		this.saveBtn = this.addComponent(this.saveBtn,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint + 240, 80, 30);
		this.saveBtn.setText("Save");
		this.saveBtn.setForeground(Color.BLUE);
		this.add(this.saveBtn);

		this.resetBtn = this.addComponent(this.resetBtn,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth + 140,
				milkManagementSystemService.yPoint + 240, 80, 30);
		this.resetBtn.setText("Reset");
		this.resetBtn.setForeground(Color.BLUE);
		this.add(this.resetBtn);

		this.startDateL = this.addComponent(startDateL,
				milkManagementSystemService.xPoint,
				milkManagementSystemService.yPoint + 200,
				milkManagementSystemService.tfWidth, 30);
		this.startDateL.setText("Date");

		startdateTimePicker = new DateTimePicker();
		startdateTimePicker.setFormats(DateFormat.getDateTimeInstance(
				DateFormat.SHORT, DateFormat.MEDIUM));
		startdateTimePicker.setTimeFormat(DateFormat
				.getTimeInstance(DateFormat.MEDIUM));
		startdateTimePicker.setDate(new Date());
		startdateTimePicker.setBounds(milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint + 200,
				milkManagementSystemService.tfWidth, 30);
		this.add(startdateTimePicker);

		displyMilkTable();
		milkDisplayBaseJP.setBounds(milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth * 2
				+ milkManagementSystemService.tfWidth + 50,
				milkManagementSystemService.yPoint,
				milkManagementSystemService.SCREEN_WIDTH / 2 + 50, 400);
		this.add(milkDisplayBaseJP);
		// save button
		this.saveBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				milkPriceErrorL.setText("");
				milkPadErrorL.setText("");
				milkValueErrorL.setText("");
				milkPriceErrorL.setText("");
				formerNameErrorL.setText("");
				if ("".equals(formerNameTF.getText())) {
					formerNameErrorL.setText(ERROR_FORM);
				} else if ("".equals(milkPriceTF.getText())) {
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
				} else {
					Collection collection = new Collection();
					collection.setFormerID(formerIDCombo.getSelectedItem()
							.toString());
					collection.setFormerName(formerNameTF.getText());
					collection.setPadValue(milkPadTF.getText());
					collection.setMilkQuantity(milkValueTF.getText());
					collection.setMilkPrice(milkPriceTF.getText());
					String dateValue = DateTimeUtil
							.convertdateToStringeWithTime(startdateTimePicker
									.getDate());
					collection.setTimeStemp(dateValue);
					String amPM = dateValue.substring(dateValue.length() - 2).trim();
					//LOG.info("AMPM "+amPM);
					if (CollectionUtils.isEmpty(persistenceManager.getCollectionsBy(
							formerIDCombo.getSelectedItem().toString(),
							formerNameTF.getText(), amPM, DateTimeUtil
									.getOnlyDate(startdateTimePicker.getDate())))) {
						persistenceManager.save(collection);
						doFormEmpty();
						addTableData(collection);
					} else {
						JOptionPane.showMessageDialog(
								CollectionsStoredFormJPanel.this,
								"This collection alredy exist",
								"Coded Message", JOptionPane.ERROR_MESSAGE);
					}

					// PadValue padVal=new PadValue();
					// padVal.setCreationTime(new Date());
					// padVal.setValue(10.34);
					// persistenceManager.save(padVal);
					
				}
			}
		});

		this.milkValueTF.getDocument().addDocumentListener(new PQPriceDocumentChangeListener());
		this.milkPadTF.getDocument().addDocumentListener(new PQPriceDocumentChangeListener());

	
	     milkPriceTF.setText("");
		this.resetBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doFormEmpty();
			}
		});
		this.searchAMPMComboBox.addItemListener(new AMPMComboBoxItemLister());
		this.searchIdComboBox.addItemListener(new IDComboBoxItemListener());
		this.searchDatePicker
				.addActionListener(new DatePickComboBoxActionListener());
		this.formerIDCombo.addItemListener(new FormerIDComboxItemListener());

	}

	public void displyMilkTable() {

		GridLayout milkDisplaySearchDL = new GridLayout(4, 0);
		milkDisplaySearchDL.setHgap(2);
		milkDisplaySearchDL.setVgap(2);
		milkDisplayBaseJP = new JPanel();
		colSearchItemsJP = new JPanel();
		List<Collection> colArray = persistenceManager.getAllCollections();
		if (!CollectionUtils.isEmpty(colArray)) {
			rowData = new Object[colArray.size()][FORM_TABLE_COL_COUNT];
			for (int rowCount = 0; rowCount < rowData.length; rowCount++) {
				rowData[rowCount][0] = colArray.get(rowCount).getFormerID();
				rowData[rowCount][1] = colArray.get(rowCount).getFormerName();
				rowData[rowCount][2] = colArray.get(rowCount).getMilkPad();
				rowData[rowCount][3] = colArray.get(rowCount).getMilkQuantity();
				rowData[rowCount][4] = colArray.get(rowCount).getMilkPrice();
				rowData[rowCount][5] = colArray.get(rowCount).getTimeStemp();

			}
		}

		UtilDateModel model = new UtilDateModel();
		// model.setDate(2016, 04, 16);
		model.setValue(new Date());
		model.setSelected(true);

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		searchDatePicker = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());
		collectionDisplayJTab = new JTable(new MilkTableModel(rowData,
				milkColumnNames));
		scrollPane = new JScrollPane(collectionDisplayJTab);

		searchAMPMComboBox = new JComboBox<String>(AM_PM);
		searchIdComboBox = new JComboBox<String>(ids);
		collectionDisplayJTab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		searchResetBtn=new JButton("Reset");
		searchResetBtn.addActionListener(new SearchRestBtnActionListener());
		// amPMComboBox.setSelectedIndex(defaultMode);
		colSearchItemsJP.add(searchIdComboBox);
		colSearchItemsJP.add(searchAMPMComboBox);
		colSearchItemsJP.add(searchDatePicker);
		colSearchItemsJP.add(searchResetBtn);
		milkDisplayBaseJP.add(colSearchItemsJP, BorderLayout.EAST);
		milkDisplayBaseJP.add(scrollPane, BorderLayout.CENTER);
	}

	public void addTableData(Collection collection) {
		DefaultTableModel tableModel = (DefaultTableModel) collectionDisplayJTab
				.getModel();
		String[] addNewRowData = new String[FORM_TABLE_COL_COUNT];
		addNewRowData[0] = collection.getFormerID();
		addNewRowData[1] = collection.getFormerName();
		addNewRowData[2] = collection.getMilkPad();
		addNewRowData[3] = collection.getMilkQuantity();
		addNewRowData[4] = collection.getMilkPrice();
		addNewRowData[5] = collection.getTimeStemp();
		tableModel.insertRow(0, addNewRowData);
		tableModel.fireTableDataChanged();
	}

	public void refreshDataFilter(List<Collection> colList) {

		if (!CollectionUtils.isEmpty(colList)) {
			DefaultTableModel tableModel = (DefaultTableModel) collectionDisplayJTab
					.getModel();
			tableModel.setRowCount(0);
			String[] addNewRowData = new String[FORM_TABLE_COL_COUNT];
			for (Collection collection : colList) {
				addNewRowData[0] = collection.getFormerID();
				addNewRowData[1] = collection.getFormerName();
				addNewRowData[2] = collection.getMilkPad();
				addNewRowData[3] = collection.getMilkQuantity();
				addNewRowData[4] = collection.getMilkPrice();
				addNewRowData[5] = collection.getTimeStemp();
				tableModel.addRow(addNewRowData);
			}
			tableModel.fireTableDataChanged();
		}

	}
	class PadValueComboBoxItemLister implements ItemListener {
		public void itemStateChanged(ItemEvent paramItemEvent) {
			if (paramItemEvent.getStateChange() == ItemEvent.SELECTED) {
				String item = (String) paramItemEvent.getItem();
				List<Collection> amPmColList = persistenceManager
						.getCollectionsBy("", "", item, "");
				if(CollectionUtils.isEmpty(amPmColList)){
					JOptionPane.showMessageDialog(
							CollectionsStoredFormJPanel.this,
							"Collection not exist",
							"Coded Message", JOptionPane.INFORMATION_MESSAGE);
				}else{
				refreshDataFilter(amPmColList);
				}
			}
		}
	};
	class AMPMComboBoxItemLister implements ItemListener {
		public void itemStateChanged(ItemEvent paramItemEvent) {
			if (paramItemEvent.getStateChange() == ItemEvent.SELECTED) {
				String item = (String) paramItemEvent.getItem();
				List<Collection> amPmColList = persistenceManager
						.getCollectionsBy("", "", item, "");
				if(CollectionUtils.isEmpty(amPmColList)){
					JOptionPane.showMessageDialog(
							CollectionsStoredFormJPanel.this,
							"Collection not exist",
							"Coded Message", JOptionPane.INFORMATION_MESSAGE);
				}else{
				refreshDataFilter(amPmColList);
				}
			}
		}
	};

	class IDComboBoxItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent paramItemEvent) {
			if (paramItemEvent.getStateChange() == ItemEvent.SELECTED) {
				String item = (String) paramItemEvent.getItem();
				List<Collection> idColList = persistenceManager
						.getCollectionsBy(item, "", "", "");
				if(CollectionUtils.isEmpty(idColList)){
					JOptionPane.showMessageDialog(
							CollectionsStoredFormJPanel.this,
							"Collection not exist",
							"Coded Message", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
				refreshDataFilter(idColList);
				}
			}

		}

	};

	class DatePickComboBoxActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent paramActionEvent) {
			List<Collection> idColList = persistenceManager.getCollectionsBy(
					"", "", "", DateTimeUtil
							.getOnlyDate((Date) searchDatePicker.getModel()
									.getValue()));
			if(CollectionUtils.isEmpty(idColList)){
				JOptionPane.showMessageDialog(
						CollectionsStoredFormJPanel.this,
						"Collection not exist",
						"Coded Message", JOptionPane.INFORMATION_MESSAGE);
			}else{
			refreshDataFilter(idColList);
			}
		}

	};
	class SearchRestBtnActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent paramActionEvent) {
			List<Collection> idColList = persistenceManager.getAllCollections();
			if(CollectionUtils.isEmpty(idColList)){
				JOptionPane.showMessageDialog(
						CollectionsStoredFormJPanel.this,
						"Collection not exist",
						"Coded Message", JOptionPane.INFORMATION_MESSAGE);
			}else{
			refreshDataFilter(idColList);
			}
		}

	};
	
	class PQPriceDocumentChangeListener implements DocumentListener
	{
		  public void changedUpdate(DocumentEvent e) {
			  setPricValue();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  setPricValue();
			  }
			  public void insertUpdate(DocumentEvent e) {
				
				  setPricValue();
			  }
			  void setPricValue(){
				  String milkPad=milkPadTF.getText();
					String milkValue=milkValueTF.getText();
					if(StringUtils.isNotEmpty(milkPad)
					  &&StringUtils.isNotEmpty(milkValue)){
			       float selectedPadVal=Float.valueOf((String)padValuComboBox.getSelectedItem());
			       String totalPrice=String.valueOf(Float.valueOf(milkPad)*Float.valueOf(milkValue)*selectedPadVal);
					LOG.info("milk total price "+totalPrice);
					milkPriceTF.setText(totalPrice.substring(0, totalPrice.indexOf('.')));
					}
			  }
			}
	
	class FormerIDComboxItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent paramItemEvent) {
			if (paramItemEvent.getStateChange() == ItemEvent.SELECTED) {
				String item = (String) paramItemEvent.getItem();
				Former searchFormer = persistenceManager
						.getFormerBy(item, "");
				formerNameTF.setText(searchFormer.getName());
			}

		}

	};

	class MilkTableModel extends DefaultTableModel {
		public MilkTableModel(Object[][] rowData, String[] columnNames) {
			// TODO Auto-generated constructor stub
			super(rowData, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}

	public void doFormEmpty() {
		formerIDCombo.setSelectedIndex(-1);
		formerNameTF.setText("");
		milkPriceTF.setText("");
		milkPadTF.setText("");
		milkValueTF.setText("");
		milkPriceTF.setText("");
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
}

package com.milkdairy.former;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.milkdairy.collectionsmodule.CollectionsStoredFormJPanel;
import com.milkdairy.fileservice.MilkDairyPersistenceManager;
import com.milkdairy.managedobjects.Former;
import com.milkdairy.services.AlphabatField;
import com.milkdairy.services.CustomInitialSelectionComboBox;
import com.milkdairy.services.DateTimePicker;
import com.milkdairy.services.DateTimeUtil;
import com.milkdairy.services.IntergerField;
import com.milkdairy.services.MDMValidationUtil;
import com.milkdairy.services.MilkManagementSystemService;

public class FormerCreateFormJPanel extends JPanel {

	private MilkDairyPersistenceManager persistenceManager;

	private static final Logger LOG = Logger.getLogger(FormerCreateFormJPanel.class);
	public void setPersistenceManager(MilkDairyPersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}
	
	private JLabel headdingL;
	//private JLabel idL;
	private JLabel phoneNumL;
	private JLabel nameL;
	private JLabel startDateL;
	private JLabel addressL;
	private JLabel emailL;

	//private JLabel idError;
	private JLabel phoneNumError;
	private JLabel nameError;
	private JLabel startDateError;
	private JLabel addressError;
	private JLabel emailError;

	//private JTextField idT;
	private IntergerField phoneNumT;
	private AlphabatField nameT;
	private JTextField emailT;
	private DateTimePicker startdateTimePicker;
	private JTextArea addressTA;

	private JButton submit;
	private JButton reset;
	private JButton search;

	private JLabel updateIDL;
	private JLabel updateNAMEL;

	private String color;

	private JComboBox<String> formerIDCombo;

	//private JComboBox<String> formerNameCombo;

	private MilkManagementSystemService milkManagementSystemService;
	
	private CollectionsStoredFormJPanel collectionsStoredFormJPanel;

	public void setCollectionsStoredFormJPanel(
			CollectionsStoredFormJPanel collectionsStoredFormJPanel) {
		this.collectionsStoredFormJPanel = collectionsStoredFormJPanel;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	private String fontName;
	private int fontStyle;
	private int fontSize;
	private String panelTitle;
	private Font myFont;

	private static final String FORMER_EXIT_MEG="This Former alredy exist";
	private static final String CODED_MEG="FORMER CREATE&UPDATE";
	private static final String SEARCH_MEG="This Former not exist";
	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
	}

	// private String fontColor;

	@SuppressWarnings("unchecked")
	public void init() {

		this.setLayout(null);
		System.setProperty("collectionsStoredFormColor", this.color);
		this.setBackground(Color.getColor("collectionsStoredFormColor"));

		myFont = new Font(this.fontName, this.fontStyle, this.fontSize);

		this.headdingL = this.addComponent(headdingL,
				milkManagementSystemService.SCREEN_WIDTH / 3 + 50,
				milkManagementSystemService.yPoint - 80,
				milkManagementSystemService.lblWidth + 180,
				milkManagementSystemService.lblHeight + 20);
		this.headdingL.setText(this.panelTitle);
		this.headdingL.setFont(myFont);

//		this.idL = this.addComponent(idL, milkManagementSystemService.xPoint,
//				milkManagementSystemService.yPoint,
//				milkManagementSystemService.lblWidth,
//				milkManagementSystemService.lblHeight);
//		this.idL.setText("Id");
//		this.idT=this.addComponent(idT, milkManagementSystemService.xPoint
//				+ milkManagementSystemService.lblWidth + 20,
//				milkManagementSystemService.yPoint,
//				milkManagementSystemService.tfWidth,
//				milkManagementSystemService.tflHeight);
	

		this.nameL = this.addComponent(nameL,
				milkManagementSystemService.xPoint,
				milkManagementSystemService.yPoint + 40,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.nameL.setText("Name");
		nameT=new AlphabatField();
		nameT.setBounds( milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint + 40,
				milkManagementSystemService.tfWidth,
				milkManagementSystemService.tflHeight);
		this.add(nameT);

		this.phoneNumL = this.addComponent(phoneNumL,
				milkManagementSystemService.xPoint,
				milkManagementSystemService.yPoint + 80,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.phoneNumL.setText("Phone Number");
        phoneNumT=new IntergerField();
		phoneNumT.setBounds(milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint + 80,
				milkManagementSystemService.tfWidth,
				milkManagementSystemService.tflHeight);
		this.add(phoneNumT);

		this.startDateL = this.addComponent(startDateL,
				milkManagementSystemService.xPoint,
				milkManagementSystemService.yPoint + 120,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.startDateL.setText("Start Date");
		/*
		 * this.startDateT = this.addComponent(startDateT,
		 * milkManagementSystemService.xPoint +
		 * milkManagementSystemService.lblWidth + 20,
		 * milkManagementSystemService.yPoint + 120,
		 * milkManagementSystemService.tfWidth,
		 * milkManagementSystemService.tflHeight); this.startDateT.setText(new
		 * Date().toString());
		 */
		startdateTimePicker = new DateTimePicker();
		startdateTimePicker.setFormats(DateFormat.getDateTimeInstance(
				DateFormat.SHORT, DateFormat.MEDIUM));
		startdateTimePicker.setTimeFormat(DateFormat
				.getTimeInstance(DateFormat.MEDIUM));
		startdateTimePicker.setDate(new Date());
		startdateTimePicker.setBounds(milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint + 120,
				milkManagementSystemService.tfWidth,
				milkManagementSystemService.tflHeight);
		this.add(startdateTimePicker);

		this.addressL = this.addComponent(addressL,
				milkManagementSystemService.xPoint,
				milkManagementSystemService.yPoint + 160,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.addressL.setText("Address");
		addressTA=this.addComponent(addressTA, milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint + 160,
				milkManagementSystemService.tfWidth,
				milkManagementSystemService.tflHeight);

//		this.idError = this.addComponent(idError,
//				milkManagementSystemService.xPoint
//						+ milkManagementSystemService.lblWidth
//						+ milkManagementSystemService.tfWidth + 40,
//				milkManagementSystemService.yPoint,
//				milkManagementSystemService.lblWidth,
//				milkManagementSystemService.lblHeight);
//		this.idError.setForeground(Color.RED);

		this.nameError = this.addComponent(nameError,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth
						+ milkManagementSystemService.tfWidth + 40,
				milkManagementSystemService.yPoint + 40,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.nameError.setForeground(Color.RED);

		this.phoneNumError = this.addComponent(phoneNumError,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth
						+ milkManagementSystemService.tfWidth + 40,
				milkManagementSystemService.yPoint + 80,
				milkManagementSystemService.lblWidth+100,
				milkManagementSystemService.lblHeight);
		this.phoneNumError.setForeground(Color.RED);

		/*
		 * this.startDateError = this.addComponent(startDateError,
		 * milkManagementSystemService.xPoint +
		 * milkManagementSystemService.lblWidth +
		 * milkManagementSystemService.tfWidth + 40,
		 * milkManagementSystemService.yPoint + 120,
		 * milkManagementSystemService.lblWidth,
		 * milkManagementSystemService.lblHeight);
		 * this.startDateError.setText("");
		 * this.idError.setForeground(Color.RED);
		 */

		this.addressError = this.addComponent(addressError,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth
						+ milkManagementSystemService.tfWidth + 40,
				milkManagementSystemService.yPoint + 160,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight + 50);
		this.addressError.setForeground(Color.RED);

		this.emailL = this.addComponent(emailL,
				milkManagementSystemService.xPoint,
				milkManagementSystemService.yPoint
						+ milkManagementSystemService.lblHeight + 50 + 200,
				milkManagementSystemService.lblWidth,
				milkManagementSystemService.lblHeight);
		this.emailL.setText("Email");
		emailT=this.addComponent(emailT, milkManagementSystemService.xPoint
				+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint
						+ milkManagementSystemService.lblHeight + 50 + 200,
				milkManagementSystemService.tfWidth,
				milkManagementSystemService.tflHeight);
		this.emailError = this.addComponent(emailError,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth
						+ milkManagementSystemService.tfWidth + 40,
				milkManagementSystemService.yPoint
						+ milkManagementSystemService.lblHeight + 30 + 200,
				milkManagementSystemService.lblWidth+100,
				milkManagementSystemService.lblHeight + 50);
		this.emailError.setForeground(Color.RED);

		this.submit = this.addComponent(this.submit,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth + 20,
				milkManagementSystemService.yPoint + 310, 80, 30);
		this.submit.setText("Submit");
		this.submit.setForeground(Color.BLUE);
		this.add(this.submit);

		this.reset = this.addComponent(this.reset,
				milkManagementSystemService.xPoint
						+ milkManagementSystemService.lblWidth + 140,
				milkManagementSystemService.yPoint + 310, 80, 30);
		this.reset.setText("Reset");
		this.reset.setForeground(Color.BLUE);
		this.add(this.reset);
		/* update search */
		List<String> formerids=persistenceManager.getFormerByProperty("id");
		String ids[]=new String[]{};
		if(!CollectionUtils.isEmpty(formerids)){
			ids=new String[formerids.size()];
			formerids.toArray(ids);
		}
		
		formerIDCombo = new JComboBox(ids);
		// has to be editable
		formerIDCombo.setEditable(true);

		// change the editor's document
		new CustomInitialSelectionComboBox(formerIDCombo);

//		formerIDCombo.setBounds(milkManagementSystemService.xPoint
//				+ milkManagementSystemService.lblWidth * 2
//				+ milkManagementSystemService.tfWidth + 40,
//				milkManagementSystemService.yPoint,
//				milkManagementSystemService.lblWidth + 20,
//				milkManagementSystemService.lblHeight + 10);
//		formerIDCombo.setMaximumRowCount(5);
		formerIDCombo.setBounds(milkManagementSystemService.xPoint
		+ milkManagementSystemService.lblWidth * 2
		+ milkManagementSystemService.tfWidth + 40,
		milkManagementSystemService.yPoint
				+ milkManagementSystemService.lblHeight + 20,
		milkManagementSystemService.lblWidth + 20,
		milkManagementSystemService.lblHeight + 10);
		formerIDCombo.setMaximumRowCount(5);
		this.add(formerIDCombo);

		List<String> formerNames=persistenceManager.getFormerByProperty("name"); 
		String names[]=new String[]{};
		if(!CollectionUtils.isEmpty(formerNames)){
			names=new String[formerNames.size()];
			formerNames.toArray(names);
		}
		
//		formerNameCombo = new JComboBox(names);
//		// has to be editable
//		formerNameCombo.setEditable(true);
//
//		// change the editor's document
//		new CustomInitialSelectionComboBox(formerNameCombo);
//
//		// customerIDListScrollPane = new JScrollPane(customerIDCombo);
//		formerNameCombo.setBounds(milkManagementSystemService.xPoint
//				+ milkManagementSystemService.lblWidth * 2
//				+ milkManagementSystemService.tfWidth + 40,
//				milkManagementSystemService.yPoint
//						+ milkManagementSystemService.lblHeight + 20,
//				milkManagementSystemService.lblWidth + 20,
//				milkManagementSystemService.lblHeight + 10);
//		formerNameCombo.setMaximumRowCount(5);
//		this.add(formerNameCombo);

		this.search = this.addComponent(this.search,
				milkManagementSystemService.xPoint
						+ (milkManagementSystemService.lblWidth * 2)
						+ milkManagementSystemService.tfWidth + 60,
				milkManagementSystemService.yPoint + 310, 80, 30);
		this.search.setText("Search");
		this.search.setForeground(Color.BLUE);
		this.add(this.search);

		this.submit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//idError.setText("");
				nameError.setText("");
				phoneNumError.setText("");
				addressError.setText("");
				emailError.setText("");
				 if ("".equals(nameT.getText())) {

					nameError.setText("Can't be empty");
				} else if ("".equals(phoneNumT.getText())||!MDMValidationUtil.isValidPhoneNum(phoneNumT.getText())) {

					phoneNumError.setText("Please enter valid number");
				} else if ("".equals(addressTA.getText())) {

					addressError.setText("Can't be empty");
				} else if ("".equals(emailT.getText())||!MDMValidationUtil.isValidEmailAddress(emailT.getText())) {

					emailError.setText("Please enter valid email");
				}
				else{
		            
					Former former=new Former();
					former.setId(phoneNumT.getText());
					former.setName(nameT.getText());
					former.setPhoneNum(phoneNumT.getText());
					former.setStartdate(DateTimeUtil.convertdateToStringeWithTime(startdateTimePicker.getDate()));
					former.setEmail(emailT.getText());
					String address=addressTA.getText();
					address=address.replaceAll(",", "");
					address=address.replace('\n', ':');
					former.setAddress(address);
					Former searchFormer=persistenceManager.getFormerBy(phoneNumT.getText().trim(), nameT.getText().trim());
					if(searchFormer==null){
					persistenceManager.save(former);
					formerIDCombo.addItem(phoneNumT.getText());
					collectionsStoredFormJPanel.addItemToIdCombox(phoneNumT.getText());
					formEmpty();
					}
					else{
						 JOptionPane.showMessageDialog(FormerCreateFormJPanel.this,
								 FORMER_EXIT_MEG, CODED_MEG,
						            JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});

		this.reset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				formEmpty();		
			}
		});
		this.search.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String forID = (String) formerIDCombo.getSelectedItem();
				//String forName = (String) formerNameCombo.getSelectedItem();
				LOG.info("Search value id "+forID);
				Former former=persistenceManager.getFormerBy(forID, "");
				if(former!=null){
				//idT.setText(former.getId());
				nameT.setText(former.getName());
				phoneNumT.setText(former.getPhoneNum());
				addressTA.setText(former.getAddress());
				emailT.setText(former.getEmail());
				}
				else{
					 JOptionPane.showMessageDialog(FormerCreateFormJPanel.this,
							 SEARCH_MEG, CODED_MEG,
					            JOptionPane.ERROR_MESSAGE);
					LOG.info("Search result is null");
				}
			}
		});

	}

	public void setMilkManagementSystemService(
			MilkManagementSystemService milkManagementSystemService) {
		this.milkManagementSystemService = milkManagementSystemService;
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
		// comp.setSize(50, 100);
		// comp.setOpaque(true);
		this.add(comp);
		return comp;
	}

	public JTextArea addComponent(JTextArea comp, int xx, int yy, int width,
			int height) {
		comp = new JTextArea(5, 20);
		comp.setBounds(xx, yy, width, height);
		comp.setSize(width, 100);
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
public void formEmpty(){
	//idError.setText("");
	nameError.setText("");
	phoneNumError.setText("");
	addressError.setText("");
	emailError.setText("");
	//idT.setText("");
	nameT.setText("");
	phoneNumT.setText("");
	addressTA.setText("");
	emailT.setText("");
}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milkdairy.admin;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.milkdairy.users.LoggingService;
import com.milkdairy.users.LoginJFrame;

/**
 *
 * @author nagarjuna
 */
public class MilkDairyManagement {
	private static final Logger LOG = Logger
			.getLogger(MilkDairyManagement.class);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LOG.trace("Executing main()......");
				Resource resource = new ClassPathResource(
						"applicationContext.xml");
				BeanFactory factory = new XmlBeanFactory(resource);

				JPanel milkDairyManagementJPanel = (MilkDairyManagementJPanel) factory
						.getBean("milkDairyManagementJPanel");

				UIManager.put("swing.boldmetal", Boolean.FALSE);

				new LoginJFrame(milkDairyManagementJPanel,
						(LoggingService) factory.getBean("loggingService"))
						.setVisible(true);

			}

		});
	}

}

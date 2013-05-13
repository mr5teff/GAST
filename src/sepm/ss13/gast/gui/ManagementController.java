package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Konfiguration;
import sepm.ss13.gast.service.Service;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ManagementController implements Initializable{
	
	
	 private static Logger log = Logger.getLogger(Application.class);
	 
	 private ApplicationContext ac;
	 private GUIManager gast;
	 private Service s;
	 
	 	 
	 public void initialize(URL location, ResourceBundle resources) {
		 ac = new ClassPathXmlApplicationContext("spring-config.xml");
		 gast = (GUIManager) ac.getBean("GUIManager");
		 //s = (Service) ac.getBean("totalesService");
		 
		 /*Konfiguration k = null;
		try {
			k = s.loadKonfiguration();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 name.setText(k.getName());
		 adresse.setText(k.getAdresse());
		 tel.setText(k.getTel());
		 tischanzahl.setText(String.valueOf(k.getTischanzahl()));*/
		}
	 
	 @FXML
	 public void clickOnKassa(ActionEvent event) {
		 gast.replaceSceneContent("Kassa.fxml");
	 }
	 
	 @FXML
	 public void clickOnKueche(ActionEvent event) {
		 gast.replaceSceneContent("Kueche.fxml");
	 }
	 
	

	

}

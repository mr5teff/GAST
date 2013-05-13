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
		
	 private GAST application;
	 
	 private ApplicationContext ac;
	 private Service s;
	 
	 @FXML private Label name;
	 @FXML private Label adresse;
	 @FXML private Label tel;
	 @FXML private Label logo;
	 @FXML private Label tischanzahl;
	 
	 public void initialize(URL location, ResourceBundle resources) {
		 ac = new ClassPathXmlApplicationContext("spring-config.xml");
		 //s = (Service) ac.getBean("totalesService");
		}
	    
	 public void setApp(GAST application){
	        this.application = application;
	 }
	 
	 @FXML
	 public void clickOnKassa(ActionEvent event) {
		 if (application == null){
	            // We are running in isolated FXML, possibly in Scene Builder.
	            // NO-OP.
			 	log.warn("No application set. No operation!");
	        } 
		 else {
			 
			 application.gotoKassa();
	           
	     }
	 }
	 
	 @FXML
	 public void clickOnKueche(ActionEvent event) {
		 if (application == null){
	            // We are running in isolated FXML, possibly in Scene Builder.
	            // NO-OP.
			 	log.warn("No application set. No operation!");
	        } 
		 else {
			 
			 application.gotoKueche();
	           
	     }
	 }
	 
	 @FXML
	 public void saveKonfiguration(ActionEvent e) {
		 Konfiguration k = new Konfiguration(name.getText(),adresse.getText(),tel.getText(),null,Integer.parseInt(tischanzahl.getText()));
		 
		 try {
			s.saveKonfiguration(k);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 }

	

}

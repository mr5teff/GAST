package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class WelcomeScreenController implements Initializable,Controller {
	
	 private static Logger log = Logger.getLogger(Application.class);
	 private ApplicationContext ac;
		private Stage stage;
		
	 public void initialize(URL arg0, ResourceBundle arg1) {
		 ac = new ClassPathXmlApplicationContext("spring-config.xml");
		}
	 
	 public void setStage(Stage s) {
	    	this.stage=s;
	 }
	 
	 @FXML
	 public void clickOnKassa(ActionEvent event) {
		 GUITools.loadFXML("Kassa.fxml",stage);
	 }

	 @FXML
	 public void clickOnKueche(ActionEvent event) {
		 GUITools.loadFXML("Kueche.fxml",stage);
	 }
	 
	 @FXML
	 public void clickOnManagement(ActionEvent event) {
		 GUITools.loadFXML("Management.fxml",stage);
	 }
}

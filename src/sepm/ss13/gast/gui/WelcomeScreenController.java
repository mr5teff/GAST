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

public class WelcomeScreenController extends Controller {
	
	 private static Logger log = Logger.getLogger(Application.class);
	 private ApplicationContext ac;
	 
	 public void initialize(URL arg0, ResourceBundle arg1) {
		 ac = new ClassPathXmlApplicationContext("spring-config.xml");
	 }
	 
	 @FXML
	 public void clickOnKassa(ActionEvent event) {
		 GUITools.loadFXML("Kassa.fxml",this.getStage());
	 }

	 @FXML
	 public void clickOnKueche(ActionEvent event) {
		 GUITools.loadFXML("Kueche.fxml",this.getStage());
	 }
	 
	 @FXML
	 public void clickOnManagement(ActionEvent event) {
		 GUITools.loadFXML("Management.fxml",this.getStage());
	 }
}

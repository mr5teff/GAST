package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.service.Service;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class ManagementController extends Controller {
	
	 private static Logger log = Logger.getLogger(Application.class);
	 
	 private ApplicationContext ac;
	 private Service s;
	 
	 @FXML private SpeisekarteController speisekarteContentController;
	 	 
	 public void initialize(URL location, ResourceBundle resources) {
		 ac = new ClassPathXmlApplicationContext("spring-config.xml");
		 speisekarteContentController.setParentController(this);
	 }
	 
	 @FXML
	 public void clickOnKassa(ActionEvent event) {
		 GUITools.loadFXML("Kassa.fxml",this.getStage());
	 }
	 
	 @FXML
	 public void clickOnKueche(ActionEvent event) {
		 GUITools.loadFXML("Kueche.fxml",this.getStage());
	 }
}

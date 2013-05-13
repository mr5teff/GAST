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

public class KassaController implements Initializable {
	
	private ApplicationContext ac;
	private GUIManager gast;
	 private static Logger log = Logger.getLogger(Application.class);
	 
	 public void initialize(URL location, ResourceBundle resources) {
		 ac = new ClassPathXmlApplicationContext("spring-config.xml");
		 gast = (GUIManager) ac.getBean("GUIManager");
	}
	 
	 @FXML
	 public void clickOnManagement(ActionEvent event) {
		 gast.replaceSceneContent("Management.fxml");
	 }
	 
	 @FXML
	 public void clickOnKueche(ActionEvent event) {
		 gast.replaceSceneContent("Kueche.fxml");
	 }

	

}

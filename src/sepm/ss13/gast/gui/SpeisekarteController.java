package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpeisekarteController implements Initializable {
	
	private ApplicationContext ac;
	private GUIManager gast;
	 private static Logger log = Logger.getLogger(Application.class);

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		 ac = new ClassPathXmlApplicationContext("spring-config.xml");
		 gast = (GUIManager) ac.getBean("GUIManager");
		
	}
	
	 @FXML
	 public void clickOnNeueKategorie(ActionEvent event) {
		 
		System.out.println("GEEEHT");
	 }
	 
	 @FXML
	 public void clickOnKategorieBearbeiten(ActionEvent event) {
		 
		System.out.println("GEEEHT");
	 }
	 @FXML
	 public void clickOnKategorieLoeschen(ActionEvent event) {
		 
		System.out.println("GEEEHT");
	 }
	 @FXML
	 public void clickOnNeuesProdukt(ActionEvent event) {
		 
		System.out.println("GEEEHT");
	 }
	 @FXML
	 public void clickOnProduktBearbeiten(ActionEvent event) {
		 
		System.out.println("GEEEHT");
	 }
	 @FXML
	 public void clickOnProduktLoeschen(ActionEvent event) {
		 
		System.out.println("GEEEHT");
	 }
	

}

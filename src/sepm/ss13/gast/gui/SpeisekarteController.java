package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.service.GASTService;
import sepm.ss13.gast.service.Service;

public class SpeisekarteController implements Initializable {
	
	private ApplicationContext ac;
	private GUIManager gast;
	 private static Logger log = Logger.getLogger(Application.class);
	 private Service s;
	 private ArrayList<ProduktKategorie> DAOkategorien;
	 private ObservableList<String> kategorieItems;
	 
	 private static final ProduktKategorie kategoreiBlank = new ProduktKategorie();
	 
	 @FXML private ListView<String> kategorieListe;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		 ac = new ClassPathXmlApplicationContext("spring-config.xml");
		 gast = (GUIManager) ac.getBean("GUIManager");
		 
		 s = (Service) ac.getBean("GASTService");
		 
		 //Lade alle Produktkategorien
		 try {
			 DAOkategorien = s.searchProduktKategorie(kategoreiBlank);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		kategorieItems = FXCollections.observableArrayList();
		for(ProduktKategorie pk: DAOkategorien)
		{
			kategorieItems.add(pk.getBezeichnung());
		}	 
		kategorieListe.setItems(kategorieItems);
		
		
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

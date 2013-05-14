package sepm.ss13.gast.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

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
	 private ArrayList<Produkt> DAOprodukte;
	 private ObservableList<ProduktKategorie> kategorieItems;
	 private ObservableList<Produkt> produktItems;
	 
	 private NeueProduktKategorieDialog npkd;
	 
	 private static final ProduktKategorie kategorieBlank = new ProduktKategorie();
	 
	 @FXML private ListView<ProduktKategorie> kategorieListView;
	 @FXML private ListView<Produkt> produktListView;
	 

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		 ac = new ClassPathXmlApplicationContext("spring-config.xml");
		 gast = (GUIManager) ac.getBean("GUIManager");
		 
		 s = (Service) ac.getBean("GASTService");
		 
		 initListView();
		 	
		npkd = new NeueProduktKategorieDialog(gast.getStage().getScene().getWindow(), Modality.WINDOW_MODAL, "Neue Produktkategorie");
	}
	
	private void initListView()
	{
		 kategorieListView.setCellFactory(new Callback<ListView<ProduktKategorie>, ListCell<ProduktKategorie>>() {
		      public ListCell<ProduktKategorie> call(ListView<ProduktKategorie> list) {
		         return new ProduktKategorieCell();
		     }
		 });
		 
		 produktListView.setCellFactory(new Callback<ListView<Produkt>, ListCell<Produkt>>() {
		      public ListCell<Produkt> call(ListView<Produkt> list) {
		         return new ProduktCell();
		     }
		 });
		 
		 kategorieItems = FXCollections.observableArrayList();
		 produktItems = FXCollections.observableArrayList();
		 
		 //Lade alle Produktkategorien
		 try {
			 DAOkategorien = s.searchProduktKategorie(kategorieBlank);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //Mit allen Produktkategorien befüllen
		 kategorieItems.addAll(DAOkategorien);
		 kategorieListView.setItems(kategorieItems);
		
		 kategorieListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProduktKategorie>() {	   
		    public void changed(ObservableValue<? extends ProduktKategorie> observable, ProduktKategorie oldValue, ProduktKategorie newValue) {
		        // Your action here
		        System.out.println("Selected item: " + newValue.getBezeichnung());
		        produktListeBefuellen(newValue);
		    }
		 });
		 
		 produktListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produkt>() {	   
			    public void changed(ObservableValue<? extends Produkt> observable, Produkt oldValue, Produkt newValue) {
			        // Your action here
			       
			    }
		 });
		 
	}
	
	private void produktListeBefuellen(ProduktKategorie pk)
	{
		Produkt searchP = new Produkt();
		searchP.setKategorie(pk.getId());
		//Lade alle Produkte einer bestimmten Kategorie
		 try {
			 DAOprodukte = s.searchProdukt(searchP);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		 produktItems.clear();
		 produktItems.addAll(DAOprodukte);
		 produktListView.setItems(produktItems);
	}
	
	 @FXML
	 public void clickOnNeueKategorie(ActionEvent event) {
		 
		 npkd.clearForm();
		 npkd.setTitle("Neue Kategorie anlegen");
		 npkd.show();
	 }
	 
	 @FXML
	 public void clickOnKategorieBearbeiten(ActionEvent event) {
		 npkd.setContent(kategorieListView.getSelectionModel().getSelectedItem());
		 npkd.setTitle("Kategorie bearbeiten");
		 npkd.show();
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

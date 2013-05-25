package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;


import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.service.Service;

public class SpeisekarteController extends Controller{
	private Service s;
	 
	 private ArrayList<ProduktKategorie> DAOkategorien;
	 private ArrayList<Produkt> DAOprodukte;
	 private ObservableList<ProduktKategorie> kategorieItems;
	 private ObservableList<Produkt> produktItems;
	 
	 private static final ProduktKategorie kategorieBlank = new ProduktKategorie();
	 private ProduktKategorie selectedPK = null;
	 
	 private Produkt selectedProdukt = null;
	 
	 
	 @FXML private ListView<ProduktKategorie> kategorieListView;
	 @FXML private ListView<Produkt> produktListView;
	 
	public void initialize(URL arg0, ResourceBundle arg1) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
			 
		initListView();
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
		 
		 refreshKategorieListView();
		
		 kategorieListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProduktKategorie>() {	   
		    public void changed(ObservableValue<? extends ProduktKategorie> observable, ProduktKategorie oldValue, ProduktKategorie newValue) {
		        // Your action here
		    	if(newValue != null)
		    	{
		    		System.out.println("Selected item: " + newValue.getBezeichnung());
		    		produktListeBefuellen(newValue);
		    		selectedPK = newValue;
		    	}
		    	else
		    	{
		    		selectedPK = null;
		    	}
		    }
		 });
		 
		 produktListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produkt>() {	   
			    public void changed(ObservableValue<? extends Produkt> observable, Produkt oldValue, Produkt newValue) {
			        // Your action here
			       
			    }
		 });
		 
	}
	
	public void refreshKategorieListView()
	{
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
		 kategorieItems.clear();
		 kategorieItems.addAll(DAOkategorien);
		 kategorieListView.setItems(kategorieItems);
	}
	
	public void produktListeBefuellen(ProduktKategorie pk)
	{
		if(pk == null)
			return;
		
		Produkt searchP = new Produkt();
		searchP.setKategorie(pk.getId());
		searchP.setDeleted(false);
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
		 Stage stage = GUITools.openDialog("Produktkategorie anlegen",this.getStage());
		 GUITools.loadFXML("NeueKategorieDialog.fxml", stage, this);
		 stage.show();
	 }
	 
	 @FXML
	 public void clickOnKategorieBearbeiten(ActionEvent event) {
		 if(selectedPK == null)
			 return;
		 
		 Stage stage = GUITools.openDialog("Produktkategorie bearbeiten",this.getStage());
		 ProduktKategorieDialogController pkdc = (ProduktKategorieDialogController) GUITools.loadFXML("NeueKategorieDialog.fxml", stage, this);
		 
		 pkdc.setPK(selectedPK);
		 stage.show();
	 }
	 
	 @FXML
	 public void clickOnKategorieLoeschen(ActionEvent event) {
		 
		 selectedPK = kategorieListView.getSelectionModel().getSelectedItem();
		 
		 if(selectedPK == null)
		 {
			Dialogs.showInformationDialog(this.getStage(), "Bitte wählen Sie eine Kategorie die Sie löschen wollen aus.", "Produktkategorie löschen", "Information");
			return;
		 }
		 
		 DialogResponse drp = Dialogs.showConfirmDialog(this.getStage(), "Wollen Sie die Produktkategorie wirklich löschen?", "Produktkategorie löschen", "Frage", DialogOptions.YES_NO);
		 
		 if(drp.equals(DialogResponse.YES))
		 {
			 try {
				 s.deleteProduktKategorie(selectedPK);
			} catch (IllegalArgumentException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (DAOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} 	 
			 
			 refreshKategorieListView();
		 }
	 }
	 
	 
	 @FXML
	 public void clickOnNeuesProdukt(ActionEvent event) {
		 if(selectedPK == null)
			 return;
		 
		 Stage stage = GUITools.openDialog("Produkt erstellen", this.getStage());
		 ProduktDialogController pdc = (ProduktDialogController) GUITools.loadFXML("NeuesProduktDialog.fxml", stage, this);
		 pdc.setPK(selectedPK);
		 stage.show();
	 }
	 
	 @FXML
	 public void clickOnProduktBearbeiten(ActionEvent event) {
		 
		 Produkt p = produktListView.getSelectionModel().getSelectedItem();
		 if(p == null || selectedPK == null)
			 return;
		 
		 Stage stage = GUITools.openDialog("Produkt erstellen", this.getStage());
		 ProduktDialogController pdc = (ProduktDialogController) GUITools.loadFXML("NeuesProduktDialog.fxml", stage, this);
		 pdc.setPK(selectedPK);
		 pdc.setProdukt(p);
		 stage.show();
	 }
	 
	 @FXML
	 public void clickOnProduktLoeschen(ActionEvent event) {
		 
		 selectedProdukt = produktListView.getSelectionModel().getSelectedItem();
		 
		 if(selectedProdukt == null)
		 {
			Dialogs.showInformationDialog(this.getStage(), "Bitte wählen Sie ein Produkt aus, welches Sie löschen wollen.", "Produkt löschen", "Information");
			return;
		 }
		 
		 DialogResponse drp = Dialogs.showConfirmDialog(this.getStage(), "Wollen Sie das Produkt wirklich löschen?", "Produkt löschen", "Frage", DialogOptions.YES_NO);
		 
		 if(drp.equals(DialogResponse.YES))
		 {
			 try {
				 s.deleteProdukt(selectedProdukt);
			} catch (IllegalArgumentException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (DAOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} 	 
			 
			 produktListeBefuellen(selectedPK);
		 }
	 }

	 
	 
	 
	 
}

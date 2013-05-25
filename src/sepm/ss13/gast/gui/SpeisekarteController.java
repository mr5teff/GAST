package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import name.antonsmirnov.javafx.dialog.Dialog;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.service.Service;

public class SpeisekarteController extends Controller implements EventHandler<ActionEvent>{
	private Service s;
	 
	 private ArrayList<ProduktKategorie> DAOkategorien;
	 private ArrayList<Produkt> DAOprodukte;
	 private ObservableList<ProduktKategorie> kategorieItems;
	 private ObservableList<Produkt> produktItems;
	 
	 private static final ProduktKategorie kategorieBlank = new ProduktKategorie();
	 private ProduktKategorie selectedPK;
	 
	 private Dialog deleteDialog;
	 
	 @FXML private ListView<ProduktKategorie> kategorieListView;
	 @FXML private ListView<Produkt> produktListView;
	 
	public void initialize(URL arg0, ResourceBundle arg1) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
		
		initConfirmationDialog();
		 
		initListView();
	}
	
	private void initConfirmationDialog()
	{
		Dialog.Builder db = Dialog.buildConfirmation("Produktkategorie löschen", "Wollen Sie wirklich die ausgewählte Produktkategorie löschen?");
		db.addYesButton("Ja", this);
		db.addNoButton("Nein", this);
		db.addCancelButton("Abbrechen", this);
		
		deleteDialog = db.build();
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
			Dialog.showInfo("Produktkategorie löschen", "Bitte wählen Sie eine Kategorie die Sie löschen wollen aus.", this.getStage().getScene().getWindow());
			return;
		 }
		 
		 deleteDialog.show();
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
		 
		System.out.println("GEEEHT");
	 }

	 
	 
	 
	 
	 //EventHandler

	public void handle(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource().getClass() == Button.class)
		{
			Button b = (Button) e.getSource();
			
			if(b.getText() == "Ja" )
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
				selectedPK = null;
				
				refreshKategorieListView();
			}
			else if(b.getText() == "Nein")
			{
				System.out.println("Nein");
			}
			else if(b.getText() == "Abbrechen")
			{
				System.out.println("Abbrechen");
			}
		}
	}
}

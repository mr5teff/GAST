package sepm.ss13.gast.gui;

import java.net.URL;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.ResourceBundle;


import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Ware;
import sepm.ss13.gast.service.Service;


public class WarenController extends Controller {

	private Service s;
	 @FXML private TextField name;
	 @FXML private ComboBox<String> einheit; 
	 
	 @FXML private ListView<Ware> warenListView;
	 
     private ObservableList<String> einheiten;
	 private ObservableList<Ware> wareItems;
	 
	 private Ware selectedW;
	 

	 private ArrayList<Ware> DAOwaren;
	 
	 private static final Ware wareBlank = new Ware(null,"%","%",-1);
	 


		public void initialize(URL arg0, ResourceBundle arg1) {
			s = (Service) this.getApplicationContext().getBean("GASTService");
			
			einheiten = FXCollections.observableArrayList();
			einheiten.add("Gramm");
			einheiten.add("Milliliter");
			einheiten.add("Prozent");
			einheit.setItems(einheiten);
			einheit.setValue("Gramm");
			
			
			warenListView.setCellFactory(new Callback<ListView<Ware>, ListCell<Ware>>() {
			      public ListCell<Ware> call(ListView<Ware> list) {
			         return new WareCell();
			     }
			 });
			 wareItems = FXCollections.observableArrayList();
			 
			 refreshWareListView();
			
		}	
	

		public void refreshWareListView()
		{
			 //Lade alle Produktkategorien
			 try {
				 DAOwaren = s.searchWare(wareBlank);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 //Mit allen Produktkategorien befüllen
			 wareItems.clear();
			 wareItems.addAll(DAOwaren);
			 warenListView.setItems(wareItems);
		}
		
		 @FXML
		 public void saveWare(ActionEvent e) {

			if(name.getText().isEmpty()){
				Dialogs.showInformationDialog(this.getStage(), "Es wurden nicht alle Felder befüllt!", "Ware speichern", "Information");
				return;
			}
			String n_save = name.getText();
			String e_save = einheit.getValue().toLowerCase();

			Ware w_saved = null;
			try {
				Ware w = new Ware(-1,n_save,e_save,0);
				w_saved = s.createWare(w);
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/*if(w_saved.getId() != -1){
				
			}else{
				
			}*/
			refreshWareListView();
			 
		 }
		 
		 @FXML
		 public void clickOnWareLoeschen(ActionEvent event) {
			 
			 selectedW = warenListView.getSelectionModel().getSelectedItem();
			 
			 if(selectedW == null)
			 {
					Dialogs.showInformationDialog(this.getStage(), "Bitte wählen Sie eine Ware aus, welches Sie löschen wollen.", "Ware löschen", "Information");
					return;
			 }
			 
			 DialogResponse drp = Dialogs.showConfirmDialog(this.getStage(), "Wollen Sie die Produktkategorie wirklich löschen?", "Produktkategorie löschen", "Frage", DialogOptions.YES_NO);
			 
			 
			 if(true /*|| drp.equals(DialogResponse.YES)*/)
			 {
				 try {
					 s.deleteWare(selectedW);
				} catch (IllegalArgumentException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} catch (DAOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} 	 
				 
				 refreshWareListView();
			 }
			 

		 }
}

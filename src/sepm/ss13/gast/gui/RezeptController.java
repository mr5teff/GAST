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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.domain.Rezept;
import sepm.ss13.gast.domain.Ware;
import sepm.ss13.gast.service.Service;

public class RezeptController extends Controller {
	private Service s;

	@FXML
	private ComboBox<String> produktKategorieBox;
	private ObservableList<String> pkList;
	private ArrayList<ProduktKategorie> pkA;

	@FXML
	private ComboBox<String> produkteBox;
	private ObservableList<String> pList;
	private ArrayList<Produkt> pA;
	
	@FXML
	private ComboBox<String> wareBox;
	private ObservableList<String> wList;
	private ArrayList<Ware> wA;
	
	@FXML
	private TextField wMenge;
	
	
	@FXML
	private TableView<Rezept> rezeptTable;
	@FXML
	private TableColumn<Rezept,String> wareCol;
	@FXML
	private TableColumn<Rezept,Integer> mengeCol;
	private ObservableList<Rezept> rezeptList;

	//private Ware selectedPK;

	// private ArrayList<Ware> DAOwaren;

	// private static final ProduktKategorie produktKategorie = new
	// ProduktKategorie();

	public void initialize(URL arg0, ResourceBundle arg1) {
		s = (Service) this.getApplicationContext().getBean("GASTService");

		wareCol.setCellValueFactory(new PropertyValueFactory<Rezept,String>("wName"));
		mengeCol.setCellValueFactory(new PropertyValueFactory<Rezept,Integer>("menge"));
		
		
		
		
		
		pkList = FXCollections.observableArrayList();
		try {
			pkA = s.searchProduktKategorie(new ProduktKategorie());
			for (int i = 0; i < pkA.size(); i++) {
				pkList.add(pkA.get(i).getBezeichnung());
			}
		} catch (IllegalArgumentException e) {
			Dialogs.showErrorDialog(this.getStage(),
					"Kategorien konnten nicht geladen werden.", "Ladefehler",
					"Kategorien laden", e);
		} catch (DAOException e) {
			Dialogs.showErrorDialog(this.getStage(),
					"Kategorien konnten nicht geladen werden.", "Ladefehler",
					"Kategorien laden", e);
		}
		produktKategorieBox.setItems(pkList);
		produktKategorieBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
				pList = FXCollections.observableArrayList();
				try {
					Produkt sprodukt = new Produkt();
					sprodukt.setKategorie(pkA.get(produktKategorieBox.getSelectionModel().getSelectedIndex()).getId());
					pA = s.searchProdukt(sprodukt);
					for (int i = 0; i < pA.size(); i++) {
						pList.add(pA.get(i).getName());
						}
					} catch (IllegalArgumentException e) {
						Dialogs.showErrorDialog(null,
									"Produkte konnten nicht geladen werden.",
									"Ladefehler", "Produkte laden", e);
					} catch (DAOException e) {
						Dialogs.showErrorDialog(null,
									"Produkte konnten nicht geladen werden.",
									"Ladefehler", "Produkte laden", e);
					}
					produkteBox.setItems(pList);
					produkteBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
						public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
							listRezepte();
							updateWaren();
							}

						});
				}

			});
		updateWaren();
		listRezepte();
	}
	
	private void updateWaren(){
		wList = FXCollections.observableArrayList();
		try {
			wA = s.searchWare(new Ware());
			for (int i = 0; i < wA.size(); i++) {
				wList.add(wA.get(i).getBezeichnung());
			}
		} catch (IllegalArgumentException e) {
			Dialogs.showErrorDialog(this.getStage(),
					"Waren konnten nicht geladen werden.", "Ladefehler",
					"Waren laden", e);
		} catch (DAOException e) {
			Dialogs.showErrorDialog(this.getStage(),
					"Waren konnten nicht geladen werden.", "Ladefehler",
					"Waren laden", e);
		}
		wareBox.setItems(wList);
	}
	
	@FXML
	public void listRezepte(){
		//System.out.println("list Rezepte");
		if(pA != null && produkteBox.getSelectionModel().getSelectedIndex() != -1){
			rezeptList = FXCollections.observableArrayList();
			
			try{
				//System.out.println("Produkt: " + pA.get(produkteBox.getSelectionModel().getSelectedIndex()).getId());
				for(Rezept r:s.searchRezept(new Rezept(pA.get(produkteBox.getSelectionModel().getSelectedIndex()).getId(),null,-1,null))){
					rezeptList.add(r);
				}
			}catch(IllegalArgumentException e){
				Dialogs.showErrorDialog(this.getStage(), "Warenverbrauch konnte nicht geladen werden","Ladefehler","Verbrauch laden",e);
			} catch (DAOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Warenverbrauch konnte nicht geladen werden","Ladefehler","Verbrauch laden",e);
			}
			rezeptTable.setItems(rezeptList);
		}else{
			//System.out.println("p Array is null");
		}
	}
	
	
    @FXML
    public void clickAddWare(ActionEvent event) {
    	if(pA != null && wA != null && produkteBox.getSelectionModel().getSelectedIndex() != -1){
    		Integer p_tmp = pA.get(produkteBox.getSelectionModel().getSelectedIndex()).getId();
    		Integer w_tmp = wA.get(wareBox.getSelectionModel().getSelectedIndex()).getId();
    		int n = 0;
    		String message = "";
    		try {
    			n= Integer.parseInt(wMenge.getText());
    		}catch(NumberFormatException e) {
    			message+="Keine gueltige Anzahl ausgewaehlt!\n";
    		}
    		
    		if(n != 0){
    			try {
					s.createRezept(new Rezept(p_tmp,w_tmp,n,null));
				} catch (IllegalArgumentException e) {
					Dialogs.showErrorDialog(this.getStage(), "Verbrauch konnte nicht erstellt werden.", "Speicherfehler", "Verbrauch erstellen", e);
				} catch (DAOException e) {
					Dialogs.showErrorDialog(this.getStage(), "Verbrauch konnte nicht erstellt werden.", "Speicherfehler", "Verbrauch erstellen", e);
				}
    		}else{
    			Dialogs.showInformationDialog(this.getStage(), message , "Verbrauch hinzufuegen", "Information");
    		}
    		
	    	listRezepte();
    	}
    }
    
    @FXML
    public void clickDeleteAll(ActionEvent event) {
    	if(pA != null && produkteBox.getSelectionModel().getSelectedIndex() != -1){
	    	DialogResponse drp = Dialogs.showConfirmDialog(this.getStage(),
	    			"Wollen Sie die Liste wirklich löschen?","Verbrauch löschen", "Frage", DialogOptions.YES_NO);
	    	
	    	
	    	if(drp.equals(DialogResponse.YES)) {
	    		try {
	    			s.deleteRezept(new Rezept(pA.get(produkteBox.getSelectionModel().getSelectedIndex()).getId(),null,-1,null));
	    		} catch (IllegalArgumentException ex) {
	    			
	    		}catch (DAOException ex) {
	    			ex.printStackTrace(); 
	    		}
	    	}
	    	listRezepte();
    	}
    }

}
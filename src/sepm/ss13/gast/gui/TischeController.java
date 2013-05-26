package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;




import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Tisch;

import sepm.ss13.gast.service.Service;

public class TischeController extends Controller {
	private Service s;
	private ObservableList<Tisch> tische;
	private ArrayList<Tisch> tischListe;
	
	@FXML private TableView<Tisch> tischeTableView;
	@FXML private TableColumn<Tisch,Integer> nummerCol;
	@FXML private TableColumn<Tisch,Integer> plaetzeCol;
	@FXML private TableColumn<Tisch,String> beschreibungCol;
	@FXML private TableColumn<Tisch,String> artCol;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
		nummerCol.setCellValueFactory(new PropertyValueFactory<Tisch,Integer>("nummer"));
		plaetzeCol.setCellValueFactory(new PropertyValueFactory<Tisch,Integer>("plaetze"));
		beschreibungCol.setCellValueFactory(new PropertyValueFactory<Tisch,String>("beschreibung"));
		artCol.setCellValueFactory(new PropertyValueFactory<Tisch,String>("art"));
		listTische();
	}
	@FXML
	 public void listTische() {
		 tische = FXCollections.observableArrayList();
		 try {
				Tisch tisch = new Tisch();
				tischListe=s.searchTisch(tisch);
				tische.addAll(tischListe);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tischeTableView.setItems(tische);
	}
	@FXML
	 public void addTisch() {
		
		
		Stage stage = GUITools.openDialog("Tisch anlegen",this.getStage());
		Tisch t= null;
		TischDialogController tdc = (TischDialogController)GUITools.loadFXML("TischDialog.fxml", stage, this);
		tdc.setParentController(this);
		tdc.setTisch(t);
		stage.show();


	}
	
	@FXML
	 public void updateTisch(ActionEvent event) {
		
		Tisch t = tischeTableView.getSelectionModel().getSelectedItem();
		 
		if(t == null) {
			Dialogs.showInformationDialog(this.getStage(), "Keine Tisch ausgewählt!", "Tisch bearbeiten", "Information");
			return;
		}
		
		Stage stage = GUITools.openDialog("Tisch bearbeiten",this.getStage());
		TischDialogController tdc = (TischDialogController) GUITools.loadFXML("TischDialog.fxml", stage, this);
		tdc.setTisch(t);
		stage.show();
		
	 }
	
	@FXML
	 public void deleteTisch() {
		Tisch tisch = tischeTableView.getSelectionModel().getSelectedItem();
		if(tisch == null) {
			Dialogs.showInformationDialog(this.getStage(), "Keine Tisch ausgewählt!", "Tisch löschen", "Information");
			return;
		}
		try {
			s.deleteTisch(tisch);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listTische();
	}
	
	public Tisch getSelectedTisch() {
		Tisch t = tischeTableView.getSelectionModel().getSelectedItem();
		 	
		return t;
	}
}

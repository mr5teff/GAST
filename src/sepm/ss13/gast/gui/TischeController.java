package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
		nummerCol.setCellValueFactory(new PropertyValueFactory<Tisch,Integer>("nummer"));
		plaetzeCol.setCellValueFactory(new PropertyValueFactory<Tisch,Integer>("plaetze"));
		beschreibungCol.setCellValueFactory(new PropertyValueFactory<Tisch,String>("beschreibung"));
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
		
		/*Stage stage = GUITools.openDialog("Tisch anlegen",this.getStage());
		GUITools.loadFXML("NeuerTischDialog.fxml", stage, this);
		stage.show();*/
		Tisch tisch= new Tisch();
		
		try {
			s.createTisch(tisch);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listTische();
	}
	@FXML
	 public void deleteTisch() {
		Tisch tisch = tischeTableView.getSelectionModel().getSelectedItem();
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
}

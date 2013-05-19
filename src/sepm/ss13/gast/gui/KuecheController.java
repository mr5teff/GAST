package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.service.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class KuecheController extends Controller 
{
	private Service s;
	
	private ObservableList<Bestellung> bestellungen;
	
	private ArrayList<Bestellung> liste;
	
	@FXML private TableView<Bestellung> kuecheBestellungTableView;
	
	@FXML private TableColumn<Bestellung, Integer> idCol;
	@FXML private TableColumn<Bestellung, Integer> tischnummerCol;
	@FXML private TableColumn<Bestellung, Integer> produktIdCol;
	@FXML private TableColumn<Bestellung, String> produktNameCol;
	@FXML private TableColumn<Bestellung, Integer> preisCol;
	@FXML private TableColumn<Bestellung, Integer> rechnungIdCol;
	@FXML private TableColumn<Bestellung, String> statusCol;
	
	public void initialize(URL location, ResourceBundle resources) 
	{
		s = (Service) this.getApplicationContext().getBean("GASTService");
		 
		idCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("id"));
		tischnummerCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("tischnummer"));
		produktIdCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("produktid"));
		produktNameCol.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("pname"));
		preisCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("preis"));
		rechnungIdCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("rechnungid"));
		statusCol.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("status"));
		
		listBestellungen();
	}
	
	@FXML
	public void listBestellungen() 
	{
		bestellungen = FXCollections.observableArrayList();
		 
		try 
		{
			Bestellung bestellung = new Bestellung();
			 		
			bestellung.setStatus("bestellt");
			
			liste = s.searchBestellung(bestellung);
			 
			bestellungen.addAll(liste);
			//System.out.println(liste.get(3).getTisch());
		}
		catch(IllegalArgumentException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch(DAOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kuecheBestellungTableView.setItems(bestellungen);
	}
	 
	// Zum einfacheren Wechseln der Anzeige (aus Testgründen)
	@FXML
	public void clickOnManagement(ActionEvent event) {
		GUITools.loadFXML("Management.fxml",this.getStage());
	}
	 
	@FXML
	public void clickOnKassa(ActionEvent event) {
		GUITools.loadFXML("Kassa.fxml",this.getStage());
	}
}

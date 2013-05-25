package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.service.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Dialogs;
import javafx.scene.control.SelectionMode;
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
	@FXML private TableColumn<Bestellung, Date> bestelldatumCol;
	@FXML private TableColumn<Bestellung, Integer> bearbeitungszeitCol;
	
	public void initialize(URL location, ResourceBundle resources) 
	{
		s = (Service) this.getApplicationContext().getBean("GASTService");
		 
		kuecheBestellungTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		idCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("id"));
		tischnummerCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("tisch"));
		produktIdCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("produkt"));
		produktNameCol.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("pname"));
		preisCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("preis"));
		rechnungIdCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("rechnung"));
		statusCol.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("status"));
		bestelldatumCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Date>("bestelldatum"));
		bearbeitungszeitCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("bearbeitungszeit"));
		
		listBestellungen();
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				clickOnBearbeitungszeitAktualisieren();
			}
		}, 0, 10*1000);
		
	}
		
	@FXML
	public void listBestellungen() 
	{
		bestellungen = FXCollections.observableArrayList();
		 
		try 
		{
			Bestellung bestellungStatus = new Bestellung();
			 		
			bestellungStatus.setStatus("bestellt");
			
			liste = s.searchBestellung(bestellungStatus);
			
			bestellungStatus.setStatus("wirdGekocht");
			
			liste.addAll(s.searchBestellung(bestellungStatus));

			bestellungen.addAll(liste);		
		}
		catch(IllegalArgumentException e) 
		{
			// Dialog "Fehler" ausgeben.
			e.printStackTrace();
		} 
		catch(DAOException e) 
		{
			// Dialog "Fehler" ausgeben.
			e.printStackTrace();
		}
		kuecheBestellungTableView.setItems(bestellungen);
	}
	
	// todo: Die für die Speise laut Rezept notwendigen Waren aus dem Lager entfernen (Serviceschicht).
	@FXML
	public void clickOnChangeStatusToWirdGekocht(ActionEvent event) 
	{					
		if(kuecheBestellungTableView.getSelectionModel().getSelectedIndex() == -1)
			Dialogs.showInformationDialog(this.getStage(), "Keine Bestellung ausgewählt!", "Bestellung zubereiten", "Information");
		else
		{		
			Bestellung changeBestellungStatus = new Bestellung();
			
			changeBestellungStatus = (kuecheBestellungTableView.getSelectionModel().getSelectedItem());			
			
			try
			{
				changeBestellungStatus.setStatus("wirdGekocht");
				s.updateBestellung(changeBestellungStatus);
				listBestellungen(); 
			}
			catch(DAOException e) 
			{
				// Dialog "Fehler" ausgeben.
				e.printStackTrace();
			}
		}			
	}	
	
	@FXML
	public void clickOnChangeStatusToFertigGekocht(ActionEvent event) 
	{
		if(kuecheBestellungTableView.getSelectionModel().getSelectedIndex() == -1)
			Dialogs.showInformationDialog(this.getStage(), "Keine Bestellung ausgewählt!", "Bestellung kann abgeholt werden", "Information");
		else
		{		
			Bestellung changeBestellungStatus = new Bestellung();
			
			changeBestellungStatus = (kuecheBestellungTableView.getSelectionModel().getSelectedItem());			
			
			try
			{
				changeBestellungStatus.setStatus("fertigGekocht");	
				s.updateBestellung(changeBestellungStatus);
				s.aktualisiereBearbeitungszeit();
				listBestellungen(); 
			}
			catch(DAOException e) 
			{
				// Dialog "Fehler" ausgeben.
				e.printStackTrace();
			}
		}	
	}	
	
	@FXML
	public void clickOnBearbeitungszeitAktualisieren() 
	{
		this.getLogger().info("refreshing view...");
		
		try 
		{
			s.aktualisiereBearbeitungszeit();
		} 
		catch (IllegalArgumentException e) 
		{
			// Dialog "Fehler" ausgeben.
			e.printStackTrace();
		} 
		catch (DAOException e) 
		{
			// Dialog "Fehler" ausgeben.
			e.printStackTrace();
		}
		
		listBestellungen();
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
package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

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

public class KuecheController extends RefreshableController 
{
	private Service s;
	
	private ObservableList<Bestellung> bestellungen;
	
	private ArrayList<Bestellung> liste;
	
	@FXML private TableView<Bestellung> kuecheBestellungTableView;
	
	@FXML private TableColumn<Bestellung, Integer> tischnummerCol;
	@FXML private TableColumn<Bestellung, String> produktNameCol;
	@FXML private TableColumn<Bestellung, String> statusCol;
	@FXML private TableColumn<Bestellung, Date> bestelldatumCol;
	@FXML private TableColumn<Bestellung, Integer> bearbeitungszeitCol;
	
	public void initialize(URL location, ResourceBundle resources) 
	{
		s = (Service) this.getApplicationContext().getBean("GASTService");
		 
		kuecheBestellungTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tischnummerCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("tisch"));
		produktNameCol.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("pname"));
		statusCol.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("status"));
		bestelldatumCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Date>("bestelldatum"));
		bearbeitungszeitCol.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("bearbeitungszeit"));
		
		listBestellungen();
		this.startRefresh();
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
			Dialogs.showErrorDialog(this.getStage(), "Bestellungen konnten nicht geladen werden.", "Ladefehler", "Bestellungen laden", e);
		} 
		catch(DAOException e) 
		{
			Dialogs.showErrorDialog(this.getStage(), "Bestellungen konnten nicht geladen werden.", "Ladefehler", "Bestellungen laden", e);
		}
		kuecheBestellungTableView.setItems(bestellungen);
	}
	
	// todo: Die f�r die Speise laut Rezept notwendigen Waren aus dem Lager entfernen (Serviceschicht).
	@FXML
	public void clickOnChangeStatusToWirdGekocht(ActionEvent event) 
	{
		ObservableList<Bestellung> gewaehlteBestellungen = kuecheBestellungTableView.getSelectionModel().getSelectedItems();
		
		if(gewaehlteBestellungen.isEmpty())
			Dialogs.showInformationDialog(this.getStage(), "Keine Bestellung ausgew�hlt!", "Bestellung zubereiten", "Information");
		else
		{
			try {
				for(Bestellung b:gewaehlteBestellungen) {
					b.setStatus("wirdGekocht");
					s.updateBestellung(b);
				}
			}
			catch(DAOException e) 
			{
				Dialogs.showErrorDialog(this.getStage(), "Status konnte nicht ge�ndert werden.", "Speicherfehler", "Status �ndern", e);
			}
			
			listBestellungen();
		}			
	}	
	
	@FXML
	public void clickOnChangeStatusToFertigGekocht(ActionEvent event) 
	{
		ObservableList<Bestellung> gewaehlteBestellungen = kuecheBestellungTableView.getSelectionModel().getSelectedItems();
		
		if(gewaehlteBestellungen.isEmpty())
			Dialogs.showInformationDialog(this.getStage(), "Keine Bestellung ausgew�hlt!", "Bestellung als fertig markieren", "Information");
		else
		{
			try {
				for(Bestellung b:gewaehlteBestellungen) {
					b.setStatus("fertigGekocht");
					s.updateBestellung(b);
					s.aktualisiereBearbeitungszeit();
				}
			}
			catch(DAOException e) 
			{
				Dialogs.showErrorDialog(this.getStage(), "Status konnte nicht ge�ndert werden.", "Speicherfehler", "Status �ndern", e);
			}
			
			listBestellungen();
		}	
	}	
	
	@FXML
	public void clickOnBearbeitungszeitAktualisieren() 
	{
		try 
		{
			s.aktualisiereBearbeitungszeit();
		} 
		catch (IllegalArgumentException e) 
		{
			Dialogs.showErrorDialog(this.getStage(), "Bearbeitungszeit konnte nicht aktualisiert werden.", "Speicherfehler", "Bearbeitungszeit aktualisieren", e);
		} 
		catch (DAOException e) 
		{
			Dialogs.showErrorDialog(this.getStage(), "Bearbeitungszeit konnte nicht aktualisiert werden.", "Speicherfehler", "Bearbeitungszeit aktualisieren", e);
		}
		
		listBestellungen();
	}	
		 
	// Zum einfacheren Wechseln der Anzeige (aus Testgr�nden)
	@FXML
	public void clickOnManagement(ActionEvent event) {
		this.stopRefresh();
		GUITools.loadFXML("Management.fxml",this.getStage());
	}
	 
	@FXML
	public void clickOnKassa(ActionEvent event) {
		this.stopRefresh();
		GUITools.loadFXML("Kassa.fxml",this.getStage());
	}

	@Override
	protected void refresh() {
		clickOnBearbeitungszeitAktualisieren();
	}
}
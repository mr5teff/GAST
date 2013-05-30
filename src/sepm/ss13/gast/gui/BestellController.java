package sepm.ss13.gast.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.springframework.beans.BeansException;


import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.domain.Rechnung;
import sepm.ss13.gast.domain.Tisch;
import sepm.ss13.gast.service.PdfService;
import sepm.ss13.gast.service.Service;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class BestellController extends RefreshableController {
	private Service s;
	private ObservableList<Bestellung> bestellungen;
	private ObservableList<Integer> tischnummern;
	
	private ArrayList<ProduktKategorie> pk;
	private ArrayList<Produkt> p;
	private ArrayList<Tisch> tischList;
	
	//Für die button Klick abfolge
	private int buttonState = 0;
	private Tisch clickedTisch;
	private ProduktKategorie clickedPK;
	

	 
	@FXML private ComboBox<Integer> tisch; 
	@FXML private ComboBox<String> kategorie;
	@FXML private ComboBox<String> produkt;
	@FXML private TableView<Bestellung> bestellungTableView;
	@FXML private TableColumn<Bestellung,String> nameCol;
	@FXML private TableColumn<Bestellung,Integer> preisCol;
	@FXML private TableColumn<Bestellung,String> statusCol;
	@FXML private CheckBox alleBestellungen;
	@FXML private TextField anzahl;
	@FXML private ComboBox<Integer> zielTisch;
	@FXML private GridPane gridPane;
	@FXML private Label bestellLabel;
	

	 public void initialize(URL arg0, ResourceBundle arg1) {
			s = (Service) this.getApplicationContext().getBean("GASTService");
			
			bestellungTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			nameCol.setCellValueFactory(new PropertyValueFactory<Bestellung,String>("pname"));
			preisCol.setCellValueFactory(new PropertyValueFactory<Bestellung,Integer>("preis"));
			statusCol.setCellValueFactory(new PropertyValueFactory<Bestellung,String>("status"));
			
			tischnummern = FXCollections.observableArrayList();
			try {
				tischList = s.searchTisch(new Tisch());
				for(Tisch t : tischList) {
					tischnummern.add(t.getNummer());
				}
			} catch (DAOException e1) {
				Dialogs.showErrorDialog(this.getStage(), "Tische konnten nicht geladen werden.", "Ladefehler", "Tische laden", e1);
			}
			tisch.setItems(tischnummern);
			tisch.setValue(tischnummern.get(0));
			listBestellungen();
			
			tisch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
				public void changed(ObservableValue<? extends Integer> arg0,Integer arg1, Integer arg2) {
					listBestellungen();
				}
			});
			
			zielTisch.setItems(tischnummern);

			
			try {
				pk = s.searchProduktKategorie(new ProduktKategorie());
			} catch (IllegalArgumentException e) {
				Dialogs.showErrorDialog(this.getStage(), "Kategorien konnten nicht geladen werden.", "Ladefehler", "Kategorien laden", e);
			} catch (DAOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Kategorien konnten nicht geladen werden.", "Ladefehler", "Kategorien laden", e);
			}
						
			addTischButtons(tischList);
			
			this.startRefresh();
		 }
	 
		 @FXML
		 public void listBestellungen() {
			 bestellungen = FXCollections.observableArrayList();
			 try {
					Bestellung bestellung = new Bestellung();
					if(alleBestellungen.isSelected()==false) {
						bestellung.setTisch(tisch.getValue());
					}
					
					for(Bestellung b:s.searchBestellung(bestellung)) {
						if(b.getRechnung()==null) bestellungen.add(b);
					}
				} catch (IllegalArgumentException e) {
					Dialogs.showErrorDialog(this.getStage(), "Bestellungen konnten nicht geladen werden.", "Ladefehler", "Bestellungen laden", e);
				} catch (DAOException e) {
					Dialogs.showErrorDialog(this.getStage(), "Bestellungen konnten nicht geladen werden.", "Ladefehler", "Bestellungen laden", e);
				}
				bestellungTableView.setItems(bestellungen);
		 }
		 
		 @FXML
		 public void clickOnTisch(ActionEvent event) {
			 System.out.println("GEEEHT");
		 }
		 
		 @FXML
		 protected void storniereBestellung(ActionEvent event) {
			 int gewaehlt=bestellungTableView.getSelectionModel().getSelectedItems().size();
			 if(gewaehlt==0)
			 {
				 Dialogs.showInformationDialog(this.getStage(), "Keine Bestellung ausgewählt!", "Bestellung stornieren", "Information");
				 return;
			 }
			 try {
				 ObservableList<Bestellung> gewaehlteBestellungen=bestellungTableView.getSelectionModel().getSelectedItems();
				 for(int i=0;i<gewaehlt;i++) {
					Bestellung b= new Bestellung();
				 	b.setId(gewaehlteBestellungen.get(i).getId());
				 	s.deleteBestellung(b);
				 }
				 
			} catch (IllegalArgumentException e) {
				Dialogs.showErrorDialog(this.getStage(), "Bestellungen konnten nicht storniert werden.", "Speicherfehler", "Bestellungen stornieren", e);
			} catch (DAOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Bestellungen konnten nicht storniert werden.", "Speicherfehler", "Bestellungen stornieren", e);
			}
			 
			bestellungTableView.getSelectionModel().clearSelection();
			listBestellungen();
		}
		
			 
		 @FXML
		 public void moveToTable() {
			 if(bestellungTableView.getSelectionModel().getSelectedItems().isEmpty()) {
				 Dialogs.showInformationDialog(this.getStage(), "Keine Bestellung ausgewählt!", "Bestellung verschieben", "Information");
				 return;
			 }
			 
			 try {
				 ObservableList<Bestellung> gewaehlteBestellungen=bestellungTableView.getSelectionModel().getSelectedItems();
				 for(Bestellung b:gewaehlteBestellungen) {
					b.setTisch(zielTisch.getValue());
					s.updateBestellung(b);
				 }
			} catch (IllegalArgumentException e) {
				Dialogs.showInformationDialog(this.getStage(), "Kein Tisch ausgewählt!", "Bestellung verschieben", "Information");
				return;
			} catch (DAOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Bestellung konnte nicht verschocben werden.", "Speicherfehler", "Bestellung verschieben", e);
			}
			 
			bestellungTableView.getSelectionModel().clearSelection();
			listBestellungen();
		 }
		 
		 @FXML
		 public void clickOnDruckeRechnung(ActionEvent event) {
			 ObservableList<Bestellung> gewaehlteBestellungen=bestellungTableView.getSelectionModel().getSelectedItems();
			 
			 if(gewaehlteBestellungen.isEmpty()) {
				 Dialogs.showInformationDialog(this.getStage(), "Keine Bestellung ausgewählt!", "Rechnung erstellen", "Information");
				 return;
			 }
			 
			Rechnung r = null;
			 
			try {
				r=s.createRechung(new ArrayList<Bestellung>(gewaehlteBestellungen),0); //TODO 0 steht fuer Trinkgeld
			} catch (IllegalArgumentException e) {
				Dialogs.showErrorDialog(this.getStage(), "Rechnung konnte nicht erstellt werden.", "Speicherfehler", "Rechnung erstellen", e);
			} catch (DAOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Rechnung konnte nicht erstellt werden.", "Speicherfehler", "Rechnung erstellen", e);
			}
			
			bestellungTableView.getSelectionModel().clearSelection();
			listBestellungen();
			 
			try {
				Desktop.getDesktop().open(((PdfService)this.getApplicationContext().getBean("PdfService")).getFile(r));
			} catch (IOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Fehler beim öffnen der Rechnung.", "Dateizugriffsfehler", "Rechnung öffnen", e);
			} catch (BeansException e) {
				Dialogs.showErrorDialog(this.getStage(), "Fehler beim öffnen der Rechnung.", "Beans Exception", "Rechnung öffnen", e);
			} catch (DAOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Fehler beim öffnen der Rechnung.", "Dateizugriffsfehler", "Rechnung öffnen", e);
			}
		 }

		@Override
		protected void refresh() {
			listBestellungen();
		}
		
		
		@FXML
		public void back(ActionEvent event) {
			
			if(buttonState == 1)
			{
				addTischButtons(tischList);
				bestellLabel.setText("Tisch auswählen");
			}
			else if(buttonState == 2)
			{
				addProduktKategorieButtons(this.pk);
				bestellLabel.setText("Tischnummer " + clickedTisch.getNummer());
			}
		
		}
		
		private void addTischButtons(ArrayList<Tisch> tischList)
		{
			gridPane.getChildren().clear();
			buttonState = 0;
			
			int size = tischList.size();
			int index = 0;
			for(int x=0; x<gridPane.getRowConstraints().size() && index < size; x++)
			{
				for(int y=0; y < gridPane.getColumnConstraints().size() && index < size; y++)
				{
					final Button b = new Button(tischList.get(index).getNummer().toString());
					b.setPrefSize(100, 100);
					b.setWrapText(true);
					b.setUserData(tischList.get(index));
					gridPane.add(b, y, x);
					b.setOnAction(new EventHandler<ActionEvent>() {

						public void handle(ActionEvent e) {
							tischclicked(b);				
						}
						
					});
					
					index++;
				}
			}
	
		}
		
		private void addProduktKategorieButtons(ArrayList<ProduktKategorie> pkList)
		{
			gridPane.getChildren().clear();
			buttonState = 1;
			
			int size = pkList.size();
			int index = 0;
			for(int x=0; x<gridPane.getRowConstraints().size() && index < size; x++)
			{
				for(int y=0; y < gridPane.getColumnConstraints().size() && index < size; y++)
				{
					final Button b = new Button(pkList.get(index).getBezeichnung());
					b.setUserData(pkList.get(index));
					b.setPrefSize(100, 100);
					b.setWrapText(true);
					gridPane.add(b, y, x);
					b.setOnAction(new EventHandler<ActionEvent>() {

						public void handle(ActionEvent e) {
							katgorieclicked(b);				
						}
						
					});
					
					index++;
				}
			}
	
		}
		
		private void addProduktButtons(ArrayList<Produkt> pList)
		{
			gridPane.getChildren().clear();
			buttonState = 2;
			
			int size = pList.size();
			int index = 0;
			for(int x=0; x<gridPane.getRowConstraints().size() && index < size; x++)
			{
				for(int y=0; y < gridPane.getColumnConstraints().size() && index < size; y++)
				{
					final Button b = new Button(pList.get(index).getName());
					b.setUserData(pList.get(index));
					b.setPrefSize(100, 100);
					b.setWrapText(true);
					gridPane.add(b, y, x);
					b.setOnAction(new EventHandler<ActionEvent>() {

						public void handle(ActionEvent e) {
							produktclicked(b);				
						}
						
					});
					
					index++;
				}
			}
	
		}
		
		private void tischclicked(Button b)
		{
					
			Tisch t = (Tisch)b.getUserData();
			clickedTisch = t;
			
			tisch.setValue(t.getNummer());
			
			bestellLabel.setText("Tischnummer " + clickedTisch.getNummer());
			addProduktKategorieButtons(this.pk);
		}
		
		private void katgorieclicked(Button b)
		{
			buttonState = 2;
			
			ProduktKategorie pk = (ProduktKategorie)b.getUserData();
			clickedPK = pk;
			
			Produkt sProdukt = new Produkt();
			sProdukt.setKategorie(pk.getId());
			ArrayList<Produkt> pList = new ArrayList<Produkt>();
			
			try {
				pList = s.searchProdukt(sProdukt);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			bestellLabel.setText("Tischnummer " + clickedTisch.getNummer() + " >> " + pk.getBezeichnung());
			addProduktButtons(pList);
		}
		
		private void produktclicked(Button b)
		{
			 Produkt p = (Produkt) b.getUserData();
			
			 Bestellung bestellung=new Bestellung();
			 bestellung.setTisch(clickedTisch.getNummer());
			 bestellung.setProdukt(p.getId());
			 bestellung.setPname(p.getName());
			 bestellung.setPreis(p.getPreis());
			 bestellung.setSteuer(clickedPK.getSteuer());
			 bestellung.setStatus("bestellt");
			 bestellung.setDeleted(false);
			 try {
				 s.createBestellung(bestellung);
			 } catch (IllegalArgumentException e) {
				 Dialogs.showErrorDialog(this.getStage(), "Bestellung konnte nicht erstellt werden.", "Speicherfehler", "Bestellung erstellen", e);
			 } catch (DAOException e) {
				 Dialogs.showErrorDialog(this.getStage(), "Bestellung konnte nicht erstellt werden.", "Speicherfehler", "Bestellung erstellen", e);
			 }
			 refresh();
		}
}

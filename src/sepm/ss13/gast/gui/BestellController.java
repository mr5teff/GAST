package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import name.antonsmirnov.javafx.dialog.Dialog;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.domain.Konfiguration;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.service.Service;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BestellController extends Controller {
	private Service s;
	private ObservableList<Bestellung> bestellungen;
	private ObservableList<Integer> tischnummern;
	private ObservableList<String> kategorien;
	private ObservableList<String> produkte;
	private ArrayList<Bestellung> bestellungenListe;
	private ArrayList<ProduktKategorie> pk;
	private ArrayList<Produkt> p;

	 
	@FXML private ComboBox<Integer> tisch; 
	@FXML private ComboBox<String> kategorie;
	@FXML private ComboBox<String> produkt;
	@FXML private TableView<Bestellung> bestellungTableView;
	@FXML private TableColumn<Bestellung,String> nameCol;
	@FXML private TableColumn<Bestellung,Integer> preisCol;
	@FXML private TableColumn<Bestellung,String> statusCol;
	@FXML private CheckBox alleBestellungen;
	@FXML private TextField anzahl;

	 public void initialize(URL arg0, ResourceBundle arg1) {
			s = (Service) this.getApplicationContext().getBean("GASTService");
			
			bestellungTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			nameCol.setCellValueFactory(new PropertyValueFactory<Bestellung,String>("pname"));
			preisCol.setCellValueFactory(new PropertyValueFactory<Bestellung,Integer>("preis"));
			statusCol.setCellValueFactory(new PropertyValueFactory<Bestellung,String>("status"));
			tischnummern = FXCollections.observableArrayList();
			try {
				Konfiguration k=s.loadKonfiguration();
				for(int i=1;i<=k.getTischanzahl();i++) {
					tischnummern.add(i);
				}
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tisch.setItems(tischnummern);
			tisch.setValue(1);
			listBestellungen();
			tisch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
				public void changed(ObservableValue<? extends Integer> arg0,Integer arg1, Integer arg2) {
					listBestellungen();
				}
			});

			kategorien = FXCollections.observableArrayList();
			try {
				pk = s.searchProduktKategorie(new ProduktKategorie());
				for(int i=0;i<pk.size();i++) {
					kategorien.add(pk.get(i).getBezeichnung());
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			kategorie.setItems(kategorien);
			kategorie.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					produkte = FXCollections.observableArrayList();
					try {
						Produkt sprodukt=new Produkt();
						sprodukt.setKategorie(pk.get(kategorie.getSelectionModel().getSelectedIndex()).getId());
						p = s.searchProdukt(sprodukt);
						for(int i=0;i<p.size();i++) {
							produkte.add(p.get(i).getName());
						}
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					produkt.setItems(produkte);
				}
				
			});
		 }
		 @FXML
		 public void listBestellungen() {
			 bestellungen = FXCollections.observableArrayList();
			 try {
					Bestellung bestellung=new Bestellung();
					if(alleBestellungen.isSelected()==false) {
						bestellung.setTisch(tisch.getValue());
					}
					bestellungenListe=s.searchBestellung(bestellung);
					bestellungen.addAll(bestellungenListe);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
				 Dialog.showInfo("Bestellung stornieren", "Keine Bestellung ausgewählt!", null); //this.getStage().getScene().getWindow());
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listBestellungen();
		}
		
		 @FXML
		 public void clickOnAddBestellung(ActionEvent event) {
			 int n=0;
			 String message="";
			 try {
				 n= Integer.parseInt(anzahl.getText());
			 }
			 catch(NumberFormatException e) {
				 message+="Keine gültige Anzahl ausgewählt!\n";
				 }
			 if((n!=0)&&(produkt.getSelectionModel().isEmpty()==false)) {
				 for(int i=0; i<n;i++) {
					 Bestellung bestellung=new Bestellung();
					 bestellung.setTisch(tisch.getValue());
					 bestellung.setProdukt(p.get(produkt.getSelectionModel().getSelectedIndex()).getId());
					 bestellung.setPname(p.get(produkt.getSelectionModel().getSelectedIndex()).getName());
					 bestellung.setPreis(p.get(produkt.getSelectionModel().getSelectedIndex()).getPreis());
					 bestellung.setStatus("bestellt");
					 bestellung.setDeleted(false);
					 try {
						 s.createBestellung(bestellung);
					 } catch (IllegalArgumentException e) {
						 // TODO Auto-generated catch block
						 e.printStackTrace();
					 } catch (DAOException e) {
						 // TODO Auto-generated catch block
						 e.printStackTrace();
					 }
				 }
				 listBestellungen();
				 anzahl.setText("");
			 }
			 else{
				 if(produkt.getSelectionModel().isEmpty()) {
					 message+="Kein Produkt ausgewählt!";
				 }
				 Dialog.showInfo("Bestellung hinzufügen", message, null); //this.getStage().getScene().getWindow());
			 }
			 
		 }

}

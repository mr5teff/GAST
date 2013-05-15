package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.domain.Konfiguration;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.service.Service;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BestellController extends Controller {
	
	private ApplicationContext ac;
	private static Logger log = Logger.getLogger(Application.class);
	
	private Service s;
	private ObservableList<Bestellung> bestellungen;
	private ObservableList<Integer> tischnummern;
	private ObservableList<String> kategorien;
	private ObservableList<String> produkte;
	private ArrayList<Bestellung> liste;
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
			// TODO Auto-generated method stub
			
			ac = new ClassPathXmlApplicationContext("spring-config.xml");

			s = (Service) ac.getBean("GASTService");
			
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
					liste=s.searchBestellung(bestellung);
					bestellungen.addAll(liste);
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
			 try {
				 Bestellung b= new Bestellung();
				 b.setId(liste.get(tisch.getSelectionModel().getSelectedIndex()-1).getId());
				 System.out.println(b.getId());
				 s.deleteBestellung(b);
				 
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
			 for(int i=0; i<Integer.parseInt(anzahl.getText());i++) {
				 Bestellung bestellung=new Bestellung();
				 bestellung.setTisch(tisch.getValue());
				 bestellung.setProdukt(p.get(produkt.getSelectionModel().getSelectedIndex()).getId());
				 bestellung.setStatus("bestellt");
				 bestellung.setPreis(p.get(produkt.getSelectionModel().getSelectedIndex()).getPreis());
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

}

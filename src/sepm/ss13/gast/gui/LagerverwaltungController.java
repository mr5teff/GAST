package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialogs;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.Ware;
import sepm.ss13.gast.service.Service;

public class LagerverwaltungController extends Controller {
	
	private Service s;
	
	@FXML private TableView<Ware> warenTableView;
	@FXML private TableView<Ware> einkaufTableView;
	
	@FXML private TableColumn<Ware, Integer> idCol;
	@FXML private TableColumn<Ware, String> bezeichnungCol;
	@FXML private TableColumn<Ware, Integer> lagerstandCol;
	@FXML private TableColumn<Ware, String> einheitCol;
	
	@FXML private TableColumn<Ware, String> bezeichnungCol2;
	@FXML private TableColumn<Ware, Integer> mengeCol;
	
	@FXML private TextField mengeTextField;
	
	@FXML private ComboBox<Ware> warenComboBox; 
	
	private ObservableList<Ware> waren;
	
	private ObservableList<Ware> einkaufwaren;
	
	private ArrayList<Ware> warenListe;
	

	public void initialize(URL arg0, ResourceBundle arg1) {
		
		s = (Service) this.getApplicationContext().getBean("GASTService");
		
		idCol.setCellValueFactory(new PropertyValueFactory<Ware, Integer>("id"));
		bezeichnungCol.setCellValueFactory(new PropertyValueFactory<Ware, String>("bezeichnung"));
		lagerstandCol.setCellValueFactory(new PropertyValueFactory<Ware, Integer>("lagerstand"));
		einheitCol.setCellValueFactory(new PropertyValueFactory<Ware, String>("einheit"));
		
		bezeichnungCol2.setCellValueFactory(new PropertyValueFactory<Ware, String>("bezeichnung"));
		mengeCol.setCellValueFactory(new PropertyValueFactory<Ware, Integer>("lagerstand"));
		
		warenComboBox.setConverter(new StringConverter<Ware>(){
	        @Override public String toString(Ware item) {
	            if (item != null)
	            	return item.getBezeichnung();
	            else
	            	return "NULL";
	        }

			@Override
			public Ware fromString(String arg0) {
				throw new RuntimeException("not required for non editable ComboBox");
			}
	    });
		

		warenComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ware>() {

			public void changed(ObservableValue<? extends Ware> observable, Ware oldValue, Ware newValue) {
				
			
			}
			
		});
		
		waren = FXCollections.observableArrayList();		
		einkaufwaren = FXCollections.observableArrayList();
		
		refreshWareContent();
	}
	
	public void refreshWareContent()
	{
			Ware searchWare = new Ware();
			try {
				warenListe = s.searchWare(searchWare);		
			} catch (IllegalArgumentException e) {
				Dialogs.showErrorDialog(this.getStage(), "Warenliste konnte nicht geladen werden.", "Ladefehler", "Error", e);
			} catch (DAOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Warenliste konnte nicht geladen werden.", "Ladefehler", "Error", e);
			}
			waren.clear();
			waren.addAll(warenListe);
			warenTableView.setItems(waren);
			warenComboBox.setItems(waren);
	}
	
	public void refreshEinkaufListe()
	{
		einkaufTableView.setItems(einkaufwaren);
	}
	
	
	@FXML
	public void clickOnListeSpeichern() 
	{
		for(Ware w: einkaufwaren)
		{
			Ware neu = null;
			try {
				neu = s.searchWare(w).get(0);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			neu.setLagerstand(neu.getLagerstand() + w.getLagerstand());
			
			try {
				s.updateWare(neu);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		clickOnListeLeeren();
		refreshWareContent();
	}
	
	@FXML
	public void clickOnListeLeeren() 
	{
		einkaufwaren.clear();
		refreshEinkaufListe();
	}
	
	@FXML
	public void clickOnWarehinzufuegen() 
	{
		Ware w = warenComboBox.getValue();
		if(w == null)
		{
			Dialogs.showInformationDialog(this.getStage(), "Bitte wählen Sie eine Ware aus.", "Ware hinzufügen", "Information");
			return;
		}
		
		int menge = 0;
		try
		{
			menge = Integer.parseInt(mengeTextField.getText());
			if(menge<0)
				throw new NumberFormatException();
		}
		catch(NumberFormatException e)
		{
			Dialogs.showInformationDialog(this.getStage(), "Geben Sie einen positiven ganzzahligen Wert für die Menge an!", "Ware hinzufügen", "Information");
			return;
		}
		
		w.setLagerstand(menge);
		
		einkaufwaren.add(w);
		refreshEinkaufListe();
	}

}

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
	
	@FXML private ComboBox<Ware> warenComboBox; 
	
	private ObservableList<Ware> waren;
	
	private ArrayList<Ware> warenListe;
	

	public void initialize(URL arg0, ResourceBundle arg1) {
		
		s = (Service) this.getApplicationContext().getBean("GASTService");
		
		idCol.setCellValueFactory(new PropertyValueFactory<Ware, Integer>("id"));
		bezeichnungCol.setCellValueFactory(new PropertyValueFactory<Ware, String>("bezeichnung"));
		lagerstandCol.setCellValueFactory(new PropertyValueFactory<Ware, Integer>("lagerstand"));
		einheitCol.setCellValueFactory(new PropertyValueFactory<Ware, String>("einheit"));
		
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
		/*
		warenComboBox.setCellFactory( new Callback<ListView<Ware>, ListCell<Ware>>() {					 
            public ListCell<Ware> call(ListView<Ware> param) {       	
            	final ListCell<Ware> cell = new ListCell<Ware>() { 

                    @Override public void updateItem(Ware item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                               setText(item.getBezeichnung());
                            }

                        }
            };
            return cell;
        }
    });*/

		warenComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ware>() {

			public void changed(ObservableValue<? extends Ware> observable, Ware oldValue, Ware newValue) {
				
			
			}
			
		});
		
		waren = FXCollections.observableArrayList();
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
	
	
	@FXML
	public void clickOnListeSpeichern() 
	{
		
	}
	
	@FXML
	public void clickOnListeLeeren() 
	{
		
	}
	
	@FXML
	public void clickOnWarehinzufuegen() 
	{
		
	}

}

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

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Ware;
import sepm.ss13.gast.service.Service;

public class LagerverwaltungController extends Controller {
	
	private Service s;
	
	@FXML private TableView<Ware> warenTableView;
	
	@FXML private TableColumn<Ware, Integer> idCol;
	@FXML private TableColumn<Ware, String> bezeichnungCol;
	@FXML private TableColumn<Ware, Integer> lagerstandCol;
	@FXML private TableColumn<Ware, String> einheitCol;
	
	private ObservableList<Ware> waren;
	
	private ArrayList<Ware> warenListe;
	

	public void initialize(URL arg0, ResourceBundle arg1) {
		
		s = (Service) this.getApplicationContext().getBean("GASTService");
		
		idCol.setCellValueFactory(new PropertyValueFactory<Ware, Integer>("id"));
		bezeichnungCol.setCellValueFactory(new PropertyValueFactory<Ware, String>("bezeichnung"));
		lagerstandCol.setCellValueFactory(new PropertyValueFactory<Ware, Integer>("lagerstand"));
		einheitCol.setCellValueFactory(new PropertyValueFactory<Ware, String>("einheit"));
		
		waren = FXCollections.observableArrayList();
		listLager();
		
	}
	
	public void listLager()
	{
			Ware searchWare = new Ware();
			try {
				warenListe = s.searchWare(searchWare);		
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Waren: " + warenListe.size());
			waren.clear();
			waren.addAll(warenListe);
			
			System.out.println(waren.size());
			warenTableView.setItems(waren);
	}

}

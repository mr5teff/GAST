package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Konsumstatistik;
import sepm.ss13.gast.service.Service;

public class KonsumstatistikController extends Controller
{
	private Service s;
	
	private ArrayList<Konsumstatistik> liste;
	
	private ObservableList<Konsumstatistik> popProducts;
	
	@FXML private TableView<Konsumstatistik> popularProductsTableView;
	
	@FXML private TableColumn<Konsumstatistik, String> produktnameCol;
	@FXML private TableColumn<Konsumstatistik, Integer> absatzmengeCol;
	
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		s = (Service) this.getApplicationContext().getBean("GASTService");
		
		produktnameCol.setCellValueFactory(new PropertyValueFactory<Konsumstatistik, String>("produktname"));
		absatzmengeCol.setCellValueFactory(new PropertyValueFactory<Konsumstatistik, Integer>("absatzmenge"));
		
		listPopularProducts();
	}

	@FXML
	public void listPopularProducts() 
	{
		popProducts = FXCollections.observableArrayList();
		 
		try 
		{
			liste = s.showPopularProducts();	
			popProducts.addAll(liste);
		}
		catch(IllegalArgumentException|DAOException e) 
		{
			Dialogs.showErrorDialog(this.getStage(), "Produkte konnten nicht gefunden werden.", "Ladefehler", "Produkte finden", e);
		} 

		popularProductsTableView.setItems(popProducts);
	}
}

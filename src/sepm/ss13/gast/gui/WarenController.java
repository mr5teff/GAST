package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


import name.antonsmirnov.javafx.dialog.Dialog;


import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Ware;
import sepm.ss13.gast.service.Service;

public class WarenController extends Controller {
	private Service s;
	 @FXML private TextField name;
	 @FXML private ComboBox<String> einheit; 
     private ObservableList<String> einheiten;

	public void initialize(URL arg0, ResourceBundle arg1) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
		
		einheiten = FXCollections.observableArrayList();
		einheiten.add("Gramm");
		einheiten.add("Millilieter");
		einheiten.add("Prozent");
		einheit.setItems(einheiten);
		einheit.setValue("Gramm");
			
	}
	 @FXML
	 public void saveWare(ActionEvent e) {

		if(name.getText().isEmpty()){
			Dialog.showInfo("Ware speichern", "Es wurden nicht alle Felder befüllt!", null); //this.getStage().getScene().getWindow());
			return;
		}
		String n_save = name.getText();
		String e_save = einheit.getValue().toLowerCase();

		Ware w_saved = null;
		try {
			Ware w = new Ware(-1,n_save,e_save,0);
			w_saved = s.createWare(w);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(w_saved.getId() != -1){
			
		}else{
			
		}
		 
	 }
}

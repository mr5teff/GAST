package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


import name.antonsmirnov.javafx.dialog.Dialog;


import sepm.ss13.gast.service.Service;

public class WarenController extends Controller {
	private Service s;
	 @FXML private TextField name;

	public void initialize(URL arg0, ResourceBundle arg1) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
			
	}
	 @FXML
	 public void saveWare(ActionEvent e) {

		if(name.getText().isEmpty()){
			Dialog.showInfo("Ware speichern", "Es wurden nicht alle Felder befüllt!", null); //this.getStage().getScene().getWindow());
			return;
		}

		 /*
		try {
			s.saveKonfiguration(k);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		 
	 }
}

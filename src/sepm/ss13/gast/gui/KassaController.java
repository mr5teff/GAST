package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class KassaController extends Controller {
	@FXML private BestellController bestellContentController;
	
	public void initialize(URL location, ResourceBundle resources) {
	}
	 
	 @FXML
	 public void clickOnManagement(ActionEvent event) {
		 bestellContentController.stopRefresh();
		 GUITools.loadFXML("Management.fxml",this.getStage());
	 }
	 
	 @FXML
	 public void clickOnKueche(ActionEvent event) {
		 bestellContentController.stopRefresh();
		 GUITools.loadFXML("Kueche.fxml",this.getStage());
	 }

	

}

package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WelcomeScreenController extends Controller {
	 public void initialize(URL arg0, ResourceBundle arg1) {
	 }
	 
	 @FXML
	 public void clickOnKassa(ActionEvent event) {
		 GUITools.loadFXML("Kassa.fxml",this.getStage());
	 }

	 @FXML
	 public void clickOnKueche(ActionEvent event) {
		 GUITools.loadFXML("Kueche.fxml",this.getStage());
	 }
	 
	 @FXML
	 public void clickOnManagement(ActionEvent event) {
		 GUITools.loadFXML("Management.fxml",this.getStage());
	 }
}

package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ManagementController extends Controller {
	 @FXML private SpeisekarteController speisekarteContentController;
	 	 
	 public void initialize(URL location, ResourceBundle resources) {
		 speisekarteContentController.setParentController(this);
	 }
	 
	 @FXML
	 public void clickOnKassa(ActionEvent event) {
		 GUITools.loadFXML("Kassa.fxml",this.getStage());
	 }
	 
	 @FXML
	 public void clickOnKueche(ActionEvent event) {
		 GUITools.loadFXML("Kueche.fxml",this.getStage());
	 }
}

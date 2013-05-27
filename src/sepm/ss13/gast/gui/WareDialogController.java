package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Ware;
import sepm.ss13.gast.service.Service;

public class WareDialogController extends Controller{

	private Service s;
	
	private Ware w;
	
	@FXML private TextField name;
	 @FXML private ComboBox<String> einheit; 
	 
    private ObservableList<String> einheiten;
	
	 
	public void initialize(URL location, ResourceBundle resources) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
   	 	
		einheiten = FXCollections.observableArrayList();
		einheiten.add("Gramm");
		einheiten.add("Milliliter");
		einheiten.add("Prozent");
		einheit.setItems(einheiten);
		einheit.setValue("Gramm");
		
   	 	this.w=null;
	}
    
    public void setW(Ware w) {
    	this.w=w;
    	
    	if(w!=null) {
    		name.setText(this.w.getBezeichnung());
            einheit.setValue(String.valueOf(this.w.getEinheit()));
    	}
    }
	
	 @FXML
	 public void clickOnSave(ActionEvent event) {
		if(w==null) w=new Ware();
		 
		try {
			 w.setBezeichnung(name.getText());
			 w.setEinheit(einheit.getValue());
			 
			 if(w.getId()==null) s.createWare(w);
			 else s.updateWare(w);
		} 
		catch (IllegalArgumentException e) {
				Dialogs.showInformationDialog(this.getStage(), "Bitte geben Sie gültige Paramter an.\n" +
										 "Bezeichnung muss jeweils mindestens ein Zeichen beinhalten, Einheit muss 'Gramm' , 'Milliliter' , 'Prozent' sein",
										 "Ungültige Eingabe", "Ware anlegen/bearbeiten");
				return;
		} 
		catch (DAOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Ware konnte nicht angelegt/bearbeitet werden.", "Speicherfehler", "Ware anlegen/bearbeiten", e);
		}
		 
		 this.getStage().hide();
		 WarenController wc = (WarenController) this.getParentController();
		 wc.refreshWareListView();
	 }
	 
	 @FXML
	 public void clickOnAbort(ActionEvent event) {
		 this.getStage().hide();
	 }
	

}

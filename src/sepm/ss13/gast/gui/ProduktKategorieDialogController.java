package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.service.Service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;

public class ProduktKategorieDialogController extends Controller {
	
	 private ProduktKategorie pk;
	
	@FXML private TextField kurzbezeichnung;
	@FXML private TextField bezeichnung;
	@FXML private TextField steuer;
	 
	private Service s;
	 
	public void initialize(URL location, ResourceBundle resources) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
   	 	
   	 	this.pk=null;
	}
    
    public void setPK(ProduktKategorie pk) {
    	this.pk=pk;
    	
    	if(pk!=null) {
    		bezeichnung.setText(this.pk.getBezeichnung());
            kurzbezeichnung.setText(this.pk.getKurzbezeichnung());
            steuer.setText(String.valueOf(this.pk.getSteuer()));
    	}
    }
	
	 @FXML
	 public void clickOnSave(ActionEvent event) {
		if(pk==null) pk=new ProduktKategorie();
		 
		try {
			 pk.setBezeichnung(bezeichnung.getText());
			 pk.setKurzbezeichnung(kurzbezeichnung.getText());
			 pk.setSteuer(Integer.parseInt(steuer.getText()));
			 
			 if(pk.getId()==null) s.createProduktKategorie(pk);
			 else s.updateProduktKategorie(pk);
		} 
		catch (IllegalArgumentException e) {
				Dialogs.showInformationDialog(this.getStage(), "Bitte geben Sie gültige Paramter an.\n" +
										 "Bezeichnung und Kurzbezeichnung müssen jeweils mindestens ein Zeichen beinhalten, Steuer muss eine positive Ganzzahl sein!",
										 "Ungültige Eingabe", "Produktkategorie anlegen/bearbeiten");
				pk = null;
				return;
		} 
		catch (DAOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Produktkategorie konnte nicht angelegt/bearbeitet werden.", "Speicherfehler", "Produktkategorie anlegen/bearbeiten", e);
		}
		 
		 this.getStage().hide();
		 SpeisekarteController skc = (SpeisekarteController) this.getParentController();
		 skc.refreshKategorieListView();
	 }
	 
	 @FXML
	 public void clickOnAbort(ActionEvent event) {
		 this.getStage().hide();
	 }
}

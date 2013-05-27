package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Tisch;
import sepm.ss13.gast.service.Service;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class TischDialogController extends Controller {
	
	private Tisch t;
	private Boolean neu;
	@FXML private TextField beschreibung;
	@FXML private TextField plaetzeTF;
	@FXML private TextField nummerTF;
	
	@FXML private ToggleGroup  raucherToggleGroup;	
	@FXML private RadioButton  raucherRadio;
	@FXML private RadioButton  nichtRaucherRadio;
	
	private Service s;
	 
	public void initialize(URL location, ResourceBundle resources) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
		
		raucherRadio.setUserData("Raucher");
		nichtRaucherRadio.setUserData("Nichtraucher");
		
		raucherRadio.setSelected(true);
		neu=false;
	}
	
	 @FXML
	 public void clickOnSave(ActionEvent event) {

		if(t == null) {
			neu=true;
			t = new Tisch();
		}
		int plaetze=0;
		int nummer=0;
		Boolean valid=true;
		try {
			plaetze=Integer.parseInt(plaetzeTF.getText());
			nummer=Integer.parseInt(nummerTF.getText());
		}
		catch(NumberFormatException e) {
			valid=false;
		}
		if(valid&&plaetze!=0&&nummer!=0&&plaetze>0&&nummer>0) {
			
		t.setArt(raucherToggleGroup.getSelectedToggle().getUserData().toString());
		t.setBeschreibung(beschreibung.getText());
		t.setPlaetze(plaetze);
		t.setNummer(nummer);
		Boolean avTN=false;
		try {
			avTN=s.availableTischnummer(t);
		} catch (IllegalArgumentException e1) {
			showErrorTischSpeichern(e1);
			return;
		} catch (DAOException e1) {
			showErrorTischSpeichern(e1);
			return;
		}
		if(avTN==false && neu) {
			Dialogs.showInformationDialog(this.getStage(), "Tischnummer wird bereits verwendet!", "Tisch erstellen/bearbeiten", "Information");
			t=null;
			return;
		}
		if(neu) { 
			try {
				s.createTisch(t);
			} catch (IllegalArgumentException e) {
				showErrorTischSpeichern(e);
				return;
			} catch (DAOException e) {
				showErrorTischSpeichern(e);
				return;
			}
		 }
		 else {
			 try {
				s.updateTisch(t);
			} catch (IllegalArgumentException e) {
				showErrorTischSpeichern(e);
				return;
			} catch (DAOException e) {
				showErrorTischSpeichern(e);
				return;
			}
		 }
		 
		 TischeController tc = (TischeController) this.getParentController();
		 tc.listTische();
		 this.getStage().hide();
		}
		else {
			Dialogs.showInformationDialog(this.getStage(), "Keinen gültigen Wert für Tischnummer oder Sitzplatzanzahl gewaehlt!", "Tisch erstellen/bearbeiten", "Information");

		}
	 }
	 
	 private void showErrorTischSpeichern(Exception e)
	 {
		 Dialogs.showErrorDialog(this.getStage(), "Tisch konnte nicht gespeichert werden.", "Speicherfehler", "Error", e);
	 }
	 
	 @FXML
	 public void clickOnAbort(ActionEvent event) {
		 this.getStage().hide();
	 }
	 
	 public void setTisch(Tisch t) {
		 this.t=t;
		 if(t!=null) {
				plaetzeTF.setText(t.getPlaetze().toString());
				if(t.getArt().contentEquals("Raucher"))
					raucherRadio.setSelected(true);
				else
					nichtRaucherRadio.setSelected(true);
				beschreibung.setText(t.getBeschreibung());
				nummerTF.setText(t.getNummer().toString());
			}
	 }
}

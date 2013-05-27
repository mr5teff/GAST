package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Tisch;
import sepm.ss13.gast.service.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class TischDialogController extends Controller {
	
	private Tisch t;
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
	
	}
	
	 @FXML
	 public void clickOnSave(ActionEvent event) {
		Boolean neu=false;
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
		if(valid&&plaetze!=0&&nummer!=0) {
			
		t.setArt(raucherToggleGroup.getSelectedToggle().getUserData().toString());
		t.setBeschreibung(beschreibung.getText());
		t.setPlaetze(plaetze);
		t.setNummer(nummer);
		Boolean avTN=false;
		try {
			avTN=s.availableTischnummer(t);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 else {
			 try {
				s.updateTisch(t);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	 
	 @FXML
	 public void clickOnAbort(ActionEvent event) {
		 this.getStage().hide();
	 }
	 
	 public void setTisch(Tisch t) {
		 this.t=t;
		 if(t!=null) {
			 System.out.println(t.getArt());
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

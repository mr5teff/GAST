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
import javafx.scene.control.TextField;

public class TischDialogController extends Controller {
	
	private Tisch t;
	private ObservableList<String> art;
	@FXML private TextField beschreibung;
	@FXML private ComboBox<String> artCB;
	@FXML private TextField plaetzeTF;
	@FXML private TextField nummerTF;
	private Service s;
	 
	public void initialize(URL location, ResourceBundle resources) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
		
		art = FXCollections.observableArrayList();
   	 	art.add("Raucher");
   	 	art.add("Nichtraucher");
   	 	artCB.setItems(art);
   	 	artCB.setValue("Raucher");
   	 	
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
			
		t.setArt(artCB.getSelectionModel().getSelectedItem());
		t.setBeschreibung(beschreibung.getText());
		t.setPlaetze(plaetze);
		t.setNummer(nummer);
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
				plaetzeTF.setText(t.getPlaetze().toString());
				artCB.setValue(t.getArt());
				beschreibung.setText(t.getBeschreibung());
				nummerTF.setText(t.getNummer().toString());
			}
	 }
}

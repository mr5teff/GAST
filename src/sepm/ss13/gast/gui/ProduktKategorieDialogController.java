package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.service.Service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProduktKategorieDialogController extends Controller {
	 private ProduktKategorie pk;
	
	 @FXML private TextField kurzbezeichnung;
	 @FXML private TextField bezeichnung;
	 
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
    	}
    }
	
	 @FXML
	 public void clickOnSave(ActionEvent event) {
		 if(pk == null) {
			 pk = new ProduktKategorie(0, bezeichnung.getText(), kurzbezeichnung.getText());
			 try {
				s.createProduktKategorie(pk);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 else {
			 pk.setBezeichnung(bezeichnung.getText());
			 pk.setKurzbezeichnung(kurzbezeichnung.getText());
			 try {
				s.updateProduktKategorie(pk);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 this.getStage().hide();
		 SpeisekarteController skc = (SpeisekarteController) this.getParentController();
		 skc.refreshKategorieListView();
	 }
	 
	 @FXML
	 public void clickOnAbbort(ActionEvent event) {
		 this.getStage().hide();
	 }
}

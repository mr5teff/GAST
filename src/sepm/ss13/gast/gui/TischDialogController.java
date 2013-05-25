package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Tisch;
import sepm.ss13.gast.service.Service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TischDialogController extends Controller {
	
	 private Tisch t;
	
	@FXML private TextField kurzbezeichnung;
	@FXML private TextField bezeichnung;
	 
	private Service s;
	 
	public void initialize(URL location, ResourceBundle resources) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
   	 	this.t=null;
	}
    
    public void setTisch(Tisch t) {
    	this.t=t;
    	
    	if(t!=null) {
    		//bezeichnung.setText(this.t.getBezeichnung());
            //kurzbezeichnung.setText(this.t.getKurzbezeichnung());
    	}
    }
	
	 @FXML
	 public void clickOnSave(ActionEvent event) {
		 if(t == null) {
			 t = new Tisch();
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
			 //t.setBezeichnung(bezeichnung.getText());
			 //t.setKurzbezeichnung(kurzbezeichnung.getText());
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
		 
		 this.getStage().hide();
		 TischeController tc = (TischeController) this.getParentController();
		 tc.listTische();
	 }
	 
	 @FXML
	 public void clickOnAbort(ActionEvent event) {
		 this.getStage().hide();
	 }
}

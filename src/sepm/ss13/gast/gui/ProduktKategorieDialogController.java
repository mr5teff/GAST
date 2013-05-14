package sepm.ss13.gast.gui;

import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.service.Service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProduktKategorieDialogController {
	 private ProduktKategorie pk;
	
	 @FXML private TextField kurzbezeichnung;
	 @FXML private TextField bezeichnung;
	 
	 private ApplicationContext ac;
	 private Service s;
	 
	 private Stage stage;
	 
	
	 /**
     * Default constructor.
     * 
     * @param owner
     * @param modality
     * @param style
     * @param title
     */	
    public ProduktKategorieDialogController(Stage stage, ProduktKategorie pk) {
    	this.pk=pk;
    	this.stage=stage;
    	
    	try {
			stage.setScene(GUIManager.loadFXML("NeueKategorieDialog.fxml", this));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
   	 	
   	 	ac = new ClassPathXmlApplicationContext("spring-config.xml");
   	 	s = (Service) ac.getBean("GASTService");
   	 	
        if(this.pk!=null) {
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
		 
		 stage.hide();
	 }
	 
	 @FXML
	 public void clickOnAbbort(ActionEvent event) {
		 stage.hide();
	 }
}

package sepm.ss13.gast.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.service.Service;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class NeueProduktKategorieDialog {
	 private ProduktKategorie content = null;
	
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
    public NeueProduktKategorieDialog() {
    	stage=GUIManager.openDialog("Produktkategorie anlegen/bearbeiten");
 
    	try {
			stage.setScene(GUIManager.loadFXML("NeueKategorieDialog.fxml", this));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
   	 	
   	 	ac = new ClassPathXmlApplicationContext("spring-config.xml");
   	 	s = (Service) ac.getBean("GASTService");
    }
   
    
    public void showDialog()
    {
    	stage.show();
    }
    
    
    public void hideDialog()
    {
    	stage.hide();
    }
    
	public void clearForm()
	{
		bezeichnung.setText("");
		kurzbezeichnung.setText("");
		content = null;
	}
	
	public void setContent(ProduktKategorie pk)
	{
		content = pk;
		bezeichnung.setText(pk.getBezeichnung());
		kurzbezeichnung.setText(pk.getKurzbezeichnung());	
	}
	
	 @FXML
	 public void clickOnSave(ActionEvent event) {
		 ProduktKategorie pkNew = null;
		 if(content == null)
			 pkNew = new ProduktKategorie(0, bezeichnung.getText(), kurzbezeichnung.getText());
		 
		 try {
			 if(content == null)
				 s.createProduktKategorie(pkNew);
			 else
			 {
				 content.setBezeichnung(bezeichnung.getText());
				 content.setKurzbezeichnung(kurzbezeichnung.getText());
				 s.updateProduktKategorie(content);
			 }
				 
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 stage.hide();
		 
	 }
	 
	 @FXML
	 public void clickOnAbbort(ActionEvent event) {
		
		 stage.hide();
	 }
    
 
    
   
    

}

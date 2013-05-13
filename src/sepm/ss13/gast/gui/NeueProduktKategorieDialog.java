package sepm.ss13.gast.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

public class NeueProduktKategorieDialog extends Stage{
	

	private static Logger log = Logger.getLogger(Application.class);
	
	 private ProduktKategorie content = null;
	
	 @FXML private TextField kurzbezeichnung;
	 @FXML private TextField bezeichnung;
	 
	
	 /**
     * Default constructor.
     * 
     * @param owner
     * @param modality
     * @param style
     * @param title
     */	
    public NeueProduktKategorieDialog(Window owner, Modality modality, String title) {
 
    	super();
 
        initOwner(owner);
        initModality(modality);
        setOpacity(1);
        if (title != null && !title.isEmpty())
            setTitle(title);
        
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NeueKategorieDialog.fxml"));
        fxmlLoader.setController(this);
   	 	try {
   		 root = (Parent)fxmlLoader.load(); 
   	 	} catch (IOException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	 	}
   	 	
   	 	
   	 	
   	 	setScene(new Scene(root)); 
    }
   
    
    public void showDialog()
    {
    	super.show();
    }
    
    
    public void hideDialog()
    {
    	super.hide();
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
		
	 }
	 
	 @FXML
	 public void clickOnAbbort(ActionEvent event) {
		
		 
	 }
    
 
    
   
    

}

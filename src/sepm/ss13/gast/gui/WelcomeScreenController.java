package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class WelcomeScreenController implements Initializable {
	
	 private static Logger log = Logger.getLogger(Application.class);
	
	 private GAST application;    
	    
	 public void setApp(GAST application){
	        this.application = application;
	 }
	 
	 
	 
	 public void clickOnKassa(ActionEvent event) {
		 
		 if (application == null){
	            // We are running in isolated FXML, possibly in Scene Builder.
	            // NO-OP.
			 	log.warn("No application set. No operation!");
	        } 
		 else {
			 
			 application.gotoKassa();
	     
	     }
	 }

	 public void clickOnKueche(ActionEvent event) {
		 if (application == null){
	            // We are running in isolated FXML, possibly in Scene Builder.
	            // NO-OP.
			 log.warn("No application set. No operation!");
	        } 
	     else {
	    	 application.gotoKueche();
	           
	        }
	 }
	 
	 public void clickOnManagement(ActionEvent event) {
		 if (application == null){
	            // We are running in isolated FXML, possibly in Scene Builder.
	            // NO-OP.
			 log.warn("No application set. No operation!");
	     } 
		 else {
			 
			 application.gotoManagement();
	            
	     }
	 }
 
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

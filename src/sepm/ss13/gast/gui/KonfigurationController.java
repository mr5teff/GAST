package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Konfiguration;
import sepm.ss13.gast.service.Service;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class KonfigurationController implements Initializable {

	private ApplicationContext ac;
	private GUIManager gast;
	private static Logger log = Logger.getLogger(Application.class);
	 
	private Service s;
	 
	 
	 @FXML private Label name;
	 @FXML private Label adresse;
	 @FXML private Label tel;
	 @FXML private Label logo;
	 @FXML private Label tischanzahl;


	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		 ac = new ClassPathXmlApplicationContext("spring-config.xml");
		 gast = (GUIManager) ac.getBean("GUIManager");
		
	}
	
	 @FXML
	 public void saveKonfiguration(ActionEvent e) {
	/*	 Konfiguration k = new Konfiguration(name.getText(),adresse.getText(),tel.getText(),null,Integer.parseInt(tischanzahl.getText()));
		/* 
		 try {
			s.saveKonfiguration(k);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 */
		 System.out.println("GEEEHT auch");
	 }
	
	
}

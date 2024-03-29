package sepm.ss13.gast.gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;


import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Konfiguration;
import sepm.ss13.gast.service.Service;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class KonfigurationController extends Controller {
	private Service s;
	private Konfiguration k;
	private BufferedImage image;
	 
	 @FXML private TextField name;
	 @FXML private TextField adresse;
	 @FXML private TextField tel;
	 @FXML private ImageView logo;
	 @FXML private TextField timerIntervalTF;

	public void initialize(URL arg0, ResourceBundle arg1) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
		loadKonfiguration();	
	}
		
	private void loadKonfiguration()  {
		k = null;
		try {
			k = s.loadKonfiguration();
		} catch (DAOException e) {
			Dialogs.showErrorDialog(this.getStage(), "Konfiguration konnte nicht geladen werden.", "Ladefehler", "Konfiguration laden", e);
		}


		name.setText(k.getName());
		adresse.setText(k.getAdresse());
		tel.setText(k.getTel());
		timerIntervalTF.setText(String.valueOf(k.getTimerIntervall()));
		try {
			if(k.getLogo()==null) {
				logo.setImage(null);
			}
			else {
				image=ImageIO.read(new ByteArrayInputStream(k.getLogo()));
				logo.setImage( SwingFXUtils.toFXImage(image, null));
			}
		} catch (IOException e) {
			Dialogs.showErrorDialog(this.getStage(), "Logo konnte nicht geladen werden.", "Ladefehler", "Konfiguration laden", e);
		}
	}
	 @FXML
	 public void saveKonfiguration(ActionEvent e) {
		

		if(name.getText().isEmpty()||adresse.getText().isEmpty()||tel.getText().isEmpty()){
			Dialogs.showInformationDialog(this.getStage(), "Es wurden nicht alle Felder bef�llt!", "Konfiguration speichern", "Information");
			return;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write( image, "jpg", baos );
			baos.flush();
		} catch (IOException e2) {
			Dialogs.showErrorDialog(this.getStage(), "Logo konnte nicht gespeichert werden.", "Speicherfehler", "Konfiguration speichern", e2);
		}
		 
		 byte[] logo = baos.toByteArray();

		k = new Konfiguration(name.getText(),adresse.getText(),tel.getText(),logo,30);
		
		try {
			s.saveKonfiguration(k);
		} catch (IllegalArgumentException e1) {
			Dialogs.showErrorDialog(this.getStage(), "Konfiguration konnte nicht gespeichert werden.", "Speicherfehler", "Konfiguration speichern", e1);
		} catch (DAOException e1) {
			Dialogs.showErrorDialog(this.getStage(), "Konfiguration konnte nicht gespeichert werden.", "Speicherfehler", "Konfiguration speichern", e1);
		}
		 
	 }
	 
	 @FXML
	 public void undoKonfiguration(ActionEvent e) {
		 loadKonfiguration();
	 }	
	 
	 @FXML
	 public void chooseLogo(ActionEvent e) {
		 FileChooser fileChooser = new FileChooser();
		 
        //extension filter setzen
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.png", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        extFilter = new FileChooser.ExtensionFilter("*.jpg", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        extFilter = new FileChooser.ExtensionFilter("*.gif", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        //dialog �ffnen
        File picture = fileChooser.showOpenDialog(null);
        if(picture!=null) {
        	try {
        		image = ImageIO.read(picture);
        		logo.setImage( SwingFXUtils.toFXImage(image, null));
        	} catch (IOException e1) {
        		Dialogs.showErrorDialog(this.getStage(), "Logo konnte nicht geladen werden.", "Dateizugriffsfehler", "Logo ausw�hlen", e1);
        	}
        }
      
     
   	 }	   

	@FXML
	public void saveTimerInterval(ActionEvent e) 
	{
		int timerInterval = 0;

		try 
		{
			timerInterval = Integer.parseInt(timerIntervalTF.getText());
		}
		catch(NumberFormatException e2) 
		{
			Dialogs.showErrorDialog(this.getStage(), "Aktualisierungsintervall konnte nicht ge�ndert werden.", "Eingabefehler", "Aktualisierungsintervall �ndern", e2);
		}
		
		k.setTimerIntervall(timerInterval);
		
		try 
		{
			s.saveKonfiguration(k);
		} 
		catch (IllegalArgumentException e1) 
		{
			Dialogs.showErrorDialog(this.getStage(), "Konfiguration konnte nicht gespeichert werden.", "Speicherfehler", "Konfiguration speichern", e1);
		} 
		catch (DAOException e1) 
		{
			Dialogs.showErrorDialog(this.getStage(), "Konfiguration konnte nicht gespeichert werden.", "Speicherfehler", "Konfiguration speichern", e1);
		}
	}	 
}

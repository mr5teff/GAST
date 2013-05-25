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
	private BufferedImage image;
	 
	 @FXML private TextField name;
	 @FXML private TextField adresse;
	 @FXML private TextField tel;
	 @FXML private ImageView logo;
	 @FXML private TextField tischanzahl;

	public void initialize(URL arg0, ResourceBundle arg1) {
		s = (Service) this.getApplicationContext().getBean("GASTService");
		loadKonfiguration();	
	}
		
	private void loadKonfiguration()  {
		Konfiguration k = null;
		try {
			k = s.loadKonfiguration();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		name.setText(k.getName());
		adresse.setText(k.getAdresse());
		tel.setText(k.getTel());
		tischanzahl.setText(String.valueOf(k.getTischanzahl()));
		try {
			if(k.getLogo()==null) {
				logo.setImage(null);
			}
			else {
				image=ImageIO.read(new ByteArrayInputStream(k.getLogo()));
				logo.setImage( SwingFXUtils.toFXImage(image, null));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 @FXML
	 public void saveKonfiguration(ActionEvent e) {
		
		int anzahl=0;
		try {
			anzahl=Integer.parseInt(tischanzahl.getText());
			if(anzahl<1) throw new NumberFormatException();
		}
		catch(NumberFormatException e1) {
			Dialogs.showInformationDialog(this.getStage(), "Keine gültige Tischanzahl!", "Konfiguration speichern", "Information");
			return;
		}
		if(name.getText().isEmpty()||adresse.getText().isEmpty()||tel.getText().isEmpty()){
			Dialogs.showInformationDialog(this.getStage(), "Es wurden nicht alle Felder befüllt!", "Konfiguration speichern", "Information");
			return;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write( image, "jpg", baos );
			baos.flush();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 
		 byte[] logo = baos.toByteArray();

		 Konfiguration k = new Konfiguration(name.getText(),adresse.getText(),tel.getText(),logo,anzahl);
		
		try {
			s.saveKonfiguration(k);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
	 }
	 
	 public void undoKonfiguration(ActionEvent e) {
		 loadKonfiguration();
	 }	
	 
	 public void chooseLogo(ActionEvent e) {
		 FileChooser fileChooser = new FileChooser();
		 
        //extension filter setzen
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.png", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        extFilter = new FileChooser.ExtensionFilter("*.jpg", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        extFilter = new FileChooser.ExtensionFilter("*.gif", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        //dialog öffnen
        File picture = fileChooser.showOpenDialog(null);
        if(picture!=null) {
        	try {
        		image = ImageIO.read(picture);
        		logo.setImage( SwingFXUtils.toFXImage(image, null));
        	} catch (IOException e1) {
        		// TODO Auto-generated catch block
        		e1.printStackTrace();
        	}
        }
        
	 }
}

package sepm.ss13.gast.gui;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.service.Service;

public class ProduktDialogController extends Controller {

	private Service s;
	
	private ProduktKategorie pk;
	private Produkt produkt;
	
	@FXML private TextField name;
	@FXML private TextField preis;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		s = (Service) this.getApplicationContext().getBean("GASTService");
   	 	
	}
	
	public void setPK(ProduktKategorie pk) {
		
    	this.pk=pk;
	}
	
	public void setProdukt(Produkt produkt) {
		
    	this.produkt=produkt;
    	
    	if(produkt != null)
    	{
    		name.setText(produkt.getName());
    		preis.setText(Integer.toString(produkt.getPreis()));
    	}
	}
	
	@FXML
	 public void clickOnSave(ActionEvent event) {
		
		//Neues Produkt erstellen
		if(produkt == null)
		{
			if(pk == null)
			{
				//Unerwarteter Fehler
				this.getStage().hide();
				return;
			}
			
			int preisInt = readPreis();
			if(preisInt == -1)
				return;
			
			Produkt p = new Produkt(0, name.getText(), pk.getId(), preisInt);
			try {
				s.createProdukt(p);
			} catch (IllegalArgumentException e) {
				Dialogs.showInformationDialog(this.getStage(), "Bitte geben Sie gültige Paramter an.\n" +
						 "Name muss mindestens ein Zeichen beinhalten und der Preis muss ein positiver ganzzahliger Centbetrag sein.",
						 "Ungültige Eingabe", "Produkt anlegen");
				produkt = null;
				return;
			} catch (DAOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Produkt konnte nicht erstellet werden.", "Speicherfehler", "Produkt erstellen", e);
			}
			
		}
		//Produkt bearbeiten
		else
		{
			int preisInt = readPreis();
			if(preisInt == -1)
				return;
			
			produkt.setName(name.getText());
			produkt.setPreis(preisInt);
			
			 try {
				s.updateProdukt(produkt);
			} catch (IllegalArgumentException e) {
				Dialogs.showInformationDialog(this.getStage(), "Bitte geben Sie gültige Paramter an.\n" +
						 "Name muss mindestens ein Zeichen beinhalten und der Preis muss ein positiver ganzzahliger Centbetrag sein.",
						 "Ungültige Eingabe", "Produkt bearbeiten");
				return;
			} catch (DAOException e) {
				Dialogs.showErrorDialog(this.getStage(), "Produkt konnte nicht bearbeitet werden.", "Speicherfehler", "Produkt bearbeiten", e);
			}
		}
		
		SpeisekarteController skc = (SpeisekarteController) this.getParentController();
		skc.produktListeBefuellen(pk);	
		this.getStage().hide();
	}
	
	private int readPreis()
	{
		int preisInt = 0;
		try
		{
			preisInt = Integer.parseInt(preis.getText());
		}
		catch(NumberFormatException ex)
		{
			Dialogs.showInformationDialog(this.getStage(), "Bitte geben Sie den Preis als ganzzahligen Centbetrag an.", "Produkt erstellen", "Information");
			return -1;
		}
		
		return preisInt;
	}
	
	 @FXML
	 public void clickOnAbort(ActionEvent event) {
		 this.getStage().hide();
	 }
	
	

}

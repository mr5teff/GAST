package sepm.ss13.gast.service;

import java.util.ArrayList;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.domain.Einkauf;
import sepm.ss13.gast.domain.Konfiguration;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.domain.Rechnung;
import sepm.ss13.gast.domain.Reservierung;
import sepm.ss13.gast.domain.Ware;

public interface Service {
	
	/*
	 * Services für Bestellung
	 */
	
	public Bestellung createBestellung(Bestellung b) throws DAOException, IllegalArgumentException;
	
	public ArrayList<Bestellung> searchBestellung(Bestellung b) throws DAOException, IllegalArgumentException;
	
	public void deleteBestellung(Bestellung b) throws DAOException, IllegalArgumentException;
	
	/*
	 * Services für Einkauf
	 */
	
	public Einkauf createEinkauf(Einkauf ek) throws DAOException, IllegalArgumentException;
	
	public ArrayList<Einkauf> searchEinkauf(Einkauf ek) throws DAOException, IllegalArgumentException;
	
	public void updateEinkauf(Einkauf ek) throws DAOException, IllegalArgumentException;
	
	public void deleteEinkauf(Einkauf ek) throws DAOException, IllegalArgumentException;
	
	/*
	 * Services für Konfiguration
	 */
	
	public Konfiguration loadKonfiguration() throws DAOException;

	public void saveKonfiguration(Konfiguration k) throws DAOException, IllegalArgumentException;
	
	/*
	 * Services für Produkt
	 */
	
	public Produkt createProdukt(Produkt p) throws DAOException, IllegalArgumentException;

	public ArrayList<Produkt> searchProdukt(Produkt p) throws DAOException, IllegalArgumentException;

	public void updateProdukt(Produkt p) throws DAOException, IllegalArgumentException;
	
	/*
	 * Services für ProduktKategorie
	 */
	
	public ProduktKategorie createProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException;

	public ArrayList<ProduktKategorie> searchProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException;

	public void updateProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException;

	public void deleteProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException;
	
	/*
	 * Services für Rechnung
	 */
	
	public Rechnung createRechung(Rechnung r) throws DAOException, IllegalArgumentException;

	public ArrayList<Rechnung> searchRechung(Rechnung r) throws DAOException, IllegalArgumentException;

	/*
	 * Services für Reservierung
	 */
	
	public Reservierung createReservierung(Reservierung r) throws DAOException, IllegalArgumentException;
	
	public ArrayList<Reservierung> searchReservierung(Reservierung r) throws DAOException, IllegalArgumentException;
	
	public void updateReservierung(Reservierung r) throws DAOException, IllegalArgumentException;
	
	public void deleteReservierung(Reservierung r) throws DAOException, IllegalArgumentException;

	/*
	 * Services für Ware
	 */
	
	public Ware createWare(Ware w) throws DAOException, IllegalArgumentException;
	
	public ArrayList<Ware> searchWare(Ware w) throws DAOException, IllegalArgumentException;
	
	public void updateWare(Ware ek) throws DAOException, IllegalArgumentException;
	
	public void deleteWare(Ware ek) throws DAOException, IllegalArgumentException;
}

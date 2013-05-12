package sepm.ss13.gast.service;

import java.util.ArrayList;

import sepm.ss13.gast.dao.BestellungDAO;
import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.dao.EinkaufDAO;
import sepm.ss13.gast.dao.KonfigurationDAO;
import sepm.ss13.gast.dao.ProduktDAO;
import sepm.ss13.gast.dao.ProduktKategorieDAO;
import sepm.ss13.gast.dao.RechnungDAO;
import sepm.ss13.gast.dao.ReservierungDAO;
import sepm.ss13.gast.dao.WareDAO;
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.domain.Einkauf;
import sepm.ss13.gast.domain.Konfiguration;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.domain.Rechnung;
import sepm.ss13.gast.domain.Reservierung;
import sepm.ss13.gast.domain.Ware;

public class GASTService implements Service{
	
	private BestellungDAO bestellungDAO;
	private EinkaufDAO einkaufDAO;
	private KonfigurationDAO konfigurationDAO;
	private ProduktDAO produktDAO;
	private ProduktKategorieDAO produktKategorieDAO;
	private RechnungDAO rechnungDAO;
	private ReservierungDAO reservierungDAO;
	private WareDAO wareDAO;
	
	public BestellungDAO getBestellungDAO() {
		return bestellungDAO;
	}

	public void setBestellungDAO(BestellungDAO bestellungDAO) {
		this.bestellungDAO = bestellungDAO;
	}
	

	public EinkaufDAO getEinkaufDAO() {
		return einkaufDAO;
	}

	public void setEinkaufDAO(EinkaufDAO einkaufDAO) {
		this.einkaufDAO = einkaufDAO;
	}
	
	public KonfigurationDAO getKonfigurationDAO() {
		return konfigurationDAO;
	}

	public void setKonfigurationDAO(KonfigurationDAO konfigurationDAO) {
		this.konfigurationDAO = konfigurationDAO;
	}
	
	public ProduktDAO getProduktDAO() {
		return produktDAO;
	}

	public void setProduktDAO(ProduktDAO produktDAO) {
		this.produktDAO = produktDAO;
	}
	
	public ProduktKategorieDAO getProduktKategorieDAO() {
		return produktKategorieDAO;
	}

	public void setProduktKategorieDAO(ProduktKategorieDAO produktKategorieDAO) {
		this.produktKategorieDAO = produktKategorieDAO;
	}
	
	public RechnungDAO getRechnungDAO() {
		return rechnungDAO;
	}

	public void setRechnungDAO(RechnungDAO rechnungDAO) {
		this.rechnungDAO = rechnungDAO;
	}

	public ReservierungDAO getReservierungDAO() {
		return reservierungDAO;
	}

	public void setReservierungDAO(ReservierungDAO reservierungDAO) {
		this.reservierungDAO = reservierungDAO;
	}

	public WareDAO getWareDAO() {
		return wareDAO;
	}

	public void setWareDAO(WareDAO wareDAO) {
		this.wareDAO = wareDAO;
	}

	/*
	 * Services für Bestellung
	 */
	public Bestellung createBestellung(Bestellung b) throws DAOException,IllegalArgumentException{
		if(b==null) throw new IllegalArgumentException();
		return bestellungDAO.create(b);
	}

	public ArrayList<Bestellung> searchBestellung(Bestellung b) throws DAOException, IllegalArgumentException {
		if(b==null) throw new IllegalArgumentException();
		return bestellungDAO.search(b);
	}

	public void deleteBestellung(Bestellung b) throws DAOException, IllegalArgumentException {
		if(b==null) throw new IllegalArgumentException();
		bestellungDAO.delete(b);
		
	}

	/*
	 * Services für Einkauf
	 */
	
	public Einkauf createEinkauf(Einkauf ek) throws DAOException, IllegalArgumentException {
		if(ek==null) throw new IllegalArgumentException();
		return einkaufDAO.create(ek);
	}

	public ArrayList<Einkauf> searchEinkauf(Einkauf ek) throws DAOException, IllegalArgumentException {
		if(ek==null) throw new IllegalArgumentException();
		return einkaufDAO.search(ek);
	}

	public void updateEinkauf(Einkauf ek) throws DAOException, IllegalArgumentException {
		if(ek==null) throw new IllegalArgumentException();
		einkaufDAO.update(ek);
	}

	public void deleteEinkauf(Einkauf ek) throws DAOException, IllegalArgumentException {
		if(ek==null) throw new IllegalArgumentException();
		einkaufDAO.delete(ek);
	}

	/*
	 * Services für Konfiguration
	 */
	public Konfiguration loadKonfiguration() throws DAOException {
		return konfigurationDAO.load();
	}

	public void saveKonfiguration(Konfiguration k) throws DAOException, IllegalArgumentException {
		if(k==null) throw new IllegalArgumentException();
		konfigurationDAO.save(k);
	}

	/*
	 * Services für Produkt
	 */
	
	public Produkt createProdukt(Produkt p) throws DAOException, IllegalArgumentException {
		if(p==null) throw new IllegalArgumentException();
		return produktDAO.create(p);
	}

	public ArrayList<Produkt> searchProdukt(Produkt p) throws DAOException, IllegalArgumentException {
		if(p==null) throw new IllegalArgumentException();
		return produktDAO.search(p);
	}

	public void updateProdukt(Produkt p) throws DAOException, IllegalArgumentException {
		if(p==null) throw new IllegalArgumentException();
		produktDAO.update(p);
	}

	/*
	 * Services für ProduktKategorie
	 */
	
	public ProduktKategorie createProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException {
		if(p==null) throw new IllegalArgumentException();
		return produktKategorieDAO.create(p);
	}

	public ArrayList<ProduktKategorie> searchProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException {
		if(p==null) throw new IllegalArgumentException();
		return produktKategorieDAO.search(p);
	}

	public void updateProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException {
		if(p==null) throw new IllegalArgumentException();
		produktKategorieDAO.update(p);
	}

	public void deleteProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException {
		if(p==null) throw new IllegalArgumentException();
		produktKategorieDAO.delete(p);
	}

	/*
	 * Services für RechnungDAO
	 */
	
	public Rechnung createRechung(Rechnung r) throws DAOException, IllegalArgumentException {
		if(r==null) throw new IllegalArgumentException();
		return rechnungDAO.create(r);
	}

	public ArrayList<Rechnung> searchRechung(Rechnung r) throws DAOException, IllegalArgumentException {
		if(r==null) throw new IllegalArgumentException();
		return rechnungDAO.search(r);
	}

	/*
	 * Services für Reservierung
	 */
	
	public Reservierung createReservierung(Reservierung r) throws DAOException, IllegalArgumentException {
		if(r==null) throw new IllegalArgumentException();
		return reservierungDAO.create(r);
	}
	
	public ArrayList<Reservierung> searchReservierung(Reservierung r) throws DAOException, IllegalArgumentException {
		if(r==null) throw new IllegalArgumentException();
		return reservierungDAO.search(r);
	}

	public void updateReservierung(Reservierung r) throws DAOException, IllegalArgumentException {
		if(r==null) throw new IllegalArgumentException();
		reservierungDAO.update(r);
	}

	public void deleteReservierung(Reservierung r) throws DAOException, IllegalArgumentException {
		if(r==null) throw new IllegalArgumentException();
		reservierungDAO.delete(r);
	}
	
	/*
	 * Services für Ware
	 */
	
	public Ware createWare(Ware w) throws DAOException, IllegalArgumentException {
		if(w==null) throw new IllegalArgumentException();
		return wareDAO.create(w);
	}

	public ArrayList<Ware> searchWare(Ware w) throws DAOException, IllegalArgumentException {
		if(w==null) throw new IllegalArgumentException();
		return wareDAO.search(w);
	}

	public void updateWare(Ware ek) throws DAOException, IllegalArgumentException {
		if(ek==null) throw new IllegalArgumentException();
		wareDAO.update(ek);
	}

	public void deleteWare(Ware ek) throws DAOException, IllegalArgumentException {
		if(ek==null) throw new IllegalArgumentException();
		wareDAO.delete(ek);
	}	
	
}

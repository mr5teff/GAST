package sepm.ss13.gast.service;

import java.sql.Connection;
import java.util.ArrayList;

import sepm.ss13.gast.dao.BestellungDAO;
import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.dao.DBConnector;
import sepm.ss13.gast.dao.EinkaufDAO;
import sepm.ss13.gast.dao.JDBCBestellungDAO;
import sepm.ss13.gast.dao.JDBCEinkaufDAO;
import sepm.ss13.gast.dao.JDBCKonfigurationDAO;
import sepm.ss13.gast.dao.JDBCProduktDAO;
import sepm.ss13.gast.dao.JDBCProduktKategorieDAO;
import sepm.ss13.gast.dao.JDBCRechnungDAO;
import sepm.ss13.gast.dao.JDBCReservierungDAO;
import sepm.ss13.gast.dao.JDBCWareDAO;
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
	
	public GASTService(DBConnector dbCon) {	//Die Zuweisung hier sollte man glaub ich über spring machen
		
		Connection con = dbCon.getConnection();
		this.bestellungDAO = new JDBCBestellungDAO(con);
		this.einkaufDAO = new JDBCEinkaufDAO(con);
		this.konfigurationDAO = new JDBCKonfigurationDAO(con);
		this.produktDAO = new JDBCProduktDAO(con);
		this.produktKategorieDAO = new JDBCProduktKategorieDAO(con);
		this.rechnungDAO = new JDBCRechnungDAO(con);
		this.reservierungDAO = new JDBCReservierungDAO(con);
		this.wareDAO = new JDBCWareDAO(con);
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
	
	public ArrayList<Produkt> getProduktNachKategorie(ProduktKategorie pk) throws DAOException, IllegalArgumentException {
		if(pk==null) throw new IllegalArgumentException();
		return produktDAO.getProdukteNachKategorie(pk);
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

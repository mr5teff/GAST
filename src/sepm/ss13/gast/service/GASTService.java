package sepm.ss13.gast.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import sepm.ss13.gast.dao.BestellungDAO;
import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.dao.DBConnector;
import sepm.ss13.gast.dao.EinkaufDAO;
import sepm.ss13.gast.dao.JDBCBestellungDAO;
import sepm.ss13.gast.dao.JDBCEinkaufDAO;
import sepm.ss13.gast.dao.JDBCKonfigurationDAO;
import sepm.ss13.gast.dao.JDBCKonsumstatistikDAO;
import sepm.ss13.gast.dao.JDBCProduktDAO;
import sepm.ss13.gast.dao.JDBCProduktKategorieDAO;
import sepm.ss13.gast.dao.JDBCRechnungDAO;
import sepm.ss13.gast.dao.JDBCReservierungDAO;
import sepm.ss13.gast.dao.JDBCRezeptDAO;
import sepm.ss13.gast.dao.JDBCTischDAO;
import sepm.ss13.gast.dao.JDBCWareDAO;
import sepm.ss13.gast.dao.KonfigurationDAO;
import sepm.ss13.gast.dao.KonsumstatistikDAO;
import sepm.ss13.gast.dao.ProduktDAO;
import sepm.ss13.gast.dao.ProduktKategorieDAO;
import sepm.ss13.gast.dao.RechnungDAO;
import sepm.ss13.gast.dao.ReservierungDAO;
import sepm.ss13.gast.dao.TischDAO;
import sepm.ss13.gast.dao.WareDAO;
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.domain.Einkauf;
import sepm.ss13.gast.domain.Konfiguration;
import sepm.ss13.gast.domain.Konsumstatistik;
import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;
import sepm.ss13.gast.domain.Rechnung;
import sepm.ss13.gast.domain.Reservierung;
import sepm.ss13.gast.domain.Rezept;
import sepm.ss13.gast.domain.Tisch;
import sepm.ss13.gast.domain.Ware;
import sepm.ss13.gast.gui.GAST;

public class GASTService implements Service{
	
	private BestellungDAO bestellungDAO;
	private EinkaufDAO einkaufDAO;
	private KonfigurationDAO konfigurationDAO;
	private ProduktDAO produktDAO;
	private ProduktKategorieDAO produktKategorieDAO;
	private RechnungDAO rechnungDAO;
	private ReservierungDAO reservierungDAO;
	private WareDAO wareDAO;
	private TischDAO tischDAO;
	private JDBCRezeptDAO rezeptDAO;
	private KonsumstatistikDAO konsumstatistikDAO;
	
	public GASTService(DBConnector dbCon) {	//Die Zuweisung hier sollte man glaub ich ueber spring machen
		
		Connection con = dbCon.getConnection();
		this.bestellungDAO = new JDBCBestellungDAO(con);
		this.einkaufDAO = new JDBCEinkaufDAO(con);
		this.konfigurationDAO = new JDBCKonfigurationDAO(con);
		this.produktDAO = new JDBCProduktDAO(con);
		this.produktKategorieDAO = new JDBCProduktKategorieDAO(con);
		this.rechnungDAO = new JDBCRechnungDAO(con);
		this.reservierungDAO = new JDBCReservierungDAO(con);
		this.wareDAO = new JDBCWareDAO(con);
		this.tischDAO= new JDBCTischDAO(con);
		this.rezeptDAO= new JDBCRezeptDAO(con);
		this.konsumstatistikDAO= new JDBCKonsumstatistikDAO(con);
	}
	
	/*
	 * Services fuer Bestellung
	 */
	
	public void verifyBestellung(Bestellung b) throws DAOException, IllegalArgumentException{
		try {
			if(b.getPname().equals("")||b.getPreis()<0||b.getProdukt()<0||b.getTisch()<0) throw new IllegalArgumentException();
		} catch(NullPointerException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public Bestellung createBestellung(Bestellung b) throws DAOException,IllegalArgumentException{
		verifyBestellung(b);
		b.setBestelldatum(new java.util.Date());
		return bestellungDAO.create(b);
	}

	public ArrayList<Bestellung> searchBestellung(Bestellung b) throws DAOException, IllegalArgumentException {
		if(b==null) throw new IllegalArgumentException();
		return bestellungDAO.search(b);
	}
	
	public void updateBestellung(Bestellung b) throws DAOException, IllegalArgumentException {
		verifyBestellung(b);
		if(b.getId()<0) throw new IllegalArgumentException();
		bestellungDAO.update(b);
	}

	public void deleteBestellung(Bestellung b) throws DAOException, IllegalArgumentException {
		if(b==null) throw new IllegalArgumentException();
		if(b.getId()<0) throw new IllegalArgumentException();
		bestellungDAO.delete(b);
		
	}

	/*
	 * Services fuer Einkauf
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
	 * Services fuer Konfiguration
	 */
	public Konfiguration loadKonfiguration() throws DAOException {
		return konfigurationDAO.load();
	}

	public void saveKonfiguration(Konfiguration k) throws DAOException, IllegalArgumentException {
		if(k==null) throw new IllegalArgumentException();
		if(k.getAdresse().equals("")||k.getName().equals("")||k.getTel().equals("")) throw new IllegalArgumentException();
		konfigurationDAO.save(k);
	}

	/*
	 * Services fuer Produkt
	 */
	
	public void verifyProdukt(Produkt p) throws DAOException, IllegalArgumentException{
		if(p==null) throw new IllegalArgumentException();
		if(p.getKategorie()<0||p.getName().equals("")||p.getPreis()<0) throw new IllegalArgumentException();
	}
	
	public Produkt createProdukt(Produkt p) throws DAOException, IllegalArgumentException {
		verifyProdukt(p);
		return produktDAO.create(p);
	}

	public ArrayList<Produkt> searchProdukt(Produkt p) throws DAOException, IllegalArgumentException {
		if(p==null) throw new IllegalArgumentException();
		return produktDAO.search(p);
	}

	public void updateProdukt(Produkt p) throws DAOException, IllegalArgumentException {
		verifyProdukt(p);
		produktDAO.update(p);
	}
	
	public void deleteProdukt(Produkt p) throws DAOException, IllegalArgumentException {
		if(p==null) throw new IllegalArgumentException();
		if(p.getId()<0) throw new IllegalArgumentException();
		produktDAO.delete(p);
	}


	/*
	 * Services fuer ProduktKategorie
	 */
	
	public void verifyProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException{
		try {
			if(p.getBezeichnung().equals("")||p.getKurzbezeichnung().equals("")||p.getSteuer()<0) throw new IllegalArgumentException();
		} catch (NullPointerException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public ProduktKategorie createProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException {
		verifyProduktKategorie(p);
		return produktKategorieDAO.create(p);
	}

	public ArrayList<ProduktKategorie> searchProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException {
		if(p==null) throw new IllegalArgumentException();
		return produktKategorieDAO.search(p);
	}

	public void updateProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException {
		verifyProduktKategorie(p);
		if(p.getId()==null) throw new IllegalArgumentException();
		produktKategorieDAO.update(p);
	}

	public void deleteProduktKategorie(ProduktKategorie p) throws DAOException, IllegalArgumentException {
		if(p==null) throw new IllegalArgumentException();
		if(p.getId()<0) throw new IllegalArgumentException();
		produktKategorieDAO.delete(p);
	}

	/*
	 * Services fuer Rechnungen
	 */
	
	public Rechnung createRechung(ArrayList<Bestellung> al, int trinkgeld) throws DAOException, IllegalArgumentException {
		if(al==null) throw new IllegalArgumentException();
		
		Rechnung r = new Rechnung();
		r.setDatum(new Date());
		r.setTrinkgeld(trinkgeld);
		r=rechnungDAO.create(r);
		
		for(Bestellung b:al) {
			if(b==null) throw new IllegalArgumentException();
			b.setRechnung(r.getId());
			bestellungDAO.update(b);
		}
		
		PdfService pdfS = (PdfService) GAST.getApplicationContext().getBean("PdfService");
		pdfS.createPDF(r);
		
		return r;
	}

	public ArrayList<Rechnung> searchRechung(Rechnung r) throws DAOException, IllegalArgumentException {
		if(r==null) throw new IllegalArgumentException();
		return rechnungDAO.search(r);
	}
	
	public void updateRechnung(Rechnung r) throws DAOException {
		if(r==null) throw new IllegalArgumentException();
		rechnungDAO.update(r);
	}

	/*
	 * Services fuer Reservierung
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
	 * Services fuer Ware
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
	
	/*
	 * Services fuer Rezept
	 */

	public void createRezept(Rezept w) throws DAOException, IllegalArgumentException {
		if(w==null) throw new IllegalArgumentException();
		Rezept toS = new Rezept(w.getProduktId(),w.getWareId(),-1,null);
		ArrayList<Rezept> tmp = rezeptDAO.search(toS);
		if(tmp.size() != 0){
			Rezept toU = new Rezept(w.getProduktId(),w.getWareId(),w.getMenge() + tmp.get(0).getMenge(),null);
			rezeptDAO.update(toU);
		}else{
			rezeptDAO.create(w);
		}
		
		
	}

	
	public ArrayList<Rezept> searchRezept(Rezept r) throws DAOException, IllegalArgumentException {
		if(r==null) throw new IllegalArgumentException();
		return rezeptDAO.search(r);
	}
	
	public void deleteRezept(Rezept r) throws DAOException, IllegalArgumentException {
		if(r==null) throw new IllegalArgumentException();
		rezeptDAO.delete(r);
	}

	/*
	 * Services fuer Tisch
	 */
	
	public Tisch createTisch(Tisch t) throws DAOException, IllegalArgumentException {
		if(t==null) throw new IllegalArgumentException();
		return tischDAO.create(t);
	}

	public ArrayList<Tisch> searchTisch(Tisch t) throws DAOException, IllegalArgumentException {
		if(t==null) throw new IllegalArgumentException();
		return tischDAO.search(t);
	}

	public void updateTisch(Tisch t) throws DAOException, IllegalArgumentException {
		if(t==null) throw new IllegalArgumentException();
		tischDAO.update(t);
	}

	public void deleteTisch(Tisch t) throws DAOException, IllegalArgumentException {
		if(t==null) throw new IllegalArgumentException();
		tischDAO.delete(t);
	}

	public boolean availableTischnummer(Tisch t) {
		Tisch sTisch=new Tisch();
		sTisch.setNummer(t.getNummer());
		ArrayList<Tisch> tische=null;
		try {
			tische = tischDAO.search(sTisch);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(tische.isEmpty());
	}
	/*
	 * Services fuer Küche
	 */
	public void aktualisiereBearbeitungszeit() throws DAOException, IllegalArgumentException 
	{
		Date now = new Date();	
		Bestellung bestellungStatus = new Bestellung();	
		bestellungStatus.setStatus("bestellt");
		ArrayList<Bestellung> liste = searchBestellung(bestellungStatus);	
		bestellungStatus.setStatus("wirdGekocht");	
		liste.addAll(searchBestellung(bestellungStatus));
		int anzahlBestellungen = liste.size();
		
		for(int i = 0; i < anzahlBestellungen; i++)
		{			
			if(liste.get(i).getBestelldatum() != null)
			{			
				int bearbeitungszeit = (int) ((now.getTime() - liste.get(i).getBestelldatum().getTime())/1000/60);
				
				liste.get(i).setBearbeitungszeit(bearbeitungszeit);
				
				updateBestellung(liste.get(i));		 
			}
		}
	}	
	
	/*
	 * Services fuer Konsumstatistik
	 */
	
	public ArrayList<Konsumstatistik> showPopularProducts() throws DAOException, IllegalArgumentException 
	{
		return konsumstatistikDAO.searchPopularProducts();
	}
}

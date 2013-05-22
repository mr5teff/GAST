package sepm.ss13.gast.domain;

import java.util.ArrayList;
import java.util.Date;


public class Rechnung  {
	private Integer id;
	private Date datum;
	private ArrayList<String> bestellungen;
	private double summeNet;
	private double  summeBrt;
	private int trinkgeld;
	
	
	
// Rechnungskonstruktoren---------------------------------------------
	
	// Alter Konstruktor wegen JDBC
	public Rechnung(int i, Date date, int j) {
		// TODO Auto-generated constructor stub
	}
	
	// Rechnung erstellen aus der DB
	public Rechnung(Integer id, Date datum, String bestellungenString, double summeNet, double summeBrt) {
		this.id = id;
		this.datum = datum;
		this.summeBrt = summeBrt;
		this.summeNet = summeNet;
		this.createProdukte(bestellungenString);
	}

	// Rechnung erstellen aus der Bestellung
	public Rechnung(ArrayList<Bestellung> bestellListe) {
		this.datum = new Date();		
		this.createProdukte(bestellListe);
	}
	
	
	
// Methoden zum erstellen der Produktliste----------------------------
	
	// Produkte aus String basteln
	private void createProdukte(String bestellungenString) {
		this.bestellungen = new ArrayList<String>();
		
		String[] s = bestellungenString.split(",");
		for (int i = 0; i < s.length; i+=2) {
			bestellungen.add(s[i] + "," + s[i+1]);
		}
	}

	// Produkte aus Liste basteln
	private void createProdukte(ArrayList<Bestellung> bestellListe) {
		this.bestellungen = new ArrayList<String>();
		
		summeNet = 0;
		summeBrt = 0;
		
		for (Bestellung p : bestellListe) {
			summeBrt += p.getPreis();
			bestellungen.add(p.getPname() + "," + p.getPreis());
		}	
		summeNet = summeBrt / 1.1;
	}
	
	public String getProdukteString() {
		String result = "";
		for (String s : bestellungen) {
			result += s + ",";
		}
		
		return result;
	}
	
	
	
// Getter u Setter----------------------------------------------------	
	
	public void setTrinkgeld(int trinkgeld) {
		this.trinkgeld = trinkgeld;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public void setSummeBrt(double summeBrt) {
		this.summeBrt = summeBrt;
	}

	public void setSummeNet(double summeNet) {
		this.summeNet = summeNet;
	}

	public void setBestellungen(ArrayList<String> bestellungen) {
		this.bestellungen = bestellungen;
	}

	public ArrayList<String> getBestellungen() {
		return bestellungen;
	}

	public double getSummeNet() {
		return summeNet;
	}

	public double getSummeBrt() {
		return summeBrt;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public int getTrinkgeld() {
		return trinkgeld;
	}
	
}

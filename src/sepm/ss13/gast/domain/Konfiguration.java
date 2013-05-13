package sepm.ss13.gast.domain;

import java.sql.Date;

public class Konfiguration {
	private String name;
	private String adresse;
	private String tel;
	private byte[] logo;
	private int tischanzahl;
	
	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Konfiguration()
	{
		this("", "", "", new byte[1], 0);
	}
	
	public Konfiguration(String name, String adresse, String tel, byte[] logo, int tischanzahl) {
		this.setName(name);
		this.setAdresse(adresse);
		this.setTel(tel);
		this.setLogo(logo);
		this.setTischanzahl(tischanzahl);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public int getTischanzahl() {
		return tischanzahl;
	}

	public void setTischanzahl(int tischanzahl) {
		this.tischanzahl = tischanzahl;
	}
	
	

}

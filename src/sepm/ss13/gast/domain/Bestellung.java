package sepm.ss13.gast.domain;

import java.util.Date;

public class Bestellung {
	private Integer id;
	private Integer tisch;
	private Integer produkt;
	private String pname;
	private int preis;
	private Integer rechnung;
	private String status;
	private boolean deleted;
	private Date bestelldatum;
	private Long bestelldatumLong;
	private Integer bearbeitungszeit;
	private int steuer;

	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Bestellung()
	{
		this(null,null,null,null,0,null,null,false, new Date(0), null, null, 0);
	}
	
	public Bestellung(Integer id, Integer tisch, Integer produkt, String pname, int preis, Integer rechnung, String status, boolean deleted, Date bestelldatum, Long bestelldatumLong, Integer bearbeitungszeit, int steuer) {
		this.setId(id);
		this.setTisch(tisch);
		this.setProdukt(produkt);
		this.setPname(pname);
		this.setPreis(preis);
		this.setRechnung(rechnung);
		this.setStatus(status);
		this.setDeleted(deleted);
		this.setBestelldatum(bestelldatum);
		this.setBestelldatumLong(bestelldatumLong);
		this.setBearbeitungszeit(bearbeitungszeit);
		this.setSteuer(steuer);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTisch() {
		return tisch;
	}

	public void setTisch(Integer tisch) {
		this.tisch = tisch;
	}

	public Integer getProdukt() {
		return produkt;
	}

	public void setProdukt(Integer produkt) {
		this.produkt = produkt;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}

	public Integer getRechnung() {
		return rechnung;
	}

	public void setRechnung(Integer rechnung) {
		this.rechnung = rechnung;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setDeleted(boolean deleted){
		this.deleted = deleted;
	}
	
	public boolean getDeleted(){
		return this.deleted;
	}
	
	public void setBestelldatum(Date bestelldatum){
		this.bestelldatum = bestelldatum;
	}
	
	public Date getBestelldatum(){
		return this.bestelldatum;
	}
	
	public void setBestelldatumLong(Long bestelldatumLong){
		this.bestelldatumLong = bestelldatumLong;
	}
	
	public Long getBestelldatumLong(){
		return this.bestelldatumLong;
	}
	
	public void setBearbeitungszeit(Integer bearbeitungszeit){
		this.bearbeitungszeit = bearbeitungszeit;
	}
	
	public Integer getBearbeitungszeit(){
		return this.bearbeitungszeit;
	}

	public int getSteuer() {
		return steuer;
	}

	public void setSteuer(int steuer) {
		this.steuer = steuer;
	}
}

package sepm.ss13.gast.domain;

import java.sql.Date;

public class Einkauf {
	
	private Integer id;
	private Integer warenId;
	private int menge;
	private Date datum;
	private int preis;
	
	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Einkauf()
	{
		this(null,null, 0, new Date(0), 0);
	}
	
	public Einkauf(Integer id, Integer warenId, int menge, Date datum, int preis) {
		
		this.id = id;
		this.warenId = warenId;
		this.menge = menge;
		this.datum = datum;
		this.preis = preis;
	}
	
	public Integer getId(){
		
		return id;
	}
	
	public void setId(Integer id){
		
		this.id = id;
	}
	
	public Integer getWarenId(){
		
		return warenId;
	}
	
	public void setWarenId(Integer warenId){
		
		this.warenId = warenId;
	}
	
	public int getMenge(){
		
		return menge;
	}
	
	public void setMenge(int menge){
		
		this.menge = menge;
	}
	
	public Date getDatum(){
		
		return datum;
	}
	
	public void setDatum(Date datum){
		
		this.datum = datum;
	}
	
	public int getPreis(){
		
		return preis;
	}
	
	public void setPreis(int preis){
		
		this.preis = preis;
	}
}

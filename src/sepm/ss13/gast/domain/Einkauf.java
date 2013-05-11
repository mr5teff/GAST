package sepm.ss13.gast.domain;

import java.sql.Date;

public class Einkauf {
	
	private int id;
	private int warenId;
	private int menge;
	private Date datum;
	private double preis;
	
	public Einkauf(int id, int warenId, int menge, Date datum, double preis) {
		
		this.id = id;
		this.warenId = warenId;
		this.menge = menge;
		this.datum = datum;
		this.preis = preis;
	}
	
	public int getId(){
		
		return id;
	}
	
	public void setId(int id){
		
		this.id = id;
	}
	
	public int getWarenId(){
		
		return warenId;
	}
	
	public void setWarenId(int warenId){
		
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
	
	public double getPreis(){
		
		return preis;
	}
	
	public void setPreis(double preis){
		
		this.preis = preis;
	}
}

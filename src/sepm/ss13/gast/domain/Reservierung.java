package sepm.ss13.gast.domain;

import java.sql.Date;

public class Reservierung {

	private int id;
	private Date datum;
	private int dauer;
	private int personen;
	private int tischnummer;
	private String name;
	private String telefonnummer;
	
	public Reservierung(int id, Date datum, int dauer, int personen, int tischnummer, String name, String telefonnummer) {
		
		this.id = id;
		this.datum = datum;
		this.dauer = dauer;
		this.personen = personen;
		this.tischnummer = tischnummer;
		this.name = name;
		this.telefonnummer = telefonnummer;
	}
	
	public int getId(){
		
		return id;
	}
	
	public void setId(int id){
		
		this.id = id;
	}
	
	public Date getDatum(){
		
		return datum;
	}
	
	public void setDatum(Date datum){
		
		this.datum = datum;
	}
	
	public int getDauer(){
		
		return dauer;
	}
	
	public void setDauer(int dauer){
		
		this.dauer = dauer;
	}
	
	public int getPersonen(){
		
		return personen;
	}
	
	public void setPersonen(int personen){
		
		this.personen = personen;
	}
	
	public int getTischnummer(){
		
		return tischnummer;
	}
	
	public void setTischnummer(int tischnummer){
		
		this.tischnummer = tischnummer;
	}
	
	public String getName(){
		
		return name;
	}
	
	public void setName(String name){
		
		this.name = name;
	}
	
	public String getTelefonnummer(){
		
		return telefonnummer;
	}
	
	public void setTelefonnummer(String telefonnummer){
		
		this.telefonnummer = telefonnummer;
	}
}

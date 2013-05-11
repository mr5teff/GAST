package sepm.ss13.gast.domain;

public class Ware {
	
	private int id;
	private String bezeichnung;
	private int lagerstand;
	
	public Ware(int id, String bezeichnung, int lagerstand) {
		
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.lagerstand = lagerstand;
	}
	
	public int getId(){
		
		return id;
	}
	
	public void setId(int id){
		
		this.id = id;
	}
	
	public String getBezeichnung(){
		
		return bezeichnung;
	}
	
	public void setBezeichnung(String bezeichnung){
		
		this.bezeichnung = bezeichnung;
	}
	
	public int getLagerstand(){
		
		return lagerstand;
	}
	
	public void setLagerstand(int lagerstand){
		
		this.lagerstand = lagerstand;
	}
}

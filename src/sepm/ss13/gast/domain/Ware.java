package sepm.ss13.gast.domain;

public class Ware {
	
	private Integer id;
	private String bezeichnung;
	private String einheit;
	private int lagerstand;
	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Ware()
	{
		this(null, "","prozent", 0);
	}
	
	public Ware(Integer id, String bezeichnung, String einheit, int lagerstand) {
		
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.einheit = einheit;
		this.lagerstand = lagerstand;
	}
	
	public Integer getId(){
		
		return id;
	}
	
	public void setId(Integer id){
		
		this.id = id;
	}
	
	public String getBezeichnung(){
		
		return bezeichnung;
	}
	
	public void setBezeichnung(String bezeichnung){
		
		this.bezeichnung = bezeichnung;
	}
	
	public String getEinheit(){
		return this.einheit;
	}
	
	public void setEinheit(String einheit){
		this.einheit = einheit;
	}
	
	public int getLagerstand(){
		
		return lagerstand;
	}
	
	public void setLagerstand(int lagerstand){
		
		this.lagerstand = lagerstand;
	}
}

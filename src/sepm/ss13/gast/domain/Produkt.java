package sepm.ss13.gast.domain;

public class Produkt {
	private int id;
	private String name;
	private int kategorie;
	private int preis;
	
	
	/**
	 * Default Konstruktor. "Leeres" Dom�nenelement
	 */
	public Produkt()
	{
		this(-1, "", 0, 0);
	}
	
	public Produkt(int id, String name, int kategorie, int preis) {
		this.setId(id);
		this.setName(name);
		this.setKategorie(kategorie);
		this.setPreis(preis);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKategorie() {
		return kategorie;
	}

	public void setKategorie(int kategorie) {
		this.kategorie = kategorie;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}
	
	

}

package sepm.ss13.gast.domain;

public class Produkt {
	private int id;
	private String name;
	private int kategorie;
	private int preis;
	private boolean deleted;
	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Produkt()
	{
		this(-1, "", -1, 0,false);
	}
	
	public Produkt(int id, String name, int kategorie, int preis) {
		this.setId(id);
		this.setName(name);
		this.setKategorie(kategorie);
		this.setPreis(preis);
		this.setDeleted(false);
	}
	
	public Produkt(int id, String name, int kategorie, int preis,boolean deleted) {
		this.setId(id);
		this.setName(name);
		this.setKategorie(kategorie);
		this.setPreis(preis);
		this.setDeleted(deleted);
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
	
	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
		

}

package sepm.ss13.gast.domain;

public class Produkt {
	private Integer id;
	private String name;
	private Integer kategorie;
	private int preis;
	private boolean deleted;
	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Produkt()
	{
		this(null, "", null, 0,false);
	}
	
	public Produkt(Integer id, String name, Integer kategorie, int preis) {
		this(id,name,kategorie,preis,false);
	}
	
	public Produkt(Integer id, String name, Integer kategorie, int preis,boolean deleted) {
		this.setId(id);
		this.setName(name);
		this.setKategorie(kategorie);
		this.setPreis(preis);
		this.setDeleted(deleted);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getKategorie() {
		return kategorie;
	}

	public void setKategorie(Integer kategorie) {
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

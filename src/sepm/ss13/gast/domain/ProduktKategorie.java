package sepm.ss13.gast.domain;

public class ProduktKategorie {
	private Integer id;
	private String bezeichnung;
	private String kurzbezeichnung;
	private boolean deleted;
	private int steuer;

	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public ProduktKategorie()
	{
		this(null, "", "", false, 0);
	}
	
	public ProduktKategorie(Integer id, String bezeichnung, String kurzbezeichnung, boolean deleted, int steuer) {
		this.setId(id);
		this.setBezeichnung(bezeichnung);
		this.setKurzbezeichnung(kurzbezeichnung);
		this.setDeleted(deleted);
		this.setSteuer(steuer);
	} 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getKurzbezeichnung() {
		return kurzbezeichnung;
	}

	public void setKurzbezeichnung(String kurzbezeichnung) {
		this.kurzbezeichnung = kurzbezeichnung;
	}
	
	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getSteuer() {
		return steuer;
	}

	public void setSteuer(int steuer) {
		this.steuer = steuer;
	}
	
	
}

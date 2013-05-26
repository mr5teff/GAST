package sepm.ss13.gast.domain;

public class ProduktKategorie {
	private Integer id;
	private String bezeichnung;
	private String kurzbezeichnung;
	private boolean deleted;

	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public ProduktKategorie()
	{
		this(null, "", "", false);
	}
	
	public ProduktKategorie(Integer id, String bezeichnung, String kurzbezeichnung) {
		this(id, bezeichnung, kurzbezeichnung, false);
	}
	
	public ProduktKategorie(Integer id, String bezeichnung, String kurzbezeichnung, boolean deleted) {
		this.setId(id);
		this.setBezeichnung(bezeichnung);
		this.setKurzbezeichnung(kurzbezeichnung);
		this.setDeleted(deleted);
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
	
	
}

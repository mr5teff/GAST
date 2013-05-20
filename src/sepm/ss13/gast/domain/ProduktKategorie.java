package sepm.ss13.gast.domain;

public class ProduktKategorie {
	private Integer id;
	private String bezeichnung;
	private String kurzbezeichnung;

	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public ProduktKategorie()
	{
		this(null, "", "");
	}
	
	public ProduktKategorie(Integer id, String bezeichnung, String kurzbezeichnung) {
		this.setId(id);
		this.setBezeichnung(bezeichnung);
		this.setKurzbezeichnung(kurzbezeichnung);
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
	
	
}

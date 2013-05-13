package sepm.ss13.gast.domain;

public class ProduktKategorie {
	private int id;
	private String bezeichnung;
	private String kurzbezeichnung;

	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public ProduktKategorie()
	{
		this(-1, "", "");
	}
	
	public ProduktKategorie(int id, String bezeichnung, String kurzbezeichnung) {
		this.setId(id);
		this.setBezeichnung(bezeichnung);
		this.setKurzbezeichnung(kurzbezeichnung);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

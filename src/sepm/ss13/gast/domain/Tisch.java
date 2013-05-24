package sepm.ss13.gast.domain;

public class Tisch {
	private Integer id;
	private Integer nummer;
	private Integer plaetze;
	private String beschreibung;
	private Boolean deleted;
	
	public Tisch() {
		this(null,0,0,"",false);
	}
	
	public Tisch(Integer id,Integer nummer, Integer plaetze, String beschreibung, Boolean deleted) {
		this.id=id;
		this.nummer=nummer;
		this.plaetze=plaetze;
		this.beschreibung=beschreibung;
		this.deleted=deleted;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getNummer() {
		return nummer;
	}
	public void setNummer(Integer nummer) {
		this.nummer = nummer;
	}
	
	public Integer getPlaetze() {
		return plaetze;
	}
	public void setPlaetze(Integer plaetze) {
		this.plaetze = plaetze;
	}
	
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}

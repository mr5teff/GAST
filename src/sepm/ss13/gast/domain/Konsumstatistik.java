package sepm.ss13.gast.domain;

public class Konsumstatistik 
{
	private String produktname;
	private Integer absatzmenge;

	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Konsumstatistik()
	{
		this(null,null);
	}
	
	public Konsumstatistik(String produktname, Integer absatzmenge) 
	{
		this.setProduktname(produktname);
		this.setAbsatzmenge(absatzmenge);
	}

	public String getProduktname() {
		return produktname;
	}

	public void setProduktname(String produktname) {
		this.produktname = produktname;
	}
	
	public Integer getAbsatzmenge() {
		return absatzmenge;
	}

	public void setAbsatzmenge(Integer absatzmenge) {
		this.absatzmenge = absatzmenge;
	}
}

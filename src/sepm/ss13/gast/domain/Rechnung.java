package sepm.ss13.gast.domain;

import java.util.Date;

public class Rechnung {
	private Integer id;
	private Date datum;
	private int trinkgeld;
	
	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Rechnung()
	{
		this(null, new Date(0), 0);
	}
	
	public Rechnung(Integer id, Date datum, int trinkgeld) {
		this.setId(id);
		this.setDatum(datum);
		this.setTrinkgeld(trinkgeld);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getTrinkgeld() {
		return trinkgeld;
	}

	public void setTrinkgeld(int trinkgeld) {
		this.trinkgeld = trinkgeld;
	}
	
	

}

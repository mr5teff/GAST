package sepm.ss13.gast.domain;

public class Bestellung {
	private int id;
	private int tisch;
	private int produkt;
	private int preis;
	private int rechnung;
	private String status;
	private String pname;
	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Bestellung()
	{
		this(-1, -1, 0, 0, 0, "-1", "-1");
	}
	
	public Bestellung(int id, int tisch, int produkt, int preis, int rechnung, String status, String pname) {
		this.setId(id);
		this.setTisch(tisch);
		this.setProdukt(produkt);
		this.setPreis(preis);
		this.setRechnung(rechnung);
		this.setStatus(status);
		this.setPname(pname);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTisch() {
		return tisch;
	}

	public void setTisch(int tisch) {
		this.tisch = tisch;
	}

	public int getProdukt() {
		return produkt;
	}

	public void setProdukt(int produkt) {
		this.produkt = produkt;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}

	public int getRechnung() {
		return rechnung;
	}

	public void setRechnung(int rechnung) {
		this.rechnung = rechnung;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	
	

}

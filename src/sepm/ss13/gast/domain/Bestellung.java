package sepm.ss13.gast.domain;

public class Bestellung {
	private Integer id;
	private Integer tisch;
	private Integer produkt;
	private int preis;
	private Integer rechnung;
	private String status;
	private boolean deleted;
	private String pname;
	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Bestellung()
	{
		this(null,null,null,null,0,null,null,false);
	}
	
	public Bestellung(Integer id, Integer tisch, Integer produkt, String pname, int preis, Integer rechnung, String status, boolean deleted) {
		this.setId(id);
		this.setTisch(tisch);
		this.setProdukt(produkt);
		this.setPname(pname);
		this.setPreis(preis);
		this.setRechnung(rechnung);
		this.setStatus(status);
		this.setDeleted(deleted);
	}
	
	public Bestellung(Integer id, Integer tisch, Integer produkt, String pname, int preis, Integer rechnung, String status) {
		this(id,tisch,produkt,pname,preis,rechnung,status,false);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTisch() {
		return tisch;
	}

	public void setTisch(Integer tisch) {
		this.tisch = tisch;
	}

	public Integer getProdukt() {
		return produkt;
	}

	public void setProdukt(Integer produkt) {
		this.produkt = produkt;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}

	public Integer getRechnung() {
		return rechnung;
	}

	public void setRechnung(Integer rechnung) {
		this.rechnung = rechnung;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setDeleted(boolean deleted){
		this.deleted = deleted;
	}
	
	public boolean getDeleted(){
		return this.deleted;
	}

}

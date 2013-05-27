package sepm.ss13.gast.domain;

public class Konfiguration {
	private String name;
	private String adresse;
	private String tel;
	private byte[] logo;
	
	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Konfiguration()
	{
		this("", "", "", new byte[1]);
	}
	
	public Konfiguration(String name, String adresse, String tel, byte[] logo) {
		this.setName(name);
		this.setAdresse(adresse);
		this.setTel(tel);
		this.setLogo(logo);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	

}

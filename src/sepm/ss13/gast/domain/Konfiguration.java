package sepm.ss13.gast.domain;

public class Konfiguration {
	private String name;
	private String adresse;
	private String tel;
	private byte[] logo;
	private int timerIntervall;
	
	
	/**
	 * Default Konstruktor. "Leeres" Domänenelement
	 */
	public Konfiguration()
	{
		this("", "", "", new byte[1],0);
	}
	
	public Konfiguration(String name, String adresse, String tel, byte[] logo, int timerIntervall) {
		this.setName(name);
		this.setAdresse(adresse);
		this.setTel(tel);
		this.setLogo(logo);
		this.setTimerIntervall(timerIntervall);
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
	
	public int getTimerIntervall() {
		return timerIntervall;
	}

	public void setTimerIntervall(int timerIntervall) {
		this.timerIntervall = timerIntervall;
	}

}

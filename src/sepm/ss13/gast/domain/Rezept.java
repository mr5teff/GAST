package sepm.ss13.gast.domain;

public class Rezept {
	private Integer w;
	private Integer p;
	private String wname;
	private int menge;
	
	public Rezept(){
		this.w = null;
		this.p = null;
		this.menge = -1;
		this.wname = null;
	}
	
	public Rezept(Integer p,Integer w,int menge,String wname){
		this.w = w;
		this.p = p;
		this.menge = menge;
		this.wname = wname;
	}
	
	public String getWName(){
		return this.wname;
	}
	
	public void setWareId(Integer w){
		this.w = w;
	}
	
	public Integer getWareId(){
		return this.w;
	}
	
	public void setProduktId(Integer p){
		this.p = p;
	}
	
	public Integer getProduktId(){
		return this.p;
	}
	
	public void setMenge(int menge){
		this.menge = menge;
	}
	
	public int getMenge(){
		return this.menge;
	}
	
}

package model;

public class Gelaxka {
	private int mota;
	
	public Gelaxka(int pMota) { //mota: 0:hutsik 1:gurea 2:etsaia 3:tiro
		this.mota=pMota;
	}
	public String getMota(){
		String motaS=null;
		if(mota==0) {
			motaS="hutsik";
		}else if(mota==1){
			motaS="gurea";
		}else if(mota==2) {
			motaS="etsaia";
		}else if(mota==3){
			motaS="tiroa";
		}
		return motaS;
	}
}

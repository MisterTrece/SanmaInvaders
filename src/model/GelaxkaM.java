package model;

import java.util.Observable;

public class GelaxkaM extends Observable{
	private int mota;
	
	public GelaxkaM(int pMota) { //mota: 0:hutsik 1:gurea 2:etsaia 3:tiro
		this.mota=pMota;
	}
	
	public void aldatuMota(int pMota) {
		this.mota=pMota;
		notifyObservers();
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
	
	public String getMotaIrudi(){
		String motaS=null;
		if(mota==0) {
			motaS="-";
		}else if(mota==1){
			motaS="X";
		}else if(mota==2) {
			motaS="O";
		}else if(mota==3){
			motaS="|";
		}
		return motaS;
	}
}

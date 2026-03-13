package model;

import java.util.Observable;

public class GelaxkaM extends Observable{
	private int mota;
	
	public GelaxkaM(int pMota) { //mota: 0:hutsik 1:gurea 2:etsaia 3:tiro
		this.mota=pMota;
	}
	
	public void aldatuMota(int pMota) {
		this.mota=pMota;
		setChanged();
		notifyObservers(mota);
	}
	
	public int getMota() {
		return this.mota;
	}
}

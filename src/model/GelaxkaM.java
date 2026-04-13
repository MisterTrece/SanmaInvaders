package model;

import java.util.Observable;

public class GelaxkaM extends Observable{
	
	private Egoera mota;
	
	public GelaxkaM(Egoera pMota) {
		this.mota=pMota;
	}
	
	public void aldatuMota(Egoera pMota) {
		this.mota=pMota;
		setChanged();
		notifyObservers(pMota.getId());
	}
	
	public int getMota() {
		return this.mota.getId();
	}
	
	public Egoera getEgoera() {
		return this.mota;
	}
}

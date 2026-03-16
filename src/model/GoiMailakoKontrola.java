package model;

import java.util.Observable;

public class GoiMailakoKontrola extends Observable {
	private static GoiMailakoKontrola nKontrola = null;
	
	private GoiMailakoKontrola() {
		
	}
	
	public static GoiMailakoKontrola getKontrola() {
		if(nKontrola==null) {
			nKontrola = new GoiMailakoKontrola();
		}
		return nKontrola;
	}
	
	public void partidaHasi() {
		Espazio.getEspazioEMA().hasi();
		setChanged();
		notifyObservers("INIT");
	}
	
	public void partidaGaldu() {
		Espazio.getEspazioEMA().bukatu();
		setChanged();
		notifyObservers("LOSE");
	}
	
	public void partidaIrabazi() {
		Espazio.getEspazioEMA().bukatu();
		setChanged();
		notifyObservers("WIN");
	}
}

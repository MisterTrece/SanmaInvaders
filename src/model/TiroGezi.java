package model;

public class TiroGezi implements TiroPortaera {
	private int tiroKop = 30;
	@Override
	public void tiroEgin(int pX, int pY) {
		long orain = System.currentTimeMillis();

		if(orain - Espazio.getEspazioEMA().getAzkenTiro() < 200) {
		    return;
		}
		Espazio.getEspazioEMA().setAzkenTiro(orain);
		
		if(tiroKop>0) {
			
			NodoTiro tiro = new NodoTiro("Gezi");
			Espazio.getEspazioEMA().tiroSortu(tiro);
			tiroKop--;
		}
	}
}

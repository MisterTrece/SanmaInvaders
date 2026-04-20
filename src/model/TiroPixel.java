package model;

public class TiroPixel implements TiroPortaera {

	@Override
	public void tiroEgin(int pX, int pY) {
		long orain = System.currentTimeMillis();

		if(orain - Espazio.getEspazioEMA().getAzkenTiro() < 100) {
		    return;
		}
		Espazio.getEspazioEMA().setAzkenTiro(orain);
		
		NodoTiro tiro = new NodoTiro("Pixel");
		Espazio.getEspazioEMA().tiroSortu(tiro);
	}

}

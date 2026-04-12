package model;

public class TiroPixel implements TiroPortaera {

	@Override
	public void tiroEgin(int pX, int pY) {
		Espazio.getEspazioEMA().tiroSortu(pX,pY);
	}

}

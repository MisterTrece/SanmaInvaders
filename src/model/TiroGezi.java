package model;

public class TiroGezi implements TiroPortaera {

	@Override
	public void tiroEgin(int pX, int pY) {
		Espazio.getEspazioEMA().tiroSortu(pX,pY-1);
		Espazio.getEspazioEMA().tiroSortu(pX+1,pY);
		Espazio.getEspazioEMA().tiroSortu(pX-1,pY);
	}

}

package model;

public class TiroErronbo implements TiroPortaera {
	private int tiroKop = 20;
	@Override
	public void tiroEgin(int pX, int pY) {
		long orain = System.currentTimeMillis();

		if(orain - Espazio.getEspazioEMA().getAzkenTiro() < 400) {
		    return;
		}
		Espazio.getEspazioEMA().setAzkenTiro(orain);
		
		if(pX-2<0 || pX+2>99) {
			return;
		}
		
		if(tiroKop>0) {
			
			NodoTiro tiro = new NodoTiro("Erronbo");
			Espazio.getEspazioEMA().tiroSortu(tiro);
		}
	}
	@Override
	public void tiroKopMur() {
		tiroKop--;
	}
}

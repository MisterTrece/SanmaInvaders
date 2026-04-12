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
			Espazio.getEspazioEMA().tiroSortu(pX,pY-1);
			
			Espazio.getEspazioEMA().tiroSortu(pX+1,pY);
			Espazio.getEspazioEMA().tiroSortu(pX-1,pY);
			
			tiroKop--;
		}
	}
}

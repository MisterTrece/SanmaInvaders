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
		
		if(tiroKop>0) {
			Espazio.getEspazioEMA().tiroSortu(pX,pY-4);
			
			Espazio.getEspazioEMA().tiroSortu(pX,pY-3);
			Espazio.getEspazioEMA().tiroSortu(pX+1,pY-3);
			Espazio.getEspazioEMA().tiroSortu(pX-1,pY-3);
			
			Espazio.getEspazioEMA().tiroSortu(pX,pY-2);
			Espazio.getEspazioEMA().tiroSortu(pX+1,pY-2);
			Espazio.getEspazioEMA().tiroSortu(pX-1,pY-2);
			Espazio.getEspazioEMA().tiroSortu(pX+2,pY-2);
			Espazio.getEspazioEMA().tiroSortu(pX-2,pY-2);
			
			Espazio.getEspazioEMA().tiroSortu(pX,pY-1);
			Espazio.getEspazioEMA().tiroSortu(pX+1,pY-1);
			Espazio.getEspazioEMA().tiroSortu(pX-1,pY-1);
			
			Espazio.getEspazioEMA().tiroSortu(pX,pY);
			
			tiroKop--;
		}
	}
}

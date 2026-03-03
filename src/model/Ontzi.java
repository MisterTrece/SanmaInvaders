package model;

public abstract class Ontzi {
	int x;
	int y;
	int mota;
	public Ontzi() {
		
	}
	public void sartuMatrizean() {
		Espazio matrize = Espazio.getEspazioEMA();
		matrize.sartu(x,y,mota);
	}
}

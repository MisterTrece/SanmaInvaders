package model;

public abstract class Ontzi {
	int x;
	int y;
	int mota;
	public Ontzi() {
		
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void sartuMatrizean() {
		Espazio matrize = Espazio.getEspazioEMA();
		matrize.sartu(x,y,mota);
	}
}

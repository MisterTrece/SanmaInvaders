package model;

public class OntziTxarra extends Pixel{
	public OntziTxarra() {
		this.y=5;
		this.x= (int)(Math.random()*98)+1;
		this.mota=2;
	}
	public void birkalkulatuX() {
		this.x= (int)(Math.random()*98)+1;
	}
}

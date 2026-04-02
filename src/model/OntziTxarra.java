package model;

public class OntziTxarra extends Pixel{
	public OntziTxarra() {
		super(((int)(Math.random()*98)+1),5);
		this.mota=2;
	}
	public void birkalkulatuX() {
		this.x= (int)(Math.random()*98)+1;
	}	
}

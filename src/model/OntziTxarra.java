package model;

public class OntziTxarra extends Ontzi{
	public OntziTxarra() {
		this.y=3;
		this.x= (int)(Math.random()*24)+1;
		this.mota=2;
	}
	public void birkalkulatuX() {
		this.x= (int)(Math.random()*24)+1;
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
}

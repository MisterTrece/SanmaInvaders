package model;

public abstract class Pixel implements ElementuPixel{
	protected int x;
	protected int y;
	protected int mota;
	public Pixel(int pX, int pY) {
		this.x=pX;
		this.y=pY;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	@Override
	public void mugituPixel(int pX, int pY) {
		// TODO Auto-generated method stub
		
	}
	
}

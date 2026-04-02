package model;

import java.util.ArrayList;
import java.util.Iterator;

public class NodoOntziOn implements ElementuPixel{

	private ArrayList<ElementuPixel> pixelak;
	
	protected int x;
	protected int y;
	
	public NodoOntziOn() {
		this.x=55;
		this.y=50;
		
		pixelak = new ArrayList<ElementuPixel>();
		pixelak.add(JokalariFabrika.getJokalariFabrika().createOntziOna(1, 50, 55));
		pixelak.add(JokalariFabrika.getJokalariFabrika().createOntziOna(1, 50, 54));
		pixelak.add(JokalariFabrika.getJokalariFabrika().createOntziOna(1, 49, 55));
		pixelak.add(JokalariFabrika.getJokalariFabrika().createOntziOna(1, 51, 55));
		
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void aldatuKords(int px, int py) {
		this.x=px;
		this.y=py;
	}
	
	/*
	public void addElementu(ElementuPixel pEl) {
		pixelak.add(pEl);
	}
	*/
	@Override
	public void mugituPixel(int pX, int pY) {
		
		Iterator<ElementuPixel> itr = pixelak.iterator();
		while(itr.hasNext()) {
			ElementuPixel pixel = itr.next();
			if(pixel.getX()+pX>=100 || pixel.getX()+pX<0 || pixel.getY()+pY>=60 || pixel.getY()+pY<0) {
				return;
			}
		}
		
		ArrayList<int[]> posizioak = new ArrayList<int[]>();
	    itr = pixelak.iterator();
		while(itr.hasNext()) {
			ElementuPixel pixel = itr.next();
			int[] posi = new int[2];
			posi[0]=pixel.getX();
			posi[1]=pixel.getY();
			posizioak.add(posi);
		}
		
		for(int[] pos : posizioak) {
	        Espazio.getEspazioEMA().getGelaxka(pos[0], pos[1]).aldatuMota(new Hutsik());
	    }
		
		itr = pixelak.iterator();
		while(itr.hasNext()) {
			ElementuPixel pixel = itr.next();
			pixel.mugituPixel(pX, pY);
		}
		
		this.x = x + pX;
		this.y = y + pY;
	}
}

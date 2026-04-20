package model;

import java.util.ArrayList;
import java.util.Iterator;

public class NodoTiro implements ElementuPixel{
	
	private ArrayList<ElementuPixel> pixelak;
	
	private int x;			//beheko pixela
	private int y;			//beheko pixela 
	private boolean desagertu = false;
	private boolean atera = false;
	
	public NodoTiro(String pMota) {
		
		x = Espazio.getEspazioEMA().getGurea().getX();
		y = Espazio.getEspazioEMA().getGurea().getY()-2;
		pixelak = new ArrayList<ElementuPixel>();
		if(pMota.equals("Pixel")) {
			pixelak.add(new Tiro(x,y));
		}else if (pMota.equals("Gezi")) {
			pixelak.add(new Tiro(x,y-1));
			
			pixelak.add(new Tiro(x+1,y));
			pixelak.add(new Tiro(x-1,y));
		}else if (pMota.equals("Erronbo")) {
			pixelak.add(new Tiro(x,y-4));
			
			pixelak.add(new Tiro(x-1,y-3));
			pixelak.add(new Tiro(x,y-3));
			pixelak.add(new Tiro(x+1,y-3));
			
			pixelak.add(new Tiro(x-2,y-2));
			pixelak.add(new Tiro(x-1,y-2));
			pixelak.add(new Tiro(x,y-2));
			pixelak.add(new Tiro(x+1,y-2));
			pixelak.add(new Tiro(x+2,y-2));
			
			pixelak.add(new Tiro(x-1,y-1));
			pixelak.add(new Tiro(x,y-1));
			pixelak.add(new Tiro(x+1,y-1));
			
			pixelak.add(new Tiro(x,y));
		}
	}
	
	public boolean desagertu() {
		return this.desagertu;
	}
	
	public boolean atera() {
		return this.atera;
	}
	
	private void garbitu() {
		for (ElementuPixel p: pixelak) {
			Espazio.getEspazioEMA().getGelaxka(p.getX(), p.getY()).aldatuMota(new Hutsik());
		}
	}
	
	@Override
	public void mugituPixel(int pX, int pY) {
		Iterator<ElementuPixel> itr = pixelak.iterator();
		while(itr.hasNext()) {
			ElementuPixel pixel = itr.next();
			if(pixel.getY()+pY>=60) {
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
			Tiro tiro = (Tiro) pixel;
			if(tiro.desagertu()) {
				desagertu=true;
				garbitu();
				return;
			}
			if(tiro.atera() && tiro.getX()==x && tiro.getY()==y) {
				atera=true;
			}
			
		}
		this.y = y + pY;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getId() {
		return -1;
	}

}

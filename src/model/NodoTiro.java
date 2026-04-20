package model;

import java.util.ArrayList;
import java.util.Iterator;

public class NodoTiro implements ElementuPixel{
	
	private ArrayList<ElementuPixel> pixelak;
	
	private int x;			
	private int botY;		//beheko pixela 
	private int topY;		//goiko pixela
	
	private boolean desagertu = false;
	private boolean atera = false;
	
	public NodoTiro(String pMota) {
		
		x = Espazio.getEspazioEMA().getGurea().getX();
		botY = Espazio.getEspazioEMA().getGurea().getY()-3;
		pixelak = new ArrayList<ElementuPixel>();
		if(pMota.equals("Pixel")) {
			topY = botY;
			
			pixelak.add(new Tiro(x,botY));
		}else if (pMota.equals("Gezi")) {
			topY = botY - 1;
			
			pixelak.add(new Tiro(x,botY-1));
			
			pixelak.add(new Tiro(x+1,botY));
			pixelak.add(new Tiro(x-1,botY));
		}else if (pMota.equals("Erronbo")) {
			topY = botY - 4;
					
			pixelak.add(new Tiro(x,botY-4));
			
			pixelak.add(new Tiro(x-1,botY-3));
			pixelak.add(new Tiro(x,botY-3));
			pixelak.add(new Tiro(x+1,botY-3));
			
			pixelak.add(new Tiro(x-2,botY-2));
			pixelak.add(new Tiro(x-1,botY-2));
			pixelak.add(new Tiro(x,botY-2));
			pixelak.add(new Tiro(x+1,botY-2));
			pixelak.add(new Tiro(x+2,botY-2));
			
			pixelak.add(new Tiro(x-1,botY-1));
			pixelak.add(new Tiro(x,botY-1));
			pixelak.add(new Tiro(x+1,botY-1));
			
			pixelak.add(new Tiro(x,botY));
		}
		/*
		for(ElementuPixel p: pixelak) {
			if(Espazio.getEspazioEMA().getGelaxka(p.getX(), p.getY()).getMota()==2) {
				Espazio.getEspazioEMA().etsaiaHil(p.getX(), p.getY());
				Espazio.getEspazioEMA().getGurea().tiroKopMur();
				garbitu();
			}
		}*/
	}
	
	public boolean desagertu() {
		return this.desagertu;
	}
	
	public void desagertarazi() {
		this.desagertu=true;
		garbitu();
	}
	
	public boolean atera() {
		return this.atera;
	}
	
	private void garbitu() {
		for (ElementuPixel p: pixelak) {
			Espazio.getEspazioEMA().getGelaxka(p.getX(), p.getY()).aldatuMota(new Hutsik());
		}
	}
	
	public boolean baduPixela(int pX, int pY) {
		boolean badu = false;
		for(ElementuPixel p: pixelak) {
			if(p.getX()==pX && p.getY()==pY) {
				badu=true;
				break;
			}
		}
		return badu;
	}
	
	public void erakutsi() {
		for(ElementuPixel p: pixelak) {
			Espazio.getEspazioEMA().getGelaxka(p.getX(), p.getY()).aldatuMota(new TiroEgoera());
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
			if(tiro.atera() && tiro.getX()==x && tiro.getY()==botY) {
				atera=true;
			}
			
		}
		this.botY = botY + pY;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.botY;
	}
	
	public int getTopY() {
		return this.topY;
	}

	@Override
	public int getId() {
		return -1;
	}

}

package model;

import java.util.ArrayList;
import java.util.Iterator;

public class NodoOntziTxarra implements ElementuPixel{

	private ArrayList<ElementuPixel> pixelak;
	
	private int x;
	private int y;
	private int id;
	
	public NodoOntziTxarra(int pId) {
		
		this.id = pId;
		// Espaziontziaren pixel blokeak (Composite patroia) 
		pixelak = new ArrayList<ElementuPixel>();
		pixelak.add(new OntziTxarra(-1,-1, pId));
		int pX = pixelak.get(0).getX();
		pixelak.add(new OntziTxarra(pX+1,5, pId));
		pixelak.add(new OntziTxarra(pX-1,5,pId));
		pixelak.add(new OntziTxarra(pX,6,pId));
		
		this.x = pX;
		this.y = 5;
	}
	
	public void birkalkulatuX() {
		pixelak.removeAll(pixelak);
		pixelak.add(new OntziTxarra(-1,-1,id));
		int pX = pixelak.get(0).getX();
		pixelak.add(new OntziTxarra(pX+1,5,id));
		pixelak.add(new OntziTxarra(pX-1,5,id));
		pixelak.add(new OntziTxarra(pX,6,id));
		
		this.x = pX;
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
			if(pixel.getX()+pX>=100 || pixel.getX()+pX<0 || pixel.getY()+pY<0) {
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

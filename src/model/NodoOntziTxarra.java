package model;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

public class NodoOntziTxarra implements ElementuPixel{

	private ArrayList<ElementuPixel> pixelak;
	
	private int x;
	private int y;
	private int id;
	private boolean borratu = false;
	
	public NodoOntziTxarra(int pId) {
		
		this.id = pId; 
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
	
	@Override
	public int getId() {
		return this.id;
	}
	
	public ArrayList<ElementuPixel> getPixelak(){
		return this.pixelak;
	}
	
	@Override
	public void mugituPixel(int pX, int pY) {
		
		Iterator<ElementuPixel> itr = pixelak.iterator();
		while(itr.hasNext()) {
			ElementuPixel pixel = itr.next();
			if(pixel.getX()+pX>=100 || pixel.getX()+pX<0 || pixel.getY()+pY<0) {
				return;
			}
			if(pixel.getY()+pY<60) {
				if(Espazio.getEspazioEMA().getGelaxka(pixel.getX()+pX, pixel.getY()+pY).getMota()==2) {
					if(Espazio.getEspazioEMA().etsaiKolisioa(id,pixel.getX()+pX,pixel.getY()+pY)) {
						return;
					}
				}
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
		boolean hilda = false;
		while(itr.hasNext()) {
			ElementuPixel pixel = itr.next();
			pixel.mugituPixel(pX, pY);
			if (pixel.getY()+pY==60) {
				if(pixel.getX()==x && pixel.getY()==y) {
					Timer timerEND = new Timer(190, e -> {
						GoiMailakoKontrola.getKontrola().partidaGaldu();
				    });
					timerEND.setRepeats(false);
					timerEND.start();
				}
			}
			OntziTxarra a = (OntziTxarra) pixel;
			if(a.getHilda()) {
				hilda = true;
			}
		}
		
		if(hilda) {
		    for (ElementuPixel p : pixelak) {
		        Espazio.getEspazioEMA().getGelaxka(p.getX(), p.getY()).aldatuMota(new Hutsik());
		    }
		    Espazio.getEspazioEMA().etsaiaHil(x, y);
		    return;
		}
		
		this.x = x + pX;
		this.y = y + pY;
	}
	
	public void eztanda() {
		for (int i=0; i<pixelak.size();i++) {
			ElementuPixel pixel = pixelak.get(i);
			Espazio.getEspazioEMA().getGelaxka(pixel.getX(), pixel.getY()).aldatuMota(new Eztanda());
		}
	}
	
	public void borratuBehar() {
		this.borratu = true;
	}
	
	public boolean borratuKonprobatu() {
		if (borratu) {
			for (ElementuPixel p : pixelak) {
				if(Espazio.getEspazioEMA().getGelaxka(p.getX(), p.getY()).getMota()==2) {
					Espazio.getEspazioEMA().getGelaxka(p.getX(), p.getY()).aldatuMota(new Eztanda());
				}
			}
		}
		return this.borratu;
	}
}

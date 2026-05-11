package model;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class NodoOntziOn implements ElementuPixel{

	private ArrayList<ElementuPixel> pixelak;
	
    private TiroPortaera tiroMota;
    private ArrayList<TiroPortaera> tiroMotaGuztiak;

	private int x;
	private int y;
	
	private int mota;
	
	protected NodoOntziOn(int pMota, TiroPortaera pTiro) {
		this.mota = pMota;
		this.x=55;
		this.y=50;
		tiroMotaGuztiak = new ArrayList<>();
		tiroMotaGuztiak.add(new TiroPixel());
		tiroMotaGuztiak.add(pTiro);
        
		pixelak = new ArrayList<ElementuPixel>();
		pixelak.add(new OntziOna(50, 55));
		pixelak.add(new OntziOna(50, 54));
		pixelak.add(new OntziOna(49, 55));
		pixelak.add(new OntziOna(51, 55));
        
        tiroMota = tiroMotaGuztiak.get(0);
	}
	
	public int getMotaOntzi() {
		return this.mota;
	}
	
	public void setMotaOntzi(int pMota) {
		this.mota = pMota;
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
	
	public void tiroKopMur() {
		tiroMota.tiroKopMur();
	}
	
	@Override
	public int getId() {
		return -1;
	}
	
	@Override
	public void mugituPixel(int pX, int pY) {
		
		/*
		Iterator<ElementuPixel> itr = pixelak.iterator();
		while(itr.hasNext()) {
			ElementuPixel pixel = itr.next();
			if(pixel.getX()+pX>=100 || pixel.getX()+pX<0 || pixel.getY()+pY>=60 || pixel.getY()+pY<0) {
				return;
			}
		}
		*/
		boolean pixelKanpo = pixelak.stream()
				.anyMatch(p -> 
			        p.getX() + pX >= 100 || 
			        p.getX() + pX < 0 || 
			        p.getY() + pY >= 60 || 
			        p.getY() + pY < 0
			    );

		if (pixelKanpo) {
			   return;
		}
		
		
		ArrayList<int[]> posizioak = new ArrayList<int[]>();
		/*
	    itr = pixelak.iterator();
		while(itr.hasNext()) {
			ElementuPixel pixel = itr.next();
			int[] posi = new int[2];
			posi[0]=pixel.getX();
			posi[1]=pixel.getY();
			posizioak.add(posi);
		}
		*/
		pixelak.stream()
			.forEach(p ->{
				int[] posi = new int[2];
				posi[0] = p.getX();
				posi[1] = p.getY();
				posizioak.add(posi);}
					);
		/*
		for(int[] pos : posizioak) {
	        Espazio.getEspazioEMA().getGelaxka(pos[0], pos[1]).aldatuMota(new Hutsik());
	    }
		*/
		
		posizioak.stream().forEach(p -> Espazio.getEspazioEMA().getGelaxka(p[0], p[1]).aldatuMota(new Hutsik()));
		
		/*
		itr = pixelak.iterator();
		while(itr.hasNext()) {
			ElementuPixel pixel = itr.next();
			pixel.mugituPixel(pX, pY);
		}
		*/
		
		pixelak.stream().forEach(p -> p.mugituPixel(pX, pY));
		
		this.x = x + pX;
		this.y = y + pY;
	}
	
	public void tiroEgin() {
		this.tiroMota.tiroEgin(this.x, this.y-3);
	}
	
	public void aldatuTiroMota() {
		if(tiroMotaGuztiak.indexOf(tiroMota)+1>=tiroMotaGuztiak.size()) {
			tiroMota = tiroMotaGuztiak.get(0);
		}else {
			tiroMota = tiroMotaGuztiak.get(tiroMotaGuztiak.indexOf(tiroMota)+1);
		}
	}
	
	protected void tiroGehitu(TiroPortaera pTiro) {
		tiroMotaGuztiak.add(pTiro);
	}
}

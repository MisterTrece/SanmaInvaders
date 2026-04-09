package model;

import java.util.ArrayList;
import java.util.Iterator;

public class NodoOntziOn implements ElementuPixel{

	private ArrayList<ElementuPixel> pixelak;
	
    // Strategy patroiaren atributuak: uneko arma eta eskuragarri dauden armen zerrenda
    protected TiroEstrategia unekoa;
    protected ArrayList<TiroEstrategia> motaGuztiak;
    protected int armaIndizea = 0;

	private int x;
	private int y;
	
	public NodoOntziOn(int pMota) {
		this.x=55;
		this.y=50;
		
        // Espaziontziaren pixel blokeak (Composite patroia) JokalariFabrika erabiliz sortu
		pixelak = new ArrayList<ElementuPixel>();
		pixelak.add(JokalariFabrika.getJokalariFabrika().createOntziOna(pMota, 50, 55));
		pixelak.add(JokalariFabrika.getJokalariFabrika().createOntziOna(pMota, 50, 54));
		pixelak.add(JokalariFabrika.getJokalariFabrika().createOntziOna(pMota, 49, 55));
		pixelak.add(JokalariFabrika.getJokalariFabrika().createOntziOna(pMota, 51, 55));

        // Ontzi motaren arabera (pMota), bat edo beste tiro motak edukiko ditu (Strategy esleipena)
        motaGuztiak = new ArrayList<>();
        motaGuztiak.add(new TiroPixelEstrategia()); // Ontzi guztiek daukate Pixel tiroa
        
        switch(pMota) {
            case 1: // Blue motako espaziontzia (Pixel + Erronbo)
                motaGuztiak.add(new TiroErronboEstrategia());
                break;
            case 2: // Green motako espaziontzia (Pixel + Gezia)
                motaGuztiak.add(new TiroGeziEstrategia());
                break;
            case 3: // Red motako espaziontzia (Pixel + Gezia + Erronbo)
                motaGuztiak.add(new TiroGeziEstrategia());
                motaGuztiak.add(new TiroErronboEstrategia());
                break;
        }
        
        // Hasierako arma lehenetsia beti pixela izango da
        unekoa = motaGuztiak.get(0);
	}
	
    // Teklatuarekin uneko arma aldatzeko 
    public void aldatuTiroMota() {
        armaIndizea++;
        if (armaIndizea >= motaGuztiak.size()) {
            armaIndizea = 0;
        }
        unekoa = motaGuztiak.get(armaIndizea);
    }

    // Uneko armarekin tiro egiteko metodoa
    public ArrayList<Tiro> disparatu() {
        if (unekoa.baduMuniziorik()) {
            unekoa.munizioaGastatu();
            return unekoa.tiroEgin(this.x, this.y); 
        }
        return new ArrayList<>(); 
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
		return -1;
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

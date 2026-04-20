package model;

import javax.swing.Timer;

public class OntziTxarra extends Pixel{
	private int id;
	private boolean hilda = false;
	
	public OntziTxarra(int pX, int pY, int pId) {
		super(((int)(Math.random() * 96) + 2),5);
		this.id = pId;
		if(pX>=0 && pY>=0) {
			this.x=pX;
			this.y=pY;
		}
		this.mota=2;
	}
	
	public boolean getHilda() {
		return this.hilda;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void birkalkulatuX() {
		this.x= (int)(Math.random() * 96) + 2;
	}
	@Override
	public void mugituPixel(int pX, int pY) {
		int berriaX = this.x + pX;
		int berriaY = this.y + pY;
		int gureX = Espazio.getEspazioEMA().getGurea().getX();
		int gureY = Espazio.getEspazioEMA().getGurea().getY();
		
		int maxX = 100 - 1;
		
		
		if (berriaX < 0 || berriaX > maxX) {
			
		}else if(berriaY<=60) {
	
			
			
			if(berriaY!=60) { 																									//matrizean dago
				if(Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).getMota()==3) {
					hilda = true;
				}
				if(berriaX==gureX && berriaY==gureY || Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).getMota()==1) {		//jokalariarekin topa
					GoiMailakoKontrola.getKontrola().partidaGaldu();
				}
				if(Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).getMota()==2) {											//beste etsaiarekin topa - ez gainjarri
					return;
				}
				Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).aldatuMota(new Etsaia());			
				this.x=berriaX;
				this.y=berriaY;	
				if(hilda) {
					Espazio.getEspazioEMA().etsaiaHil(this.x, this.y);
					Espazio.getEspazioEMA().getTiro(berriaX, berriaY).desagertarazi();
				}
			}
		}
	}	
}

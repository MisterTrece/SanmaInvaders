package model;

public class Tiro extends Pixel{
	private boolean desagertu = false;
	private boolean atera = false;
	public Tiro(int x, int y) {
		super(x,y);
		if(this.mota!=-1) {
			this.mota=3;
		}
		if(y>=0) {
			if(Espazio.getEspazioEMA().getGelaxka(x, y).getMota()==2) {
				desagertu=true;
			}
			if(this.mota!=-1) {
				Espazio.getEspazioEMA().getGelaxka(x, y).aldatuMota(new TiroEgoera());
			}
			if(desagertu) {
				Espazio.getEspazioEMA().etsaiaHil(x, y);
			}
		}
		else {
			atera = true;
		}
	}
	
	public boolean desagertu() {
		return this.desagertu;
	}
	
	public void desagertarazi() {
		this.desagertu=true;
	}
	
	public boolean atera() {
		return this.atera;
	}
	
	@Override
	public void mugituPixel(int pX, int pY) {
		int berriaY = y - 1;
			
			if (berriaY < 0) {
				atera  = true;
				return;
			}
			
			if(Espazio.getEspazioEMA().getGelaxka(x, berriaY).getMota()==2) {
				Espazio.getEspazioEMA().etsaiaHil(x,berriaY);
				desagertu = true;
				
				return;
			}
			
			if(Espazio.getEspazioEMA().getGelaxka(x, berriaY).getMota()==1) {
				desagertu = true;
				GoiMailakoKontrola.getKontrola().partidaGaldu();
				return;
			}
			
			Espazio.getEspazioEMA().getGelaxka(x, berriaY).aldatuMota(new TiroEgoera());
			this.y=berriaY;
		}

	@Override
	public int getId() {
		return -1;
	}
}

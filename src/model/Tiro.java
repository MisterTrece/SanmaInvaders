package model;

public class Tiro extends Pixel{
	private boolean desagertu = false;
	
	public Tiro(int x, int y) {
		super(x,y);
		}
	public boolean desagertu() {
		return this.desagertu;
	}
	
	@Override
	public void mugituPixel(int pX, int pY) {
		int maxY = 60 - 1;
		int berriaY = y - 1;
			if (this.y >= 0 && this.y <= maxY) {
				Espazio.getEspazioEMA().getGelaxka(x, y).aldatuMota(new Hutsik());
			}
			
			if (berriaY < 0) {
				desagertu = true;
				return;
			}

			if(Espazio.getEspazioEMA().getGelaxka(x, berriaY).getMota()==2) {
				Espazio.getEspazioEMA().etsaiaHil(x,berriaY);
				//Espazio.getEspazioEMA().getGelaxka(x, berriaY).aldatuMota(new Eztanda());
				desagertu = true;
				return;
			}
			
			if(Espazio.getEspazioEMA().getGelaxka(x, berriaY).getMota()==1) {
				desagertu = true;
				GoiMailakoKontrola.getKontrola().partidaGaldu();
				return;
			}
			
			Espazio.getEspazioEMA().getGelaxka(x, berriaY).aldatuMota(new TiroMota());
			this.y=berriaY;
		}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return -1;
	}
}

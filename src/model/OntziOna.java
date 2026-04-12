package model;

public abstract class OntziOna extends Pixel {

	public OntziOna(int pX, int pY) {
		super(pX,pY);
		this.mota=1;
	}
	
	@Override
	public int getId() {
		return -1;
	}
	
	@Override
	public void mugituPixel(int pX, int pY) {
		int berriaX = this.x + pX;
		int berriaY = this.y + pY;

		if(Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).getMota()!=2 && Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).getMota()!=3) {
			Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).aldatuMota(new Gurea());

			this.x = berriaX;
			this.y = berriaY;
		}else {
				GoiMailakoKontrola.getKontrola().partidaGaldu();
		}
	}
}

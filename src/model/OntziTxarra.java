package model;

import javax.swing.Timer;

public class OntziTxarra extends Pixel{
	public OntziTxarra(int pX, int pY) {
		super(((int)(Math.random()*97)+2),5);
		if(pX>=0 && pY>=0) {
			this.x=pX;
			this.y=pY;
		}
		this.mota=2;
	}
	public void birkalkulatuX() {
		this.x= (int)(Math.random()*98)+1;
	}
	@Override
	public void mugituPixel(int pX, int pY) {
		int berriaX = this.x + pX;
		int berriaY = this.y + pY;
		int gureX = Espazio.getEspazioEMA().getGurea().x;
		int gureY = Espazio.getEspazioEMA().getGurea().y;
		
		int maxX = 100 - 1;
		
		
		if (berriaX < 0 || berriaX > maxX) {
			
		}else if(berriaY<=60) {
	
			
			
			if(berriaY!=60) { 								//matrizean dago
				if(Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).getMota()==3) {
					//etsaiaHil(berriaX,berriaY);
					Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).aldatuMota(new Eztanda());
					return;
				}
				if(berriaX==gureX && berriaY==gureY || Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).getMota()==1) {				//jokalariarekin topa
					GoiMailakoKontrola.getKontrola().partidaGaldu();
				}
				if(Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).getMota()==2) {			//beste etsaiarekin topa - ez gainjarri
					return;
				}
				Espazio.getEspazioEMA().getGelaxka(this.x,this.y).aldatuMota(new Hutsik());
				Espazio.getEspazioEMA().getGelaxka(berriaX,berriaY).aldatuMota(new Etsaia());			
				this.x=berriaX;
				this.y=berriaY;
				
			}else { 													//matrizetik kanpo
				Espazio.getEspazioEMA().getGelaxka(this.x,this.y).aldatuMota(new Hutsik());
				/////
				GoiMailakoKontrola.getKontrola().partidaGaldu();
				/////
				/*Timer timerEND = new Timer(1000, e -> {
					GoiMailakoKontrola.getKontrola().partidaGaldu();
			    });
			    
			timerEND.setRepeats(false);
			timerEND.start();	
			*/
			}
		}
	}	
}

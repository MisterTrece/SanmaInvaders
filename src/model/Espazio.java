package model;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

public class Espazio{
	private GelaxkaM[][] matrizea = new GelaxkaM[60][100];
	private OntziOna gurea;
	private ArrayList<OntziTxarra> etsaiak;
	private int etsaiKop;
	private static Espazio nEspazio = null;
	private ArrayList<Tiro> tiroak;
	private Timer jokoTimer;
	private int gameTick = 0;
	
	private long azkenTiroa = 0;
	
	private Espazio() {
		for(int i=0;i<matrizea.length;i++) {
			for(int j=0;j<matrizea[i].length;j++) {
				matrizea[i][j]= new GelaxkaM(new Hutsik());
			}
		}
		gurea= Fabrika.getFabrika().createOntziOna(1);
		GelaxkaM gureG = new GelaxkaM(new Gurea());
		matrizea[55][50]= gureG;
		gurea.x = 50;
		gurea.y = 55;
	
		tiroak = new ArrayList<Tiro>();
		etsaiak= new ArrayList<OntziTxarra>();
		etsaiKop = (int)(Math.random()*5)+4;
		for (int i=0;i<etsaiKop;i++) {
			OntziTxarra e = new OntziTxarra();
			etsaiak.add(e);
		}
		Iterator<OntziTxarra> itr = etsaiak.iterator();
		boolean[] etsaiBool = new boolean[100];
		while(itr.hasNext()) {
			OntziTxarra o = itr.next();
			boolean jarrita = false;
			while(!jarrita) {
				if(!etsaiBool[o.getX()] && !etsaiBool[o.getX()+1] && !etsaiBool[o.getX()-1]) {
					sartu(o.getX(), o.getY(), new Etsaia());
					etsaiBool[o.getX()]=true;
					etsaiBool[o.getX()+1]=true;
					etsaiBool[o.getX()-1]=true;
					jarrita = true;
				}else {
					o.birkalkulatuX();
				}
			}
		}
	}
	
	public static Espazio getEspazioEMA() {
		if(nEspazio==null) {
			nEspazio = new Espazio();
		}
		return nEspazio;
	}

	public void hasi() {
		Timer timerEND = new Timer(500, e -> {
	        if (jokoTimer == null) {
	        	jokoTimer = new Timer(50, ev -> {
	        		Espazio.getEspazioEMA().mugituTiroak();
	        		gameTick++;
	        		if(gameTick==4) {
	        			gameTick=0;
	        			Espazio.getEspazioEMA().mugituEtsaiak();
	        		}
	        	});
	        }
	        jokoTimer.start();
	    });
	 timerEND.setRepeats(false);
	 timerEND.start();
		
	}
	
	public void bukatu() {
		jokoTimer.stop();
		nEspazio = null;
	}
	
	public void sartu(int x,int y,Egoera mota) {
		GelaxkaM g = new GelaxkaM(mota);
		matrizea[y][x]=g;
	}
	
	public GelaxkaM getGelaxka(int x, int y) {
		return matrizea[y][x];
	}
	

	public void mugituOntzia(int dx, int dy) {
		int berriaX = gurea.x + dx;
		int berriaY = gurea.y + dy;

		int maxY = matrizea.length - 1;
		int maxX = matrizea[0].length - 1;

		// Matrizaren mugetatik kanpo ez mugitzea ziurtatu
		if (berriaX < 0 || berriaX > maxX || berriaY < 0 || berriaY > maxY) {
			return; // no se mueve
		}

		// espaziontzia kendu bere posizio zaharretik
		matrizea[gurea.y][gurea.x].aldatuMota(new Hutsik());

		// posizio berria
		if(matrizea[berriaY][berriaX].getMota()!=2) {
			matrizea[berriaY][berriaX].aldatuMota(new Gurea());

			gurea.x = berriaX;
			gurea.y = berriaY;
		}else {
			GoiMailakoKontrola.getKontrola().partidaGaldu();
		}
		
	}

	public void tiro() {

		long orain = System.currentTimeMillis();

		if(orain - azkenTiroa < 100) {
		    return;
		}

		azkenTiroa = orain;
		
		int tiroX = gurea.x;
		int tiroY = gurea.y - 2;

		if (tiroY < 0 || tiroY >= matrizea.length) {
			return;
		}

		if (matrizea[tiroY][tiroX].getMota()==3) {
			return;
		}
		if(matrizea[tiroY][tiroX].getMota()==2) {
			etsaiaHil(tiroX,tiroY);
			matrizea[tiroY][tiroX].aldatuMota(new Eztanda());
			return;
		}

		matrizea[tiroY][tiroX].aldatuMota(new TiroMota());
		tiroak.add(new Tiro(tiroX,tiroY));
	}

	public void mugituTiroak() {
		if (tiroak.isEmpty()) {
			return;
		}

		int maxY = matrizea.length - 1;
		
		Iterator<Tiro> itr = tiroak.iterator();
		while (itr.hasNext()) {
			Tiro tiro = itr.next();
			int x = tiro.x;
			int y = tiro.y;
			if (y >= 0 && y <= maxY) {
				matrizea[y][x].aldatuMota(new Hutsik());
			}

			int berriaY = y - 1;

			if (berriaY < 0) {
				itr.remove();
				continue;
			}

			if(matrizea[berriaY][x].getMota()==2) {
				etsaiaHil(x,berriaY);
				matrizea[berriaY][x].aldatuMota(new Eztanda());
				itr.remove();
				continue;
			}
			matrizea[berriaY][x].aldatuMota(new TiroMota());
			tiro.y=berriaY;
		}
		
	}
	
	private void etsaiaHil(int x, int y) {
		matrizea[y][x].aldatuMota(new Hutsik());
		Iterator<OntziTxarra> itr = etsaiak.iterator();
		boolean aurkituta = false;
		while(!aurkituta && itr.hasNext()) {
			OntziTxarra etsai = itr.next();
			if (etsai.x==x && etsai.y==y) {
				itr.remove();
				aurkituta=true;
				etsaiKop--;
			}
		}
		if(etsaiKop==0) {
			Timer timerEND = new Timer(1500, e -> {
				GoiMailakoKontrola.getKontrola().partidaIrabazi();
		    });
		 timerEND.setRepeats(false);
		 timerEND.start();
		}
	}
	
	public void mugituEtsaiak() {
		boolean kanpo=false;
		Iterator<OntziTxarra> itr = etsaiak.iterator();
		while(itr.hasNext()) {
			OntziTxarra etsaia = itr.next();
			int etsaiMug = (int)(Math.random()*3);
			switch(etsaiMug) {
				case 0:
					kanpo=mugituOntziEtsai(etsaia,0,1);		//beherantz
					break;
				case 1:
					kanpo=mugituOntziEtsai(etsaia,-1,0);	//ezkerrerantz
					break;
				case 2:
					kanpo=mugituOntziEtsai(etsaia,1,0);		//eskuinerantz
					break;
			}
			if(kanpo) {
				itr.remove();
			}
		}
	}
	
	private boolean mugituOntziEtsai(OntziTxarra pEtsai,int xMug,int yMug) {
		int berriaX = pEtsai.x + xMug;
		int berriaY = pEtsai.y + yMug;
		int gureX = gurea.x;
		int gureY = gurea.y;
		
		int maxX = matrizea[0].length - 1;
		
		boolean kanpo = false;
		
		if (berriaX < 0 || berriaX > maxX) {
			
		}else if(berriaY<=matrizea.length) {
		
			
			
			if(berriaY!=matrizea.length) { 								//matrizean dago
				if(matrizea[berriaY][berriaX].getMota()==3) {
					etsaiaHil(berriaX,berriaY);
					matrizea[berriaY][berriaX].aldatuMota(new Eztanda());
					return false;
				}
				if(berriaX==gurea.x && berriaY==gurea.y) {				//jokalariarekin topa
					GoiMailakoKontrola.getKontrola().partidaGaldu();
				}
				if(matrizea[berriaY][berriaX].getMota()==2) {			//beste etsaiarekin topa - ez gainjarri
					return false;
				}
				matrizea[pEtsai.y][pEtsai.x].aldatuMota(new Hutsik());
				matrizea[berriaY][berriaX].aldatuMota(new Etsaia());			
				pEtsai.x=berriaX;
				pEtsai.y=berriaY;
				
			}else { 													//matrizetik kanpo
				matrizea[pEtsai.y][pEtsai.x].aldatuMota(new Hutsik());
				kanpo = true;
				Timer timerEND = new Timer(1000, e -> {
					GoiMailakoKontrola.getKontrola().partidaGaldu();
			    });
			timerEND.setRepeats(false);
			timerEND.start();	
			}
		}
		return kanpo;
	}
}
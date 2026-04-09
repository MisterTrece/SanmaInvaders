package model;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

public class Espazio{
	private GelaxkaM[][] matrizea = new GelaxkaM[60][100];
	private NodoOntziOn gurea;
	private ArrayList<NodoOntziTxarra> etsaiak;
	//private OntziOna gurea;
	//private ArrayList<OntziTxarra> etsaiak;
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

		tiroak = new ArrayList<Tiro>();
		etsaiak= new ArrayList<NodoOntziTxarra>();
		
		
	}
	
	public static Espazio getEspazioEMA() {
		if(nEspazio==null) {
			nEspazio = new Espazio();
		}
		return nEspazio;
	}

	public void hasi() {
		
		//gurea= JokalariFabrika.getJokalariFabrika().createOntziOna(1,55,50);
		
		gurea=new NodoOntziOn(1);
		
		GelaxkaM gureG = new GelaxkaM(new Gurea());
		matrizea[55][50]= gureG;
		matrizea[54][50]=new GelaxkaM(new Gurea());
		matrizea[55][51]=new GelaxkaM(new Gurea());
		matrizea[55][49]=new GelaxkaM(new Gurea());
		
		gurea.aldatuKords(50, 55);
		
		etsaiKop = (int)(Math.random()*5)+4;
		for (int i=0;i<etsaiKop;i++) {
			NodoOntziTxarra e = new NodoOntziTxarra(i);
			etsaiak.add(e);
		}
		
		Iterator<NodoOntziTxarra> itr = etsaiak.iterator();
		boolean[] etsaiBool = new boolean[100];
		while(itr.hasNext()) {
			NodoOntziTxarra o = itr.next();
			boolean jarrita = false;
			
			while(!jarrita) {
				if(!etsaiBool[o.getX()] && !etsaiBool[o.getX()+1] && !etsaiBool[o.getX()-1] && !etsaiBool[o.getX()+2] && !etsaiBool[o.getX()-2]) {
					matrizea[o.getY()][o.getX()]=new GelaxkaM(new Etsaia());
					matrizea[o.getY()][o.getX()+1]=new GelaxkaM(new Etsaia());
					matrizea[o.getY()][o.getX()-1]=new GelaxkaM(new Etsaia());
					matrizea[o.getY()+1][o.getX()]=new GelaxkaM(new Etsaia());
					etsaiBool[o.getX()]=true;
					etsaiBool[o.getX()+1]=true;
					etsaiBool[o.getX()+2]=true;
					etsaiBool[o.getX()-1]=true;
					etsaiBool[o.getX()-2]=true;
					jarrita = true;
				}else {
					o.birkalkulatuX();
				}
			}
		}
		
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
		//nEspazio = null;
	}
	
	public void sartu(int x,int y,Egoera mota) {
		GelaxkaM g = new GelaxkaM(mota);
		matrizea[y][x]=g;
	}
	
	public GelaxkaM getGelaxka(int x, int y) {
		return matrizea[y][x];
	}

	public void mugituOntzia(int pX, int pY) {
		gurea.mugituPixel(pX, pY);
		
	}
	

	public void tiro() {

		long orain = System.currentTimeMillis();

		if(orain - azkenTiroa < 100) {
		    return;
		}

		azkenTiroa = orain;
		
		int tiroX = gurea.getX();
		int tiroY = gurea.getY() - 3;

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
		Iterator<NodoOntziTxarra> itr = etsaiak.iterator();
		boolean aurkituta = false;
		while(!aurkituta && itr.hasNext()) {
			NodoOntziTxarra etsai = itr.next();
			if (etsai.getX()==x && etsai.getY()==y) {
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
		Iterator<NodoOntziTxarra> itr = etsaiak.iterator();
		while(itr.hasNext()) {
			NodoOntziTxarra etsaia = itr.next();
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
	private boolean mugituOntziEtsai(NodoOntziTxarra pEtsai,int pX,int pY) {
		pEtsai.mugituPixel(pX, pY);
		/*if(pEtsai.y>=59 && pY==1) {
			return true;
		}*/
		return false;
	}
	
	
	public NodoOntziOn getGurea() {
		return gurea;
	}
}
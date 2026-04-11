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
		
		Timer timerHasi = new Timer(500, e -> {
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
	 timerHasi.setRepeats(false);
	 timerHasi.start();
		
	}
	
	public void bukatu() {
		jokoTimer.stop();
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
		Iterator<Tiro> itr = tiroak.iterator();
		while (itr.hasNext()) {
			Tiro tiro = itr.next();
			tiro.mugituPixel(0,-1);
			if(tiro.desagertu()) {
				itr.remove();
			}
		}
	}
	
	public void etsaiaHil(int pX, int pY) {
		int nX = 0;
		int nY = 0;
		Iterator<NodoOntziTxarra> itr = etsaiak.iterator();
		boolean aurkituta = false;
		while(!aurkituta && itr.hasNext()) {
			NodoOntziTxarra etsai = itr.next();
			ArrayList<ElementuPixel> pixelak = etsai.getPixelak();
			for(int i=0; i<pixelak.size();i++) {
				if(pixelak.get(i).getX()==pX && pixelak.get(i).getY()==pY){	
					nX = etsai.getX();
					nY = etsai.getY();
					itr.remove();
					aurkituta = true;
					etsaiKop--;
				}
			}
		}
		matrizea[nY][nX].aldatuMota(new Eztanda());
		matrizea[nY][nX-1].aldatuMota(new Eztanda());
		matrizea[nY][nX+1].aldatuMota(new Eztanda());
		matrizea[nY+1][nX].aldatuMota(new Eztanda());
		
		if(etsaiKop==0) {
			Timer timerEND = new Timer(1500, e -> {
				GoiMailakoKontrola.getKontrola().partidaIrabazi();
		    });
			timerEND.setRepeats(false);
			timerEND.start();
		}
	}
	
	public void etsaiaHil2(int x, int y) {
		matrizea[y][x].aldatuMota(new Eztanda());
		
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
		return false;
	}
	
	public boolean etsaiKolisioa(int pId, int pX, int pY) {
		boolean kolisioa = false;	
		boolean aurkituta = false;
		Iterator<NodoOntziTxarra> itr = etsaiak.iterator();
		while(!aurkituta && itr.hasNext()) {
			NodoOntziTxarra etsai = itr.next();
			ArrayList<ElementuPixel> pixelak = etsai.getPixelak();
			for(int i=0; i<pixelak.size();i++) {
				if(pixelak.get(i).getX()==pX && pixelak.get(i).getY()==pY){
					if(pixelak.get(i).getId()!=pId) {
						kolisioa = true;
					}
					aurkituta = true;
				}
			}
		}
		return kolisioa;
	}
	
	public NodoOntziOn getGurea() {
		return gurea;
	}
}
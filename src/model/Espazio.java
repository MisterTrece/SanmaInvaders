package model;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

public class Espazio{
	private static final int PUNTUAZIOA_ETSAIA = 100;
	private GelaxkaM[][] matrizea = new GelaxkaM[60][100];
	private NodoOntziOn gurea;
	private int ontziMota;
	private ZailtasunMaila zailtasunMaila = ZailtasunMaila.NORMALA;
	private ArrayList<NodoOntziTxarra> etsaiak;
	private int etsaiKop;
	private int puntuazioa = 0;
	private boolean puntuazioaGordeta = false;
	private static Espazio nEspazio = null;
	private ArrayList<NodoTiro> tiroak;
	private long azkenTiroa = 0;
	private Timer jokoTimer;
	private int gameTick = 0;
	
	
	private Espazio() {
		for(int i=0;i<matrizea.length;i++) {
			for(int j=0;j<matrizea[i].length;j++) {
				matrizea[i][j]= new GelaxkaM(new Hutsik());
			}
		}

		tiroak = new ArrayList<NodoTiro>();
		etsaiak= new ArrayList<NodoOntziTxarra>();
		
		
	}
	
	public static Espazio getEspazioEMA() {
		if(nEspazio==null) {
			nEspazio = new Espazio();
		}
		return nEspazio;
	}

	public void hasi() {
		puntuazioa = 0;
		puntuazioaGordeta = false;
		gurea = sortuJokalari(ontziMota);
		
		GelaxkaM gureG = new GelaxkaM(new Gurea());
		matrizea[55][50]= gureG;
		matrizea[54][50]=new GelaxkaM(new Gurea());
		matrizea[55][51]=new GelaxkaM(new Gurea());
		matrizea[55][49]=new GelaxkaM(new Gurea());
		
		gurea.aldatuKords(50, 55);
		
		etsaiKop = zailtasunMaila.getEtsaiMin()
				+ (int) (Math.random() * (zailtasunMaila.getEtsaiMax() - zailtasunMaila.getEtsaiMin() + 1));
		for (int i=0;i<etsaiKop;i++) {
			NodoOntziTxarra e = new NodoOntziTxarra(i);
			etsaiak.add(e);
		}
		
		/*Iterator<NodoOntziTxarra> itr = etsaiak.iterator();
		boolean[] etsaiBool = new boolean[100];
		while(itr.hasNext()) {
			NodoOntziTxarra o = itr.next();
			boolean jarrita = false;
			
			while(!jarrita) {
				if(!etsaiBool[o.getX()] && !etsaiBool[o.getX()+1] && !etsaiBool[o.getX()-1] && !etsaiBool[o.getX()+2] && !etsaiBool[o.getX()-2]) {
					//matrizea[o.getY()][o.getX()]=new GelaxkaM(new Etsaia());
					//matrizea[o.getY()][o.getX()+1]=new GelaxkaM(new Etsaia());
					//matrizea[o.getY()][o.getX()-1]=new GelaxkaM(new Etsaia());
					//matrizea[o.getY()+1][o.getX()]=new GelaxkaM(new Etsaia());
					
					matrizea[o.getY()][o.getX()] = new GelaxkaM(new Etsaia());
					
					matrizea[o.getY()-1][o.getX()] = new GelaxkaM(new Etsaia());
					matrizea[o.getY()-1][o.getX()+1] = new GelaxkaM(new Etsaia());
					matrizea[o.getY()-1][o.getX()-1] = new GelaxkaM(new Etsaia());
					
					matrizea[o.getY()][o.getX()+1] = new GelaxkaM(new Etsaia());
					matrizea[o.getY()][o.getX()-1] = new GelaxkaM(new Etsaia());
					
					matrizea[o.getY()+1][o.getX()+1] = new GelaxkaM(new Etsaia());
					matrizea[o.getY()+1][o.getX()-1] = new GelaxkaM(new Etsaia());
					
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
		}*/
		
		boolean[] etsaiBool = new boolean[100];
		etsaiak.stream()
			.forEach(ets -> {
				boolean jarrita = false;
				
				while(!jarrita) {
					if(!etsaiBool[ets.getX()] && !etsaiBool[ets.getX()+1] && !etsaiBool[ets.getX()-1] && !etsaiBool[ets.getX()+2] && !etsaiBool[ets.getX()-2]) {
						
						matrizea[ets.getY()][ets.getX()] = new GelaxkaM(new Etsaia());
						
						matrizea[ets.getY()-1][ets.getX()] = new GelaxkaM(new Etsaia());
						matrizea[ets.getY()-1][ets.getX()+1] = new GelaxkaM(new Etsaia());
						matrizea[ets.getY()-1][ets.getX()-1] = new GelaxkaM(new Etsaia());
						
						matrizea[ets.getY()][ets.getX()+1] = new GelaxkaM(new Etsaia());
						matrizea[ets.getY()][ets.getX()-1] = new GelaxkaM(new Etsaia());
						
						matrizea[ets.getY()+1][ets.getX()+1] = new GelaxkaM(new Etsaia());
						matrizea[ets.getY()+1][ets.getX()-1] = new GelaxkaM(new Etsaia());
						
						etsaiBool[ets.getX()]=true;
						etsaiBool[ets.getX()+1]=true;
						etsaiBool[ets.getX()+2]=true;
						etsaiBool[ets.getX()-1]=true;
						etsaiBool[ets.getX()-2]=true;
						jarrita = true;
					}else {
						ets.birkalkulatuX();
					}
				}
			});
		
		Timer timerHasi = new Timer(500, e -> {
	        if (jokoTimer == null) {
	        	jokoTimer = new Timer(50, ev -> {
	        		Espazio.getEspazioEMA().mugituTiroak();
	        		gameTick++;
	        		if (gameTick >= zailtasunMaila.getEtsaiMugimenduTick()) {
	        			gameTick = 0;
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
	
	public void setOntziMota(int pMota) {
		this.ontziMota = pMota;
	}

	public String getOntziMotaIzena() {
		switch (ontziMota) {
			case 1:
				return "Blue";
			case 2:
				return "Green";
			case 3:
				return "Red";
			default:
				return "Red";
		}
	}

	public void setZailtasunMaila(ZailtasunMaila pMaila) {
		this.zailtasunMaila = pMaila;
	}

	public ZailtasunMaila getZailtasunMaila() {
		return this.zailtasunMaila;
	}

	private int getPuntuazioPerEtsai() {
		switch (zailtasunMaila) {
			case ERRAZA:
				return PUNTUAZIOA_ETSAIA;
			case NORMALA:
				return PUNTUAZIOA_ETSAIA * 2;
			case ZAILA:
				return PUNTUAZIOA_ETSAIA * 3;
			default:
				return PUNTUAZIOA_ETSAIA;
		}
	}

	public int getPuntuazioa() {
		return puntuazioa;
	}

	public boolean isPuntuazioaGordeta() {
		return puntuazioaGordeta;
	}

	public void gordePuntuazioa(String pIzena) {
		if (puntuazioaGordeta) {
			return;
		}
		new KonexioDB().puntuazioakInsertatu(pIzena, getOntziMotaIzena(), puntuazioa);
		puntuazioaGordeta = true;
	}
	
	private NodoOntziOn sortuJokalari(int pOntziMota) {
		return JokalariFabrika.getJokalariFabrika().createOntziOna(ontziMota);
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
	
	public void aldatuTiroMota() {
		gurea.aldatuTiroMota();
	}
	
	public void tiro() {
		gurea.tiroEgin();
	}
	
	public long getAzkenTiro() {
		return this.azkenTiroa;
	}
	
	public void setAzkenTiro(long pTime) {
		this.azkenTiroa=pTime;
	}
	
	public void tiroSortu(NodoTiro pTiro) {
		int tiroX = pTiro.getX();
		int tiroBotY = pTiro.getY();
		int tiroTopY = pTiro.getTopY();
		if (tiroBotY < 0) {
			return;
		}
		gurea.tiroKopMur();
		pTiro.erakutsi();
		tiroak.add(pTiro);
	}

	public void mugituTiroak() {
		if (tiroak.isEmpty()) {
			return;
		}
		/*Iterator<NodoTiro> itr = tiroak.iterator();
		while (itr.hasNext()) {
			NodoTiro tiro = itr.next();
			if(tiro.desagertu() || (tiro.atera())) {
				itr.remove();
				return;
			}
			tiro.mugituPixel(0,-1);
			if(tiro.desagertu()) {
				itr.remove();
			}
		}
		*/
		ArrayList<NodoTiro> tiroakKopia = new ArrayList<NodoTiro>(tiroak);
		tiroakKopia.stream()
		.forEach(t -> {
			if(t.desagertu() || (t.atera())) {
				tiroak.remove(t);
				return;
			}
			t.mugituPixel(0,-1);
			if(t.desagertu()) {
				tiroak.remove(t);
			}
		});
	}
	
	public NodoTiro getTiro(int pX, int pY) {
		return tiroak.stream()
				.filter(n -> n.baduPixela(pX, pY))
				.findFirst()
				.orElse(null);
		
		/*
		NodoTiro tiroa = null;
		boolean aurkituta = false;
		Iterator<NodoTiro> itr = tiroak.iterator();
		while(itr.hasNext() && !aurkituta) {
			NodoTiro tiro = itr.next();
			if(tiro.baduPixela(pX, pY)) {
				tiroa = tiro;
			}
		}
		return tiroa;
		*/
	}
	
	public void etsaiaHil(int pX, int pY) {
		/*Iterator<NodoOntziTxarra> itr = etsaiak.iterator();
		boolean aurkituta = false;
		while(!aurkituta && itr.hasNext()) {
			NodoOntziTxarra etsai = itr.next();
			if(!etsai.borratuKonprobatu()) {
				ArrayList<ElementuPixel> pixelak = etsai.getPixelak();
				for(int i=0; i<pixelak.size();i++) {
					if(pixelak.get(i).getX()==pX && pixelak.get(i).getY()==pY){
						aurkituta = true;
						etsaiKop--;
						puntuazioa += getPuntuazioPerEtsai();
						break;
					}
				}
				if (aurkituta) {
					etsai.eztanda();
					etsai.borratuBehar();
				}
			}
			
		}*/
		
		etsaiak.stream()
			.filter(et -> !et.borratuKonprobatu())
			.forEach(ets -> {
				boolean aurkituta = false;
				ArrayList<ElementuPixel> pixelak = ets.getPixelak();
				for(int i=0; i<pixelak.size();i++) {
					if(pixelak.get(i).getX()==pX && pixelak.get(i).getY()==pY){
						aurkituta = true;
						etsaiKop--;
						puntuazioa += getPuntuazioPerEtsai();
						break;
					}
				}
				if (aurkituta) {
					ets.eztanda();
					ets.borratuBehar();
				}
			});
		
		if(etsaiKop==0) {
			Timer timerEND = new Timer(1500, e -> {
				GoiMailakoKontrola.getKontrola().partidaIrabazi();
		    });
			timerEND.setRepeats(false);
			timerEND.start();
		}
	}
	
	public void mugituEtsaiak() {
		/*Iterator<NodoOntziTxarra> itr = etsaiak.iterator();
		while(itr.hasNext()) {
			NodoOntziTxarra etsaia = itr.next();
			if(etsaia.borratuKonprobatu()) {
				itr.remove();
			} else {
				int etsaiMug = (int)(Math.random()*3);
				switch(etsaiMug) {
					case 0:
						mugituOntziEtsai(etsaia,0,1);		//beherantz
						break;
					case 1:
						mugituOntziEtsai(etsaia,-1,0);	//ezkerrerantz
						break;
					case 2:
						mugituOntziEtsai(etsaia,1,0);		//eskuinerantz
						break;
				}
			}
		}*/
		ArrayList<NodoOntziTxarra> etsaiakKopia = new ArrayList<NodoOntziTxarra>(etsaiak);
		etsaiakKopia.stream()
			.forEach(e -> {
				if(e.borratuKonprobatu()) {
					etsaiak.remove(e);
				} else {
					int etsaiMug = 0;
					if(e.getY() >= gurea.getY()) {
						etsaiMug = 0;
					}else if(e.getX() > gurea.getX()) {
						etsaiMug = (int)(Math.random()*2);
					}else if(e.getX() < gurea.getX()) {
						etsaiMug = (int)(Math.random()*3);
						if (etsaiMug==1) {
							etsaiMug = 2;
						}
					}
					switch(etsaiMug) {
						case 0:
							mugituOntziEtsai(e,0,1);		//beherantz
							break;
						case 1:
							mugituOntziEtsai(e,-1,0);	//ezkerrerantz
							break;
						case 2:
							mugituOntziEtsai(e,1,0);		//eskuinerantz
							break;
					}
				}
			});
	}
	private void mugituOntziEtsai(NodoOntziTxarra pEtsai,int pX,int pY) {
		pEtsai.mugituPixel(pX, pY);
	}
	
	public boolean etsaiKolisioa(int pId, int pX, int pY) {
		/*boolean kolisioa = false;	
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
		}*/
		boolean kolisioa = etsaiak.stream()
		        .anyMatch(et -> 
	            et.getPixelak().stream()
	                .anyMatch(p -> 
	                    p.getX() == pX && 
	                    p.getY() == pY && 
	                    p.getId() != pId
	                )
	        );
		return kolisioa;
	}
	
	public NodoOntziOn getGurea() {
		return gurea;
	}
}
package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Espazio{
	private GelaxkaM[][] matrizea = new GelaxkaM[15][26];
	private OntziOna gurea;
	private ArrayList<OntziTxarra> etsaiak;
	private int etsaiKop;
	private static Espazio nEspazio = null;
	private int EspaziontziaX;
	private int EspaziontziaY;
	private ArrayList<int[]> tiroak; 
	
	private Espazio() {
		for(int i=0;i<matrizea.length;i++) {
			for(int j=0;j<matrizea[i].length;j++) {
				matrizea[i][j]= new GelaxkaM(0);
			}
		}
		gurea= new OntziOna();
		GelaxkaM gureG = new GelaxkaM(1);
		matrizea[13][13]= gureG;
		EspaziontziaX = 13;
		EspaziontziaY = 13;
	
		tiroak = new ArrayList<int[]>();
		etsaiak= new ArrayList<OntziTxarra>();
		etsaiKop = (int)(Math.random()*5)+4;
		for (int i=0;i<etsaiKop;i++) {
			OntziTxarra e = new OntziTxarra();
			etsaiak.add(e);
		}
		Iterator<OntziTxarra> itr = etsaiak.iterator();
		boolean[] etsaiBool = new boolean[26];
		while(itr.hasNext()) {
			OntziTxarra o = itr.next();
			boolean jarrita = false;
			while(!jarrita) {
				if(!etsaiBool[o.getX()]) {
					sartu(o.getX(), o.getY(), 2);
					etsaiBool[o.getX()]=true;
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
	public void sartu(int x,int y,int mota) {
		GelaxkaM g = new GelaxkaM(mota);
		matrizea[y][x]=g;
	}
	
	public GelaxkaM[][] getGelaxkakM(){
		return this.matrizea;
	}
	
	public int getEtsaiKop() {
		return this.etsaiKop;
	}

	public void moverNave(int dx, int dy) {
		int berriaX = EspaziontziaX + dx;
		int berriaY = EspaziontziaY + dy;

		int maxY = matrizea.length - 1;
		int maxX = matrizea[0].length - 1;

		// Matrizaren mugetatik kanpo ez mugitzea ziurtatu
		if (berriaX < 0 || berriaX > maxX || berriaY < 0 || berriaY > maxY) {
			return; // no se mueve
		}

		// espaziontzia kendu bere posizio zaharretik
		matrizea[EspaziontziaY][EspaziontziaX].aldatuMota(0);

		// posizio berria
		matrizea[berriaY][berriaX].aldatuMota(1);

		EspaziontziaX = berriaX;
		EspaziontziaY = berriaY;
	}

	public void tiro() {

		int tiroX = EspaziontziaX;
		int tiroY = EspaziontziaY - 2;

		if (tiroY < 0 || tiroY >= matrizea.length) {
			return;
		}

		// hutsik badago tiroa
		int motaHelburu = matrizea[tiroY][tiroX].getMota();
		if (motaHelburu==3) {
			return;
		}
		if(motaHelburu==2) {
			etsaiaHil(tiroX,tiroY);
			matrizea[tiroY][tiroX].aldatuMota(4);
			return;
		}

		matrizea[tiroY][tiroX].aldatuMota(3);
		tiroak.add(new int[] { tiroX, tiroY });
	}

	public void mugituTiroak() {
		if (tiroak.isEmpty()) {
			return;
		}

		int maxY = matrizea.length - 1;

		for (int i = tiroak.size() - 1; i >= 0; i--) {
			int[] pos = tiroak.get(i);
			int x = pos[0];
			int y = pos[1];

			if (y >= 0 && y <= maxY) {
				matrizea[y][x].aldatuMota(0);
			}

			int berriaY = y - 1;

			if (berriaY < 0) {
				tiroak.remove(i);
				continue;
			}

			if(matrizea[berriaY][x].getMota()==2) {
				etsaiaHil(x,berriaY);
				matrizea[berriaY][x].aldatuMota(4);
				tiroak.remove(i);
				continue;
			}
			matrizea[berriaY][x].aldatuMota(3);
			pos[1] = berriaY;
		}
	}
	
	public void etsaiaHil(int x, int y) {
		matrizea[y][x].aldatuMota(0);
		Iterator<OntziTxarra> itr = etsaiak.iterator();
		boolean aurkituta = false;
		while(!aurkituta && itr.hasNext()) {
			OntziTxarra etsai = itr.next();
			if (etsai.x==x && etsai.y==y) {
				etsaiak.remove(etsai);
				aurkituta=true;
				etsaiKop--;
			}
		}
		System.out.println("Etsaia hilda, etsai kopurua: "+etsaiKop);
	}
}
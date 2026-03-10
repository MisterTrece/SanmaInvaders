package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Iterator;

public class Espazio extends Observable{
	private Gelaxka[][] matrizea = new Gelaxka[15][26];
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
				matrizea[i][j]= new Gelaxka(0);
			}
		}
		gurea= new OntziOna();
		Gelaxka gureG = new Gelaxka(1);
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
		setChanged();
		notifyObservers();
	}
	
	public static Espazio getEspazioEMA() {
		if(nEspazio==null) {
			nEspazio = new Espazio();
		}
		return nEspazio;
	}
	public void sartu(int x,int y,int mota) {
		Gelaxka g = new Gelaxka(mota);
		matrizea[y][x]=g;
	}
	
	public Gelaxka[][] getMatriz(){
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
		matrizea[EspaziontziaY][EspaziontziaX] = new Gelaxka(0);

		// posizio berria
		matrizea[berriaY][berriaX] = new Gelaxka(1);

		EspaziontziaX = berriaX;
		EspaziontziaY = berriaY;

		setChanged();
		notifyObservers();
	}

	public void tiro() {

		int tiroX = EspaziontziaX;
		int tiroY = EspaziontziaY - 2;

		if (tiroY < 0 || tiroY >= matrizea.length) {
			return;
		}

		// hutsik badago tiroa
		String motaHelburu = matrizea[tiroY][tiroX].getMotaIrudi();
		if (!"-".equals(motaHelburu)) {
			return;
		}

		matrizea[tiroY][tiroX] = new Gelaxka(3); 
		tiroak.add(new int[] { tiroX, tiroY });

		setChanged();
		notifyObservers();
	}

	public void moverTiros() {
		if (tiroak.isEmpty()) {
			return;
		}

		int maxY = matrizea.length - 1;

		for (int i = tiroak.size() - 1; i >= 0; i--) {
			int[] pos = tiroak.get(i);
			int x = pos[0];
			int y = pos[1];

			if (y >= 0 && y <= maxY) {
				matrizea[y][x] = new Gelaxka(0);
			}

			int berriaY = y - 1;

			if (berriaY < 0) {
				tiroak.remove(i);
				continue;
			}

			matrizea[berriaY][x] = new Gelaxka(3);
			pos[1] = berriaY;
		}

		setChanged();
		notifyObservers();
	}

	public String[][] bihurtuStringMatrizera() {
        Gelaxka[][]matrizeM = getMatriz();
		int lerro = matrizeM.length;
        int zutabe = matrizeM[0].length;

        String[][] mString = new String[lerro][zutabe];

        for (int i = 0; i < lerro; i++) {
            for (int j = 0; j < zutabe; j++) {
                mString[i][j] = matrizeM[i][j].getMotaIrudi();
            }
        }

        return mString;
    }

	
}
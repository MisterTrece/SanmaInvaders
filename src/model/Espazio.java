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
	
	
	private Espazio() {
		for(int i=0;i<matrizea.length;i++) {
			for(int j=0;j<matrizea[i].length;j++) {
				matrizea[i][j]= new Gelaxka(0);
			}
		}
		gurea= new OntziOna();
		Gelaxka gureG = new Gelaxka(1);
		matrizea[13][13]= gureG;
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
package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Espazio {
	private Gelaxka[][] matrizea = new Gelaxka[26][15];
	private OntziOna gurea;
	private ArrayList<OntziTxarra> etsaiak;
	private static Espazio nEspazio = null;
	
	private Espazio() {
		for(int i=0;i<26;i++) {
			for(int j=0;j<15;j++) {
				matrizea[i][j]= new Gelaxka(0);
			}
		}
		gurea= new OntziOna();
		Gelaxka gureG = new Gelaxka(1);
		matrizea[13][13]= gureG ;
		etsaiak= new ArrayList<OntziTxarra>();
		int etsaiKop = (int)(Math.random()*5)+4;
		for (int i=0;i<etsaiKop;i++) {
			OntziTxarra e = new OntziTxarra();
			etsaiak.add(e);
		}
		Iterator<OntziTxarra> itr = etsaiak.iterator();
		
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
}
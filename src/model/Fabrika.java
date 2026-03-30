package model;

public class Fabrika {
	private static Fabrika sortzailea;
	
	private Fabrika() {
		
	}
	public static Fabrika getFabrika() {
		if (sortzailea==null) {
			sortzailea = new Fabrika();
		}
		return sortzailea;
	}
	
	public OntziOna createOntziOna(int pMota) {
		OntziOna nireOntzia;
		switch (pMota) {
			case 1:
				nireOntzia = new OntziOnTxikia();
			case 2:
				nireOntzia = new OntziOnErtaina();
			case 3:
				nireOntzia = new OntziOnHandia();
			default:
				nireOntzia = new OntziOnTxikia();
		}
		return nireOntzia;
	}
}

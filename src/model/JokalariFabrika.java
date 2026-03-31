package model;

public class JokalariFabrika {
	private static JokalariFabrika sortzailea;
	
	private JokalariFabrika() {
		
	}
	public static JokalariFabrika getJokalariFabrika() {
		if (sortzailea==null) {
			sortzailea = new JokalariFabrika();
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

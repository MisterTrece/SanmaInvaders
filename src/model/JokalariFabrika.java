package model;

public class JokalariFabrika {
	private static JokalariFabrika nJokalariFabrika = null;
	
	private JokalariFabrika() {
		
	}
	public static JokalariFabrika getJokalariFabrika() {
		if (nJokalariFabrika==null) {
			nJokalariFabrika = new JokalariFabrika();
		}
		return nJokalariFabrika;
	}
	
	public OntziOna createOntziOna(int pMota, int pX, int pY) {
		OntziOna nireOntzia;
		switch (pMota) {
			case 1:
				nireOntzia = new OntziOnBlue(pX,pY);
			case 2:
				nireOntzia = new OntziOnGreen(pX,pY);
			case 3:
				nireOntzia = new OntziOnRed(pX,pY);
			default:
				nireOntzia = new OntziOnBlue(pX,pY);
		}
		return nireOntzia;
	}
}

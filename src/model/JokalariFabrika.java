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
	
	public NodoOntziOn createOntziOna(int pMota) {
		NodoOntziOn nireOntzia;
		switch (pMota) {
			case 1:
				nireOntzia = new OntziOnBlue();
				break;
			case 2:
				nireOntzia = new OntziOnGreen();
				break;
			case 3:
				nireOntzia = new OntziOnRed();
				break;
			default:
				nireOntzia = new OntziOnRed();
				break;
		}
		return nireOntzia;
	}
}

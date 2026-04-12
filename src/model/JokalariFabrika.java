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
				System.out.println("Hasi:Blue");
				break;
			case 2:
				nireOntzia = new OntziOnGreen();
				System.out.println("Hasi:Green");
				break;
			case 3:
				nireOntzia = new OntziOnRed();
				System.out.println("Hasi:Red");
				break;
			default:
				nireOntzia = new OntziOnRed();
				break;
		}
		return nireOntzia;
	}
}

package model;

public class main {

	public static void main(String[] args) {
		Espazio esp = Espazio.getEspazioEMA();
		Gelaxka[][] m = esp.getMatriz();
		for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j].getMotaIrudi() + " ");
            }
            System.out.println();
        }
		System.out.println("Etsai Kopurua: "+esp.getEtsaiKop());
	}
}

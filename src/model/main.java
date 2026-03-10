package model;
import GUI_Package.*;

public class main {

	public static void main(String[] args) {
		HasieraPantaila hasiera = new HasieraPantaila();
		GameController gC = GameController.getGC();
		gC.addObserver(hasiera);
	/*	
		gC.empezarPartida("Green");
		Matrizea joko = new Matrizea();
		joko.setVisible(true);
	*/
	}
}


//WIP: no tocar, se hará más adelante
package model;
import GUI_Package.*;

public class main {

	public static void main(String[] args) {
		HasierakoPAntaila hasiera = new HasieraPantaila();
        hasiera.setLocationRelativeTo(null);
        hasiera.setVisible(true);
		GameController gC = GameController.getGC();
	}
}

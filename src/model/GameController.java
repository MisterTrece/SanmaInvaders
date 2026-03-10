package model;

import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

import GUI_Package.HasieraPantaila;
import GUI_Package.Matrizea;

public class GameController extends Observable{

    private static GameController nGC = null;
    private Timer tiroTimer;

    private GameController() {
    
    }
    
    public static GameController getGC() {
    	if(nGC==null) {
    		nGC = new GameController();
    	}
    	return nGC;
    }

    public void empezarPartida(String tipoNave) {
    	//jokoa hasieratu
    	Espazio jokoa = Espazio.getEspazioEMA();
    	//hasiera ixteko notifikazioa
    	setChanged();
    	notifyObservers();
    	//tiroen Timer-a hasieratu
    	if (tiroTimer == null) {
            tiroTimer = new Timer(50, e -> Espazio.getEspazioEMA().mugituTiroak());
        }
        tiroTimer.start();
/*		
        hasiera.setVisible(false);

        // Crear la ventana del juego si no existe
        if (joko == null) {
            joko = new Matrizea();
        }
        joko.setLocationRelativeTo(null);
        joko.setVisible(true);
        
        */
		
        
        
    }

    public void moverNave(int dx, int dy) {
    	Espazio.getEspazioEMA().moverNave(dx, dy);
    }

    public void tiro() {
    	Espazio.getEspazioEMA().tiro();
    }

}
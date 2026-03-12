package model;

import javax.swing.Timer;

import GUI_Package.HasieraPantaila;
import GUI_Package.Matrizea;

public class GameController{

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

    public void partidaHasi(String tipoNave) {
    	//jokoa hasieratu
    	Espazio.getEspazioEMA();
    	
    	//gelaxkak lotu
    	modeloVistaLotu();

    	//tiroen Timer-a hasieratu
    	if (tiroTimer == null) {
            tiroTimer = new Timer(50, e -> Espazio.getEspazioEMA().mugituTiroak());
        }
        tiroTimer.start();
        
      //hasiera ixteko notifikazioa
    	HasieraPantaila.getHasieraPantaila().itxi(); 
    }
    
    private void modeloVistaLotu() {
    	GelaxkaM[][] gelaxkakM = Espazio.getEspazioEMA().getGelaxkakM();
    	for (int i = 0; i < gelaxkakM.length; i++) {
            for (int j = 0; j < gelaxkakM[i].length; j++) {
            	gelaxkakM[i][j].addObserver(Matrizea.getMatrizea().getGelaxkakV()[i][j]);
            	gelaxkakM[i][j].aldatuMota(gelaxkakM[i][j].getMota());
            }
    	}
    }

    public void gCOntziaMugitu(int dx, int dy) {
    	Espazio.getEspazioEMA().mugituOntzia(dx, dy);
    }

    public void tiro() {
    	Espazio.getEspazioEMA().tiro();
    }
    
    public void partidaIrabazi() {
    	Timer timerEND = new Timer(2500, e -> {
	        Matrizea.getMatrizea().irabazi();
	    });
	 timerEND.setRepeats(false);
	 timerEND.start();	
    }
}
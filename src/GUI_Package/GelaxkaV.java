package GUI_Package;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

public class GelaxkaV extends JLabel implements Observer{
    
    private int x;
    private int y;

    public GelaxkaV(int x, int y) {
        this.x = x;
        this.y = y;
        
        
        
        this.setOpaque(true); 
        this.setBackground(Color.BLACK); 
    }

    
   
    public int getXCoord() { return x; }
    public int getYCoord() { return y; }
    

	@Override
	public void update(Observable o, Object arg) {

        if (arg instanceof Integer) {
            int egoera = (Integer) arg;
            
            switch (egoera) {
                case 0:
                    this.setBackground(Color.BLACK); // Hutsik (Vacío)
                    break;
                case 1:
                    this.setBackground(Color.GREEN); // Gurea (Nave aliada)
                    break;
                case 2:
                    this.setBackground(Color.RED);   // Etsaia (Enemigo)
                    break;
                case 3:
                    this.setBackground(Color.WHITE); // Tiroa (Disparo)
                    break;
            }
        }	
        Matrizea.getMatrizea().errefreskatu();
	}   
}  
            

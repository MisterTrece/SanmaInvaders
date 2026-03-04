package GUI_Package;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

public class Gelaxka extends JLabel implements Observer {
    
    private int x;
    private int y;

    public Gelaxka(int x, int y) {
        this.x = x;
        this.y = y;
        
        
        
        this.setOpaque(true); 
        this.setBackground(Color.BLACK); 
    }

    @Override
    public void update(Observable o, Object arg) {

    }
    
   
    public int getXCoord() { return x; }
    public int getYCoord() { return y; }
}
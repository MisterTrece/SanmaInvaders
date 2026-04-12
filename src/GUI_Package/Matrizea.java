
package GUI_Package;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Espazio;
import model.GoiMailakoKontrola;

public class Matrizea extends JFrame implements Observer{

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private GelaxkaV[][] gelaxkak = new GelaxkaV[60][100];
    private static Matrizea nMatrizea = null;

    private Matrizea() {
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 720);
        
        contentPane = new JPanel();
        setContentPane(contentPane);

        contentPane.setLayout(new GridLayout(gelaxkak.length, gelaxkak[0].length));
        contentPane.setBackground(Color.BLACK);
        

        for (int i = 0; i < gelaxkak.length; i++) {
            for (int j = 0; j < gelaxkak[i].length; j++) {
                
            	gelaxkak[i][j]= new GelaxkaV(i,j);
            	contentPane.add(gelaxkak[i][j]);
            }
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> Espazio.getEspazioEMA().mugituOntzia(-1, 0);
                    case KeyEvent.VK_RIGHT -> Espazio.getEspazioEMA().mugituOntzia(1, 0);
                    case KeyEvent.VK_UP -> Espazio.getEspazioEMA().mugituOntzia(0, -1);
                    case KeyEvent.VK_DOWN -> Espazio.getEspazioEMA().mugituOntzia(0, 1);
                    case KeyEvent.VK_SPACE -> Espazio.getEspazioEMA().tiro();
                    case KeyEvent.VK_T -> Espazio.getEspazioEMA().aldatuTiroMota();
                }
            }
        });
        setFocusable(true);
        
        GoiMailakoKontrola.getKontrola().addObserver(this);
    }
    
    public static Matrizea getMatrizea() {
    	if(nMatrizea==null) {
    		nMatrizea=new Matrizea();
    	}
    	return nMatrizea;
    }
    
    public void lotu() {
    	for (int i = 0; i < gelaxkak.length; i++) {
            for (int j = 0; j < gelaxkak[i].length; j++) {
            	Espazio.getEspazioEMA().getGelaxka(j, i).addObserver(gelaxkak[i][j]);
            	Espazio.getEspazioEMA().getGelaxka(j, i).aldatuMota(Espazio.getEspazioEMA().getGelaxka(j, i).getEgoera());
            }
    	}
    }

    public void errefreskatu() {
    	contentPane.revalidate();
        contentPane.repaint();
    }  

	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("LOSE")) {
			setEnabled(false);
	    	GalduPantaila.getGalduPantaila().setLocationRelativeTo(null);
	    	GalduPantaila.getGalduPantaila().setVisible(true);
		}
		
		if(arg.equals("WIN")) {
			setEnabled(false);
	    	IrabaziPantaila.getIrabaziPantaila().setLocationRelativeTo(null);
	    	IrabaziPantaila.getIrabaziPantaila().setVisible(true);
		}
	}
}
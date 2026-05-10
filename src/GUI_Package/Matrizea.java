
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
import javax.swing.JOptionPane;

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
                    case KeyEvent.VK_ESCAPE -> System.exit(0);
                    							
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
            eskatuEtaGordePuntuazioa();
			setEnabled(false);
	    	GalduPantaila.getGalduPantaila().setLocationRelativeTo(null);
	    	GalduPantaila.getGalduPantaila().setUndecorated(true);
	    	GalduPantaila.getGalduPantaila().setVisible(true);
		}
		
		if(arg.equals("WIN")) {
            eskatuEtaGordePuntuazioa();
			setEnabled(false);
	    	IrabaziPantaila.getIrabaziPantaila().setLocationRelativeTo(null);
	    	IrabaziPantaila.getIrabaziPantaila().setUndecorated(true);
	    	IrabaziPantaila.getIrabaziPantaila().setVisible(true);
		}

	}

    private void eskatuEtaGordePuntuazioa() {
        if (Espazio.getEspazioEMA().isPuntuazioaGordeta()) {
            return;
        }
        String izena;
        while (true) {
            izena = JOptionPane.showInputDialog(this, "Sartu zure izena (3 karaktere):");
            if (izena == null) {
                return;
            }
            izena = izena.trim();
            if (izena.length() == 3) {
                break;
            }
            JOptionPane.showMessageDialog(this, "Izena 3 karaktere izan behar du.");
        }
        Espazio.getEspazioEMA().gordePuntuazioa(izena.toUpperCase());
    }
}
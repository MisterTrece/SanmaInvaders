
package GUI_Package;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameController;

public class Matrizea extends JFrame{

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private GelaxkaV[][] gelaxkak = new GelaxkaV[60][100];
    private static Matrizea nMatrizea = null;

    private Matrizea() {
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);

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
                    case KeyEvent.VK_LEFT -> GameController.getGC().gCOntziaMugitu(-1, 0);
                    case KeyEvent.VK_RIGHT -> GameController.getGC().gCOntziaMugitu(1, 0);
                    case KeyEvent.VK_UP -> GameController.getGC().gCOntziaMugitu(0, -1);
                    case KeyEvent.VK_DOWN -> GameController.getGC().gCOntziaMugitu(0, 1);
                    case KeyEvent.VK_SPACE -> GameController.getGC().tiro();
                }
            }
        });
        setFocusable(true);
    }
    
    public static Matrizea getMatrizea() {
    	if(nMatrizea==null) {
    		nMatrizea=new Matrizea();
    	}
    	return nMatrizea;
    }

    public GelaxkaV[][] getGelaxkakV() {
        return gelaxkak;
    }
    
    public void errefreskatu() {
    	contentPane.revalidate();
        contentPane.repaint();
    }
    
    public void irabazi() {
    	//WIP
    	dispose();
    	System.out.println("IRABAZI DUZU");
    }
}
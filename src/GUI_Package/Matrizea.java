package GUI_Package;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Espazio;

public class Matrizea extends JFrame implements Observer{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;//JPanel gero sortzeko atributu pribatua
    
   
    private Gelaxka[][] gelaxkak;//Gelaxka Klaseko atributu pribatua


    private final int ZUTABEAK = 100; //zutabeen balioa
    private final int LERROAK = 60;   //errenkaden balioa

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Matrizea frame = new Matrizea();//Matrizea sortu
                    frame.setVisible(true);//Matrizea ikusteko
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Matrizea() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);

        contentPane = new JPanel();
        setContentPane(contentPane);

        Espazio esp = Espazio.getEspazioEMA();
        gelaxkak = matrizeaBihurtu(esp.bihurtuStringMatrizera());

        contentPane.setLayout(new GridLayout(gelaxkak.length, gelaxkak[0].length));
        contentPane.setBackground(Color.BLACK);

        for (int i = 0; i < gelaxkak.length; i++) {
            for (int j = 0; j < gelaxkak[i].length; j++) {
                contentPane.add(gelaxkak[i][j]);
            }
        }

        esp.addObserver(this);
    }
    
    
    public Gelaxka[][] getGelaxkak() {//Matrizea bueltatzeko metodoa
        return gelaxkak;
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public Gelaxka[][] matrizeaBihurtu(String[][] matrizString) {
        int lerro = matrizString.length;
        int zutabe = matrizString[0].length;

        Gelaxka[][] gelaxkaGUI = new Gelaxka[lerro][zutabe];

        // Aurreko panela garbitu eta layout-a konfiguratu
        contentPane.removeAll();
        contentPane.setLayout(new GridLayout(lerro, zutabe));
        contentPane.setBackground(Color.BLACK);

        for (int i = 0; i < lerro; i++) {
            for (int j = 0; j < zutabe; j++) {
                Gelaxka g = new Gelaxka(i, j);

                String simboloa = matrizString[i][j];
                
                // Colorea eman simboloen arabehera
                switch (simboloa) {
                    case "-" -> g.setBackground(Color.BLACK);   // Hutsik
                    case "X" -> g.setBackground(Color.BLUE);    // Gurea
                    case "O" -> g.setBackground(Color.RED);     // Etsaia
                    case "|" -> g.setBackground(Color.WHITE);   // Tiroa
                    default -> g.setBackground(Color.BLACK);    // Badaezpada
                }

                gelaxkaGUI[i][j] = g;
                contentPane.add(g);
            }
        }

        // Panela refreskatzeko
        contentPane.revalidate();
        contentPane.repaint();

        return gelaxkaGUI;
    }
}

package GUI_Package;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Espazio;
import model.GameController;
import model.GelaxkaM;

public class Matrizea extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private GelaxkaV[][] gelaxkak = new GelaxkaV[15][26];
    private static Matrizea nMatrizea = null;

    private Matrizea() {
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);

        contentPane = new JPanel();
        setContentPane(contentPane);
        
        //Espazio e = Espazio.getEspazioEMA();
        //gelaxkak = new GelaxkaV[e.getMatriz().length][e.getMatriz()[0].length];
        //GelaxkaM[][] gM = e.getMatriz();

        contentPane.setLayout(new GridLayout(gelaxkak.length, gelaxkak[0].length));
        contentPane.setBackground(Color.BLACK);

        for (int i = 0; i < gelaxkak.length; i++) {
            for (int j = 0; j < gelaxkak[i].length; j++) {
                
            	gelaxkak[i][j]= new GelaxkaV(i,j);
            	contentPane.add(gelaxkak[i][j]);
            	//GelaxkaV g = gelaxkak[i][j];

                //GelaxkaM gm = gM[i][j];
 /*           	
                switch (gm.getMota()) {
                case "hutsik" -> g.setBackground(Color.BLACK);   // Hutsik
                case "gurea" -> g.setBackground(Color.BLUE);    // Gurea
                case "etsaia" -> g.setBackground(Color.RED);     // Etsaia
                case "tiroa" -> g.setBackground(Color.WHITE);   // Tiroa
                default -> g.setBackground(Color.BLACK);    // Badaezpada
                }
 */             
                //e.getMatriz()[i][j].addObserver(g);
            	
            }
        }
/*        
        gelaxkak[0][0].setBackground(Color.GREEN);
        gelaxkak[0][25].setBackground(Color.GREEN);
        gelaxkak[14][0].setBackground(Color.GREEN);
        gelaxkak[14][25].setBackground(Color.GREEN);
*/        
        Espazio.getEspazioEMA().addObserver(this);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> GameController.getGC().gContziaMugitu(-1, 0);
                    case KeyEvent.VK_RIGHT -> GameController.getGC().gContziaMugitu(1, 0);
                    case KeyEvent.VK_UP -> GameController.getGC().gContziaMugitu(0, -1);
                    case KeyEvent.VK_DOWN -> GameController.getGC().gContziaMugitu(0, 1);
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

    @Override
    public void update(Observable o, Object arg) {
        Espazio esp = (Espazio) o;
        this.gelaxkak = matrizeaBihurtu();
    }
	
	public GelaxkaV[][] matrizeaBihurtu() {
		
        GelaxkaV[][] gelaxkaGUI = new GelaxkaV[gelaxkak.length][gelaxkak[0].length];
        Espazio e = Espazio.getEspazioEMA();
        GelaxkaM[][] gM = e.getGelaxkakM();

        // Aurreko panela garbitu eta layout-a konfiguratu
        contentPane.removeAll();
        contentPane.setLayout(new GridLayout(gelaxkak.length, gelaxkak[0].length));
        contentPane.setBackground(Color.BLACK);

        for (int i = 0; i < gelaxkak.length; i++) {
            for (int j = 0; j < gelaxkak[0].length; j++) {
            	GelaxkaV g = gelaxkak[i][j];
                GelaxkaM gm = gM[i][j];
                
                // Kolorea eman simboloen arabehera
                switch (gm.getMota()) {
                case "hutsik" -> g.setBackground(Color.BLACK);   // Hutsik
                case "gurea" -> g.setBackground(Color.BLUE);    // Gurea
                case "etsaia" -> g.setBackground(Color.RED);     // Etsaia
                case "tiroa" -> g.setBackground(Color.WHITE);   // Tiroa
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
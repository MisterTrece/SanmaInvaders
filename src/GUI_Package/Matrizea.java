
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

public class Matrizea extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private GelaxkaV[][] gelaxkak;


    public Matrizea() {
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);

        contentPane = new JPanel();
        setContentPane(contentPane);

        gelaxkak = matrizeaBihurtu(Espazio.getEspazioEMA().bihurtuStringMatrizera());

        contentPane.setLayout(new GridLayout(gelaxkak.length, gelaxkak[0].length));
        contentPane.setBackground(Color.BLACK);

        for (int i = 0; i < gelaxkak.length; i++) {
            for (int j = 0; j < gelaxkak[i].length; j++) {
                contentPane.add(gelaxkak[i][j]);
            }
        }

        Espazio.getEspazioEMA().addObserver(this);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> GameController.getGC().moverNave(-1, 0);
                    case KeyEvent.VK_RIGHT -> GameController.getGC().moverNave(1, 0);
                    case KeyEvent.VK_UP -> GameController.getGC().moverNave(0, -1);
                    case KeyEvent.VK_DOWN -> GameController.getGC().moverNave(0, 1);
                    case KeyEvent.VK_SPACE -> GameController.getGC().tiro();
                }
            }
        });

        setFocusable(true);
    }

    public GelaxkaV[][] getGelaxkak() {
        return gelaxkak;
    }

    @Override
    public void update(Observable o, Object arg) {
        Espazio esp = (Espazio) o;
        this.gelaxkak = matrizeaBihurtu(esp.bihurtuStringMatrizera());
    }
	
	public GelaxkaV[][] matrizeaBihurtu(String[][] matrizString) {
        int lerro = matrizString.length;
        int zutabe = matrizString[0].length;

        GelaxkaV[][] gelaxkaGUI = new GelaxkaV[lerro][zutabe];

        // Aurreko panela garbitu eta layout-a konfiguratu
        contentPane.removeAll();
        contentPane.setLayout(new GridLayout(lerro, zutabe));
        contentPane.setBackground(Color.BLACK);

        for (int i = 0; i < lerro; i++) {
            for (int j = 0; j < zutabe; j++) {
                GelaxkaV g = new GelaxkaV(i, j);

                String simboloa = matrizString[i][j];
                
                // Kolorea eman simboloen arabehera
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
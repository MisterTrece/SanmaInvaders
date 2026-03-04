package GUI_Package;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Matrizea extends JFrame {

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
        
        contentPane = new JPanel();//Panel honetan Gelaxkak gehituko ditugu
        
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0)); 
        setContentPane(contentPane);

        
        contentPane.setLayout(new GridLayout(LERROAK, ZUTABEAK));
        contentPane.setBackground(Color.BLACK);

        gelaxkak = new Gelaxka[ZUTABEAK][LERROAK];//Gelaxka guztiak sortu bakoitza posizio bat izango duena
        
        for (int y = 0; y < LERROAK; y++) {//Matrizearen x eta y ardatzetan gelaxkak gehitu(for honetan errenkadak).PD: beharbada errenkadak x-etan eta zutabeak y-n jarri beharko litezke.
            for (int x = 0; x < ZUTABEAK; x++) {//Zutabeak errekorritu
                Gelaxka g = new Gelaxka(x, y); //gelaxka motako objetua sortu x(zutabeak)eta y(errenkadak posizioarekin
                gelaxkak[x][y] = g;//Aurreko lerroan sortutako objetua
                contentPane.add(g);//Gelaxka panelera gehitu
            }
        }
    }
    
    
    public Gelaxka[][] getGelaxkak() {//Matrizea bueltatzeko metodoa
        return gelaxkak;
    }
}
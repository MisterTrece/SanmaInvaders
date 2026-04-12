package GUI_Package;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Espazio;
import model.GoiMailakoKontrola;

public class HasieraPantaila extends JFrame implements Observer{

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private final JLabel lblSakatu = new JLabel("Sakatu <Up-Down-Left-Right> mugitzeko eta <Space> tiro egiteko");
    private final JLabel lblStart = new JLabel("Sakatu <P> hasteko eta <R><G><B> espazio-ontziaren kolorea aldatzeko");
    
    private static HasieraPantaila nHasieraPantaila = null;
    
    private HasieraPantaila() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1264, 784);
        

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/GUI_Package/FondoSanmaInvaders.png")));
        background.setBounds(0, 0, 1264, 784);

        lblSakatu.setFont(new Font("Consolas", Font.BOLD, 20));
        lblSakatu.setForeground(Color.WHITE);
        lblSakatu.setBounds(280, 75, 800, 30);

        lblStart.setFont(new Font("Consolas", Font.BOLD, 20));
        lblStart.setForeground(Color.WHITE);
        lblStart.setBounds(235, 650, 800, 30);

        contentPane.add(lblSakatu);
        contentPane.add(lblStart);
        contentPane.add(background);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	switch (e.getKeyCode()) {
                case KeyEvent.VK_P:
                	GoiMailakoKontrola.getKontrola().addObserver(nHasieraPantaila);
                	GoiMailakoKontrola.getKontrola().partidaHasi();
                	break;
                case KeyEvent.VK_B:
                	Espazio.getEspazioEMA().setOntziMota(1);
                	break;
                case KeyEvent.VK_G:
                	Espazio.getEspazioEMA().setOntziMota(2);
                	break;
                case KeyEvent.VK_R:
                	Espazio.getEspazioEMA().setOntziMota(3);
                	break;
                case KeyEvent.VK_ESCAPE:
                	System.exit(0);
                	break;
            	}
            	
            	
            }
        });
        setFocusable(true);

        Timer blinkTimer = new Timer(750, e -> {
            lblStart.setVisible(!lblStart.isVisible());
            lblSakatu.setVisible(!lblSakatu.isVisible());
        });
        blinkTimer.start();
        
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
        
        
    }
    
    public static HasieraPantaila getHasieraPantaila() {
    	if(nHasieraPantaila==null) {
    		nHasieraPantaila=new HasieraPantaila();
    	}
    	return nHasieraPantaila;
    }
    

	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("INIT")){
			Matrizea.getMatrizea().lotu();
			Matrizea.getMatrizea().setLocationRelativeTo(null);
			Matrizea.getMatrizea().setUndecorated(true);
			Matrizea.getMatrizea().setVisible(true);
			this.dispose();
		}
	}
}

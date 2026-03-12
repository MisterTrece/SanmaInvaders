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

import model.GameController;

public class HasieraPantaila extends JFrame implements Observer {

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
                String tipo = "Green";
            	int code = e.getKeyCode();
                if (code == KeyEvent.VK_P) { 
                	GameController.getGC().empezarPartida(tipo);
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
		this.dispose();
		Matrizea.getMatrizea().setLocationRelativeTo(null);
		Matrizea.getMatrizea().setVisible(true);
	}
}

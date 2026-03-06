package GUI_Package;

import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HasieraPantaila extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=471,88
	 */
	private final JLabel lblSakatu = new JLabel("Press <Up-Down-Left-Right> to move. And <Space> to shoot.");
	private final JLabel lblStart = new JLabel("Press <P> to start. And <R><G><B> to change spaceship colour.");

	/**
	 * Martxan jarri.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HasieraPantaila frame = new HasieraPantaila();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sortu.
	 */
	public HasieraPantaila() {

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 1264, 784);

	    contentPane = new JPanel();
	    contentPane.setLayout(null);
	    setContentPane(contentPane);

	    JLabel background = new JLabel(new ImageIcon(getClass().getResource("/GUI_Package/FondoSanmaInvaders.png")));
	    
	    
	    background.setBounds(0, 0, 1264, 784);

	    lblSakatu.setFont(new Font("Consolas", Font.BOLD, 20));
	    lblSakatu.setForeground(Color.WHITE);
	    lblSakatu.setBounds(310, 75, 700, 30);

	    lblStart.setFont(new Font("Consolas", Font.BOLD, 20));
	    lblStart.setForeground(Color.WHITE);
	    lblStart.setBounds(300, 650, 700, 30);

	    contentPane.add(lblSakatu);
	    contentPane.add(lblStart);
	    contentPane.add(background);
	    
	    Timer blinkTimer = new Timer(500, e -> {
	        lblStart.setVisible(!lblStart.isVisible());
	        lblSakatu.setVisible(!lblSakatu.isVisible());
	    });
	    blinkTimer.start();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}

package GUI_Package;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class GalduPantaila extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	JLabel galdu = new JLabel(new ImageIcon(getClass().getResource("/GUI_Package/GameOver.png")));
	JLabel garezurra = new JLabel(new ImageIcon(getClass().getResource("/GUI_Package/Skull.png")));
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { //Ea ondo ikusten den jakiteko
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GalduPantaila frame = new GalduPantaila();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GalduPantaila() {

	    setForeground(new Color(0, 0, 0));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(0, 0, 450, 300);

	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setBackground(Color.BLACK);
	    contentPane.setLayout(null);

	    galdu.setHorizontalAlignment(JLabel.CENTER);
	    galdu.setVerticalAlignment(JLabel.CENTER);
	    
	    garezurra.setHorizontalAlignment(JLabel.CENTER);
	    garezurra.setVerticalAlignment(JLabel.CENTER);

	    galdu.setBounds(87, 38, 246, 176);
	    garezurra.setBounds(96, 11, 250, 220);

	    contentPane.add(galdu);
	    contentPane.add(garezurra);

	    setContentPane(contentPane);
	    
	    galdu.setVisible(true);
	    garezurra.setVisible(false);
	    
	    
	    Timer timer = new Timer(750, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	galdu.setVisible(!galdu.isVisible());
	        	garezurra.setVisible(!garezurra.isVisible());
	        }
	    });
	    
	    timer.start();
	}
	
	

}

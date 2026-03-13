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

public class IrabaziPantaila extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	JLabel koroia = new JLabel(new ImageIcon(getClass().getResource("/GUI_Package/Koroia.png")));
	JLabel irabazi = new JLabel(new ImageIcon(getClass().getResource("/GUI_Package/IrabaziDuzu.png")));
	
	private static IrabaziPantaila nIrabaziPantaila = null;
	
	private IrabaziPantaila() {

	    setForeground(new Color(0, 0, 0));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(0, 0, 450, 300);

	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setBackground(Color.BLACK);
	    contentPane.setLayout(null);
	    
	    JLabel background = new JLabel(new ImageIcon(getClass().getResource("/GUI_Package/Izarrak.png")));
        background.setBounds(0, 0, 740, 423);
	    
	    koroia.setHorizontalAlignment(JLabel.CENTER);
	    koroia.setVerticalAlignment(JLabel.CENTER);
	    
	    irabazi.setHorizontalAlignment(JLabel.CENTER);
	    irabazi.setVerticalAlignment(JLabel.CENTER);

	    koroia.setBounds(87, 38, 233, 195);
	    irabazi.setBounds(20, 15, 376, 250);

	    contentPane.add(koroia);
	    contentPane.add(irabazi);
	    contentPane.add(background);

	    setContentPane(contentPane);
	    
	    koroia.setVisible(true);
	    irabazi.setVisible(false);
	    
	    
	    Timer timer = new Timer(750, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	koroia.setVisible(!koroia.isVisible());
	        	irabazi.setVisible(!irabazi.isVisible());
	        }
	    });
	    
	    timer.start();
	}
	
	public static IrabaziPantaila getIrabaziPantaila() {
		if(nIrabaziPantaila==null) {
			nIrabaziPantaila=new IrabaziPantaila();
		}
		return nIrabaziPantaila;
	}

}

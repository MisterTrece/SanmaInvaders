package GUI_Package;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ZailtasunPantaila extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static ZailtasunPantaila nZailtasunPantaila;

    private ZailtasunPantaila() {

        setTitle("Zailtasuna");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 740, 423);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.BLACK);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/GUI_Package/Izarrak.png")));
        background.setBounds(0, 0, 740, 423);

        contentPane.add(background);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }

            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }
    
    public static ZailtasunPantaila getZailtasunPantaila() {
    	if(nZailtasunPantaila == null) {
    		nZailtasunPantaila = new ZailtasunPantaila();
    	}
    	
    	return nZailtasunPantaila;
    }
}
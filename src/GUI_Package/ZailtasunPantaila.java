package GUI_Package;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;

import model.Espazio;
import model.ZailtasunMaila;

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

        JLabel titulua = new JLabel("ZAILTASUNA AUKERATU");
        titulua.setForeground(Color.WHITE);
        titulua.setFont(new Font("Consolas", Font.BOLD, 28));
        titulua.setBounds(200, 30, 400, 40);

        JButton btnErraza = new JButton("Erraza");
        JButton btnNormala = new JButton("Normala");
        JButton btnZaila = new JButton("Zaila");

        btnErraza.setFont(new Font("Consolas", Font.BOLD, 20));
        btnNormala.setFont(new Font("Consolas", Font.BOLD, 20));
        btnZaila.setFont(new Font("Consolas", Font.BOLD, 20));
        
        ZailtasunMaila maila = Espazio.getEspazioEMA().getZailtasunMaila();

        if (maila == ZailtasunMaila.ERRAZA) {
            btnErraza.setForeground(Color.GREEN);
        } 
        else if (maila == ZailtasunMaila.NORMALA) {
            btnNormala.setForeground(Color.GREEN);
        } 
        else if (maila == ZailtasunMaila.ZAILA) {
            btnZaila.setForeground(Color.GREEN);
        }

        btnErraza.setBounds(100, 150, 160, 40);
        btnNormala.setBounds(290, 150, 160, 40);
        btnZaila.setBounds(480, 150, 160, 40);

        btnErraza.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	btnErraza.setForeground(Color.GREEN);
            	btnNormala.setForeground(Color.BLACK);
            	btnZaila.setForeground(Color.BLACK);
            	aukeratu(ZailtasunMaila.ERRAZA);
            	HasieraPantaila.getHasieraPantaila().eguneratuZailtasuna(ZailtasunMaila.ERRAZA);
            }
        });

        btnNormala.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	btnErraza.setForeground(Color.BLACK);
            	btnNormala.setForeground(Color.GREEN);
            	btnZaila.setForeground(Color.BLACK);
            	aukeratu(ZailtasunMaila.NORMALA);
            	HasieraPantaila.getHasieraPantaila().eguneratuZailtasuna(ZailtasunMaila.NORMALA);
            }
        });

        btnZaila.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	btnErraza.setForeground(Color.BLACK);
            	btnNormala.setForeground(Color.BLACK);
            	btnZaila.setForeground(Color.GREEN);
            	aukeratu(ZailtasunMaila.ZAILA);
            	HasieraPantaila.getHasieraPantaila().eguneratuZailtasuna(ZailtasunMaila.ZAILA);
            }
        });

               
        contentPane.add(background);
        background.setLayout(null);

        background.add(titulua);
        background.add(btnErraza);
        background.add(btnNormala);
        background.add(btnZaila);

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
        contentPane.revalidate();
        contentPane.repaint();
    }
    
    private void aukeratu(ZailtasunMaila pMaila) {
        Espazio.getEspazioEMA().setZailtasunMaila(pMaila);
        HasieraPantaila.getHasieraPantaila().eguneratuZailtasuna(pMaila);
        dispose();
    }
    
    public static ZailtasunPantaila getZailtasunPantaila() {
    	if(nZailtasunPantaila == null) {
    		nZailtasunPantaila = new ZailtasunPantaila();
    	}
    	
    	return nZailtasunPantaila;
    }
}
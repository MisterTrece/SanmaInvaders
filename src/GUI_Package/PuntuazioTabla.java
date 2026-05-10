package GUI_Package;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.KonexioDB;

import java.io.BufferedReader;
import java.sql.Connection;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class PuntuazioTabla extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BufferedReader br;
	private KonexioDB c = new KonexioDB();
	private JLabel lblNewLabel_1;
	private JTextArea textArea;

	
	String punt ="IZENA                   ONTZIMOTA                   PUNTUAZIOA\n";
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PuntuazioTabla frame = new PuntuazioTabla();
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
	public PuntuazioTabla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PUNTUAZIOAK");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(520, 11, 196, 34);
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 25));
		contentPane.add(lblNewLabel);
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("/def/Estrellas.png")));
		c.konektatu();
		contentPane.add(getTextArea());
		lblNewLabel_1 = background;
		lblNewLabel_1.setBounds(0, 0, 1186, 703);
		contentPane.add(lblNewLabel_1);
		
		
		punt += "--------------------------------------------------------------\n";
		punt += c.puntuazioakErakutsi();
		textArea.setText(punt);
		
		
		
		System.out.println(punt);
		
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBounds(106, 83, 960, 495);
	        textArea.setOpaque(false);
	        textArea.setForeground(Color.WHITE);
		    textArea.setEditable(false);
		    textArea.setText(punt);
		    textArea.setForeground(new Color(255, 255, 255));
		    textArea.setOpaque(false);
		    textArea.setCaretColor(Color.WHITE);
		    textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		    textArea.setFont(new Font("Monospaced", Font.PLAIN, 25));
		}
		return textArea;
	}
}



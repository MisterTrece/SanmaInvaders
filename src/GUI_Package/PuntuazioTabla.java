package GUI_Package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class PuntuazioTabla extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BufferedReader br;
	private Connection konexioa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}
	
	private void konektatu(){
		try {
		//Class.forName("com.mysql.jdbc.Driver"); //deprecated
		Class.forName("com.mysql.cj.jdbc.Driver");

		String zerbitzaria = "jdbc:mysql://localhost:3306/jokoa";
		String erabiltzailea = "root";
		String pasahitza = "";
		konexioa = DriverManager.getConnection(zerbitzaria, erabiltzailea, pasahitza);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void puntuazioakErakutsi() throws SQLException {
		String query = "PUNTUAZIO TABLA:";
		PreparedStatement st = konexioa.prepareStatement(query);
		st.setString(1, "");
		ResultSet rs=st.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString("izena")+" "+rs.getString("abizena"));
		}				
	}
	}

}

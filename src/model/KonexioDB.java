package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class KonexioDB {

	
	private static Connection konexioa;
	
	public static Connection konektatu(){
		try {
		//Class.forName("com.mysql.jdbc.Driver"); //deprecated
		Class.forName("com.mysql.cj.jdbc.Driver");

		String zerbitzaria = "jdbc:mysql://localhost:3306/puntuazioak";
		String erabiltzailea = "root";
		String pasahitza = "";
		konexioa = DriverManager.getConnection(zerbitzaria, erabiltzailea, pasahitza);
		return konexioa;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String puntuazioakErakutsi() {
    	String sql = "SELECT * FROM JOKALARIA LIMIT 10";
    	String puntuazioak = "";
    	try (Connection con = konektatu();
    		    PreparedStatement ps = con.prepareStatement(sql)) {
    			ResultSet rs= ps.executeQuery();
    			while (rs.next()){
					String izen = rs.getString("IZENA");
					String ontzia = rs.getString("ONTZIMOTA");
					int punt = rs.getInt("PUNTUAZIOA");
					
					puntuazioak = puntuazioak.concat(izen+"                     "+ontzia+"                         "+punt+"\n");		
    			}
    		} catch (SQLException e) {
    		    e.printStackTrace();
    		}
    	return puntuazioak;
    }
	
	public void puntuazioakInsertatu(String pIz, String pOntz, int pPunt) {
    	String sql = "INSERT INTO JOKALARIA(IZENA, ONTZIMOTA, PUNTUAZIOA) VALUES("+"'"+pIz+"'"+",'"+ pOntz+"','"+pPunt+"')";
    	try (Connection con = konektatu();
    		    PreparedStatement ps = con.prepareStatement(sql)) {
    			ps.executeUpdate();
    		} catch (SQLException e) {
    		    e.printStackTrace();
    		}
    }
	
	
}

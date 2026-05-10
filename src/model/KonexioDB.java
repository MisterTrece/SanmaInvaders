package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KonexioDB {

	
	private static Connection konexioa;
	private static final String DB_URL = "jdbc:sqlite:puntuazioak.db";
	
	public static Connection konektatu(){
		try {
			Class.forName("org.sqlite.JDBC");
			konexioa = DriverManager.getConnection(DB_URL);
			return konexioa;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void initDb() {
		String sql = "CREATE TABLE IF NOT EXISTS JOKALARIA ("
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "IZENA TEXT, "
				+ "ONTZIMOTA TEXT, "
				+ "PUNTUAZIOA INTEGER DEFAULT 0"
				+ ")";
		try (Connection con = konektatu();
				Statement st = con.createStatement()) {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String puntuazioakErakutsi() {
	    String sql = "SELECT IZENA, ONTZIMOTA, PUNTUAZIOA FROM JOKALARIA ORDER BY PUNTUAZIOA DESC LIMIT 5";
    	String puntuazioak = "";
    	try (Connection con = konektatu();
    		    PreparedStatement ps = con.prepareStatement(sql)) {
    			ResultSet rs= ps.executeQuery();
    			while (rs.next()){
					String izen = rs.getString("IZENA");
					String ontzia = rs.getString("ONTZIMOTA");
					int punt = rs.getInt("PUNTUAZIOA");
					
					puntuazioak = puntuazioak.concat(String.format("%-20s %-20s %12d\n", izen, ontzia, punt));		
    			}
    		} catch (SQLException e) {
    		    e.printStackTrace();
    		}
    	return puntuazioak;
    }
	
	public void puntuazioakInsertatu(String pIz, String pOntz, int pPunt) {
	    String sql = "INSERT INTO JOKALARIA(IZENA, ONTZIMOTA, PUNTUAZIOA) VALUES(?, ?, ?)";
	    try (Connection con = konektatu();
	    		PreparedStatement ps = con.prepareStatement(sql)) {
	    		ps.setString(1, pIz);
	    		ps.setString(2, pOntz);
	    		ps.setInt(3, pPunt);
	    		ps.executeUpdate();
	    	} catch (SQLException e) {
	    	    e.printStackTrace();
	    	}
    }
	
	
}

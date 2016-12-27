package sistema;

import java.sql.*;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import eccezioni.DuplicateUsernameException;


public class DataBase {
	
	
	
	public DataBase() throws ClassNotFoundException, SQLException{
	}
	
	public void connetti() throws ClassNotFoundException, SQLException{
		// Carichiamo un driver di tipo 1 (bridge jdbc-odbc)
        this.driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        // Creiamo la stringa di connessione
        this.url = "jdbc:mysql://127.0.0.1/Progetto";
        // Otteniamo una connessione con username e password
       this.con =  DriverManager.getConnection (url, "root", "carmine");
       
	}
	
	public ResultSet query(String query){
		try {
			
			// Creiamo un oggetto Statement per poter interrogare il db
		       this.cmd = con.createStatement (); 
		    // Eseguiamo una query e immagazziniamone i risultati
			// in un oggetto ResultSet
		       ResultSet res = cmd.executeQuery(query);
			        
			  // Stampiamone i risultati riga per riga
			         //while (res.next()) {
			        	// System.out.println(res.getString("Titolo"));
			        	 //System.out.println(res.getString("columnName2"));
			       // }
			         //return res;
			         //res.close();
			       //  System.out.println("Ci sono riuscito!");
			       //cmd.close();
			       //con.close();
			       return res;
			     }catch (SQLException e) {
			          e.printStackTrace();
			     }
		return null;
	}
	
	public void fineQuery(ResultSet res) throws SQLException{
        res.close();
        
	}
	
	public boolean eseguiAggiornamento(String query) {
	      @SuppressWarnings("unused")
		int numero = 0;
	      boolean risultato = false;
	      try {
	         Statement stmt = con.createStatement();
	         numero = stmt.executeUpdate(query);
	         risultato = true;
	         stmt.close();
	      } catch (MySQLIntegrityConstraintViolationException e2){
	    	  throw new DuplicateUsernameException();
	      } catch (Exception e) {
	         e.printStackTrace();
	         errore = e.getMessage();
	         risultato = false;
	      }
	      return risultato;
	   }

	public void disconnetti() throws SQLException{
		cmd.close();
        con.close();
	}
	@SuppressWarnings("unused")
	private String errore;
	private String driver;
	private String url;
	private Connection con;
	private Statement cmd;
	
		
		
}

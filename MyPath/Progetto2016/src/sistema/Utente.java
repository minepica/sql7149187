package sistema;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eccezioni.MissCampoException;
import eccezioni.MissDigitException;
import eccezioni.MissUpperException;
import eccezioni.MissUtenteException;

public class Utente {

	public Utente(){
		this.username = null;
		this.password = null;
		this.nome = null;
		this.sede = null;
		this.tipo = null;
	}
	
	public Utente(String username, String password, String nome, String sede, String tipo) throws ClassNotFoundException, SQLException {
		verificaPass(password);
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.sede = sede;
		this.tipo = tipo;
		
		this.salvaUtente();
	}
	
	public Utente(JTextField user, JPasswordField pass, JTextField nome, JTextField sede, JComboBox<String> tipo) throws ClassNotFoundException, SQLException{
		
		
		verificaCampo(user.getText());
		verificaCampo(String.valueOf(pass.getPassword()));
		verificaCampo(nome.getText());
		verificaCampo(sede.getText());
		verificaCampo((String)tipo.getSelectedItem());
		
		verificaPass(String.valueOf(pass.getPassword()));
		
		this.username = user.getText();
		this.password = String.valueOf(pass.getPassword());
		this.nome = nome.getText();
		this.sede = sede.getText();
		this.tipo = (String)tipo.getSelectedItem();
		
		this.salvaUtente();
	}

	
	public void verificaPass(String pass){
		boolean dig = false, maiusc = false;
		for(int i = 0; i < pass.length(); i++){
			if(Character.isDigit(pass.charAt(i)))
				dig = true;
			if(Character.isUpperCase(pass.charAt(i)))
				maiusc = true;
		}

		if(!dig){
			throw new MissDigitException("Necessita di almeno un numero");
		}else if(!maiusc){
			throw new MissUpperException("Necessita di almeno una maiuscola");
		}
	}
	
	public void verificaCampo(String campo){
		if(campo == null || campo.equals(""))
			throw new MissCampoException("Manca un campo!");
	}
	
	
	public void salvaUtente() throws ClassNotFoundException, SQLException{
		
			DataBase db = new DataBase();
			db.connetti();
		
			String qry  = ("INSERT INTO " + this.tipo + " VALUES(null,'"+ this.username + "','"
				+ this.password + "','" + this.nome + "','" + this.sede + "')");
		
			db.eseguiAggiornamento(qry);
	}
	
	
	public Utente caricaUtente(String user, String pass, String tipo) throws ClassNotFoundException, SQLException{
			this.tipo = tipo;
			DataBase db = new DataBase();
			db.connetti();
	
			String qry  = ("SELECT * FROM "+tipo+" WHERE username ='"+user+"' AND pass ='"+pass+"'");
	
			ResultSet rs = db.query(qry);
			
			if(rs.next()){
				this.username = rs.getString("username");
				this.password = rs.getString("pass");
				this.sede = rs.getString("sede");
				this.nome = rs.getString("nome");
				this.codice = rs.getString("codice");
				return this;
			}
			
			throw new MissUtenteException();
			
	}
	
	


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		verificaPass(password);
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + ", password=" + password + ", nome=" + nome + ", sede=" + sede
				+ ", tipo=" + tipo + "]";
	}






	private String username, password, nome, sede, tipo, codice;
}

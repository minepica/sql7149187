package grafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import eccezioni.DuplicateUsernameException;
import eccezioni.MissCampoException;
import eccezioni.MissDigitException;
import eccezioni.MissUpperException;
import sistema.Utente;

public class Registrazione extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Registrazione() {
		layout = new GridBagLayout();
		
		this.setLayout(layout);
		lim = new GridBagConstraints();
		
		paint();
	}
	
	
	
	
	private void paint() {
		welcome = new JLabel("Registrazione");
		welcome.setFont(welcomefont);
		welcome.setForeground(testo);
		welcome.setHorizontalAlignment(JLabel.CENTER);
		
		lim.gridwidth = 2;
		lim.insets.bottom = 20;
		
		
		
		aggiungi(welcome, 0, 0);
		lim.gridwidth = 1;// reset gridwidth
		lim.insets.bottom = 0; //reset insets
		
		
		
		username = new JLabel("Username");
		lim.anchor = GridBagConstraints.EAST;
		configura(username);
		aggiungi(username, 0, 1);
		
		
		
		
		usertxt = new JTextField(10);
		lim.anchor = GridBagConstraints.CENTER;
		configuratxt(usertxt);
		aggiungi(usertxt, 1, 1);
		
		
		
		usererr = new JLabel("Username già esistente");
		usererr.setVisible(false);
		configura(usererr);
		usererr.setForeground(Color.red);
		aggiungi(usererr, 1, 2);
		
		
		
		password = new JLabel("Password");
		lim.anchor = GridBagConstraints.EAST;
		configura(password);
		aggiungi(password, 0, 3);
		
		
		
		passtxt = new JPasswordField(10);
		lim.anchor = GridBagConstraints.CENTER;
		configuraPassTxt(passtxt);
		aggiungi(passtxt, 1, 3);
		
		
		
		passerr = new JLabel("MIN 1 num e 1 maiusc");
		passerr.setVisible(false);
		configura(passerr);
		passerr.setForeground(Color.RED);
		aggiungi(passerr, 1, 4);
		
		
		
		nome = new JLabel("Nome");
		lim.anchor = GridBagConstraints.EAST;
		configura(nome);
		aggiungi(nome,0,5);
		
		
		
		nometxt = new JTextField(10);
		lim.anchor = GridBagConstraints.CENTER;
		configuratxt(nometxt);
		aggiungi(nometxt, 1, 5);
		
		
		
		sede = new JLabel("Sede");
		lim.anchor = GridBagConstraints.EAST;
		configura(sede);
		aggiungi(sede, 0, 6);
		
		
		
		sedetxt = new JTextField(10);
		lim.anchor = GridBagConstraints.CENTER;
		configuratxt(sedetxt);
		aggiungi(sedetxt, 1, 6);
		
		
		
		tipo = new JLabel("Tipo");
		lim.anchor = GridBagConstraints.EAST;
		configura(tipo);
		aggiungi(tipo, 0, 7);
	
	
		
		tipobox = new JComboBox<String>();
		tipobox.addItem("Agenzia");
		tipobox.addItem("Gestore");
		tipobox.setBackground(backtext);
		tipobox.setPreferredSize(new Dimension(210, 30));
		lim.anchor = GridBagConstraints.CENTER;
		configura(tipobox);
		aggiungi(tipobox,1,7);
		
		
		
		campoerr = new JLabel("Non è possibile lasciare campi vuoti!");
		configura(campoerr);
		campoerr.setForeground(Color.RED);
		campoerr.setVisible(false);
		lim.gridwidth = 3;
		aggiungi(campoerr, 0, 8);
		
		
		
		completa = new JButton("Completa");
		configura(completa);
		lim.insets.left = 0;
		lim.gridwidth = 3;
		lim.fill = GridBagConstraints.BOTH;
		aggiungi(completa, 0, 9);
		
		
		//Action
		completa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				campoerr.setVisible(false);
				passerr.setVisible(false);
				usererr.setVisible(false);
				
				try {
					new Utente(usertxt, passtxt, nometxt, sedetxt, tipobox);
					
					Component c = (Component) e.getSource();
					JFrame j = (JFrame)SwingUtilities.getRoot(c);
					j.getContentPane().remove(0);
					j.getContentPane().add(new Login());
					JPanel p = (JPanel)j.getContentPane().getComponent(0);
					p.updateUI();
					
					j.getContentPane().repaint();
					j.setTitle("Login");
					
					
				} catch (MissCampoException e1){
					campoerr.setVisible(true);
				} catch (MissDigitException | MissUpperException e2){
					passerr.setVisible(true);
				} catch (DuplicateUsernameException e3){
					usererr.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	private void configura(JComponent j){
		j.setFont(testofont);
		j.setForeground(testo);
	}
	
	private void configuratxt(JTextField j){
		j.setFont(testofont);
		j.setForeground(testo);
		j.setBackground(backtext);
	}
	
	private void configuraPassTxt(JPasswordField j){
		j.setFont(testofont);
		j.setForeground(testo);
		j.setBackground(backtext);
	}

	private void aggiungi(JComponent j, int x, int y){
		lim.gridx = x;
		lim.gridy = y;
		
		layout.setConstraints(j, lim);
		this.add(j);
	}


	private GridBagLayout layout;
	private GridBagConstraints lim;
	
	private JLabel welcome, username, password, nome, sede, tipo;
	private JLabel usererr, passerr, campoerr;
	private JTextField usertxt, nometxt, sedetxt;
	private JPasswordField passtxt;
	private JButton completa;
	private JComboBox<String> tipobox;
	
	private static Color testo = new Color(149, 149, 149);
	private static Color backtext =  new Color(216, 216, 216);
	
	private static Font testofont =  new Font("American Typewriter", Font.BOLD , 20);
	private static Font welcomefont = new Font("American Typewriter", Font.BOLD , 55);

}

package grafica;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import eccezioni.MissUtenteException;
import sistema.Utente;

public class Login extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Login(){
		layout = new GridBagLayout();
		
		this.setLayout(layout);
		lim = new GridBagConstraints();
		this.setBackground(new Color(208, 208, 208));
		
		welcome();
		campi();	
	}
		
	private void welcome() {
		welcome = new JLabel("LOGIN");
		welcome.setFont(welcomefont);
		welcome.setForeground(testo);
		welcome.setHorizontalAlignment(JLabel.CENTER);
		
		lim.gridx = 0;
		lim.gridy = 0;
		lim.gridwidth = 2;
		lim.gridheight = 1;
		lim.fill = GridBagConstraints.HORIZONTAL;
		lim.insets.bottom = 20;
		
		layout.setConstraints(welcome, lim);
		this.add(welcome);
	}

	private void campi() {
		nickname = new JLabel("Username");
		nickname.setFont(testofont);
		nickname.setForeground(testo);
		nickname.setHorizontalAlignment(JLabel.CENTER);
		
		lim.gridx = 0;
		lim.gridy = 2;
		lim.gridwidth = 1;
		lim.gridheight = 1;
		lim.insets.left = 0;
		lim.fill = 0;
		lim.insets.bottom = 0;
		lim.anchor = GridBagConstraints.EAST;
		
		layout.setConstraints(nickname, lim);
		this.add(nickname);
		
		
		
		txtnickname = new JTextField(10);
		txtnickname.setFont(testofont);
		txtnickname.setForeground(testo);
		txtnickname.setBackground(backtext);
		
		lim.gridx = 1;
		lim.gridy = 2;
		lim.anchor = GridBagConstraints.CENTER;
		
		layout.setConstraints(txtnickname, lim);
		this.add(txtnickname);
		
		
		
		nickerr = new JLabel("Errore Username");
		nickerr.setVisible(false);
		nickerr.setFont(testofont);
		nickerr.setForeground(Color.red);
		
		lim.gridx = 1;
		lim.gridy = 3;
		lim.insets.bottom = 20;
		lim.anchor = GridBagConstraints.CENTER;
		
		layout.setConstraints(nickerr, lim);
		this.add(nickerr);
		
		
		
		password = new JLabel("Password");
		password.setFont(testofont);
		password.setForeground(testo);
		
		lim.gridx = 0;
		lim.gridy = 4;
		lim.insets.bottom = 0;
		lim.anchor = GridBagConstraints.EAST;
		
		layout.setConstraints(password, lim);
		this.add(password);
		
		
		
		txtpassword = new JPasswordField(10);
		txtpassword.setFont(testofont);
		txtpassword.setForeground(testo);
		txtpassword.setBackground(backtext);
		
		lim.gridx = 1;
		lim.gridy = 4;
		lim.anchor = GridBagConstraints.CENTER;
		
		layout.setConstraints(txtpassword, lim);
		this.add(txtpassword);
		
		
		
		passerr = new JLabel("Errore Password");
		passerr.setVisible(false);
		passerr.setFont(testofont);
		passerr.setForeground(Color.red);
		
		lim.gridx = 1;
		lim.gridy = 5;
		
		layout.setConstraints(passerr, lim);
		this.add(passerr);
		
		
		tipo = new JLabel("Tipo");
		tipo.setFont(testofont);
		tipo.setForeground(testo);
		
		lim.gridx = 0;
		lim.gridy = 6;
		lim.anchor = GridBagConstraints.EAST;
		
		layout.setConstraints(tipo, lim);
		this.add(tipo);
		
		
		
		tipobox = new JComboBox<String>();
		tipobox.addItem("Agenzia");
		tipobox.addItem("Gestore");
		tipobox.setForeground(testo);
		tipobox.setFont(testofont);
		tipobox.setBackground(backtext);
		
		lim.gridx = 1;
		lim.gridy = 6;
		lim.anchor = GridBagConstraints.CENTER;
		lim.fill = GridBagConstraints.BOTH;
		
		layout.setConstraints(tipobox, lim);
		this.add(tipobox);
		
		
		
		accedi = new JButton("Accedi");
		accedi.setFont(testofont);
		accedi.setForeground(testo);
		accedi.setBackground(backtext);
		
		lim.gridx = 0;
		lim.gridy = 7;
		lim.insets.top = 20;
		lim.fill = GridBagConstraints.BOTH;
		
		layout.setConstraints(accedi, lim);
		this.add(accedi);
		
		//Action
		accedi.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				
				nickerr.setVisible(false);
				passerr.setVisible(false);
				
				
				Utente u = new Utente();
				try {
					u.caricaUtente(txtnickname.getText(), String.valueOf(txtpassword.getPassword()), (String)tipobox.getSelectedItem());
					
					Component c = (Component) e.getSource();
					JFrame j = (JFrame)SwingUtilities.getRoot(c);
					j.getContentPane().remove(0);
					
					if(u.getTipo().equals("Gestore"))
						j.getContentPane().add(new Gestore(u));
					if(u.getTipo().equals("Agenzia"))
						j.getContentPane().add(new Agenzia(u));
					
					JPanel p = (JPanel)j.getContentPane().getComponent(0);
					p.updateUI();
					
					j.getContentPane().repaint();
					j.setTitle(u.getNome());
					//j.pack();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MissUtenteException e2){
					nickerr.setVisible(true);
					passerr.setVisible(true);
				}
				
			}
		});
		
		registrati = new JButton("Registrati");
		registrati.setFont(testofont);
		registrati.setForeground(testo);
		registrati.setBackground(backtext);
		
		lim.gridx = 1;
		lim.gridy = 7;
		
		layout.setConstraints(registrati, lim);
		this.add(registrati);
		
		//Action
		
		registrati.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Component c = (Component) e.getSource();
				JFrame j = (JFrame)SwingUtilities.getRoot(c);
				j.getContentPane().remove(0);
				j.getContentPane().add(new Registrazione());
				JPanel p = (JPanel)j.getContentPane().getComponent(0);
				p.updateUI();
				
				j.getContentPane().repaint();
				j.setTitle("Registrazione");
			}
		});
	}






	private GridBagConstraints lim;
	private JLabel welcome, nickname, nickerr, password, passerr, tipo;
	private JTextField txtnickname;
	private JPasswordField txtpassword;
	private JButton accedi, registrati;
	private GridBagLayout layout;
	private JComboBox<String> tipobox;
	
	private static Color testo = new Color(149, 149, 149);
	private static Color backtext =  new Color(216, 216, 216);
	
	private static Font testofont =  new Font("American Typewriter", Font.BOLD , 20);
	private static Font welcomefont = new Font("American Typewriter", Font.BOLD , 55);
	

}

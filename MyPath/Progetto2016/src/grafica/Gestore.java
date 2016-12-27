package grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import sistema.Utente;

public class Gestore extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Gestore(Utente u){
		this.u = u;
		
		layout = new GridBagLayout();
		
		this.setLayout(layout);
		lim = new GridBagConstraints();
		
		paint();
	}

	private void paint() {
		// TODO Auto-generated method stub
		
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

	private Utente u;
	
	private GridBagLayout layout;
	private GridBagConstraints lim;
	
	private static Color testo = new Color(149, 149, 149);
	private static Color backtext =  new Color(216, 216, 216);
	
	private static Font testofont =  new Font("American Typewriter", Font.BOLD , 20);
	private static Font welcomefont = new Font("American Typewriter", Font.BOLD , 55);
}



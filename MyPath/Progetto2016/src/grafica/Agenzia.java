package grafica;



//Ciao Lugi come sta? 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import sistema.Utente;

public class Agenzia extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Agenzia(Utente u){
		this.u = u;
		
		layout = new GridBagLayout();
		
		this.setLayout(layout);
		lim = new GridBagConstraints();
		
		paint();
		
		
	}
	
	private void paint() {
		paintInfo();
		
	}
	
	private void paintInfo(){
		info = new JPanel();
		info.setBackground(backtext);
		
		lim.gridx = 0;
		lim.gridy = 0;
		
		
		lim.gridwidth = 10;
		

		lim.fill = GridBagConstraints.HORIZONTAL;
		lim.anchor = GridBagConstraints.NORTHWEST;
		
		
		codice = new JLabel("<html><b>Codice: </b>"+ u.getCodice()+"&nbsp;</html>");
		codice.setPreferredSize(new Dimension(267, 30));
		configura(codice);
		info.add(codice);
		
		nome = new JLabel("<html><b>Nome: </b>"+ u.getNome()+"&nbsp;&nbsp;&nbsp;</html>");
		nome.setPreferredSize(new Dimension(267, 30));
		configura(nome);
		info.add(nome);
		
		sede = new JLabel("<html><b>Sede: </b>"+ u.getSede()+"\t</html>");
		sede.setPreferredSize(new Dimension(267, 30));
		configura(sede);
		info.add(sede);
		
		layout.setConstraints(info, lim);
		this.add(info);
		
		JButton b = new JButton("Prova");
		lim.gridx = 0;
		lim.gridy = 1;
		
		
		lim.gridwidth = 1;
		

		lim.fill = GridBagConstraints.BOTH;
		lim.anchor = GridBagConstraints.NORTHWEST;
		
		//lim.weightx = 0.5;
		//lim.weighty = 0;
		layout.setConstraints(b, lim);
		this.add(b);
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Component c = (Component) e.getSource();
				JFrame j = (JFrame)SwingUtilities.getRoot(c);
				j.pack();
				
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
	
	private void aggiungi(JComponent j, int x, int y){
		lim.gridx = x;
		lim.gridy = y;
		
		layout.setConstraints(j, lim);
		this.add(j);
	}


	private JPanel info;
	private JLabel codice, nome, sede;

	private Utente u;
	
	private GridBagLayout layout;
	private GridBagConstraints lim;
	
	private static Color testo = new Color(149, 149, 149);
	private static Color backtext =  new Color(216, 216, 216);
	
	private static Font testofont =  new Font("American Typewriter", Font.PLAIN , 20);
	private static Font welcomefont = new Font("American Typewriter", Font.BOLD , 55);

}

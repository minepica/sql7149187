package grafica;

import javax.swing.JFrame;

import sistema.Utente;



public class Tester {

	public static void main(String[] args) {
		JFrame main = new JFrame("GestoreParchi");
		main.setSize(803, 756);
			
				
			
			main.add(new Login());
			//main.add(new Registrazione());
			//main.pack();
			//main.add(new Agenzia(new Utente()));
			
		
		
		
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setResizable(false);
	}

}

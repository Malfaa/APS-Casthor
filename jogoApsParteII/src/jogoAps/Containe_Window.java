package jogoAps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
//// tela
public class Containe_Window extends JFrame {
	public Containe_Window() {
		
		/*
		JMenuBar barraMenu = new JMenuBar();
		
		JMenu menu = new JMenu("Menu");

		JMenuItem sobre = new JMenuItem("Sobre");
		sobre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Jogo desenvolvido por CasthorCompany Tm!", "Informações", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		*/
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		//////////////////////////////
		
		add(new fase());

		setTitle("Lord of Casthor Tm"); //titulo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fecha ao clicar no X
		setSize(565,300);// tamanho da tela 450,300
		setLocationRelativeTo(null); //positicao onde  a tela aparece  null = centro	
		setResizable(false); // travar a resolucao da tela
		setVisible(true); //visibilidade da tela
		
		///////////////////////////////
	}
	public static void main(String[] args) {
		new Containe_Window();
		////////////////////////////////
	}
}

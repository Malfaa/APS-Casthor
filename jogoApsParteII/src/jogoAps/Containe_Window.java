package jogoAps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

//// tela
public class Containe_Window extends JFrame {
	public Containe_Window() {

		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		//////////////////////////////
		add(new fase());
		setTitle("Lord of Casthor Tm"); //titulo do gamezinho
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fecha ao clicar no X
		setSize(565,300);// tamanho da tela 450,300
		setLocationRelativeTo(null); //positicao onde  a tela aparece  null = centro	
		setResizable(false); // travar a resolucao da tela
		setVisible(true); //visibilidade da tela
 
		/////////////////////////////////////////////////////////////////////////////////////////////

	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		new menu();

		////////////////////////////////
	}
}

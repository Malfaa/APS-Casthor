package jogoAps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class fase extends JPanel implements ActionListener{
	
	
	private Image fundo;
	private	perso  personagem;
	private	Timer	timer;
	
	public fase() {
		
		setDoubleBuffered(true);
		setFocusable(true); // personagem em foco para conseguir mexer
		addKeyListener(new tecladoAdapter());
		
		ImageIcon referencia = new ImageIcon("res\\fundo.gif"); // imagem do fundo
		fundo = referencia.getImage();
		
		personagem = new perso();// persnagem
		
		timer = new Timer(5, this); // velocidade q a tela repinta o fundo
		timer.start();
	}
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(personagem.getImagem(), personagem.getX(), personagem.getY(), this);
		
		List<fire> fires = personagem.getFires();
		
		for (int i = 0; i < fires.size() ; i++ ) {
			
			fire f = (fire) fires.get(i);
			graficos.drawImage(f.getImagem(), f.getX(), f.getY(), this);
		}
		
		g.dispose();
		
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		List<fire> fires = personagem.getFires();
		for (int i = 0; i < fires.size() ; i++) {
			fire f = (fire) fires.get(i);
			
			if(f.isVisivel()) {
				f.mexer();
			}else {
				fires.remove(i);
			}
			
		}
		
		personagem.mexer();
		repaint();
		
	}
	private class tecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			personagem.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			personagem.keyReleased(e);
		}
		
}
}
package jogoAps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class fase extends JPanel implements ActionListener{
	
	private lenhador lenha;
	private Image fundo;
	private	perso  personagem;
	private	Timer	timer;
	private int inimigos = 5;
	private boolean emJogo;
	private Image vermelho;
	private Image branco;
	private Image gameOver;
	private Image concluida;
	
	private List<lenhador> lenhador;
	private int [][] coordenadas = {{565, 210}};
	
	public fase() {
		
		setDoubleBuffered(true);
		setFocusable(true); // personagem em foco para conseguir mexer
		addKeyListener(new tecladoAdapter());
		
		ImageIcon referencia = new ImageIcon("jogoApsParteII\\src\\res\\fundo.gif"); // imagem do fundo
		fundo = referencia.getImage();
		ImageIcon Vida = new ImageIcon("jogoApsParteII\\src\\res\\comVida.png");
		vermelho = Vida.getImage();
		ImageIcon VidaB = new ImageIcon("jogoApsParteII\\src\\res\\semVida.png");
		branco = VidaB.getImage();
		ImageIcon fimJogo = new ImageIcon("jogoApsParteII\\src\\res\\GameOverT.gif");// Novo design
		gameOver = fimJogo.getImage();
		ImageIcon faseConcluida = new ImageIcon("jogoApsParteII\\src\\res\\faseConcluidaT.gif"); // Novo design
		concluida = faseConcluida.getImage();
		
		personagem = new perso();// persnagem
		
		emJogo = true;
		
		inicializaLenhador();
		
		timer = new Timer(5, this); // velocidade q a tela repinta o fundo
		timer.start();
	}
	
	public void inicializaLenhador() {
		
		lenhador = new ArrayList<lenhador>();
		
		for(int i = 0; i<coordenadas.length; i++) {
			lenhador.add(new lenhador(coordenadas[i][0], coordenadas[i][1]));
		}
		}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(vermelho, 510, 15, null);
		graficos.drawImage(vermelho, 460, 15, null);
		graficos.drawImage(vermelho, 410, 15, null);
		
		if (emJogo) {
		
			graficos.drawImage(personagem.getImagem(), personagem.getX(), personagem.getY(), this);
			
			List<fire> fires = personagem.getFires();
			
			for (int i = 0; i < fires.size() ; i++ ) {
				
				fire f = (fire) fires.get(i);
				graficos.drawImage(f.getImagem(), f.getX(), f.getY(), this);
			}
			
			for (int i = 0; i < lenhador.size() ; i++ ) {
				
				lenhador in = lenhador.get(i);
				graficos.drawImage(in.getLenhador(), in.getX(), in.getY(), this);
			}

			graficos.drawString("INIMIGOS RESTANTES: "+ getInimigos(), 5, 15);
		} 
		
			if(getInimigos() == 0) {
				
				graficos.drawImage(concluida, 25, 88, null);
			
			}
			if(personagem.getVidas() == 2) {
				graficos.drawImage(branco, 510, 15, null);
			}
			
			if(personagem.getVidas() == 1) {

				graficos.drawImage(branco, 510, 15, null);
				graficos.drawImage(branco, 460, 15, null);
			}
			
			if(personagem.getVidas() == 0) {
				
				graficos.drawImage(branco, 510, 15, null);
				graficos.drawImage(branco, 460, 15, null);
				graficos.drawImage(branco, 410, 15, null);
				graficos.drawImage(gameOver, 45, 70, null);
				personagem.setVisivel(false);
				emJogo = false;
				
			}
		g.dispose();
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(lenhador.size() == 0) {
			emJogo = false;
		}
		
		List<fire> fires = personagem.getFires();
		
		for (int i = 0; i < fires.size() ; i++) {
			
			fire f = (fire) fires.get(i);
			
			if(f.isVisivel()) {
				f.mexer();
			}else {
				fires.remove(i);
			}
			
		}
		
		for (int i = 0; i < lenhador.size() ; i++) {
			
			lenhador in = lenhador.get(i);
			
			if(in.isVisivel()) {
				in.mexer();
			}else {
				lenhador.remove(i);
			}
			
		}
		
		personagem.mexer();
		repaint();
		
		if(getInimigos() != 0 && personagem.getVidas() !=0) {
			
			checarColisoes();
		
		}
	}
	
	public void checarColisoes() {
		
		Rectangle formaPerso =personagem.getBounds();
		Rectangle formaFire;
		Rectangle formaLenhador;
	
		for(int i = 0; i<lenhador.size(); i++) {
			
			lenhador tempLenhador = lenhador.get(i);
			formaLenhador = tempLenhador.getBounds();
			
			if(formaPerso.intersects(formaLenhador)) {
				
				if(personagem.getVidas() != 0) {
		
					tempLenhador.setVisivel(false);
					personagem.setVidas(personagem.getVidas()-1);
					inicializaLenhador();
					
				}
			}
		}
		List<fire> fire = personagem.getFires();
		
		for(int i = 0; i<fire.size(); i++) {
			
			fire tempFire = fire.get(i);
			formaFire = tempFire.getBounds();
			
			for(int j = 0; j< lenhador.size(); j++) {
				
				lenhador tempLenhador = lenhador.get(j);
				formaLenhador = tempLenhador.getBounds();
				
				if(formaFire.intersects(formaLenhador)) {
					
					tempLenhador.setVisivel(false);
					tempFire.setVisivel(false);
					inicializaLenhador();
					setInimigos(getInimigos()-1);
					
				}
				
				if (getInimigos() == 0) {
					tempLenhador.setVisivel(false);
					tempFire.setVisivel(false);
					personagem.setVisivel(false);
					emJogo = false;
					
				}
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
	
	public int getInimigos() {
		return inimigos;
	}
	
	public void setInimigos(int inimigos) {
		this.inimigos = inimigos;
	}
}
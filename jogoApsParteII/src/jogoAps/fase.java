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

public class fase extends JPanel implements ActionListener {

	private lenhador lenha;
	private Image fundo;
	private LenhadorAtirador atirador;
	private perso personagem;
	private Timer timer;
	private int inimigos = 100;
	private boolean emJogo;
	private Image vermelho, branco, gameOver, concluida, meiaVida;
	private int contador, contador2;
	private int sequencia;

	private List<lenhador> lenhador;
	private int[][] coordenadas = { { 565, 210 } };

	public fase() {

		setDoubleBuffered(true);
		setFocusable(true); // personagem em foco para conseguir mexer
		addKeyListener(new tecladoAdapter());

		ImageIcon referencia = new ImageIcon("res\\fundo.gif"); // imagem do fundo
		fundo = referencia.getImage();
		ImageIcon Vida = new ImageIcon("res\\comVida.png");
		vermelho = Vida.getImage();
		ImageIcon VidaB = new ImageIcon("res\\semVida.png");
		branco = VidaB.getImage();
		ImageIcon fimJogo = new ImageIcon("res\\GameOverT.gif");
		gameOver = fimJogo.getImage();
		ImageIcon faseConcluida = new ImageIcon("res\\faseConcluidaT.gif");
		concluida = faseConcluida.getImage();
		ImageIcon meiaV = new ImageIcon("res\\meiaVida.png");
		meiaVida = meiaV.getImage();

		personagem = new perso();// persnagem

		atirador = new LenhadorAtirador();

		emJogo = true;

		inicializaLenhador();

		timer = new Timer(5, this); // velocidade q a tela repinta o fundo
		timer.start();
	}

	public void inicializaLenhador() {

		lenhador = new ArrayList<lenhador>();

		for (int i = 0; i < coordenadas.length; i++) {
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

			if (contador >= 500) {
				atirador.setVisivel(true);
				if (atirador.isVisivel()) {
					graficos.drawImage(atirador.getImagem(), atirador.getX(), atirador.getY(), this);
				}
			}

			List<fire> fires = personagem.getFires();

			List<fire> fireAtirador = atirador.getFires();

			for (int i = 0; i < fires.size(); i++) {

				fire f = (fire) fires.get(i);
				graficos.drawImage(f.getImagem(), f.getX(), f.getY(), this);
			}

			for (int i = 0; i < fireAtirador.size(); i++) {

				fire f = (fire) fireAtirador.get(i);
				if (f.isVisivel()) {
					graficos.drawImage(f.getImagem(), f.getX(), f.getY(), this);
				}
			}

			for (int i = 0; i < lenhador.size(); i++) {

				lenhador in = lenhador.get(i);
				graficos.drawImage(in.getLenhador(), in.getX(), in.getY(), this);
			}

			graficos.drawString("INIMIGOS RESTANTES: " + getInimigos(), 5, 15);
		}

		if (getInimigos() == 0) {

			graficos.drawImage(concluida, 50, 90, null);
			emJogo = false;

		}

		if (personagem.getVidas() == 2.5) {
			graficos.drawImage(meiaVida, 510, 15, null);
			sequencia = 0;
		}

		if (personagem.getVidas() == 2) {
			graficos.drawImage(branco, 510, 15, null);
			sequencia = 0;
		}

		if (personagem.getVidas() == 1.5) {
			graficos.drawImage(branco, 510, 15, null);
			graficos.drawImage(meiaVida, 460, 15, null);
			sequencia = 0;
		}

		if (personagem.getVidas() == 1) {
			graficos.drawImage(branco, 510, 15, null);
			graficos.drawImage(branco, 460, 15, null);
			sequencia = 0;
		}

		if (personagem.getVidas() == 0.5) {
			graficos.drawImage(branco, 510, 15, null);
			graficos.drawImage(branco, 460, 15, null);
			graficos.drawImage(meiaVida, 410, 15, null);
			sequencia = 0;
		}

		if (personagem.getVidas() == 0) {

			graficos.drawImage(branco, 510, 15, null);
			graficos.drawImage(branco, 460, 15, null);
			graficos.drawImage(branco, 410, 15, null);
			graficos.drawImage(gameOver, 50, 60, null);
			personagem.setVisivel(false);
			emJogo = false;

		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		System.out.println(sequencia);
		contador++;
		contador2++;

		if (contador2 > 600) {
			inicializaLenhador();
			contador2 = 0;
		}

		List<fire> fires = personagem.getFires();

		List<fire> fireAtirador = atirador.getFires();

		for (int i = 0; i < fires.size(); i++) {

			fire f = (fire) fires.get(i);

			if (f.isVisivel() && sequencia < 10) {

				f.mexer();

			} else if (f.isVisivel() && sequencia >= 10) {
				f.mexerS();
			} else {
				fires.remove(i);
			}
		}

		for (int i = 0; i < fireAtirador.size(); i++) {

			fire f = (fire) fireAtirador.get(i);

			if (f.isVisivel()) {
				if (sequencia < 10) {
					f.mexerEsquerdaMachado();
				}

			}

		}

		for (int i = 0; i < lenhador.size(); i++) {

			lenhador in = lenhador.get(i);

			if (in.isVisivel()) {
				in.mexer();
			} else {
				lenhador.remove(i);
			}

		}
		if (atirador.isVisivel()) {
			atirador.atira();
		}
		personagem.mexer();
		repaint();

		if (getInimigos() != 0 && personagem.getVidas() != 0) {

			checarColisoes();

		}
	}

	public void checarColisoes() {

		Rectangle formaPerso = personagem.getBounds();
		Rectangle formaFire;
		Rectangle formaFireAtirador;
		Rectangle formaLenhador;

		for (int i = 0; i < lenhador.size(); i++) {

			lenhador tempLenhador = lenhador.get(i);
			formaLenhador = tempLenhador.getBounds();

			if (formaPerso.intersects(formaLenhador)) {

				if (personagem.getVidas() != 0) {
					if (personagem.isEscudo() && personagem.isEscudoD()) {
						personagem.setVidas(personagem.getVidas() - 0.5);
						tempLenhador.setVisivel(false);
					} else if (personagem.isEscudo() && personagem.isEscudoE()) {
						personagem.setVidas(personagem.getVidas() - 1);
						tempLenhador.setVisivel(false);
					} else {
						personagem.setVidas(personagem.getVidas() - 1);
						tempLenhador.setVisivel(false);
					}
				}
			}
		}
		if (atirador.isVisivel()) {
			List<fire> fireAtirador = atirador.getFires();
			Rectangle formaAtirador = atirador.getBounds();
			for (int i = 0; i < fireAtirador.size(); i++) {

				fire tempFire = fireAtirador.get(i);
				if (tempFire.isVisivel()) {
					formaFire = tempFire.getBounds();
					if (formaPerso.intersects(formaAtirador)) {

						tempFire.setVisivel(false);
						personagem.setVidas(personagem.getVidas() - 1);
						this.contador = 0;
					}
					if (formaFire.intersects(formaPerso)) {
						if (personagem.isEscudo() && personagem.isEscudoD()) {
							tempFire.setVisivel(false);
						} else if (personagem.isEscudo() && personagem.isEscudoE()) {
							tempFire.setVisivel(false);
							personagem.setVidas(personagem.getVidas() - 1);
						} else {
							tempFire.setVisivel(false);
							personagem.setVidas(personagem.getVidas() - 1);
						}
					}
				}
			}
			List<fire> fire = personagem.getFires();

			for (int i = 0; i < fire.size(); i++) {

				fire tempFire = fire.get(i);
				formaFire = tempFire.getBounds();

				if (formaFire.intersects(formaAtirador)) {

					tempFire.setVisivel(false);
					atirador.contador = 0;
					atirador.setVisivel(false);
					setInimigos(getInimigos() - 1);
					this.contador = 0;
					sequencia++;
				}
			}
		}
		List<fire> fire = personagem.getFires();

		for (int i = 0; i < fire.size(); i++) {

			fire tempFire = fire.get(i);
			formaFire = tempFire.getBounds();

			for (int j = 0; j < lenhador.size(); j++) {

				lenhador tempLenhador = lenhador.get(j);
				formaLenhador = tempLenhador.getBounds();

				if (formaFire.intersects(formaLenhador)) {

					tempLenhador.setVisivel(false);
					tempFire.setVisivel(false);
					setInimigos(getInimigos() - 1);
					sequencia++;

				}

			}
		}
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
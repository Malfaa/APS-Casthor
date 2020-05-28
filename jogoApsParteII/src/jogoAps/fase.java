package jogoAps;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

//import com.sun.prism.paint.Color;

public class fase extends JPanel implements ActionListener {

	private Image fundo;
	private LenhadorAtirador atirador, atiradorE;
	private perso personagem;
	private Boss boss;
	private Timer timer;
	private int inimigos = 5, QtdLenhadores = 4, QtdAtiradores = 0, QtdLenhadoresE = 0, QtdAtiradoresE = 0, Boss,
			fases = 1;
	private boolean emJogo, foraLoading = true, fimJogo = false, subir = true, descer = false, spawnLenhador = false,
			spawnLenhadorE = false;
	private Image vermelho, branco, gameOver, concluida, meiaVida, Loading, icone;
	private double contador, contador2, contador3, contador4, contador5, contador6, chamarLoading, voltarLoading;

	private int sequencia;


	private List<lenhador> lenhador;
	private int[][] coordenadas = { { 565, 172 } };

	private List<lenhador> lenhadorE;
	private int[][] coordenadasLE = { { -200, 172 } };

	public fase() {

		setDoubleBuffered(true);
		setFocusable(true); // personagem em foco da tela
		addKeyListener(new tecladoAdapter());

		ImageIcon referencia = new ImageIcon("jogoApsParteII\\src\\res\\fundo.gif"); // imagem do fundo
		fundo = referencia.getImage();
		ImageIcon Vida = new ImageIcon("jogoApsParteII\\src\\res\\comVida.png"); //Imagem da vida
		vermelho = Vida.getImage();
		ImageIcon VidaB = new ImageIcon("jogoApsParteII\\src\\res\\semVida.png"); //Immagem sem vida
		branco = VidaB.getImage();
		ImageIcon fimJogo = new ImageIcon("jogoApsParteII\\src\\res\\GameOverT.gif"); //GIF do game over
		gameOver = fimJogo.getImage();
		ImageIcon faseConcluida = new ImageIcon("jogoApsParteII\\src\\res\\faseConcluidaT.gif"); //GIF concluído
		concluida = faseConcluida.getImage();
		ImageIcon meiaV = new ImageIcon("jogoApsParteII\\src\\res\\meiaVida.png");// GIF meia vida
		meiaVida = meiaV.getImage();
		ImageIcon TLoading = new ImageIcon("jogoApsParteII\\src\\res\\fundoLoading.jpg"); //GIF fundo Loading
		Loading = TLoading.getImage();
		ImageIcon Icone = new ImageIcon("jogoApsParteII\\src\\res\\casthor.gif"); // GIF Casthor  Loading
		icone = Icone.getImage();

		personagem = new perso();// personagem

		atirador = new LenhadorAtirador(); // atirador direita

		atiradorE = new LenhadorAtirador(); // atirador esquerda

		boss = new Boss();

		emJogo = true;

		inicializaLenhador(); // spawn lenhador correndo direita

		inicializaLenhadorE(); // spawn lenhador correndo esquerda

		timer = new Timer(5, this); // velocidade q a tela repinta o fundo
		timer.start();

		boss.setVisivel(false);
	}

	public void inicializaLenhador() {

		lenhador = new ArrayList<lenhador>();

		for (int i = 0; i < coordenadas.length; i++) {
			lenhador.add(new lenhador(coordenadas[i][0], coordenadas[i][1]));
		}
	}

	public void inicializaLenhadorE() {

		lenhadorE = new ArrayList<lenhador>();

		for (int i = 0; i < coordenadasLE.length; i++) {
			lenhadorE.add(new lenhador(coordenadasLE[i][0], coordenadasLE[i][1]));
		}
	}

	public void paint(Graphics g) {
		if (foraLoading) {
			Graphics2D graficos = (Graphics2D) g;
			graficos.drawImage(fundo, 0, 0, null); // fundo
			graficos.drawImage(vermelho, 510, 15, null); // coração vermelho
			graficos.drawImage(vermelho, 460, 15, null); // coração vermelho
			graficos.drawImage(vermelho, 410, 15, null); // coração vermelho

			if (emJogo) {

				graficos.drawImage(personagem.getImagem(), personagem.getX(), personagem.getY(), this); // imagem
																										// personagem

				if (contador >= 500 && getQtdAtiradores() != 0) {
					atirador.setVisivel(true);
					if (atirador.isVisivel()) {
						graficos.drawImage(atirador.getImagem(), atirador.getX(), atirador.getY(), this); // imagem
																											// atirador
																											// direita
					}
				}

				if (contador3 >= 700 && getQtdAtiradoresE() != 0) {
					atiradorE.setVisivel(true);
					if (atiradorE.isVisivel()) {
						graficos.drawImage(atiradorE.getImagem(), atiradorE.getX(), atiradorE.getY(), this); // imagem
																												// atirador
																												// esquerda
					}
				}

				if (fases == 5 && contador6 > 1200) {
					if (boss.getVidas() >= 0) {
						boss.setVisivel(true);
						if (boss.getVidas() == 2) {
							graficos.drawImage(boss.getImagem(), boss.getX(), boss.getY(), this);
							graficos.drawImage(boss.getImagemVida(), boss.getX() + 15, boss.getY(), this);
						}
						if (boss.getVidas() == 1) {
							graficos.drawImage(boss.getImagem(), boss.getX(), boss.getY(), this);
							graficos.drawImage(boss.getImagemMeiaVida(), boss.getX() + 15, boss.getY(), this);
						}
					}
				}

				List<fire> fires = personagem.getFires(); // martelo personagem

				List<fire> fireAtirador = atirador.getFires(); // machado lenhador direita

				List<fire> fireAtiradorE = atiradorE.getFires(); // machado lenhador esquerda

				List<fire> fireBoss = boss.getFires();

				for (int i = 0; i < fires.size(); i++) {

					fire f = (fire) fires.get(i);
					graficos.drawImage(f.getImagem(), f.getX(), f.getY(), this); // imagem martelo personagem
				}

				for (int i = 0; i < fireAtirador.size(); i++) {

					fire f = (fire) fireAtirador.get(i);
					if (f.isVisivel()) {
						graficos.drawImage(f.getImagem(), f.getX(), f.getY(), this); // imagem machado atirador direita
					}
				}
				if (boss.isVisivel()) {
					for (int i = 0; i < fireAtiradorE.size(); i++) {

						fire f = (fire) fireAtiradorE.get(i);
						if (f.isVisivel()) {
							graficos.drawImage(f.getImagem(), f.getX(), f.getY(), this); // imagem machado atirador
																							// esquerda
						}
					}
				}
				for (int i = 0; i < fireBoss.size(); i++) {

					fire f = (fire) fireBoss.get(i);
					if (f.isVisivel()) {
						graficos.drawImage(f.getImagem(), f.getX(), f.getY(), this); // imagem machado atirador esquerda
					}
				}

				for (int i = 0; i < lenhador.size(); i++) {

					lenhador in = lenhador.get(i);
					graficos.drawImage(in.getLenhador(), in.getX(), in.getY(), this); // imagem lenhador correndo
																						// direita
				}

				for (int i = 0; i < lenhadorE.size(); i++) {

					lenhador inE = lenhadorE.get(i);
					graficos.drawImage(inE.getLenhador(), inE.getX(), inE.getY(), this); // imagem lenhador correndo
																							// esquerda
				}

				graficos.drawString("INIMIGOS RESTANTES: " + getInimigos(), 5, 15);
			}

			if (getInimigos() == 0) {
				graficos.drawImage(concluida, 50, 90, null); // imagem fase concluida
				emJogo = false;
			}

			if (personagem.getVidas() == 2.5) {
				graficos.drawImage(meiaVida, 510, 15, null); // imagem meio coração
				sequencia = 0;
			}

			if (personagem.getVidas() == 2) {
				graficos.drawImage(branco, 510, 15, null); // imagem coração branco
				sequencia = 0;
			}

			if (personagem.getVidas() == 1.5) {
				graficos.drawImage(branco, 510, 15, null); // imagem coração branco
				graficos.drawImage(meiaVida, 460, 15, null); // imagem meio coração
				sequencia = 0;
			}

			if (personagem.getVidas() == 1) {
				graficos.drawImage(branco, 510, 15, null); // imagem coração branco
				graficos.drawImage(branco, 460, 15, null); // imagem coração branco
				sequencia = 0;
			}

			if (personagem.getVidas() == 0.5) {
				graficos.drawImage(branco, 510, 15, null); // imagem coração branco
				graficos.drawImage(branco, 460, 15, null); // imagem coração branco
				graficos.drawImage(meiaVida, 410, 15, null); // imagem meio coração
				sequencia = 0;
			}

			if (personagem.getVidas() <= 0) {

				graficos.drawImage(branco, 510, 15, null); // imagem coração branco
				graficos.drawImage(branco, 460, 15, null); // imagem coração branco
				graficos.drawImage(branco, 410, 15, null); // imagem coração branco
				graficos.drawImage(gameOver, 50, 60, null); // imagem game over
				personagem.setVisivel(false);
				emJogo = false;
			}
		}

		if (getInimigos() == 0 && fimJogo == false) {
			if (fases == 5) {
				fimJogo = true;
			}
			if (chamarLoading > 500) {
				foraLoading = false;
				g.clearRect(0, 0, 565, 300); // limpa tela
				g.drawImage(Loading, 0, 0, null); // imagem loadingd
				g.drawImage(icone, 470, 190, null); // icone casthor piscando
				voltarLoading++; // contador
				if (fases < 2) {
					fases = 2; // chama fase 2
				}
				if (voltarLoading > 500) {
					// fase 2
					if (fases == 2) {
						g.clearRect(0, 0, 565, 300); // limpa tela
						setInimigos(5); // quantidade de inimigos na fase 2
						setQtdLenhadores(3); // quantidade de lenhadores correndo direita
						setQtdAtiradores(2); // quantidade de atiradores direita
						foraLoading = true; // variavel para saber se esta dentro ou fora do loading
						emJogo = true;
						chamarLoading = 0; // contador
						voltarLoading = 0; // contador
						fases = 3; // chama fase 3
						personagem.setX(200);
					}
					// fase 3
					else if (fases == 3) {
						g.clearRect(0, 0, 565, 300);
						setInimigos(6);
						setQtdLenhadores(4); // quantidade de lenhadores correndo direita
						setQtdAtiradores(1); // quantidade de atiradores direita
						setQtdLenhadoresE(1);
						foraLoading = true;
						emJogo = true;
						chamarLoading = 0;
						voltarLoading = 0;
						fases = 4;
						personagem.setX(200);
					}
					// fase 4
					else if (fases == 4) {
						g.clearRect(0, 0, 565, 300);
						setInimigos(1);
						setQtdLenhadores(0); // quantidade de lenhadores correndo direita
						setQtdAtiradores(0); // quantidade de atiradores direita
						setQtdLenhadoresE(0);
						setQtdAtiradoresE(0);
						foraLoading = true;
						emJogo = true;
						chamarLoading = 0;
						voltarLoading = 0;
						fases = 5;
						personagem.setX(200);
					}
				}
			}
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		System.out.println(getQtdLenhadoresE() + " , " + getQtdLenhadores() + " , " + fases);

		if (foraLoading && getInimigos() != 0) {
			if (getQtdAtiradores() != 0) {
				contador++;
			}
			if (getQtdAtiradoresE() != 0) {
				contador3++;
			}
			contador5++;
			contador6++;
			contador2++;
			contador4++;
			if (contador2 > 600 && getQtdLenhadores() > 0 && spawnLenhador) {
				inicializaLenhador();
				contador2 = 0;
				spawnLenhador = false;
			}
			if (contador4 > 1050 && getQtdLenhadoresE() > 0 && spawnLenhadorE) {
				inicializaLenhadorE();
				contador4 = 0;
				spawnLenhadorE = false;
			}

			if (personagem.isPulo()) {
				if (subir) {
					personagem.setY(personagem.getY() - 1);
					if (personagem.getY() == 40) {
						subir = false;
						descer = true;
					}
				}
				if (descer) {
					personagem.setY(personagem.getY() + 1);
					if (personagem.getY() == 157) {
						personagem.setPulo(false);
						subir = true;
						descer = false;
					}
				}
			}

			List<fire> fires = personagem.getFires();

			List<fire> fireAtirador = atirador.getFires();

			List<fire> fireAtiradorE = atiradorE.getFires();

			List<fire> fireBoss = boss.getFires();

			for (int i = 0; i < fires.size(); i++) {

				fire f = (fire) fires.get(i);

				if (f.isVisivel() && sequencia < 10 && personagem.isAtkDireita()) {
					personagem.setControleE(false);
					f.mexer();

				} else if (f.isVisivel() && sequencia >= 10 && personagem.isAtkDireita()) {
					personagem.setControleE(false);
					f.mexer();
				} else if (f.isVisivel() && sequencia < 10 && personagem.isAtkEsquerda()) {
					personagem.setControleD(false);
					f.mexerE();

				} else if (f.isVisivel() && sequencia >= 10 && personagem.isAtkEsquerda()) {
					personagem.setControleD(false);
					f.mexerE();
				} else {
					fires.remove(i);
					personagem.setControleD(true);
					personagem.setControleE(true);
				}
			}

			for (int i = 0; i < fireAtirador.size(); i++) {

				fire f = (fire) fireAtirador.get(i);

				if (f.isVisivel()) {

					f.mexerEsquerdaMachado();

				}

			}

			for (int i = 0; i < fireAtiradorE.size(); i++) {

				fire f = (fire) fireAtiradorE.get(i);

				if (f.isVisivel()) {

					f.mexerDireitaMachado();

				}

			}

			if (boss.isVisivel()) {
				for (int i = 0; i < fireBoss.size(); i++) {

					fire f = (fire) fireBoss.get(i);

					if (f.isVisivel() && boss.isCorrida() == false) {
						f.mexerEsquerdaMachado();
					} else if (f.isVisivel() && boss.isCorrida() == true) {
						boss.mexer();
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

			for (int i = 0; i < lenhadorE.size(); i++) {

				lenhador in = lenhadorE.get(i);

				if (in.isVisivel()) {
					in.mexerE();
				} else {
					lenhadorE.remove(i);
				}

			}
			if (atirador.isVisivel()) {
				atirador.atira();
			}
			if (atiradorE.isVisivel()) {
				atiradorE.atiraE();
			}
			if (boss.isCorrida() == false && boss.isVisivel()) {
				if (contador5 > 500) {
					boss.atira();
				}
			} else if (boss.isCorrida() == true && boss.isVisivel()) {
				boss.mexer();
			}
			personagem.mexer();
			repaint();

			if (getInimigos() != 0 && personagem.getVidas() != 0) {

				Colisoes();
			}

		}
		if (getInimigos() == 0) {
			chamarLoading++;
		}

	}

	public void Colisoes() {

		Rectangle formaPerso = personagem.getBounds();
		Rectangle formaFire;
		Rectangle formaFireAtirador;
		Rectangle formaLenhador;

		for (int i = 0; i < lenhador.size(); i++) {

			lenhador tempLenhador = lenhador.get(i);
			formaLenhador = tempLenhador.getBounds();

			if (formaPerso.intersects(formaLenhador)) {

				if (personagem.getVidas() != 0) {
					setQtdLenhadores(getQtdLenhadores() - 1);
					if (personagem.isEscudo() && personagem.isEscudoD()) {
						personagem.setVidas(personagem.getVidas() - 0.5);
						tempLenhador.setVisivel(false);
						spawnLenhador = true;
						setInimigos(getInimigos() - 1);
					} else if (personagem.isEscudo() && personagem.isEscudoE()) {
						personagem.setVidas(personagem.getVidas() - 1);
						tempLenhador.setVisivel(false);
						spawnLenhador = true;
						setInimigos(getInimigos() - 1);
					} else {
						personagem.setVidas(personagem.getVidas() - 1);
						tempLenhador.setVisivel(false);
						spawnLenhador = true;
						setInimigos(getInimigos() - 1);
					}
				}
			}
		}
		for (int i = 0; i < lenhadorE.size(); i++) {

			lenhador tempLenhador = lenhadorE.get(i);
			formaLenhador = tempLenhador.getBounds();

			if (formaPerso.intersects(formaLenhador)) {

				if (personagem.getVidas() != 0) {
					setQtdLenhadoresE(getQtdLenhadoresE() - 1);
					if (personagem.isEscudo() && personagem.isEscudoD()) {
						personagem.setVidas(personagem.getVidas() - 1);
						tempLenhador.setVisivel(false);
						spawnLenhadorE = true;
						setInimigos(getInimigos() - 1);
					} else if (personagem.isEscudo() && personagem.isEscudoE()) {
						personagem.setVidas(personagem.getVidas() - 0.5);
						tempLenhador.setVisivel(false);
						spawnLenhadorE = true;
						setInimigos(getInimigos() - 1);
					} else {
						personagem.setVidas(personagem.getVidas() - 1);
						tempLenhador.setVisivel(false);
						spawnLenhadorE = true;
						setInimigos(getInimigos() - 1);
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

						setQtdAtiradores(getQtdAtiradores() - 1);
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

				jogoAps.fire tempFire = fire.get(i);
				formaFire = tempFire.getBounds();
				if (formaFire.intersects(formaAtirador)) {

					personagem.setControleE(true);
					personagem.setControleD(true);
					setQtdAtiradores(getQtdAtiradores() - 1);
					tempFire.setVisivel(false);
					atirador.contador = 0;
					atirador.setVisivel(false);
					setInimigos(getInimigos() - 1);
					this.contador = 0;
					sequencia++;
				}
			}
		}

		if (atiradorE.isVisivel()) {
			List<fire> fireAtirador = atiradorE.getFires();
			Rectangle formaAtirador = atiradorE.getBounds();
			for (int i = 0; i < fireAtirador.size(); i++) {

				fire tempFire = fireAtirador.get(i);
				if (tempFire.isVisivel()) {
					formaFire = tempFire.getBounds();
					if (formaPerso.intersects(formaAtirador)) {

						setQtdAtiradoresE(getQtdAtiradoresE() - 1);
						tempFire.setVisivel(false);
						personagem.setVidas(personagem.getVidas() - 1);
						this.contador3 = 0;
					}
					if (formaFire.intersects(formaPerso)) {
						if (personagem.isEscudo() && personagem.isEscudoE()) {
							tempFire.setVisivel(false);
						} else if (personagem.isEscudo() && personagem.isEscudoD()) {
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

				jogoAps.fire tempFire = fire.get(i);
				formaFire = tempFire.getBounds();

				if (formaFire.intersects(formaAtirador)) {

					personagem.setControleE(true);
					personagem.setControleD(true);
					setQtdAtiradoresE(getQtdAtiradoresE() - 1);
					tempFire.setVisivel(false);
					atirador.contador = 0;
					atirador.setVisivel(false);
					setInimigos(getInimigos() - 1);
					this.contador3 = 0;
					sequencia++;
				}
			}
		}

		List<fire> fire = personagem.getFires();

		for (int i = 0; i < fire.size(); i++) {

			jogoAps.fire tempFire = fire.get(i);
			formaFire = tempFire.getBounds();

			for (int j = 0; j < lenhador.size(); j++) {

				lenhador tempLenhador = lenhador.get(j);
				formaLenhador = tempLenhador.getBounds();

				if (formaFire.intersects(formaLenhador)) {

					spawnLenhador = true;
					personagem.setControleD(true);
					personagem.setControleE(true);
					setQtdLenhadores(getQtdLenhadores() - 1);
					tempLenhador.setVisivel(false);
					tempFire.setVisivel(false);
					setInimigos(getInimigos() - 1);
					sequencia++;

				}

			}
		}
		for (int i = 0; i < fire.size(); i++) {

			jogoAps.fire tempFire = fire.get(i);
			formaFire = tempFire.getBounds();

			for (int j = 0; j < lenhadorE.size(); j++) {

				lenhador tempLenhador = lenhadorE.get(j);
				formaLenhador = tempLenhador.getBounds();

				if (formaFire.intersects(formaLenhador)) {

					spawnLenhadorE = true;
					personagem.setControleD(true);
					personagem.setControleE(true);
					setQtdLenhadoresE(getQtdLenhadoresE() - 1);
					tempLenhador.setVisivel(false);
					tempFire.setVisivel(false);
					setInimigos(getInimigos() - 1);
					sequencia++;

				}

			}
		}
		if (boss.isVisivel()) {
			List<jogoAps.fire> fireBoss = boss.getFires();
			Rectangle formaBoss = boss.getBounds();
			for (int i = 0; i < fireBoss.size(); i++) {

				jogoAps.fire tempFire = fireBoss.get(i);
				if (tempFire.isVisivel()) {
					formaFire = tempFire.getBounds();
					if (formaPerso.intersects(formaBoss)) {

						boss.setVidas(boss.getVidas() - 1);
						personagem.setVidas(personagem.getVidas() - 1);
						if (boss.getVidas() == 0) {
							setInimigos(getInimigos() - 1);
							boss.setVisivel(false);
						}

					}
					if (formaFire.intersects(formaPerso)) {
						if (personagem.isEscudo() && personagem.isEscudoD()) {
							personagem.setVidas(personagem.getVidas() - 0.5);
							tempFire.setVisivel(false);
						} else if (personagem.isEscudo() && personagem.isEscudoE()) {
							tempFire.setVisivel(false);
							personagem.setVidas(personagem.getVidas() - 1.5);
						} else {
							tempFire.setVisivel(false);
							personagem.setVidas(personagem.getVidas() - 2);
						}
					}
				}
			}
			List<jogoAps.fire> firePerso = personagem.getFires();

			for (int i = 0; i < firePerso.size(); i++) {

				jogoAps.fire tempFire = firePerso.get(i);
				formaFire = tempFire.getBounds();

				if (formaFire.intersects(formaBoss)) {

					personagem.setControleE(true);
					personagem.setControleD(true);
					boss.setVidas(boss.getVidas() - 1);
					tempFire.setVisivel(false);
					if (boss.getVidas() == 0) {
						setInimigos(getInimigos() - 1);
						boss.setVisivel(false);
					}
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

	public int getQtdLenhadores() {
		return QtdLenhadores;
	}

	public void setQtdLenhadores(int qtdLenhadores) {
		QtdLenhadores = qtdLenhadores;
	}

	public int getQtdAtiradores() {
		return QtdAtiradores;
	}

	public void setQtdAtiradores(int qtdAtiradores) {
		QtdAtiradores = qtdAtiradores;
	}

	public int getQtdLenhadoresE() {
		return QtdLenhadoresE;
	}

	public void setQtdLenhadoresE(int qtdLenhadoresE) {
		QtdLenhadoresE = qtdLenhadoresE;
	}

	public int getQtdAtiradoresE() {
		return QtdAtiradoresE;
	}

	public void setQtdAtiradoresE(int qtdAtiradoresE) {
		QtdAtiradoresE = qtdAtiradoresE;
	}

}
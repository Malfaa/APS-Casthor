
package jogoAps;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class perso {

	private int x, y;
	private int dx, dy;
	private int altura, largura;
	private boolean isVisivel;
	private double vidas = 3;
	private int vidaEscudo = 3;
	private int contadorTiro;
	private boolean direita, esquerda, escudoD, escudoE, escudo, AtkDireita, AtkEsquerda, controleE = true, controleD = true, pulo;

	ImageIcon andandoDireita, andandoEsquerda, rodandoMarteloD, rodandoMarteloE, paradoD, paradoE, escudoDireita, escudoEsquerda;

	private Image imagem;

	private List<fire> fires;

	public perso() { // imagem

		andandoEsquerda = new ImageIcon("res\\casthorwalkingEsq.gif");// movimento: andando esquerda

		andandoDireita = new ImageIcon("res\\casthorwalking.gif");// movimento: andando direita

		rodandoMarteloD = new ImageIcon("res\\casthorSegDir.gif"); // movimento: segurando para direita

		rodandoMarteloE = new ImageIcon("res\\rodarMarteloEsquerda.gif");

		paradoD = new ImageIcon("res\\casthoridle.gif");// movimento: parado direita

		paradoE = new ImageIcon("res\\casthorEsquerda.gif");// movimento:parado esquerda

		escudoDireita = new ImageIcon("res\\casthorComEscudo.gif"); // movimento: escudo vidado para direita

		escudoEsquerda = new ImageIcon("res\\casthorComEscudoEsq.gif"); // movimento: escudo vidado para esquerda

		imagem = paradoD.getImage();

		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);

		fires = new ArrayList<fire>();

		this.x = 50;
		this.y = 100;
		y =157;

	}

	public List<fire> getFires() {
		return fires;
	}

	///// entrada de dados teclado para mexer
	public void mexer() {
		
		System.out.println(x+ " , " +y);
		contadorTiro++;
		x += dx; // LIMITE TELA PERSONAGEM X
		//y += dy; // LIMITE Y "TRAVADO EM Y"

		if (this.x < -10) { // limite esquerdo
			x = -10;
		}
		if (this.x > 485) {
			x = 485;
		}

		

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getVidas() {
		return vidas;
	}

	public void setVidas(double vidas) {
		this.vidas = vidas;
	}

	public Image getImagem() {
		return imagem;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public void atirar() {

		this.fires.add(new fire(x + largura - 73, y + altura / 2));

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura - 80, altura -20);
	}

	public void keyPressed(KeyEvent tecla) {/// pressionando tecla

		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_SPACE) {
			if (esquerda) {
				imagem = rodandoMarteloE.getImage();
			} else {
				imagem = rodandoMarteloD.getImage();
			}
			setEscudo(false);
		}

		if (codigo == KeyEvent.VK_W) {
			pulo = true;

		}

		if (codigo == KeyEvent.VK_S) {
			dy = 1;
		}

		if (codigo == KeyEvent.VK_A) {
			dx = -1;
			imagem = andandoEsquerda.getImage();
			setEscudo(false);
			if(isControleE()) {
				setAtkDireita(false);
				setAtkEsquerda(true);
			}
			esquerda = true;
			direita = false;

		}

		if (codigo == KeyEvent.VK_D) {
			dx = 1;
			imagem = andandoDireita.getImage();
			setEscudo(false);
			if(isControleD()) {
				setAtkDireita(true);
				setAtkEsquerda(false);
			}
			esquerda = false;
			direita = true;
		}

		if (codigo == KeyEvent.VK_E) {
			if (getVidaEscudo() != 0) {
				setEscudo(true);
			}
			if (isEscudo()) {
				if (direita) {
					imagem = escudoDireita.getImage();
					setEscudoD(true);
					setEscudoE(false);
				} else if (esquerda) {
					imagem = escudoEsquerda.getImage();
					setEscudoD(false);
					setEscudoE(true);
				}
			}
		}
	}

	public void keyReleased(KeyEvent tecla) {//// soltando tecla

		int codigo = tecla.getKeyCode();
		if (codigo == KeyEvent.VK_SPACE) {
			dx = 0;
			if (contadorTiro > 200) {
				atirar();
				contadorTiro = 0;
			}
			if (esquerda) {
				imagem = paradoE.getImage();
			} else if (direita) {
				imagem = paradoD.getImage();
			}
		}

		if (codigo == KeyEvent.VK_A) {
			dx = 0;
			imagem = paradoE.getImage();
			
		}

		if (codigo == KeyEvent.VK_S) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_W) {
			dx = 0;
		}

		if (codigo == KeyEvent.VK_D) {
			dx = 0;
			imagem = paradoD.getImage();
			
		}
	}

	public boolean isEscudoD() {
		return escudoD;
	}

	public void setEscudoD(boolean escudoD) {
		this.escudoD = escudoD;
	}

	public boolean isEscudoE() {
		return escudoE;
	}

	public void setEscudoE(boolean escudoE) {
		this.escudoE = escudoE;
	}

	public boolean isEscudo() {
		return escudo;
	}

	public void setEscudo(boolean escudo) {
		this.escudo = escudo;
	}

	public int getVidaEscudo() {
		return vidaEscudo;
	}

	public void setVidaEscudo(int vidaEscudo) {
		this.vidaEscudo = vidaEscudo;
	}

	public boolean isAtkDireita() {
		return AtkDireita;
	}

	public void setAtkDireita(boolean atkDireita) {
		AtkDireita = atkDireita;
	}

	public boolean isAtkEsquerda() {
		return AtkEsquerda;
	}

	public void setAtkEsquerda(boolean atkEsquerda) {
		AtkEsquerda = atkEsquerda;
	}

	public boolean isControleE() {
		return controleE;
	}

	public void setControleE(boolean controleE) {
		this.controleE = controleE;
	}

	public boolean isControleD() {
		return controleD;
	}

	public void setControleD(boolean controleD) {
		this.controleD = controleD;
	}

	public boolean isPulo() {
		return pulo;
	}

	public void setPulo(boolean pulo) {
		this.pulo = pulo;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public void setY(int y) {
		this.y = y;
	}

}

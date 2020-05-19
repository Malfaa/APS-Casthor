package jogoAps;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Boss {
	private int x, y;
	private int dx, dy;
	private int altura, largura, contador, contador2;
	private boolean isVisivel, finalTela = true, corrida = true;
	private double vidas = 2;
	ImageIcon andandoDireita, andandoEsquerda, vida, meiaVida;

	private static final int LARGURA_TELA = 565; // Ate onde o fogo vai
	private static final int LARGURA_TELA_E = 0;
	private static final double VELOCIDADE = 1; // velocidade do lenhador

	private Image imagem, imagemVida, imagemMeiaVida;

	private List<fire> fires;

	public Boss() { // imagem

		andandoEsquerda = new ImageIcon("jogoApsParteII\\src\\res\\lenhadorEspecial.gif");// movimento: andando esquerda

		andandoDireita = new ImageIcon("jogoApsParteII\\src\\res\\lenhadorEspecialVolta.gif");// movimento: andando

		vida = new ImageIcon("jogoApsParteII\\src\\res\\vidaInimigo.png");

		meiaVida = new ImageIcon("jogoApsParteII\\src\\res\\meiaVidaInimigo.png");

		imagem = andandoEsquerda.getImage();
		imagemVida = vida.getImage();
		imagemMeiaVida = meiaVida.getImage();
		

		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);

		fires = new ArrayList<fire>();

		this.x = 529;
		this.y = 100;
		y = 174;

	}

	public List<fire> getFires() {
		return fires;
	}

	///// entrada de dados teclado para mexer
	public void mexer() {
		if (corrida == true) {
			if (this.x > 0 && finalTela) {
				this.x -= VELOCIDADE;
				imagem = andandoEsquerda.getImage();
			} else {
				finalTela = false;
			}
			if (finalTela == false) {
				this.x += VELOCIDADE;
				imagem = andandoDireita.getImage();
			}
			if (this.x == 530) {
				finalTela = true;
				corrida = false;
			}
		}
	}

	public void atira() {
		contador++;
		contador2++;
		imagem = andandoEsquerda.getImage();
		if (corrida == false) {
			if (contador > 300) {
				atirar();
				contador = 0;
			}
			if (contador2 > 700 && isVisivel()) {
				corrida = true;
			}

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
		return new Rectangle(x, y, largura - 80, altura - 20);
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

	public void setX(int x) {
		this.x = x;
	}

	public boolean isCorrida() {
		return corrida;
	}

	public void setCorrida(boolean corrida) {
		this.corrida = corrida;
	}

	public Image getImagemVida() {
		return imagemVida;
	}

	public void setImagemVida(Image imagemVida) {
		this.imagemVida = imagemVida;
	}

	public Image getImagemMeiaVida() {
		return imagemMeiaVida;
	}

	public void setImagemMeiaVida(Image imagemMeiaVida) {
		this.imagemMeiaVida = imagemMeiaVida;
	}

}

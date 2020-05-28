
package jogoAps;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class LenhadorAtirador {

	private int x, y;
	private int altura, largura;
	private boolean isVisivel;
	public int contador, contador2;

	ImageIcon esquerda, direita;

	private Image imagem;

	private List<fire> fireAtirador;

	public LenhadorAtirador() { // imagem

		esquerda = new ImageIcon("jogoApsParteII\\src\\res\\lenhadorAzul.png"); // Imagem Lenhador Atirador
		direita = new ImageIcon("jogoApsParteII\\src\\res\\lenhadorAzulOposto.png"); //Imagem Lenhador Atirador
		imagem = esquerda.getImage();

		setAltura(imagem.getHeight(null));
		setLargura(imagem.getWidth(null));

		fireAtirador = new ArrayList<fire>();

	}

	public List<fire> getFires() {
		return fireAtirador;
	}

	public void atira() {
		this.x = 490;
		this.y = 172;
		contador++;
		if (contador < 650) {
			imagem = esquerda.getImage();
		} else {
			ImageIcon atirando = new ImageIcon("jogoApsParteII\\src\\res\\lenhadorAzulAtirando.png");
			imagem = atirando.getImage();
		}
		if (contador > 700) {
			atirar();
			contador = 0;
		}
	}

	public void atiraE() {
		this.x = 10;
		this.y = 172;
		contador2++;
		if (contador2 < 650) {
			imagem = direita.getImage();
		} else {
			ImageIcon atirando = new ImageIcon("jogoApsParteII\\src\\res\\lenhadorAzulAtirandoOposto.png");
			imagem = atirando.getImage();
		}
		if (contador2 > 700) {
			atirar();
			contador2 = 0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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

		this.fireAtirador.add(new fire(x + getLargura() - 50, y + getAltura() / 4));

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, getLargura(), getAltura());
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

}

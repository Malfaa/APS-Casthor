
package jogoAps;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class LenhadorAtirador {

	private boolean esquerdaAtk;
	private int x, y;
	private int altura, largura;
	private boolean isVisivel;
	public int contador;

	ImageIcon esquerda;

	private Image imagem;

	private List<fire> fireAtirador;

	public LenhadorAtirador() { // imagem

		esquerda = new ImageIcon("res\\lenhadorAzul.gif");
		imagem = esquerda.getImage();

		setAltura(imagem.getHeight(null));
		setLargura(imagem.getWidth(null));

		fireAtirador = new ArrayList<fire>();

		this.x = 520;
		this.y = 210;
		
		

	}

	public List<fire> getFires() {
		return fireAtirador;
	}

	
	public void atira() {
		contador++;
		if (contador > 700) {
			atirar();
			contador = 0;
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

		this.fireAtirador.add(new fire(x + getLargura() - 50, y + getAltura() /4));

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, getLargura(), getAltura());
	}


	public boolean isEsquerdaAtk() {
		return esquerdaAtk;
	}

	public void setEsquerdaAtk(boolean esquerdaAtk) {
		this.esquerdaAtk = esquerdaAtk;
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

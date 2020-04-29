package jogoAps;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class fire {
	
	private Image imagem;
	private int	x , y;
	private int largura, altura;
	private boolean	isVisivel;
	
	
	private static final int LARGURA_TELA = 565; // Ate onde o fogo vai
	private static final double VELOCIDADE = 1.5; // velocidade do fogo
	
	public fire (int x, int y) {
		this.x = x;
		this.y = y;
		
		ImageIcon referencia = new ImageIcon("jogoApsParteII\\src\\res\\martelo.gif"); // imagem do martelo
		
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		isVisivel = true;
		
		
	}
	public void mexer() {
		this.x += VELOCIDADE;
		if(this.x > LARGURA_TELA) {
			isVisivel = false;
		}
		
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura-20, altura);	
	}

}

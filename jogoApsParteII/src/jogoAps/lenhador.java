package jogoAps;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class lenhador {
	
	private Image lenhador, lenhadorVolta;
	private int	x , y;
	private int altura, largura;
	private boolean	isVisivel;
	

	private static final int LARGURA_TELA = 565; // Ate onde o fogo vai
	private static final double VELOCIDADE = 0.0000001; // velocidade do lenhador
	
	public lenhador (int x, int y) {
		this.x = x;
		this.y = y;
		
		ImageIcon referencia = new ImageIcon("jogoApsParteII\\src\\res\\lenhador.gif"); // imagem do lenhador
		lenhador = referencia.getImage();
		ImageIcon volta = new ImageIcon("jogoApsParteII\\src\\res\\lenhadorVolta.gif"); // imagem do lenhador
		lenhadorVolta = volta.getImage();
		
		this.largura = lenhador.getWidth(null);
		this.altura = lenhador.getHeight(null);
		isVisivel = true;
		
		
	}
	public void mexer() {
		if(this.x < -50) {
			this.x = LARGURA_TELA;
			this.x = (int) -VELOCIDADE;
			lenhadorVolta.getGraphics();
		}else {
			this.x -= VELOCIDADE;
		}
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getLenhador() {
		return lenhador;
	}
	
	public Image getLenhadorVolta() {
		return lenhadorVolta;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);	
	}

}

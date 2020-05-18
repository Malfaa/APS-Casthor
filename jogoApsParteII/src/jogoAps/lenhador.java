package jogoAps;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class lenhador {
	
	ImageIcon lenhador, lenhadorVolta;
	private int	x , y;
	private int altura, largura;
	private boolean	isVisivel, finalTela = true;
	private Image imagem;
	

	private static final int LARGURA_TELA = 565; // Ate onde o fogo vai
	private static final int LARGURA_TELA_E = 0;
	private static final double VELOCIDADE = 1; // velocidade do lenhador
	
	public lenhador (int x, int y) {
		this.x = x;
		this.y = y;
		
		lenhador = new ImageIcon("jogoApsParteII\\src\\res\\lenhador.gif"); // imagem do lenhador
		
		lenhadorVolta = new ImageIcon("jogoApsParteII\\src\\res\\lenhadorVolta.gif"); // imagem do lenhador
	
		imagem = lenhador.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		isVisivel = true;
		
		
	}
	public void mexer() {
		if(this.x > 10 && finalTela) {
			this.x -= VELOCIDADE;
			imagem = lenhador.getImage();
		}else {
			finalTela = false;
		}
		if(finalTela == false){
			this.x += VELOCIDADE;
			imagem = lenhadorVolta.getImage();
		}
		if(this.x == 530) {
			finalTela = true;
		}
	}

	public void mexerE() {
		if(this.x < 530 && finalTela) {
			this.x += VELOCIDADE;
			imagem = lenhadorVolta.getImage();
		}else {
			finalTela = false;
		}
		if(finalTela == false){
			this.x -= VELOCIDADE;
			imagem = lenhador.getImage();
		}
		if(this.x == 10) {
			finalTela = true;
		}
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getLenhador() {
		return imagem;
	}
	
	public Image getLenhadorVolta() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura-30, altura);	
	}

}

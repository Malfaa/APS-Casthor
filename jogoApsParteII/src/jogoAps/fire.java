package jogoAps;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class fire {
	
	private Image imagem;
	private int	x , y; //coordenadas iniciais
	private int largura, altura; // altura e largura do martelo
	private boolean	isVisivel; // visibilidade na tela do martelo
	ImageIcon marteloD, marteloE, machadoE, machadoD, fogo; // Imagens do martelo
	private static final int LARGURA_TELA = 540; // Ate onde o martelo vai
	private static final int LARGURA_TELA_E = 0;
	private static final double VELOCIDADE = 1.5; // velocidade do martelo
	
	public fire(int x, int y) {
		this.x = x;
		this.y = y;
		
		marteloD = new ImageIcon("jogoApsParteII\\src\\res\\martelo.gif"); // imagem do martelo
		marteloE = new ImageIcon("jogoApsParteII\\src\\res\\marteloEsquerda.gif"); //imagem martelo esquerda
		machadoE = new ImageIcon("jogoApsParteII\\src\\res\\machadoLenhador.gif"); //machado lenhador
		machadoD = new ImageIcon("jogoApsParteII\\src\\res\\machadoLenhadorVolta.gif");//machado lenhador esq.
		fogo = new ImageIcon("jogoApsParteII\\src\\res\\fogo.gif");
		
		imagem = marteloD.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		isVisivel = true;
		
		
	}
	public void mexer() {
		this.x += VELOCIDADE;
		imagem = marteloD.getImage();
		if(this.x > LARGURA_TELA) {
			isVisivel = false;
		}
	}
	
	public void mexerS() {
		this.x += VELOCIDADE;
		imagem = fogo.getImage();
		if(this.x > LARGURA_TELA) {
			isVisivel = false;
		}
	}
	
	public void mexerE() {
		this.x -= VELOCIDADE;
		imagem = marteloE.getImage();
		if(this.x < LARGURA_TELA_E) {
			setVisivel(false);
		}	
	}
	
	public void mexerDireitaMachado() {
		this.x += VELOCIDADE;
		imagem = machadoD.getImage();
		if(this.x > LARGURA_TELA) {
			isVisivel = false;
		}
		
	}
	
	public void mexerEsquerdaMachado() {
		this.x -= VELOCIDADE;
		imagem = machadoE.getImage();
		if(this.x < LARGURA_TELA_E) {
			setVisivel(false);
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

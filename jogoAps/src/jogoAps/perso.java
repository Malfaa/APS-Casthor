package jogoAps;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class perso {
	
	private int x, y;
	private int dx, dy;
	private int altura, largura;
	private int vida;
	
	ImageIcon direita, idle, Ref, esquerda;
	
	private Image imagem;
	
	
	private List<fire> fires;
	
	
	public perso() { // imagem
		
		esquerda = new ImageIcon("res\\Walk_02.gif");
		direita = new ImageIcon("res\\Walk_01.gif");
        idle = new ImageIcon("res\\Idle_01.gif");
        Ref = new ImageIcon("res\\Idle_01.gif");
        imagem = Ref.getImage();
		
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		
		fires = new ArrayList<fire>();
		
		this.x = 100;
		this.y = 100;
		
		
	}
	public List<fire> getFires() {
		return fires;
	}
	///// entrada de dados teclado para mexer
	public void mexer() {
		
		System.out.println(x + "," + y);
		x += dx; //LIMITE TELA PERSONAGEM X
		y += dy; //LIMITE Y "TRAVADO EM Y"
		
		if(this.x < -50) { // limite esquerdo
				x = -50;
		}
		if(this.x >485) {
			x = 485;
		}
		
		y = 163;
		
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
	
	public void atirar() {
		
		this.fires.add(new fire(x +largura - 73, y + altura/2));
		
		
	}
	
	public void keyPressed(KeyEvent tecla) {///pressionando tecla
		
		int codigo = tecla.getKeyCode();
		
		if (codigo == KeyEvent.VK_SPACE) {
			atirar();
		}
		
		
		if (codigo == KeyEvent.VK_W) {
			dy = -1;
			
		}
		
		if (codigo == KeyEvent.VK_S) {
			dy = 1;
		}
		
		if (codigo == KeyEvent.VK_A) {
			dx = -1;
			imagem = esquerda.getImage();
	
		}
		
		if (codigo == KeyEvent.VK_D) {
			dx = 1;	
			imagem = direita.getImage();
		}
	}
		
		public void keyReleased(KeyEvent tecla) {//// soltando tecla
			
			int codigo = tecla.getKeyCode();
			
			if (codigo == KeyEvent.VK_A) {
				dy = 0;
				imagem = idle.getImage();
			}
			
			if (codigo == KeyEvent.VK_S) {
				dy = 0;
				imagem = idle.getImage();
			}
			
			if (codigo == KeyEvent.VK_A) {
				dx = 0;
				imagem = idle.getImage();
			}
			
			if (codigo == KeyEvent.VK_D) {
				dx = 0;
				imagem = idle.getImage();
			}
			
		
	}

}

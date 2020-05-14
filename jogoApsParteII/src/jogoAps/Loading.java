package jogoAps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Loading extends JPanel implements ActionListener{
	
	private Image fundo;
	private int contador;

	public Loading() {
		fase fase1 = new fase();
		fase1.removeAll();
		ImageIcon referencia = new ImageIcon("res\\fundoLoading.jpg"); // imagem do fundo
		fundo = referencia.getImage();
		
	}
	
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		contador++;
		repaint();
	}
	
}

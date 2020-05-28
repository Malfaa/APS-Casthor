package jogoAps;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class menu {
    //Frame
    JFrame menu = new JFrame();

    //Painel
    JPanel principal = new JPanel();

    //Complementos
    JLabel tituloL = new JLabel("Lord of Casthor");
    JButton iniciarB = new JButton("Iniciar");

    //Fontes
    Font fonteTitulo = new Font("Arial",Font.BOLD,30);
    Font fonteBotao = new Font("Arial", Font.BOLD,14);

    //Imagens
    ImageIcon fundo = new ImageIcon("jogoApsParteII\\src\\res\\fundoFloresta.png");


    public class Tela extends JPanel{
        public void paintComponent(Graphics g) {
            //Graphics2D graficos = (Graphics2D) g;
            Image background = fundo.getImage();
            g.drawImage(background, 0, 0, this);
        }
    }

    public void Titulo(){
        tituloL.setForeground(Color.WHITE);
        tituloL.setBackground(Color.GRAY);
        tituloL.setFont(fonteTitulo);
    }
    public void Iniciar(){
        iniciarB.setForeground(Color.WHITE);
        iniciarB.setBackground(Color.GRAY);
        iniciarB.setFont(fonteBotao);
    }

    /*EXPLICAÇÃO CASO O MÉTODO "play" DÊ ERRO:
      Houve uma tentativa de inserir o método play no menu, por conta do tempo, não foi possível fazer a alteração
      da música para PARAR quando for iniciado a tela de JOGO e o tratamento
      dos erros presentes nas IDES utilizadas para o desenvolvimento desse projeto.
      CASO DÊ ERRO, DESCONSIDERAR O MÉTODO "play". */

    //Tocar música
    public void play(String MenuOST) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File musica = new File("jogoApsParteII\\src\\res\\MainMenuOST.wav");
        AudioInputStream colocarMusica = AudioSystem.getAudioInputStream(musica);
        final Clip play = AudioSystem.getClip();
        play.open(colocarMusica);
        FloatControl volume= (FloatControl) play.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(1.0f); // Reduzir volume para 10 decibels.
        play.start();
    }

    public menu() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        menu.setSize(565, 300);
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Tela tela = new Tela();
        menu.setContentPane(tela);
        tela.setLayout(new GridBagLayout());

        tela.add(tituloL);
        tela.add(iniciarB);

        Titulo();
        Iniciar();
        play("MenuOST");


        iniciarB.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                menu.dispose();
                new Containe_Window();
            }
        });
    }
}

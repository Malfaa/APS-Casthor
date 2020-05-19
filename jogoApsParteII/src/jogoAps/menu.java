package jogoAps;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;//nono
import java.io.IOException;

public class menu {
    //Frame
    JFrame menu = new JFrame();

    //Painel
    JPanel principal = new JPanel();

    //Complementos
    JLabel tituloL = new JLabel("Lord of Casthor");
    JButton iniciarB = new JButton("Iniciar");
    JButton dificuldadeB = new JButton("Dificuldade");
    GridBagConstraints gbc = new GridBagConstraints();

    //Fontes
    Font fonteTitulo = new Font("Arial",Font.BOLD,30);
    Font fonteBotao = new Font("Arial", Font.BOLD,14);

    //Imagens
    private Image background;
    ImageIcon fundo = new ImageIcon("jogoApsParteII\\src\\res\\fundoFloresta.png");


    public class Tela extends JPanel{
        public void paintComponent(Graphics g) {
            //Graphics2D graficos = (Graphics2D) g;
            Image background = fundo.getImage();
            g.drawImage(background, 0, 0, this);
        }
    }

    public void Titulo(){
        //tituloL.setBounds(145,10,400,400);
        tituloL.setForeground(Color.WHITE);
        tituloL.setBackground(Color.GRAY);//.decode("#000000")
        tituloL.setFont(fonteTitulo);
    }
    public void Iniciar(){
        //  iniciarB.setBounds(230,125,120,50);
        iniciarB.setForeground(Color.WHITE);
        iniciarB.setBackground(Color.GRAY);
        iniciarB.setFont(fonteBotao);
    }
    public void Dificuldade(){
        //dificuldadeB.setBounds(230,185,120,50);
        dificuldadeB.setForeground(Color.WHITE);
        dificuldadeB.setBackground(Color.GRAY);
        dificuldadeB.setFont(fonteBotao);
    }

    //Tocar música
    public void play(String MenuOST) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File musica = new File("jogoApsParteII\\src\\res\\MainMenuOST.wav");
        AudioInputStream colocarMusica = AudioSystem.getAudioInputStream(musica);
        final Clip play = AudioSystem.getClip();
        play.open(colocarMusica);
        FloatControl volume= (FloatControl) play.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(1.0f); // Reduce volume by 10 decibels.
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
        tela.add(dificuldadeB);

        Titulo();
        Iniciar();
        Dificuldade();
        play("MenuOST");


        iniciarB.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                menu.dispose();
                //play.stop();
                new Containe_Window();
            }
        });
    }
}

package jogoAps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu {
    //Frame
    JFrame menu2 = new JFrame();

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

    static boolean mudar = false;

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

    public menu() {

        menu2.setSize(565, 300);
        menu2.setLocationRelativeTo(null);
        menu2.setResizable(false);
        menu2.setVisible(true);
        menu2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Tela tela = new Tela();
        menu2.setContentPane(tela);
        tela.setLayout(new GridBagLayout());

        tela.add(tituloL);
        tela.add(iniciarB);
        tela.add(dificuldadeB);

        Titulo();
        Iniciar();
        Dificuldade();

        iniciarB.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                menu2.dispose();
                new Containe_Window();
            }
        });
    }
}

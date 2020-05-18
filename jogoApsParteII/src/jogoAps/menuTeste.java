package jogoAps;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuTeste {
    private Image Logo;

    public menuTeste(){

        //Tela
        JFrame menu = new JFrame();
        menu.setSize(565,300);
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.getContentPane().setBackground(Color.BLACK);

        //----------------------------------------------------------------------------


        //Escritas na tela

        JPanel tituloInicial = new JPanel();                                 //  |
        tituloInicial.setBackground(Color.BLACK);                            //  |
        tituloInicial.setBounds(80,10,400,400);            //  |
        //Algumas caracteristicas da escrita                                 //  |     Tipo da Escrita
        JLabel escrita = new JLabel("Lord of Casthor");                  //  |
        Font fontEscrita = new Font("Calibri", Font.BOLD, 30);   //   |
        escrita.setForeground(Color.WHITE);                                 //   |
        escrita.setFont(fontEscrita);                                       //   |
        tituloInicial.add(escrita);//Colocando dentro de um só container
        //----------------------------------------------------------------------------


        //Botão p/ começar
        JPanel comecarPainel = new JPanel(); //Caracteristicas do botão             //   |
        comecarPainel.setBounds(225,125,100,100);                 //   |
        comecarPainel.setBackground(Color.BLACK);                                   //   |
        //----------------------------------------------------------------------------
        //Algumas Características do botão                                          //   |
        JButton comecar = new JButton("Iniciar"); // Inicializador botão        //   |
        Font fontBotao = new Font("Calibri", Font.BOLD, 14);              //   |
        comecar.setBackground(Color.BLACK);                                         //   |Tipo da Escrita
        comecar.setForeground(Color.WHITE);                                         //   |
        comecar.setFont(fontBotao);                                                 //   |
        comecarPainel.add(comecar);//Incrementa todos os atributos em um container só


        //Botão p/ dificuldade

        JPanel difPainel = new JPanel(); //Caracteristicas do botão             //   |
        difPainel.setBounds(224,175,70,70);                   //   |
        difPainel.setBackground(Color.BLACK);                                   //   |
        //Algumas Características do botão                                      //   |
        JButton dif = new JButton("Dificuldade"); // Inicializador botão    //   |     Tipo da Escrita
        dif.setBackground(Color.BLACK);                                         //   |
        dif.setForeground(Color.WHITE);                                         //   |
        dif.setFont(fontBotao);                                                 //   |
        difPainel.add(dif);//Incrementa todos os atributos em um container só


        //----------------------------------------------------------------------------

        //Logo
        //ImageIcon icon = new ImageIcon("jogoApsParteII\\src\\res\\casthor.gif" );
        //----------------------------------------------------------------------------

        //Final OUTPUT
        menu.add(tituloInicial);
        menu.add(comecarPainel);
        menu.add(difPainel);

        menu.setLayout(new GridLayout(3,1));


        //Quando o botão "Iniciar é CLICADO, inicia a outra JANELA
        comecar.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                menu.dispose();
                new Containe_Window();
            }
        });


    } //Novo
/*      ----------------------------------------TESTE PARA FUNDO COM GIF ----------------------------------------
    public class ImageJPanel extends JPanel
    {
        private Image aImage = null;

        public ImageJPanel() {
            init();
        }

        private void init() {
            ImageIcon imageIcon = new ImageIcon("jogoApsParteII\\src\\res\\fundo.gif");
            aImage = imageIcon.getImage();
        }

        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(aImage, 0, 0, this);
        }
    }
*/

}
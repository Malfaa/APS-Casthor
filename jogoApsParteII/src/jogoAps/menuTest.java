package jogoAps;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class menuTest {
    JButton iniciarB = new JButton("Iniciar");
    Font fonteBotao = new Font("Arial", Font.BOLD,14);
    @org.junit.jupiter.api.Test
    void iniciar() {
        iniciarB.setForeground(Color.WHITE);
        iniciarB.setBackground(Color.GRAY);
        iniciarB.setFont(fonteBotao);
        assert true;
    }
}
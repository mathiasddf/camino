package src.caminito;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JPanel {
    private JFrame mainFrame;

    public SplashScreen(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Configurar la imagen de fondo
        ImageIcon backgroundImage = new ImageIcon("./img/Elcaminodelsaber.png"); // Cambia el path a tu imagen
        JLabel backgroundLabel = new JLabel(backgroundImage);
        add(backgroundLabel, BorderLayout.CENTER);

        // Crear y agregar botones
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Iniciar Juego");
        JButton exitButton = new JButton("Salir");

        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Acción para el botón "Iniciar Juego"
        startButton.addActionListener(e -> showGame());

        // Acción para el botón "Salir"
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void showGame() {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(new CaminoGame(), BorderLayout.CENTER); // Mostrar el panel del juego
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}

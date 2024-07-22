package src.caminito;

import javax.swing.*;
import java.awt.*;

public class Caminito {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Caminito");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600); // Tamaño de ventana especificado
            frame.setLayout(new BorderLayout());

            // Crear y agregar la pantalla de inicio
            SplashScreen splashScreen = new SplashScreen(frame);
            frame.add(splashScreen, BorderLayout.CENTER);

            // Mostrar la ventana
            frame.setVisible(true);
        });
    }
}

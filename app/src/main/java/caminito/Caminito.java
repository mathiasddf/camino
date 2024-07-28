package caminito;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Caminito {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Caminito");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(870, 730); // Tamaño de ventana especificado

            // Hacer que la ventana no sea redimensionable
            frame.setResizable(false);

            // Centrar la ventana en la pantalla
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - frame.getWidth()) / 2;
            int y = (screenSize.height - frame.getHeight()) / 2;
            frame.setLocation(x, y);

            frame.setLayout(new BorderLayout());

            // Crear y agregar la pantalla de inicio
            SplashScreen splashScreen = new SplashScreen(frame);
            frame.add(splashScreen, BorderLayout.CENTER);

            // Mostrar la ventana
            frame.setVisible(true);
        });
    }
}

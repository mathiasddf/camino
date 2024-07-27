package caminito;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class SplashScreen extends JPanel {
    private JFrame mainFrame;

    public SplashScreen(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Configurar el layout y tamaño del panel
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Configurar la imagen de fondo
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/Elcaminodelsaber.png"));
        Image originalImage = originalIcon.getImage();

        // Aplicar opacidad a la imagen
        Image transparentImage = setImageOpacity(originalImage, 0.6f); // Cambia la opacidad aquí
        ImageIcon transparentIcon = new ImageIcon(transparentImage);

        // Redimensionar la imagen
        int newWidth = 850;  // Ancho del panel
        int newHeight = 700; // Alto del panel
        Image scaledImage = transparentIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setLayout(new GridBagLayout()); // Usar GridBagLayout para centrar los botones
        add(backgroundLabel, BorderLayout.CENTER);

        // Crear y agregar botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10)); // Disposición vertical de botones
        buttonPanel.setOpaque(false); // Hacer transparente el panel de botones

        JButton startButton = new JButton("Iniciar Juego");
        JButton exitButton = new JButton("Salir");

        // Estilo de botones
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setBackground(new Color(50, 150, 50)); // Color de fondo
        startButton.setForeground(Color.WHITE); // Color del texto
        startButton.setFocusPainted(false); // Quitar el borde de enfoque

        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBackground(new Color(150, 50, 50)); // Color de fondo
        exitButton.setForeground(Color.WHITE); // Color del texto
        exitButton.setFocusPainted(false); // Quitar el borde de enfoque

        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        // Usar GridBagConstraints para centrar el panel de botones en el JLabel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(250, 20, 20, 20); // Espaciado alrededor del panel
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el panel en el JLabel

        backgroundLabel.add(buttonPanel, gbc);

        // Acción para el botón "Iniciar Juego"
        startButton.addActionListener(e -> showPlayerRegistrationScreen());

        // Acción para el botón "Salir"
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void showPlayerRegistrationScreen() {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(new PlayerRegistrationScreen(mainFrame), BorderLayout.CENTER); // Mostrar el panel de registro
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    // Método para ajustar la opacidad de una imagen
    private Image setImageOpacity(Image img, float opacity) {
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }
}

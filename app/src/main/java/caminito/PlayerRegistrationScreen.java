package caminito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

public class PlayerRegistrationScreen extends JPanel {

    private JTextField player1NameField;
    private JTextField player2NameField;
    private JFrame mainFrame;

    public PlayerRegistrationScreen(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        // Configurar la imagen de fondo
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/fondoUsuarios.png"));
        Image originalImage = originalIcon.getImage();

        // Aplicar opacidad a la imagen
        Image transparentImage = setImageOpacity(originalImage, 0.5f); // Cambia la opacidad aquí
        ImageIcon transparentIcon = new ImageIcon(transparentImage);

        // Redimensionar la imagen
        int newWidth = 850;  // Ancho del panel
        int newHeight = 700; // Alto del panel
        Image scaledImage = transparentIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setLayout(new GridBagLayout()); // Usar GridBagLayout para centrar los botones
        add(backgroundLabel, BorderLayout.CENTER);

        
        backgroundLabel.setLayout(new GridBagLayout());  // Cambiar el layout para añadir otros componentes

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaciado interno

        // Etiquetas y cajas de texto para nombres de jugadores
        JLabel player1Label = new JLabel("Nombre del Jugador 1:");
        player1Label.setFont(new Font("Arial", Font.BOLD, 16));
        player1Label.setForeground(Color.BLACK);

        player1NameField = new JTextField(20);

        JLabel player2Label = new JLabel("Nombre del Jugador 2:");
        player2Label.setFont(new Font("Arial", Font.BOLD, 16));
        player2Label.setForeground(Color.BLACK);

        player2NameField = new JTextField(20);

        JButton startButton = new JButton("Iniciar Juego");
        startButton.setPreferredSize(new Dimension(150, 30));  // Tamaño del botón
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setBackground(new Color(50, 150, 50)); // Color de fondo
        startButton.setForeground(Color.WHITE); // Color del texto

        // Añadir componentes al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundLabel.add(player1Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        backgroundLabel.add(player1NameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundLabel.add(player2Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundLabel.add(player2NameField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundLabel.add(startButton, gbc);

        // Acción para el botón "Iniciar Juego"
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String player1Name = player1NameField.getText().trim();
                String player2Name = player2NameField.getText().trim();

                if (player1Name.isEmpty() || player2Name.isEmpty()) {
                    JOptionPane.showMessageDialog(PlayerRegistrationScreen.this,
                            "Por favor, ingrese los nombres de ambos jugadores.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Almacenar los nombres en la base de datos
                    JugadorDAO jugadorDAO = new JugadorDAO();
                    jugadorDAO.agregarJugador(player1Name);
                    jugadorDAO.agregarJugador(player2Name);

                    // Cambiar a la pantalla del juego
                    showGame();
                }
            }
        });
    }

    private void showGame() {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(new CaminoGame(), BorderLayout.CENTER); // Mostrar el panel del juego
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

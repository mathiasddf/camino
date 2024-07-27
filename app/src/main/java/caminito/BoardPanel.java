package caminito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;

import java.net.URL;

public class BoardPanel extends JPanel {
    private BufferedImage boardImage;
    private final int TILE_SIZE = 60;
    private final int BOARD_SIZE = 14;

    private final int[] PLAYER_START_POS = {1, 1}; // Posición inicial del jugador
    private final int[] PLAYER_POS = new int[2]; 
    private boolean isRolling = false; // Indica si se está tirando el dado
    private int diceRoll = 0; // Resultado del dado
    private JLabel diceLabel;
    private String playerName = "Jugador"; // Valor predeterminado

    public BoardPanel(String boardImagePath) {
        try {
            // Cargar la imagen usando ClassLoader
            URL imageUrl = getClass().getClassLoader().getResource(boardImagePath);
            if (imageUrl != null) {
                boardImage = ImageIO.read(imageUrl);
            } else {
                throw new RuntimeException("Imagen no encontrada: " + boardImagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores, podrías lanzar una excepción o usar una imagen predeterminada
        }
        
        setPreferredSize(new Dimension(boardImage.getWidth(null), boardImage.getHeight(null)));
        // Inicializa la posición de los jugadores
        PLAYER_POS[0] = PLAYER_START_POS[0];
        PLAYER_POS[1] = PLAYER_START_POS[1];

        // Crea el label para el dado
        diceLabel = new JLabel();
        diceLabel.setBounds(50, 40, 100, 100);
        add(diceLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(boardImage, 0, 0, this.getWidth(), this.getHeight(), this);
        drawBoard(g);
        drawPlayers(g);
        drawNumbers(g); // Dibuja los números en el tablero

        // Dibuja el "START" y "FIN"
        drawTextCentered(g, "START", 61, 30, 59, 30);
        drawTextCentered(g, "FIN", 781, 30, 59, 30);
    }

    private void drawBoard(Graphics g) {
        int[][] camino = {
            {1, 1}, {2, 1}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5}, {2, 5},
            {1, 5}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {1, 8}, {2, 8}, {2, 7}, {3, 7},
            {4, 7}, {4, 8}, {4, 9}, {4, 10}, {3, 10}, {2, 10}, {2, 11}, {2, 12}, {1, 12}, {1, 13}
        };

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (isPathSquare(row, col, camino)) {
                    g.setColor(new Color(0, 182, 90));
                    g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    private boolean isPathSquare(int row, int col, int[][] path) {
        for (int[] square : path) {
            if (row == square[0] && col == square[1]) {
                return true;
            }
        }
        return false;
    }

    private void drawPlayers(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(PLAYER_POS[1] * TILE_SIZE + 5, PLAYER_POS[0] * TILE_SIZE + 5, TILE_SIZE - 10, TILE_SIZE - 10);
    }

    public void rollDice() {
        if (!isRolling) {
            isRolling = true;
            Random random = new Random();
            diceRoll = random.nextInt(6) + 1;
            movePlayer(); // Mueve al jugador inmediatamente después de lanzar el dado
            isRolling = false; // Resetea la bandera
        }
    }

    
        public void movePlayer() {
            int[][] camino = {
                {1, 1}, {2, 1}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5}, {2, 5},
                {1, 5}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {1, 8}, {2, 8}, {2, 7}, {3, 7},
                {4, 7}, {4, 8}, {4, 9}, {4, 10}, {3, 10}, {2, 10}, {2, 11}, {2, 12}, {1, 12}, {1, 13}
            };
        
            // Encuentra la posición actual del jugador
            int currentPosition = 0;
            for (int i = 0; i < camino.length; i++) {
                if (PLAYER_POS[0] == camino[i][0] && PLAYER_POS[1] == camino[i][1]) {
                    currentPosition = i;
                    break;
                }
            }
        
            // Calcula la nueva posición
            int newPosition = currentPosition + diceRoll;
            if (newPosition >= camino.length) {
                newPosition = camino.length - 1; // No exceder el final del tablero
            }
        
            // Guarda la posición antes de mover
            int[] previousPosition = {PLAYER_POS[0], PLAYER_POS[1]};
        
            // Mueve al jugador a la nueva posición
            PLAYER_POS[0] = camino[newPosition][0];
            PLAYER_POS[1] = camino[newPosition][1];
            repaint(); // Redibuja el panel después de mover al jugador
        
            // Verifica si la nueva posición tiene una pregunta
            if (newPosition > 0 && newPosition < camino.length) {
                Question question = Question.getQuestionForTile(newPosition + 1); // Ajustar el número de casilla según tu lógica
                if (question != null) {
                    boolean isCorrect = Question.showQuestion(question);
                    if (isCorrect) {
                        JOptionPane.showMessageDialog(this, "¡Respuesta correcta!", "Correcto", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Respuesta incorrecta. Retrocediendo.", "Incorrecto", JOptionPane.WARNING_MESSAGE);
                        // Retrocede el jugador a la posición anterior
                        PLAYER_POS[0] = previousPosition[0];
                        PLAYER_POS[1] = previousPosition[1];
                        repaint(); // Redibuja el panel después de mover al jugador
                    }
                }
            }
        
            // Verifica si el jugador ha ganado
            if (newPosition == camino.length - 1) {
                System.out.println("¡Jugador gana!");
                // Aquí puedes agregar lógica para mostrar un mensaje de victoria o reiniciar el juego
            }
        }
    

    private void drawNumbers(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        FontMetrics fm = g2d.getFontMetrics();
        int[][] path = {
            {1, 1}, {2, 1}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5}, {2, 5},
            {1, 5}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {1, 8}, {2, 8}, {2, 7}, {3, 7},
            {4, 7}, {4, 8}, {4, 9}, {4, 10}, {3, 10}, {2, 10}, {2, 11}, {2, 12}, {1, 12}, {1, 13}
        };

        for (int i = 0; i < path.length; i++) {
            String number = String.valueOf(i + 1);
            int x = path[i][1] * TILE_SIZE + (TILE_SIZE - fm.stringWidth(number)) / 2;
            int y = path[i][0] * TILE_SIZE + ((TILE_SIZE - fm.getHeight()) / 2) + fm.getAscent();
            g2d.drawString(number, x, y);
        }
        g2d.dispose();
    }

    private void drawTextCentered(Graphics g, String text, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = g2d.getFontMetrics();
        
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        
        int xPos = x + (width - textWidth) / 2;
        int yPos = y + (height + textHeight) / 2;

        g2d.setColor(new Color(0, 150, 255));
        g2d.fillRoundRect(x, y, width, height, 10, 10);
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, xPos, yPos);
        g2d.dispose();
    }

}

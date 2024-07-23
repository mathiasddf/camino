package src.caminito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BoardPanel extends JPanel {
    private final Image boardImage;
    private final int TILE_SIZE = 60;
    private final int BOARD_SIZE = 14;

    private final int[] PLAYER_START_POS = {1, 1}; // Posición inicial del jugador
    private final int[] PLAYER_POS = new int[2]; 
    private int currentPlayer = 1; // Jugador actual
    private boolean isRolling = false; // Indica si se está tirando el dado
    private int diceRoll = 0; // Resultado del dado
    private JLabel diceLabel;
    private Timer diceTimer; // Temporizador para la animación del dado
   

    public BoardPanel(String boardImagePath) {
        this.boardImage = new ImageIcon(boardImagePath).getImage();
    setPreferredSize(new Dimension(boardImage.getWidth(null), boardImage.getHeight(null)));
     // Inicializa la posición de los jugadores
     PLAYER_POS[0] = PLAYER_START_POS[0];
     PLAYER_POS[1] = PLAYER_START_POS[1];

      JButton rollButton = new JButton("Lanzar Dado");
        rollButton.setBounds(300, 450, 150, 40);
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        add(rollButton);

        // Crea el label para el dado
        diceLabel = new JLabel();
        diceLabel.setBounds(50, 40, 100, 100);
        add(diceLabel);



        diceTimer = new Timer(100, new ActionListener() {
            private int rollCount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                rollCount++;
                if (rollCount > diceRoll) {
                    diceTimer.stop();
                    isRolling = false;
                    movePlayer();
                } else {
                
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(boardImage, 0, 0, this.getWidth(), this.getHeight(), this);
        drawBoard(g);
        drawPlayers(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
    
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.black);
        g2d.drawString("1", 80, 85);
        g2d.drawString("2", 80, 150);
        g2d.drawString("3", 80, 210);
        g2d.drawString("4", 140, 210);
        g2d.drawString("5", 200, 210);
        g2d.drawString("6", 260, 210);
        g2d.drawString("7", 320, 210);
        g2d.drawString("8", 321, 170);
        g2d.drawString("9", 321, 100);
        g2d.drawString("10", 314, 50);
        g2d.drawString("11", 380, 50);
        g2d.drawString("12", 450, 50);
        g2d.drawString("13", 500, 50);
        g2d.drawString("14", 500, 100);
        g2d.drawString("15", 500, 150);
        g2d.drawString("16", 430, 150);
        g2d.drawString("17", 435, 220);
        g2d.drawString("18", 435, 270);
        g2d.drawString("19", 500, 270);
        g2d.drawString("20", 570, 270);
        g2d.drawString("21", 620, 270);
        g2d.drawString("22", 620, 220);
        g2d.drawString("23", 620, 150);
        g2d.drawString("24", 680, 150);
        g2d.drawString("25", 730, 150);
        g2d.drawString("26", 730, 100);
        g2d.drawString("27", 800, 100);


        g2d.setColor(new Color(0, 150, 255));
        g2d.fillRoundRect(60, 29, 60, 30, 10, 10);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("START", 62, 50);

        g2d.setColor(new Color(0, 150, 255));
        g2d.fillRoundRect(779, 32, 60, 30, 10, 10);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("FIN", 795, 53);
        g2d.dispose();

        g2d.dispose();


    }

    private void drawBoard(Graphics g) {
        
        int[][] camino = {
            {1, 1},{2,1},{3,1},{3,2},{3,3},{3,4},{3,5},{2,5},
            {1,5},{0,5},{0,6},{0,7},{0,8},{1,8},{2,8},{2,7},{3,7},
            {4,7},{4,8},{4,9},{4,10},{3,10},{2,10},{2,11},{2,12},{1,12},{1,13}
        };
    
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                // Dibuja los cuadrados del camino en color amarillo

                if (isPathSquare(row, col, camino)) {
                    g.setColor(new Color(0, 182, 90));
                    g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    g.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    
                    
                } 
                // Dibuja los cuadrados normales en gris
                else {
                    g.setColor(Color.BLACK);
                    g.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    
                }
               
                g.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                g.setColor(Color.BLACK);
                  
            }
        }
        g.setColor(Color.BLACK);
    }
    
    // Función para comprobar si un cuadrado está en el camino
    private boolean isPathSquare(int row, int col, int[][] path) {
        for (int i = 0; i < path.length; i++) {
            if (row == path[i][0] && col == path[i][1]) {
                return true;
            }
        }
        return false;
    }

    public void updatePlayerPosition(int playerNumber, int position) {
        // Implementa la lógica para actualizar la posición del jugador en el tablero
    }
    private void drawPlayers(Graphics g) {

        g.setColor(Color.BLUE);
        g.fillOval(PLAYER_POS[1] * TILE_SIZE + 5, PLAYER_POS[0] * TILE_SIZE + 5, TILE_SIZE - 10, TILE_SIZE - 10);
    }
    private void rollDice() {
        if (!isRolling) {
            isRolling = true;
            Random random = new Random();
            diceRoll = random.nextInt(6) + 1;
            diceTimer.start();
        }
    }
        public void movePlayer() {
         
            int[][] camino = {
                {1, 1},{2,1},{3,1},{3,2},{3,3},{3,4},{3,5},{2,5},
                {1,5},{0,5},{0,6},{0,7},{0,8},{1,8},{2,8},{2,7},{3,7},
                {4,7},{4,8},{4,9},{4,10},{3,10},{2,10},{2,11},{2,12},{1,12},{1,13}
            };
            int currentPosition = 0;
            for (int i = 0; i < camino.length; i++) {
                if (PLAYER_POS[0] == camino[i][0] && PLAYER_POS[1] == camino[i][1]) {
                    currentPosition = i;
                    break;
                }
            }
            int newPosition = currentPosition + diceRoll;
            if (newPosition >= camino.length) {
                newPosition = camino.length - 1;
            }
            PLAYER_POS[0] = camino[newPosition][0];
            PLAYER_POS[1] = camino[newPosition][1];
            repaint();
    
            // Comprueba si el jugador ha llegado a la meta
            if (newPosition == camino.length - 1) {
                System.out.println("¡Jugador gana!");
                // Aquí puedes agregar lógica para mostrar un mensaje de victoria o reiniciar el juego
            } 
        }
}

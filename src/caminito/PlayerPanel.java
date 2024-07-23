package src.caminito;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class PlayerPanel extends JPanel {
    private JLabel playerLabel;
    private BoardPanel boardPanel;
    private final int[] PLAYER_START_POS = {1, 1}; // Posición inicial del jugador
    private final int[] PLAYER_ONE_POS = new int[2]; // Posición actual del jugador 1
    private final int[] PLAYER_TWO_POS = new int[2]; // Posición actual del jugador 2
    private final int TILE_SIZE = 60;
    private Timer diceTimer; 
    private int diceRoll = 0;
    private boolean isRolling = false;
    private int currentPlayer = 1;
    private String playerName;
    
    public PlayerPanel(String playerName, BoardPanel boardPanel) {
        if(playerName.equals("Jugador 2")){
            
        }
        this.boardPanel = boardPanel;

  
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 200));
        setOpaque(false);
        PLAYER_ONE_POS[0] = PLAYER_START_POS[0];
        PLAYER_ONE_POS[1] = PLAYER_START_POS[1];

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

        // Panel para los datos del jugador
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setOpaque(false);
        
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
        playerLabel = new JLabel(playerName, SwingConstants.CENTER);
        infoPanel.add(playerLabel, BorderLayout.NORTH);

        add(infoPanel, BorderLayout.NORTH);
        
        // Panel para el tablero
        JPanel boardContainer = new JPanel();
        boardContainer.setLayout(new BorderLayout());
        boardContainer.setOpaque(false);

        boardPanel.setPreferredSize(new Dimension(800, 100));
        boardContainer.add(boardPanel, BorderLayout.CENTER);

        add(boardContainer, BorderLayout.CENTER);
    }

    public void updateBoard(int playerNumber, int newPosition) {
        if (boardPanel != null) {
            boardPanel.movePlayer();
        } else {
            System.err.println("Error: boardPanel es null.");
        }
    }
    @Override
    protected void paintComponent( Graphics g) {
        super.paintComponent(g);
        drawPlayers(g);
    }
    private void rollDice() {
        if (!isRolling) {
            isRolling = true;
            Random random = new Random();
            diceRoll = random.nextInt(6) + 1;
            diceTimer.start();
        }
    }

     public void drawPlayers(Graphics g) {
       
        g.setColor(Color.RED);
        g.fillOval(PLAYER_ONE_POS[1] * TILE_SIZE + 5, PLAYER_ONE_POS[0] * TILE_SIZE + 5, TILE_SIZE - 10, TILE_SIZE - 10);
        

    }
    public void movePlayer() {
        int[] playerPos = (currentPlayer == 1) ? PLAYER_ONE_POS : PLAYER_TWO_POS;
        // Define las coordenadas de los cuadrados del camino en el tablero
        int[][] path = {
            {1, 1},{2,1},{3,1},{3,2},{3,3},{3,4},{3,5},{2,5},
            {1,5},{0,5},{0,6},{0,7},{0,8},{1,8},{2,8},{2,7},{3,7},
            {4,7},{4,8},{4,9},{4,10},{3,10},{2,10},{2,11},{2,12},{1,12},{1,13}
        };
        int currentPosition = 0;
        for (int i = 0; i < path.length; i++) {
            if (playerPos[0] == path[i][0] && playerPos[1] == path[i][1]) {
                currentPosition = i;
                break;
            }
        }
        int newPosition = currentPosition + diceRoll;
        if (newPosition >= path.length) {
            newPosition = path.length - 1;
        }
        playerPos[0] = path[newPosition][0];
        playerPos[1] = path[newPosition][1];
        repaint();

        // Comprueba si el jugador ha llegado a la meta
        if (newPosition == path.length - 1) {
            System.out.println("¡Jugador " + currentPlayer + " gana!");
            // Aquí puedes agregar lógica para mostrar un mensaje de victoria o reiniciar el juego
        } else {
            // Cambia al siguiente jugador
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
    }



    public String getPlayerName() {
        return playerName;
    }

}

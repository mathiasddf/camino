package caminito;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class PlayerPanel extends JPanel {
    private JLabel playerLabel;
    private BoardPanel boardPanel;
    private final int[] PLAYER_POS = new int[2]; // Posición actual del jugador
    private final int TILE_SIZE = 60;
    private int diceRoll = 0;
    private String playerName;

    public PlayerPanel(String playerName, BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        this.playerName = playerName;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 200));
        setOpaque(false);
        
        PLAYER_POS[0] = 1; // Posición inicial del jugador
        PLAYER_POS[1] = 1; // Posición inicial del jugador

        // Panel para los datos del jugador
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setOpaque(false);
        
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayer(g);
    }

    public void rollDice(int diceRoll) {
        this.diceRoll = diceRoll;
        movePlayer();
    }

    private void drawPlayer(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(PLAYER_POS[1] * TILE_SIZE + 5, PLAYER_POS[0] * TILE_SIZE + 5, TILE_SIZE - 10, TILE_SIZE - 10);
    }

    private void movePlayer() {
        int[][] path = {
            {1, 1},{2,1},{3,1},{3,2},{3,3},{3,4},{3,5},{2,5},
            {1,5},{0,5},{0,6},{0,7},{0,8},{1,8},{2,8},{2,7},{3,7},
            {4,7},{4,8},{4,9},{4,10},{3,10},{2,10},{2,11},{2,12},{1,12},{1,13}
        };
        int currentPosition = 0;
        for (int i = 0; i < path.length; i++) {
            if (PLAYER_POS[0] == path[i][0] && PLAYER_POS[1] == path[i][1]) {
                currentPosition = i;
                break;
            }
        }
        int newPosition = currentPosition + diceRoll;
        if (newPosition >= path.length) {
            newPosition = path.length - 1;
        }
        PLAYER_POS[0] = path[newPosition][0];
        PLAYER_POS[1] = path[newPosition][1];
        repaint();

        if (newPosition == path.length - 1) {
            System.out.println("¡Jugador gana!");
            // Aquí puedes agregar lógica para mostrar un mensaje de victoria o reiniciar el juego
        }
    }

    public String getPlayerName() {
        return playerName;
    }
}

package src.caminito;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    private JLabel playerLabel;
    private BoardPanel boardPanel;

    public PlayerPanel(String playerName, BoardPanel boardPanel) {
        this.boardPanel = boardPanel;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 200));
        setOpaque(false);

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

    public void updateBoard(int playerNumber, int newPosition) {
        if (boardPanel != null) {
            boardPanel.updatePlayerPosition(playerNumber, newPosition);
        } else {
            System.err.println("Error: boardPanel es null.");
        }
    }

    private int getPlayerNumber() {
        if (playerLabel.getText().equals("Jugador 1")) {
            return 1;
        } else if (playerLabel.getText().equals("Jugador 2")) {
            return 2;
        } else {
            return -1; // Valor de error
        }
    }
}

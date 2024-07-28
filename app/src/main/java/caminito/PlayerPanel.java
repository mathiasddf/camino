package caminito;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayerPanel extends JPanel {
    private JLabel playerLabel;
    private BoardPanel boardPanel;
    private final int[] PLAYER_START_POS = {1, 1}; // Posición inicial del jugador
    private final int[] PLAYER_ONE_POS = new int[2]; // Posición actual del jugador 1
    private final int[] PLAYER_TWO_POS = new int[2]; // Posición actual del jugador 2
    private String playerName;

    public PlayerPanel(String playerName, BoardPanel boardPanel, String playerNumber) {
        this.boardPanel = boardPanel;
        this.playerName = playerName;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 200));
        setOpaque(false);
        PLAYER_ONE_POS[0] = PLAYER_START_POS[0];
        PLAYER_ONE_POS[1] = PLAYER_START_POS[1];
        PLAYER_TWO_POS[0] = PLAYER_START_POS[0];
        PLAYER_TWO_POS[1] = PLAYER_START_POS[1];

        // Panel para los datos del jugador
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setOpaque(false);

        // Crear y formatear la etiqueta del jugador
        playerLabel = new JLabel(playerNumber + ": " + playerName, SwingConstants.CENTER);
        playerLabel.setFont(playerLabel.getFont().deriveFont(18f)); // Cambiar el tamaño de la fuente a 18
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
}
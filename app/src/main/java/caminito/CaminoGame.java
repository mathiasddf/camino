package caminito;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JFrame;


public class CaminoGame extends JPanel {

    private PlayerPanel player1Panel;
    private PlayerPanel player2Panel;
    private JButton rollDiceButton;
    private boolean player1Turn = true;
    private BoardPanel boardPanel1;
    private BoardPanel boardPanel2;

    public CaminoGame(String player1Name, String player2Name, JFrame mainFrame) {
        setLayout(new BorderLayout());

        // Crear panel para los datos de los jugadores y tableros
        JPanel playerPanels = new JPanel(new GridLayout(2, 1));

        // Crear y agregar el panel del tablero y los datos del jugador 1
        boardPanel1 = new BoardPanel("img/fondoTablero.jpeg", "BLUE", player1Name, mainFrame);
        player1Panel = new PlayerPanel(player1Name, boardPanel1, "Jugador 1");
        playerPanels.add(player1Panel);

        // Crear y agregar el panel del tablero y los datos del jugador 2
        boardPanel2 = new BoardPanel("img/fondoTablero.jpeg", "RED", player2Name, mainFrame);
        player2Panel = new PlayerPanel(player2Name, boardPanel2, "Jugador 2");
        playerPanels.add(player2Panel);
        add(playerPanels, BorderLayout.CENTER);

        // Botón para lanzar el dado
        rollDiceButton = new JButton("Lanzar dado");
        rollDiceButton.setPreferredSize(new Dimension(150, 20));
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player1Turn) {
                    boardPanel1.rollDice(); // Llama a rollDice en el panel del tablero del jugador 1
                } else {
                    boardPanel2.rollDice(); // Llama a rollDice en el panel del tablero del jugador 2
                }
                player1Turn = !player1Turn;
            }
        });

        // Crear un panel para los botones y agregar el botón de lanzar dado
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rollDiceButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Configurar los listeners de victoria
        boardPanel1.setGameWinListener(new BoardPanel.GameWinListener() {
            @Override
            public void onGameWin(String playerName) {
                resetGame();
            }
        });

        boardPanel2.setGameWinListener(new BoardPanel.GameWinListener() {
            @Override
            public void onGameWin(String playerName) {
                resetGame();
            }
        });
    }

    private void resetGame() {
        boardPanel1.resetBoard();
        boardPanel2.resetBoard();
        player1Turn = true;
    }
}

package src.caminito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CaminoGame extends JPanel {
    private PlayerPanel player1Panel;
    private PlayerPanel player2Panel;
    private JButton rollDiceButton;
    private JButton backToMenuButton;
    private boolean player1Turn = true;
    private final Random random = new Random();

    public CaminoGame() {
        setLayout(new BorderLayout());

        // Crear panel para los datos de los jugadores y tableros
        JPanel playerPanels = new JPanel(new GridLayout(2, 1));

        // Crear y agregar el panel del tablero y los datos del jugador 1
        BoardPanel boardPanel1 = new BoardPanel("./img/snake.jpg");
        player1Panel = new PlayerPanel("Jugador 1", boardPanel1);
        playerPanels.add(player1Panel);

        // Crear y agregar el panel del tablero y los datos del jugador 2
        BoardPanel boardPanel2 = new BoardPanel("./img/snake.jpg");
        player2Panel = new PlayerPanel("Jugador 2", boardPanel2);
        playerPanels.add(player2Panel);

        add(playerPanels, BorderLayout.CENTER);

        // Botones para lanzar el dado y volver al menú
        rollDiceButton = new JButton("Lanzar Dado");
        rollDiceButton.setPreferredSize(new Dimension(150, 50));
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        backToMenuButton = new JButton("Volver al Menú");
        backToMenuButton.setPreferredSize(new Dimension(150, 50));
        backToMenuButton.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                parentFrame.getContentPane().removeAll();
                parentFrame.add(new SplashScreen(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

        // Crear un panel para los botones y agregarlos
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rollDiceButton);
        buttonPanel.add(backToMenuButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void rollDice() {
        int diceResult = random.nextInt(6) + 1;
        PlayerPanel currentPlayerPanel = player1Turn ? player1Panel : player2Panel;

        int newPosition = diceResult; // Ya no se almacena la posición en el PlayerPanel
        boolean correctAnswer = askQuestion();

        if (correctAnswer) {
            currentPlayerPanel.updateBoard(getCurrentPlayerNumber(), newPosition);
        } else {
            currentPlayerPanel.updateBoard(getCurrentPlayerNumber(), -newPosition);
        }

        // Cambiar de turno
        player1Turn = !player1Turn;
    }

    private boolean askQuestion() {
        return random.nextBoolean();
    }

    private int getCurrentPlayerNumber() {
        return player1Turn ? 1 : 2;
    }
}

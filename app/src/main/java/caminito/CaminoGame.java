package caminito;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.GridLayout; // Asegúrate de importar GridLayout

public class CaminoGame extends JPanel {

    private PlayerPanel player1Panel;
    private PlayerPanel player2Panel;
    private JButton rollDiceButton;
    private boolean player1Turn = true;

    public CaminoGame() {
        setLayout(new BorderLayout());

        // Crear panel para los datos de los jugadores y tableros
        JPanel playerPanels = new JPanel(new GridLayout(2, 1));

        // Crear y agregar el panel del tablero y los datos del jugador 1
        BoardPanel boardPanel1 = new BoardPanel("img/fondoTablero.jpeg","BLUE");
        player1Panel = new PlayerPanel("Jugador 1", boardPanel1);
        playerPanels.add(player1Panel);

        // Crear y agregar el panel del tablero y los datos del jugador 2
        BoardPanel boardPanel2 = new BoardPanel("img/fondoTablero.jpeg", "RED");
        player2Panel = new PlayerPanel("Jugador 2", boardPanel2);
        playerPanels.add(player2Panel);
        add(playerPanels, BorderLayout.CENTER);

        // Botón para lanzar el dado
        rollDiceButton = new JButton("Lanzar dado");
        rollDiceButton.setPreferredSize(new Dimension(150, 50));
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
        

        
    }
    
}

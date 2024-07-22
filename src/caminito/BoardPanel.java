package src.caminito;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private final Image boardImage;

    public BoardPanel(String boardImagePath) {
        this.boardImage = new ImageIcon(boardImagePath).getImage();
        setPreferredSize(new Dimension(boardImage.getWidth(null), boardImage.getHeight(null)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(boardImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void updatePlayerPosition(int playerNumber, int position) {
        // Implementa la lógica para actualizar la posición del jugador en el tablero
    }
}

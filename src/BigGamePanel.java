import javax.swing.*;
import java.awt.*;
import java.nio.channels.NetworkChannel;
import java.util.HashMap;
import java.util.Map;

public class BigGamePanel extends JPanel {
    GamePanel gamePanel;
    Piece piece;
    NextPieceBoard nextPieceBoard;

    Map<Rectangle, Color> borderPixels = new HashMap<>();

    GridBagConstraints gbc = new GridBagConstraints();

    public BigGamePanel(Piece piece, NextPieceBoard nextPieceBoard){
        this.piece = piece;
        this.nextPieceBoard = nextPieceBoard;
        this.gamePanel = new GamePanel(piece, nextPieceBoard);

        this.setPreferredSize(new Dimension(240,440));
        this.setLayout(new GridBagLayout());
        gbc.insets = new Insets(20,20,20,20);
        this.add(gamePanel, gbc);

        Color color = new Color(0,0,0,80);

        //top
        for(int i = 0; i<10; i++){
            borderPixels.put(new Rectangle((i*20)+20, 0, 20,20), color);
        }
        //bottom
        for(int i = 0; i<10; i++){
            borderPixels.put(new Rectangle((i*20)+20, 420, 20,20), color);
        }
        //right
        for(int i = 0; i<22; i++){
            borderPixels.put(new Rectangle(0, i*20, 20,20), color);
        }
        //left
        for(int i = 0; i<22; i++){
            borderPixels.put(new Rectangle(220, i*20, 20,20), color);
        }





    }

    @Override
    public void paintComponent(Graphics g){
        for(Map.Entry<Rectangle, Color> entry: borderPixels.entrySet()){
            piece.drawPixel(g, entry);
        }
    }
}
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class NextPieceBoard extends JPanel {

    Piece piece;

    public NextPieceBoard(Piece piece){
        this.setPreferredSize(new Dimension(175, 60));
        this.piece = piece;
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        drawNextPiece(g, piece.nextPiece);
    }

    public void drawNextPiece(Graphics g, Map<Rectangle, Color> pieceMap){
        for (Map.Entry<Rectangle, Color> entry: pieceMap.entrySet()){
            if (entry != null) {
                piece.drawPixel(g, entry);
            }
        }
    }
}

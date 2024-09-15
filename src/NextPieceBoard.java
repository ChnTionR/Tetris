import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class NextPieceBoard extends JPanel {

    Piece piece;
    Draw draw;
    int preferredX;
    int preferredY;

    int[][] chars = {
            {
                    1, 0, 0, 1,
                    1, 1, 0, 1,
                    1, 0, 1, 1,
                    1, 0, 0, 1,
                    1, 0, 0, 1

            },
            {
                    1, 1, 1,
                    1, 0, 0,
                    1, 1, 1,
                    1, 0, 0,
                    1, 1, 1
            },
            {
                    1, 0, 1,
                    1, 0, 1,
                    0, 1, 0,
                    1, 0, 1,
                    1, 0, 1
            },
            {
                    1, 1, 1,
                    0, 1, 0,
                    0, 1, 0,
                    0, 1, 0,
                    0, 1, 0
            },
            {
                    0, 0, 0,
                    0, 1, 0,
                    0, 0, 0,
                    0, 1, 0,
                    0, 0, 0
            }
    };

    public NextPieceBoard(Piece piece){
        this.piece = piece;
        draw = new Draw();
        preferredX = 0;
        preferredY = 0;
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        drawNextPiece(g, piece.nextPiece);
        updateSize();
    }

    public void drawNextPiece(Graphics g, Map<Rectangle, Color> pieceMap){
        for (Map.Entry<Rectangle, Color> entry: pieceMap.entrySet()){
            if (entry != null) {
                draw.drawPixel(g, entry);
            }
        }
    }

    public void updateSize(){
        switch (piece.nextPieceStr) {
            case "b":
                preferredX = 40;
                preferredY = 40;
                break;
            case "i":
                preferredX = 80;
                preferredY = 20;
                break;
            default:
                preferredX = 60;
                preferredY = 40;
        }
    }

    public void drawChar(Graphics g, int character, int posX, int posY){
        int charWidth = chars[character].length/5;
        Graphics2D g2D = (Graphics2D) g;
        for (int i = 0; i < 5; i++) { //rows
            for (int j = 0; j < charWidth; j++) { //columns

                int pixel = chars[character][i*charWidth + j];

                if (pixel == 1) {
                    g2D.setColor(Color.white);
                    g2D.fillRect((j + posX)*5, (i + posY)*5, 5, 5);
                }
            }
        }
    }
}

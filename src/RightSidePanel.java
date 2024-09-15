import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RightSidePanel extends JPanel {

    Draw draw;
    Map<Rectangle, Color> borderPixels;
    Map<Rectangle, Color> fillerPixels;
    NextPieceBoard nextPieceBoard;

    int[][] fillerPixelPos = {
            {0,1},{1,1},{2,1},{3,1},{4,1},{5,1},{6,1},{7,1},{8,1},
            {0,2},{7,2},{8,2},
            {0,3},{7,3},{8,3},
            {0,4},{7,4},{8,4},
            {0,5},{7,5},{8,5},
            {0,6},{7,6},{8,6},
            {0,7},{1,7},{2,7},{3,7},{4,7},{5,7},{6,7},{7,7},{8,7},
            {0,20},{1,20},{2,20},{3,20},{4,20},{5,20},{6,20},{7,20},{8,20},
    };

    RightSidePanel(NextPieceBoard nextPieceBoard, ScoreBoard scoreBoard){

        borderPixels = new HashMap<>();
        fillerPixels = new HashMap<>();
        draw = new Draw();
        this.nextPieceBoard = nextPieceBoard;

        this.setPreferredSize(new Dimension(200,440));
        this.setLayout(null);

        this.nextPieceBoard.setLocation(40,40);
        scoreBoard.setBounds(0,160, 180,240);
        this.add(this.nextPieceBoard);
        this.add(scoreBoard);

        Color color = new Color(0,0,0,80);
        for(int[] pos: fillerPixelPos){
            fillerPixels.put(new Rectangle(pos[0]*20, pos[1]*20, 20,20), color);
        }

        //top
        for(int i = 0; i<11; i++){
            borderPixels.put(new Rectangle((i*20), 0, 20,20), color);
        }
        //bottom
        for(int i = 0; i<11; i++){
            borderPixels.put(new Rectangle((i*20), 420, 20,20), color);
        }
        //right
        for(int i = 0; i<22; i++){
            borderPixels.put(new Rectangle(180, i*20, 20,20), color);
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawNextPiece(g,30,50);
        for(Map.Entry<Rectangle, Color> entry: borderPixels.entrySet()){
            draw.drawPixel(g,entry);
        }

        for(Map.Entry<Rectangle, Color> entry: fillerPixels.entrySet()){
            draw.drawPixel(g,entry);
        }
    }

    public void drawNextPiece(Graphics g, int x, int y){
        g.setColor(Color.black);
        g.fillRect(20, 40, 120, 100);
        nextPieceBoard.drawChar(g, 0, x/5 + 1, y/5 + 1);
        nextPieceBoard.drawChar(g, 1, x/5 + 6, y/5 + 1);
        nextPieceBoard.drawChar(g, 2, x/5 + 10, y/5 + 1);
        nextPieceBoard.drawChar(g, 3, x/5 + 14, y/5 + 1);
        nextPieceBoard.drawChar(g, 4, x/5 + 18, y/5 + 1);

        nextPieceBoard.updateSize();
        nextPieceBoard.setBounds((100- nextPieceBoard.preferredX)/2 + x, y+40, nextPieceBoard.preferredX, nextPieceBoard.preferredY);
        revalidate();
        repaint();
    }

}

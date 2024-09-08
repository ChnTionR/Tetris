import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    BigGamePanel bigGamePanel;
    ScoreBoard scoreBoard;
    Piece piece;

    public GameFrame(Piece piece, ScoreBoard scoreBoard){
        this.piece = piece;
        bigGamePanel = new BigGamePanel(this.piece);
        this.scoreBoard = scoreBoard;
        //set attributes
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));

        this.add(bigGamePanel);
        this.add(scoreBoard);

        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

}

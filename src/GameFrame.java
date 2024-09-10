import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    BigGamePanel bigGamePanel;
    FlowLayout flowLayout;
    ScoreBoard scoreBoard;
    Piece piece;
    RightSidePanel rightSidePanel;
    NextPieceBoard nextPieceBoard;
    ExtraFrame extraFrame;

    public GameFrame(ExtraFrame extraFrame){
        this.extraFrame = extraFrame;

        flowLayout = new FlowLayout();
        flowLayout.setHgap(0);
        flowLayout.setVgap(0);

        scoreBoard = new ScoreBoard();
        piece = new Piece(scoreBoard, extraFrame);
        nextPieceBoard = new NextPieceBoard(piece);
        bigGamePanel = new BigGamePanel(piece,nextPieceBoard);
        rightSidePanel = new RightSidePanel(nextPieceBoard, scoreBoard);


        //set attributes
        this.setLayout(flowLayout);

        this.add(bigGamePanel);
        this.add(rightSidePanel);

        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void stopWindow(){
        bigGamePanel.stopGame();
        this.dispose();
    }

}

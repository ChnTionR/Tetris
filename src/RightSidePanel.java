import javax.swing.*;

public class RightSidePanel extends JPanel {

    RightSidePanel(NextPieceBoard nextPieceBoard, ScoreBoard scoreBoard){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(nextPieceBoard);
        this.add(Box.createVerticalStrut(20));
        this.add(scoreBoard);
    }
}

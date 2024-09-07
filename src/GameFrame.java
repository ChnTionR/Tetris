import javax.swing.*;

public class GameFrame extends JFrame {
    BigGamePanel bigGamePanel;

    public GameFrame(){
        bigGamePanel = new BigGamePanel();
        //set attributes
        this.add(bigGamePanel);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

}

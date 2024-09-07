import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    GamePanel gamePanel;

    public MyFrame(){
        gamePanel = new GamePanel();
        //set attributes
        this.add(gamePanel);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

}

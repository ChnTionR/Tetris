import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExtraFrame extends JFrame {
    GameFrame gameFrame;
    JPanel startScreen;
    JPanel endScreen;
    JPanel startGameButton;
    JPanel restartGameButton;
    GridBagConstraints gbc;
    public ExtraFrame(){
        //attributes
        this.setPreferredSize(new Dimension(500,500));
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        spawnStartScreen();
    }


    public void spawnStartScreen(){
        this.getContentPane().removeAll();
        startScreen = new JPanel();
        startGameButton = new JPanel();
        startGameButton.setPreferredSize(new Dimension(100,40));
        startGameButton.setBackground(Color.BLUE);
        startGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ExtraFrame.this.setVisible(false);
                gameFrame = new GameFrame(ExtraFrame.this);
            }
        });

        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.weightx = 8;
        this.add(startGameButton, gbc);
        this.setVisible(true);

        this.revalidate();
        this.repaint();
    }

    public void spawnEndScreen(){
        this.getContentPane().removeAll();
        gameFrame.stopWindow();

        endScreen = new JPanel();
        restartGameButton = new JPanel();
        restartGameButton.setPreferredSize(new Dimension(100,40));
        restartGameButton.setBackground(Color.BLUE);
        restartGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ExtraFrame.this.setVisible(false);
                gameFrame = new GameFrame(ExtraFrame.this);
            }
        });

        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.weightx = 8;
        this.add(restartGameButton, gbc);
        this.setVisible(true);

        this.revalidate();
        this.repaint();
    }
}

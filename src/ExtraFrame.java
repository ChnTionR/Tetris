import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtraFrame extends JFrame {
    GameFrame gameFrame;
    JPanel startScreen;
    JPanel endScreen;
    JPanel startGameButton;
    JPanel restartGameButton;
    GridBagConstraints gbc;
    ScoreBoard scoreBoard;
    File file;

    List<String> top5Score = new ArrayList<>(List.of("0","0","0","0","0"));
    int topScore;

    public ExtraFrame(){

        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

        String folderPath = desktopPath + File.separator + "Tetris";
        File folder = new File(folderPath);

        file = new File(folderPath + File.separator + "topScores.txt");

        //create folder
        if(!folder.exists()){
            folder.mkdir();
        }

        if(!file.exists()){
            try{
                if(file.createNewFile()){ //create a file if it doesn't exist
                    Files.write(file.toPath(), top5Score); //write all 0's to it
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{

            try{
                top5Score = Files.readAllLines(file.toPath()); //read and write top 5 scores to a list
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        topScore = 0;
        scoreBoard = new ScoreBoard(topScore);

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

        scoreBoard.topScore = Integer.parseInt(top5Score.getFirst());

        startScreen = new JPanel();
        startGameButton = new JPanel();
        startGameButton.setPreferredSize(new Dimension(100,40));
        startGameButton.setBackground(Color.BLUE);
        startGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ExtraFrame.this.setVisible(false);
                gameFrame = new GameFrame(ExtraFrame.this, scoreBoard);
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
        for(String score: top5Score) {
            if(Integer.parseInt(score) < scoreBoard.score) {
                top5Score.add(top5Score.indexOf(score), String.valueOf(scoreBoard.score));
                top5Score.removeLast();
                break;
            }
        }

        try{
            Files.write(file.toPath(), top5Score);
        }catch (IOException e){
            throw new RuntimeException();
        }

        scoreBoard.topScore = Integer.parseInt(top5Score.getFirst());

        scoreBoard.score = 0;

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
                gameFrame = new GameFrame(ExtraFrame.this, scoreBoard);
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

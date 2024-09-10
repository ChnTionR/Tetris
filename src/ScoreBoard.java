import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {

    int score = 0;
    int level = 0;

    int[][] chars ={
            {
                1,1,1,
                1,0,1,
                1,0,1,
                1,0,1,
                1,1,1

            },
            {
                0,1,0,
                1,1,0,
                0,1,0,
                0,1,0,
                1,1,1
            },
            {
                1,1,1,
                0,0,1,
                1,1,1,
                1,0,0,
                1,1,1
            },
            {
                1,1,1,
                0,0,1,
                1,1,1,
                0,0,1,
                1,1,1
            },
            {
                1,0,1,
                1,0,1,
                1,1,1,
                0,0,1,
                0,0,1
            },
            {
                1,1,1,
                1,0,0,
                1,1,1,
                0,0,1,
                1,1,1
            },
            {
                1,1,1,
                1,0,0,
                1,1,1,
                1,0,1,
                1,1,1
            },
            {
                1,1,1,
                0,0,1,
                0,1,1,
                0,0,1,
                0,0,1
            },
            {
                1,1,1,
                1,0,1,
                1,1,1,
                1,0,1,
                1,1,1
            },
            {
                1,1,1,
                1,0,1,
                1,1,1,
                0,0,1,
                0,0,1
            },
            {
                1,0,0,
                1,0,0,
                1,0,0,
                1,0,0,
                1,1,1
            },
            {
                1,1,1,
                1,0,0,
                1,1,1,
                1,0,0,
                1,1,1
            },
            {
                1,0,1,
                1,0,1,
                1,0,1,
                1,0,1,
                0,1,0
            },
            {
                0,0,0,
                0,1,0,
                0,0,0,
                0,1,0,
                0,0,0
            }
    };
    public ScoreBoard(){
        this.setPreferredSize(new Dimension(175, 80));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,this.getWidth(), this.getHeight());
        drawScore(g, score);
        drawLevel(g, level);
    }

    public void drawChar(Graphics g, int character, int posX, int posY){
        Graphics2D g2D = (Graphics2D) g;
        for (int i = 0; i < 5; i++) { //rows
            for (int j = 0; j < 3; j++) { //columns

                int pixel = chars[character][i*3 + j];

                if (pixel == 1) {
                    g2D.setColor(Color.white);
                    g2D.fillRect((j + posX)*5, (i + posY)*5, 5, 5);
                }
            }
        }
    }

    public void drawScore(Graphics g, int score){
        String scoreStr = String.valueOf(score);
        String fullScore = "";
        if(scoreStr.length() <= 8){
            for(int i = 0; i < 8 - scoreStr.length(); i++){
                fullScore = fullScore.concat("0");
            }
            fullScore = fullScore.concat(scoreStr);

            for(int i = 0; i <8; i ++){
                drawChar(g, Character.getNumericValue(fullScore.charAt(i)), 2+(i*4), 2);
            }
        }else {
            for(int i = 0; i <8; i ++){
                drawChar(g, 9, 1+(i*4), 2);
            }
        }
    }

    public void drawLevel(Graphics g, int level){
        String levelStr = String.valueOf(level);
        String fullLevel = "";
        drawChar(g, 10, 2, 9);
        drawChar(g, 11, 6, 9);
        drawChar(g, 12, 10, 9);
        drawChar(g, 11, 14, 9);
        drawChar(g, 10, 18, 9);
        drawChar(g, 13, 22, 9);
        for(int i = 0; i < 2 - levelStr.length(); i++){
            fullLevel = fullLevel.concat("0");
        }
        fullLevel = fullLevel.concat(levelStr);
        for(int i = 0; i < 2; i++){
            drawChar(g, Character.getNumericValue(fullLevel.charAt(i)), (i*4) + 26, 9);
        }
    }

    public void increaseScore(int numLineClears, int level){
        switch (numLineClears){
            case 1 -> score += 40 * (level+1);
            case 2 -> score += 100 * (level+1);
            case 3 -> score += 300 * (level+1);
            case 4 -> score += 1200 * (level+1);
        }
        this.level = level;
        repaint();
    }
}

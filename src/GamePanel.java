import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener{
    Piece piece;
     //create timer
    Timer fallTimer = new Timer(500, _-> {
        piece.move('y', "+");
        if(!piece.controllable){
            piece.spawnNew();

        }
        repaint();
    });

    //constructor
    public GamePanel() {
        piece = new Piece();
        //set panel attributes
        this.setPreferredSize(new Dimension(200, 400));
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        //start timer
        fallTimer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        //clear background
        super.paintComponent(g);


        for (Rectangle i: piece.controlledPiece){
            if (i != null) {
                piece.drawPixel(g, i);
            }
        }

        for (Rectangle i: piece.boardPixel){
            if (i != null) {
                piece.drawPixel(g, i);
            }
        }

    }

    //empty
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            //"a"
            case 65:
                piece.move('x', "-");
                repaint();
                break;
            //"d"
            case 68:
                piece.move('x', "+");
                repaint();
                break;
            //"s"
            case 83:
                piece.move('y', "+");
                if(!piece.controllable){
                    piece.spawnNew();
                    fallTimer.restart();
                }
                repaint();
                break;
            //"e"
            case 69:
                piece.rotate("right");
                repaint();
                break;
            //"q"
            case 81:
                piece.rotate("left");
                repaint();
                break;
            //"space"
            case 32:
            while(piece.controllable){
                piece.move('y', "+");
            }
            repaint();
            if(!piece.controllable){
                piece.spawnNew();
                fallTimer.restart();
            }
            repaint();
            break;
        }

    }

    //get key codes
    @Override
    public void keyReleased(KeyEvent e) {
        this.requestFocusInWindow();
        System.out.printf("%c: %d\n", e.getKeyChar(), e.getKeyCode());
    }
}
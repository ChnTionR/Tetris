import javax.lang.model.type.ArrayType;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Piece{

    Random rand = new Random();

    int posX;
    int posY;

    boolean controllable;

    Map<Rectangle, Color> pixel = new HashMap<>();
    Rectangle leftBorder = new Rectangle(-20,0,20, 400);
    Rectangle rightBorder = new Rectangle(200, 0, 20,400);
    Rectangle bottomBorder = new Rectangle(0, 400, 200, 20);

    Map<Rectangle, Color> boardPixel = new HashMap<>();
    Map<Rectangle, Color> controlledPiece = new HashMap<>();

    int[][][] chosenPiece = new int[4][4][2];
    String chosenPieceStr;
    int[][] chosenSprite = new int[4][2];
    int spriteNo;

    String[] pieces = {"i", "t", "l1", "l2", "z1", "z2", "b"};

    int[][] iInitial = {{0,0},{1,0},{2,0},{3,0}};
    int[][][] l = {
            {{2,0},{2,1},{2,2},{2,3}},
            {{0,2},{1,2},{2,2},{3,2}},
            {{1,0},{1,1},{1,2},{1,3}},
            {{0,2},{1,2},{2,2},{3,2}},
    };

    int[][] tInitial = {{0,0},{1,0},{2,0},{1,1}};
    int[][][] t = {
            {{1,0},{0,1},{1,1},{1,2}},
            {{1,1},{0,2},{1,2},{2,2}},
            {{1,0},{1,1},{2,1},{1,2}},
            {{0,1},{1,1},{2,1},{1,2}},
    };

    int[][] l1Initial = {{0,0},{1,0},{2,0},{0,1}};
    int[][][] l1 = {
            {{0,0},{1,0},{1,1},{1,2}},
            {{2,1},{0,2},{1,2},{2,2}},
            {{1,0},{1,1},{1,2},{2,2}},
            {{0,1},{1,1},{2,1},{0,2}},
    };

    int[][] l2Initial = {{0,0},{1,0},{2,0},{2,1}};
    int[][][] l2 = {
            {{1,0},{1,1},{1,2},{0,2}},
            {{0,1},{0,2},{1,2},{2,2}},
            {{1,0},{2,0},{1,1},{1,2}},
            {{0,1},{1,1},{2,1},{2,2}},
    };

    int[][] z1Initial = {{1,0},{2,0},{0,1},{1,1}};
    int[][][] z1 = {
            {{1,0},{1,1},{2,1},{2,2}},
            {{1,1},{2,1},{0,2},{1,2}},
            {{0,0},{0,1},{1,1},{1,2}},
            {{1,1},{2,1},{0,2},{1,2}},
    };

    int[][] z2Initial = {{0,0},{1,0},{1,1},{2,1}};
    int[][][] z2 = {
            {{2,0},{1,1},{2,1},{1,2}},
            {{0,1},{1,1},{1,2},{2,2}},
            {{1,0},{0,1},{1,1},{0,2}},
            {{0,1},{1,1},{1,2},{2,2}},
    };

    int[][] bInitial = {{1,0},{2,0},{1,1},{2,1}};
    int[][][] b = {
            {{1,0},{2,0},{1,1},{2,1}},
            {{1,0},{2,0},{1,1},{2,1}},
            {{1,0},{2,0},{1,1},{2,1}},
            {{1,0},{2,0},{1,1},{2,1}},
    };

    //constructor
    public Piece(){
        spawnNew();
    }

    public void createPixel(int x, int y){
        Color color = new Color(0,0,0,100);
        pixel.clear();
        switch (chosenPieceStr){
            case "i" -> color = new Color(120,180,230,100);
            case "t" -> color = new Color(120,0,210, 100);
            case "l1" -> color = new Color(200,100,1,100);
            case "l2" -> color = new Color(0,60,170, 100);
            case "z1" -> color = new Color(0,175,0,100);
            case "z2" -> color = new Color(230,0,0,100);
            case "b" -> color = new Color(230,200,0,100);
        }
        pixel.put(new Rectangle((x+posX)*20, (y+posY)*20, 20, 20), color);
    }

    public void createPiece(int[][] sprite){
        controlledPiece.clear();
        for(int[] pixels: sprite){
            createPixel(pixels[0], pixels[1]);
            controlledPiece.putAll(pixel);
        }

    }


    public void choosePiece(String pieceName){
        switch (pieceName){
            case "i":
                chosenPiece = l;
                chosenSprite = iInitial;
                break;
            case "t":
                chosenPiece = t;
                chosenSprite = tInitial;
                break;
            case "l1":
                chosenPiece = l1;
                chosenSprite = l1Initial;
                break;
            case "l2":
                chosenPiece = l2;
                chosenSprite = l2Initial;
                break;
            case "z1":
                chosenPiece = z1;
                chosenSprite = z1Initial;
                break;
            case "z2":
                chosenPiece = z2;
                chosenSprite = z2Initial;
                break;
            case "b":
                chosenPiece = b;
                chosenSprite = bInitial;
                break;
        }
    }

    public void drawPixel(Graphics g, Map.Entry<Rectangle, Color> entry){
        Graphics2D g2D = (Graphics2D) g;
        int[] topSideX = {entry.getKey().x, entry.getKey().x+4, entry.getKey().x+16, entry.getKey().x+20};
        int[] topSideY = {entry.getKey().y, entry.getKey().y+4, entry.getKey().y+4, entry.getKey().y};

        int[] leftSideX = {entry.getKey().x, entry.getKey().x+4, entry.getKey().x+4, entry.getKey().x};
        int[] leftSideY = {entry.getKey().y,entry.getKey().y+4, entry.getKey().y+16, entry.getKey().y+20};

        int[] rightSideX = {entry.getKey().x+20, entry.getKey().x+16, entry.getKey().x+16, entry.getKey().x+20};
        int[] rightSideY = {entry.getKey().y,entry.getKey().y+4, entry.getKey().y+16, entry.getKey().y+20};

        int[] bottomSideX = {entry.getKey().x, entry.getKey().x+4, entry.getKey().x+16, entry.getKey().x+20};
        int[] bottomSideY = {entry.getKey().y+20, entry.getKey().y+16, entry.getKey().y+16, entry.getKey().y+20};

        int[] middleX = {entry.getKey().x+4, entry.getKey().x+16, entry.getKey().x+16, entry.getKey().x+4};
        int[] middleY = {entry.getKey().y+4,entry.getKey().y+4, entry.getKey().y+16, entry.getKey().y+16};

        g2D.setColor(new Color(180,180,180));
        g2D.fillPolygon(topSideX, topSideY, 4);

        g2D.setColor(new Color(160,160,160));
        g2D.fillPolygon(leftSideX, leftSideY, 4);

        g2D.setColor(new Color(140,140,140));
        g2D.fillPolygon(middleX, middleY, 4);

        g2D.setColor(new Color(80,80,80));
        g2D.fillPolygon(rightSideX, rightSideY, 4);

        g2D.setColor(new Color(50,50,50));
        g2D.fillPolygon(bottomSideX, bottomSideY, 4);

        g2D.setColor(entry.getValue());
        g2D.fill(new Rectangle(entry.getKey().x, entry.getKey().y, 20,20));
    }

    public void spawnNew(){
        if (pixel != null){
            boardPixel.putAll(controlledPiece);
        }
        for(int i = 0; i < 4; i++){
            removeLine(checkLines());
        }

        chosenPieceStr = pieces[rand.nextInt(pieces.length)];

        choosePiece(chosenPieceStr);
        controllable = true;
        posX = 3;
        posY = 0;
        spriteNo = 3;
        assert chosenSprite != null;
        createPiece(chosenSprite);
    }

    public void move(char c, String s){

        switch (c){
            //go in x direction
            case 'x':
                if(s.equals("+")){//right
                    posX ++;
                    createPiece(chosenSprite);
                    if(detectCollision(rightBorder) || detectCollision()){
                        posX --;
                        createPiece(chosenSprite);
                    }
                }else{//left
                    posX --;
                    createPiece(chosenSprite);
                    if(detectCollision(leftBorder) || detectCollision()){
                        posX ++;
                        createPiece(chosenSprite);
                    }
                }
                break;
            //go in y direction
            case 'y':
                if(s.equals("+")){//down
                    posY ++;
                    createPiece(chosenSprite);
                    if(detectCollision(bottomBorder) || detectCollision()){
                        posY--;
                        controllable = false;
                        createPiece(chosenSprite);
                    }
                }else{//up
                    posY --;
                    createPiece(chosenSprite);
                }
                break;
        }

    }

    public void rotate(String direction){
        switch (direction){
            case "right":
                spriteNo++;
                if(spriteNo > 3){
                    spriteNo = 0;
                }
                chosenSprite = chosenPiece[spriteNo];
                createPiece(chosenSprite);
                if(detectCollision()|| detectCollision(leftBorder)|| detectCollision(rightBorder)|| detectCollision(bottomBorder)){
                    spriteNo--;
                    if(spriteNo<0){
                        spriteNo = 3;
                    }
                }
                createPiece(chosenSprite);
                break;
            case "left":
                spriteNo--;
                if(spriteNo<0){
                    spriteNo = 3;
                }
                chosenSprite = chosenPiece[spriteNo];
                createPiece(chosenSprite);
                if(detectCollision()|| detectCollision(leftBorder)|| detectCollision(rightBorder)|| detectCollision(bottomBorder)){
                    spriteNo++;
                    if(spriteNo>3){
                        spriteNo = 0;
                    }
                }
                createPiece(chosenSprite);
                break;
        }

        chosenSprite = chosenPiece[spriteNo];
        createPiece(chosenSprite);
    }

    public boolean detectCollision(Rectangle r){
        boolean collision = false;
        for(Map.Entry<Rectangle, Color> controlRec: controlledPiece.entrySet()){
            if(controlRec.getKey().intersects(r)){
                collision = true;
            }
        }
        return collision;
    }

    public boolean detectCollision(){
        boolean collision = false;
        for (Map.Entry<Rectangle, Color> boardRec : boardPixel.entrySet()) {
            if (boardRec != null) {
                for(Map.Entry<Rectangle, Color> controlRec: controlledPiece.entrySet()){
                    if(controlRec.getKey().intersects(boardRec.getKey())){
                        collision = true;
                    }
                }
            }
        }
        return collision;

    }

    public int checkLines(){
        int[] rows = new int[20];
        //write how many pieces are in each row
        if (boardPixel != null){
            //each possible y-levels
            for(int i = 0; i<20; i++){
                //look at each square on the board
                for(Map.Entry<Rectangle, Color> boardRec: boardPixel.entrySet()){
                    if(boardRec.getKey().y == (20-i)*20){
                        rows[i] ++;
                    }
                }
            }
        }
        // if any rows are full
        for(int i = 0; i < 20; i++){
            if(rows[i] == 10) return i;
        }
        return -1;
    }

    public void removeLine(int lineNo){
        ArrayList<Rectangle> removeKey = new ArrayList<>();
        Map<Rectangle, Color> replaceKey = new HashMap<>();

        //declare which pixel needs to be removed
        if(lineNo != -1) {
            for (Map.Entry<Rectangle, Color> boardRec : boardPixel.entrySet()) {
                if (400 - (lineNo * 20) == boardRec.getKey().y) {
                    removeKey.add(boardRec.getKey());
                }

                //pieces above the cleared line
                if (400-(lineNo *20) > boardRec.getKey().y) {
                    removeKey.add(boardRec.getKey());
                    replaceKey.put(new Rectangle(boardRec.getKey().x, boardRec.getKey().y + 20, 20,20), boardRec.getValue());
                }
            }
            for(Rectangle rec: removeKey){
                boardPixel.remove(rec);
            }

            boardPixel.putAll(replaceKey);
        }
    }
}
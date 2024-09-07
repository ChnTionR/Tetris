import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Piece{

    Random rand = new Random();

    int posX;
    int posY;

    boolean controllable;

    Rectangle pixel;
    Rectangle leftBorder = new Rectangle(-20,0,20, 400);
    Rectangle rightBorder = new Rectangle(200, 0, 20,400);
    Rectangle bottomBorder = new Rectangle(0, 400, 200, 20);

    ArrayList<Rectangle> boardPixel = new ArrayList<>();
    ArrayList<Rectangle> controlledPiece = new ArrayList<>();

    int[][][] chosenPiece = new int[4][4][2];
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
        pixel = new Rectangle((x+posX)*20, (y+posY)*20, 20, 20);
    }

    public void createPiece(int[][] sprite){
        controlledPiece.clear();
        for(int[] pixels: sprite){
            createPixel(pixels[0], pixels[1]);
            controlledPiece.add(pixel);
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

    public void drawPixel(Graphics g, Rectangle r){
        Graphics2D g2D = (Graphics2D) g;
        int[] topSideX = {r.x, r.x+10, r.x+20};
        int[] topSideY = {r.y, r.y+10, r.y};

        int[] sideSideX = {r.x, r.x+20, r.x+20, r.x};
        int[] sideSideY = {r.y,r.y+20, r.y, r.y+20};

        int[] bottomSideX = {r.x, r.x+10, r.x+20};
        int[] bottomSideY = {r.y+20, r.y+10, r.y+20};

        g2D.setColor(new Color(180,180,180));
        g2D.fillPolygon(topSideX, topSideY, 3);

        g2D.setColor(new Color(150,150,150));
        g2D.fillPolygon(sideSideX, sideSideY, 4);

        g2D.setColor(new Color(80,80,80));
        g2D.fillPolygon(bottomSideX, bottomSideY, 3);
    }

    public void spawnNew(){
        if (pixel != null){
            boardPixel.addAll(controlledPiece);
        }
        for(int i = 0; i < 4; i++){
            removeLine(checkLines());
        }

        choosePiece(pieces[rand.nextInt(pieces.length)]);
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
        for(Rectangle rec: controlledPiece){
            if(rec.intersects(r)){
                collision = true;
            }
        }
        return collision;
    }

    public boolean detectCollision(){
        boolean collision = false;
        for (Rectangle boardPiece : boardPixel) {
            if (boardPiece != null) {
                for(Rectangle rec: controlledPiece){
                    if(rec.intersects(boardPiece)){
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
                for(Rectangle rec: boardPixel){
                    if(rec.y == (20-i)*20){
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
        int recX;
        int recY;

        //clear line
        boardPixel.removeIf(rec -> (400 - rec.y) / 20 == lineNo);

        int boardSize = boardPixel.size();

        //move pieces down
        if (lineNo != -1){
            for(int i = 0; i < boardSize; i++){
                //pieces above the cleared line
                if(boardPixel.get(i).y < (20-lineNo)*20){
                    recX = boardPixel.get(i).x;
                    recY = boardPixel.get(i).y;
                    //replace pieces
                    boardPixel.set(i, new Rectangle(recX,recY + 20,20,20));
                }
            }
        }
    }
}
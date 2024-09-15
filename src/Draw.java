import java.awt.*;
import java.util.Map;

public class Draw {
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
}

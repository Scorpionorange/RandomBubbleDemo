import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by ScorpionOrange on 2016/12/09.
 * A frame that contains a panel with drawings.
 */
public class RandomBubbleFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;

    public RandomBubbleFrame(){
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        add(new RandomBubbleComponent());
        pack();
    }

    public RandomBubbleFrame(int width, int height){
        setSize(width, height);
        add(new RandomBubbleComponent());
        pack();
    }
}

/**
 * A component that drawing a rectangle and some circle.
 */
class RandomBubbleComponent extends JComponent {
    private static final int DEFAULT_WIDTH = RandomBubbleFrame.DEFAULT_WIDTH;
    private static final int DEFAULT_HEIGHT = RandomBubbleFrame.DEFAULT_HEIGHT;

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        drawRectangle2D(g2);

        for(int i = 0; i < 10; i++){
            drawRandomCircle2D(g2);
        }
    }

    public void drawRectangle2D(Graphics2D g2){
        // draw a rectangle
        double leftX = 0;
        double topY = 0;
        double width = DEFAULT_WIDTH;
        double heigth = DEFAULT_HEIGHT;

        Rectangle2D rectangle2D = new Rectangle2D.Double(leftX, topY, width, heigth);
        g2.draw(rectangle2D);
    }

    public void drawRandomCircle2D(Graphics2D g2){
        // draw a circle in random center and with random radius
        double centerX = Math.random() * DEFAULT_WIDTH;
        double centerY = Math.random() * DEFAULT_HEIGHT;
        double maxRadiusX = centerX > (DEFAULT_WIDTH / 2) ? (DEFAULT_WIDTH - centerX) : centerX;
        double maxRadiusY = centerY > (DEFAULT_HEIGHT / 2) ? (DEFAULT_HEIGHT - centerY) : centerY;
        double radius = Math.random() * (maxRadiusX > maxRadiusY ? maxRadiusY : maxRadiusX );

        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
        g2.draw(circle);
    }

    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

}

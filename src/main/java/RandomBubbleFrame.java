import javax.swing.*;
import java.awt.*;
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
 * A component that drawing a rectangle and a circle.
 */
class RandomBubbleComponent extends JComponent {
    private static final int DEFAULT_WIDTH = RandomBubbleFrame.DEFAULT_WIDTH;
    private static final int DEFAULT_HEIGHT = RandomBubbleFrame.DEFAULT_HEIGHT;

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        // draw a rectangle
        double leftX = 0;
        double topY = 0;
        double width = DEFAULT_WIDTH;
        double heigth = DEFAULT_HEIGHT;

        Rectangle2D rectangle2D = new Rectangle2D.Double(leftX, topY, width, heigth);
        g2.draw(rectangle2D);
    }

    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
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

        // drawRectangle2D(g2);

        for(int i = 0; i < 10; i++){
            // drawRandomCircle2D(g2);
        }

        //drawLine2D(g2, 100, 100, 300, 400);
        drawRegularPolygon(g2, 300, 300, 100, 5);
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

    public void drawLine2D(Graphics2D g2, double startX, double startY, double endX, double endY){
        // draw a line from start(X, Y) to end(X, Y)
        Line2D line2D = new Line2D.Double(startX, startY, endX, endY);
        g2.draw(line2D);
    }

    public void drawRegularPolygon(Graphics2D g2,
                                   double centerX, double centerY,
                                   double radius, int vertex){
        // draw a polygon that center(X, Y), radius and with vertex
        Point center = new Point();
        center.setLocation(centerX, centerY);
        Point[] vertexs = new Point[vertex];
        vertexs[0].setLocation(center.getX(), center.getY() + radius);

        for(int i = 1; i < vertexs.length; i++){
            vertexs[i] = nextPoint(center, vertexs[i - 1], Math.PI / vertex, radius);
        }

        for(int i = 0; i < vertex; i++){
            drawLine2D(g2,
                    vertexs[i].getX(), vertexs[i].getY(),
                    vertexs[i + 1].getX(), vertexs[i + 1].getY());
        }
    }

    public Point nextPoint(Point center, Point first, double arcFirstToSecond, double radius){
        // arc为弧度，在已知圆心、第一点、第一第二点的夹角的前提下，用radius和arc确定第二点的坐标
        Point second = new Point();
        double arcFirst = Math.asin((first.getY() - center.getY()) / radius);
        double arcSecond = arcFirst - arcFirstToSecond;
        double secondX = center.getX() + radius * Math.cos(arcSecond);
        double secondY = center.getY() + radius * Math.sin(arcSecond);
        second.setLocation(secondX, secondY);
        return second;
    }

    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

}

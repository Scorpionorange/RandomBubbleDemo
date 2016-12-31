import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by ScorpionOrange on 2016/12/09.
 * A frame that contains a panel with drawings.
 */
public class RandomBubbleFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 1920;
    public static final int DEFAULT_HEIGHT = 1080;

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
            //drawRandomCircle2D(g2);
        }

        // drawLine2D(g2, 100, 100, 300, 400);
        drawRegularPolygon(g2, 500, 500, 250, 8);
        drawRoundRect(g2, 600, 600, 400, 300, 50);
    }

    public void drawRectangle2D(Graphics2D g2){
        // draw a rectangle
        double leftX = 0;
        double topY = 0;
        double width = DEFAULT_WIDTH;
        double heigth = DEFAULT_HEIGHT;

        g2.draw(new Rectangle2D.Double(leftX, topY, width, heigth));
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
        g2.draw(new Line2D.Double(startX, startY, endX, endY));
    }

    public void drawRegularPolygon(Graphics2D g2,
                                   double centerX, double centerY,
                                   double radius, int vertex){
        // draw a polygon that center(X, Y), radius and with vertex
        Point center = new Point((int)centerX, (int)centerY);
        Point[] vertexs = new Point[vertex];
        vertexs[0] = new Point((int) center.x, (int)(center.y - radius));

        for(int i = 1; i < vertexs.length; i++){
            vertexs[i] = nextPoint(center, vertexs[0], Math.PI * 2 / vertex * i, radius);
            drawLine2D(g2, vertexs[i - 1].x, vertexs[i - 1].y, vertexs[i].x, vertexs[i].y);
        }
        drawLine2D(g2,
                vertexs[vertexs.length - 1].x, vertexs[vertexs.length - 1].y,
                vertexs[0].x, vertexs[0].y);
    }

    public Point nextPoint(Point center, Point first, double arcFirstToSecond, double radius){
        // arc为弧度，在已知圆心、第一点、第一第二点的夹角的前提下，用radius和arc确定第二点的坐标
        Point second = new Point();
        double arcFirst = Math.asin((first.y - center.y) / radius);
        double arcSecond = arcFirst + arcFirstToSecond;
        double secondX = center.x + radius * Math.cos(arcSecond);
        double secondY = center.y + radius * Math.sin(arcSecond);
        second.setLocation(secondX, secondY);
        return second;
    }

    public void drawRoundRect(Graphics2D g2, double topLeftX, double topLeftY,
                              int length, int width, double filletRadius){
        // 圆角矩形：左上角是(20，30)，宽是130，高是100，圆角的长轴是18，短轴是15。
        // RoundRectangle2D rectRound = new RoundRectangle2D.Double(20,30,130,100,18,15);
        g2.draw(new RoundRectangle2D.Double(
                topLeftX, topLeftY, length, width, filletRadius, filletRadius
        ));
    }

    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}

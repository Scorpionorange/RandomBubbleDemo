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
                                   double centerX, double centerY, double radius, int vertex){
        // draw a polygon that center(X, Y), radius and with vertex
        double[] vertexX = new double[vertex];
        double[] vertexY = new double[vertex];
        vertexX[0] = centerX;
        vertexY[0] = centerY - radius;
        for(int i = 1; i < vertex; i++){
            vertexX[i] = nextPoint((2 * Math.PI) / vertex * (i + 1),
                    vertexX[i - 1], vertexY[i - 1], radius).x;
            vertexY[i] = nextPoint((2 * Math.PI) / vertex * (i + 1),
                    vertexX[i - 1], vertexY[i - 1], radius).y;
        }
        for(int i = 0; i < vertex ; i++){
            if(i < vertex - 1){
                drawLine2D(g2, vertexX[i], vertexY[i], vertexX[i + 1], vertexY[i + 1]);
            }
            else {
                drawLine2D(g2, vertexX[i], vertexY[i], vertexX[0], vertexY[0]);
            }
        }
    }

    public Point nextPoint(double arc, double vertexX, double vertexY, double radius){
        // arc为弧度，在顶点出建立直角坐标系，用radius和arc确定下一个点的坐标
        Point point = new Point();
        point.x = (int) (vertexX - radius * Math.sin(arc));
        point.y = (int) (vertexY + radius - radius * Math.cos(arc));
        return point;
    }

    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

}

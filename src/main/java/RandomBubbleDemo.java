import javax.swing.*;
import java.awt.*;

/**
 * Created by ScorpionOrange on 2016/12/09.
 */
public class RandomBubbleDemo {
    public static void main(String[] args){
        EventQueue.invokeLater(() ->{
            RandomBubbleFrame frame = new RandomBubbleFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

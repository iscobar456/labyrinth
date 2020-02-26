import javax.swing.*;
import java.awt.*;

public class LabyrinthFrame {
    public LabyrinthFrame() {
        JFrame f = new JFrame();
        f.setMinimumSize(new Dimension(700, 500));
        f.setSize(new Dimension(1200, 900));
        Image icon = Toolkit.getDefaultToolkit().getImage("media\\maze.png");
        f.setIconImage(icon);
        f.setVisible(true);

    }
    public static void main(String args[]) {
        new LabyrinthFrame();
    }
}

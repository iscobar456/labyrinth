import javax.swing.*;
import java.awt.*;

public class LabyrinthFrame {
    public LabyrinthFrame() {
        JFrame f = new JFrame();

        f.setMinimumSize(new Dimension(700, 500));
        f.setSize(new Dimension(1200, 900));
        f.setMaximumSize(new Dimension(2400, 1500));
        Image icon = Toolkit.getDefaultToolkit().getImage("media\\maze.png");
        f.setIconImage(icon);
        f.setTitle("Labyrinth");

        JPanel mainContent = new JPanel();
        mainContent.setBackground(Color.decode("#bfdec7"));
        JLabel gameTitle = new JLabel("Labyrinth");
        gameTitle.setForeground(Color.WHITE);
        mainContent.add(gameTitle);

        JPanel gameGrid = new JPanel();

        for (int i = 0; i < 49; i++) {
            gameGrid.add(new GameTile("tee", "right"));
        }


        GridLayout gameLayout = new GridLayout(7, 7);
        gameGrid.setLayout(gameLayout);



//        f.add(mainContent);
        f.add(gameGrid);


        f.setVisible(true);



    }
    public static void main(String args[]) {
        new LabyrinthFrame();
    }
}

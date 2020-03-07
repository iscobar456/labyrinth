import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Collection;

public class LabyrinthFrame {
    public LabyrinthFrame() {
        JFrame f = new JFrame("Labyrinth");
        f.setMinimumSize(new Dimension(700, 500));
        f.setSize(new Dimension(1600, 1000));
        f.getContentPane().setBackground(Color.decode("#0b0c10"));
        f.setLayout(new GridBagLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("media\\maze.png");
        f.setIconImage(icon);

        JPanel gameGrid = new JPanel();

        gameGrid.setPreferredSize(new Dimension(800, 800));
        gameGrid.setSize(new Dimension(800, 800));
        gameGrid.setBackground(Color.decode("#1f2833"));

        GameTracker tracker = new GameTracker();
        for (Tile[] tRow : tracker.getGrid()) {
            for (Tile t : tRow) {
                if (t != null) {
                    gameGrid.add(new GameTile(t));
                } else {
                    gameGrid.add(new JPanel());
                }
            }
        }

        GridLayout gameLayout = new GridLayout(7, 7, 3, 3);
        gameGrid.setLayout(gameLayout);

        GridBagConstraints gameGridConstraints = new GridBagConstraints();
        gameGridConstraints.ipadx = 50;
        gameGridConstraints.ipady = 50;

        f.add(gameGrid, gameGridConstraints);

        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }
    public static void main(String args[]) {
        new LabyrinthFrame();
    }
}

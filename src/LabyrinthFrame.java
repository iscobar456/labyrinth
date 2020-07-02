import javax.swing.*;
import java.awt.*;

public class LabyrinthFrame {
    private JFrame f;
    private JPanel gameGrid;
    private GridBagConstraints gameGridConstraints;
    private GameTracker tracker;
    private CurrentTileDisplay currentTile;
    private DisplacedTileDisplay displacedTile;
    public LabyrinthFrame() {
        f = new JFrame("Labyrinth");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setMinimumSize(new Dimension(1100, 700));
        f.setSize(new Dimension(800, 800));
        f.getContentPane().setBackground(Color.decode("#0b0c10"));
        f.setLayout(new GridBagLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("media/maze.png");
        f.setIconImage(icon);

//        Instructions
        JLabel instructions = new JLabel("Click arrows to insert current tile. Use arrow keys to move character. Press the enter key to end turn.");
        GridBagConstraints instructionsGridConstraints = new GridBagConstraints();
        instructionsGridConstraints.gridy = 0;
        instructionsGridConstraints.gridwidth = 3;

//        Game Grid
        gameGrid = new JPanel();
        gameGrid.setLayout(new GridBagLayout());
        gameGrid.setBackground(Color.decode("#0b0c10"));
        gameGridConstraints = new GridBagConstraints();
        gameGridConstraints.gridx = 1;
        gameGridConstraints.gridy = 1;

//        Initializing Game Grid
        tracker = new GameTracker(this);
        renderGrid(false);

//        Creating and assigning arrows
        gameGrid.add(new InsertArrow('d', 1, this), new ArrowConstants(2, 0));
        gameGrid.add(new InsertArrow('d', 3, this), new ArrowConstants(4, 0));
        gameGrid.add(new InsertArrow('d', 5, this), new ArrowConstants(6, 0));
        gameGrid.add(new InsertArrow('l', 1, this), new ArrowConstants(8, 2));
        gameGrid.add(new InsertArrow('l', 3, this), new ArrowConstants(8, 4));
        gameGrid.add(new InsertArrow('l', 5, this), new ArrowConstants(8, 6));
        gameGrid.add(new InsertArrow('u', 1, this), new ArrowConstants(2, 8));
        gameGrid.add(new InsertArrow('u', 3, this), new ArrowConstants(4, 8));
        gameGrid.add(new InsertArrow('u', 5, this), new ArrowConstants(6, 8));
        gameGrid.add(new InsertArrow('r', 1, this), new ArrowConstants(0, 2));
        gameGrid.add(new InsertArrow('r', 3, this), new ArrowConstants(0, 4));
        gameGrid.add(new InsertArrow('r', 5, this), new ArrowConstants(0, 6));

//        Current Tile Container
        currentTile = new CurrentTileDisplay(tracker.getCurrentTile(), this);
        GridBagConstraints currentTileConstraints = new GridBagConstraints();
        currentTileConstraints.gridy = 1;

//        Displaced Tile Container
        displacedTile = new DisplacedTileDisplay(tracker.getDisplacedTile());
        GridBagConstraints displacedTileConstraints = new GridBagConstraints();
        displacedTileConstraints.gridy = 1;


        f.add(instructions, instructionsGridConstraints);
        f.add(currentTile, currentTileConstraints);
        f.add(gameGrid, gameGridConstraints);
        f.add(displacedTile, displacedTileConstraints);

        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }
    public static void main(String args[]) {
        new LabyrinthFrame();
    }

    public void renderGrid(boolean reRender) {
        GridBagConstraints newTileConsts = new GridBagConstraints();
        Tile[][] gridToRender = tracker.getGrid();
        for (int i = 0; i < gridToRender.length; i++) {
            Tile[] tRow = gridToRender[i];
            for (int j = 0; j < tRow.length; j++) {
                Tile t = tRow[j];
                newTileConsts.gridx = j + 1;
                newTileConsts.gridy = i + 1;
                if (reRender) {
                    gameGrid.remove(i * tRow.length + j);
                }
                if (t == tracker.getCurrentTile()) {
                    this.gameGrid.add(new GameTile(t, true), newTileConsts, i * tRow.length + j);
                } else {
                    this.gameGrid.add(new GameTile(t), newTileConsts, i * tRow.length + j);
                }
            }
        }
        if (tracker.getDisplacedTile() != null) {
            displacedTile.update(tracker.getDisplacedTile());
        }
        f.revalidate();
    }

    public GameTracker getTracker() {
        return tracker;
    }

    public void revalidate() {
        f.revalidate();
    }
}

import javax.swing.*;
import java.awt.*;

public class LabyrinthFrame extends JFrame {
    private JPanel gameGrid;
    private GridBagConstraints gameGridConstraints;
    private GameTracker tracker;
    private CurrentTileDisplay currentTile;
    private ScoreCounter scoreCounter;
    private JLabel playerTurn;
    private DisplacedTileDisplay displacedTile;
    public LabyrinthFrame() {
        setTitle("Labyrinth");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 700));
        setSize(new Dimension(800, 800));
        getContentPane().setBackground(Color.decode("#0b0c10"));
        setLayout(new GridBagLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("media/maze.png");
        setIconImage(icon);

//        Game Grid
        gameGrid = new JPanel();
        gameGrid.setLayout(new GridBagLayout());
        gameGrid.setBackground(Color.decode("#0b0c10"));
        gameGridConstraints = new GridBagConstraints();
        gameGridConstraints.gridx = 1;
        gameGridConstraints.gridy = 2;

//        Initializing Game Grid
        tracker = new GameTracker(this);
        renderGrid(false, false);

//        Player Score Count
        scoreCounter = new ScoreCounter(this);
        GridBagConstraints scConstraints = new GridBagConstraints();
        scConstraints.gridy = 0;
        scConstraints.gridx = 0;
        scConstraints.gridheight = 2;
        scConstraints.gridwidth = 1;

//        Instructions
        JLabel instructions = new JLabel("Click arrows to insert current tile. Use arrow keys to move character. Press the enter key to end turn.");
        instructions.setForeground(Color.WHITE);
        GridBagConstraints instructionsGridConstraints = new GridBagConstraints();
        instructionsGridConstraints.gridy = 0;
        instructionsGridConstraints.gridx = 1;
        instructionsGridConstraints.gridwidth = 2;

//        Player Turn
        playerTurn = new JLabel("Current Player: " + tracker.getCurrentPlayer().getPlayerName());
        playerTurn.setForeground(Color.WHITE);
        GridBagConstraints turnConstraints = new GridBagConstraints();
        turnConstraints.gridy = 1;
        turnConstraints.gridx = 1;
        turnConstraints.ipady = 20;
        turnConstraints.gridwidth = 2;

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
        currentTileConstraints.gridy = 2;

//        Displaced Tile Container
        displacedTile = new DisplacedTileDisplay(tracker.getDisplacedTile());
        GridBagConstraints displacedTileConstraints = new GridBagConstraints();
        displacedTileConstraints.gridy = 2;

//        Start Key Bindings
        InputMap im = gameGrid.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = gameGrid.getActionMap();
        KeyListeners.startListeners(tracker, am, im);

        add(instructions, instructionsGridConstraints);
        add(playerTurn, turnConstraints);
        add(scoreCounter, scConstraints);
        add(currentTile, currentTileConstraints);
        add(gameGrid, gameGridConstraints);
        add(displacedTile, displacedTileConstraints);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String args[]) {
        new LabyrinthFrame();
    }

    public void renderGrid(boolean reRender, boolean newTurn) {
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
        if (newTurn) {
            scoreCounter.setNewScores();
            playerTurn.setText("Current Player: " + tracker.getCurrentPlayer().getPlayerName());
            currentTile.setTrackerTile(tracker.getCurrentTile());
            displacedTile.update(tracker.getDisplacedTile());
        }
        revalidate();
    }

    public GameTracker getTracker() {
        return tracker;
    }
}

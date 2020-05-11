import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class TileDisplay extends JButton implements ActionListener {
    private GameTile gameTile;
    private Tile trackerTile;
    private JButton displayTile;
    TileDisplay(Tile t, String tileLabel) {
        this.trackerTile = t;
        this.gameTile = new GameTile(t);
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#0b0c10"));

//        Tile Display Label
        JLabel label = new JLabel(tileLabel);

        label.setForeground(Color.WHITE);

        GridBagConstraints lc = new GridBagConstraints();
        lc.gridx = 0;
        lc.gridy = 0;

        this.add(label, lc);


//        Tile Display Tile
        displayTile = new JButton();

        displayTile.setPreferredSize(new Dimension(125, 125));
        displayTile.setBorderPainted(false);
        displayTile.setFocusPainted(false);
        displayTile.setContentAreaFilled(false);
        displayTile.setMargin(new Insets(0,0,0,0));
        displayTile.add(this.gameTile);
        displayTile.addActionListener(this);

        GridBagConstraints tc = new GridBagConstraints();
        tc.gridx = 0;
        tc.gridy = 1;

        this.add(displayTile, tc);


//        Tile Display Hint
        JLabel tileHint = new JLabel("Click to rotate");

        tileHint.setForeground(Color.WHITE);

        GridBagConstraints thc = new GridBagConstraints();
        thc.gridx = 0;
        thc.gridy = 2;
        thc.anchor = GridBagConstraints.PAGE_END;

        this.add(tileHint, thc);
    }

    public GameTile getGameTile() {
        return gameTile;
    }

    public Tile getTrackerTile() {
        return trackerTile;
    }

    public void rotateGameTile(LabyrinthFrame frame) {
        Tile newTile = new Tile(
                this.trackerTile.getTileType(),
                this.trackerTile.getTileOrientation() + 1 > 3 ? 0 : this.trackerTile.getTileOrientation() + 1,
                this.trackerTile.getPlayerOnTile()
        );
        this.trackerTile = newTile;
        displayTile.remove(gameTile);
        this.gameTile = new GameTile(this.trackerTile);
        displayTile.add(gameTile);
        frame.revalidate();
    }

}

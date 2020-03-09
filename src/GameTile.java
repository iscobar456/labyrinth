import sun.security.util.ArrayUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GameTile extends JPanel {
    private Tile tile;
    public GameTile(Tile t) {
        tile = t;
        constructTile();
    }

    private void constructTile() {
        this.setBackground(Color.decode("#7c7646"));
        this.setPreferredSize(new Dimension(100, 100));
        this.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JPanel path = new JPanel();
            path.setBackground(Color.decode("#7c7646"));
            if (i == 4) {
                path.setBackground(Color.decode("#bab99d"));
                if (tile.getPlayerOnTile() != null) {
                    path.setLayout(new GridBagLayout());
                    JPanel playerPiece = new JPanel();
                    playerPiece.setBackground(tile.getPlayerOnTile().getPlayerColor());
                    GridBagConstraints playerPieceConstraints = new GridBagConstraints();
                    playerPieceConstraints.ipady = 5;
                    playerPieceConstraints.ipadx = 5;
                    path.add(playerPiece, playerPieceConstraints);
                }
            } else if (i == 1 && Arrays.stream(tile.getOutlets()).anyMatch(o -> o == TileConfiguration.UP)) {
                path.setBackground(Color.decode("#bab99d"));
            } else if (i == 3 && Arrays.stream(tile.getOutlets()).anyMatch(o -> o == TileConfiguration.LEFT)) {
                path.setBackground(Color.decode("#bab99d"));
            } else if (i == 5 && Arrays.stream(tile.getOutlets()).anyMatch(o -> o == TileConfiguration.RIGHT)) {
                path.setBackground(Color.decode("#bab99d"));
            } else if (i == 7 && Arrays.stream(tile.getOutlets()).anyMatch(o -> o == TileConfiguration.DOWN)) {
                path.setBackground(Color.decode("#bab99d"));
            }
            this.add(path);
        }
    }

}

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GameTile extends JPanel {
    private Tile tile;
    private boolean isCurrentTile = false;
    public GameTile(Tile t) {
        tile = t;
        if (tile == null) {
            constructNullTile();
        } else {
            constructTile();
        }
    }

    public GameTile(Tile t, boolean ct) {
        tile = t;
        isCurrentTile = ct;
        if (tile == null) {
            constructNullTile();
        } else {
            constructTile();
        }
    }

    private void constructTile() {
        setBackground(Color.decode("#7c7646"));
        if (isCurrentTile) {
            setBorder(BorderFactory.createBevelBorder(0));
        } else {
            setBorder(BorderFactory.createEtchedBorder());
        }
        setPreferredSize(new Dimension(70, 70));
        setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JPanel path = new JPanel();
            FlowLayout pathLayout = new FlowLayout();
            pathLayout.setVgap(0);
            path.setLayout(pathLayout);
            path.setBackground(Color.decode("#7c7646"));
            if (i == 0 && tile.getTreasure() != null) {
                path.add(new JLabel(tile.getTreasure().getIcon()));
            } else if (i == 4) {
                path.setBackground(Color.decode("#bab99d"));
                if (tile.getPlayersOnTile() != null) {
                    path.setLayout(new GridBagLayout());
                    JPanel playerPiece = new JPanel();
                    playerPiece.setBackground(Color.decode("#bab99d"));
                    // Next 20 lines divides up the player piece based on how many players share the same tile
                    playerPiece.setLayout(new GridLayout(2, 2));
                    for (int j = 0; j < 4; j++) {
                        JPanel playerSubPiece = new JPanel();
                        playerSubPiece.setPreferredSize(new Dimension(6,6));
                        if (tile.getPlayersOnTile().length == 1) {
                            playerSubPiece.setBackground(tile.getPlayersOnTile()[0].getPlayerColor());
                        } else if (tile.getPlayersOnTile().length == 2) {
                            if (j % 2 == 0) {
                                playerSubPiece.setBackground(tile.getPlayersOnTile()[0].getPlayerColor());
                            } else {
                                playerSubPiece.setBackground(tile.getPlayersOnTile()[1].getPlayerColor());
                            }
                        } else if (tile.getPlayersOnTile().length == 3) {
                            if (j != 3) {
                                playerSubPiece.setBackground(tile.getPlayersOnTile()[j].getPlayerColor());
                            } else {
                                playerSubPiece.setBackground(Color.decode("#bab99d"));
                            }
                        } else {
                            playerSubPiece.setBackground(tile.getPlayersOnTile()[j].getPlayerColor());
                        }

                        playerPiece.add(playerSubPiece);
                    }


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
            add(path);
        }
    }
    private void constructNullTile() {
        setBackground(Color.decode("#0b0c10"));
    }
}

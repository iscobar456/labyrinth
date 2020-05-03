import java.awt.*;
import java.util.ArrayList;

public class GameTracker {
    private Tile[][] grid;
    private Tile currentTile;
    public GameTracker() {
        int c = TileConfiguration.CORNER;
        int s = TileConfiguration.STRAIGHT;
        int t = TileConfiguration.TEE;
        int u = TileConfiguration.UP;
        int r = TileConfiguration.RIGHT;
        int d = TileConfiguration.DOWN;
        int l = TileConfiguration.LEFT;

        Player player1 = new Player("Player 1", Color.red);
        Player player2 = new Player("Player 2", Color.blue);
        Player player3 = new Player("Player 3", Color.green);
        Player player4 = new Player("Player 4", Color.yellow);

        grid = new Tile[][] {
                {new Tile(c, r, player1), null, new Tile(t, r), null, new Tile(t, r), null, new Tile(c, d, player2)},
                {null,                    null, null,           null, null,           null, null},
                {new Tile(t, u),          null, new Tile(t, u), null, new Tile(t, r), null, new Tile(t, d)},
                {null,                    null, null,           null, null,           null, null},
                {new Tile(t, u),          null, new Tile(t, l), null, new Tile(t, d), null, new Tile(t, d)},
                {null,                    null, null,           null, null,           null, null},
                {new Tile(c, u, player4), null, new Tile(t, r), null, new Tile(t, r), null, new Tile(c, l, player3)},
        };

        ArrayList randomTiles = new ArrayList<Tile>();

        for (int i = 0; i < 12; i++) {
            int orientation = (int)(Math.random() * 4);
            randomTiles.add(new Tile(TileConfiguration.STRAIGHT, orientation));
        }

        for (int i = 0; i < 16; i++) {
            int orientation = (int)(Math.random() * 4);
            randomTiles.add(new Tile(TileConfiguration.CORNER, orientation));
        }

        for (int i = 0; i < 6; i++) {
            int orientation = (int)(Math.random() * 4);
            randomTiles.add(new Tile(TileConfiguration.TEE, orientation));
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    int tileIndex = (int) (Math.random() * randomTiles.size());
                    Tile randomTile = (Tile) randomTiles.remove(tileIndex);
                    grid[i][j] = randomTile;
                }
            }
        }
        currentTile = (Tile) randomTiles.remove(0);
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }
}
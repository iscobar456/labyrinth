import java.awt.*;

public class GameTracker {
    private Tile[][] grid;
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
    }

    public Tile[][] getGrid() {
        return grid;
    }
}

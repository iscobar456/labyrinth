import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class GameTracker {
    private Tile[][] grid;
    private Tile[][] intermediaryGrid = null;
    private Tile currentTile;
    private Tile displacedTile = null;
    private ArrayList<Player>players;
    private Player currentPlayer;
    private LabyrinthFrame gameFrame;
//    private
    public GameTracker(LabyrinthFrame frame) {
        gameFrame = frame;

        int c = TileConfiguration.CORNER;
        int s = TileConfiguration.STRAIGHT;
        int t = TileConfiguration.TEE;
        int u = TileConfiguration.UP;
        int r = TileConfiguration.RIGHT;
        int d = TileConfiguration.DOWN;
        int l = TileConfiguration.LEFT;

//        Setting up players
        Player player1 = new Player("Player Red", Color.red, true);
        Player player2 = new Player("Player Blue", Color.blue);
        Player player3 = new Player("Player Green", Color.green);
        Player player4 = new Player("Player Yellow", Color.yellow);
        currentPlayer = player1;
        players = new ArrayList<Player>();
        players.add(player1); players.add(player2); players.add(player3); players.add(player4);

        grid = new Tile[][] {
                {new Tile(c, r, player1), null, new Tile(t, r), null, new Tile(t, r), null, new Tile(c, d, player2)},
                {null,                    null, null,           null, null,           null, null},
                {new Tile(t, u),          null, new Tile(t, u), null, new Tile(t, r), null, new Tile(t, d)},
                {null,                    null, null,           null, null,           null, null},
                {new Tile(t, u),          null, new Tile(t, l), null, new Tile(t, d), null, new Tile(t, d)},
                {null,                    null, null,           null, null,           null, null},
                {new Tile(c, u, player4), null, new Tile(t, r), null, new Tile(t, r), null, new Tile(c, l, player3)},
        };
//        player1.tile =

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

    public void shiftStack(ShiftStackConfig config) {
//        Cloning grid for intermediary grid
        ArrayList<Tile[]>intermediaryGridList = new ArrayList<>();
        for (Tile[] tRow : grid) {
            ArrayList<Tile> newRow = new ArrayList<>();
            for (Tile tile : tRow) {
                newRow.add((Tile) tile.clone());
            }
            intermediaryGridList.add(newRow.toArray(new Tile[newRow.size()]));
        }
        intermediaryGrid = intermediaryGridList.toArray(new Tile[intermediaryGridList.size()][intermediaryGridList.get(0).length]);


        Tile store1 = currentTile;
        Tile store2 = null;
        if (config.horizontal) {
            if (config.upShift) {
                for (int i = 0; i <= intermediaryGrid.length - 1; i++) {
                    store2 = store1;
                    store1 = intermediaryGrid[config.position][i];
                    intermediaryGrid[config.position][i] = store2;
                }
            } else {
                for (int i = intermediaryGrid.length - 1; i >= 0; i--) {
                    store2 = store1;
                    store1 = intermediaryGrid[config.position][i];
                    intermediaryGrid[config.position][i] = store2;
                }
            }
        } else {
            if (config.upShift) {
                for (int i = 0; i <= intermediaryGrid[0].length - 1; i++) {
                    store2 = store1;
                    store1 = intermediaryGrid[i][config.position];
                    intermediaryGrid[i][config.position] = store2;
                }
            } else {
                for (int i = intermediaryGrid[0].length - 1; i >= 0; i--) {
                    store2 = store1;
                    store1 = intermediaryGrid[i][config.position];
                    intermediaryGrid[i][config.position] = store2;
                }
            }
        }
        displacedTile = store1;
        gameFrame.renderGrid(true, false);
    }

    public Tile[][] getGrid() {
        if (intermediaryGrid != null) {
            return intermediaryGrid;
        } else {
            return grid;
        }
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Tile getDisplacedTile() {
        return displacedTile;
    }

    public void updateGrid() {
        grid = intermediaryGrid;
        currentTile = displacedTile;
        displacedTile = null;
        intermediaryGrid = null;
        gameFrame.renderGrid(true, true);
        this.currentPlayer.setHasMovedGrid(true);
        System.out.println("moving grid");
    }

    public void endTurn() {
        System.out.println("ending turn");
//        updateGrid();
        currentPlayer.setOnTurn(false);
        Player nextPlayer = players.get(players.indexOf(currentPlayer) + 1 > 3 ? 0 : players.indexOf(currentPlayer) + 1);
        nextPlayer.setOnTurn(true);
        currentPlayer = nextPlayer;
    }

    public void movePlayer(String direction) {
//        If the current player hasn't moved the grid, don't let them move
        if (!this.currentPlayer.hasMovedGrid()){
            System.out.println(this.currentPlayer.hasMovedGrid());
            return;
        }
//        System.out.println(direction);

//        Find the tile that the current player is on
        int[] currentPlayerTileLocation = null;
        Tile currentPlayerTile = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Tile tile = grid[i][j];
                if (tile.getPlayerOnTile() == this.currentPlayer) {
                    currentPlayerTileLocation = new int[] {i, j};
                    currentPlayerTile = tile;
                }
            }
        }
//        System.out.println(currentPlayerTile + " \\ " + Arrays.toString(currentPlayerTileLocation));

//        Moving the player
        if (direction.equalsIgnoreCase("UP")) {
//            If the tile has an upwards -> (0) outlet and it is not on the top row, continue program execution.
            if (currentPlayerTileLocation[0] - 1 >= 0 && IntStream.of(currentPlayerTile.getOutlets()).anyMatch(n -> n == 0)) {
//                Get the tile above the player's current tile
                Tile nextPlayerTile = grid[currentPlayerTileLocation[0] - 1][currentPlayerTileLocation[1]];

//                If the tile above has a downwards outlet, move the player onto it.
                if (IntStream.of(nextPlayerTile.getOutlets()).anyMatch(n -> n == 2)) {
                    nextPlayerTile.setPlayerOnTile(currentPlayer);
                    currentPlayerTile.setPlayerOnTile(null);
                    gameFrame.renderGrid(true, false);
                }
            }
        } else if (direction.equalsIgnoreCase("RIGHT")) {
            if (currentPlayerTileLocation[1] + 1 <= 6 && IntStream.of(currentPlayerTile.getOutlets()).anyMatch(n -> n == 1)) {
                Tile nextPlayerTile = grid[currentPlayerTileLocation[0]][currentPlayerTileLocation[1] + 1];
                if (IntStream.of(nextPlayerTile.getOutlets()).anyMatch(n -> n == 3)) {
                    nextPlayerTile.setPlayerOnTile(currentPlayer);
                    currentPlayerTile.setPlayerOnTile(null);
                    gameFrame.renderGrid(true, false);
                }
            }
        } else if (direction.equalsIgnoreCase("DOWN")) {
            if (currentPlayerTileLocation[0] + 1 <= 6 && IntStream.of(currentPlayerTile.getOutlets()).anyMatch(n -> n == 2)) {
                Tile nextPlayerTile = grid[currentPlayerTileLocation[0] + 1][currentPlayerTileLocation[1]];
                if (IntStream.of(nextPlayerTile.getOutlets()).anyMatch(n -> n == 0)) {
                    nextPlayerTile.setPlayerOnTile(currentPlayer);
                    currentPlayerTile.setPlayerOnTile(null);
                    gameFrame.renderGrid(true, false);
                }
            }
        } else if (direction.equalsIgnoreCase("LEFT")) {
            if (currentPlayerTileLocation[1] - 1 >= 0 && IntStream.of(currentPlayerTile.getOutlets()).anyMatch(n -> n == 3)) {
                Tile nextPlayerTile = grid[currentPlayerTileLocation[0]][currentPlayerTileLocation[1] - 1];
                if (IntStream.of(nextPlayerTile.getOutlets()).anyMatch(n -> n == 1)) {
                    nextPlayerTile.setPlayerOnTile(currentPlayer);
                    currentPlayerTile.setPlayerOnTile(null);
                    gameFrame.renderGrid(true, false);
                }
            }
        }
    }
}

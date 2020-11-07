import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameTracker {
    private Tile[][] grid;
    private Tile[][] intermediaryGrid = null;
    private Tile currentTile;
    private Tile displacedTile = null;
    private ArrayList<Player>players;
    private Player currentPlayer;
    private LabyrinthFrame gameFrame;
    private ArrayList<Treasure>treasures = new ArrayList<>();

//    Get Random Treasure
    private Treasure grt() {
        return treasures.remove(
            Math.round((float)Math.random() * (float)treasures.size())
        );
    }

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

//        Creating Treasure instances
        treasures.add(new Treasure("Book",           "book.png"));
        treasures.add(new Treasure("Candle",         "candle.png"));
        treasures.add(new Treasure("Crown",          "crown.png"));
        treasures.add(new Treasure("Diamond",        "diamond.png"));
        treasures.add(new Treasure("Helmet",         "helmet.png"));
        treasures.add(new Treasure("Money",          "money-bag.png"));
        treasures.add(new Treasure("Key",            "old-key.png"));
        treasures.add(new Treasure("Ring",           "ring.png"));
        treasures.add(new Treasure("Skull",          "skull.png"));
        treasures.add(new Treasure("Sword",          "sword.png"));
        treasures.add(new Treasure("Treasure Chest", "treasure-chest.png"));
        treasures.add(new Treasure("Map",            "treasure-map.png"));

//        Assigning players their treasures
        ArrayList<Integer>treasuresNotTaken = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            treasuresNotTaken.add(i);
        }

        for (Player player : players) {
            for (int i = 0; i < 3; i++) {
                player.addTreasure(
                    treasures.get(
                        treasuresNotTaken.remove((int) (Math.random() * treasuresNotTaken.size()))
                    )
                );
            }
        }
//        Creating Tile grid
        grid = new Tile[][] {
                {new Tile(c, r, new Player[] {player1}),  null, new Tile(t, r, treasures.get(0)),  null, new Tile(t, r, treasures.get(1)),  null, new Tile(c, d, new Player[] {player2})},
                {null,                                    null, null,                              null, null,                              null, null                                  },
                {new Tile(t, u, treasures.get(2)),        null, new Tile(t, u, treasures.get(3)),  null, new Tile(t, r, treasures.get(4)),  null, new Tile(t, d, treasures.get(5))      },
                {null,                                    null, null,                              null, null,                              null, null                                  },
                {new Tile(t, u, treasures.get(6)),        null, new Tile(t, l, treasures.get(7)),  null, new Tile(t, d, treasures.get(8)),  null, new Tile(t, d, treasures.get(9))      },
                {null,                                    null, null,                              null, null,                              null, null                                  },
                {new Tile(c, u, new Player[] {player4}),  null, new Tile(t, l, treasures.get(10)), null, new Tile(t, l, treasures.get(11)), null, new Tile(c, l, new Player[] {player3})},
        };

        ArrayList randomTiles = new ArrayList<Tile>();
//        Creating new random/movable tiles and assigning starting orientation.
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
//        If player was transported across the grid in the previous stack shift,
//        remove it from current tile before shifting again.
        if (currentPlayer.hasMovedGrid()) {
            return;
        }
        if (currentTile.getPlayersOnTile() != null) {
            currentTile.setPlayersOnTile(null);
        }

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

        if (displacedTile.getPlayersOnTile() != null) {
            currentTile.setPlayersOnTile(displacedTile.getPlayersOnTile());
            displacedTile.setPlayersOnTile(null);
        }

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

    public ArrayList<Player> getPlayers() {
        return players;
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
    }

    public void endTurn() {
        currentPlayer.setOnTurn(false);
        Player nextPlayer = players.get(players.indexOf(currentPlayer) + 1 > 3 ? 0 : players.indexOf(currentPlayer) + 1);
        nextPlayer.setOnTurn(true);
        currentPlayer = nextPlayer;
        gameFrame.renderGrid(true, true);
    }

    public void endGame() {
        String winnerName = null;
        for (Player player : players) {
            if (player.getPlayerScore() == 3) {
                winnerName = player.getPlayerName();
            }
        }
        if (winnerName == null) {
            winnerName = "No one";
        }
        JPanel endGameButtons = new JPanel();
        String endGameMessage = winnerName + " won! This game will now close.";
        JOptionPane.showMessageDialog(gameFrame, endGameMessage, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void swapPlayerPositions(Tile currentPlayerTile, Tile nextPlayerTile) {
//            If the next tile has players on it, concatenate the player onto the player list
        if (nextPlayerTile.getPlayersOnTile() != null) {
            nextPlayerTile.setPlayersOnTile(
                    Stream.concat(
                            Arrays.stream(new Player[] {currentPlayer}),
                            Arrays.stream(nextPlayerTile.getPlayersOnTile())
                    ).toArray(Player[]::new)
            );
        } else {
            nextPlayerTile.setPlayersOnTile(new Player[] {currentPlayer});
        }
//        If the current tile has more than one player, remove the current player, otherwise set null.
        if (currentPlayerTile.getPlayersOnTile().length > 1) {
            Player[] newPlayerList = new Player[currentPlayerTile.getPlayersOnTile().length - 1];
            for (int i = 0, k = 0; i < currentPlayerTile.getPlayersOnTile().length; i++) {
                if (currentPlayerTile.getPlayersOnTile()[i] != currentPlayer) {
                    newPlayerList[k] = currentPlayerTile.getPlayersOnTile()[i];
                    k++;
                }
            }
            currentPlayerTile.setPlayersOnTile(newPlayerList);
        } else {
            currentPlayerTile.setPlayersOnTile(null);
        }

//        If the player has moved onto their current treasure tile, end their turn and add a point to their score.
        if (nextPlayerTile.getTreasure() == currentPlayer.getCurrentTreasure()) {
            currentPlayer.reachedCurrentTreasure();
            if (currentPlayer.isTreasuresEmpty()) {
                endGame();
            } else {
                endTurn();
            }
        }
    }

    public void movePlayer(String direction) {
//        If the current player hasn't moved the grid, don't let them move
        if (!this.currentPlayer.hasMovedGrid()){
            System.out.println(this.currentPlayer.hasMovedGrid());
            return;
        }

//        Find the tile that the current player is on
        int[] currentPlayerTileLocation = null;
        Tile currentPlayerTile = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Tile tile = grid[i][j];
                if (tile.getPlayersOnTile() != null) {
                    int indexOfPlayer = -1;
                    for (int k = 0; k < tile.getPlayersOnTile().length; k++) {
                        if (tile.getPlayersOnTile()[k] == this.currentPlayer) {
                            indexOfPlayer = k;
                            break;
                        }
                    }
                    if (indexOfPlayer != -1) {
                        currentPlayerTileLocation = new int[] {i, j};
                        currentPlayerTile = tile;
                        break;
                    }
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
                    swapPlayerPositions(currentPlayerTile, nextPlayerTile);
                    gameFrame.renderGrid(true, false);
                }
            }
        } else if (direction.equalsIgnoreCase("RIGHT")) {
            if (currentPlayerTileLocation[1] + 1 <= 6 && IntStream.of(currentPlayerTile.getOutlets()).anyMatch(n -> n == 1)) {
                Tile nextPlayerTile = grid[currentPlayerTileLocation[0]][currentPlayerTileLocation[1] + 1];
                if (IntStream.of(nextPlayerTile.getOutlets()).anyMatch(n -> n == 3)) {
                    swapPlayerPositions(currentPlayerTile, nextPlayerTile);
                    gameFrame.renderGrid(true, false);
                }
            }
        } else if (direction.equalsIgnoreCase("DOWN")) {
            if (currentPlayerTileLocation[0] + 1 <= 6 && IntStream.of(currentPlayerTile.getOutlets()).anyMatch(n -> n == 2)) {
                Tile nextPlayerTile = grid[currentPlayerTileLocation[0] + 1][currentPlayerTileLocation[1]];
                if (IntStream.of(nextPlayerTile.getOutlets()).anyMatch(n -> n == 0)) {
                    swapPlayerPositions(currentPlayerTile, nextPlayerTile);
                    gameFrame.renderGrid(true, false);
                }
            }
        } else if (direction.equalsIgnoreCase("LEFT")) {
            if (currentPlayerTileLocation[1] - 1 >= 0 && IntStream.of(currentPlayerTile.getOutlets()).anyMatch(n -> n == 3)) {
                Tile nextPlayerTile = grid[currentPlayerTileLocation[0]][currentPlayerTileLocation[1] - 1];
                if (IntStream.of(nextPlayerTile.getOutlets()).anyMatch(n -> n == 1)) {
                    swapPlayerPositions(currentPlayerTile, nextPlayerTile);
                    gameFrame.renderGrid(true, false);
                }
            }
        }
    }
}

import java.awt.Color;

public class Player implements Comparable{
    private int id;
    private String playerName;
    private boolean onTurn = false;
    private boolean hasInsertedTile = false;
    private boolean hasMovedGrid = false;
    private Color playerColor;
    private int playerScore = 0;

    public Player(String name, Color color) {
        playerName = name;
        playerColor = color;
    }

    public Player(String name, Color color, boolean starting) {
        playerName = name;
        playerColor = color;
        onTurn = starting;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public boolean isOnTurn() {
        return onTurn;
    }

    public void setOnTurn(boolean onTurn) {
        this.onTurn = onTurn;
        this.setHasMovedGrid(false);
    }

    public boolean hasMovedGrid() {
        return hasMovedGrid;
    }

    public void setHasMovedGrid(boolean hasMovedGrid) {
        this.hasMovedGrid = hasMovedGrid;
    }

    public boolean hasInsertedTile() {
        return hasInsertedTile;
    }

    public void setHasInsertedTile(boolean hasInsertedTile) {
        this.hasInsertedTile = hasInsertedTile;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

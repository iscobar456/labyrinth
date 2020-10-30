import java.awt.Color;
import java.util.ArrayList;

public class Player implements Comparable{
    private int id;
    private String playerName;
    private boolean onTurn = false;
    private boolean hasInsertedTile = false;
    private boolean hasMovedGrid = false;
    private Color playerColor;
    private int playerScore = 0;
    private ArrayList<Treasure>treasures = new ArrayList<>();

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

    public void addTreasure(Treasure treasure) {
        this.treasures.add(treasure);
    }

    public Treasure getCurrentTreasure() {
        return treasures.get(0);
    }

    public void reachedCurrentTreasure() {
        Treasure currentTreasure = treasures.get(0);
        currentTreasure.setIsCollected(true);
        treasures.remove(currentTreasure);
        playerScore += 1;
    }

    public boolean isTreasuresEmpty() {
        if (treasures.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

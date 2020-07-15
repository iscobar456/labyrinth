import java.awt.Color;

public class Player {
    private int id;
    private String playerName;
    private boolean onTurn = false;
    private boolean hasMovedGrid = false;
    public Color playerColor;

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
}

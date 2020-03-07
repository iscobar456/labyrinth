import java.awt.Color;

public class Player {
    private int id;
    private String playerName;
    public Color playerColor;

    public Player(String name, Color color) {
        playerName = name;
        playerColor = color;
    }

    public Color getPlayerColor() {
        return playerColor;
    }
}

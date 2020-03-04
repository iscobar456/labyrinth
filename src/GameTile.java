import javax.swing.*;
import java.awt.*;

public class GameTile extends JPanel {
    private String tileType;
    private String tileOrientation;
    private boolean tileHasPlayer;
    public GameTile(String type, String orientation) {
        if (type.equalsIgnoreCase("TEE") || type.equalsIgnoreCase("CORNER") || type.equalsIgnoreCase("STRAIGHT")) {
            tileType = type.toUpperCase();
        } else {
            throw new ExceptionInInitializerError();
        }

        if (orientation.equalsIgnoreCase("RIGHT") || orientation.equalsIgnoreCase("LEFT") || orientation.equalsIgnoreCase("UP") || orientation.equalsIgnoreCase("DOWN")) {
            tileOrientation = orientation.toUpperCase();
        } else {
            throw new ExceptionInInitializerError();
        }
    }

    public GameTile(String type, String orientation, boolean hasPlayer) {
        if (type.equalsIgnoreCase("TEE") || type.equalsIgnoreCase("CORNER") || type.equalsIgnoreCase("STRAIGHT")) {
            tileType = type.toUpperCase();
        } else {
            throw new ExceptionInInitializerError();
        }

        if (orientation.equalsIgnoreCase("RIGHT") || orientation.equalsIgnoreCase("LEFT") || orientation.equalsIgnoreCase("UP") || orientation.equalsIgnoreCase("DOWN")) {
            tileOrientation = orientation.toUpperCase();
        } else {
            throw new ExceptionInInitializerError();
        }

        tileHasPlayer = hasPlayer;

        constructTile();
    }

    private void constructTile() {
        super.setBackground(Color.blue);
        super.setSize(30, 30);
    }

}

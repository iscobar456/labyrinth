import java.util.InputMismatchException;

public class Tile {
    private Tile upperTile;
    private Tile lowerTile;
    private Tile leftTile;
    private Tile rightTile;
    private int tileOrientation;
    private int tileType;
    private int[] outlets;
    private Player playerOnTile = null;

    public Tile(int type, int orientation, Player player) {
        tileType = type;
        tileOrientation = orientation;
        setOutlets(type, orientation);
        playerOnTile = player;
    }

    public Tile(int type, int orientation) {
        tileType = type;
        tileOrientation = orientation;
        setOutlets(type, orientation);
    }

    private void setOutlets(int type, int orientation) {
        switch (type) {
            case TileConfiguration.STRAIGHT:
                outlets = new int[] {
                        ((TileConfiguration.UP + orientation) % 4),
                        ((TileConfiguration.DOWN + orientation) % 4)
                };
                break;
            case TileConfiguration.CORNER:
                outlets = new int[] {
                        ((TileConfiguration.UP + orientation) % 4),
                        ((TileConfiguration.RIGHT + orientation) % 4)
                };
                break;
            case TileConfiguration.TEE:
                outlets = new int[] {
                        ((TileConfiguration.UP + orientation) % 4),
                        ((TileConfiguration.RIGHT + orientation) % 4),
                        ((TileConfiguration.DOWN + orientation) % 4)
                };
                break;
            default:
                throw new Error("Invalid Tile Type");
        }
    }

    public int[] getOutlets() {
        return outlets;
    }

    public Player getPlayerOnTile() {
        return playerOnTile;
    }
}

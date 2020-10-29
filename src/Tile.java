import java.util.Arrays;
import java.util.InputMismatchException;

public class Tile {
    private int tileOrientation;
    private int tileType;
    private int[] outlets;
    private Player[] playersOnTile = null;

    public Tile(int type, int orientation, Player[] players) {
        tileType = type;
        tileOrientation = orientation;
        setOutlets(type, orientation);
        playersOnTile = players;
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

    @Override
    public String toString() {
        return "{" +
                "tileOrientation=" + tileOrientation +
                ", tileType=" + tileType +
                '}';
    }

    @Override
    public Object clone() {
        Tile newTile = new Tile(this.tileType, this.tileOrientation, this.playersOnTile);
        newTile.outlets = Arrays.copyOf(this.outlets, this.outlets.length);
        return newTile;
    }

    public int[] getOutlets() {
        return outlets;
    }

    public int getTileType() {
        return tileType;
    }

    public int getTileOrientation() {
        return tileOrientation;
    }

    public Player[] getPlayersOnTile() {
        if (playersOnTile != null) {
            Arrays.sort(playersOnTile);
        }
        return playersOnTile;
    }

    public void setPlayersOnTile(Player[] playersOnTile) {
        this.playersOnTile = playersOnTile;
    }
}

import java.awt.image.TileObserver;
import java.util.LinkedList;

public abstract class Stack {
    public LinkedList tiles = new LinkedList<Tile>();
    public Stack() {

    }
    public void addTile(Tile t) {
        tiles.add(t);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrentTileDisplay extends TileDisplay {
    private LabyrinthFrame frame;
    CurrentTileDisplay(Tile gameTile, LabyrinthFrame labFrame) {
        super(gameTile, "Current Tile");
        frame = labFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.rotateGameTile(frame);
    }
}

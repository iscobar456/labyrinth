import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class DisplacedTileDisplay extends JPanel {
    private JButton tile = new JButton();
    DisplacedTileDisplay(Tile displacedTile) {
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#0b0c10"));

//        Tile Display Label
        JLabel label = new JLabel("Displaced Tile");
        label.setForeground(Color.WHITE);

        GridBagConstraints lc = new GridBagConstraints();
        lc.gridx = 0;
        lc.gridy = 0;

        add(label, lc);


//        Display Tile
        tile.setPreferredSize(new Dimension(125, 125));
        tile.setBorderPainted(false);
        tile.setFocusPainted(false);
        tile.setContentAreaFilled(false);
        tile.setMargin(new Insets(0,0,0,0));
        tile.add(new GameTile(displacedTile));

        GridBagConstraints tc = new GridBagConstraints();
        tc.gridx = 0;
        tc.gridy = 1;

        add(tile, tc);
    }

    public void update(Tile t) {
        tile.removeAll();
        tile.add(new GameTile(t));
    }
}

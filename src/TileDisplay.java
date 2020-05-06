import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class TileDisplay extends JButton implements ActionListener {
    TileDisplay(Tile gameTile, String tileLabel) {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#0b0c10"));

//        Tile Display Label
        JLabel label = new JLabel(tileLabel);

        label.setForeground(Color.WHITE);

        GridBagConstraints lc = new GridBagConstraints();
        lc.gridx = 0;
        lc.gridy = 0;

        this.add(label, lc);


//        Tile Display Tile
        JButton tile = new JButton();

        tile.setPreferredSize(new Dimension(125, 125));
        tile.setBorderPainted(false);
        tile.setFocusPainted(false);
        tile.setContentAreaFilled(false);
        tile.setMargin(new Insets(0,0,0,0));
        tile.add(new GameTile(gameTile));
        tile.addActionListener(this);

        GridBagConstraints tc = new GridBagConstraints();
        tc.gridx = 0;
        tc.gridy = 1;

        this.add(tile, tc);


//        Tile Display Hint
        JLabel tileHint = new JLabel("Click to rotate");

        tileHint.setForeground(Color.WHITE);

        GridBagConstraints thc = new GridBagConstraints();
        thc.gridx = 0;
        thc.gridy = 2;
        thc.anchor = GridBagConstraints.PAGE_END;

        this.add(tileHint, thc);
    }
}

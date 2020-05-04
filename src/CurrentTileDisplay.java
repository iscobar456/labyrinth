import javax.swing.*;
import java.awt.*;

public class CurrentTileDisplay extends JPanel{
    CurrentTileDisplay(Tile current) {
//        Current Tile Container
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#0b0c10"));

//        Current Tile Label
        JLabel currentTileLabel = new JLabel("Click to rotate");
        currentTileLabel.setForeground(Color.WHITE);
        GridBagConstraints ctlc = new GridBagConstraints();
        ctlc.gridx = 0;
        ctlc.gridy = 0;
        ctlc.anchor = GridBagConstraints.PAGE_END;
        add(currentTileLabel, ctlc);

//        Current Tile Card
        JButton currentTile = new JButton();
        currentTile.setPreferredSize(new Dimension(125, 125));

        currentTile.add(new GameTile(current));
        currentTile.setBorderPainted(false);
        currentTile.setFocusPainted(false);
        currentTile.setContentAreaFilled(false);
        currentTile.setMargin(new Insets(0,0,0,0));


        GridBagConstraints ctcc = new GridBagConstraints();
        ctcc.gridx = 0;
        ctcc.gridy = 1;
        add(currentTile, ctcc);
    }
}

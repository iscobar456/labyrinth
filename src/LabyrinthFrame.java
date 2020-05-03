import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;

public class LabyrinthFrame {
    public LabyrinthFrame() {
        JFrame f = new JFrame("Labyrinth");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setMinimumSize(new Dimension(700, 500));
        f.setSize(new Dimension(1600, 1000));
        f.getContentPane().setBackground(Color.decode("#0b0c10"));
        f.setLayout(new GridBagLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("media\\maze.png");
        f.setIconImage(icon);

//        Game Grid Container
        JPanel gameGrid = new JPanel();
        gameGrid.setLayout(new GridBagLayout());
        gameGrid.setBackground(Color.decode("#0b0c10"));

        GridBagConstraints ggcc = new GridBagConstraints();
        ggcc.gridx = 1;
        ggcc.gridy = 0;


//        Creating and assigning arrows
        gameGrid.add(new InsertArrow('d'), new ArrowConstants(6, 0));
        gameGrid.add(new InsertArrow('d'), new ArrowConstants(4, 0));
        gameGrid.add(new InsertArrow('d'), new ArrowConstants(2, 0));
        gameGrid.add(new InsertArrow('l'), new ArrowConstants(8, 2));
        gameGrid.add(new InsertArrow('l'), new ArrowConstants(8, 4));
        gameGrid.add(new InsertArrow('l'), new ArrowConstants(8, 6));
        gameGrid.add(new InsertArrow('u'), new ArrowConstants(6, 8));
        gameGrid.add(new InsertArrow('u'), new ArrowConstants(4, 8));
        gameGrid.add(new InsertArrow('u'), new ArrowConstants(2, 8));
        gameGrid.add(new InsertArrow('r'), new ArrowConstants(0, 6));
        gameGrid.add(new InsertArrow('r'), new ArrowConstants(0, 4));
        gameGrid.add(new InsertArrow('r'), new ArrowConstants(0, 2));

//        Initializing Game Grid
        GameTracker tracker = new GameTracker();
        GridBagConstraints newTileConsts = new GridBagConstraints();
        for (int i = 0; i < tracker.getGrid().length; i++) {
            Tile[] tRow = tracker.getGrid()[i];
            for (int j = 0; j < tRow.length; j++) {
                Tile t = tRow[j];
                newTileConsts.gridx = j + 1;
                newTileConsts.gridy = i + 1;
                gameGrid.add(new GameTile(t), newTileConsts);
            }
        }

//        Current Tile Container
        JPanel currentTile = new JPanel();
        currentTile.setLayout(new GridBagLayout());
        currentTile.setBackground(Color.decode("#0b0c10"));
        GridBagConstraints ctc = new GridBagConstraints();
        ctc.ipadx = 40;
        ctc.gridx = 0;
        ctc.gridy = 0;
        ctc.anchor = GridBagConstraints.WEST;

//        Current Tile Label
        JLabel ctl = new JLabel("Click to rotate");
        ctl.setForeground(Color.WHITE);
        GridBagConstraints ctlc = new GridBagConstraints();
        ctlc.gridx = 0;
        ctlc.gridy = 0;
        ctlc.anchor = GridBagConstraints.PAGE_END;
        currentTile.add(ctl, ctlc);

//        Current Tile Card
        GameTile ctcard = new GameTile(tracker.getCurrentTile());
        ctcard.setPreferredSize(new Dimension(200, 200));
        GridBagConstraints ctcc = new GridBagConstraints();
        ctcc.gridx = 0;
        ctcc.gridy = 1;
        ctcc.anchor = GridBagConstraints.PAGE_START;
        currentTile.add(ctcard, ctcc);

        f.add(currentTile, ctc);
        f.add(gameGrid, ggcc);

        f.addMouseListener(new GameGridClickListener(f, gameGrid));

        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }
    public static void main(String args[]) {
        new LabyrinthFrame();
    }
}

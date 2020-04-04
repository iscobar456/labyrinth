import javafx.scene.shape.TriangleMesh;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.Collection;

public class LabyrinthFrame {
    public LabyrinthFrame() {
        JFrame f = new JFrame("Labyrinth");
        f.setMinimumSize(new Dimension(700, 500));
        f.setSize(new Dimension(1600, 1000));
        f.getContentPane().setBackground(Color.decode("#0b0c10"));
        f.setLayout(new GridBagLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("media\\maze.png");
        f.setIconImage(icon);

//        Game Grid Container
        JPanel ggc = new JPanel();
        ggc.setLayout(new GridBagLayout());
        ggc.setBackground(Color.decode("#0b0c10"));

        GridBagConstraints ggcc = new GridBagConstraints();
        ggcc.gridx = 1;
        ggcc.gridy = 0;


//        Game Grid Arrows
        class InsertArrow extends JPanel {
            private char orientation;
            public InsertArrow(char orient) {
                orientation = Character.toLowerCase(orient);
                this.setPreferredSize(new Dimension(100, 100));
                this.setBackground(Color.decode("#0b0c10"));
            }
            public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                super.paintComponent(g2d);
                g2d.setColor(Color.decode("#677e75"));
                if (orientation == 'u') {
                    System.out.println("running");
                    g2d.translate(30, 60);
                    Polygon triangle = new Polygon(new int[] {0, 20, 40}, new int[] {0, -20, 0}, 3);
                    g2d.fill(triangle);
                } else if (orientation == 'r') {
                    g2d.translate(40,30);
                    Polygon triangle = new Polygon(new int[] {0, 20, 0}, new int[] {0, 20, 40}, 3);
                    g2d.fill(triangle);
                } else if (orientation == 'd') {
                    g2d.translate(30, 40);
                    Polygon triangle = new Polygon(new int[] {0, 20, 40}, new int[] {0, 20, 0}, 3);
                    g2d.fill(triangle);
                } else if (orientation == 'l') {
                    g2d.translate(40, 30);
                    Polygon triangle = new Polygon(new int[] {0, 20, 20}, new int[] {20, 0, 40}, 3);
                    g2d.fill(triangle);
                }
            }
        }

//        Creating and assigning arrows
        class ArrowConsts extends GridBagConstraints {
            public ArrowConsts(int x, int y, int ipadx, int ipady) {
                this.gridx = x;
                this.gridy = y;
//                this.ipadx = ipadx;
//                this.ipady = ipady;
            }
        }

        ggc.add(new InsertArrow('d'), new ArrowConsts(7, 0, 200, 0));
        ggc.add(new InsertArrow('d'), new ArrowConsts(7, 0, 0, 0));
        ggc.add(new InsertArrow('d'), new ArrowConsts(7, 0, 200, 0));
//        ggc.add(new InsertArrow('l'), new ArrowConsts(8, 1, 1, 2));
//        ggc.add(new InsertArrow('l'), new ArrowConsts(8, 2, 1, 2));
//        ggc.add(new InsertArrow('l'), new ArrowConsts(8, 3, 1, 2));
//        ggc.add(new InsertArrow('u'), new ArrowConsts(3, 8, 2, 1));
//        ggc.add(new InsertArrow('u'), new ArrowConsts(2, 8, 2, 1));
//        ggc.add(new InsertArrow('u'), new ArrowConsts(1, 8, 2, 1));
//        ggc.add(new InsertArrow('r'), new ArrowConsts(0, 3, 1, 2));
//        ggc.add(new InsertArrow('r'), new ArrowConsts(0, 2, 1, 2));
//        ggc.add(new InsertArrow('r'), new ArrowConsts(0, 1, 1, 2));

//        Game Grid
        JPanel gameGrid = new JPanel();
        gameGrid.setBackground(Color.decode("#1f2833"));

        GridLayout gameLayout = new GridLayout(7, 7, 3, 3);
        gameGrid.setLayout(gameLayout);

        GridBagConstraints gameGridConstraints = new GridBagConstraints();
        gameGridConstraints.gridy = 1;
        gameGridConstraints.gridx = 1;
        gameGridConstraints.gridheight = 7;
        gameGridConstraints.gridwidth = 7;

        ggc.add(gameGrid, gameGridConstraints);

//        Initializing Game Grid
        GameTracker tracker = new GameTracker();
        GridBagConstraints newTileConsts = new GridBagConstraints();
        for (int i = 0; i < tracker.getGrid().length; i++) {
            Tile[] tRow = tracker.getGrid()[i];
            for (int j = 0; j < tRow.length; j++) {
                Tile t = tRow[j];
                newTileConsts.gridx = j + 1;
                newTileConsts.gridy = i + 1;
                ggc.add(new GameTile(t), newTileConsts);
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
        f.add(ggc, ggcc);

        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }
    public static void main(String args[]) {
        new LabyrinthFrame();
    }
}

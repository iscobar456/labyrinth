import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertArrow extends JButton implements ActionListener {
    private final char orientation;
    private ShiftStackConfig config = new ShiftStackConfig();
    private LabyrinthFrame gameFrame;
    public InsertArrow(char orient, int position, LabyrinthFrame frame) {
        orientation = Character.toLowerCase(orient);

        config.horizontal = orientation == 'r' || orientation == 'l';
        config.upShift = orientation == 'r' || orientation == 'd';
        config.position = position;

        gameFrame = frame;

        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setPreferredSize(new Dimension(70, 70));
        this.setBackground(Color.decode("#0b0c10"));

        this.addActionListener(this);
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setColor(Color.decode("#677e75"));
        if (orientation == 'u') {
            g2d.translate(20, 42.5);
            Polygon triangle = new Polygon(new int[] {0, 15, 30}, new int[] {0, -15, 0}, 3);
            g2d.fill(triangle);
        } else if (orientation == 'r') {
            g2d.translate(27.5,20);
            Polygon triangle = new Polygon(new int[] {0, 15, 0}, new int[] {0, 15, 30}, 3);
            g2d.fill(triangle);
        } else if (orientation == 'd') {
            g2d.translate(20, 27.5);
            Polygon triangle = new Polygon(new int[] {0, 15, 30}, new int[] {0, 15, 0}, 3);
            g2d.fill(triangle);
        } else if (orientation == 'l') {
            g2d.translate(27.5, 20);
            Polygon triangle = new Polygon(new int[] {0, 15, 15}, new int[] {15, 0, 30}, 3);
            g2d.fill(triangle);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        gameFrame.getTracker().shiftStack(config);
    }
}
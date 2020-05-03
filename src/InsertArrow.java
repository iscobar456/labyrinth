import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InsertArrow extends JButton {
    private char orientation;
    public InsertArrow(char orient) {
        orientation = Character.toLowerCase(orient);
        Border border = BorderFactory.createEmptyBorder();
        this.setBorder(border);
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(Color.decode("#0b0c10"));
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setColor(Color.decode("#677e75"));
        if (orientation == 'u') {
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
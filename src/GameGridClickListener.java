import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameGridClickListener implements MouseListener {
    private JFrame gameFrame;
    private JPanel gameGrid;
    GameGridClickListener(JFrame frame, JPanel grid) {
        gameFrame = frame;
        gameGrid = grid;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("(" + mouseEvent.getPoint().x + ", " + mouseEvent.getPoint().y + ")");
        System.out.println(gameFrame.getSize() + " " + gameGrid.getBounds());
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }
}

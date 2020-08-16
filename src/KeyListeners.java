import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class KeyListeners {
    public static void startListeners(GameTracker tracker, ActionMap am, InputMap im) {

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "endTurn");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUp");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDown");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");

        am.put("endTurn", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (tracker.getCurrentPlayer().hasMovedGrid()) {
                    tracker.endTurn();
                } else if (tracker.getCurrentPlayer().hasInsertedTile()) {
                    tracker.updateGrid();
                }
            }
        });
        am.put("moveUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                tracker.movePlayer("UP");
            }
        });
        am.put("moveRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                tracker.movePlayer("RIGHT");
            }
        });
        am.put("moveDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                tracker.movePlayer("DOWN");
            }
        });
        am.put("moveLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                tracker.movePlayer("LEFT");
            }
        });
    }
}

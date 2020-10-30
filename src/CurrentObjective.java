import javax.swing.*;
import java.awt.*;

public class CurrentObjective extends JPanel {
    private LabyrinthFrame frame;
    public CurrentObjective(LabyrinthFrame frame) {
        this.frame = frame;

        setBackground(Color.decode("#0b0c10"));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel title = new JLabel("Current Treasure: ");
        title.setForeground(Color.WHITE);
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.ipady = 15;
        add(title, titleConstraints);

        ImageIcon treasureIcon = frame.getTracker().getCurrentPlayer().getCurrentTreasure().getIcon();
        JLabel icon = new JLabel(treasureIcon);
        icon.setBackground(Color.decode("#7c7646"));
        add(icon);
    }

    //    Replaces existing icon.
    public void setNewObjective() {
        remove(1);

        ImageIcon treasureIcon = frame.getTracker().getCurrentPlayer().getCurrentTreasure().getIcon();
        JLabel icon = new JLabel(treasureIcon);
        icon.setBackground(Color.decode("#7c7646"));
        add(icon);
    }
}

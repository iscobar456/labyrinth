import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScoreCounter extends JPanel {
    private LabyrinthFrame frame;
    public ScoreCounter(LabyrinthFrame frame) {
        this.frame = frame;

        setBackground(Color.decode("#0b0c10"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Player Scores: (first to 3 wins)");
        title.setForeground(Color.WHITE);
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.ipady = 15;
        add(title, titleConstraints);

        ArrayList<Player> players = frame.getTracker().getPlayers();
        for (Player player : players) {
            JLabel score = new JLabel(player.getPlayerName().toUpperCase() + ": " + player.getPlayerScore());
            score.setForeground(Color.WHITE);
            add(score);
        }
    }

//    Removes all existing child components and runs copied code from line #13-24 to display new scores.
    public void setNewScores() {
        removeAll();

        JLabel title = new JLabel("Player Scores: (first to 3 wins)");
        title.setForeground(Color.WHITE);
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.ipady = 15;
        add(title, titleConstraints);

        ArrayList<Player> players = frame.getTracker().getPlayers();
        for (Player player : players) {
            JLabel score = new JLabel(player.getPlayerName().toUpperCase() + ": " + player.getPlayerScore());
            score.setForeground(Color.WHITE);
            add(score);
        }
    }
}

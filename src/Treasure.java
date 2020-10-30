import javax.swing.*;

public class Treasure {
    private String name;
    private String iconName;
    private Boolean isCollected = false;

    public Treasure(String name, String imageName) {
        this.name = name;
        this.iconName = imageName;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("media/small-icons/" + iconName));
    }

    public ImageIcon getIcon(boolean large) {
        if (large) {
            return new ImageIcon(getClass().getResource("media/large-icons/" + iconName));
        } else {
            return new ImageIcon(getClass().getResource("media/small-icons/" + iconName));
        }
    }

    public Boolean getCollectedState() {
        return isCollected;
    }

    public void setIsCollected(Boolean b) {
        if (b) {
            this.iconName = "taken.png";
        }
        this.isCollected = b;
    }
}

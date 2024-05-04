import java.util.*;

public class RoomWithScene extends Room {
    private boolean activeScene;

    public RoomWithScene(String name, boolean activeScene) {
        super(name);
        this.activeScene = activeScene;
    }

    public void addRole(Role role) {

    }

    public void removeRole(Role role) {

    }

    public List<Role> availableRoles() {
        List<Role> list = new ArrayList<Role>();
        return list;
    }

    // getters and setters


    public boolean getActiveScene() {
        return activeScene;
    }

    public void setActiveScene(boolean activeScene) {
        this.activeScene = activeScene;
    }
}

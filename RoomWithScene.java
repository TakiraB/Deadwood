import java.util.*;

// Added takesForSuccess to store takes for each scene in each room
public class RoomWithScene extends Room {
    private boolean activeScene;
    private ArrayList<Role> offCardRoles;
    // private int takesForSuccess;
    private ArrayList<Takes> takesForSuccess;

    // Now holds all the information for off-card roles associated with a Room with a scene
    public RoomWithScene(String name) {
        super(name);
        this.offCardRoles = new ArrayList<>();
    }

    // adds role to the room
    public void addRole(Role role) {
        offCardRoles.add(role);
    }

    // removed role from the room
    public void removeRole(Role role) {
        offCardRoles.remove(role);
    }

    // returns a list of all the available roles
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

    public void addTakesForScene(Takes take){
        takesForSuccess.add(take);
    }

    public ArrayList<Takes> getTakesList(){
        return takesForSuccess;
    }
}

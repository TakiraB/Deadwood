import java.util.*;

// Added takesForSuccess to store takes for each scene in each room
public class RoomWithScene extends Room {
    private boolean activeScene;
    private ArrayList<Role> offCardRoles;
    private ArrayList<Takes> takesForSuccess;
    private SceneCard sceneCard;
    private Scene currentScene;
    private Area sceneRoomArea;
    private int playerCounter;

    // Now holds all the information for off-card roles associated with a Room with
    // a scene
    public RoomWithScene(String name) {
        super(name);
        this.offCardRoles = new ArrayList<>();
        this.takesForSuccess = new ArrayList<>();
        this.sceneCard = null;
        this.currentScene = new Scene(name, false, false, null);
        this.playerCounter = 0;
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

    public void addTakesForScene(Takes take) {
        takesForSuccess.add(take);
    }

    public void removeTakesForScene() {
        if (!takesForSuccess.isEmpty()) {
            takesForSuccess.remove(0);
        } else {
            System.out.println("The scene is wrapped, there are no shot counters to be removed!");
        }
    }

    public ArrayList<Takes> getTakesList() {
        return takesForSuccess;
    }

    public SceneCard getSceneCard() {
        return this.sceneCard;
    }

    public void setSceneCard(SceneCard sceneCard) {
        this.sceneCard = sceneCard;
        if (sceneCard != null) {
            this.activeScene = true;
        } else {
            this.activeScene = false;
        }
    }

    public boolean hasSceneCard() {
        return sceneCard != null;
    }

    public Scene getRoomScene() {
        return currentScene;
    }

    public void setRoomScene(Scene newScene) {
        this.currentScene = newScene;
    }

    public void checkWrapScene(SceneCard currentSceneCard) {
        if (currentScene != null && takesForSuccess.isEmpty()) {
            currentScene.wrapScene(this, currentSceneCard);
            setActiveScene(false);
        }
    }

    public void setOffCardRoles(ArrayList<Role> offCardRoles) {
        this.offCardRoles = offCardRoles;
    }

    public ArrayList<Role> getOffCardRoles() {
        return this.offCardRoles;
    }

    public Area getSceneRoomArea(){
        return sceneRoomArea;
    }

    public void setSceneRoomArea(Area roomArea){
        this.sceneRoomArea = roomArea;
    }

    public void incrementCounter(){
        playerCounter++;
    }

    public int getCounter(){
        return playerCounter;
    }
}

public class Scene {
    private String name;
    private boolean sceneWrapped;
    private boolean sceneCardActive;
    private SceneCard sceneCard;

    public Scene(String name, boolean sceneWrapped, boolean sceneCardActive, SceneCard sceneCard) {
        this.name = name;
        this.sceneWrapped = sceneWrapped;
        this.sceneCardActive = sceneCardActive;
        this.sceneCard = sceneCard;
    }

    public boolean wrapScene() {
        return true;
    }

    public void updateShots() {

    }

    public void updateScene() {

    }

    public boolean flipSceneCard() {
        return true;
    }

    // getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSceneWrapped() {
        return sceneWrapped;
    }

    public void setSceneWrapped(boolean sceneWrapped) {
        this.sceneWrapped = sceneWrapped;
    }

    public boolean getSceneCardActive() {
        return sceneCardActive;
    }

    public void setSceneCardActive(boolean sceneCardActive) {
        this.sceneCardActive = sceneCardActive;
    }

    public SceneCard getSceneCard() {
        return sceneCard;
    }

    public void setSceneCard(SceneCard sceneCard) {
        this.sceneCard = sceneCard;
    }
}

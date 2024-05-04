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

    // wrap the scene and do the actions associated with wrapping a scene
    public boolean wrapScene() {
        return true;
    }

    // increase the shots counter
    public void updateShots() {

    }

    // update the scene
    public void updateScene() {

    }

    // flip the SceneCard associated with this scene
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

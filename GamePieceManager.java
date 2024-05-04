import java.util.*;

public class GamePieceManager {
    private int sides;
    private List<SceneCard> sceneCards;

    public GamePieceManager(int sides, List<SceneCard> sceneCards) {
        this.sides = sides;
        this.sceneCards = sceneCards;
    }

    // roll a six sided die
    public int roll() {
        return 1;
    }

    // roll a six sided die with rollWithModifier
    public int rollWithModifier(int rollWithModifier) {
        return 1 + rollWithModifier;
    }

    // shuffle the sceneCards
    public void shuffle() {

    }

    // pick a scene from the list of SceneCards
    public String pickScene() {
        return "scene";
    }

    // getters and setters


    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public List<SceneCard> getSceneCards() {
        return sceneCards;
    }

    public void setSceneCards(List<SceneCard> sceneCards) {
        this.sceneCards = sceneCards;
    }
}

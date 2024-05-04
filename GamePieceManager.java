import java.util.*;

public class GamePieceManager {
    private int sides;
    private List<SceneCard> sceneCards;

    public GamePieceManager(int sides, List<SceneCard> sceneCards) {
        this.sides = sides;
        this.sceneCards = sceneCards;
    }

    public int roll() {
        return 1;
    }

    public int rollWithModifier(int rollWithModifier) {
        return 1;
    }

    public void shuffle() {

    }

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

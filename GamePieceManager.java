import java.util.*;
import java.util.Random;

public class GamePieceManager {
    private int sides;
    private List<SceneCard> sceneCards;

    public GamePieceManager(int sides, List<SceneCard> sceneCards) {
        this.sides = sides;
        this.sceneCards = sceneCards;
    }

    // roll a six sided die
    public int roll() {
        Random random = new Random();
        return random.nextInt(this.sides) + 1;
    }

    // roll a six sided die with rollWithModifier
    public int rollWithModifier(int rollWithModifier) {
        return roll() + rollWithModifier;
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

import java.util.*;

public class GamePieceManager {
    private int sides;
    private ArrayList<SceneCard> sceneCards;

    public GamePieceManager(int sides, ArrayList<SceneCard> sceneCards) {
        this.sides = sides;
        this.sceneCards = sceneCards;
    }

    // roll a six sided die
    public int roll() {
        Random random = new Random();
        int rollValue = random.nextInt(sides) + 1;
        return rollValue;
    }

    // roll a six sided die with rollWithModifier
    public int rollWithModifier(int rollWithModifier) {
        return roll() + rollWithModifier;
    }

    // shuffle the sceneCards
    public void shuffle(ArrayList<SceneCard> sceneCards) {
        Collections.shuffle(sceneCards);
    }

    // pick a scene from the list of SceneCards
    public SceneCard pickScene() {
        if (sceneCards.isEmpty()) {
            System.out.println("There are no Scene Cards left!");
            return null;
        } else {
            return sceneCards.get(0);
        }
    }

    // getters and setters

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public ArrayList<SceneCard> getSceneCards() {
        return sceneCards;
    }

    public void setSceneCards(ArrayList<SceneCard> sceneCards) {
        this.sceneCards = sceneCards;
    }
}

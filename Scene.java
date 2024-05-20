import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    public void wrapScene(RoomWithScene activeRoom, SceneCard currentSceneCard) {
        giveWrappedBonuses(activeRoom, currentSceneCard);
        this.sceneWrapped = true;
        this.sceneCardActive = false;
        this.sceneCard = null;
    }

    // gives all the player's their bonuses from the scene wrapping
    public void giveWrappedBonuses(RoomWithScene activeRoom, SceneCard currentSceneCard) {
        GamePieceManager scenePieceManager = new GamePieceManager(6, null);
        int sceneBudget = currentSceneCard.getBudget();
        List<Role> starredRoles = currentSceneCard.getRoles();

        // checking players in role on card
        ArrayList<Player> playerOnCard = new ArrayList<>();
        for (Role starredRole : starredRoles) {
            if (starredRole.getPlayerOnRole() != null) {
                playerOnCard.add(starredRole.getPlayerOnRole());
            }
        }

        // bonuses are only given if there is a player on the sceneCard when wrapped
        if (playerOnCard.size() > 0) {
            System.out.println("Here are your bonuses for finishing the scene!");
            // get and sort the rolls for the bonuses
            ArrayList<Integer> rollResults = new ArrayList<Integer>();
            for (int i = 0; i < sceneBudget; i++) {
                rollResults.add(scenePieceManager.roll());
            }
            Collections.sort(rollResults, Comparator.reverseOrder());

            // give the rewards to the correct players
            int rewardGiver = 0;
            for (Integer reward : rollResults) {
                if (rewardGiver > playerOnCard.size()) {
                    rewardGiver = 0;
                }
                Player currentPlayer = playerOnCard.get(rewardGiver);
                int currentPlayerDollars = currentPlayer.getDollars();
                currentPlayer.setDollars(currentPlayerDollars + reward);
            }

            // print all the new balances of the players
            for (Player player : playerOnCard) {
                System.err.println(player.getName() + " now has " + player.getDollars());
            }

            for (Role offCardRole : activeRoom.getOffCardRoles()) {
                Player offCardPlayer = offCardRole.getPlayerOnRole();
                if (offCardPlayer != null) {
                    offCardPlayer.setDollars(offCardPlayer.getDollars() + offCardRole.getRank());
                    System.out
                            .println(offCardPlayer.getName() + " now has " + offCardPlayer.getDollars() + " dollars!");
                }
            }
        } else {
            System.out.println("There was no players on card role, so no bonus was given.");
        }
    }

    // // increase the shots counter
    // public void updateShots() {

    // }

    // // update the scene
    // public void updateScene() {

    // }

    // // flip the SceneCard associated with this scene
    // public boolean flipSceneCard() {
    // return true;
    // }

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

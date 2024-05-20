import java.util.ArrayList;
import java.util.List;

public class Scene {
    private String name;
    private boolean sceneWrapped;
    private boolean sceneCardActive;
    private SceneCard sceneCard;
    private GamePieceManager scenePieceManager;

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

    public void giveWrappedBonuses(RoomWithScene activeRoom, SceneCard currentSceneCard){
        int sceneBudget = currentSceneCard.getBudget();
        // System.out.println("Still need to implement this logic!");
        List<Role> starredRoles = currentSceneCard.getRoles();
        System.out.println(starredRoles);
        System.out.println("Here are your bonuses for finishing the scene!");

        for(Role activeRole : starredRoles){
            Player activePlayer = activeRole.getPlayerOnRole();
            if (activePlayer != null){
                System.out.println(activePlayer.getName());
                if(activeRole.getStarredRole() == true){
                    int bonusInitialized = 0;
                    for(int i=0; i<sceneBudget; i++){
                        bonusInitialized += scenePieceManager.roll();
                }
                activePlayer.setDollars(activePlayer.getDollars()+bonusInitialized);
                System.out.println(activePlayer.getName() + " was given " + bonusInitialized + " dollars!");
                }
                else {
                    activePlayer.setDollars(activePlayer.getDollars()+sceneBudget);
                    System.out.println("Do we ever get here?");
                }
            }
        }

        for (Role offCardRole: activeRoom.getOffCardRoles()) {
            Player offCardPlayer = offCardRole.getPlayerOnRole();
            if (offCardPlayer != null) {
                System.out.println(offCardPlayer.getName());
                offCardPlayer.setDollars(offCardPlayer.getDollars() + offCardRole.getRank());
                System.out.println(offCardPlayer.getName() + " was given " + offCardRole.getRank() + " dollars!");
            }
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
    //     return true;
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

import java.util.ArrayList;

public class Player {
    private String name;
    private int rank;
    private int dollars;
    private int credits;
    private Role activeRole;
    private int practiceChips;
    private Room currentRoom;
    private boolean hasMoved;
    private boolean hasActed;
    // private GamePieceManager playerPieceManager;
    // private Board board;
    private SceneCard currentSceneCard;

    public Player(String name, int rank, int dollars, int credits, Role activeRole, int practiceChips, Room currentRoom,
            boolean hasMoved, boolean hasActed) {
        this.name = name;
        this.rank = rank;
        this.dollars = dollars;
        this.credits = credits;
        this.activeRole = activeRole;
        this.practiceChips = practiceChips;
        this.currentRoom = currentRoom;
        this.hasMoved = hasMoved;
        this.hasActed = hasActed;
    }

    // move to a destination (Room role)
    public void move(Room destination) {
        this.setPlayerRoom(destination);
        this.setHasMoved(true);
    }

    public boolean act(Board board, GamePieceManager playerPieceManager) {

        System.out.println("Oh, so you want to act, do ya? Let's do it!");

        String activeCurrentRoomName = currentRoom.getName();
        RoomWithScene activeCurrentRoom = (RoomWithScene) board.getRoomFromBoard(activeCurrentRoomName);

        SceneCard activeCurrentSceneCard = activeCurrentRoom.getSceneCard();

        // RoomWithScene actRoom = (RoomWithScene) currentRoom;
        // SceneCard currentScene = actRoom.getSceneCard();
        // int budget = currentScene.getBudget();

        // // Get the name of the current room they are in, and grab that Room object
        // from the Board
        // String activeCurrentRoomName = currentRoom.getName();
        // RoomWithScene activeCurrentRoom = (RoomWithScene)
        // board.getRoomFromBoard(activeCurrentRoomName);

        // SceneCard activeCurrentSceneCard;

        // If there is an active scene, get the scene card in the room and display it
        if (activeCurrentRoom.hasSceneCard()) {
            // if(activeCurrentRoom.getActiveScene()){
            // activeCurrentSceneCard = activeCurrentRoom.getSceneCard();
            System.out.println("There's an active scene already here: ");
            System.out.println("Scene Name: " + activeCurrentSceneCard.getName());
            System.out.println("Description: " + activeCurrentSceneCard.getDescription());
            System.out.println("Budget: " + activeCurrentSceneCard.getBudget());
            // System.out.println("Roles available: " + activeCurrentSceneCard.getRoles());
        }
        // else, grab the list of SceneCards and shuffle them, pick one and display it
        else {
            System.out.println("No active scene here!: ");
            ArrayList<SceneCard> currentSceneCards = playerPieceManager.getSceneCards();
            playerPieceManager.shuffle(currentSceneCards);
            activeCurrentSceneCard = playerPieceManager.pickScene();

            if (activeCurrentSceneCard == null) {
                System.out.println("Oh no! There are no scene cards left! :(");
                return false;
            }
            System.out.println("Flipping the scene card in this room: ");
            System.out.println("Scene Name: " + activeCurrentSceneCard.getName());
            System.out.println("Description: " + activeCurrentSceneCard.getDescription());
            System.out.println("Budget: " + activeCurrentSceneCard.getBudget());
            // System.out.println("Roles available: " + newSceneCard.getRoles());

            activeCurrentRoom.setSceneCard(activeCurrentSceneCard);
            activeCurrentRoom.setActiveScene(true);
        }

        System.out.println("Time to roll! Will you succeed or fail?");

        int sceneRoll;
        if (practiceChips > 0) {
            sceneRoll = playerPieceManager.rollWithModifier(practiceChips);
        } else {
            sceneRoll = playerPieceManager.roll();
        }

        System.out.println("You rolled a: " + sceneRoll);

        int sceneBudget = activeCurrentSceneCard.getBudget();

        // If the roll is successful, distribute awards based on starred role
        if (sceneRoll >= sceneBudget) {
            if (activeRole.getStarredRole() == true) {
                // activeCurrentRoom.removeTakesForScene();
                this.credits += 2;
                System.out.println("Success! Here's two credits.");
            } else {
                // activeCurrentRoom.removeTakesForScene();
                this.credits += 1;
                this.dollars += 1;
                System.out.println("Success! Here's a credit and a dollar.");
            }
            activeCurrentRoom.removeTakesForScene();
        } else {
            if (activeRole.getStarredRole() == false) {
                this.dollars += 1;
                System.out.println("Failure! But you still get a dollar.");
            } else {
                System.out.println("Boo! You get nothing.");
            }
        }

        this.hasActed = true;
        if (activeCurrentRoom.getTakesList().isEmpty()) {
            activeCurrentRoom.checkWrapScene(activeCurrentSceneCard);
            this.activeRole = null;
        }
        return true;
    }

    public boolean rehearse(Board board) {

        // Get the name of the current room they are in, and grab that Room object from
        // the Board

        RoomWithScene rehearseRoom = (RoomWithScene) currentRoom;
        SceneCard currentScene = rehearseRoom.getSceneCard();
        int budget = currentScene.getBudget();

        System.out.println("Current Scene: " + currentScene.getName());
        System.out.println("Scene Budget: " + currentScene.getBudget());

        if (practiceChips >= budget - 1) {
            System.out.println(
                    "You cannot get more practice chips, since you are already are guaranteed success when acting");
        } else if (activeRole != null && practiceChips < budget) {
            practiceChips++;
            System.out.println("You have successfully rehearsed! Here's a practice chip!");
            this.hasActed = true;
        } else {
            System.out.println("Boo! You aren't in a role right now.");
            return false;
        }
        return true;
    }

    // takes in parameters for rankChoice (provided by CastingOffice) and payment
    // type
    public boolean upgrade(int rankChoice, String dollarOrCredit) {
        return true;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public Role getRole() {
        return activeRole;
    }

    public int getDollars() {
        return dollars;
    }

    public int getCredits() {
        return credits;
    }

    public Role getActiveRole() {
        return activeRole;
    }

    public int getPracticeChips() {
        return practiceChips;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public void setPlayerRank(int rank) {
        this.rank = rank;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setActiveRole(Role role) {
        this.activeRole = role;
    }

    public void setPracticeChips(int practiceChips) {
        this.practiceChips = practiceChips;
    }

    public Room getPlayerRoom() {
        return currentRoom;
    }

    public void setPlayerRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean getHasActed() {
        return hasActed;
    }

    public void setHasActed(boolean hasActed) {
        this.hasActed = hasActed;
    }
}

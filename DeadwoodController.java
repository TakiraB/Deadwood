import org.w3c.dom.Document;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DeadwoodController {
    private Board board;
    private DeadwoodView view;
    private ArrayList<Player> playerList;
    private GameState gameState;
    private List<Integer> numbers;
    private ArrayList<SceneCard> cards;
    // private GamePieceManager pieceManager;

    // public DeadwoodController(Board board, DeadwoodView view) {
    public DeadwoodController(Board board) {
        this.board = board;
        // this.view = view;
        this.gameState = new GameState(null, playerList, 0, 0, board);
        this.playerList = new ArrayList<>();
        this.numbers = new ArrayList<>();
        this.cards = new ArrayList<SceneCard>();

    }

    public void setView(DeadwoodView view) {
        this.view = view;
    }

    // set up the board
    public void setUpBoard() {
        Document doc = null;
        parseBoard parsingBoard = new parseBoard();
        Board board = new Board();
        try {
            doc = parsingBoard.getDocFromFile("board.xml");
            board = parsingBoard.readBoardData(doc);
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }
    }

    // setup the scene cards for each room
    public void setUpSceneCards() {
        Document doc = null;
        parseCards parsingCards = new parseCards();
        try {
            doc = parsingCards.getDocFromFile("cards.xml");
            cards = parsingCards.readCardData(doc);
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }
        for (int i = 0; i < cards.size(); i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        // giving each RoomWithScene a sceneCard
        board.sceneCardDistribution(numbers, cards, 1);

        GamePieceManager gamePieceManager = new GamePieceManager(6, cards);
    }

    // Create Player objects based on the number of players
    public void setActivePlayers(int playerCount) {
        // if (playerCount < 2 || playerCount > 8) {
        // JOptionPane.showMessageDialog(view,"That's not an appropriate amount of
        // players! Sorry! :(");
        // }
        for (int i = 0; i < playerCount; i++) {
            String playerName = JOptionPane.showInputDialog("What is the name for Player " + (i + 1) + "?");
            // Initialize Player object for if statement
            Player tempPlayer;
            // Setting up Player starting room, setting up credits based on number of
            // players per rules
            if (playerCount == 5) {
                tempPlayer = new Player(playerName, 1, 0, 2, null, 0, board.getRoomFromBoard("Trailer"), false, false);
            } else if (playerCount == 6) {
                tempPlayer = new Player(playerName, 1, 0, 4, null, 0, board.getRoomFromBoard("Trailer"), false, false);
            } else if (playerCount == 7 || playerCount == 8) {
                tempPlayer = new Player(playerName, 2, 0, 0, null, 0, board.getRoomFromBoard("Trailer"), false, false);
            } else {
                tempPlayer = new Player(playerName, 1, 0, 0, null, 0, board.getRoomFromBoard("Trailer"), false, false);
            }
            // Add player to list of active players
            playerList.add(tempPlayer);
        }
    }

    // initialize the game state
    public void setGameState(int numPlayers) {
        Random random = new Random();
        int firstPlayerInt = random.nextInt(numPlayers);

        // Setting up the max number of days in the game based on the number of players
        // 3 if 2 players, and 4 for everything else
        if (numPlayers < 3) {
            gameState.setActivePlayer(playerList.get(firstPlayerInt));
            gameState.setPlayers(playerList);
            gameState.setCurrentDayCount(1);
            gameState.setMaxDayCount(3);
            gameState.setBoard(board);
        } else {
            gameState.setActivePlayer(playerList.get(firstPlayerInt));
            gameState.setPlayers(playerList);
            gameState.setCurrentDayCount(1);
            gameState.setMaxDayCount(4);
            gameState.setBoard(board);
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    // initialize the active players in the game
    public void initializeActivePlayers(int numPlayers) {
        setActivePlayers(numPlayers);
        setGameState(numPlayers);
    }

    // move action listener
    // Move case
    public void moveOption() {

        Player activePlayer = gameState.getActivePlayer();
        // System.out.println("Move option triggered for player: " +
        // activePlayer.getName());

        if (activePlayer.getActiveRole() != null) {
            view.displayGameMessage("You are currently in a Role, you cannot move until your role is completed!");
            return;
        }
        // makes sure the player has not already moved this turn
        else if (activePlayer.getHasMoved() == true) {
            view.displayGameMessage("You have already moved, you will have to wait until your next turn!");
            return;
        }
        ArrayList<String> neighbors = activePlayer.getPlayerRoom().getAdjacentNeighbors();
        view.showValidRooms(neighbors);
        view.playerCheck(gameState);
    }

    public void performMove(String neighborName) {
        Player activePlayer = gameState.getActivePlayer();

        if (activePlayer.getHasMoved()) {
            view.displayGameMessage("You have already moved this turn.");
            return;
        }
        Room destination;
        // special case for casting office
        if (neighborName.equals("Casting Office") || neighborName.equals("Office")) {
            destination = board.getRoomFromBoard("Casting Office");
        } else {
            destination = board.getRoomFromBoard(neighborName);
        }
        // moves activePlayer to the destination if the destination can be moved to
        if (destination != null &&
                (activePlayer.getPlayerRoom().getAdjacentNeighbors().contains(destination.getName())
                        || activePlayer.getPlayerRoom().getAdjacentNeighbors()
                                .contains(neighborName))) {
            activePlayer.move(destination);
            activePlayer.setHasMoved(true);
            destination.incrementCounter();
            view.updatePlayerRoom(activePlayer);
            view.displayCurrentPlayer(activePlayer);
            view.displayCurrentScene();
        } else {
            view.displayGameMessage("This move is not valid, either it doesn't exist or not adjacent.");
        }
    }

    // act action listener
    public boolean playerAct() {
        Player player = gameState.getActivePlayer();
        RoomWithScene room = (RoomWithScene) player.getPlayerRoom();
        int budget = room.getSceneCard().getBudget();
        int pChips = player.getPracticeChips();
        Random random = new Random();
        int rollValue = random.nextInt(6) + 1;
        int total = rollValue + pChips;
        view.textAction
                .append("You rolled a " + rollValue + " with " + pChips + "practice chips for a total of " + total);
        player.setHasActed(true);
        return total >= budget;
    }

    public boolean inStarredRole() {
        return gameState.getActivePlayer().getActiveRole().getStarredRole();
    }

    public void reward() {
        Player currentPlayer = gameState.getActivePlayer();
        if (inStarredRole()) {
            int credits = currentPlayer.getCredits();
            currentPlayer.setCredits(credits + 2);
        } else {
            int dollars = currentPlayer.getDollars();
            int credits = currentPlayer.getCredits();
            currentPlayer.setDollars(dollars + 1);
            currentPlayer.setCredits(credits + 1);
        }
    }

    public int takesAppear() {
        RoomWithScene room = (RoomWithScene) gameState.getActivePlayer().getPlayerRoom();
        ArrayList<Takes> takesList = room.getTakesList();
        int index = 0;
        while (takesList.get(index).getTakeCompleted()) {
            index += 1;
        }
        Takes take = takesList.get(index);
        take.setTakeCompleted(true);
        return index;
    }

    public void fail() {
        Player currentPlayer = gameState.getActivePlayer();
        if (!inStarredRole()) {
            int dollars = currentPlayer.getDollars();
            currentPlayer.setDollars(dollars + 1);
        } else {
            // do nothing since on card players get nothing for failing during act
        }
    }

    public boolean wrapSceneCheck() {
        RoomWithScene room = (RoomWithScene) gameState.getActivePlayer().getPlayerRoom();
        for (Takes take : room.getTakesList()) {
            if (!take.getTakeCompleted()) {
                return false;
            }
        }
        room.getRoomScene().setSceneCardActive(false);
        room.getRoomScene().setSceneWrapped(true);
        return true;
    }

    public void wrappedReward() {
        // check if player on card, if so give reward if not do nothing but explain why
        RoomWithScene room = (RoomWithScene) gameState.getActivePlayer().getPlayerRoom();
        List<Role> onCardRoles = room.getSceneCard().getRoles();
        ArrayList<Player> playerOnCard = new ArrayList<>();
        for (Role starredRole : onCardRoles) {
            if (starredRole.getPlayerOnRole() != null) {
                playerOnCard.add(starredRole.getPlayerOnRole());
            }
        }
        if (playerOnCard.size() > 0) {
            // get and sort the rolls for the bonuses
            int budget = room.getSceneCard().getBudget();
            ArrayList<Integer> rollResults = new ArrayList<Integer>();
            for (int i = 0; i < budget; i++) {
                Random random = new Random();
                int rollValue = random.nextInt(6) + 1;
                rollResults.add(rollValue);
            }
            Collections.sort(rollResults, Comparator.reverseOrder());

            // give the rewards to the correct players
            int rewardReceiverIndex = 0;
            for (Integer reward : rollResults) {
                if (rewardReceiverIndex > playerOnCard.size()) {
                    rewardReceiverIndex = 0;
                }
                Player currentPlayer = playerOnCard.get(rewardReceiverIndex);
                int currentPlayerDollars = currentPlayer.getDollars();
                currentPlayer.setDollars(currentPlayerDollars + reward);
            }

            // give rewards to the off card roles
            for (Role offCardRole : room.getOffCardRoles()) {
                Player offCardPlayer = offCardRole.getPlayerOnRole();
                if (offCardPlayer != null) {
                    offCardPlayer.setDollars(offCardPlayer.getDollars() + offCardRole.getRank());
                }
            }
        } else {
            view.textAction.append("There is not a player acting on the scene card, so no reward is given for the scene wrapping.");
        }
    }

    public ArrayList<Player> wrappedPlayerMove() {
        RoomWithScene room = (RoomWithScene) gameState.getActivePlayer().getPlayerRoom();
        ArrayList<Player> playersMoved = new ArrayList<>();
        ArrayList<Role> offCardRoles = room.getOffCardRoles();
        for (Role role: offCardRoles) {
            if(role.getPlayerOnRole() != null) {
                Player player = role.getPlayerOnRole();
                player.setActiveRole(null);
                playersMoved.add(player);
            }
        }
        List<Role> onCardRoles = room.getSceneCard().getRoles();
        for (Role role: onCardRoles) {
            if (role.getPlayerOnRole() != null) {
                Player player = role.getPlayerOnRole();
                player.setActiveRole(null);
                playersMoved.add(player);
            }
        }
        return playersMoved;
    }

    public Boolean checkEndDay() {
        int wrappedRooms = 0;
        for (Room room: board.getBoardLayout().values()) {
            if (room instanceof RoomWithScene) {
                RoomWithScene roomWithScene = (RoomWithScene) room;
                if (roomWithScene.getRoomScene().getSceneWrapped()) {
                    wrappedRooms += 1;
                }
            }
        }
        System.out.println(wrappedRooms);
        if (wrappedRooms == 9) {
            gameState.endDay(board, numbers, cards, gameState.getCurrentDayCount());
            for (Player player : playerList) {
                player.setActiveRole(null);
                player.setHasActed(false);
                player.setHasMoved(false);
                player.setPlayerRoom(board.getRoomFromBoard("trailer"));
                player.setPracticeChips(0);
                return true;
            }
        }
        return false;
    }

    // rehearse action listener
    public void playerRehearse() {
        int currentChips = gameState.getActivePlayer().getPracticeChips();
        gameState.getActivePlayer().setPracticeChips(currentChips + 1);
        gameState.getActivePlayer().setHasActed(true);
        view.displayCurrentPlayer(gameState.getActivePlayer());
    }

    // upgrade action listener
    public ArrayList<ArrayList<Object>> availableUpgrades() {
        Player currentPlayer = gameState.getActivePlayer();
        Room tempCastingOffice = board.getBoardLayout().get("Casting Office");
        CastingOffice castingOfficeUpgrades = (CastingOffice) tempCastingOffice;
        // double ArrayList of objects
        // the order is rank 2 dollar, rank 2 credit, rank 3 dollar, rank 3 credit...
        // rank 6 dollar, rank 6 credit,
        // first value in object: 0 or 1 if the player can upgrade to that rank
        // second value in object: String, rank *rank* costs *amount* (dollars or
        // credits)
        // initializing of arraylist
        ArrayList<ArrayList<Object>> rankInfoAndCost = new ArrayList<ArrayList<Object>>();
        for (int i = 0; i < castingOfficeUpgrades.getUpgradeChoices().size(); i++) {
            ArrayList<Object> tempArray = new ArrayList<Object>();
            tempArray.add(0);
            tempArray.add("E");
            rankInfoAndCost.add(tempArray);
        }

        // Adds values to rankInfoAndCost for all the dollar upgrades
        for (int i = 0; i < 5; i++) {
            Upgrades currentUpgrade = castingOfficeUpgrades.getUpgradeChoices().get(i);
            int rank = currentUpgrade.getUpgradeLevel();
            int amountRequired = currentUpgrade.getUpgradeAmount();
            int temp = i * 2;
            ArrayList<Object> tempArray = new ArrayList<Object>();
            // checking to make sure player is not too high of a level or does not have
            // enough credits
            if (currentPlayer.getDollars() >= amountRequired && currentPlayer.getRank() < rank) {
                tempArray.add(1);
            } else {
                tempArray.add(0);
            }
            tempArray.add("Rank " + currentUpgrade.getUpgradeLevel() + " costs " + currentUpgrade.getUpgradeAmount()
                    + " " + currentUpgrade.getCurrencyType() + "s");
            rankInfoAndCost.set(temp, tempArray);
        }

        // Adds values to rankInfoAndCost for all the credit upgrades
        for (int i = 5; i < 10; i++) {
            Upgrades currentUpgrade = castingOfficeUpgrades.getUpgradeChoices().get(i);
            int rank = currentUpgrade.getUpgradeLevel();
            int amountRequired = currentUpgrade.getUpgradeAmount();
            int temp = ((i - 5) * 2) + 1;
            ArrayList<Object> tempArray = new ArrayList<Object>();
            // checking to make sure player is not too high of a level or does not have
            // enough credits
            if (currentPlayer.getCredits() >= amountRequired && currentPlayer.getRank() < rank) {
                tempArray.add(1);
            } else {
                tempArray.add(0);
            }
            tempArray.add("Rank " + currentUpgrade.getUpgradeLevel() + " costs " + currentUpgrade.getUpgradeAmount()
                    + " " + currentUpgrade.getCurrencyType() + "s");
            rankInfoAndCost.set(temp, tempArray);
        }
        return rankInfoAndCost;
    }

    public void upgradePlayerRank(int newRank) {
        gameState.getActivePlayer().setPlayerRank(newRank);
    }

    // taking role action listener
    public List<Role> availableOnCardRoles() {
        Player currentPlayer = gameState.getActivePlayer();
        RoomWithScene currentRoom = (RoomWithScene) currentPlayer.getPlayerRoom();
        List<Role> onCardRoles = currentRoom.getSceneCard().getRoles();
        List<Role> availableRoles = new ArrayList<Role>();
        for (Role role : onCardRoles) {
            if (role.getRank() <= currentPlayer.getRank() && role.getPlayerOnRole() == null) {
                availableRoles.add(role);
            }
        }
        return availableRoles;
    }

    public List<Role> availableOffCardRoles() {
        Player currentPlayer = gameState.getActivePlayer();
        RoomWithScene currentRoom = (RoomWithScene) currentPlayer.getPlayerRoom();
        ArrayList<Role> offCardRoles = currentRoom.getOffCardRoles();
        List<Role> availableRoles = new ArrayList<Role>();
        for (Role role : offCardRoles) {
            if (role.getRank() <= currentPlayer.getRank() && role.getPlayerOnRole() == null) {
                availableRoles.add(role);
            }
        }
        return availableRoles;
    }

    public void giveRoleToPlayer(Role role) {
        gameState.getActivePlayer().setActiveRole(role);
        view.displayCurrentPlayer(gameState.getActivePlayer());
    }

    public void endTurnOption() {
        gameState.endTurn();
        view.displayCurrentPlayer(gameState.getActivePlayer());
        view.displayCurrentScene();
        view.resetRoomButtons();
    }
    // need some kind of start game method

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
}

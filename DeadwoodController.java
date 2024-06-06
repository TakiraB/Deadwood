import org.w3c.dom.Document;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DeadwoodController {
    private Board board;
    private DeadwoodView view;
    private ArrayList<Player> playerList;
    private GameState gameState;
    // private GamePieceManager pieceManager;

    // public DeadwoodController(Board board, DeadwoodView view) {
    public DeadwoodController(Board board) {
        this.board = board;
        // this.view = view;
        this.gameState = new GameState(null, playerList, 0, 0, board);
        this.playerList = new ArrayList<>();

    }

    public void setView(DeadwoodView view){
        this.view= view;
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
        ArrayList<SceneCard> cards = new ArrayList<SceneCard>();
        try {
            doc = parsingCards.getDocFromFile("cards.xml");
            cards = parsingCards.readCardData(doc);
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }
        List<Integer> numbers = new ArrayList<>();
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
    // public void startGameLoop() {
    //     // Start the game
    //     gameState.startGame();

    //     // set a variable to control the state of the game
    //     boolean activeGame = true;

    //     // Main game loop while an active game is still going
    //     while (activeGame) {
    //         // Get current active player
    //         Player activePlayer = gameState.getActivePlayer();

    //         // variable to control state of player's turn
    //         boolean playerTurnActive = true;

    //         // While it is the Player's turn
    //         // Menu of player options to choose from
    //         while (playerTurnActive) {
    //             System.out.println("Please select an action you would like to do from the following: ");
    //             System.out.println("Move");
    //             System.out.println("Act");
    //             System.out.println("Rehearse");
    //             System.out.println("Upgrade");
    //             System.out.println("Take a Role");
    //             System.out.println("Display Stats");
    //             System.out.println("Display Players");
    //             System.out.println("End");
    //             System.out.println("End Game");
    //             System.out.println("\n");
    //             Scanner userInputScanner = new Scanner(System.in);
    //             // Get the player input of their choice of interaction
    //             String playerInput = userInputScanner.nextLine().toLowerCase();

    //             // Start of the switch statement for Player choices
    //             switch (playerInput) {

    //                 // Move case
    //                 // Does a validation for adjacent rooms players can move to immediately
    //                 // Prints adjacent neighbors out based on their current room, and allows users
    //                 // to choose from those options
    //                 case "move":
    //                     // player cannot move if in a role
    //                     if (activePlayer.getRole() != null) {
    //                         // System.out.println(
    //                         // "You are currently in a Role, you cannot move until you're role is
    //                         // completed!");
    //                         view.displayGameMessage(
    //                                 "You are currently in a Role, you cannot move until your role is completed!");
    //                         break;
    //                     }
    //                     // makes sure the player has not already moved this turn
    //                     else if (activePlayer.getHasMoved()) {
    //                         // System.out.println("You have already moved, you will have to wait until your
    //                         // next turn!");
    //                         view.displayGameMessage(
    //                                 "You have already moved, you will have to wait until your next turn!");
    //                         break;
    //                     }
    //                     System.out.println("Where would you like to move to? You are currently located: "
    //                             + activePlayer.getPlayerRoom().getName());
    //                     System.out.println("\n");
    //                     System.out.println("Here are adjacent locations you can move to:");
    //                     System.out.println("\n");

    //                     for (int i = 0; i < activePlayer.getPlayerRoom().getAdjacentNeighbors().size(); i++) {
    //                         System.out.println(
    //                                 (i + 1) + " " + activePlayer.getPlayerRoom().getAdjacentNeighbors().get(i));
    //                         System.out.println("\n");
    //                     }
    //                     System.out.println("Please enter the name of the location you'd like to move to: ");
    //                     String moveToLocation = userInputScanner.nextLine();

    //                     // takes the user input and converts it to title case (First letter of each word
    //                     // is capital, everything else in lowercase)
    //                     // this is required since the names of all the places room are in title case
    //                     String[] words = moveToLocation.toLowerCase().split(" ");
    //                     StringBuilder capitalized = new StringBuilder();
    //                     for (String word : words) {
    //                         if (word.length() > 0) {
    //                             capitalized.append(Character.toUpperCase(word.charAt(0)))
    //                                     .append(word.substring(1))
    //                                     .append(" ");
    //                         }
    //                     }
    //                     moveToLocation = capitalized.toString().trim();
    //                     Room destination;

    //                     // special case for casting office
    //                     if (moveToLocation.equals("Office")) {
    //                         destination = board.getRoomFromBoard("Casting Office");
    //                     } else {
    //                         destination = board.getRoomFromBoard(moveToLocation);
    //                     }

    //                     // moves activePlayer to the destination if the destination can be moved to
    //                     if (destination != null &&
    //                             (activePlayer.getPlayerRoom().getAdjacentNeighbors().contains(destination.getName())
    //                                     || activePlayer.getPlayerRoom().getAdjacentNeighbors()
    //                                             .contains(moveToLocation))) {
    //                         // System.out.println(activePlayer.move(destination));
    //                         activePlayer.move(destination);
    //                         System.out.println("You have successfully moved to " + destination.getName());
    //                     } else {
    //                         // System.out.println("This move is not valid, either it doesn't exist or not
    //                         // adjacent.");
    //                         view.displayGameMessage("This move is not valid, either it doesn't exist or not adjacent.");
    //                     }
    //                     break;

    //                 // Act case
    //                 case "act":
    //                     if (activePlayer.getActiveRole() == null) {
    //                         // System.out.println("You have to have a role before you can act!");
    //                         view.displayGameMessage("You have to have a role before you can act!");
    //                         break;
    //                     } else if (activePlayer.getHasActed()) {
    //                         // System.out.println(
    //                         // "You have already acted or rehearse this turn, you can do either next
    //                         // turn!");
    //                         view.displayGameMessage(
    //                                 "You have already acted or rehearse this turn, you can do either next turn!");
    //                         break;
    //                     }
    //                     // activePlayer.act(board, gamePieceManager);
    //                     activePlayer.setPracticeChips(0);
    //                     int wrappedRooms = 0;
    //                     for (Room room : board.getBoardLayout().values()) {
    //                         if (room instanceof RoomWithScene) {
    //                             RoomWithScene roomWithScene = (RoomWithScene) room;
    //                             if (!roomWithScene.getRoomScene().getSceneCardActive()) {
    //                                 wrappedRooms += 1;
    //                             }
    //                         }
    //                     }
    //                     if (wrappedRooms == 9) {
    //                         // gameState.endDay(board, numbers, cards, gameState.getCurrentDayCount());
    //                         for (Player player : playerList) {
    //                             player.setActiveRole(null);
    //                             player.setHasActed(false);
    //                             player.setHasMoved(false);
    //                             player.setPlayerRoom(board.getRoomFromBoard("trailer"));
    //                             player.setPracticeChips(0);
    //                         }
    //                     }
    //                     break;

    //                 // Rehearse case
    //                 case "rehearse":
    //                     if (activePlayer.getHasActed()) {
    //                         // System.out.println(
    //                         // "You have already acted or rehearse this turn, you can do either next
    //                         // turn!");
    //                         view.displayGameMessage(
    //                                 "You have already acted or rehearsed this turn, you can do either next turn!");
    //                         break;
    //                     }
    //                     activePlayer.rehearse(board);
    //                     break;

    //                 // Upgrade case
    //                 case "upgrade":
    //                     // making sure the player is in the casting room
    //                     System.out.println(activePlayer.getPlayerRoom().getName());
    //                     if (!activePlayer.getPlayerRoom().getName().equals("Casting Office")) {
    //                         // System.out.println(
    //                         // "You are not in the Casting Office. To upgrade, please move to the Casting
    //                         // Office and try again");
    //                         view.displayGameMessage(
    //                                 "You are not in the Casting Office. To upgrade, please move to the Casting Office and try again");
    //                         break;
    //                     }

    //                     // prints out all the available options for rank upgrades
    //                     Room tempCastingOffice = board.getBoardLayout().get("Casting Office");
    //                     CastingOffice castingOfficeUpgrades = (CastingOffice) tempCastingOffice;
    //                     for (int i = 0; i < castingOfficeUpgrades.getUpgradeChoices().size(); i++) {
    //                         System.out.print("To upgrade to Rank "
    //                                 + castingOfficeUpgrades.getUpgradeChoices().get(i).getUpgradeLevel());
    //                         System.out.print(", it will cost "
    //                                 + castingOfficeUpgrades.getUpgradeChoices().get(i).getUpgradeAmount());
    //                         System.out.print(
    //                                 " " + castingOfficeUpgrades.getUpgradeChoices().get(i).getCurrencyType() + "s.");
    //                         System.out.println();
    //                     }

    //                     // asking the player was rank they would like to upgrade to and how they would
    //                     // like to pay for it
    //                     System.out.println("What rank would you like to upgrade to?");
    //                     int chosenRank = userInputScanner.nextInt();
    //                     userInputScanner.nextLine();
    //                     System.out.println("Would you like to pay using dollar or credit?");
    //                     String chosenPaymentType = userInputScanner.nextLine().toLowerCase();
    //                     int chosenUpgrades = 9999999;
    //                     for (int i = 0; i < castingOfficeUpgrades.getUpgradeChoices().size(); i++) {
    //                         if (castingOfficeUpgrades.getUpgradeChoices().get(i).getUpgradeLevel() == chosenRank &&
    //                                 castingOfficeUpgrades.getUpgradeChoices().get(i).getCurrencyType()
    //                                         .equals(chosenPaymentType)) {
    //                             chosenUpgrades = castingOfficeUpgrades.getUpgradeChoices().get(i).getUpgradeAmount();
    //                         }
    //                     }
    //                     Bank bank = new Bank();
    //                     int paymentAmount = chosenUpgrades;
    //                     System.out.println(chosenUpgrades);
    //                     if (chosenPaymentType.equals("dollar")) {
    //                         if (bank.checkDollars(activePlayer, paymentAmount)) {
    //                             bank.payDollars(activePlayer, paymentAmount);
    //                             castingOfficeUpgrades.updateRank(activePlayer, chosenRank);
    //                             // System.out.println("You have successfully upgraded to Rank " + chosenRank +
    //                             // "!");
    //                             view.displayGameMessage("You have successfully upgraded to Rank " + chosenRank + "!");
    //                         } else {
    //                             // System.out.println("You do not have enough dollars to upgrade to that rank");
    //                             view.displayGameMessage("You do not have enough dollars to upgrade to that rank.");
    //                         }
    //                     } else if (chosenPaymentType == "credit") {
    //                         if (bank.checkCredits(activePlayer, paymentAmount)) {
    //                             bank.payCredits(activePlayer, paymentAmount);
    //                             castingOfficeUpgrades.updateRank(activePlayer, chosenRank);
    //                             // System.out.println("You have successfully upgraded to Rank " + chosenRank +
    //                             // "!");
    //                             view.displayGameMessage("You have successfully upgraded to Rank " + chosenRank + "!");
    //                         } else {
    //                             // System.out.println("You do not have enough credits to upgrade to that rank");
    //                             view.displayGameMessage("You do not have enough credits to upgrade to that rank.");
    //                         }
    //                     } else {
    //                         // System.out.println(
    //                         // "You have either selected a rank or currency that exists. Please try
    //                         // again.");
    //                         view.displayGameMessage(
    //                                 "You have either selected a rank or currency that doesn't exist. Please try again.");
    //                     }
    //                     break;

    //                 // TODO: Taking a Role use case logic
    //                 // Probably will need a list of roles based on current room, validation for
    //                 // being in a room with roles, etc.
    //                 case "take a role":
    //                     if (activePlayer.getActiveRole() != null) {
    //                         // System.out.println("You already have a role!");
    //                         view.displayGameMessage("You already have a role!");
    //                         break;
    //                     } else if (!(activePlayer.getPlayerRoom() instanceof RoomWithScene)) {
    //                         // System.out.println("You are not in a room with roles!");
    //                         view.displayGameMessage("You are not in a room with roles!");
    //                         break;
    //                     }

    //                     RoomWithScene activePlayerLocation = (RoomWithScene) activePlayer.getPlayerRoom();
    //                     ArrayList<Role> offCardRoles = activePlayerLocation.getOffCardRoles();
    //                     List<Role> onCardRoles = activePlayerLocation.getSceneCard().getRoles();
    //                     System.out.println();
    //                     System.out.println("The off card roles available are:");
    //                     for (Role role : offCardRoles) {
    //                         if (activePlayer.getRank() >= role.getRank()) {
    //                             System.out.println("Rank " + role.getRank() + ": " + role.getRoleName());
    //                         }
    //                     }
    //                     System.out.println();
    //                     System.out.println("The on card roles available are:");
    //                     for (Role role : onCardRoles) {
    //                         if (activePlayer.getRank() >= role.getRank()) {
    //                             System.out.println("Rank " + role.getRank() + ": " + role.getRoleName());
    //                         }
    //                     }
    //                     System.out.println();

    //                     System.out.println("Would you like to take a role? (yes or no)");
    //                     String takeRoleInput = userInputScanner.nextLine();
    //                     if (takeRoleInput.equals("yes")) {
    //                         boolean didNotGetRole = true;
    //                         while (didNotGetRole) {
    //                             System.out.println("What role would you like to take?");
    //                             String roleInput = userInputScanner.nextLine();
    //                             if (roleInput.equals("none")) {
    //                                 didNotGetRole = false;
    //                                 break;
    //                             }
    //                             for (Role role : offCardRoles) {
    //                                 if (role.getRoleName().toLowerCase().equals(roleInput.toLowerCase())) {
    //                                     if (role.getPlayerOnRole() == null) {
    //                                         activePlayer.setActiveRole(role);
    //                                         role.setPlayerOnRole(activePlayer);
    //                                         didNotGetRole = false;
    //                                         System.out.println("You got " + activePlayer.getRole().getRoleName());
    //                                         break;
    //                                     } else {
    //                                         // System.out.println(
    //                                         // "There is a player already in that role, take a different role or type
    //                                         // none if you no longer want to take a role.");
    //                                         view.displayGameMessage("There is a player already in that role.");
    //                                     }

    //                                 }
    //                             }
    //                             for (Role role : onCardRoles) {
    //                                 if (role.getRoleName().toLowerCase().equals(roleInput.toLowerCase())) {
    //                                     activePlayer.setActiveRole(role);
    //                                     role.setPlayerOnRole(activePlayer);
    //                                     didNotGetRole = false;
    //                                     System.out.println("You got " + activePlayer.getRole().getRoleName());
    //                                     break;
    //                                 }
    //                             }
    //                             if (didNotGetRole) {
    //                                 // System.out.println("You did not input a valid role please try again.");
    //                                 view.displayGameMessage("You did not input a valid role, please try again.");
    //                             }
    //                         }
    //                     } else {
    //                         System.out.println("Please use take a role again if you would like to take a role.");
    //                     }
    //                     break;

    //                 // Display current player stats
    //                 case "display stats":
    //                     System.out.println("Here is your current stats: ");
    //                     System.out.println("Name: " + activePlayer.getName());
    //                     System.out.println("Rank: " + activePlayer.getRank());
    //                     System.out.println("Dollars: " + activePlayer.getDollars());
    //                     System.out.println("Credits: " + activePlayer.getCredits());
    //                     if (activePlayer.getRole() != null) {
    //                         System.out.println("Active Role: " + activePlayer.getRole().getRoleName());
    //                     } else {
    //                         System.out.println("Active Role: No Role");
    //                     }

    //                     System.out.println("Practice Chips: " + activePlayer.getPracticeChips());
    //                     System.out.println("Current Room: " + activePlayer.getPlayerRoom().getName());
    //                     String allAdj = "";
    //                     for (String str : activePlayer.getPlayerRoom().getAdjacentNeighbors()) {
    //                         if (!allAdj.isEmpty()) {
    //                             allAdj += ", ";
    //                         }
    //                         allAdj += str;
    //                     }
    //                     System.out.println("Adjacent Rooms: " + allAdj);
    //                     System.out.println();
    //                     break;

    //                 // displays all players locations
    //                 case "display players":
    //                     System.out.println("Here is where all players are located:");
    //                     for (Player player : playerList) {
    //                         if (player == activePlayer) {
    //                             System.out.println("Active Player: " + player.getName() + " is located at "
    //                                     + player.getPlayerRoom().getName());
    //                         } else {
    //                             System.out.println(
    //                                     player.getName() + " is located at " + player.getPlayerRoom().getName());
    //                         }

    //                     }
    //                     System.out.println();
    //                     break;

    //                 // Player doesn't have to do anything on their turn, end turn
    //                 case "end":
    //                     playerTurnActive = false;
    //                     gameState.endTurn();
    //                     break;

    //                 // end game case
    //                 case "end game":
    //                     gameState.endGame();
    //                     activeGame = false;
    //                     playerTurnActive = false;

    //                     // Default to break out of switch statement
    //                 default:
    //                     break;
    //             }
    //         }
    //     }
    // }

    // move action listener
    // Move case
    public void moveOption(){

        Player activePlayer = gameState.getActivePlayer();
        // System.out.println("Move option triggered for player: " + activePlayer.getName());

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
    }

    public void performMove(String neighborName){
        Player activePlayer = gameState.getActivePlayer();

        if(activePlayer.getHasMoved()){
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
        view.textAction.append("You rolled a " + rollValue + " with " + pChips + "practice chips for a total of " + total);
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

    public void fail() {
        Player currentPlayer = gameState.getActivePlayer();
        if (!inStarredRole()) {
            int dollars = currentPlayer.getDollars();
            currentPlayer.setDollars(dollars + 1);
        } else {
            // do nothing since on card players get nothing for failing during act
        }
    }

    // TODO: add logic for wrapping scene
    public boolean wrapSceneCheck() {
        






        return true;
    }



    // TODO: add logic for ending the day



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
        // the order is rank 2 dollar, rank 2 credit, rank 3 dollar, rank 3 credit... rank 6 dollar, rank 6 credit,
        // first value in object: 0 or 1 if the player can upgrade to that rank
        // second value in object: String, rank *rank* costs *amount* (dollars or credits)
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
            // checking to make sure player is not too high of a level or does not have enough credits
            if (currentPlayer.getDollars() >= amountRequired && currentPlayer.getRank() < rank) {
                tempArray.add(1);
            } else {
                tempArray.add(0);
            }
            tempArray.add("Rank " + currentUpgrade.getUpgradeLevel() + " costs " + currentUpgrade.getUpgradeAmount() + " " + currentUpgrade.getCurrencyType() + "s");
            rankInfoAndCost.set(temp, tempArray);
        }
        
        // Adds values to rankInfoAndCost for all the credit upgrades
        for (int i = 5; i < 10; i++) {
            Upgrades currentUpgrade = castingOfficeUpgrades.getUpgradeChoices().get(i);
            int rank = currentUpgrade.getUpgradeLevel();
            int amountRequired = currentUpgrade.getUpgradeAmount();
            int temp = ((i - 5) * 2) + 1;
            ArrayList<Object> tempArray = new ArrayList<Object>();
            // checking to make sure player is not too high of a level or does not have enough credits
            if (currentPlayer.getCredits() >= amountRequired && currentPlayer.getRank() < rank) {
                tempArray.add(1);
            } else {
                tempArray.add(0);
            }
            tempArray.add("Rank " + currentUpgrade.getUpgradeLevel() + " costs " + currentUpgrade.getUpgradeAmount() + " " + currentUpgrade.getCurrencyType() + "s");
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
        for (Role role: onCardRoles) {
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
        for (Role role: offCardRoles) {
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
    // for(Player activePlayer : playerList){
    //     activePlayer.setHasMoved(false);
    // }
    Player activePlayer = gameState.getActivePlayer();
    // activePlayer.setHasMoved(false);
    gameState.endTurn();
    view.displayCurrentPlayer(gameState.getActivePlayer());
    }
    // need some kind of start game method



    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
}

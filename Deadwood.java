import org.w3c.dom.Document;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Deadwood {
    public static void main(String args[]) {
        // creation of board
        Document doc = null;
        parseBoard parsingBoard = new parseBoard();
        Board board = new Board();
        try {
            doc = parsingBoard.getDocFromFile("xml/board.xml");
            board = parsingBoard.readBoardData(doc);
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }

        parseCards parsingCards = new parseCards();
        try{
            doc = parsingCards.getDocFromFile("cards.xml");
            ArrayList<SceneCard> cards = parsingCards.readCardData(doc);
        //    parsing.readCardData(doc);
        }catch (Exception e){
           System.out.println("Error = "+e);
        }

        // prepping the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);
        System.out.println("Welcome to Deadwood!");
        System.out.println("We hope you have a great time playing!");

        // asking the user how many people are playing
        boolean isValidInput = false;
        int playerCount = 0;
        // checking if the input from the player is a valid number between 2-8
        while (!isValidInput) {
            System.out.println("How many players are you playing with? (2-8 players are required) ");
            String userInput = userInputScanner.nextLine();

            // checking if the input can be made an int
            try {
                playerCount = Integer.parseInt(userInput);
                // checking if the integer in put is between 2 and 8
                if (1 < playerCount && playerCount < 9) {
                    isValidInput = true;
                } else {
                    System.out.println(
                            "That is not a number of players that we can handle, please put in a number between 2 and 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("That is not a number, please put in a number between 2 and 8.");
                System.out.println("\n");
            }
        }

        // gives each player a name sets all their other properties to 0/null
        ArrayList<Player> playerList = new ArrayList<Player>();
        for (int i = 0; i < playerCount; i++) {
            System.out.println("What is the name for Player " + (i + 1) + "?");
            String playerName = userInputScanner.nextLine();

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

        // creation of GameState with the first activePlayer being randomized
        Random random = new Random();
        int firstPlayerInt = random.nextInt(playerCount);
        // GameState gameState = new GameState(playerList.get(firstPlayerInt),
        // playerList, 1, board);

        // Initialize the GameState
        GameState gameState;

        // Setting up the max number of days in the game based on the number of players
        // 3 if 2 players, and 4 for everything else
        if (playerCount < 3) {
            gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, 3, board);
        } else {
            gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, 4, board);
        }

        // Start the game
        gameState.startGame();

        // set a variable to control the state of the game
        boolean activeGame = true;

        // Main game loop while an active game is still going
        while (activeGame) {
            // Get current active player
            Player activePlayer = gameState.getActivePlayer();

            // variable to control state of player's turn
            boolean playerTurnActive = true;

            // While it is the Player's turn
            // Menu of player options to choose from
            while (playerTurnActive) {
                System.out.println("Please select an action you would like to do from the following: ");
                System.out.println("Move");
                System.out.println("Act");
                System.out.println("Rehearse");
                System.out.println("Upgrade");
                System.out.println("Take Role");
                System.out.println("Display");
                System.out.println("End");
                System.out.println("\n");

                // Get the player input of their choice of interaction
                String playerInput = userInputScanner.nextLine().toLowerCase();

                // Start of the switch statement for Player choices
                switch (playerInput) {

                    // Move case
                    // Does a validation for adjacent rooms players can move to immediately
                    // Prints adjacent neighbors out based on their current room, and allows users
                    // to choose from those options
                    case "move":
                        // makes sure the player has not already moved this turn
                        if (activePlayer.getHasMoved()) {
                            System.out.println("You have already moved, you will have to wait until your next turn!");
                            break;
                        }
                        System.out.println("Where would you like to move to? You are currently located: "
                                + activePlayer.getPlayerRoom().getName());
                        System.out.println("\n");
                        System.out.println("Here are adjacent locations you can move to:");
                        System.out.println("\n");

                        for (int i = 0; i < activePlayer.getPlayerRoom().getAdjacentNeighbors().size(); i++) {
                            System.out.println(
                                    (i + 1) + " " + activePlayer.getPlayerRoom().getAdjacentNeighbors().get(i));
                            System.out.println("\n");
                        }
                        System.out.println("Please enter the name of the location you'd like to move to: ");
                        String moveToLocation = userInputScanner.nextLine();

                        // takes the user input and converts it to title case (First letter of each word
                        // is capital, everything else in lowercase)
                        // this is required since the names of all the places room are in title case
                        // TODO: check to make sure all titles are this way otherwise it will not work
                        String[] words = moveToLocation.toLowerCase().split(" ");
                        StringBuilder capitalized = new StringBuilder();
                        for (String word : words) {
                            if (word.length() > 0) {
                                capitalized.append(Character.toUpperCase(word.charAt(0)))
                                        .append(word.substring(1))
                                        .append(" ");
                            }
                        }
                        moveToLocation = capitalized.toString().trim();
                        Room destination;

                        // special case for casting office
                        if (moveToLocation.equals("Office")) {
                            destination = board.getRoomFromBoard("Casting Office");
                        } else {
                            destination = board.getRoomFromBoard(moveToLocation);
                        }

                        if (destination != null && 
                                (activePlayer.getPlayerRoom().getAdjacentNeighbors().contains(destination.getName()) 
                                || activePlayer.getPlayerRoom().getAdjacentNeighbors().contains(moveToLocation))) {
                            System.out.println(activePlayer.move(destination)); // TODO: This doesnt work for casting office, idk why every other place works
                            System.out.println("You have successfully moved to " + destination.getName());
                        } else {
                            System.out.println("This move is not valid, either it doesn't exist or not adjacent.");
                        }
                        break;

                    // Act case
                    // Not sure if we want to keep practice chips as a parameter for acting
                    case "act":
                        if (activePlayer.getActiveRole() == null) {
                            System.out.println("You have to have a role before you can act!");
                            break;
                        } else if (activePlayer.getHasActed()) {
                            System.out.println("You have already acted, you can act again next turn!");
                        }
                        activePlayer.act();
                        break;

                    // Rehearse case
                    // This should be done
                    case "rehearse":
                        activePlayer.rehearse();
                        break;

                    // TODO: logic for upgrade switch statement + upgrade method in Player 
                    // This should be completed
                    case "upgrade":
                        // making sure the player is in the casting room
                        System.out.println(activePlayer.getPlayerRoom().getName());
                        if (!activePlayer.getPlayerRoom().getName().equals("Casting Office")) {
                            System.out.println(
                                    "You are not in the Casting Office. To upgrade, please move to the Casting Office and try again");
                            break;
                        }

                        // have to cast the Room Casting Office to class castingOfficeUpgrades to allows for .getUpgradeChoices to be called
                        Room tempCastingOffice = board.getBoardLayout().get("Casting Office");
                        CastingOffice castingOfficeUpgrades = (CastingOffice) tempCastingOffice;
                        for (int i = 0; i  < castingOfficeUpgrades.getUpgradeChoices().size(); i++){
                            System.out.print("To upgrade to Rank " + castingOfficeUpgrades.getUpgradeChoices().get(i).getUpgradeLevel());
                            System.out.print(", it will cost " + castingOfficeUpgrades.getUpgradeChoices().get(i).getUpgradeAmount());
                            System.out.print(" " + castingOfficeUpgrades.getUpgradeChoices().get(i).getCurrencyType() + "s.");
                            System.out.println();
                        }
                        System.out.println("What rank would you like to upgrade to?");
                        int chosenRank = userInputScanner.nextInt();
                        userInputScanner.nextLine();
                        System.out.println("Would you like to pay using dollar or credit?");
                        String chosenPaymentType = userInputScanner.nextLine().toLowerCase();
                        int chosenUpgrades = 9999999;
                        for (int i = 0; i  < castingOfficeUpgrades.getUpgradeChoices().size(); i++){
                            if (castingOfficeUpgrades.getUpgradeChoices().get(i).getUpgradeLevel() == chosenRank && 
                                    castingOfficeUpgrades.getUpgradeChoices().get(i).getCurrencyType().equals(chosenPaymentType)) {
                                chosenUpgrades = castingOfficeUpgrades.getUpgradeChoices().get(i).getUpgradeAmount();
                            }
                        }
                        Bank bank = new Bank();
                        int paymentAmount = chosenUpgrades;
                        System.out.println(chosenUpgrades);
                        if (chosenPaymentType.equals("dollar")) {
                            if (bank.checkDollars(activePlayer, paymentAmount)) {
                                bank.payDollars(activePlayer, paymentAmount);
                                castingOfficeUpgrades.updateRank(activePlayer, chosenRank);
                                System.out.println("You have successfully upgraded to Rank " + chosenRank + "!");
                            } else {
                                System.out.println("You do not have enough dollars to upgrade to that rank");
                            }
                        } else if (chosenPaymentType == "credit") {
                            if (bank.checkCredits(activePlayer, paymentAmount)) {
                                bank.payCredits(activePlayer, paymentAmount);
                                castingOfficeUpgrades.updateRank(activePlayer, chosenRank);
                                System.out.println("You have successfully upgraded to Rank " + chosenRank + "!");
                            } else {
                                System.out.println("You do not have enough credits to upgrade to that rank");
                            }
                        } else {
                            System.out.println("You have either selected a rank or currency that exists. Please try again.");
                        }
                        break;

                    // TODO: Taking a Role use case logic
                    // Probably will need a list of roles based on current room, validation for
                    // being in a room with roles, etc.
                    case "take a role":
                        if (activePlayer.getActiveRole() != null) {
                            System.out.println("You already have a role!");
                            break;
                        }
                        System.out.println("Work in progress!");
                        break;

                    // Display current player stats
                    // TODO: Figure out how to display String version of the room, probably need to
                    // access Board's Hashmap
                    case "display":
                        System.out.println("Here is your current stats: ");
                        System.out.println("Name: " + activePlayer.getName());
                        System.out.println("Rank: " + activePlayer.getRank());
                        System.out.println("Dollars: " + activePlayer.getDollars());
                        System.out.println("Credits: " + activePlayer.getCredits());
                        System.out.println("Active Role: " + activePlayer.getRole());
                        System.out.println("Practice Chips: " + activePlayer.getPracticeChips());
                        System.out.println("Current Room: " + activePlayer.getPlayerRoom().getName());
                        String allAdj = "";
                        for (String str: activePlayer.getPlayerRoom().getAdjacentNeighbors()) {
                            if (!allAdj.isEmpty()) {
                                allAdj += ", ";
                            }
                            allAdj += str;
                        }
                        System.out.println("Adjacent Rooms: " + allAdj);
                        System.out.println("\n");
                        break;

                    // Player doesn't have to do anything on their turn, end turn
                    case "end":
                        playerTurnActive = false;
                        gameState.endTurn();
                        break;

                    // Default to break out of switch statement (this could be the end turn case)
                    default:
                        break;
                }
            }
        }

        // Set flag of ongoing game to false (indicating game over)
        activeGame = false;
        // random testing
        // System.out.println(gameState.getActivePlayer().getName());

        gameState.endTurn();
        // System.out.println(gameState.getActivePlayer().getName());

        gameState.endGame();


        // closes scanner since vscode was yelling at me
        userInputScanner.close();
    }
}

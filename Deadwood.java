import org.w3c.dom.Document;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Deadwood {
    public static void main(String args[]) {
        // creation of board
        Document doc = null;
        parseBoard parsing = new parseBoard();
        Board board = new Board();
        try {
            doc = parsing.getDocFromFile("board.xml");
            board = parsing.readBoardData(doc);
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }

        // prepping the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);
        System.out.println("Welcome to Deadwood!");
        System.out.println("We hope you have a great time playing!");

        // asking the user how many people are playing
        boolean isValidInput = false;
        int playerCount = 0;
        // checking if the input from the player is a valid number between 2-8
        while(!isValidInput) {
            System.out.println("How many players are you playing with? (2-8 players are required) ");
            String userInput = userInputScanner.nextLine();

            // checking if the input can be made an int
            try {
                playerCount = Integer.parseInt(userInput);
                // checking if the integer in put is between 2 and 8
                if (1 < playerCount && playerCount < 9) {
                    isValidInput = true;
                } else {
                    System.out.println("That is not a number of players that we can handle, please put in a number between 2 and 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("That is not a number, please put in a number between 2 and 8.");
                System.out.println("\n");
            }
        }

        // gives each player a name sets all their other properties to 0/null
        ArrayList<Player> playerList = new ArrayList<Player>();
        for (int i = 0; i < playerCount; i++) {
            System.out.println("What is the name for Player " + (i+1) + "?");
            String playerName = userInputScanner.nextLine();

            // Initialize Player object for if statement
            Player tempPlayer;

            // Setting up Player starting room, setting up credits based on number of players per rules
            if (playerCount == 5){
                tempPlayer = new Player(playerName, 1, 0, 2, null, 0, board.getRoomFromBoard("Trailer"));
            }
            else if (playerCount == 6) {
                tempPlayer = new Player(playerName, 1, 0, 4, null, 0, board.getRoomFromBoard("Trailer"));
            }
            else if (playerCount == 7 || playerCount == 8){
                tempPlayer = new Player(playerName, 2, 0, 0, null, 0,board.getRoomFromBoard("Trailer"));
            }
            else{
                tempPlayer = new Player(playerName, 1, 0, 0, null, 0, board.getRoomFromBoard("Trailer"));
            }
            // Add player to list of active players
            playerList.add(tempPlayer);
        }
        
        // creation of GameState with the first activePlayer being randomized
        Random random = new Random();
        int firstPlayerInt = random.nextInt(playerCount);
        // GameState gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, board);

        // Initialize the GameState
        GameState gameState;

        // Setting up the max number of days in the game based on the number of players
        // 3 if 2 players, and 4 for everything else
        if(playerCount < 3){
            gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, 3, board);
        }
        else {
            gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, 4, board); 
        }

        // Start the game
        gameState.startGame();

        // set a variable to control the state of the game
        boolean activeGame = true;

        // Main game loop while an active game is still going
        while(activeGame){
            // Get current active player
            Player activePlayer = gameState.getActivePlayer();

            // variable to control state of player's turn
            boolean playerTurnActive = true;

            // While it is the Player's turn
            // Menu of player options to choose from
            while(playerTurnActive){
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
            switch(playerInput) {

                // Move case
                // Does a validation for adjacent rooms players can move to immediately
                // Prints adjacent neighbors out based on their current room, and allows users to choose from those options
                // TODO: Figure out why user input is not being recongnized as a valid room (always goes to else block)
                case "move":
                    System.out.println("Where would you like to move to? You are currently located at the: " + activePlayer.getPlayerRoom());
                    System.out.println("\n");
                    System.out.println("Here are adjacent locations you can move to: ");
                    System.out.println("\n");

                    for (int i=0;i<activePlayer.getPlayerRoom().getAdjacentNeighbors().size(); i++){
                        System.out.println((i+1) + " " + activePlayer.getPlayerRoom().getAdjacentNeighbors().get(i));
                        System.out.println("\n");
                    }
                    System.out.println("Please enter the name of the location you'd like to move to: ");
                    String moveToLocation = userInputScanner.nextLine().toLowerCase();
                    Room destination = board.getRoomFromBoard(moveToLocation);
                    if(destination != null && activePlayer.getPlayerRoom().getAdjacentNeighbors().contains(destination.getName().toLowerCase())) {
                        activePlayer.move(destination);
                    }
                    else {
                        System.out.println("This move is not valid, either it doesn't exist or not adjacent.");
                    }
                    break;

                // Act case
                // Not sure if we want to keep practice chips as a parameter for acting
                case "act":
                    activePlayer.act(activePlayer.getPracticeChips());
                    break;
                
                // Rehearse case
                case "rehearse":
                    activePlayer.rehearse();
                    break;
                
                // TODO: logic for upgrade switch statement + upgrade method in Player
                case "upgrade":
                    System.out.println("Work in progress!");
                    break;
                
                // TODO: Taking a Role use case logic
                // Probably will need a list of roles based on current room, validation for being in a room with roles, etc.
                case "take a role":
                    System.out.println("Work in progress!");
                    break;
                
                // Display current player stats
                // TODO: Figure out how to display String version of the room, probably need to access Board's Hashmap
                case "display":
                    System.out.println("Here is your current stats: ");
                    System.out.println("Name: " + activePlayer.getName());
                    System.out.println("Rank: " + activePlayer.getRank());
                    System.out.println("Dollars: " + activePlayer.getDollars());
                    System.out.println("Credits: " + activePlayer.getCredits());
                    System.out.println("Active Role: " + activePlayer.getRole());
                    System.out.println("Practice Chips: " + activePlayer.getPracticeChips());
                    System.out.println("Current Room: " + activePlayer.getPlayerRoom());
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

        // closes scanner since vscode was yelling at me
        userInputScanner.close();
    }
}

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
            doc = parsing.getDocFromFile("xml/board.xml");
            board = parsing.readBoardData(doc);
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }

        // random testing for board import
        System.err.println(board.getBoardLayout().get("trailer").getAdjacentNeighbors());

        // prepping the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);
        System.out.println("Welcome to Deadwood!");
        System.out.println("We hope you have a great time playing!");

        // asking the user how many people are playing
        boolean isValidInput = false;
        int playerCount = 0;
        // checking if the input from the player is a valid number between 2-6
        while(!isValidInput) {
            System.out.println("How many players are you playing with? (2-6 players are required) ");
            String userInput = userInputScanner.nextLine();

            // checking if the input can be made an int
            try {
                playerCount = Integer.parseInt(userInput);
                // checking if the integer in put is between 2 and 6
                if (1 < playerCount && playerCount < 7) {
                    isValidInput = true;
                } else {
                    System.out.println("That is not a number of players that we can handle please put in a number between 2 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("That is not a number, please put in a number between 2 and 6.");
            }
        }

        // gives each player a name sets all their other properties to 0/null
        ArrayList<Player> playerList = new ArrayList<Player>();
        for (int i = 0; i < playerCount; i++) {
            System.out.println("What is the name for Player " + i + "?");
            String playerName = userInputScanner.nextLine();
            Player tempPlayer = new Player(playerName, 0, 0, 0, null, 0);
            playerList.add(tempPlayer);
        }
        
        // creation of GameState with the first activePlayer being randomized
        Random random = new Random();
        int firstPlayerInt = random.nextInt(playerCount);
        GameState gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, board);

        // random testing
        System.out.println(gameState.getActivePlayer().getName());
        gameState.endTurn();
        System.out.println(gameState.getActivePlayer().getName());





        // closes scanner since vscode was yelling at me
        userInputScanner.close();
    }
}

import org.w3c.dom.Document;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class DeadwoodController {
    private Board board;
    private DeadwoodView view;
    ArrayList<Player> playerList = new ArrayList<Player>();
    private GameState gameState;
    private GamePieceManager pieceManager;

    public DeadwoodController(Board board, DeadwoodView view){
        this.board = board;
        this.view = view;
        this.playerList = new ArrayList<>();

    }

    // initialize game class (maybe include initialization of players too? or just the scene cards)

    // setPlayersClass (may get absorbed into method above, or set the player count)
    public void setActivePlayers(int playerCount){
    // if (playerCount < 2 || playerCount > 8) {
    //     JOptionPane.showMessageDialog(view,"That's not an appropriate amount of players! Sorry! :(");
    // }
    
    for (int i = 0; i < playerCount; i++) {
        String playerName = JOptionPane.showInputDialog("What is the name for Player " + (i + 1) + "?");
        // Initialize Player object for if statement
        Player tempPlayer;
        // Setting up Player starting room, setting up credits based on number of players per rules
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
}


    // act class

    // rehearse class

    // Listener stuff
}




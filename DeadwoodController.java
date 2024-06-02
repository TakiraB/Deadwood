import org.w3c.dom.Document;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class DeadwoodController {
    private Board board;
    // private DeadwoodView view;
    ArrayList<Player> playerList = new ArrayList<Player>();
    private GameState gameState;
    private GamePieceManager pieceManager;

    // public DeadwoodController(Board board, DeadwoodView view) {
    public DeadwoodController(Board board) {
        this.board = board;
        // this.view = view;
        this.gameState = new GameState(null, playerList, 0, 0, board);
        this.playerList = new ArrayList<>();

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
        // view.setPlayerIcons(playerCount);
    }

    // // creation of GameState with the first activePlayer being randomized
    // Random random = new Random();
    // int firstPlayerInt = random.nextInt(playerCount);
    // // Initialize the GameState
    // GameState gameState;

    // // Setting up the max number of days in the game based on the number of
    // players
    // // 3 if 2 players, and 4 for everything else
    // if (playerCount < 3) {
    // gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, 3,
    // board);
    // } else {
    // gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, 4,
    // board);
    // }

    // // Start the game
    // gameState.startGame();

    // initialize the game state
    public void setGameState(int numPlayers) {
        Random random = new Random();
        int firstPlayerInt = random.nextInt(numPlayers);

        // Setting up the max number of days in the game based on the number of players
        // 3 if 2 players, and 4 for everything else
        if (numPlayers < 3) {
            // gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, 3, board);
            gameState.setActivePlayer(playerList.get(firstPlayerInt));
            gameState.setPlayers(playerList);
            gameState.setCurrentDayCount(1);
            gameState.setMaxDayCount(3);
            gameState.setBoard(board);
        } else {
            // gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, 4, board);
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

    // act action listener

    // rehearse action listener

    // upgrade action listener

    // taking role action listener

    // displaying stats action listener

    // display players action listener

    // need some kind of start game method

}

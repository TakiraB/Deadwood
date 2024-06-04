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
    }

    // initialize the game state
    public void setGameState(int numPlayers) {
        Random random = new Random();
        int firstPlayerInt = random.nextInt(numPlayers);

        // Setting up the max number of days in the game based on the number of players
        // 3 if 2 players, and 4 for everything else
        if (numPlayers < 3) {
            // gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, 3,
            // board);
            gameState.setActivePlayer(playerList.get(firstPlayerInt));
            gameState.setPlayers(playerList);
            gameState.setCurrentDayCount(1);
            gameState.setMaxDayCount(3);
            gameState.setBoard(board);
        } else {
            // gameState = new GameState(playerList.get(firstPlayerInt), playerList, 1, 4,
            // board);
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
    public ArrayList<Integer> availableUpgrades() {
        Player currentPlayer = gameState.getActivePlayer();
        Room tempCastingOffice = board.getBoardLayout().get("Casting Office");
        CastingOffice castingOfficeUpgrades = (CastingOffice) tempCastingOffice;
        ArrayList<Integer> canUpgrade = new ArrayList<>();
        for (int i = 0; i < castingOfficeUpgrades.getUpgradeChoices().size(); i++) {
            canUpgrade.add(0);
        }
        for (int i = 0; i < 5; i++) {
            Upgrades currentUpgrade = castingOfficeUpgrades.getUpgradeChoices().get(i);
            int rank = currentUpgrade.getUpgradeLevel();
            int amountRequired = currentUpgrade.getUpgradeAmount();
            int temp = i * 2;
            System.out.println("current dollars " + currentPlayer.getCredits());
            System.out.println("required dollars " + amountRequired);
            System.out.println("current player rank " + currentPlayer.getRank());
            System.out.println("upgrade rank " + rank);
            if (currentPlayer.getDollars() >= amountRequired && currentPlayer.getRank() < rank) {
                canUpgrade.set(temp, 1);
            } else {
                canUpgrade.set(temp, 0);
            }
            System.out.println(temp + " is set to " + canUpgrade.get(temp));
            System.out.println("=================");
        }
        for (int i = 5; i < 10; i++) {
            Upgrades currentUpgrade = castingOfficeUpgrades.getUpgradeChoices().get(i);
            int rank = currentUpgrade.getUpgradeLevel();
            int amountRequired = currentUpgrade.getUpgradeAmount();
            int temp = ((i - 5) * 2) + 1;
            System.out.println("current credits " + currentPlayer.getCredits());
            System.out.println("required credit " + amountRequired);
            System.out.println("current player rank " + currentPlayer.getRank());
            System.out.println("upgrade rank " + rank);
            if (currentPlayer.getCredits() >= amountRequired && currentPlayer.getRank() < rank) {
                canUpgrade.set(temp, 1);
            } else {
                canUpgrade.set(temp, 0);
            }
            System.out.println(temp + " is set to " + canUpgrade.get(temp));
            System.out.println("=================");
        }
        return canUpgrade;
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
    }
    // displaying stats action listener

    // display players action listener

    // need some kind of start game method

}

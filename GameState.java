import java.util.*;

public class GameState {
    private Player activePlayer;
    private ArrayList<Player> players;
    private int dayCount;
    private Board board;

    public GameState(Player activePlayer, ArrayList<Player> players, int dayCount, Board board) {
        this.activePlayer = activePlayer;
        this.players = players;
        this.dayCount = dayCount;
        this.board = board;
    }

    // does everything for the game to start
    public void startGame() {

    }

    // does everything for when the game is supposed to end
    public boolean endGame() {
        return true;
    }

    // starts the turn of the next active player
    public void startTurn() {

    }

    // ends the turns of the active player
    public void endTurn() {
        int indexOfActivePlayer = players.indexOf(activePlayer); // finds the index of the active player in the player array

        // sets the next player in the array to the active player, if there is no next player then start from the 0 index player instead
        if ((indexOfActivePlayer + 1) != players.size()) {
            activePlayer = players.get(indexOfActivePlayer + 1);
        } else {
            activePlayer = players.get(0);
        }

        // do other things if needed here, before starting the next turn

        // starts the next turn 
        startTurn();
    }

    // does everything that happens at the end of the day
    public boolean endDay(Board board) {
        this.dayCount += 1;
        // do the rest of the end of day stuff here
        board.resetBoardLayout();
        return true;
    }

    // getters and setters


    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}

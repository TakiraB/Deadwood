import java.util.*;

public class GameState {
    private Player activePlayer;
    private List<Player> players;
    private int dayCount;
    private String currentPhase;
    private Board board;

    public GameState(Player activePlayer, List<Player> players, int dayCount, String currentPhase, Board board) {
        this.activePlayer = activePlayer;
        this.players = players;
        this.dayCount = dayCount;
        this.currentPhase = currentPhase;
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
    public boolean endTurn() {
        return true;
    }

    // does everything that happens at the end of the day
    public boolean endDay() {
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

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public String getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(String currentPhase) {
        this.currentPhase = currentPhase;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}

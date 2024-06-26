import java.util.*;

public class GameState {
    private Player activePlayer;
    private ArrayList<Player> players;
    private int currentDayCount;
    private int maxDayCount;
    private Board board;

    public GameState(Player activePlayer, ArrayList<Player> players, int currentDayCount, int maxDayCount,
            Board board) {
        this.activePlayer = activePlayer;
        this.players = players;
        this.currentDayCount = currentDayCount;
        this.maxDayCount = maxDayCount;
        this.board = board;
    }

    // does everything for the game to start
    public void startGame() {
        System.out.println("Deadwood game is starting... NOW!");
    }

    // does everything for when the game is supposed to end
    public void endGame() {
        System.out.println("The game of Deadwood has ended! Woohoo!");
        System.out.println("Time to find out who won!");
        ArrayList<Integer> playerPointTotals = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            Player totallingPlayer = players.get(i);
            Integer total = totallingPlayer.getDollars();
            total = total + totallingPlayer.getCredits();
            total = total + (5 * totallingPlayer.getRank());
            playerPointTotals.add(total);
        }

        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < playerPointTotals.size(); i++) {
            indexes.add(i);
        }
        Collections.sort(indexes, Comparator.comparingInt(playerPointTotals::get).reversed());

        int place = 0;
        String[] places = { "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth" };
        for (Integer index : indexes) {
            System.err.println(players.get(index).getName() + " got " + places[place] + " with "
                    + playerPointTotals.get(index) + " points.");
            place++;
        }
    }

    // starts the turn of the next active player
    public void startTurn() {

    }

    // ends the turns of the active player
    public void endTurn() {
        // so that player can act on their next turn
        activePlayer.setHasActed(false);
        activePlayer.setHasMoved(false);
        // finds the index of the active player in the player array
        int indexOfActivePlayer = players.indexOf(activePlayer);

        // sets the next player in the array to the active player, if there is no next
        // player then start from the 0 index player instead
        if ((indexOfActivePlayer + 1) != players.size()) {
            activePlayer = players.get(indexOfActivePlayer + 1);
        } else {
            activePlayer = players.get(0);
        }

        // do other things if needed here, before starting the next turn
        System.out.println("\nIt is now " + activePlayer.getName() + "'s turn!\n");
        // starts the next turn
        startTurn();
    }

    // does everything that happens at the end of the day
    public boolean endDay(Board board, List<Integer> numbers, ArrayList<SceneCard> cards, int day) {
        this.currentDayCount += 1;
        // do the rest of the end of day stuff here
        board.sceneCardDistribution(numbers, cards, currentDayCount);
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

    public int getCurrentDayCount() {
        return currentDayCount;
    }

    public void setCurrentDayCount(int dayCount) {
        this.currentDayCount = dayCount;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getMaxDayCount() {
        return maxDayCount;
    }

    public void setMaxDayCount(int maxDayCount) {
        this.maxDayCount = maxDayCount;
    }
}

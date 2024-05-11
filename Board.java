import java.util.*;

public class Board {
    private List<Room> boardLayout;
    private int activeScenes;

    public Board(List<Room> boardLayout, int activeScenes) {
        this.boardLayout = boardLayout;
        this.activeScenes = activeScenes;
    }

    // updates the Board
    public void updateBoard() {

    }

    // finds all adjacent rooms to room and returns in list
    public List<Room> getAdjacent(Room room) {
        List<Room> list = new ArrayList<Room>();
        return list;
    }

    // resets board
    public boolean resetBoard() {
        return true;
    }

    // getters and setters


    public List<Room> getBoardLayout() {
        return boardLayout;
    }

    public void setBoardLayout(List<Room> boardLayout) {
        this.boardLayout = boardLayout;
    }

    public int getActiveScenes() {
        return activeScenes;
    }

    public void setActiveScenes(int activeScenes) {
        this.activeScenes = activeScenes;
    }
}

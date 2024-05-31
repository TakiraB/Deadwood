
// import java.util.Map;
import java.util.*;

// Reconfigured Board so HashMap controls the layout
// String is the name of the Room as the key, and Room object as the value
public class Board {
    private HashMap<String, Room> boardLayout;
    private int activeScenes;

    // Removed activeScenes for now - not sure how to incorporate this
    public Board() {
        this.boardLayout = new HashMap<>();
        // this.activeScenes = activeScenes;
    }

    // TODO: update the board logic (not sure about this either)
    public void updateBoard() {

    }

    // grabs adjacent rooms depending on current room
    public List<Room> getAdjacent(Room room) {
        // Initialize empty Array List (not sure if this is needed yet)
        List<Room> list = new ArrayList<>();
        // while(room != null){
        // loop to get the neighbors of a given room
        for (String adjacentNeighbor : room.getAdjacentNeighbors()) {
            Room adjacentRoom = boardLayout.get(adjacentNeighbor);

            // Add the neighbor to the list we initialized
            if (adjacentRoom != null) {
                list.add(adjacentRoom);
            }
        }
        // }
        return list;
    }

    // gives all RoomWithScene a SceneCard, randomly and will make sure each is not
    // used twice
    public void sceneCardDistribution(List<Integer> numbers, ArrayList<SceneCard> cards, int day) {
        // numbers is a random order of 0-39, temp will get the value at index and then
        // pass the corresponding SceneCard to room
        int temp = 0 + ((day - 1) * 10);
        for (Map.Entry<String, Room> key : boardLayout.entrySet()) {
            Room room = key.getValue();
            if (room instanceof RoomWithScene) {
                RoomWithScene currentRoomWithScene = (RoomWithScene) room;
                currentRoomWithScene.setSceneCard(cards.get(numbers.get(temp)));
                temp++;
            }
        }
    }

    public void resetBoardLayout() {
        boardLayout.clear();
    }

    // add a room to the board layout (used in XML)
    public void addRoomToBoard(Room room) {
        if (!boardLayout.containsKey(room.getName())) {
            boardLayout.put(room.getName(), room);

        }
    }

    // getters and setters

    public HashMap<String, Room> getBoardLayout() {
        return boardLayout;
    }

    public void setBoardLayout(HashMap<String, Room> boardLayout) {
        this.boardLayout = boardLayout;
    }

    public int getActiveScenes() {
        return activeScenes;
    }

    public void setActiveScenes(int activeScenes) {
        this.activeScenes = activeScenes;
    }

    public Room getRoomFromBoard(String name) {
        return boardLayout.get(name);
    }

}

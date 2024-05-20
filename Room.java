import java.util.ArrayList;

public class Room {
    protected String name;
    private ArrayList<String> adjacentNeighbors;

    public Room(String name) {
        this.name = name;
        this.adjacentNeighbors = new ArrayList<>();
    }

    // // update the room
    // public void updateRoom() {

    // }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAdjacentNeighbors() {
        return adjacentNeighbors;
    }

    public void setAdjacentNeighbors(String adjacentNeighbor) {
        adjacentNeighbors.add(adjacentNeighbor);
    }

}

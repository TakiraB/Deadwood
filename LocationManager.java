import java.util.*;

public class LocationManager {
    private List<Room> adjacentRooms;

    public LocationManager(List<Room> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }

    // verify the location
    public boolean verifyLocation(Room room) {
        return true;
    }

    // verify that a player can move to the room they selected(next) from the current room
    public boolean validateMove(Room next, Room current, Player player) {
        return true;
    }

    // getters and setters


    public List<Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    public void setAdjacentRooms(List<Room> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }
}

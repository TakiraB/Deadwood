import java.util.*;

public class LocationManager {
    private List<Room> adjacentRooms;

    public LocationManager(List<Room> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }

    public boolean verifyLocation(Room room) {
        return true;
    }

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

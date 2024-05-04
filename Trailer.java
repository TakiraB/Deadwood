import java.util.*;

public class Trailer extends Room{
    private List<Player> playersStart;

    public Trailer (String name, List<Player> playersStart) {
        super(name);
        this.playersStart = playersStart;
    }

    // getters and setters


    public List<Player> getPlayer() {
        return playersStart;
    }
}

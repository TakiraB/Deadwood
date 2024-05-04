import java.util.*;

public class Trailer extends Room{
    private List<Player> playersStart;

    public Trailer (String name, List<Player> playersStart) {
        super(name);
        this.playersStart = playersStart;
    }

    // getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        List<Player> list = new ArrayList<Player>();
        return list;
    }

    public void setPlayers(List<Player> playersStart) {
        this.playersStart = playersStart;
    }
}

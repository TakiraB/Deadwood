import java.util.*;

public class CastingOffice extends Room {
    private List<String> rankChoices;
    private List<Integer> rankPrices;

    public CastingOffice(String name, List<String> rankChoices, List<Integer> rankPrices) {
        super(name);
        this.rankChoices = rankChoices;
        this.rankPrices = rankPrices;
    }

    // updates rank of player, given player and rank
    public void updateRank(Player player, int rank) {
        player.setPlayerRank(rank);
    }

    // getters and setters


    public List<String> getRankChoices() {
        return rankChoices;
    }

    public void setRankChoices(List<String> rankChoices) {
        this.rankChoices = rankChoices;
    }

    public List<Integer> getRankPrices() {
        return rankPrices;
    }

    public void setRankPrices(List<Integer> rankPrices) {
        this.rankPrices = rankPrices;
    }
}

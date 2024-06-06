import java.util.*;

// Updated CastingOffice to contain all attributes of the upgrade choices
// Seperated into Upgrades class to hold all the choice information
public class CastingOffice extends Room {
    private ArrayList<Upgrades> upgradeChoices;
    private Area officeArea;
    private int playerCounter;

    public CastingOffice(String name) {
        super(name);
        this.upgradeChoices = new ArrayList<>();
    }

    // updates rank of player
    public void updateRank(Player player, int rank) {
        player.setPlayerRank(rank);
    }

    public void addUpgradeChoice(Upgrades upgradeChoice) {
        upgradeChoices.add(upgradeChoice);
    }

    // getters and setters

    public ArrayList<Upgrades> getUpgradeChoices() {
        return upgradeChoices;
    }

    public void setRankChoices(ArrayList<Upgrades> upgradeChoices) {
        this.upgradeChoices = upgradeChoices;
    }

    public Area getOfficeArea(){
        return officeArea;
    }

    public void setOfficeArea(Area roomArea){
        this.officeArea = roomArea;
    }

    public void incrementCounter(){
        playerCounter++;
    }

    public int getCounter(){
        return playerCounter;
    }
}

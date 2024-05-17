import java.util.*;

public class Bank {
    // private List<List<Integer>> playerFunds;

    // public Bank(List<List<Integer>> playerFunds) {
    //     this.playerFunds = playerFunds;
    // }

    // gives player value amount of credits
    public String giveCredit(Player player, int value){
        int tempCredits = player.getCredits() + value;
        player.setCredits(tempCredits);
        return "Credit awarded!";
    }

    // gives player value amount of dollars
    public String giveDollar(Player player, int value){
        int tempDollars = player.getDollars() + value;
        player.setDollars(tempDollars);
        return "Dollars awarded!";
    }

    // removes dollars from the player's dollars
    public void payDollars(Player player, int dollars) {
        int newDollars = player.getDollars() - dollars;
        player.setDollars(newDollars);
    }

    // removes credits from the player's credits
    public void payCredits(Player player, int credits) {
        int newCredits = player.getCredits() - credits;
        player.setCredits(newCredits);
    }

    // checks if player has enough dollars
    public boolean checkDollars(Player player, int dollars) {
        if (player.getDollars() >= dollars) {
            return true;
        } else{
            return false;
        }
    }

    // checks if player has enough credits
    public boolean checkCredits(Player player, int credits) {
        if (player.getCredits() >= credits) {
            return true;
        } else {
            return false;
        }
    }

    // getters and setters


    // public List<List<Integer>> getPlayerFunds() {
    //     return playerFunds;
    // }

    // public void setPlayerFunds(List<List<Integer>> playerFunds) {
    //     this.playerFunds = playerFunds;
    // }
}

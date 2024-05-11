import java.util.*;

public class Bank {
    private List<List<Integer>> playerFunds;

    public Bank(List<List<Integer>> playerFunds) {
        this.playerFunds = playerFunds;
    }

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

    // changes player's credits and dollars to creditValue and dollarValue respectively
    public void updateFunds(Player player, int creditValue, int dollarValue){
        player.setCredits(creditValue);
        player.setDollars(dollarValue);
    }

    // verifies that player has specified dollars and credits
    public boolean verifyFunds(Player player, int dollars, int credits){
        if (player.getDollars() >= dollars && player.getCredits() >= credits) {
            return true;
        } else {
            return false;
        }
    }

    // getters and setters


    public List<List<Integer>> getPlayerFunds() {
        return playerFunds;
    }

    public void setPlayerFunds(List<List<Integer>> playerFunds) {
        this.playerFunds = playerFunds;
    }
}

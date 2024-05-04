import java.util.*;

public class Bank {
    private List<List<Integer>> playerFunds;

    public Bank(List<List<Integer>> playerFunds) {
        this.playerFunds = playerFunds;
    }

    // gives player value amount of credits
    public String giveCredit(Player player, int value){
        return "Credit awarded!";
    }

    // gives player value amount of dollars
    public String giveDollar(Player player, int value){
        return "Dollars awarded!";
    }

    // changes player's credits and dollars to creditValue and dollarValue respectively
    public void updateFunds(Player player, int creditValue, int dollarValue){

    }

    // verifies that player has dollars and credits
    public boolean verifyFunds(Player player, int dollars, int credits){
        return true;
    }

    // getters and setters


    public List<List<Integer>> getPlayerFunds() {
        return playerFunds;
    }

    public void setPlayerFunds(List<List<Integer>> playerFunds) {
        this.playerFunds = playerFunds;
    }
}

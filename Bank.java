public class Bank {

    public String giveCredit(Player player, int value){
        return "Credit awarded!";
    }

    public String giveDollar(Player player, int value){
        return "Dollars awarded!";
    }

    public void updateFunds(Player player, int creditValue, int dollarValue){

    }

    public boolean verifyFunds(Player player, int dollars, int credits){
        return true;
    }
}

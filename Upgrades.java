public class Upgrades {
    private int upgradeLevel;
    private String currencyType;
    private int upgradeAmount;

    public Upgrades(int upgradeLevel, String currencyType, int upgradeAmount) {
        this.upgradeLevel = upgradeLevel;
        this.currencyType = currencyType;
        this.upgradeAmount = upgradeAmount;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public int getUpgradeAmount() {
        return upgradeAmount;
    }

    public void setUpgradeAmount(int upgradeAmount) {
        this.upgradeAmount = upgradeAmount;
    }

}

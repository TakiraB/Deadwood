public class Player {
    private String name;
    private int rank;
    private int dollars;
    private int credits;
    private Role activeRole;
    int practiceChips;

    public Player(String name, int rank, int dollars, int credits, Role activeRole, int practiceChips) {
        this.name = name;
        this.rank = rank;
        this.dollars = dollars;
        this.credits = credits;
        this.activeRole = activeRole;
        this.practiceChips = practiceChips;
    }

    // move to a destination (Room role)
    public String move(Room destination) {

    }

    // practiceChips to add to rolls
    public boolean act(int practiceChips){
        return true;
    }

    // rehearsing requires no parameters, only increasing practiceChips
    public boolean rehearse(){
        return true;
    }

    // takes in parameters for rankChoice (provided by CastingOffice) and payment type
    public boolean upgrade(int rankChoice, String dollarOrCredit){
        return true;
    }

    // getters and setters

    public String getName(){
        return name;
    }

    public int getRank(){
        return rank;
    }

    public Role getRole(){
        return activeRole;
    }

    public int getDollars(){
        return dollars;
    }

    public int getCredits(){
        return credits;
    }

    public Role getActiveRole(){
        return activeRole;
    }

    public int getPracticeChips(){
        return practiceChips;
    }
    
    public void setPlayerName(String name){
        this.name = name;
    }

    public void setPlayerRank(int rank){
        this.rank = rank;
    }

    public void setFunds(int dollars, int credits){
        this.dollars = dollars;
        this.credits = credits;
    }

    public void setActiveRole(Role role) {
        this.activeRole = role;
    }

    public void setPracticeChips(int practiceChips){
        this.practiceChips = practiceChips;
    }
}


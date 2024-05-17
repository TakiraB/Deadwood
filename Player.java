public class Player {
    private String name;
    private int rank;
    private int dollars;
    private int credits;
    private Role activeRole;
    private int practiceChips;
    private Room currentRoom;
    private boolean hasMoved;
    private boolean hasActed;


    public Player(String name, int rank, int dollars, int credits, Role activeRole, int practiceChips, Room currentRoom, boolean hasMoved, boolean hasActed) {
        this.name = name;
        this.rank = rank;
        this.dollars = dollars;
        this.credits = credits;
        this.activeRole = activeRole;
        this.practiceChips = practiceChips;
        this.currentRoom = currentRoom;
        this.hasMoved = hasMoved;
        this.hasActed = hasActed;
    }

    // move to a destination (Room role)
    public String move(Room destination) {
        if(currentRoom.getAdjacentNeighbors().contains(destination.getName())){
            this.setPlayerRoom(destination);
            this.setHasMoved(true);
            return "You have successfully moved!";
        }
        else {
            return "That room is not adjacent, you cannot move there this turn. Please try moving closer to the desired room.";
        }
    }

    // TODO: finish act logic
    public boolean act(int practiceChips){
        if(activeRole != null) {
            activeRole.getRoleName();
            activeRole.getScript();
            activeRole.getRank();
        }
        return true;
    }

    // rehearsing requires no parameters, only increasing practiceChips
    public boolean rehearse(){
        if(activeRole != null){
            practiceChips++;
            System.out.println("You have successfully rehearsed! Here's a practice chip!");
        }
        else {
            System.out.println("Boo! You aren't in a role right now.");
            return false;
        }
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

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setActiveRole(Role role) {
        this.activeRole = role;
    }

    public void setPracticeChips(int practiceChips){
        this.practiceChips = practiceChips;
    }

    public Room getPlayerRoom(){
        return currentRoom;
    }

    public void setPlayerRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean getHasActed() {
        return hasActed;
    }

    public void setHasActed(boolean hasActed) {
        this.hasActed = hasActed;
    }
}


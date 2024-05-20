public class Role {
    private String name;
    private String script;
    private boolean starredRole;
    private int rank;
    private Player playerOnRole;

    public Role(String name, String script, boolean starredRole, int rank){
        this.name = name;
        this.script = script;
        // this.isActive = isActive;
        this.starredRole = starredRole;
        this.rank = rank;
    }

    // check if the tole is active, like if it is being rehearsed or acted on
    public boolean checkActiveRole(Player player){
        return true;
    }

    // update role with information
    public void updateRole(Role role) {
        this.name = role.name;
        this.script = role.script;
        this.starredRole = role.starredRole;
        this.rank = role.rank;
    }

    // make sure player has the correct rank for the role
    public boolean validateRank(Player player) {
        return true;
    }

    // getters and setters

    public String getRoleName(){
        return name;
    }

    public String getScript(){
        return script;
    }

    public int getRank(){
        return rank;
    }

    // public boolean getActive(){
    //     return isActive;
    // }

    public boolean getStarredRole(){
        return starredRole;
    }

    public void setRoleName(String name){
        this.name = name;
    }

    public void setRoleScript(String script){
        this.script = script;
    }

    // public void setActiveRole(boolean isActive){
    //     this.isActive = isActive;
    // }

    public void setStarredRole(boolean starredRole){
        this.starredRole = starredRole;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public Player getPlayerOnRole(){
        return playerOnRole;
    }

    public void setPlayer(Player newPlayer){
        this.playerOnRole = newPlayer;
    }

    
}

public class Role {
    private String name;
    private String script;
    private boolean isActive;
    private boolean starredRole;
    private int rank;

    public Role(String name, String script, boolean isActive, boolean starredRole, int rank){
        this.name = name;
        this.script = script;
        this.isActive = isActive;
        this.starredRole = starredRole;
        this.rank = rank;
    }

    public boolean checkActiveRole(Player player){
        return true;
    }

    public void updateRole(Role role) {

    }

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

    public boolean getActive(){
        return isActive;
    }

    public boolean getStarredRole(){
        return starredRole;
    }

    public void setRoleName(String name){
        this.name = name;
    }

    public void setRoleScript(String script){
        this.script = script;
    }

    public void setActiveRole(boolean isActive){
        this.isActive = isActive;
    }

    public void setStarredRole(boolean starredRole){
        this.starredRole = starredRole;
    }

    public void setRank(int rank){
        this.rank = rank;
    }
}

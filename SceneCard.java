import java.util.*;

public class SceneCard {
    private String name;
    private int budget;
    private List<Role> roles;
    private String description;

    public SceneCard(String name, int budget, List<Role> roles, String description) {
        this.name = name;
        this.budget = budget;
        this.roles = roles;
        this.description = description;
    }

    public SceneCard getSceneCard() {
        
    }

    // Getters and setters 


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

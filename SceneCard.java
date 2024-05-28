import java.util.*;

public class SceneCard {
    private String name;
    private int budget;
    // starred roles
    private List<Role> roles;
    private int sceneNumber;
    private String description;
    private String imagePlaceholder;
    private Area onCardArea;

    public SceneCard(String name, int budget, List<Role> roles, int sceneNumber, String description,
            String imagePlaceholder) {
        this.name = name;
        this.budget = budget;
        this.roles = roles;
        this.sceneNumber = sceneNumber;
        this.description = description;
        this.imagePlaceholder = imagePlaceholder;
    }

    // // TODO: get the information for the SceneCard
    // public SceneCard getSceneCard() {
    // return this;
    // }

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

    public void setSceneNumber(int sceneNumber) {
        this.sceneNumber = sceneNumber;
    }

    public int getSceneNumber() {
        return sceneNumber;
    }

    public void setImage(String imagePlaceholder) {
        this.imagePlaceholder = imagePlaceholder;
    }

    public String getImage() {
        return imagePlaceholder;
    }

    public Area getSceneCardArea() {
        return onCardArea;
    }

    public void setSceneCardArea(Area onCardArea){
        this.onCardArea = onCardArea;
    }

}

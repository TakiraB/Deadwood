public class Takes {
    private int takesForSuccess;
    private Area takesArea;

    public Takes(int takesForSuccess) {
        this.takesForSuccess = takesForSuccess;
    }

    public void setTake(int takesForSuccess) {
        this.takesForSuccess = takesForSuccess;
    }

    public int getTake() {
        return takesForSuccess;
    }

    public Area getTakeArea(){
        return takesArea;
    }

    public void setTakeArea(Area takesArea){
        this.takesArea = takesArea;
    }
}

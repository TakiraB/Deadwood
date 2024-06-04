import javax.swing.ImageIcon;

public class PlayerIcon {
    private ImageIcon playerIcon;
    private int xCord;
    private int yCord;

    public PlayerIcon(ImageIcon playerIcon, int xCord, int yCord){
        this.playerIcon = playerIcon;
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public ImageIcon getPlayerIcon(){
        return playerIcon;
    }

    public void setPlayerIcon(ImageIcon playerIcon){
        this.playerIcon = playerIcon;
    }

    public int getXCord(){
        return xCord;
    }

    public void setXCord(int xCord){
        this.xCord = xCord;
    }

    public int getYCord(){
        return yCord;
    }

    public void setYCord(int yCord){
        this.yCord = yCord;
    }
}

import java.util.ArrayList;

public interface ViewInterface {
    void setSceneCardsBoard();
    void setPlayerIcons(ArrayList<Player> playerList);
    void displayGameMessage(String gameMessage);
    void updatePlayerRoom(Player activePlayer);
    void showValidRooms(ArrayList<String> neighbors);
  // Other methods will go here as I add them to DeadwoodView.java
}

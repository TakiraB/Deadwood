import org.w3c.dom.Document;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Deadwood {
    public static void main(String args[]) {
     Document doc = null;
      parseBoard parsingBoard = new parseBoard();
      Board board = new Board();
      try {
         doc = parsingBoard.getDocFromFile("board.xml");
         board = parsingBoard.readBoardData(doc);
      } catch (Exception e) {
         System.out.println("Error = " + e);
      }

      DeadwoodController boardController = new DeadwoodController(board);
      DeadwoodView boardView = new DeadwoodView(board, boardController);

      // Take input from the user about number of players
      Integer[] playerNumberOptions = { 2, 3, 4, 5, 6, 7, 8 };
      JComboBox<Integer> playerCountDropdown = new JComboBox<>(playerNumberOptions);
      boardView.setVisible(true);
      JOptionPane.showConfirmDialog(null, playerCountDropdown, "Select number of players",
            JOptionPane.OK_CANCEL_OPTION);
      int numPlayers = (Integer) playerCountDropdown.getSelectedItem();
      boardController.initializeActivePlayers(numPlayers);
      boardView.setSceneCardsBoard();
      boardView.setPlayerIcons(boardController.getPlayerList());
      boardView.displayCurrentPlayer(boardController.getGameState().getActivePlayer());
      boardController.setUpSceneCards();
      boardView.takesInitialization(boardController.getGameState());
      boardView.playerCheck(boardController.getGameState());
    }
}

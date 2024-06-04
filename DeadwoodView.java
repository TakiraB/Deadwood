/*
   Deadwood GUI helper file
   Author: Moushumi Sharmin
*/

// Imports for Swing components
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.HashMap;
import java.util.Map;

// Extends Jframe (creates window)
public class DeadwoodView extends JFrame implements ViewInterface {

   // JComponents (labels, buttons, panes)
   JLabel boardlabel;
   JLabel cardlabel;
   JLabel playerlabel;
   JLabel mLabel;
   JLabel currentPlayerLabel;
   JLabel logLabel;
   JButton bAct;
   JButton bRehearse;
   JButton bMove;
   JButton bUpgrade;
   JButton bTakeRole;
   JButton bYourStats;
   JButton bPlayerLocations;
   JButton bEndTurn;
   JButton bEndGame;
   JButton trailerButton;
   JButton hotelButton;
   JButton churchButton;
   JButton castingButton;
   JButton bankButton;
   JButton saloonButton;
   JButton jailButton;
   JButton mainStreetButton;
   JButton generalButton;
   JButton ranchButton;
   JButton secretButton;
   JButton trainButton;
   JTextArea activePlayer;
   JTextArea textAction;
   JLayeredPane bPane;

   private Board board;
   private DeadwoodController boardController;
   private Map<String, JButton> roomButtons;
   private Map<Player, JLabel> playerLabels;

   public DeadwoodView(Board board, DeadwoodController boardController) {

      // Set the title of the JFrame
      super("Deadwood");

      System.out.println("DeadwoodView class loader: " + DeadwoodView.class.getClassLoader());

      this.board = board;
      this.boardController = boardController;
      this.boardController.setView(this);
      roomButtons = new HashMap<>();
      playerLabels = new HashMap<>();

      // Set the exit option for the JFrame
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      // Create the JLayeredPane to hold the display, cards, dice and buttons
      // stacking order
      bPane = getLayeredPane();

      // Create the deadwood board
      boardlabel = new JLabel();
      ImageIcon icon = new ImageIcon("images/board.jpg");
      boardlabel.setIcon(icon);
      boardlabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

      // Add the board to the lowest layer
      bPane.add(boardlabel, Integer.valueOf(0));

      // Set the size of the GUI
      setSize(icon.getIconWidth() + 200, icon.getIconHeight() + 00);

      // max window on open
      setExtendedState(JFrame.MAXIMIZED_BOTH);


      // Create the Menu for action buttons
      mLabel = new JLabel("Player Actions");
      mLabel.setBounds(icon.getIconWidth() + 20, 0, 150, 20);
      bPane.add(mLabel, Integer.valueOf(2));

      // ---------------------------
      // BUTTONS FOR MENU
      // ---------------------------

      bMove = new JButton("MOVE");
      bMove.setEnabled(true);
      bMove.setBackground(Color.white);
      bMove.setBounds(icon.getIconWidth() + 15, 90, 150, 20);
      bMove.addMouseListener(new boardMouseListener(boardController.getGameState()));
      bMove.addMouseListener(new validRoomListener());
      // bMove.addActionListener(new ActionListener(){
      //    @Override
      //    public void actionPerformed(ActionEvent e) {
      //       System.out.println("Move button clicked");
      //       boardController.moveOption();
      //    }
      // });

      bAct = new JButton("ACT");
      bAct.setBackground(Color.white);
      bAct.setBounds(icon.getIconWidth() + 15, 30, 150, 20);
      bAct.addMouseListener(new boardMouseListener(boardController.getGameState()));
      bAct.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            boardController.actOption();
         }
      });

      bRehearse = new JButton("REHEARSE");
      bRehearse.setBackground(Color.white);
      bRehearse.setBounds(icon.getIconWidth() + 15, 60, 150, 20);
      bRehearse.addMouseListener(new boardMouseListener(boardController.getGameState()));
      bRehearse.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            boardController.rehearseOption();
         }
      });

      bUpgrade = new JButton("UPGRADE");
      bUpgrade.setBackground(Color.white);
      bUpgrade.setBounds(icon.getIconWidth() + 15, 150, 150, 20);
      bUpgrade.addMouseListener(new boardMouseListener(boardController.getGameState()));
      bUpgrade.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            boardController.upgradeOption();
         }
      });

      bTakeRole = new JButton("TAKE A ROLE");
      bTakeRole.setBackground(Color.white);
      bTakeRole.setBounds(icon.getIconWidth() + 15, 120, 150, 20);
      bTakeRole.addMouseListener(new boardMouseListener(boardController.getGameState()));
      bTakeRole.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            boardController.takingRoleOption();
         }
      });

      bEndTurn = new JButton("END TURN");
      bEndTurn.setBackground(Color.white);
      bEndTurn.setBounds(icon.getIconWidth() + 15, 210, 150, 20);
      bEndTurn.addMouseListener(new boardMouseListener(boardController.getGameState()));
      bEndTurn.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            boardController.endTurnOption();
         }
      });

      // -------------------------------------
      // CREATING BUTTONS FOR PLAYERS MOVING
      // -------------------------------------

      // Trailer button
      JButton trailerButton = new JButton();
      trailerButton.setBounds(1020, 270, 140, 40);
      trailerButton.setOpaque(false);
      trailerButton.setContentAreaFilled(false);
      trailerButton.setBorder(BorderFactory.createEmptyBorder());
      trailerButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("Trailer", trailerButton);

      // Hotel button
      JButton hotelButton = new JButton();
      hotelButton.setBounds(1010, 860, 120, 35);
      hotelButton.setOpaque(false);
      hotelButton.setContentAreaFilled(false);
      hotelButton.setBorder(BorderFactory.createEmptyBorder());
      hotelButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("Hotel", hotelButton);

      // Church button
      JButton churchButton = new JButton();
      churchButton.setBounds(660, 855, 130, 35);
      churchButton.setOpaque(false);
      churchButton.setContentAreaFilled(false);
      churchButton.setBorder(BorderFactory.createEmptyBorder());
      churchButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("Church", churchButton);

      // Bank button
      JButton bankButton = new JButton();
      bankButton.setBounds(680, 595, 90, 35);
      bankButton.setOpaque(false);
      bankButton.setContentAreaFilled(false);
      bankButton.setBorder(BorderFactory.createEmptyBorder());
      bankButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("Bank", bankButton);

      // Saloon button
      JButton saloonButton = new JButton();
      saloonButton.setBounds(668, 400, 130, 35);
      saloonButton.setOpaque(false);
      saloonButton.setContentAreaFilled(false);
      saloonButton.setBorder(BorderFactory.createEmptyBorder());
      saloonButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("Saloon", saloonButton);

      // Main Street button
      JButton mainStreetButton = new JButton();
      mainStreetButton.setBounds(980, 145, 190, 38);
      mainStreetButton.setOpaque(false);
      mainStreetButton.setContentAreaFilled(false);
      mainStreetButton.setBorder(BorderFactory.createEmptyBorder());
      mainStreetButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("Main Street", mainStreetButton);

      // Jail button
      JButton jailButton = new JButton();
      jailButton.setBounds(328, 145, 90, 35);
      jailButton.setOpaque(false);
      jailButton.setContentAreaFilled(false);
      jailButton.setBorder(BorderFactory.createEmptyBorder());
      jailButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("Jail", jailButton);

      // General Store button
      JButton generalButton = new JButton();
      generalButton.setBounds(375, 400, 200, 38);
      generalButton.setOpaque(false);
      generalButton.setContentAreaFilled(false);
      generalButton.setBorder(BorderFactory.createEmptyBorder());
      generalButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("General Store", generalButton);

      // Ranch button
      JButton ranchButton = new JButton();
      ranchButton.setBounds(287, 595, 100, 35);
      ranchButton.setOpaque(false);
      ranchButton.setContentAreaFilled(false);
      ranchButton.setBorder(BorderFactory.createEmptyBorder());
      ranchButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("Ranch", ranchButton);

      // Secret Hideout button
      JButton secretButton = new JButton();
      secretButton.setBounds(27, 850, 208, 38);
      secretButton.setOpaque(false);
      secretButton.setContentAreaFilled(false);
      secretButton.setBorder(BorderFactory.createEmptyBorder());
      secretButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("Secret Hideout", secretButton);

      // Casting Office button
      JButton castingButton = new JButton();
      castingButton.setBounds(18, 460, 195, 40);
      castingButton.setOpaque(false);
      castingButton.setContentAreaFilled(false);
      castingButton.setBorder(BorderFactory.createEmptyBorder());
      castingButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("Casting Office", castingButton);

      // Train Station button
      JButton trainButton = new JButton();
      trainButton.setBounds(28, 185, 190, 38);
      trainButton.setOpaque(false);
      trainButton.setContentAreaFilled(false);
      trainButton.setBorder(BorderFactory.createEmptyBorder());
      trainButton.addMouseListener(new borderMouseListener());
      // roomButtons.put("Train Station", trainButton);

      // ------------------------------------------
      // ADDING BUTTONS TO FRAME ON VARIOUS LEVELS
      // ------------------------------------------

      bPane.add(bMove, Integer.valueOf(2));
      bPane.add(bAct, Integer.valueOf(2));
      bPane.add(bRehearse, Integer.valueOf(2));
      bPane.add(bUpgrade, Integer.valueOf(2));
      bPane.add(bTakeRole, Integer.valueOf(2));
      // bPane.add(bYourStats, Integer.valueOf(2));
      // bPane.add(bPlayerLocations, Integer.valueOf(2));
      bPane.add(bEndTurn, Integer.valueOf(2));
      // bPane.add(bEndGame, Integer.valueOf(2));
      bPane.add(trailerButton, Integer.valueOf(3));
      bPane.add(hotelButton, Integer.valueOf(3));
      bPane.add(churchButton, Integer.valueOf(3));
      bPane.add(bankButton, Integer.valueOf(3));
      bPane.add(saloonButton, Integer.valueOf(3));
      bPane.add(mainStreetButton, Integer.valueOf(3));
      bPane.add(jailButton, Integer.valueOf(3));
      bPane.add(generalButton, Integer.valueOf(3));
      bPane.add(ranchButton, Integer.valueOf(3));
      bPane.add(secretButton, Integer.valueOf(3));
      bPane.add(castingButton, Integer.valueOf(3));
      bPane.add(trainButton, Integer.valueOf(3));

      roomButtons.put("Trailer", trailerButton);
      roomButtons.put("Hotel", hotelButton);
      roomButtons.put("Church", churchButton);
      roomButtons.put("Bank", bankButton);
      roomButtons.put("Saloon", saloonButton);
      roomButtons.put("Main Street", mainStreetButton);
      roomButtons.put("Jail", jailButton);
      roomButtons.put("General Store", generalButton);
      roomButtons.put("Ranch", ranchButton);
      roomButtons.put("Secret Hideout", secretButton);
      roomButtons.put("Casting Office", castingButton);
      roomButtons.put("Train Station", trainButton);

      // ------------------------------------------
      // ADDITIONAL TEXT AREAS (CURRENT PLAYER AND ACTION LOG)
      // ------------------------------------------

      // Create the Current player label
      currentPlayerLabel = new JLabel("Current Player Statistics");
      currentPlayerLabel.setBounds(icon.getIconWidth() + 15, 300, 150, 20);
      bPane.add(currentPlayerLabel, Integer.valueOf(2));

      // Set the current player text area (not modifiable by user)
      activePlayer = new JTextArea();
      activePlayer.setBounds(icon.getIconWidth() + 15, 320, 200, 250);
      activePlayer.setEditable(false);
      activePlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      bPane.add(activePlayer, Integer.valueOf(2));

      // Create action log label
      logLabel = new JLabel("Recent Actions");
      logLabel.setBounds(icon.getIconWidth() + 15, 600, 150, 20);
      bPane.add(logLabel, Integer.valueOf(3));

      // Create text area for action log
      textAction = new JTextArea();
      textAction.setEditable(false);
      textAction.setBounds(icon.getIconWidth() + 15, 620, 200, 250);
      textAction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      bPane.add(textAction, Integer.valueOf(2));

   }

   // ------------------------------------------
   // MOUSE EVENTS
   // ------------------------------------------

   class boardMouseListener implements MouseListener {

      private GameState gameState;

      public boardMouseListener(GameState gameState) {
         this.gameState = gameState;
      }

      // Im not sure what to do with this yet
      public void mouseClicked(MouseEvent e) {

         if (e.getSource() == bAct) {
            // playerlabel.setVisible(true);
            textAction.append(gameState.getActivePlayer().getName() + " has tried to act.\n");
            textAction.append(gameState.getActivePlayer().getPlayerRoom().getName() + "\n");
            System.out.println("Acting is Selected\n");
         } else if (e.getSource() == bRehearse) {
            System.out.println("Rehearse is Selected\n");
         } else if (e.getSource() == bMove) {
            System.out.println("Move is Selected\n");
         } else if (e.getSource() == bTakeRole) {
            System.out.println("take a role is Selected\n");
         } else if (e.getSource() == bPlayerLocations) {
            System.out.println("player locations is Selected\n");
         } else if (e.getSource() == bEndTurn) {
            System.out.println("end turn is Selected\n");
         } else if (e.getSource() == bEndGame) {
            System.out.println("end game is Selected\n");
         }
      }

      public void mousePressed(MouseEvent e) {
      }

      public void mouseReleased(MouseEvent e) {
      }

      public void mouseEntered(MouseEvent e) {

      }

      public void mouseExited(MouseEvent e) {
      }
   }

   // MouseAdapter is used when not all functionalities of MouseListener are needed
   // to be implemented
   // I originally had the border stuff in MouseListener, but it was applying to
   // all the buttons
   // So, keeping the functionality separate will make it easier in case I want to
   // apply other effects to the action buttons
   class borderMouseListener extends MouseAdapter {

      // when mouse enters button area, create a border around the area for
      // readability
      @Override
      public void mouseEntered(MouseEvent e) {
         // cast as JButton from object (e.getsource()) so we can apply the border
         JButton dummyButton = (JButton) e.getSource();
         dummyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      }

      @Override
      // once the mouse exits the button area, make the border invisible again
      public void mouseExited(MouseEvent e) {
         // cast as JButton from object (e.getsource()) so we can manipulate the border
         // on mouse exiting
         JButton dummyButton = (JButton) e.getSource();
         dummyButton.setBorder(BorderFactory.createEmptyBorder());
      }

   }

   class validRoomListener extends MouseAdapter {

      // when mouse enters button area, create a border around the area for
      // readability
      @Override
      public void mouseClicked(MouseEvent e) {
         System.out.println("Move button clicked");
         boardController.moveOption();
      }
   }

   // class newRoomListener extends MouseAdapter {

   //    // when mouse enters button area, create a border around the area for
   //    // readability
   //    @Override
   //    public void mouseClicked(MouseEvent e) {
   //       System.out.println("New room selection clicked");
   //       boardController.performMove(neighborName);
   //    }
   // }

   // ------------------------------------------
   // Setting face-down scene card images at every set at the start of the game,
   // uncovered when one person is in the room (not implemented yet)
   // ------------------------------------------

   public void setSceneCardsBoard() {
      // ImageIcon sceneCardDown = new ImageIcon("images/CardBack-small.jpg");
      for (Room boardRoom : board.getBoardLayout().values()) {
         // get the area of each RoomWithScene
         if (boardRoom instanceof RoomWithScene) {
            RoomWithScene sceneRoom = (RoomWithScene) boardRoom;
            Area sceneArea = sceneRoom.getSceneRoomArea();

            // place Cardback-small.jpg at each area spot
            JLabel cardBackImage = new JLabel();
            ImageIcon sceneCardDown = new ImageIcon("images/CardBack-small.jpg");
            cardBackImage.setIcon(sceneCardDown);
            cardBackImage.setBounds(sceneArea.getXValue(), sceneArea.getYValue(), sceneArea.getWidth(),
                  sceneArea.getHeight());
            bPane.add(cardBackImage, Integer.valueOf(2));

         }
      }
   }

   // General display message that can be called from controller
   public void displayGameMessage(String gameMessage){
      JOptionPane.showMessageDialog(this, gameMessage);
   }

   // Update player room on the board
   public void updatePlayerRoom(Player activePlayer){
      // get the active players label (we need to change its bounds)
      JLabel playerLabel = playerLabels.get(activePlayer);
      // get the players current room
      Room currentPlayerRoom = activePlayer.getPlayerRoom();
      // Set bounds based on stored area objects and stored Icon object info
      playerLabel.setBounds(currentPlayerRoom.getRoomArea().getXValue(), currentPlayerRoom.getRoomArea().getYValue(),
      playerLabel.getIcon().getIconWidth(), playerLabel.getIcon().getIconHeight());
      playerLabel.setVisible(true);
   }

   // Setting the player icons based on starting rank and create labels for them
   public void setPlayerIcons(ArrayList<Player> playerList) {
      // Initialize all the player colored dice (white dice used for actual roles)
      ArrayList<PlayerIcon> playerIconList = new ArrayList<>();

      //Rank 1 icons
      ImageIcon player1Rank1 = new ImageIcon("dice/dice/b1.png");
      ImageIcon player2Rank1 =new ImageIcon("dice/dice/c1.png");
      ImageIcon player3Rank1 =new ImageIcon("dice/dice/g1.png");
      ImageIcon player4Rank1 =new ImageIcon("dice/dice/o1.png");
      ImageIcon player5Rank1 =new ImageIcon("dice/dice/p1.png");
      ImageIcon player6Rank1 =new ImageIcon("dice/dice/r1.png");
      ImageIcon player7Rank1 =new ImageIcon("dice/dice/v1.png");
      ImageIcon player8Rank1 =new ImageIcon("dice/dice/y1.png");

      //Rank 2 icons
      ImageIcon player1Rank2 = new ImageIcon("dice/dice/b2.png");
      ImageIcon player2Rank2 =new ImageIcon("dice/dice/c2.png");
      ImageIcon player3Rank2 =new ImageIcon("dice/dice/g2.png");
      ImageIcon player4Rank2 =new ImageIcon("dice/dice/o2.png");
      ImageIcon player5Rank2 =new ImageIcon("dice/dice/p2.png");
      ImageIcon player6Rank2 =new ImageIcon("dice/dice/r2.png");
      ImageIcon player7Rank2 =new ImageIcon("dice/dice/v2.png");
      ImageIcon player8Rank2 =new ImageIcon("dice/dice/y2.png");

   //If the number of players is equal to 7, give them all a rank 2 die
     if(playerList.size() == 7){
      playerIconList.add(new PlayerIcon(player1Rank2, 1005, 315));
      playerIconList.add(new PlayerIcon(player2Rank2, 1050, 315));
      playerIconList.add(new PlayerIcon(player3Rank2, 1095, 315));
      playerIconList.add(new PlayerIcon(player4Rank2, 1140, 315));
      playerIconList.add(new PlayerIcon(player5Rank2, 1005, 365));
      playerIconList.add(new PlayerIcon(player6Rank2, 1050, 365));
      playerIconList.add(new PlayerIcon(player7Rank2, 1095, 365));
     }
   //If number of players is equal to 8, give them all rank 2 die
     else if(playerList.size() == 8){
      playerIconList.add(new PlayerIcon(player1Rank2, 1005, 315));
      playerIconList.add(new PlayerIcon(player2Rank2, 1050, 315));
      playerIconList.add(new PlayerIcon(player3Rank2, 1095, 315));
      playerIconList.add(new PlayerIcon(player4Rank2, 1140, 315));
      playerIconList.add(new PlayerIcon(player5Rank2, 1005, 365));
      playerIconList.add(new PlayerIcon(player6Rank2, 1050, 365));
      playerIconList.add(new PlayerIcon(player7Rank2, 1095, 365));
      playerIconList.add(new PlayerIcon(player8Rank2, 1140, 365));
     }
   //Otherwise make all the player icons even if we don't use them all with rank 1
     else{
      playerIconList.add(new PlayerIcon(player1Rank1, 1005, 315));
      playerIconList.add(new PlayerIcon(player2Rank1, 1050, 315));
      playerIconList.add(new PlayerIcon(player3Rank1, 1095, 315));
      playerIconList.add(new PlayerIcon(player4Rank1, 1140, 315));
      playerIconList.add(new PlayerIcon(player5Rank1, 1005, 365));
      playerIconList.add(new PlayerIcon(player6Rank1, 1050, 365));
      playerIconList.add(new PlayerIcon(player7Rank1, 1095, 365));
      playerIconList.add(new PlayerIcon(player8Rank1, 1140, 365));
     }

   //Iterate through the players and get the player objects and assign them to appropriate JLabels
   // Put them in the hashmap for when we need them
     for(int i=0; i< playerList.size(); i++){
      // Get the Players from the input and the initialized PlayerIcons in the ArrayList associated with them
      Player newPlayer = playerList.get(i);
      PlayerIcon newPlayerInfo = playerIconList.get(i);
      // Create labels for all of the Player Icons, grab the bounds from the objects
      JLabel playerLabel = new JLabel();
      playerLabel.setIcon(newPlayerInfo.getPlayerIcon());
      playerLabel.setBounds(newPlayerInfo.getXCord(), newPlayerInfo.getYCord(), newPlayerInfo.getPlayerIcon().getIconWidth(), newPlayerInfo.getPlayerIcon().getIconHeight());
      playerLabel.setVisible(true);
      // Add it to the frame and add the players to our HashMap
      bPane.add(playerLabel, Integer.valueOf(3));
      playerLabels.put(newPlayer, playerLabel);
     }
   }

   // Showing valid rooms a player can move to once the "move" button is pressed
   public void showValidRooms(ArrayList<String> neighbors) {
      // System.out.println("Showing valid rooms: " + neighbors);

      // Iterate through the Hashmap of neighbor strings and their associated buttons
      for(Map.Entry<String, JButton> neighborEntry : roomButtons.entrySet()) {
         // Grab neighbor name string
         String neighborName = neighborEntry.getKey();
         // Grab the associated JButton
         JButton neighborButton = neighborEntry.getValue();
         // If the String and the button line up, then this is a valid move and change the border to green and enable it
         if(neighbors.contains(neighborName)){
            neighborButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            neighborButton.setEnabled(true);
         }
         else{
            // If not a valid neighbor to move to, disable the button
            neighborButton.setBorder(BorderFactory.createEmptyBorder());
            neighborButton.setEnabled(false);
         }
      }
   }

   // ------------------------------------------
   // MAIN FOR TESTING
   // ------------------------------------------

   public static void main(String[] args) {
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
      int numPlayers = Integer.parseInt(JOptionPane.showInputDialog(boardView, "How many players?"));
      boardController.initializeActivePlayers(numPlayers);
      boardView.setVisible(true);
      boardView.setSceneCardsBoard();
      boardView.setPlayerIcons(boardController.getPlayerList());
   }
}

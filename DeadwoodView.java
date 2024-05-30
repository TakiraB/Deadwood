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
import java.awt.image.BufferedImage;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

// Extends Jframe (creates window)
public class DeadwoodView extends JFrame implements ViewInterface {

   // JLabels (text/images)
   JLabel boardlabel;
   JLabel cardlabel;
   JLabel playerlabel;
   JLabel mLabel;
   JLabel currentPlayerLabel;
   JLabel logLabel;

   // JButtons (buttons)
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

   // JLayered Pane (for having multiple layers)
   JLayeredPane bPane;
   private Board board;

   // Constructor
   public DeadwoodView(Board board) {
      // Set the title of the JFrame
      super("Deadwood");

      this.board = board;

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

      // Add a scene card to this room
      // cardlabel = new JLabel();
      // ImageIcon cIcon = new ImageIcon("01.png");
      // cardlabel.setIcon(cIcon);
      // cardlabel.setBounds(20, 65, cIcon.getIconWidth() + 2, cIcon.getIconHeight());
      // cardlabel.setOpaque(true);

      // // Add the card to the lower layer
      // bPane.add(cardlabel, Integer.valueOf(1));

      // Add a dice to represent a player.
      // Role for Crusty the prospector. The x and y co-ordiantes are taken from
      // Board.xml file
      // playerlabel = new JLabel();
      // ImageIcon pIcon = new ImageIcon("r2.png");
      // playerlabel.setIcon(pIcon);
      // // playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());
      // playerlabel.setBounds(114, 227, 46, 46);
      // playerlabel.setVisible(false);
      // bPane.add(playerlabel, Integer.valueOf(3));

      // Create the Menu for action buttons
      mLabel = new JLabel("Player Actions");
      mLabel.setBounds(icon.getIconWidth() + 20, 0, 150, 20);
      bPane.add(mLabel, Integer.valueOf(2));

      //---------------------------
      // BUTTONS FOR MENU
      //---------------------------

      bMove = new JButton("MOVE");
      bMove.setEnabled(true);
      bMove.setBackground(Color.white);
      bMove.setBounds(icon.getIconWidth() + 15, 90, 150, 20);
      bMove.addMouseListener(new boardMouseListener());

      bAct = new JButton("ACT");
      bAct.setBackground(Color.white);
      bAct.setBounds(icon.getIconWidth() + 15, 30, 150, 20);
      bAct.addMouseListener(new boardMouseListener());

      bRehearse = new JButton("REHEARSE");
      bRehearse.setBackground(Color.white);
      bRehearse.setBounds(icon.getIconWidth() + 15, 60, 150, 20);
      bRehearse.addMouseListener(new boardMouseListener());

      bUpgrade = new JButton("UPGRADE");
      bUpgrade.setBackground(Color.white);
      bUpgrade.setBounds(icon.getIconWidth() + 15, 90, 150, 20);
      bUpgrade.addMouseListener(new boardMouseListener());

      bTakeRole = new JButton("TAKE A ROLE");
      bTakeRole.setBackground(Color.white);
      bTakeRole.setBounds(icon.getIconWidth() + 15, 120, 150, 20);
      bTakeRole.addMouseListener(new boardMouseListener());

      bYourStats = new JButton("YOUR STATS");
      bYourStats.setBackground(Color.white);
      bYourStats.setBounds(icon.getIconWidth() + 15, 150, 150, 20);
      bYourStats.addMouseListener(new boardMouseListener());

      bPlayerLocations = new JButton("DISPLAY PLAYERS");
      bPlayerLocations.setBackground(Color.white);
      bPlayerLocations.setBounds(icon.getIconWidth() + 15, 180, 150, 20);
      bPlayerLocations.addMouseListener(new boardMouseListener());

      bEndTurn = new JButton("END TURN");
      bEndTurn.setBackground(Color.white);
      bEndTurn.setBounds(icon.getIconWidth() + 15, 210, 150, 20);
      bEndTurn.addMouseListener(new boardMouseListener());

      bEndGame = new JButton("END GAME");
      bEndGame.setBackground(Color.white);
      bEndGame.setBounds(icon.getIconWidth() + 15, 240, 150, 20);
      bEndGame.addMouseListener(new boardMouseListener());

      //-------------------------------------
      // CREATING BUTTONS FOR PLAYERS MOVING
      //-------------------------------------

      // Trailer button
      JButton trailerButton = new JButton();
      trailerButton.setBounds(1020, 270, 140, 40);
      trailerButton.setOpaque(false);
      trailerButton.setContentAreaFilled(false);
      trailerButton.setBorder(BorderFactory.createEmptyBorder());
      trailerButton.addMouseListener(new borderMouseListener());

      // Hotel button
      JButton hotelButton = new JButton();
      hotelButton.setBounds(1010, 860, 120, 35);
      hotelButton.setOpaque(false);
      hotelButton.setContentAreaFilled(false);
      hotelButton.setBorder(BorderFactory.createEmptyBorder());
      hotelButton.addMouseListener(new borderMouseListener());

      // Church button
      JButton churchButton = new JButton();
      churchButton.setBounds(660, 855, 130, 35);
      churchButton.setOpaque(false);
      churchButton.setContentAreaFilled(false);
      churchButton.setBorder(BorderFactory.createEmptyBorder());
      churchButton.addMouseListener(new borderMouseListener());

      // Bank button
      JButton bankButton = new JButton();
      bankButton.setBounds(680, 595, 90, 35);
      bankButton.setOpaque(false);
      bankButton.setContentAreaFilled(false);
      bankButton.setBorder(BorderFactory.createEmptyBorder());
      bankButton.addMouseListener(new borderMouseListener());

      // Saloon button
      JButton saloonButton = new JButton();
      saloonButton.setBounds(668, 400, 130, 35);
      saloonButton.setOpaque(false);
      saloonButton.setContentAreaFilled(false);
      saloonButton.setBorder(BorderFactory.createEmptyBorder());
      saloonButton.addMouseListener(new borderMouseListener());

      // Main Street button
      JButton mainStreetButton = new JButton();
      mainStreetButton.setBounds(980, 145, 190, 38);
      mainStreetButton.setOpaque(false);
      mainStreetButton.setContentAreaFilled(false);
      mainStreetButton.setBorder(BorderFactory.createEmptyBorder());
      mainStreetButton.addMouseListener(new borderMouseListener());

      // Jail button
      JButton jailButton = new JButton();
      jailButton.setBounds(328, 145, 90, 35);
      jailButton.setOpaque(false);
      jailButton.setContentAreaFilled(false);
      jailButton.setBorder(BorderFactory.createEmptyBorder());
      jailButton.addMouseListener(new borderMouseListener());

      // General Store button
      JButton generalButton = new JButton();
      generalButton.setBounds(375, 400, 200, 38);
      generalButton.setOpaque(false);
      generalButton.setContentAreaFilled(false);
      generalButton.setBorder(BorderFactory.createEmptyBorder());
      generalButton.addMouseListener(new borderMouseListener());

      // Ranch button
      JButton ranchButton = new JButton();
      ranchButton.setBounds(287, 595, 100, 35);
      ranchButton.setOpaque(false);
      ranchButton.setContentAreaFilled(false);
      ranchButton.setBorder(BorderFactory.createEmptyBorder());
      ranchButton.addMouseListener(new borderMouseListener());

      // Secret Hideout button
      JButton secretButton = new JButton();
      secretButton.setBounds(27, 850, 208, 38);
      secretButton.setOpaque(false);
      secretButton.setContentAreaFilled(false);
      secretButton.setBorder(BorderFactory.createEmptyBorder());
      secretButton.addMouseListener(new borderMouseListener());

      // Casting Office button
      JButton castingButton = new JButton();
      castingButton.setBounds(18, 460, 195, 40);
      castingButton.setOpaque(false);
      castingButton.setContentAreaFilled(false);
      castingButton.setBorder(BorderFactory.createEmptyBorder());
      castingButton.addMouseListener(new borderMouseListener());

      // Train Station button
      JButton trainButton = new JButton();
      trainButton.setBounds(28, 185, 190,  38);
      trainButton.setOpaque(false);
      trainButton.setContentAreaFilled(false);
      trainButton.setBorder(BorderFactory.createEmptyBorder());
      trainButton.addMouseListener(new borderMouseListener());

      //------------------------------------------
      // ADDING BUTTONS TO FRAME ON VARIOUS LEVELS
      //------------------------------------------

      bPane.add(bMove, Integer.valueOf(2));
      bPane.add(bAct, Integer.valueOf(2));
      bPane.add(bRehearse, Integer.valueOf(2));
      bPane.add(bUpgrade, Integer.valueOf(2));
      bPane.add(bTakeRole, Integer.valueOf(2));
      bPane.add(bYourStats, Integer.valueOf(2));
      bPane.add(bPlayerLocations, Integer.valueOf(2));
      bPane.add(bEndTurn, Integer.valueOf(2));
      bPane.add(bEndGame, Integer.valueOf(2));
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

      //------------------------------------------
      // ADDITIONAL TEXT AREAS (CURRENT PLAYER AND ACTION LOG)
      //------------------------------------------

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

   //------------------------------------------
   // MOUSE EVENTS
   //------------------------------------------

   class boardMouseListener implements MouseListener {

      // Im not sure what to do with this yet
      public void mouseClicked(MouseEvent e) {

         if (e.getSource() == bAct) {
            playerlabel.setVisible(true);
            System.out.println("Acting is Selected\n");
         } else if (e.getSource() == bRehearse) {
            System.out.println("Rehearse is Selected\n");
         } else if (e.getSource() == bMove) {
            System.out.println("Move is Selected\n");
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
   // MouseAdapter is used when not all functionalities of MouseListener are needed to be implemented
   // I originally had the border stuff in MouseListener, but it was applying to all the buttons
   // So, keeping the functionality separate will make it easier in case I want to apply other effects to the action buttons
   class borderMouseListener extends MouseAdapter {

      // when mouse enters button area, create a border around the area for readability
      @Override
      public void mouseEntered(MouseEvent e) {
         // cast as JButton from object (e.getsource()) so we can apply the border
         JButton dummyButton = (JButton) e.getSource();
         dummyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      }

      @Override
      // once the mouse exits the button area, make the border invisible again
      public void mouseExited(MouseEvent e) {
         // cast as JButton from object (e.getsource()) so we can manipulate the border on mouse exiting
         JButton dummyButton = (JButton) e.getSource();
         dummyButton.setBorder(BorderFactory.createEmptyBorder());
      }

   }

   //------------------------------------------
   // Setting face-down scene card images at every set at the start of the game, uncovered when one person is in the room (not implemented yet)
   //------------------------------------------

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
   // playerlabel = new JLabel();
   // ImageIcon pIcon = new ImageIcon("r2.png");
   // playerlabel.setIcon(pIcon);
   // // playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());
   // playerlabel.setBounds(114, 227, 46, 46);
   // playerlabel.setVisible(false);
   // bPane.add(playerlabel, Integer.valueOf(3));

   // trailerButton.setBounds(1020, 270, 140, 40);

   public void setPlayerIcons(int numPlayers){
      
      // Initialize all the player colored dice (white dice used for actual roles)
      ImageIcon player1 = new ImageIcon("dice/dice/b1.png");
      ImageIcon player2 = new ImageIcon("dice/dice/c1.png");
      ImageIcon player3 = new ImageIcon("dice/dice/g1.png");
      ImageIcon player4 = new ImageIcon("dice/dice/o1.png");
      ImageIcon player5 = new ImageIcon("dice/dice/p1.png");
      ImageIcon player6 = new ImageIcon("dice/dice/r1.png");
      ImageIcon player7 = new ImageIcon("dice/dice/v1.png");
      ImageIcon player8 = new ImageIcon("dice/dice/y1.png");

      JLabel player1Label = new JLabel();
      player1Label.setIcon(player1);
      player1Label.setBounds(1005, 315, player1.getIconWidth(), player1.getIconHeight());
      player1Label.setVisible(false);
      bPane.add(player1Label, Integer.valueOf(3));

      JLabel player2Label = new JLabel();
      player2Label.setIcon(player2);
      player2Label.setBounds(1050, 315, player2.getIconWidth(), player2.getIconHeight());
      player2Label.setVisible(false);
      bPane.add(player2Label, Integer.valueOf(3));

      JLabel player3Label = new JLabel();
      player3Label.setIcon(player3);
      player3Label.setBounds(1095, 315, player3.getIconWidth(), player3.getIconHeight());
      player3Label.setVisible(false);
      bPane.add(player3Label, Integer.valueOf(3));

      JLabel player4Label = new JLabel();
      player4Label.setIcon(player4);
      player4Label.setBounds(1140, 315, player3.getIconWidth(), player3.getIconHeight());
      player4Label.setVisible(false);
      bPane.add(player4Label, Integer.valueOf(3));

      JLabel player5Label = new JLabel();
      player5Label.setIcon(player5);
      player5Label.setBounds(1005, 365, player3.getIconWidth(), player3.getIconHeight());
      player5Label.setVisible(false);
      bPane.add(player5Label, Integer.valueOf(3));

      JLabel player6Label = new JLabel();
      player6Label.setIcon(player6);
      player6Label.setBounds(1050, 365, player3.getIconWidth(), player3.getIconHeight());
      player6Label.setVisible(false);
      bPane.add(player6Label, Integer.valueOf(3));

      JLabel player7Label = new JLabel();
      player7Label.setIcon(player7);
      player7Label.setBounds(1095, 365, player3.getIconWidth(), player3.getIconHeight());
      player7Label.setVisible(false);
      bPane.add(player7Label, Integer.valueOf(3));

      JLabel player8Label = new JLabel();
      player8Label.setIcon(player8);
      player8Label.setBounds(1140, 365, player3.getIconWidth(), player3.getIconHeight());
      player8Label.setVisible(false);
      bPane.add(player8Label, Integer.valueOf(3));

      if (numPlayers == 2){
         // JLabel player1Label = new JLabel();
         // player1Label.setIcon(player1);
         // player1Label.setBounds(1005, 315, player1.getIconWidth(), player1.getIconHeight());
         // player1Label.setVisible(true);
         // bPane.add(player1Label, Integer.valueOf(3));

         // JLabel player2Label = new JLabel();
         // player2Label.setIcon(player2);
         // player2Label.setBounds(1050, 315, player2.getIconWidth(), player2.getIconHeight());
         // player2Label.setVisible(true);
         // bPane.add(player2Label, Integer.valueOf(3));

         player1Label.setVisible(true);
         player2Label.setVisible(true);
      }
      else if (numPlayers == 3){
         player1Label.setVisible(true);
         player2Label.setVisible(true);
         player3Label.setVisible(true);
      }

      else if(numPlayers == 4) {
         player1Label.setVisible(true);
         player2Label.setVisible(true);
         player3Label.setVisible(true);
         player4Label.setVisible(true);
      }

      else if (numPlayers == 5) {
         player1Label.setVisible(true);
         player2Label.setVisible(true);
         player3Label.setVisible(true);
         player4Label.setVisible(true);
         player5Label.setVisible(true);

      }
      else if (numPlayers == 6) {
         player1Label.setVisible(true);
         player2Label.setVisible(true);
         player3Label.setVisible(true);
         player4Label.setVisible(true);
         player5Label.setVisible(true);
         player6Label.setVisible(true);

      }
      else if (numPlayers == 7) {
         player1Label.setVisible(true);
         player2Label.setVisible(true);
         player3Label.setVisible(true);
         player4Label.setVisible(true);
         player5Label.setVisible(true);
         player6Label.setVisible(true);
         player7Label.setVisible(true);
      }
      else {
         player1Label.setVisible(true);
         player2Label.setVisible(true);
         player3Label.setVisible(true);
         player4Label.setVisible(true);
         player5Label.setVisible(true);
         player6Label.setVisible(true);
         player7Label.setVisible(true);
         player8Label.setVisible(true);
      }
   }
   
   //------------------------------------------
   // MAIN FOR TESTING
   //------------------------------------------

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

      DeadwoodView boardView = new DeadwoodView(board);
      DeadwoodController boardController = new DeadwoodController(board, boardView);
      boardView.setVisible(true);
      boardView.setSceneCardsBoard();

      // Take input from the user about number of players
      int numPlayers =Integer.parseInt(JOptionPane.showInputDialog(boardView, "How many players?"));
      boardController.setActivePlayers(numPlayers);

      boardView.setPlayerIcons(numPlayers);
   }

}


// Credit to Moushumi Sharmin for the helper file
// Imports for Swing components
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
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
   JTextArea sceneArea;
   JLayeredPane bPane;

   private Board board;
   private DeadwoodController boardController;
   private Map<String, JButton> roomButtons;
   private Map<Player, JLabel> playerLabels;
   private Map<Player, PlayerIcon> playerIcons;
   private Map<Room, ArrayList<JLabel>> takeIcons;
   private Map<Room, JLabel> roomSceneCards;

   public DeadwoodView(Board board, DeadwoodController boardController) {

      // Set the title of the JFrame
      super("Deadwood");

      System.out.println("DeadwoodView class loader: " + DeadwoodView.class.getClassLoader());

      this.board = board;
      this.boardController = boardController;
      this.boardController.setView(this);
      roomButtons = new HashMap<>();
      playerLabels = new HashMap<>();
      playerIcons = new HashMap<>();
      takeIcons = new HashMap<>();
      roomSceneCards = new HashMap<>();

      // Set the exit option for the JFrame
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      // Create the JLayeredPane to hold the display, cards, dice and buttons
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
      bMove.setBounds(icon.getIconWidth() + 15, 30, 150, 20);
      bMove.addMouseListener(new boardMouseListener(boardController.getGameState(), boardController));
      bMove.addMouseListener(new validRoomListener());

      bAct = new JButton("ACT");
      bAct.setBackground(Color.white);
      bAct.setBounds(icon.getIconWidth() + 15, 60, 150, 20);
      bAct.addMouseListener(new boardMouseListener(boardController.getGameState(), boardController));

      bRehearse = new JButton("REHEARSE");
      bRehearse.setBackground(Color.white);
      bRehearse.setBounds(icon.getIconWidth() + 15, 90, 150, 20);
      bRehearse.addMouseListener(new boardMouseListener(boardController.getGameState(), boardController));

      bUpgrade = new JButton("UPGRADE");
      bUpgrade.setBackground(Color.white);
      bUpgrade.setBounds(icon.getIconWidth() + 15, 120, 150, 20);
      bUpgrade.addMouseListener(new boardMouseListener(boardController.getGameState(), boardController));

      bTakeRole = new JButton("TAKE A ROLE");
      bTakeRole.setBackground(Color.white);
      bTakeRole.setBounds(icon.getIconWidth() + 15, 150, 150, 20);
      bTakeRole.addMouseListener(new boardMouseListener(boardController.getGameState(), boardController));

      bEndTurn = new JButton("END TURN");
      bEndTurn.setBackground(Color.white);
      bEndTurn.setBounds(icon.getIconWidth() + 15, 180, 150, 20);
      bEndTurn.addMouseListener(new boardMouseListener(boardController.getGameState(), boardController));

      // -------------------------------------
      // CREATING BUTTONS FOR PLAYERS MOVING
      // -------------------------------------

      // Trailer button
      JButton trailerButton = new JButton();
      trailerButton.setBounds(1020, 270, 140, 40);
      trailerButton.setOpaque(false);
      trailerButton.setContentAreaFilled(false);
      trailerButton.setBorder(BorderFactory.createEmptyBorder());

      // Hotel button
      JButton hotelButton = new JButton();
      hotelButton.setBounds(1010, 860, 120, 35);
      hotelButton.setOpaque(false);
      hotelButton.setContentAreaFilled(false);
      hotelButton.setBorder(BorderFactory.createEmptyBorder());

      // Church button
      JButton churchButton = new JButton();
      churchButton.setBounds(660, 855, 130, 35);
      churchButton.setOpaque(false);
      churchButton.setContentAreaFilled(false);
      churchButton.setBorder(BorderFactory.createEmptyBorder());

      // Bank button
      JButton bankButton = new JButton();
      bankButton.setBounds(680, 595, 90, 35);
      bankButton.setOpaque(false);
      bankButton.setContentAreaFilled(false);
      bankButton.setBorder(BorderFactory.createEmptyBorder());

      // Saloon button
      JButton saloonButton = new JButton();
      saloonButton.setBounds(668, 400, 130, 35);
      saloonButton.setOpaque(false);
      saloonButton.setContentAreaFilled(false);
      saloonButton.setBorder(BorderFactory.createEmptyBorder());

      // Main Street button
      JButton mainStreetButton = new JButton();
      mainStreetButton.setBounds(980, 145, 190, 38);
      mainStreetButton.setOpaque(false);
      mainStreetButton.setContentAreaFilled(false);
      mainStreetButton.setBorder(BorderFactory.createEmptyBorder());

      // Jail button
      JButton jailButton = new JButton();
      jailButton.setBounds(328, 145, 90, 35);
      jailButton.setOpaque(false);
      jailButton.setContentAreaFilled(false);
      jailButton.setBorder(BorderFactory.createEmptyBorder());

      // General Store button
      JButton generalButton = new JButton();
      generalButton.setBounds(375, 400, 200, 38);
      generalButton.setOpaque(false);
      generalButton.setContentAreaFilled(false);
      generalButton.setBorder(BorderFactory.createEmptyBorder());

      // Ranch button
      JButton ranchButton = new JButton();
      ranchButton.setBounds(287, 595, 100, 35);
      ranchButton.setOpaque(false);
      ranchButton.setContentAreaFilled(false);
      ranchButton.setBorder(BorderFactory.createEmptyBorder());

      // Secret Hideout button
      JButton secretButton = new JButton();
      secretButton.setBounds(27, 850, 208, 38);
      secretButton.setOpaque(false);
      secretButton.setContentAreaFilled(false);
      secretButton.setBorder(BorderFactory.createEmptyBorder());

      // Casting Office button
      JButton castingButton = new JButton();
      castingButton.setBounds(18, 460, 195, 40);
      castingButton.setOpaque(false);
      castingButton.setContentAreaFilled(false);
      castingButton.setBorder(BorderFactory.createEmptyBorder());

      // Train Station button
      JButton trainButton = new JButton();
      trainButton.setBounds(28, 185, 190, 38);
      trainButton.setOpaque(false);
      trainButton.setContentAreaFilled(false);
      trainButton.setBorder(BorderFactory.createEmptyBorder());

      // ------------------------------------------
      // ADDING BUTTONS TO FRAME ON VARIOUS LEVELS
      // ------------------------------------------

      bPane.add(bMove, Integer.valueOf(2));
      bPane.add(bAct, Integer.valueOf(2));
      bPane.add(bRehearse, Integer.valueOf(2));
      bPane.add(bUpgrade, Integer.valueOf(2));
      bPane.add(bTakeRole, Integer.valueOf(2));
      bPane.add(bEndTurn, Integer.valueOf(2));
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
      roomButtons.put("Office", castingButton);
      roomButtons.put("Train Station", trainButton);

      // ------------------------------------------
      // ADDITIONAL TEXT AREAS (CURRENT PLAYER AND ACTION LOG)
      // ------------------------------------------

      // Create the Current player label
      currentPlayerLabel = new JLabel("Current Player Statistics");
      currentPlayerLabel.setBounds(icon.getIconWidth() + 15, 210, 150, 20);
      bPane.add(currentPlayerLabel, Integer.valueOf(2));

      // Set the current player text area (not modifiable by user)
      activePlayer = new JTextArea();
      activePlayer.setBounds(icon.getIconWidth() + 15, 235, 250, 250);
      activePlayer.setEditable(false);
      activePlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      Font font = activePlayer.getFont();
      Font newFont = font.deriveFont(font.getStyle(), 17f);
      activePlayer.setFont(newFont);
      // activePlayer.setLineWrap(true);
      bPane.add(activePlayer, Integer.valueOf(2));

      // Create current room scene label
      JLabel currentSceneLabel = new JLabel("Current Room's Scene");
      currentSceneLabel.setBounds(icon.getIconWidth() + 15, 490, 150, 20);
      bPane.add(currentSceneLabel, Integer.valueOf(2));

      sceneArea = new JTextArea();
      sceneArea.setEditable(false);
      sceneArea.setBounds(icon.getIconWidth() + 15, 515, 250, 105);
      sceneArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      sceneArea.setLineWrap(true);
      bPane.add(sceneArea, Integer.valueOf(2));

      // Create action log label
      logLabel = new JLabel("Recent Actions");
      logLabel.setBounds(icon.getIconWidth() + 15, 630, 150, 20);
      bPane.add(logLabel, Integer.valueOf(3));

      // Create text area for action log
      textAction = new JTextArea();
      textAction.setEditable(false);
      textAction.setBounds(icon.getIconWidth() + 15, 650, 300, 250);
      textAction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      textAction.setLineWrap(true);
      bPane.add(textAction, Integer.valueOf(2));

   }

   // ------------------------------------------
   // MOUSE EVENTS
   // ------------------------------------------

   class boardMouseListener implements MouseListener {

      private GameState gameState;
      private DeadwoodController controller;

      public boardMouseListener(GameState gameState, DeadwoodController controller) {
         this.gameState = gameState;
         this.controller = controller;
      }

      public void mouseClicked(MouseEvent e) {
         if (e.getSource() == bMove) { // listener for move
            for (JButton roomButton : roomButtons.values()) {
               for (MouseListener mouseListener : roomButton.getMouseListeners()) {
                  roomButton.removeMouseListener(mouseListener);
               }
            }
            playerCheck(gameState);
         } else if (e.getSource() == bRehearse) { // listener for rehearse
            controller.playerRehearse();
            textAction.append(gameState.getActivePlayer().getName() + " has increased their practice chip count to: "
                  + gameState.getActivePlayer().getPracticeChips() + "\n");
            displayCurrentPlayer(gameState.getActivePlayer());
            playerCheck(gameState);
         } else if (e.getSource() == bAct) { // listener for act
            Player currentPlayer = gameState.getActivePlayer();
            // if statements to give the player the correct reward based on the results of
            // the roll and the players current role
            if (controller.playerAct()) {
               textAction.append("You were successful!");
               if (controller.inStarredRole()) {
                  textAction.append(currentPlayer.getName() + " is rewarded with 2 credits!\n");
                  controller.reward();
               } else {
                  textAction.append(currentPlayer.getName() + " is rewarded with a dollar and a credit!\n");
                  controller.reward();
               }
               // placing of the correct take dependent on how many are already on there
               int indexOfTake = controller.takesAppear();
               ArrayList<JLabel> takeLabels = takeIcons.get(currentPlayer.getPlayerRoom());
               JLabel label = takeLabels.get(indexOfTake);
               RoomWithScene room = (RoomWithScene) currentPlayer.getPlayerRoom();
               Area takeArea = room.getTakesList().get(indexOfTake).getTakeArea();
               label.setBounds(takeArea.getXValue(), takeArea.getYValue(), label.getWidth(), label.getHeight());
            } else {
               textAction.append("You failed!");
               if (controller.inStarredRole()) {
                  textAction.append(currentPlayer.getName() + " is not given anything.\n");
                  controller.fail();
               } else {
                  textAction.append(currentPlayer.getName() + " is given 1 dollar for trying.\n");
                  controller.fail();
               }
            }

            // checking if the scene is wrapping dependent on the number of takes
            Boolean wrapped = controller.wrapSceneCheck();
            if (wrapped) {
               // give rewards to players
               controller.wrappedReward();
               // move players from roles to just on card
               ArrayList<Player> playersMoved = controller.wrappedPlayerMove();
               for (Player player : playersMoved) {
                  updatePlayerRoom(player);
                  displayCurrentPlayer(player);
               }
               // flip card to the back
               JLabel sceneCard = roomSceneCards.get((RoomWithScene) currentPlayer.getPlayerRoom());
               sceneCard.setVisible(false);
               bPane.remove(sceneCard);
               bPane.revalidate();
               bPane.repaint();
               roomSceneCards.remove((RoomWithScene) currentPlayer.getPlayerRoom());
               // removal all shot counters
               RoomWithScene room = (RoomWithScene) currentPlayer.getPlayerRoom();
               ArrayList<JLabel> takesIcons = takeIcons.get(room);
               for (JLabel label : takesIcons) {
                  label.setBounds(10000, 10000, label.getWidth(), label.getHeight());
               }
            }

            // checking if the day should end dependent on the number of scenes that are
            // wrapped
            Boolean endOfDay = controller.checkEndDay();
            if (endOfDay) {
               // checking to see if the game should end instead of next day happening
               if (controller.checkEndGame()) {
                  // ArrayList of player and their total
                  ArrayList<Object> results = controller.endGameResults();
                  int rows = results.size();
                  int columns = 5;
                  String[] columnLabels = { "Name", "Dollars", "Credits", "Rank", "Total Points" };
                  // Creates an object that stores all the players data to be displayed
                  Object[][] cellData = new Object[rows][columns];
                  for (int i = 0; i < rows; i++) {
                     ArrayList tempObject = (ArrayList) results.get(i);
                     Player player = (Player) tempObject.get(0);
                     int total = (int) tempObject.get(1);
                     cellData[i][0] = player.getName();
                     cellData[i][1] = player.getDollars();
                     cellData[i][2] = player.getCredits();
                     cellData[i][3] = player.getRank();
                     cellData[i][4] = total;
                  }
                  // displaying all the player data
                  JTable table = new JTable(cellData, columnLabels);
                  JScrollPane scrollPane = new JScrollPane(table);
                  JPanel panel = new JPanel(new BorderLayout());
                  panel.setBorder(BorderFactory.createTitledBorder("End of Game Results"));
                  panel.add(scrollPane, BorderLayout.CENTER);
                  // displaying the winner
                  // Create and customize the JLabel
                  ArrayList winnerObject = (ArrayList) results.get(0);
                  Player winner = (Player) winnerObject.get(0);
                  int total = (int) winnerObject.get(1);
                  JLabel label = new JLabel(winner.getName() + " won with " + total + " total points!");
                  label.setOpaque(true);
                  label.setBackground(Color.GREEN);
                  label.setHorizontalAlignment(SwingConstants.CENTER);
                  // Add the JLabel to the panel
                  panel.add(label, BorderLayout.SOUTH);
                  // displaying the popup
                  JFrame frame = new JFrame("End of Game Results");
                  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  frame.setSize(400, 300);
                  frame.add(panel);
                  frame.setVisible(true);
               } else {
                  controller.moveAll2Trailer();
                  controller.nextDayGamestate();
                  // visually move all players back to trailer park
                  for (Player player : gameState.getPlayers()) {
                     player.setPlayerRoom(gameState.getBoard().getRoomFromBoard("Trailer"));
                     updatePlayerRoom(player);
                  }
                  // flip all scene cards back over
                  for (Room room : gameState.getBoard().getBoardLayout().values()) {
                     if (room instanceof RoomWithScene) {
                        RoomWithScene flipRoom = (RoomWithScene) room;
                        if (roomSceneCards.containsKey(flipRoom)) {
                           JLabel sceneCard = roomSceneCards.get(flipRoom);
                           sceneCard.setVisible(false);
                           bPane.remove(sceneCard);
                           bPane.revalidate();
                           bPane.repaint();
                           roomSceneCards.remove(currentPlayer.getPlayerRoom());
                        }
                     }
                  }
               }

            }
            playerCheck(gameState);
         } else if (e.getSource() == bTakeRole) { // listener for taking a role
            JLabel playerLabel = playerLabels.get(gameState.getActivePlayer());
            RoomWithScene currentRoom = (RoomWithScene) gameState.getActivePlayer().getPlayerRoom();
            Area sceneArea = currentRoom.getSceneRoomArea();
            // find roles the player can take for on and off the card roles
            List<Role> onCardRoles = controller.availableOnCardRoles();
            List<Role> offCardRoles = controller.availableOffCardRoles();
            // displaying the roles in a selectable manor
            // first creating a frame for the roles
            JFrame frame = new JFrame("Available Roles");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new GridLayout(0, 1));
            // creating a panel to be placed on the frame for all roles that are on card
            // roles
            JPanel onCardPanel = new JPanel();
            onCardPanel.setLayout(new BoxLayout(onCardPanel, BoxLayout.Y_AXIS));
            onCardPanel.setBorder(BorderFactory.createTitledBorder("On Card Roles"));
            for (Role role : onCardRoles) {
               JButton button = new JButton(role.getRank() + ": " + role.getRoleName());
               button.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                     // give player the role thats selected
                     controller.giveRoleToPlayer(role);
                     textAction.append(gameState.getActivePlayer() + " has taken the role of "
                           + gameState.getActivePlayer().getActiveRole().getRoleName() + "\n");
                     // setting the players die to the appropriate role location
                     Area roleArea = role.getRoleArea();
                     int newX = sceneArea.getXValue() + roleArea.getXValue();
                     int newY = sceneArea.getYValue() + roleArea.getYValue();
                     playerLabel.setBounds(newX + 3, newY + 3, playerLabel.getIcon().getIconWidth(),
                           playerLabel.getIcon().getIconHeight());
                     role.setPlayerOnRole(gameState.getActivePlayer());
                     playerCheck(gameState);
                     frame.dispose();
                  }
               });
               onCardPanel.add(button);
            }
            frame.add(onCardPanel);

            // creating a panel for the off card roles
            JPanel offCardPanel = new JPanel();
            offCardPanel.setLayout(new BoxLayout(offCardPanel, BoxLayout.Y_AXIS));
            offCardPanel.setBorder(BorderFactory.createTitledBorder("Off Card Roles"));
            // adding the roles to the panel
            for (Role role : offCardRoles) {
               JButton button = new JButton(role.getRank() + ": " + role.getRoleName());
               button.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                     // give player role thats selected
                     controller.giveRoleToPlayer(role);
                     textAction.append(gameState.getActivePlayer().getName() + " has taken the role of "
                           + gameState.getActivePlayer().getActiveRole().getRoleName() + "\n");
                     // moving the player icon to the role location
                     Area roleArea = role.getRoleArea();
                     playerLabel.setBounds(roleArea.getXValue() + 3, roleArea.getYValue() + 3,
                           playerLabel.getIcon().getIconWidth(), playerLabel.getIcon().getIconHeight());
                     role.setPlayerOnRole(gameState.getActivePlayer());
                     playerCheck(gameState);
                     frame.dispose();
                  }
               });
               offCardPanel.add(button);
            }
            frame.add(offCardPanel);
            frame.setVisible(true);
            playerCheck(gameState);
         } else if (e.getSource() == bEndTurn) { // listener for ending turn
            gameState.endTurn();
            // wiping the textAction box at the end of the turn
            textAction.setText("");
            textAction.append("It is now " + gameState.getActivePlayer().getName() + "'s turn!\n");
            displayCurrentPlayer(gameState.getActivePlayer());
            playerCheck(gameState);
         } else if (e.getSource() == bUpgrade) { // listener for upgrading rank
            Player activePlayer = gameState.getActivePlayer();
            JLabel playerLabel = playerLabels.get(activePlayer);
            // create frame for all the available upgrades
            JFrame frame = new JFrame("Available Upgrades");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(400, 300);
            JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
            frame.setLocationRelativeTo(null);
            // this ArrayList of ArrayList of objects contains all the values needed to
            // display the available upgrades
            // the order is rank 2 dollar, rank 2 credit, rank 3 dollar, rank 3 credit...
            // rank 6 dollar, rank 6 credit,
            // first value in object: 0 or 1 if the player can upgrade to that rank
            // second value in object: String, rank *rank* costs *amount* (dollars or
            // credits)
            ArrayList<ArrayList<Object>> availableUpgrades = controller.availableUpgrades();
            for (int i = 0; i < 10; i++) {
               // setting up the buttons so all the options that a player can do are normal
               // buttons
               // all the options a player cannot select are red
               if ((Integer) availableUpgrades.get(i).get(0) == 1) {
                  JButton button = new JButton((String) availableUpgrades.get(i).get(1));
                  int optionNumber = i;
                  button.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e) {
                        activePlayer.setPlayerRank((int) Math.floor(optionNumber / 2 + 2));
                        textAction.append(activePlayer.getName() + " is now rank " + activePlayer.getRank() + "\n");
                        int index = -1;
                        for (int i = 0; i < gameState.getPlayers().size(); i++) {
                           Player player = gameState.getPlayers().get(i);
                           if (activePlayer == player) {
                              index = i;
                           }
                        }
                        // This changes the visual dice face to be the correct color and new rank after
                        // upgrading
                        String[] colors = { "b", "c", "g", "o", "p", "r", "v", "y" };
                        ImageIcon newRankIcon = new ImageIcon(
                              "dice/dice/" + colors[index] + ((int) Math.floor(optionNumber / 2 + 2)) + ".png");
                        playerLabel.setIcon(newRankIcon);
                        frame.dispose();
                     }
                  });
                  panel.add(button);
               } else {
                  JButton button = new JButton((String) availableUpgrades.get(i).get(1));
                  button.setEnabled(false);
                  button.setBackground(Color.RED);
                  panel.add(button);
               }
            }
            frame.add(panel);
            frame.setVisible(true);
            playerCheck(gameState);
         }
         playerCheck(gameState);
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

   // disables action buttons depending on active players ability to do those
   // actions
   // this prevents players from attempting to do things that they cannot do within
   // the rules
   public void playerCheck(GameState gameState) {
      Player currentPlayer = gameState.getActivePlayer();
      // bMove
      if (!currentPlayer.getHasMoved() && currentPlayer.getActiveRole() == null) {
         bMove.setEnabled(true);
      } else {
         bMove.setEnabled(false);
      }
      if (currentPlayer.getPlayerRoom() instanceof RoomWithScene) {
         // bAct
         if (!currentPlayer.getHasActed() && currentPlayer.getActiveRole() != null) {
            System.out.println("Get to act");
            bAct.setEnabled(true);
         } else {
            bAct.setEnabled(false);
         }
         // bRehearse
         if (!currentPlayer.getHasActed() && currentPlayer.getActiveRole() != null && currentPlayer
               .getPracticeChips() < ((RoomWithScene) currentPlayer.getPlayerRoom()).getSceneCard().getBudget() - 1) {
            System.out.println("Get to rehearse");
            bRehearse.setEnabled(true);
         } else {
            bRehearse.setEnabled(false);
         }
         // bTakeRole
         if (currentPlayer.getActiveRole() == null && !((RoomWithScene)currentPlayer.getPlayerRoom()).getRoomScene().getSceneWrapped()) {
            bTakeRole.setEnabled(true);
         } else {
            bTakeRole.setEnabled(false);
         }
      } else {
         bAct.setEnabled(false);
         bRehearse.setEnabled(false);
         bTakeRole.setEnabled(false);
      }
      // bUpgrade
      if (currentPlayer.getPlayerRoom().equals(gameState.getBoard().getRoomFromBoard("Casting Office"))) {
         bUpgrade.setEnabled(true);
      } else {
         bUpgrade.setEnabled(false);
      }
      // bEndTurn
      bEndTurn.setEnabled(true);
   }

   // when mouse enters button area, create a border around the area for
   // readability
   class validRoomListener extends MouseAdapter {

      @Override
      public void mouseClicked(MouseEvent e) {
         System.out.println("Move button clicked");
         boardController.moveOption();
         playerCheck(boardController.getGameState());
      }
   }

   // perform the actual move
   class newRoomListener extends MouseAdapter {
      private String neighborName;

      public newRoomListener(String neighborName) {
         this.neighborName = neighborName;
      }

      @Override
      public void mouseClicked(MouseEvent e) {
         boardController.performMove(neighborName);
         playerCheck(boardController.getGameState());
      }
   }

   // set the Scene Cards (facedown) at the beginning of the game
   public void setSceneCardsBoard() {
      for (Room boardRoom : board.getBoardLayout().values()) {
         // get the area of each RoomWithScene
         if (boardRoom instanceof RoomWithScene) {
            RoomWithScene sceneRoom = (RoomWithScene) boardRoom;
            Area sceneArea = sceneRoom.getSceneRoomArea();

            // place Cardback-small.jpg at each area spot
            JLabel cardBackImage = new JLabel();
            ImageIcon sceneCardDown = new ImageIcon("images/CardBack-small.jpg");
            cardBackImage.setIcon(sceneCardDown);
            // setting the bounds using the stored area from XML
            cardBackImage.setBounds(sceneArea.getXValue(), sceneArea.getYValue(), sceneArea.getWidth(),
                  sceneArea.getHeight());
            bPane.add(cardBackImage, Integer.valueOf(2));

         }
      }
   }

   // Flip a scene card when someone enters a room and they are the first ones
   // there
   // Puts the appropriate scene to the room image on the board on top of the
   // flipped one and set visible
   public void flipSceneCard(RoomWithScene sceneRoom) {
      System.out.println("in flip");
      JLabel flippedCard = new JLabel();
      ImageIcon flippedCardImage = new ImageIcon(sceneRoom.getSceneCard().getImage());
      flippedCard.setIcon(flippedCardImage);
      flippedCard.setBounds(sceneRoom.getSceneRoomArea().getXValue(), sceneRoom.getSceneRoomArea().getYValue(),
            flippedCardImage.getIconWidth(), flippedCardImage.getIconHeight());
      flippedCard.setVisible(true);
      bPane.add(flippedCard, Integer.valueOf(3));
      roomSceneCards.put(sceneRoom, flippedCard);
   }

   // General display message that can be called from controller
   public void displayGameMessage(String gameMessage) {
      JOptionPane.showMessageDialog(this, gameMessage);
   }

   // Update player room on the board
   public void updatePlayerRoom(Player activePlayer) {
      // get the active players label (we need to change its bounds)
      JLabel playerLabel = playerLabels.get(activePlayer);
      // get the players current room and set it as a Room object
      Room currentPlayerRoom = activePlayer.getPlayerRoom();

      Area roomArea = null;
      RoomWithScene sceneRoom = null;
      int playerCounter = 0;

      // I realized I stored the areas of each room from the parser into 3 different
      // objects
      // rooms with scenes are RoomWithScene objects, Trailer is Room object, Casting
      // Office is CastingOffice object
      // If the room is a RoomWithScene, call Scene Room specific area getter
      if (currentPlayerRoom instanceof RoomWithScene) {
         sceneRoom = (RoomWithScene) currentPlayerRoom;
         roomArea = sceneRoom.getSceneRoomArea();
         playerCounter = sceneRoom.getCounter();
         if (playerCounter == 1 && !sceneRoom.getRoomScene().getSceneWrapped()) {
            flipSceneCard(sceneRoom);
         }
      }
      // if room is CastingOffice, call CastingOffice specific area getter
      else if (currentPlayerRoom instanceof CastingOffice) {
         CastingOffice castingOfficeRoom = (CastingOffice) currentPlayerRoom;
         roomArea = castingOfficeRoom.getOfficeArea();
         playerCounter = castingOfficeRoom.getCounter();
      }
      // else it's just a room (Trailer), get the room area
      else {
         roomArea = currentPlayerRoom.getRoomArea();
         playerCounter = currentPlayerRoom.getCounter();
      }

      int iconShift = 0;
      if (playerCounter >= 2) {
         iconShift = (playerCounter - 1) * 30;
      }

      // set the new bounds of the player on top of the room, and set the icon visible
      // in that area
      playerLabel.setBounds((roomArea.getXValue() - 20) + iconShift, roomArea.getYValue(),
            playerLabel.getIcon().getIconWidth(), playerLabel.getIcon().getIconHeight());
      playerLabel.setVisible(true);

   }

   // Setting the player icons based on starting rank and create labels for them
   public void setPlayerIcons(ArrayList<Player> playerList) {
      // Initialize all the player colored dice (white dice used for actual roles)
      ArrayList<PlayerIcon> playerIconList = new ArrayList<>();

      // Rank 1 icons
      ImageIcon player1Rank1 = new ImageIcon("dice/dice/b1.png");
      ImageIcon player2Rank1 = new ImageIcon("dice/dice/c1.png");
      ImageIcon player3Rank1 = new ImageIcon("dice/dice/g1.png");
      ImageIcon player4Rank1 = new ImageIcon("dice/dice/o1.png");
      ImageIcon player5Rank1 = new ImageIcon("dice/dice/p1.png");
      ImageIcon player6Rank1 = new ImageIcon("dice/dice/r1.png");
      ImageIcon player7Rank1 = new ImageIcon("dice/dice/v1.png");
      ImageIcon player8Rank1 = new ImageIcon("dice/dice/y1.png");

      // Rank 2 icons
      ImageIcon player1Rank2 = new ImageIcon("dice/dice/b2.png");
      ImageIcon player2Rank2 = new ImageIcon("dice/dice/c2.png");
      ImageIcon player3Rank2 = new ImageIcon("dice/dice/g2.png");
      ImageIcon player4Rank2 = new ImageIcon("dice/dice/o2.png");
      ImageIcon player5Rank2 = new ImageIcon("dice/dice/p2.png");
      ImageIcon player6Rank2 = new ImageIcon("dice/dice/r2.png");
      ImageIcon player7Rank2 = new ImageIcon("dice/dice/v2.png");
      ImageIcon player8Rank2 = new ImageIcon("dice/dice/y2.png");

      // If the number of players is equal to 7, give them all a rank 2 die
      if (playerList.size() == 7) {
         playerIconList.add(new PlayerIcon(player1Rank2, 1005, 315));
         playerIconList.add(new PlayerIcon(player2Rank2, 1050, 315));
         playerIconList.add(new PlayerIcon(player3Rank2, 1095, 315));
         playerIconList.add(new PlayerIcon(player4Rank2, 1140, 315));
         playerIconList.add(new PlayerIcon(player5Rank2, 1005, 365));
         playerIconList.add(new PlayerIcon(player6Rank2, 1050, 365));
         playerIconList.add(new PlayerIcon(player7Rank2, 1095, 365));
      }
      // If number of players is equal to 8, give them all rank 2 die
      else if (playerList.size() == 8) {
         playerIconList.add(new PlayerIcon(player1Rank2, 1005, 315));
         playerIconList.add(new PlayerIcon(player2Rank2, 1050, 315));
         playerIconList.add(new PlayerIcon(player3Rank2, 1095, 315));
         playerIconList.add(new PlayerIcon(player4Rank2, 1140, 315));
         playerIconList.add(new PlayerIcon(player5Rank2, 1005, 365));
         playerIconList.add(new PlayerIcon(player6Rank2, 1050, 365));
         playerIconList.add(new PlayerIcon(player7Rank2, 1095, 365));
         playerIconList.add(new PlayerIcon(player8Rank2, 1140, 365));
      }
      // Otherwise make all the player icons even if we don't use them all with rank 1
      else {
         playerIconList.add(new PlayerIcon(player1Rank1, 1005, 315));
         playerIconList.add(new PlayerIcon(player2Rank1, 1050, 315));
         playerIconList.add(new PlayerIcon(player3Rank1, 1095, 315));
         playerIconList.add(new PlayerIcon(player4Rank1, 1140, 315));
         playerIconList.add(new PlayerIcon(player5Rank1, 1005, 365));
         playerIconList.add(new PlayerIcon(player6Rank1, 1050, 365));
         playerIconList.add(new PlayerIcon(player7Rank1, 1095, 365));
         playerIconList.add(new PlayerIcon(player8Rank1, 1140, 365));
      }

      // Iterate through the players and get the player objects and assign them to
      // appropriate JLabels
      // Put them in the hashmap for when we need them
      for (int i = 0; i < playerList.size(); i++) {
         // Get the Players from the input and the initialized PlayerIcons in the
         // ArrayList associated with them
         Player newPlayer = playerList.get(i);
         PlayerIcon newPlayerInfo = playerIconList.get(i);
         // Create labels for all of the Player Icons, grab the bounds from the objects
         JLabel playerLabel = new JLabel();
         playerLabel.setIcon(newPlayerInfo.getPlayerIcon());
         playerLabel.setBounds(newPlayerInfo.getXCord(), newPlayerInfo.getYCord(),
               newPlayerInfo.getPlayerIcon().getIconWidth(), newPlayerInfo.getPlayerIcon().getIconHeight());
         playerLabel.setVisible(true);
         // Add it to the frame and add the players to our HashMap
         bPane.add(playerLabel, Integer.valueOf(3));
         playerLabels.put(newPlayer, playerLabel);
         playerIcons.put(newPlayer, newPlayerInfo);
      }
   }

   // Initialization of all takes for each room that are saved in takeIcons
   // for all the takes that a room can have it creates a unique icon for each take
   // and adds them to an array
   // all the icons are defaulted to be placed at 10000, 10000 so they are off
   // screen and wont appear until moved
   public void takesInitialization(GameState gameState) {
      Board board = gameState.getBoard();
      for (Map.Entry<String, Room> room : board.getBoardLayout().entrySet()) {
         // make sure that a room can have takes
         if (room.getValue() instanceof RoomWithScene) {
            RoomWithScene roomWithScene = (RoomWithScene) room.getValue();
            int numberOfTakes = roomWithScene.getTakesList().size();
            // creates a take icon for each take in the room
            for (int i = 0; i < numberOfTakes; i++) {
               JLabel takeIcon = new JLabel();
               ImageIcon takeImage = new ImageIcon("images/shot.png");
               takeIcon.setIcon(takeImage);
               takeIcon.setBounds(10000, 10000, takeImage.getIconWidth(), takeImage.getIconHeight());
               takeIcon.setVisible(true);
               bPane.add(takeIcon, Integer.valueOf(3));
               // adds the take icons to an ArrayList to be accessed later
               if (takeIcons.containsKey(roomWithScene)) {
                  takeIcons.get(roomWithScene).add(takeIcon);
               } else {
                  ArrayList<JLabel> takesList = new ArrayList<>();
                  takesList.add(takeIcon);
                  takeIcons.put(roomWithScene, takesList);
               }
            }
         }
      }
   }

   // Retrieving the die color based on the letter in the image pathway for
   // displaying on the board
   public String getDieColor(Player currentPlayer) {
      String color = "Nothing";
      PlayerIcon playerIcon = playerIcons.get(currentPlayer);
      ImageIcon playerImage = playerIcon.getPlayerIcon();
      // getDescription retrieves the path to the image "dice/dice/whatever1.png"
      String iconImagePath = playerImage.getDescription();
      if (iconImagePath.contains("b")) {
         color = "Blue";
      } else if (iconImagePath.contains("c")) {
         color = "Cyan";
      } else if (iconImagePath.contains("g")) {
         color = "Green";
      } else if (iconImagePath.contains("o")) {
         color = "Orange";
      } else if (iconImagePath.contains("p")) {
         color = "Pink";
      } else if (iconImagePath.contains("r")) {
         color = "Red";
      } else if (iconImagePath.contains("v")) {
         color = "Violet";
      } else {
         color = "Yellow";
      }
      return color;
   }

   // display the current players stats in the activePlayer textarea on the board
   // Displays all information pulled from Player object, and also their associated
   // die color
   public void displayCurrentPlayer(Player currentPlayer) {
      String name = currentPlayer.getName();
      int rank = currentPlayer.getRank();
      int dollars = currentPlayer.getDollars();
      int credits = currentPlayer.getCredits();
      int practiceChips = currentPlayer.getPracticeChips();
      String currentRole = "";
      if (currentPlayer.getActiveRole() == null) {
         currentRole = "None";
      } else {
         currentRole = currentPlayer.getActiveRole().getRoleName();
      }
      // currentRole = currentPlayer.getActiveRole().getRoleName();
      String color = getDieColor(currentPlayer);
      String currentRoom = currentPlayer.getPlayerRoom().getName();
      String playerStats = String.format(
            " Name: %s\n Rank: %d\n Dollars: %d\n Credits: %d\n Practice Chips: %d\n Active Role: %s\n Color: %s\n Current Room: %s",
            name, rank, dollars, credits, practiceChips, currentRole, color, currentRoom);
      activePlayer.setText(playerStats);
   }

   public void displayCurrentScene() {
      Player currentPlayer = boardController.getGameState().getActivePlayer();
      Room currentRoom = currentPlayer.getPlayerRoom();
      if (currentRoom instanceof RoomWithScene) {
         RoomWithScene currentSceneRoom = (RoomWithScene) currentRoom;
         SceneCard activeSceneCard = currentSceneRoom.getSceneCard();
         String sceneName = activeSceneCard.getName();
         String sceneDescription = activeSceneCard.getDescription();
         int sceneBudget = activeSceneCard.getBudget();
         String activeSceneInfo = String.format(" Scene Name: %s\n Description: %s\n Budget: %d million\n", sceneName,
               sceneDescription, sceneBudget);
         sceneArea.setText(activeSceneInfo);
      } else {
         sceneArea.setText("No scene in the room");
      }
   }

   // Showing valid rooms a player can move to once the "move" button is pressed
   public void showValidRooms(ArrayList<String> neighbors) {
      // System.out.println("Showing valid rooms: " + neighbors);
      resetRoomButtons();
      // Iterate through the Hashmap of neighbor strings and their associated buttons
      for (Map.Entry<String, JButton> neighborEntry : roomButtons.entrySet()) {
         // Grab neighbor name string
         String neighborName = neighborEntry.getKey();
         // Grab the associated JButton
         JButton neighborButton = neighborEntry.getValue();
         // If the String and the button line up, then this is a valid move and change
         // the border to green and enable it
         if (neighbors.contains(neighborName)) {
            neighborButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            neighborButton.setEnabled(true);
            neighborButton.addMouseListener(new newRoomListener(neighborName));
         }
         // else{
         // // If not a valid neighbor to move to, disable the button
         // neighborButton.setBorder(BorderFactory.createEmptyBorder());
         // neighborButton.setEnabled(false);
         // }
      }
   }

   public void resetRoomButtons() {
      for (JButton roomButton : roomButtons.values()) {
         roomButton.setEnabled(false);
         roomButton.setBorder(BorderFactory.createEmptyBorder());
      }
   }
}

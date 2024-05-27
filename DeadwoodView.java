/*

   Deadwood GUI helper file
   Author: Moushumi Sharmin
   This file shows how to create a simple GUI using Java Swing and Awt Library
   Classes Used: JFrame, JLabel, JButton, JLayeredPane

*/

// Imports for Swing components
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.border.*;

// Extends Jframe (creates window)
public class DeadwoodView extends JFrame {

  // JLabels (text/images)
  JLabel boardlabel;
  JLabel cardlabel;
  JLabel playerlabel;
  JLabel mLabel;
  JLabel currentPlayerLabel;
  JLabel logLabel;
  
  //JButtons (buttons)
  JButton bAct;
  JButton bRehearse;
  JButton bMove;
  JButton bUpgrade;
  JButton bTakeRole;
  JButton bYourStats;
  JButton bPlayerLocations;
  JButton bEndTurn;
  JButton bEndGame;

  JTextArea activePlayer;
  JTextArea textAction;
  
  // JLayered Pane (for having multiple layers)
  JLayeredPane bPane;
  
  // Constructor
  
  public DeadwoodView() {
      
       // Set the title of the JFrame
       super("Deadwood");
       // Set the exit option for the JFrame
       setDefaultCloseOperation(EXIT_ON_CLOSE);
      
       // Create the JLayeredPane to hold the display, cards, dice and buttons
      //  stacking order
       bPane = getLayeredPane();
    
       // Create the deadwood board
       boardlabel = new JLabel();
       ImageIcon icon =  new ImageIcon("images/board.jpg");
       boardlabel.setIcon(icon); 
       boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
      
       // Add the board to the lowest layer
       bPane.add(boardlabel, Integer.valueOf(0));
      
       // Set the size of the GUI
       setSize(icon.getIconWidth()+200,icon.getIconHeight()+00);
       
       // Add a scene card to this room
       cardlabel = new JLabel();
       ImageIcon cIcon =  new ImageIcon("01.png");
       cardlabel.setIcon(cIcon); 
       cardlabel.setBounds(20,65,cIcon.getIconWidth()+2,cIcon.getIconHeight());
       cardlabel.setOpaque(true);
      
       // Add the card to the lower layer
       bPane.add(cardlabel, Integer.valueOf(1));
    
       // Add a dice to represent a player. 
       // Role for Crusty the prospector. The x and y co-ordiantes are taken from Board.xml file
       playerlabel = new JLabel();
       ImageIcon pIcon = new ImageIcon("r2.png");
       playerlabel.setIcon(pIcon);
       //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());  
       playerlabel.setBounds(114,227,46,46);
       playerlabel.setVisible(false);
       bPane.add(playerlabel,Integer.valueOf(3));
      
       // Create the Menu for action buttons
       mLabel = new JLabel("Player Actions");
       mLabel.setBounds(icon.getIconWidth()+20,0,150,20);
       bPane.add(mLabel,Integer.valueOf(2));

       // Create Action buttons

       bMove = new JButton("MOVE");
       bMove.setBackground(Color.white);
       bMove.setBounds(icon.getIconWidth()+15,90,150, 20);
       bMove.addMouseListener(new boardMouseListener());

       bAct = new JButton("ACT");
       bAct.setBackground(Color.white);
       bAct.setBounds(icon.getIconWidth()+15, 30,150, 20);
       bAct.addMouseListener(new boardMouseListener());
       
       bRehearse = new JButton("REHEARSE");
       bRehearse.setBackground(Color.white);
       bRehearse.setBounds(icon.getIconWidth()+15,60,150, 20);
       bRehearse.addMouseListener(new boardMouseListener());
       
       bUpgrade = new JButton("UPGRADE");
       bUpgrade.setBackground(Color.white);
       bUpgrade.setBounds(icon.getIconWidth()+15,90,150, 20);
       bUpgrade.addMouseListener(new boardMouseListener());

       bTakeRole = new JButton("TAKE A ROLE");
       bTakeRole.setBackground(Color.white);
       bTakeRole.setBounds(icon.getIconWidth()+15,120,150, 20);
       bTakeRole.addMouseListener(new boardMouseListener());

       bYourStats = new JButton("YOUR STATS");
       bYourStats.setBackground(Color.white);
       bYourStats.setBounds(icon.getIconWidth()+15,150,150, 20);
       bYourStats.addMouseListener(new boardMouseListener());

       bPlayerLocations = new JButton("DISPLAY PLAYERS");
       bPlayerLocations.setBackground(Color.white);
       bPlayerLocations.setBounds(icon.getIconWidth()+15,180,150, 20);
       bPlayerLocations.addMouseListener(new boardMouseListener());

       bEndTurn = new JButton("END TURN");
       bEndTurn.setBackground(Color.white);
       bEndTurn.setBounds(icon.getIconWidth()+15,210,150, 20);
       bEndTurn.addMouseListener(new boardMouseListener());

       bEndGame = new JButton("END GAME");
       bEndGame.setBackground(Color.white);
       bEndGame.setBounds(icon.getIconWidth()+15,240,150, 20);
       bEndGame.addMouseListener(new boardMouseListener());

       // Place the action buttons in the top layer
       bPane.add(bAct, Integer.valueOf(2));
       bPane.add(bRehearse, Integer.valueOf(2));
       bPane.add(bMove, Integer.valueOf(2));
       bPane.add(bUpgrade, Integer.valueOf(2));
       bPane.add(bTakeRole, Integer.valueOf(2));
       bPane.add(bYourStats, Integer.valueOf(2));
       bPane.add(bPlayerLocations, Integer.valueOf(2));
       bPane.add(bEndTurn, Integer.valueOf(2));
       bPane.add(bEndGame, Integer.valueOf(2));

       currentPlayerLabel = new JLabel("Who's Turn Is It?");
       currentPlayerLabel.setBounds(icon.getIconWidth()+15, 300, 150, 20);
       bPane.add(currentPlayerLabel, Integer.valueOf(2));

       activePlayer = new JTextArea();
       activePlayer.setBounds(icon.getIconWidth()+15, 320, 150, 20);
       activePlayer.setEditable(false);
       activePlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       bPane.add(activePlayer, Integer.valueOf(2));

       logLabel = new JLabel("Recent Actions");
       logLabel.setBounds(icon.getIconWidth()+15, 350, 150, 20);
       bPane.add(logLabel, Integer.valueOf(2));

       textAction = new JTextArea();
       textAction.setEditable(false);
       textAction.setBounds(icon.getIconWidth()+15, 370, 200, 250);
       textAction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       bPane.add(textAction, Integer.valueOf(2));
  }
  
  // This class implements Mouse Events
  
  class boardMouseListener implements MouseListener{
  
      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {
         
         if (e.getSource()== bAct){
            playerlabel.setVisible(true);
            System.out.println("Acting is Selected\n");
         }
         else if (e.getSource()== bRehearse){
            System.out.println("Rehearse is Selected\n");
         }
         else if (e.getSource()== bMove){
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


  public static void main(String[] args) {
  
    DeadwoodView board = new DeadwoodView();
    board.setVisible(true);
    
    // Take input from the user about number of players
    JOptionPane.showInputDialog(board, "How many players?"); 
  }
}

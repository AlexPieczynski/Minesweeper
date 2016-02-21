//A class to implement Minesweeper board and GUI. 
//MineSweeper Images were spliced by Daniel Hajnos and originally ripped by user Black Squirrel.
//Created for CS 342 Spring 2016- Project 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MineSweeperBoard {
  
  private MineSweeperButton[][] boardSquares = new MineSweeperButton [10][10];
  private JPanel mineSweeperGrid;
  private JPanel mineSweeperGUI= new JPanel(new BorderLayout(3, 3));
  private JPanel mineSweeperDisplay;
  
  //Image array variables to hold the various images for the board 
  private Image[] gridNumberIcons = new Image[8]; //16x16 gifs
  private Image[] bombIcons= new Image[3]; //16x16 gifs
  private Image[] flagIcons = new Image[2]; //16x16 gifs
  private Image buttonIcons [] = new Image[2]; //16x16 gifs
  
  private Image[] countDownIcons = new Image[10]; //13x23 gifs
  
  private Image[] smileyIcons = new Image[5]; //26x26 gifs
  
  public enum buttonState {NEUTRAL, FLAGGED, Q_MARKED}
  
  
  public MineSweeperBoard(){
    initializeBoard();
  }
  
  private class MineSweeperButton extends JButton{ //Nested class for button on the MineSweeperBoard.
    boolean hasBomb;
    buttonState state;
    
    MineSweeperButton(){
      super();
    }
  }
  
  public void initializeBoard(){
    
    mineSweeperGUI.setBorder(new EmptyBorder(5,5,5,5));
    
    JToolBar optionsMenu = new JToolBar();
    optionsMenu.setFloatable(false);
    mineSweeperGUI.add(optionsMenu, BorderLayout.PAGE_START);
    
    Action gameAction = new AbstractAction("New") {
    @Override
     public void actionPerformed(ActionEvent e) {
      startNewGame();
      }
     };
    
     optionsMenu.add(gameAction);
     optionsMenu.add(new JButton("Game")); 
     optionsMenu.add(new JButton("Help"));
     
     mineSweeperGrid = new JPanel(new GridLayout(0,10)){ 
       @Override //Overriding the getPreferredSize method
       public final Dimension getPreferredSize() {
         Dimension d = super.getPreferredSize();
         Dimension prefSize = null;
         Component c = getParent();
         if (c == null) {
           prefSize = new Dimension((int)d.getWidth(),(int)d.getHeight());
         } 
         else if (c!=null && c.getWidth()>d.getWidth() && c.getHeight()>d.getHeight()) {
           prefSize = c.getSize();
         } 
         else {
           prefSize = d;
         }
         int w = (int) prefSize.getWidth();
         int h = (int) prefSize.getHeight();
         int s = (w>h ? h : w); //Get the smalller of the two sizes
         return new Dimension(s,s);//
       } //End of getPreferredSize() --> The new Dimension will be given to GridBagLayout object.
     };
     
     mineSweeperDisplay = new JPanel();
     mineSweeperDisplay.setLayout(new BoxLayout(mineSweeperDisplay, BoxLayout.X_AXIS));
          //-----------TODO---------------- Put in Smiley face and score display
     mineSweeperDisplay.add(new JButton("*Smiley and Score display*"));
     
     mineSweeperGrid.setBorder(new EmptyBorder(10,10,10,10));
     
     mineSweeperGUI.add(mineSweeperDisplay, BorderLayout.NORTH);
     mineSweeperGUI.add(mineSweeperGrid, BorderLayout.SOUTH);

     
     
     Insets buttonMargin = new Insets(0, 0, 0, 0); //Create buttons for the board.
        for (int ii = 0; ii < boardSquares.length; ii++) {
            for (int jj = 0; jj < boardSquares[ii].length; jj++) {
                MineSweeperButton b = new MineSweeperButton();
                b.setMargin(buttonMargin);
                //Create 16x16 transparent image squares to use as board buttons.
                ImageIcon icon = new ImageIcon(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(Color.GRAY);
                boardSquares[jj][ii] = b;
            }
        }
     //Fill in the board with the newly created gray squares.   
     for (int ii = 0; ii < 10; ii++) {
       for (int jj = 0; jj < 10; jj++) {
         mineSweeperGrid.add(boardSquares[jj][ii]);
       }
     }
     
  }//End of initializeBoard()
  
  
  public void startNewGame(){
     
  }
  
  public final JPanel getBoardGUI(){
    return mineSweeperGUI;
  }
  
}

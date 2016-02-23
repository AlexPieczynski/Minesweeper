//A class to implement Minesweeper board and GUI. 
//MineSweeper Images were spliced by Daniel Hajnos and originally ripped by user Black Squirrel.
//Created for CS 342 Spring 2016- Project 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;



public class MineSweeperBoard extends JPanel{
  
  private MineSweeperButton[][] boardSquares = new MineSweeperButton [10][10];
  private JPanel mineSweeperGrid;
  private MineSweeperMenu optionsMenu;
  private MinesweeperGame msg;
  //private JPanel mineSweeperGUI= new JPanel(new BorderLayout(3, 3));
  
  //Image array variables to hold the various images for the board 
  private ImageIcon[] gridNumberIcons = new ImageIcon[8]; //16x16 gifs
  private ImageIcon[] bombIcons= new ImageIcon[3]; //16x16 gifs
  private ImageIcon[] flagIcons = new ImageIcon[2]; //16x16 gifs
  private ImageIcon buttonIcons [] = new ImageIcon[2]; //16x16 gifs
  
  private ImageIcon[] countDownIcons = new ImageIcon[10]; //13x23 gifs
  
  private ImageIcon[] smileyIcons = new ImageIcon[5]; //26x26 gifs
  
  public enum buttonState {NORMAL, PRESSED, FLAGGED, Q_MARKED}
  
  
  public MineSweeperBoard(){
    super(new BorderLayout(3, 3));
    optionsMenu = new MineSweeperMenu();
    msg = new MinesweeperGame();
    initializeBoard();
    
  }
  
  public class ButtonListener implements ActionListener{  
   public void actionPerformed(ActionEvent event) {
   // Find out which button was clicked
    MineSweeperButton source = (MineSweeperButton)event.getSource();
    if(source.state == buttonState.NORMAL){
     source.setIcon(new ImageIcon("button_pressed.gif"));
     source.state = buttonState.PRESSED;          
    }
    if (source.hasBomb){
      msg.handleBomb(boardSquares); //set all button to pushed
      
    }
    
    //*******TEST CODE****Remove later
    //source.setText(""+source.hasBomb);
    
   }
  }
  
    private class RightClickListener extends MouseAdapter {
    public void mouseClicked (MouseEvent e){
      if (SwingUtilities.isRightMouseButton(e)){
        MineSweeperButton source = (MineSweeperButton)e.getSource();
        
        if(source.state == buttonState.NORMAL){//If right click on a normal tile:
         source.setIcon(new ImageIcon("button_flag.gif"));
         source.state = buttonState.FLAGGED;
        }
        else if(source.state==buttonState.FLAGGED){
          source.setIcon(new ImageIcon("button_question.gif"));
          source.state = buttonState.Q_MARKED;
        }
        else if(source.state==buttonState.Q_MARKED){
          source.setIcon(new ImageIcon("button_normal.gif"));
          source.state = buttonState.NORMAL;
        }
      }
    }
  }
  
  public class MineSweeperButton extends JButton{ //Nested class for button on the MineSweeperBoard.
    boolean hasBomb;
    buttonState state;
    
    int x, y;
    
    MineSweeperButton(ImageIcon icon, int i, int j){
      super(icon);
      x = i; y = j; //Index variables
      state = buttonState.NORMAL;
    }
  }
  
  public void initializeBoard(){
    
    this.setBorder(new EmptyBorder(5,5,5,5));
    
    
     
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
  
     mineSweeperGrid.setBorder(new EmptyBorder(10,10,10,10));
     
     this.add(mineSweeperGrid);     
     
     Insets buttonMargin = new Insets(0, 0, 0, 0); //Create buttons for the board.
     ImageIcon icon = new ImageIcon("button_normal.gif");
     
     for (int i = 0; i < boardSquares.length; i++) {
       for (int j = 0; j < boardSquares[i].length; j++) {
       //Create 16x16 button with an image.
         MineSweeperButton b = new MineSweeperButton(icon, i, j);
         b.setMargin(buttonMargin);
         b.setIcon(icon);
         b.addMouseListener(new RightClickListener());
         b.addActionListener(new ButtonListener());
         boardSquares[j][i] = b;
       }
     }
     
     //randomly place ten bombs on board
     int i=0;
     while (i < 10){
       int iRand = (int)(Math.random() * (boardSquares.length));
       int jRand = (int)(Math.random() * (boardSquares[iRand].length));
       
         boardSquares[iRand][jRand].hasBomb = true;
         i++;
     }
     
     //Fill in the board with the newly created gray squares.   
     for (int ii = 0; ii < 10; ii++) {
       for (int jj = 0; jj < 10; jj++) {
         mineSweeperGrid.add(boardSquares[jj][ii]);
       }
     }
     
  }//End of initializeBoard()
  
  /******Class for the drop down menu for a minesweeper board.
  *******/
  private class MineSweeperMenu extends JToolBar{
    
  private MineSweeperMenu()
  {
   
    super();
    this.setFloatable(false);
    
    this.add(new JButton("Game")); 
    this.add(new JButton("Help"));
  }
  
}
 /****** End of MineSweeperMenu class.
 *******/
  
  public MineSweeperMenu getOptionsMenu(){
    return optionsMenu;
  }
  
  public MineSweeperButton[][] getGrid(){
    return boardSquares;
  }
  
  public void startNewGame(){ //TODO start a new game
     
  }
}

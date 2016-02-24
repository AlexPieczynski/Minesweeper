//A class to implement Minesweeper board and GUI. 
//MineSweeper Images were spliced by Daniel Hajnos and originally ripped by user Black Squirrel.
//Created for CS 342 Spring 2016- Project 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class MineSweeperBoard extends JPanel{
  
  private MineSweeperButton[][] boardSquares = new MineSweeperButton [10][10];
  private JPanel mineSweeperGrid;
  private MineSweeperMenu optionsMenu;
  private MinesweeperGame msg;
  private MineSweeperDisplay msDisplay;
  //private DisabledGlassPane glass = new DisabledGlassPane();
  
  public int buttonsCleared = 0; 
  public boolean gameOver = false; //Variable to tell the program when the game has ended.

//Image array variables to hold the various images for the board 
  private ImageIcon[] gridNumberIcons = new ImageIcon[8]; //16x16 gifs
  private ImageIcon[] bombIcons= new ImageIcon[3]; //16x16 gifs
  private ImageIcon[] flagIcons = new ImageIcon[2]; //16x16 gifs
  private ImageIcon buttonIcons [] = new ImageIcon[2]; //16x16 gifs
  
  public enum buttonState {NORMAL, PRESSED, FLAGGED, Q_MARKED}
  
  public MineSweeperBoard(){  //Constructo for MineSweeperBoard
    super(new BorderLayout(3, 3));
    msg = new MinesweeperGame();
    initializeBoard();
    msDisplay = new MineSweeperDisplay();
    optionsMenu = new MineSweeperMenu(msg.getHighScores(), this);
    
    String string = new String("button_");
    String number;
    int temp;
    
    for(int i = 0; i < 8; i++){ //Create an array of number gifs.
      temp = i + 1;
      number = new String(string +temp);
      number = new String(number+ ".gif");
      
      gridNumberIcons[i]=new ImageIcon(number);
    }
  }
  
  public class ButtonListener implements ActionListener{ //Listener for each button on the grid.  
   public void actionPerformed(ActionEvent event) {
   // Find out which button was clicked
    MineSweeperButton source = (MineSweeperButton)event.getSource();
    if(source.state == buttonState.NORMAL){
      if(source.hasBomb){            //If mine was clicked AND has bomb, the game is over.
        source.setIcon(new ImageIcon("button_bomb_x.gif"));
        gameLost(source.x, source.y);//Initiate the sequence for losing a game.
      }
      else
      {//If mine was clicked and DOES NOT have a bomb.
        clearSurrounding(source); //Inspect and clear any surroundiing buttons
      }
    }
   }
  }
  
  private class RightClickListener extends MouseAdapter {
    public void mouseClicked (MouseEvent e){
      if (SwingUtilities.isRightMouseButton(e)){
        MineSweeperButton source = (MineSweeperButton)e.getSource();
        
        if(source.state == buttonState.NORMAL){//If right click on a normal tile:
          if(msg.getnMines() > 0){//To avoid getting negative values in the counter.
            source.setIcon(new ImageIcon("button_flag.gif"));
            source.state = buttonState.FLAGGED;
            msg.decrementMines(); //decrease mine counter
            msDisplay.mineCounter.setText("Mines: "+ msg.getnMines());
          }
        }
        else if(source.state==buttonState.FLAGGED){ //If right click on a button that already has a flag.
          source.setIcon(new ImageIcon("button_question.gif"));
          source.state = buttonState.Q_MARKED;
          msg.incrementMines(); //increase mine counter
          msDisplay.mineCounter.setText("Mines: "+ msg.getnMines());
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
        b.hasBomb = false;
        b.state = buttonState.NORMAL;
        b.addMouseListener(new RightClickListener());
        b.addActionListener(new ButtonListener());
        boardSquares[i][j] = b;
      }
    }
//randomly place ten bombs on board
    int i=0;
    while (i < 10){
      int iRand = (int)(Math.random() * (boardSquares.length));
      int jRand = (int)(Math.random() * (boardSquares[iRand].length));
      if (boardSquares[iRand][jRand].hasBomb == false){   //ensure no duplicates
        boardSquares[iRand][jRand].hasBomb = true;
        i++;
       }
    }

//Fill in the board with the newly created gray squares.
    for (int ii = 0; ii < 10; ii++) {
      for (int jj = 0; jj < 10; jj++) {
        mineSweeperGrid.add(boardSquares[jj][ii]);
      }
    }
  }//End of initializeBoard()
  
  public MineSweeperMenu getOptionsMenu(){
    return optionsMenu;
  }
  
  public MineSweeperButton[][] getGrid(){
    return boardSquares;
  }
  
  public void startNewGame(){//Code to reset initial conditions
    msDisplay.secs = 0;
    msDisplay.timeLabel.setText("\tTime: " + msDisplay.secs);
    msg.resetGame();
    resetBoard();
    msDisplay.mineCounter.setText("Mines: "+ msg.getnMines());
    gameOver = false;
    buttonsCleared = 0;
    msDisplay.smileyButton.setIcon(new ImageIcon("smile_button.gif"));
  }
  
  public void resetBoard(){ //A helper method to reset the entire game board.
    MineSweeperButton[][] tempButtons = getGrid();
    //Reset every button to normal.
    for(int i = 0; i < 10; i++){
      for(int j = 0; j < 10; j++){
        tempButtons[i][j].state = buttonState.NORMAL;
        tempButtons[i][j].hasBomb = false;
        tempButtons[i][j].setIcon(new ImageIcon("button_normal.gif")); 
      }
    }//randomly place ten bombs on board
    int i=0;
    while (i < 10){
      int iRand = (int)(Math.random() * (boardSquares.length));
      int jRand = (int)(Math.random() * (boardSquares[iRand].length));
      if (boardSquares[iRand][jRand].hasBomb == false){   //ensure no duplicates
        boardSquares[iRand][jRand].hasBomb = true;
        i++;
      }
    } 
  }
  
  public void gameLost(int x, int y){ //A method to perform the steps if a mine is clicked. 
    gameOver = true; //show every bomb and stop the clock
    MineSweeperButton[][] tempButtons = getGrid();
    for(int i = 0; i < 10; i++){
      for(int j = 0; j < 10; j++){
        if(!(i == x && j == y) && tempButtons[i][j].hasBomb)
          tempButtons[i][j].setIcon(new ImageIcon("button_bomb_pressed.gif")); 
      }
    }
     msDisplay.smileyButton.setIcon(new ImageIcon("head_dead.gif"));                                      
  }
  
   public void gameWon(int x, int y){
    gameOver = true; //show every bomb and stop the clock
    MineSweeperButton[][] tempButtons = getGrid();
    for(int i = 0; i < 10; i++){
      for(int j = 0; j < 10; j++){
        if(i != x & j != y && tempButtons[i][j].hasBomb)
          tempButtons[i][j].setIcon(new ImageIcon("button_bomb_pressed.gif"));
      }
    }
    msDisplay.smileyButton.setIcon(new ImageIcon("head_glasses.gif"));
    if (msg.getHighScores().isHighScore(msDisplay.secs)){
       msg.getHighScores().addHighScore(msDisplay.secs);
       msg.getHighScores().saveToFile();
     }
  }
  
  //A function to clear a normal button without a bomb. 
  public void clearButton(MineSweeperButton b){ 
    b.state = buttonState.PRESSED; //Marked button as pressed.
    buttonsCleared++;
    
    if(buttonsCleared == 90){
      if (!gameOver){
        gameWon(b.x, b.y);
        return;
      }
    }
    
    int row = b.x;
    int col = b.y;
    
    int numMines = getMineCount(b); //Gett the number of mines in adjacent squares. 
    if(numMines > 0)
      b.setIcon(gridNumberIcons[numMines-1]);  //Display number of surrounding mines as gif image.
    else
      b.setIcon(new ImageIcon("button_pressed.gif"));
  }
  
  private int getMineCount(MineSweeperButton b){ //Helper function to check neighbors for mines
    int row = b.x;
    int col = b.y;
    int numMines = 0;
    MineSweeperButton[][] neighbors = getGrid();
    
    
     //Check the same row as the button.
      if(col > 0)
        if(neighbors[row][col-1].hasBomb)
          numMines++;
      if(col < 9)
        if(neighbors[row][col+1].hasBomb)
          numMines++;
    
    if(row > 0){ //Check 1 row below the button
      if(neighbors[row-1][col].hasBomb)
        numMines++;
      if(col > 0)
        if(neighbors[row-1][col-1].hasBomb)
          numMines++;
      if(col < 9) 
        if(neighbors[row-1][col+1].hasBomb)
        numMines++;
    }
    
    if(row < 9){ //Check 1 row above the button
      if(neighbors[row+1][col].hasBomb)
        numMines++;
      if(col > 0)
        if(neighbors[row+1][col-1].hasBomb)
          numMines++;
      if(col < 9) 
        if(neighbors[row+1][col+1].hasBomb)
        numMines++;
    }
  
    return numMines;
  }

  public void clearSurrounding(MineSweeperButton b){ //Helper function to clear the empty surrounding buttons.
     
    MineSweeperButton[][] neighbors = getGrid();
    int row = b.x;
    int col = b.y;
    
    if(b.state == buttonState.NORMAL && !(b.hasBomb)){
      if(getMineCount(b) > 0){
        clearButton(b); //Marked button as pressed.
        return;
      }
      clearButton(b);
    }
    else
      return;
    
    if(row < 9) //Check 1 row above the button
      clearSurrounding(neighbors[row+1][col]);
      
    if(row > 0) //Check 1 row below the button
      clearSurrounding(neighbors[row-1][col]);
    
    if(col < 9) //Check 1 col above the button
      clearSurrounding(neighbors[row][col+1]);
      
    if(col > 0) //Check 1 col below the button
      clearSurrounding(neighbors[row][col-1]);
  }
  
 
      
  
  public class MineSweeperDisplay extends JPanel{
  
  JLabel mineCounter = new JLabel("Mines: 10"); //Dummy labels to hold later functionality. 
  JLabel timeLabel = new JLabel("\tTime: 0");
  JButton smileyButton = new JButton(new ImageIcon("smile_button.gif"));
  
  GridBagLayout gBag = new GridBagLayout();
  GridBagConstraints c = new GridBagConstraints();
  
  //---------Timer and Counter Variables-----------------
  private int nMines; //used to display num of mines left on the board. decrements when a mine is marked.
  private Timer clock; //counts time elapsed since first left-click on Grid
  private int secs; //same as above
  private HighScores highScores;
   
  public MineSweeperDisplay(){
   super();
   this.setLayout(gBag);
   c.insets = new Insets(0,10,10,0);
   
   nMines = 10;
   secs = 0;
   highScores = new HighScores();
   TimeHandler t = new TimeHandler(); 
   clock = new Timer(1000, t);
   clock.start();
   
   c.anchor = GridBagConstraints.LAST_LINE_START;
   this.add(mineCounter,c);
   
   c.anchor = GridBagConstraints.PAGE_END;
   smileyButton.addActionListener(new SmileyListener());
   this.add(smileyButton,c);
   
   c.anchor = GridBagConstraints.LAST_LINE_END;
   this.add(timeLabel,c);
  }
  
  public class SmileyListener implements ActionListener{ //Reset the game when smiley button is pressed.
  
    public void actionPerformed(ActionEvent event){
      gameOver = true;
      startNewGame();
    }
  }
  
   private class TimeHandler implements ActionListener{ //Timehandler event listener
    public void actionPerformed( ActionEvent event ){
      if(!gameOver){//Stop updating the counter once the game is over.
        secs++;}
        timeLabel.setText("\tTime: " + secs);
    }
   }
  
}
  
  public static void main(String[] args){
     Runnable r = new Runnable(){
     @Override
     public void run() { 
      MineSweeperBoard msBoard = new MineSweeperBoard();
      
      JFrame f = new JFrame("Minesweeper");
      
      f.add(msBoard.getOptionsMenu(), BorderLayout.PAGE_START); //Add the dropdown menu.
      f.add(msBoard.msDisplay,BorderLayout.PAGE_END); //Add the display panel;
      f.add(msBoard, BorderLayout.CENTER); //Add the game board.
      
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      f.setLocationByPlatform(true);
      f.pack();
      f.setMinimumSize(f.getSize());
      f.setVisible(true);
     }
    };
    SwingUtilities.invokeLater(r); 
  }//End of main method.
  
  
}

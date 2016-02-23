//This class holds information about the current minesweeper game\
//also hold the game logic

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MinesweeperGame
{
  private int nMines; //used to display num of mines left on the board. decrements when a mine is marked.
  private HighScores highScores;
  
  public MinesweeperGame()
  {
    nMines = 10;
    highScores = new HighScores();
  }
  
  public void handleBomb(MineSweeperBoard.MineSweeperButton[][] grid){
    
    //set all buttons to pressed
    for (int i=0; i < grid.length; i++){
      for (int j=0; j < grid[j].length; j++){
        grid[i][j].state = MineSweeperBoard.buttonState.PRESSED;
      }
    }
  }
  
    public static void main (String[] args)
  {
     Runnable r = new Runnable(){
     @Override
     public void run() { 
      MinesweeperGame game = new MinesweeperGame(); 
      MineSweeperBoard msBoard = new MineSweeperBoard();
      MineSweeperDisplay msDisplay = new MineSweeperDisplay();
      
      JFrame f = new JFrame("Minesweeper");
      
      f.add(msBoard.getOptionsMenu(), BorderLayout.PAGE_START); //Add the dropdown menu.
      f.add(msDisplay,BorderLayout.PAGE_END); //Add the display panel;
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
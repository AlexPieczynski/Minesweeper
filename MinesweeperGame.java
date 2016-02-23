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
    MinesweeperGame game = new MinesweeperGame();
  }
}
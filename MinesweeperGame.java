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
  
  
  public void incrementMines(){
    nMines++;
  }
  public void decrementMines(){
    nMines--;
  }
  public int getnMines(){
    return nMines;
  }
  
  public void resetGame(){
    nMines = 10;
  }
  
  public void handleBomb(MineSweeperBoard.MineSweeperButton[][] grid){
    
    //set all buttons to pressed
    for (int i=0; i < 10; i++){
      for (int j=0; j < 10; j++){
        grid[i][j].state = MineSweeperBoard.buttonState.PRESSED;
      }
    }
  }  
  
}
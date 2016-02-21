//This class is used to instantiate a game of Minesweeper
//Run this files main() begin playing

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MinesweeperGame
{
  private int nMines; //used to display num of mines left on the board. decrements when a mine is marked.
  private Timer clock; //counts time elapsed since first left-click on Grid
  private int secs; //same as above
  //private Grid board;
  private HighScores highScores;
  
  //updates secs value every time clock fires. also prints out each second. STOPS AT 15 SECONDS
  private class TimeHandler implements ActionListener{
    public void actionPerformed( ActionEvent event ){
      secs++;
      if (secs < 15)
        System.out.println(secs);
      else
        clock.stop();
    }
  }
  
  public MinesweeperGame()
  {
    nMines = 10;
    secs = 0;
    highScores = new HighScores();
    TimeHandler t = new TimeHandler(); 
    clock = new Timer(1000, t);
    clock.start();
  }
  
  public static void main (String[] args)
  {
    MinesweeperGame game = new MinesweeperGame();
  }
}
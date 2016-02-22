//Class for showing the top of the display board for minesweeper.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MineSweeperDisplay extends JPanel{
  
  JLabel mineCounter = new JLabel("##"); //Dummy labels to hold later functionality. 
  JLabel timeLabel = new JLabel("xx:xx");
  JLabel smiley = new JLabel("img");
  GridBagLayout gBag = new GridBagLayout();
  GridBagConstraints c = new GridBagConstraints();
<<<<<<< HEAD
  
  //---------Timer and Counter Variables-----------------
  private int nMines; //used to display num of mines left on the board. decrements when a mine is marked.
  private Timer clock; //counts time elapsed since first left-click on Grid
  private int secs; //same as above
  private HighScores highScores;
  
=======
  MinesweeperGame gameInfo = new MinesweeperGame();
>>>>>>> 0a298acb4bcf4f59b3cf401a4ddb943d28ea9d6f
 
  public MineSweeperDisplay(){
   super();
   this.setLayout(gBag);
   c.insets = new Insets(0,20,20,0);
   
   nMines = 10;
   secs = 0;
   highScores = new HighScores();
   TimeHandler t = new TimeHandler(); 
   clock = new Timer(1000, t);
   clock.start();
   
   c.anchor = GridBagConstraints.LAST_LINE_START;
   this.add(mineCounter,c);
   
   c.anchor = GridBagConstraints.PAGE_END;
   this.add(smiley,c);
   
   c.anchor = GridBagConstraints.LAST_LINE_END;
   this.add(timeLabel,c);
   
  }
  
  
  private void updateMineCount(){
    
  }
  
  
  private void updateSmiley(){
  }
  
   private class TimeHandler implements ActionListener{ //Timehandler event listener
    public void actionPerformed( ActionEvent event ){
     secs++;
     timeLabel.setText("" + secs);
     
    }
  }
  
}
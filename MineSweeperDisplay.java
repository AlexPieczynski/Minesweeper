//Class for showing the top of the display board for minesweeper.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MineSweeperDisplay extends JPanel{
  
  JLabel mineCounter = new JLabel(" ## "); //Dummy labels to hold later functionality. 
  JLabel timeLabel = new JLabel("0");
  
  JButton smileyButton = new JButton(new ImageIcon("smile_button.gif"));
  
  GridBagLayout gBag = new GridBagLayout();
  GridBagConstraints c = new GridBagConstraints();
  
  //---------Timer and Counter Variables-----------------
  private int nMines; //used to display num of mines left on the board. decrements when a mine is marked.
  private Timer clock; //counts time elapsed since first left-click on Grid
  private int secs; //same as above
  private HighScores highScores;
  

  MinesweeperGame gameInfo = new MinesweeperGame();

 
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
   smileyButton.setSize(16,16);
   smileyButton.addActionListener(new ButtonListener());
   this.add(smileyButton,c);
   
   c.anchor = GridBagConstraints.LAST_LINE_END;
   this.add(timeLabel,c);
  }
  
  
  private void updateMineCount(){
    
  }
  
  
  private void updateSmiley(){
  }
  
  public class ButtonListener implements ActionListener{
  
    public void actionPerformed(ActionEvent event){
      smileyButton.setIcon(new ImageIcon("smile_button_pressed.gif"));
    }
  }
  
   private class TimeHandler implements ActionListener{ //Timehandler event listener
    public void actionPerformed( ActionEvent event ){
     secs++;
     timeLabel.setText(" " + secs);     
    }
  }
  
}
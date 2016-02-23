//Class for showing the top of the display board for minesweeper.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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
  public boolean gameOverD = false; //Variable to tell an MSD object when the game has ended.
  
 
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
   smileyButton.addActionListener(new ButtonListener());
   this.add(smileyButton,c);
   
   c.anchor = GridBagConstraints.LAST_LINE_END;
   this.add(timeLabel,c);
  }
  
  public class ButtonListener implements ActionListener{
  
    public void actionPerformed(ActionEvent event){
      smileyButton.setIcon(new ImageIcon("smile_button_pressed.gif"));
    }
  }
  
   private class TimeHandler implements ActionListener{ //Timehandler event listener
    public void actionPerformed( ActionEvent event ){
      if(!gameOverD){//Stop updating the counter once the game is over.
        secs++;}
        timeLabel.setText("\tTime: " + secs);
    }
   }
  
}
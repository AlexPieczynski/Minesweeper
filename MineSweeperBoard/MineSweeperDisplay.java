//Class for showing the top of the display board for minesweeper.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MineSweeperDisplay extends JPanel{
  
  JLabel mineCounter = new JLabel("##");
  JLabel timer = new JLabel("xx:xx");
  JLabel smiley = new JLabel("img");
  GridBagLayout gBag = new GridBagLayout();
  GridBagConstraints c = new GridBagConstraints();
 
  public MineSweeperDisplay(){
   super();
   this.setLayout(gBag);
   c.insets = new Insets(0,20,20,0);
   
   c.anchor = GridBagConstraints.LAST_LINE_START;
   this.add(mineCounter,c);
   
   c.anchor = GridBagConstraints.PAGE_END;
   this.add(smiley,c);
   
   c.anchor = GridBagConstraints.LAST_LINE_END;
   this.add(timer,c);
   
  }
  
  
  private void updateMineCount(){
    
  }
  
  private void updateTimer(){
  }
  
  private void updateSmiley(){
  }
  
}
import javax.swing.*;
import java.awt.*;

public class TestBoard{
  
  public static void main(String[] args){
    Runnable r = new Runnable(){
     @Override
     public void run() { 
      MineSweeperBoard msb = new MineSweeperBoard();
      JFrame f = new JFrame("Minesweeper");
      f.add(msb.getBoardGUI());
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      f.setLocationByPlatform(true);
      f.pack();
      f.setMinimumSize(f.getSize());
      f.setVisible(true);
     }
    };
    SwingUtilities.invokeLater(r); 
  }
   //End of main method
  
}

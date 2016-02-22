import javax.swing.*;
import java.awt.*;

public class TestBoard{
  
  public static void main(String[] args){
    Runnable r = new Runnable(){
     @Override
     public void run() { 
       
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
  }
   //End of main method
  
}

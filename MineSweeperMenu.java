  /****** Class for the drop down menus for a minesweeper board. ********/

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
  
public class MineSweeperMenu extends JMenuBar
{
  private JMenu game;
  private JMenu help;
  private HighScores highScores;   //reference to highScores to call showScores function
  private MineSweeperBoard board;  //reference to board to call reset function
  
  //Constructs the toolbar with dropdown menus
  public MineSweeperMenu(HighScores highScores, MineSweeperBoard board){
    super();
    this.highScores = highScores;
    this.board = board;
    
    //build Game menu
    game = new JMenu("Game");
    game.setMnemonic(KeyEvent.VK_G);
    
    JMenuItem reset = new JMenuItem("Reset");
    reset.addActionListener(new resetListener());
    reset.setMnemonic(KeyEvent.VK_R);
    game.add(reset);
    JMenuItem topTen = new JMenuItem("Top Ten");
    topTen.addActionListener(new topTenMenuHandler());
    topTen.setMnemonic(KeyEvent.VK_T);
    game.add(topTen);
    JMenuItem exit = new JMenuItem("Exit");
    exit.addActionListener(new exitListener());
    exit.setMnemonic(KeyEvent.VK_X);
    game.add(exit);
    
    this.add(game);
    
    //build Help menu
    help = new JMenu("Help");
    help.setMnemonic(KeyEvent.VK_H);
    
    JMenuItem howToPlay = new JMenuItem("How To Play");
    howToPlay.addActionListener(new howToPlayListener());
    howToPlay.setMnemonic(KeyEvent.VK_H);
    help.add(howToPlay);
    JMenuItem about = new JMenuItem("About");
    about.addActionListener(new aboutListener());
    about.setMnemonic(KeyEvent.VK_A);
    help.add(about);
    
    this.add(help);
  }
  
  
  /************ACTION LISTENERS FOR SIMPLE MENU ITEMS*************************/
  //Exit Item, exist the game
  private class exitListener implements ActionListener, ItemListener{
    public void actionPerformed( ActionEvent event ){
      System.exit(0);
    }
    public void itemStateChanged(ItemEvent e) {}
  }
  
  private class topTenMenuHandler implements ActionListener {
    public void actionPerformed( ActionEvent event ){
      highScores.showScoreWindow();
    }
  }
  
  private class resetListener implements ActionListener {
    public void actionPerformed( ActionEvent event ){
      board.startNewGame();
    }
  }
  
  //About item, displays an about message
  private class aboutListener implements ActionListener{
    public void actionPerformed( ActionEvent event ){
      String[] aboutStrings = {"                  A Minesweeper clone created by",
        "Alex Pieczynski (apiecz2) and Alberto Hernandez (aherna71)",
        "                 For CS 342 at U. of Illinois at Chicago"};
      JOptionPane.showMessageDialog(new JFrame("About"), aboutStrings);
    }
  }
  
  private class howToPlayListener implements ActionListener{
    public void actionPerformed( ActionEvent event ){
      String[] instructions = {"How To Play:",
      "The objective is to find all 10 mines!",
      "Right-click to mark a mine, or Right-click twice to mark with a '?'",
      "Left-click a button to clear it, but don't click a mine or it's GAME OVER!"};
      JOptionPane.showMessageDialog(new JFrame("How To Play"), instructions);
    }
  }
  
}
 /****** End of MineSweeperMenu class.
 *******/
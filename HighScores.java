import java.io.*;
import java.util.Scanner;

public class HighScores
{
  private String names[];
  private int times[];
  
  public void HighScores()
  {
    try{
    names = new String[10];
    times = new int[10];
    
    //read in high scores from file (if it exists)
    File file = new File("top-ten-scores.txt");
    Scanner sc;
    if (file.exists())
    {
      sc = new Scanner(file);
      
      for (int i=0; sc.hasNext() && i < 10; i++)
      {
        names[i] = sc.next();
        times[i] = sc.nextInt();
      }
    }
    else{
      file.createNewFile();
    }
    }catch(FileNotFoundException fnfe){
      System.out.println("file not found");
      return;
    }
    catch(IOException e){
      System.out.println("IOException");
      return;
    }
  }
  
  //pass in a player's score at the end of the game
  //returns true if the player's score should be added to the high score list
  public boolean isHighScore(int newTime)
  {
    //automatically high score if empty spot on scores
    if (times.length < 10)
      return true;
    
    //check if the passed in score is better than current high scores
    for (int t : times)
    {
      if (newTime > t)
        return true;
    }
    return false;
  }
  
  //add a player's name and score to the high score list
  //isHighScore() MUST evaluate to true before this function is called
  public void addHighScore(String playerName, int playerTime)
  {
    int i;
    for (i=0; i < times.length; i++){
      if (playerTime > times[i]){
        times[i] = playerTime;
        break;
      }
    }
    names[i] = playerName;    
  }
  
  
  private void saveToFile()
  {
    try{
      File file = new File("top-ten-scores.txt");
      
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      
      
      bw.close();      
    } catch(IOException e){
      e.printStackTrace();
    }   
  }
}
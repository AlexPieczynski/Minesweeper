import java.io.*;
import java.util.Scanner;


public class HighScores
{
  private String names[]; //holds names of high scorers
  private int times[];    //holds each player's time
  private int nScores;    //number of high scores recorded
  
  
  //reads in HighScores data from a file
  //if file does not exist, it is created
  public HighScores()
  {
    try{
      names = new String[10];
      times = new int[10];
      nScores = 0;
      
      File file = new File("top-ten-scorers.txt");
      Scanner sc = new Scanner(file);
      if (file.exists()){             //read scores from file
        int i;
        for (i=0; sc.hasNext() && i < 10; i++){
          names[i] = sc.next();
          times[i] = sc.nextInt();
        }
        nScores = i;
      }
      else{       //create new high score file
        System.out.println("high score file did not exist, creating the file...");
        file.createNewFile();
      }
    }
    catch (FileNotFoundException fnfe){
      System.out.println("FILE NOT FOUND");
    }
    catch (IOException e){
      System.out.println("IO exception");
    }
  }
  
  public String[] getNames(){
    return names;
  }
  
  public int[] getTimes(){
    return times;
  }
  
  public int getNumScores(){
    return nScores;
  }
  
  public void printScores()
  {
    System.out.println("NAME : TIME");
    for (int i=0; i < nScores; i++)
    {
      System.out.println( names[i] + " : " + times[i] );
    }
  }
  
  //pass in a player's score at the end of the game
  //returns true if the player's score should be added to the high score list
  public boolean isHighScore(int newTime)
  {
    //automatically high score if empty spot on scores
    if (nScores < 10)
      return true;
    
    //check if the passed in score is better than current high scores
    for (int t : times)
    {
      if (newTime < t)
        return true;
    }
    return false;
  }
  
  //add a player's name and score to the high score list
  //isHighScore() MUST evaluate to true before this function is called
  public void addHighScore(String playerName, int playerTime)
  {
    int newTimes[] = new int[10]; //will store updated scores array
    String newNames[] = new String[10]; //updated names array
                                 
    int i;
    for (i=0; i < nScores; i++){
      if (playerTime < times[i]){
        System.arraycopy(times, 0, newTimes, 0, i);
        newTimes[i] = playerTime;
        System.arraycopy(times, i+1, newTimes, i+1, nScores-i-1);
        if (nScores < 10)
          nScores++;
        return;
      }
    }
    names[i] = playerName;
    if (nScores < 10)
      nScores++;
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
  
  
  public static void main(String[] args)
  {
    HighScores highScores = new HighScores();
    
    String[] _names = highScores.getNames();
    int[] _times = highScores.getTimes();
    int nScores = highScores.getNumScores();
    
    highScores.printScores();
  }
}
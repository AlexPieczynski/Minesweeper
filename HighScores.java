import javax.swing.*;

import java.io.*;
import java.util.Scanner;

//Class to hold information regarding High Scores for Minesweeper
//Read and Writes scores to a file named "top-ten-scores.txt"

public class HighScores
{
  private String names[]; //holds names of high scorers
  private int times[];    //holds each player's time
  private int nScores;    //number of high scores recorded
  
  
  //constructor reads in HighScores data from a file
  //if file does not exist, it is created
  public HighScores()
  {
    try{
      names = new String[10];
      times = new int[10];
      nScores = 0;
      
      File file = new File("top-ten-scores.txt");
      if (file.exists()){             //read scores from file
        Scanner sc = new Scanner(file);
        int i;
        for (i=0; sc.hasNext() && i < 10; i++){
          names[i] = sc.next();
          times[i] = sc.nextInt();
        }
        nScores = i;
      }
      else{       //create new high score file
        System.out.println("high score file not found, creating the file...");
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
  
  
  //prints out the current high scores
  public void printScores()
  {
    System.out.println("NAME : TIME");
    for (int i=0; i < nScores; i++)
      System.out.println( names[i] + " : " + times[i] );
  }
  
  
  //pass in a player's score at the end of the game
  //returns true if the player's score should be added to the high score list
  public boolean isHighScore(int newTime)
  {
    //automatically high score if empty spot on list
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
  
  //prompts for a player's name and adds their score to the high score list
  //isHighScore() MUST evaluate to true before this function is called
  public void addHighScore(int playerTime)
  {
    //prompt for user to input their name
    String playerName = JOptionPane.showInputDialog("Please type in your name");
    
    int newTimes[] = new int[10]; //will store updated scores array
    String newNames[] = new String[10]; //updated names array
                                 
    int i;
    for (i=0; i < nScores; i++){
      if (playerTime < times[i]){      //insert score, shift the other scores by one
        System.arraycopy(times, 0, newTimes, 0, i);
        System.arraycopy(names, 0, newNames, 0, i);
        newTimes[i] = playerTime;
        newNames[i] = playerName;
        System.arraycopy(times, i+1, newTimes, i+1, nScores-i-1);
        System.arraycopy(names, i+1, newNames, i+1, nScores-i-1);
        if (nScores < 10)
          nScores++;
        times = newTimes;
        names = newNames;
        return;
      }
    }
    
    //else tack score onto the end
    names[i] = playerName;
    times[i] = playerTime;
    nScores++;
  }
  
  
  //saves the contents of HighScores object to a file
  //must be called before the program is exited to save high scores
  private void saveToFile()
  {
    try{
      File file = new File("top-ten-scores.txt");
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      
      //write names and scores to file
      for (int i=0; i < nScores; i++){
        bw.write(names[i]);
        bw.write(" ");
        bw.write(Integer.toString(times[i]));
        bw.newLine();
      }            
      bw.close();
      
    } catch(IOException e){
      e.printStackTrace();
    }   
  }
  
  
  //deletes any high scores currently in the object
  //makes the high scores file empty
  public void resetHighScores()
  {
    names = null;
    times = null;
    nScores = 0;
    saveToFile();
  }
  
  
  //simple main to test functionality
  public static void main(String[] args)
  {
    HighScores highScores = new HighScores(); //initialize object from file
    
    //attempt to add some high scores to the board
    for (int i=0; i < 15; i++)
    {
      if (highScores.isHighScore(i)){
        //System.out.println("new high score: "+i);
        highScores.addHighScore(i);
      }
    }

    highScores.printScores();
    highScores.saveToFile();
    highScores.resetHighScores();
  }
}
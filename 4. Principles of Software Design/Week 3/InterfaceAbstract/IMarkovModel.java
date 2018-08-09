
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author Daniel J Hardej 
 * @version 1.0
 */

public interface IMarkovModel {
    public void setTraining(String text);
    
    public void setRandom(int seed);
    
    public String getRandomText(int numChars);
}

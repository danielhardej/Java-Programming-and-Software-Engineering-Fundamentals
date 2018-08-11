
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author Daniel J Hardej
 * @version 1.0
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    
    protected String myText;
    protected Random myRandom;
    protected static int order;
    
    public AbstractMarkovModel(int order) {
        myRandom = new Random();
        this.order = order;
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);
    
    private String getFollowingLetter(int pos) {
		// index of letter following key
		int index = pos+order;
		return myText.substring(index, index+1);
    }
    
    protected ArrayList<String> getFollows (String key) {
        ArrayList<String> follows = new ArrayList<String>();
        
        for (int i = 0; i < myText.length() - order; i++) {
            if (key.equals(myText.substring(i, i + order))) {
                follows.add(getFollowingLetter(i));
            }
        }
        
        return follows;
    }
    
    public String toString() {
        return "Markov Model of order " + order;
    }
}

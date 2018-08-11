
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
    
    protected ArrayList<String> getFollows (String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        
        while (pos < myText.length()) {
            int start = myText.indexOf(key, pos);
            if (start == -1) {
                break;
            }
            if (start + key.length() > myText.length()-1) {
                break;
            }
            String next = myText.substring(start+key.length(), start+key.length()+1);
            follows.add(next);
            pos = start + key.length();
        }
        
        return follows;
    }
    
    public String toString() {
        return "Markov Model of order " + order;
    }
}

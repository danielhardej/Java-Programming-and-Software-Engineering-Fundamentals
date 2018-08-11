
/**
 * Write a description of MarkovModel here.
 * 
 * @author Daniel J Hardej
 * @version 1.1
 */

import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
    
    
    
    public MarkovModel(int order) {
        super(order);
        myRandom = new Random();
    }
    
    
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-order);
        String key = myText.substring(index, index+order);
        sb.append(key);
        
        for(int k=0; k < numChars-order; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    
}

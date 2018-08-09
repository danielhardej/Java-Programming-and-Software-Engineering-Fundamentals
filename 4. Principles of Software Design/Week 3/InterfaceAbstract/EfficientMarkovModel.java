
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author Daniel J Hardej
 * @version 1.0
 */

import java.util.*;

public abstract class EfficientMarkovModel extends AbstractMarkovModel {
    
    private HashMap<String, ArrayList<String>> map;
    
    
    public EfficientMarkovModel(int order) {
        super(order);
        map = new HashMap<String, ArrayList<String>>();
    }
    
    
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
        
        for(int k=0; k < numChars-N; k++){
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
    
    public void buildMap () {
        
    }
    
    public ArrayList<String> getFollows (String key) {
        return map.get(key);
    }
    
    public String toString() {
        return "Markov Model of order " + N;
    }
}

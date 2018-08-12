
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author Daniel J Hardej
 * @version 1.2
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    
    private HashMap<String, ArrayList<String>> map;
    
    
    public EfficientMarkovModel(int order) {
        super(order);
        map = new HashMap<String, ArrayList<String>>();
    }
    
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
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
            
            if (follows == null) {
                break;
            }
            
            String next = follows.get(myRandom.nextInt(follows.size()));
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
     private void buildMap() {
        
        for (int i = 0; i <= myText.length() - order; i++) {
            
            if (i == myText.length() - order) {
                String key = myText.substring(i, i + order);
                map.put(key, null);
                break;
            }

            String key = myText.substring(i, i + order);
            String follow = myText.substring(i+order, i+order+1);
            
            if (map.containsKey(key)) {
                map.get(key).add(follow);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                map.put(key, list);
            }
            
        }

    }
    
    public ArrayList<String> getFollows (String key) {
        return map.get(key);
    }
    
    public String toString() {
        return "Markov Model of order " + order;
    }
    
    
    public void printHashMapInfo() {
        System.out.println("HashMap info: ");
        
        int maxFollowsSize = findMaxFollowsSize();
       
        System.out.printf("Map size:\t%d\nMax array size:\t%d\n", map.size(), maxFollowsSize);
        /*
	for (String key : map.keySet()) {
	    System.out.printf("Key:\t[%s]\tvalues: ", key);
	    System.out.println(map.get(key));
	}
	*/

    }
    
    public int findMaxFollowsSize() {
        int maxFollowsSize = 0;
        for (String key : map.keySet()) {
            
            if (!(map.get(key) == null)) {
                maxFollowsSize = Math.max(maxFollowsSize, map.get(key).size());
            }
            else {
                
            }
            
        }
        return maxFollowsSize;
    }
}

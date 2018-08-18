
/**
 * Write a description of class MarkovWordTwo here.
 * 
 * @author Daniel J Hardej
 * @version 1.0
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;        // This is an array now!
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target1, String target2, int start) {
        // find the index of any occurence of the word assigned to variable
        // target in the string array words
        
        // returns the index of the target word if found, otherwise returns -1
        
        for (int k=start; k<words.length-1; k++) {
            if (words[k].equals(target1) && words[k+1].equals(target2)) {
                return k;
            }
        }
        return -1;
    }
    
    public void testIndexOf() {
        String testString = "this is just a test yes this is a simple test";
        String[] testStringArr = testString.split("\\s+");
        
        System.out.println(indexOf(testStringArr, "this", "is", 0));
        System.out.println(indexOf(testStringArr, "just", "a", 0));
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        // finds the words that directly follow after any occurence of the
        // word assigned to the variable key
        
        // adds these words to and returns the array list 'follows'
        
        // uses the helper function indexOf() to find the index of any
        // occurence of the word assigned to key in a string array
        
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        
        while (pos < myText.length) {
            int start = indexOf(myText, key1, key2, pos);
            if (start == -1) {
                break;
            }
            if (start + 2 > myText.length-1) {
                break;
            }
            String next = myText[start+2];
            follows.add(next);
            pos = start + 2;
        }
        
        return follows;
    }

}

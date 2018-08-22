
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author Daniel J Hardej
 * @version v1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> myMap;
    
    public EfficientMarkovWord (int order) {
        myOrder = order;
        myRandom = new Random();
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram keyGram = new WordGram(myText, index, myOrder);
        sb.append(keyGram.toString());
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(keyGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            keyGram = keyGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        // find the index of any occurence of the word assigned to variable
        // target in the string array words
        
        // returns the index of the target word if found, otherwise returns -1
        
        for (int k=start; k<words.length; k++) {
            if (words[k].equals(target)) {
                return k;
            }
        }
        return -1;
    }
    
    public void testIndexOf() {
        String testString = "this is just a test yes this is a simple test";
        String[] testStringArr = testString.split("\\s+");
        
        
    }
    
    private ArrayList<String> getFollows(WordGram keyGram) {
        return myMap.get(keyGram);
    }
    
    public void buildMap() {
        
        for (int i=0; i < myText.length-(myOrder-1); i++) {
            // create a new WordGram from myText for each iteration that
            // starts at the current location in the myText array
            WordGram wg = new WordGram(myText, i, myOrder);
            
            // if a WordGram is not in the HashMap yet
            if (!(myMap.containsKey(wg))) {
                // and we HAVE NOT reached the end of the myText array
                if ((i + myOrder)<myText.length) {
                    // put the WordGram into an empty arraylist
                    myMap.put(wg, new ArrayList<String>(Arrays.asList(myText[i+myOrder])));
                }
                // and we HAVE reached the end of the myText array
                if ((i + myOrder)==myText.length) {
                    // create a new entry with key wg and and empty ArrayList as the values
                    myMap.put(wg, new ArrayList<String>());
                }
            }
            // if a WordGram is already in the HashMap
            else if (myMap.containsKey(wg) && (i + myOrder)<myText.length) {
                // do not enter anything for this case?
                // ...or try to update the values? Lets see how this goes...
                ArrayList<String> currentValues = myMap.get(wg);
                currentValues.add(myText[i+myOrder]);
                myMap.replace(wg, currentValues);
            }
        }
    }
    
    public void printHashMapInfo() {
        System.out.println("Keys in HashMap: " + myMap.size());
        
        int maxArrSize = 0;
        WordGram keyWithMaxArrSize = null;
        for (WordGram wg : myMap.keySet()) {
            maxArrSize = Math.max(maxArrSize, myMap.get(wg).size());
            if (myMap.get(wg).size()==maxArrSize) {
                keyWithMaxArrSize = wg;
            }
        }
        
        System.out.println("Size of largest ArrayList in HashMap:   " +  maxArrSize);
        System.out.println("WordGram with largest array in HashMap: " + keyWithMaxArrSize);
        
        if (myMap.size() < 30) {
            System.out.println("WordGrams in HashMap: ");
            for (WordGram wg : myMap.keySet()) {
                System.out.println(wg);
            }
        }
        
    }
}

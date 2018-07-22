
/**
 * Counting word frequencies in a document (loaded as a string from
 * a FileResource) to demonstrate use of ArrayList.
 * 
 * @author Daniel Hardej
 * @version 
 */

import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies () {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique () {
        FileResource resource = new FileResource();
        
        for (String s: resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public int findMostCommon () {
        int maxFreq = 0;
        for (int k=0; k<myFreqs.size(); k++ ) {
            int currentFreq = myFreqs.get(k);
            if (currentFreq > maxFreq) {
                
            }
        }
        return maxFreq;
    }
    
    public void tester () {
        findUnique();
        
        for (int k=0; k<myWords.size(); k++) {
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
        System.out.println("# unique words: " + myWords.size());
    }
}

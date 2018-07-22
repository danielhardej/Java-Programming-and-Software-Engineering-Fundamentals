
/**
 * Write a description of WordFrequencies here.
 * 
 * @author Daniel Hardej
 * @version (a version number or a date)
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
        myWords.clear();
        myFreqs.clear();
        
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
    
    public int findIndexOfMax () {
        int maxIndex = 0;
        int maxVal = 0;
        for (int k = 0; k<myFreqs.size(); k++) {
            int currentVal = myFreqs.get(k);
            if (currentVal > maxVal) {
                maxVal = currentVal;
                maxIndex = k;
            }
            if (currentVal == maxVal) {
                // nothing!
            }
        }
        return maxIndex;
    }
    
    public void tester () {
        findUnique();
        for (int k=0; k<myWords.size(); k++) {
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
        System.out.println("# unique words: " + myWords.size());
        
        int maxIdx = findIndexOfMax();
        System.out.println("Index of max val:   " + maxIdx);
        System.out.println("Max val in myFreqs: " + myFreqs.get(maxIdx));
        System.out.println("Most common word:   " + myWords.get(maxIdx));
    }
}

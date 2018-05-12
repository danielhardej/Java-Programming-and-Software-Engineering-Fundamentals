
/**
 * WordsWithArrays
 * The WordsWithArrays is a new, modified version of the
 * WordFrequencies class. It fulfils the same purpose, however here
 * we demonstrate how arrays and StorageResources can be used.
 * 
 * @author Daniel Hardej
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class WordsWithArrays {
    StorageResource myWords;
    
    public WordsWithArrays () {
        myWords = new StorageResource();
    }
    
    public void readWords () {
        myWords.clear();
        FileResource resource = new FileResource();
        for (String word: resource.words()) {
            myWords.add(word.toLowerCase());
        }
    }
    
    public boolean contains(String [] list, String word, int number) {
        for (int k=0; k< number; k++) {
            if (list[k].equals(word)) {
                return true;
            }
        }
        return false;
    }
    
    public int numberOfUniqueWords () {
        int numStored = 0;
        String [] words = new String [myWords.size()];
        for (String s : myWords.data()) {
            if (!contains(words, s, numStored)) {
                words[numStored] = s;
                numStored++;
            }
        }
        return numStored;
    }
    
    public void tester () {
        readWords();
        System.out.println("Number of words read: " + myWords.size());
        int unique = numberOfUniqueWords();
        System.out.println("Array count: " + unique);
    }
    
}

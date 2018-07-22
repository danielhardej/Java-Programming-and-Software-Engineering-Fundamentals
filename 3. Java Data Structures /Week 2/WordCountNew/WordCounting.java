
/**
 * Making a better word count program using HashMaps!
 * 
 * Finds word frequencies of unique words.
 * 
 * @author Daniel Hardej
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class WordCounting {
    
    public void countTotalWords () {
        
        FileResource fr = new FileResource();
        int total = 0;
        
        for (String w : fr.words() ) {
            w = w.toLowerCase();
            total += 1;
        }
        System.out.println(" total: "+total);
    }
    
    public void countUniqueWords () {
        FileResource fr = new FileResource();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int totalUniqueWords = 0;
        
        for (String w : fr.words() ) {
            w = w.toLowerCase();
            if (map.keySet().contains(w)) {
                map.put(w, map.get(w)+1);
            }
            else {
                map.put(w, 1);
                totalUniqueWords++;
            }
            
        }
        
        for (String w : map.keySet()) {
            int occurrences = map.get(w);
            if (occurrences > 200) {
                System.out.println(occurrences + "\t" + w);
            }
        }
        System.out.println("Total unique words: " + totalUniqueWords);
    }
}


/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths (FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int wordLen = 0;
            for (int i=0; i<word.length(); i++) {
                if (Character.isLetter(word.charAt(i))) {
                    wordLen++;
                }
                else {
                    break;
                }
            }
            if (wordLen < counts.length) {
                counts[wordLen]++;
            }
            if (wordLen >= counts.length) {
                counts[counts.length]++;
            }
        }
    }
    
    public int maxIndex (int [] vals) {
        int maxIdx = 0;
        for (int k=0; k<vals.length; k++) {
            if (vals[k] > vals[maxIdx]) {
                maxIdx = k;
            }
        }
        return maxIdx;
    }
    
    public void testCountWordLengths() {
        int[] counts = new int [31];
        FileResource resource = new FileResource();
        countWordLengths(resource, counts);
        
        for (int i=0; i<counts.length; i++) {
            System.out.println("Words of length " +  i + ": " + counts[i]);
        }
        int mostCommonLen = maxIndex(counts);
        System.out.println("Most common word length: " + mostCommonLen);
    }
}

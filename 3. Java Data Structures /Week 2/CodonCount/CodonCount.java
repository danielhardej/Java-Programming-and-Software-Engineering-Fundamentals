
/**
 * Week 2 Using and Improving Glad Libs
 * Assignment 1: Codon Count
 * 
 * @author Daniel Hardej
 * @version v1.0
 */

import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap<String, Integer> codonCount;
    
    public CodonCount () {
        codonCount = new HashMap<String, Integer>();
        
    }
    
    public void buildCodonCount (int start, String dna) {
        codonCount.clear();
        for (int idx=start; idx<dna.length()-3; idx+=3) {
            String codon = dna.substring(idx, idx+3);
            if (codonCount.keySet().contains(codon)) {
                codonCount.put(codon, codonCount.get(codon)+1);
            }
            else {
                codonCount.put(codon, 1);
            }
        }
    }
    
    public String getMostCommonCodon () {
        int highestCount = 0;
        String mostCommonCodon = "";
        for (String codon : codonCount.keySet()) {
            int currentCount = codonCount.get(codon);
            if (currentCount > highestCount) {
                mostCommonCodon = codon;
                highestCount = currentCount;
            }
        }
        return mostCommonCodon;
    }
    
    public void printCodonCounts (int start, int end) {
        for (String codon : codonCount.keySet()) {
            int count = codonCount.get(codon);
            if ((count >= start) && (count <= end)) {
                System.out.println(codon + "\t" + count);
            }
        }
    }
    
    public void test () {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        System.out.println(dna);
        //String dna = "CGTTCAAGTTCAA";
        for (int frame = 0; frame<3; frame++) {
            buildCodonCount(frame, dna);
            System.out.println("Reading frame: " + frame);
            System.out.println("Total number of unique codons: "+codonCount.size());
            /*
            for (String codon : codonCount.keySet()) {
                int count = codonCount.get(codon);
                System.out.println(codon + "\t" + count);
            }
            */
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("Most common codon: ");
            System.out.println(mostCommonCodon + "\t" + codonCount.get(mostCommonCodon));
            
            int start = 1;
            int end = 7;
            System.out.println("Codons with counts between " + start + " and " + end + ": ");
            printCodonCounts(start, end);
        }
    }
}


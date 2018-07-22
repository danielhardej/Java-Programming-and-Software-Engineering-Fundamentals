
/**
 * Write a description of Part1 here.
 * 
 * @author Daniel Hardej
 * @version 1.0 April 2018
 */
import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopCodonIndex = dna.indexOf(stopCodon, startIndex);
        int diff = stopCodonIndex - startIndex;
        if (diff%3 == 0) {
            return stopCodonIndex;
        }
        else {
            return dna.length();
        }
    }
    public String findGene(String dna, int startIndex) {
        
        int firstCodonIndex = dna.indexOf("ATG", startIndex);
        if (firstCodonIndex == -1) {
            return "";
        }
        int TAAIndex = findStopCodon(dna, firstCodonIndex, "TAA");
        int TAGIndex = findStopCodon(dna, firstCodonIndex, "TAG");
        int TGAIndex = findStopCodon(dna, firstCodonIndex, "TGA");
        int minStopIndex = Math.min(TAAIndex,Math.min(TAGIndex, TGAIndex));
        if (minStopIndex == dna.length()) {
            return "";
        }
        return dna.substring(firstCodonIndex, minStopIndex+3);
    }
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while ( true ) {
            String currentGene = findGene(dna, startIndex);
            if ( currentGene.isEmpty() ) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex)
                         + currentGene.length();
        }
    }
    public int countGenes(String dna) {
        int startIndex = 0;
        int count = 0;
        while ( true ) {
            String currentGene = findGene(dna, startIndex);
            if ( currentGene.isEmpty() ) {
                break;
            }
            count++;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return count;
    }
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while ( true ) {
            String currentGene = findGene(dna, startIndex);
            if ( currentGene.isEmpty() ) {
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex)
                         + currentGene.length();
        }
        return geneList;
    }
    public void testGetAllGenes() {
        StorageResource dnaStrings = new StorageResource();
        dnaStrings.add("ATGTAAGATGCCCTAGT");                            // 2 
        dnaStrings.add("ATGTAAGATGCCCTAGTCCATGCGCTGACCC");              // 3
        dnaStrings.add("ATGTAAGATGCCCTAGTCCATGCGCTGACCCATGCACTAG");     // 4
        dnaStrings.add("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");            // 2
        for (String dna : dnaStrings.data()) {
            System.out.println("Testing getAllGenes on: " + dna);
            StorageResource genes = getAllGenes(dna);
            for (String g : genes.data()) {
                System.out.println(g);
            }
            System.out.println("Gene count for " + dna + ": " + countGenes(dna));
            System.out.println("-----------------------------");
        }
    }
}

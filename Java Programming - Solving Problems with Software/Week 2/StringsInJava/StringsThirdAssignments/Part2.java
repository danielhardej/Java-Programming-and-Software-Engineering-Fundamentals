
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.StorageResource;

public class Part2 {
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
        int TAAIndex = findStopCodon(dna, firstCodonIndex+3, "TAA");
        int TAGIndex = findStopCodon(dna, firstCodonIndex+3, "TAG");
        int TGAIndex = findStopCodon(dna, firstCodonIndex+3, "TGA");
        int minStopIndex = 0;
        if ((TAAIndex == -1) || (TGAIndex != -1 && TGAIndex<TAAIndex)) {
            minStopIndex = TGAIndex;
        }
        else {
            minStopIndex = TAAIndex;
        }
        if ((minStopIndex == -1) || (TAGIndex != -1 && TAGIndex<minStopIndex)) {
            minStopIndex = TAGIndex;
        }
        if (minStopIndex == -1) {
            return "";  // Returns an empty string if no valid stop codon
        }
        String gene = dna.substring(firstCodonIndex, minStopIndex+3);
        return gene;
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
    public void testprintAllGenes() {
        String gene1 = "ATGATCATAAGAAGATAATAGAGGGCCATGTAA";
        System.out.println("Testing on: " + gene1);
        printAllGenes(gene1);
        String gene2 = "";
        System.out.println("Testing on: " + gene2);
        printAllGenes(gene2);
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
    public float cgRatio(String dna) {
        float cCount = 0;
        float gCount = 0;
        int cStartIndex = 0;
        int gStartIndex = 0;
        while ( true ) {
            cStartIndex = dna.indexOf("C", cStartIndex);
            if ( cStartIndex == -1) {
                break;
            }
            cCount++;
            cStartIndex = cStartIndex + 1;
        }
        while ( true ) {
            gStartIndex = dna.indexOf("G", gStartIndex);
            if ( gStartIndex == -1) {
                break;
            }
            gCount++;
            gStartIndex = dna.indexOf("G", gStartIndex) + 1;
        }
        float cgRatio = cCount / gCount;
        return cgRatio;
    }
    public int countCTG(String dna) {
        int CTGcount = 0;
        int ctgCodonIndex = 0;
        while (ctgCodonIndex != -1) {
            ctgCodonIndex = dna.indexOf("CTG", ctgCodonIndex);
            if (ctgCodonIndex == -1) {
                break;
            }
            CTGcount++;
            ctgCodonIndex = dna.indexOf("CTG", ctgCodonIndex) + 3;
        }
        /*
        StorageResource dnaCodons = new StorageResource();
        String gene = findGene(dna, 0);
        for (String codon : dnaCodons.data()) {
            if (codon == "CGT") {
                CTGcount++;
            }
        }
        */
        return CTGcount;
    }
    public StorageResource dnaTestStrings () {
        StorageResource dnaTestStrings = new StorageResource();
        dnaTestStrings.add("ATGTAAGATGCCCTAGT");                            //  
        dnaTestStrings.add("ATGTAAGATGCCCTAGTCCATGCGCTGACCC");              // 
        dnaTestStrings.add("ATGTAAGATGCCCTAGTCCATGCGCTGACCCATGCACTAG");     // 
        dnaTestStrings.add("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");            // 
        dnaTestStrings.add("CTGCCCGGGTTTAACGTGCTGCTGCTGTTTCTGACACTG");
        return dnaTestStrings;
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
    public void testCGRatio() {
        StorageResource dnaStrings = new StorageResource();
        dnaStrings.add("ATGTAAGATGCCCTAGT");                            // 3/4
        dnaStrings.add("ATGTAAGATGCCCTAGTCCATGCGCTGACCC");              // 10/7
        dnaStrings.add("ATGTAAGATGCCCTAGTCCATGCGCTGACCCATGCACTAG");     // 
        dnaStrings.add("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");            // 
        for (String dna : dnaStrings.data()) {
            System.out.println("CG-ratio for " + dna + ": " + cgRatio(dna));
            System.out.println("-----------------------------");
        }
    }
    public void testCountCTG () {
        StorageResource dnaStrings = dnaTestStrings();
        for (String dna : dnaStrings.data()) {
            System.out.println("CTG count for " + dna + ": " + countCTG(dna));
            System.out.println("-----------------------------");
        }
    }
}

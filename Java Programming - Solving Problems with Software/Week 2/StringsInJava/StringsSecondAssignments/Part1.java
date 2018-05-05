
/**
 * Write a description of Part1 here.
 * 
 * @author Daniel Hardej
 * @version 1.0 April 2018
 */
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
    public void testFindStopCodon() {
        System.out.println(findStopCodon("ATGTAA", 0,"TAA"));                       // Should return 3
        System.out.println(findStopCodon("ATGCGCTGCCAGGATTAA", 0,"TAA"));           // 15
        System.out.println(findStopCodon("ATGCGCTGCCAGGATTAGCGA", 0,"TAG"));        // 15
        System.out.println(findStopCodon("ATGTGAAATTTTATGGCGTGCTGAAGG", 6,"TGA"));  // 21
        System.out.println(findStopCodon("ATGTGAAATTTTATGGCGTGCTGAAGG", 5,"TGA"));  // 27
        System.out.println("------------END-----------");
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
    public void testFindGene() {
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna, 0);
        if (!gene.equals("ATGCCCGGGAAATAA")) {
            System.out.println("error");
        }
        System.out.println("tests complete");
        System.out.println("ATGCCCGGGAAATAA");
    }
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while ( true ) {
            String currentGene = findGene(dna, startIndex);
            if ( currentGene.isEmpty() ) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
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
}

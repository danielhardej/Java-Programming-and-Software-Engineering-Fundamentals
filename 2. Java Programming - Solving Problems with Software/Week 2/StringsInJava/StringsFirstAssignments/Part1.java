
/**
 * Write a description of Part1 here.
 * 
 * @author Daniel Hardej 
 * @version 1.0 April 2018
 */
public class Part1 {
    String START_CODON = "ATG";
    String STOP_CODON = "TAA";
    public String findSimpleGene (String dna) {
        String geneResult = "";
        int startIndex = dna.indexOf(START_CODON);
        if (startIndex == -1) {
            return "Error: no start codon";
        }
        int stopIndex = dna.indexOf(STOP_CODON, startIndex+3);
        if (stopIndex == -1) {
            return "Error: no end codon";
        }
        geneResult = dna.substring(startIndex, stopIndex+3);
        if (geneResult.length()%3 == 0) {
            return geneResult;
        }
        else {
            return "Error: DNA strand len not valid";
        }
    }
    public void testSimpleGene () {
        String dna = "GGGACGATGCGTCATGCTGTAACCCGCG";
        System.out.println("DNA strand is: " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is: " + gene);
        
        dna = "TATGGGAAAATGTGTGAG";
        System.out.println("DNA strand is: " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is: " + gene);
        
        dna = "TTTGGGAGTATTATAGAGCCGCGACATAAACGAGC";
        System.out.println("DNA strand is: " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is: " + gene);
        
        dna = "ACCACTACGATGGTAGAAGGGTTTCCCCACGCGGAGTAAAGGCGGCAATGC";
        System.out.println("DNA strand is: " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is: " + gene);
    }
}

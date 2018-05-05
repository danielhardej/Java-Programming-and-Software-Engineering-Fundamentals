
/**
 * Write a description of Part3 here.
 * 
 * @author Daniel Hardej
 * @version 1.0 April 2018
 */
import edu.duke.*;

public class Part3 {
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex)
                         + currentGene.length();
        }
        return geneList;
    }
    public String findGene(String dna, int startIndex) {
        int firstCodonIndex = dna.indexOf("ATG", startIndex);
        if (firstCodonIndex == -1) {
            return "";
        }
        int TAAIndex = findStopCodon(dna, firstCodonIndex, "TAA");
        int TAGIndex = findStopCodon(dna, firstCodonIndex, "TAG");
        int TGAIndex = findStopCodon(dna, firstCodonIndex, "TGA");
        // int minStopIndex = Math.min(TAAIndex,Math.min(TAGIndex, TGAIndex));
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
        return dna.substring(firstCodonIndex, minStopIndex+3);
    }
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopCodonIndex = dna.indexOf(stopCodon, startIndex+3);
        while (stopCodonIndex != -1) {
            int diff = stopCodonIndex - startIndex;
            if (diff%3 == 0) {
                return stopCodonIndex;
            }
            else {
                stopCodonIndex = dna.indexOf(stopCodon, stopCodonIndex+1);
            }
        }
        return -1;
    }
    public double cgRatio(String dna) {
        float cgCount = 0;
        for (int i=0; i<dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                cgCount++;
            }
        }
        double cgRatio = cgCount / dna.length();
        return cgRatio;
    }
    public void processGenes (StorageResource sr) {
        int longGeneCount = 0; // count of genes > 9 chars long
        //System.out.println("Genes longer than 60 characters: ");
        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                //System.out.println(gene);
                longGeneCount++;
            }
        }
        System.out.println("Number of genes longer than 60 characters: " + longGeneCount);
        //System.out.println("Genes with a CG-ratio > 0.35");
        int largeCGRatioCount = 0;
        for (String gene : sr.data()) {
            double cgRatio = cgRatio(gene);
            if ( cgRatio > 0.35) {
                //System.out.println(gene);
                largeCGRatioCount++;
            }
        }
        System.out.println("Number of genes with a CG-ratio>0.35: " + largeCGRatioCount);
        String longestGene = "";
        for (String gene : sr.data()) {
            if (gene.length() > longestGene.length()) {
                longestGene = gene;
            }
        }
        System.out.println("Length of longest gene: " + longestGene.length());
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
    public void testProcessGenes() {
        FileResource file = new FileResource("brca1line.fa");
        String sequence = file.asString();
        sequence = sequence.toUpperCase();
        StorageResource genes = getAllGenes(sequence);
        
        System.out.println("Number of genes found: " + countGenes(sequence));
        processGenes(genes);
        System.out.println("CTG count: " + countCTG(sequence));
    }
}

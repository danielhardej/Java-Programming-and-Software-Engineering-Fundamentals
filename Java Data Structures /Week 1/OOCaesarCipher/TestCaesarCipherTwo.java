
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author Daniel Hardej
 * @version 
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    
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
    
    
    public void simpleTests() {
        CaesarCipherTwo cctwo = new CaesarCipherTwo(14, 24);
        //FileResource fr = new FileResource();
        //String str = fr.toString();
        String str = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        System.out.println("Original sting: \n" + str);
        //String encrypted = cctwo.encrypt(str);
        //System.out.println("Encrypted string: \n" + encrypted);
        String decrypted = cctwo.decrypt(str);
        System.out.println("Decrypted string: \n" + decrypted);
    }
}


/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author Daniel Hardej
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher {
    
    public String alpha = "abcdefghijklmnopqrstuvwxyz";
    
    public int[] countOccurences (String message) {
        
        int[] counts = new int[alpha.length()];
        
        for (int k=0; k<message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int idx = alpha.indexOf(ch);
            if (idx != -1) {
                counts[idx]++;
            }
        }
        return counts;
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
    
    public int encryptionKey(String encrypted) {
        
        int [] freq = countOccurences(encrypted.toString());
        int maxIdx = maxIndex(freq);
        // char mostFreqChar = alpha.charAt(maxIdx);
        int eKey = maxIdx - 4;
        if (maxIdx < 4) {
            eKey = 26 - (4-maxIdx);
        }
        
        return eKey;
    }
    
    public String breakCaesarCipher(String input) {
        int key = encryptionKey(input);
        CaesarCipher cc = new CaesarCipher(key);
        String decryptedString = cc.decrypt(input);
        return decryptedString;
    }
    
    public void simpleTests () {
       // FileResource fr = new FileResource();
       //String str = fr.asString();
       String str = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
       CaesarCipher cc = new CaesarCipher(15);
       String encrypted = cc.encrypt(str);
       String decrypted = breakCaesarCipher(encrypted);
       System.out.println(encrypted);
       System.out.println(decrypted);
    }
}

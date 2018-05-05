
/**
 * Write a description of CaesarCipherTwoKeysDecrypt here.
 * 
 * @author Daniel Hardej
 * @version May 2018
 */

import edu.duke.*;


public class CaesarCipherTwoKeysDecrypt {
    public String alpha = "abcdefghijklmnopqrstuvwxyz";
    
    public String decryptOneKey (String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        
        int dKey = decryptionKey(encrypted);
        
        String decrypted = cc.encrypt(encrypted, dKey);
        return decrypted;
    }
    
    public int decryptionKey(String encrypted) {
        
        int [] freq = countOccurences(encrypted.toString());
        int maxIdx = maxIndex(freq);
        // char mostFreqChar = alpha.charAt(maxIdx);
        int eKey = maxIdx - 4;
        if (maxIdx < 4) {
            eKey = 22 + maxIdx;
        }
        
        int dKey = 26-eKey;
        
        return dKey;
    }
    
    public String decryptTwoKeys (String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        StringBuilder encryptedHalf1 = new StringBuilder();
        StringBuilder encryptedHalf2 = new StringBuilder();
        for (int i=0; i<encrypted.length(); i++) {
            if (i % 2 == 0) {
                encryptedHalf1.append(encrypted.charAt(i));
            }
            if (i % 2 == 1) {
                encryptedHalf2.append(encrypted.charAt(i));
            }
        }
        
        int dKey1 = decryptionKey(encryptedHalf1.toString());
        System.out.println("Decryption key 1: " + dKey1);
        int dKey2 = decryptionKey(encryptedHalf2.toString());
        System.out.println("Decryption key 2: " + dKey2);
        
        String decrypted = cc.encryptTwoKeys(encrypted, dKey1, dKey2);
        
        return decrypted.toString();
    }
    
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
    
    public void testDecryptOneKey () {
        String encrypted = "zzzzzzzzzzzzzzz";
        String decrypted = decryptOneKey(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
    
    public void testDecryptTwoKeys () {
        //String encrypted = "";
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
    
    public void sandbox () {
        System.out.println(1%2); //1
        System.out.println(0%2); //0
    }
}

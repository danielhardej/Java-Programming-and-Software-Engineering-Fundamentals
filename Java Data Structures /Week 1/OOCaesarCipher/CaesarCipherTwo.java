
/**
 * A modified version of the OO CaesarCipher class that can encrypt/decrypt
 * with two keys.
 * 
 * @author Daniel Hardej
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    int mainKey1;
    int mainKey2;
    
    public CaesarCipherTwo (int key1, int key2) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public int[] countOccurences (String message) {
        
        int[] counts = new int[alphabet.length()];
        
        for (int k=0; k<message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int idx = alphabet.indexOf(ch);
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
    
    
    public String halfOfString (String input, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i=start; i<input.length(); i+=2) {
            halfString.append(input.charAt(i));
        }
        return halfString.toString();
    }
    
    public String encrypt(String inputString) {
        StringBuilder newString = new StringBuilder(inputString);
        
        for (int i=0; i<inputString.length(); i++) {
            char currChar = inputString.charAt(i);
            if (i % 2 == 0) {
                int idx = alphabet.indexOf(currChar);
                if (Character.isLowerCase(currChar)) {
                    idx = alphabet.toLowerCase().indexOf(currChar);
                }
                if (idx != -1) {
                    if (Character.isLowerCase(currChar)) {
                        char newChar = shiftedAlphabet1.toLowerCase().charAt(idx);
                        newString.setCharAt(i, newChar);
                    }
                    else {
                        char newChar = shiftedAlphabet1.charAt(idx);
                        newString.setCharAt(i, newChar);
                    }
                }
            }
            if (i % 2 == 1) {
                int idx = alphabet.indexOf(currChar);
                if (Character.isLowerCase(currChar)) {
                    idx = alphabet.toLowerCase().indexOf(currChar);
                }
                if (idx != -1) {
                    if (Character.isLowerCase(currChar)) {
                        char newChar = shiftedAlphabet2.toLowerCase().charAt(idx);
                        newString.setCharAt(i, newChar);
                    }
                    else {
                        char newChar = shiftedAlphabet2.charAt(idx);
                        newString.setCharAt(i, newChar);
                    }
                }
            }
        }
        return newString.toString();
    }
    
    public String decrypt (String input) {
        String encryptedHalf1 = halfOfString(input, 0);
        String encryptedHalf2 = halfOfString(input, 1);
        
        int eKey1 = encryptionKey(encryptedHalf1.toString());
        System.out.println("Encryption key 1: " + eKey1);
        int eKey2 = encryptionKey(encryptedHalf2.toString());
        System.out.println("Encryption key 2: " + eKey2);
        
        int dKey1 = 26-mainKey1;
        int dKey2 = 26-mainKey2;
        
        CaesarCipherTwo cc2 = new CaesarCipherTwo(dKey1, dKey2);
        String decrypted = cc2.encrypt(input);
        
        return decrypted.toString();
    }
    
}

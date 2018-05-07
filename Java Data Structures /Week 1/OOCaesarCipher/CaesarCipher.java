
/**
 * An updated, refactored version of the Caesar Cipher class.
 * There's some serious OO programming here.
 * 
 * @author Daniel Hardej
 * @version April 2018
 */

import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
        
    public CaesarCipher (int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i=0; i<encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if (Character.isLowerCase(currChar)) {
                idx = alphabet.toLowerCase().indexOf(currChar);
            }
            if (idx != -1) {
                if (Character.isLowerCase(currChar)) {
                    char newChar = shiftedAlphabet.toLowerCase().charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
                else {
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt (String encrypted) {
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        String decrypted = cc.encrypt(encrypted);
        return decrypted;
    }
    
    public String encryptTwoKeys(String inputString, int key1, int key2) {
        StringBuilder newString = new StringBuilder(inputString);
        for (int i=0; i<inputString.length(); i++) {
            char currChar = inputString.charAt(i);
            if (i % 2 == 0) {
                String shiftedAlphabet = alphabet.substring(key1) + alphabet.substring(0,key1);
                int idx = alphabet.indexOf(currChar);
                if (Character.isLowerCase(currChar)) {
                    idx = alphabet.toLowerCase().indexOf(currChar);
                }
                if (idx != -1) {
                    if (Character.isLowerCase(currChar)) {
                        char newChar = shiftedAlphabet.toLowerCase().charAt(idx);
                        newString.setCharAt(i, newChar);
                    }
                    else {
                        char newChar = shiftedAlphabet.charAt(idx);
                        newString.setCharAt(i, newChar);
                    }
                }
            }
            if (i % 2 == 1) {
                String shiftedAlphabet = alphabet.substring(key2) + alphabet.substring(0,key2);
                int idx = alphabet.indexOf(currChar);
                if (Character.isLowerCase(currChar)) {
                    idx = alphabet.toLowerCase().indexOf(currChar);
                }
                if (idx != -1) {
                    if (Character.isLowerCase(currChar)) {
                        char newChar = shiftedAlphabet.toLowerCase().charAt(idx);
                        newString.setCharAt(i, newChar);
                    }
                    else {
                        char newChar = shiftedAlphabet.charAt(idx);
                        newString.setCharAt(i, newChar);
                    }
                }
            }
        }
        return newString.toString();
    }
    
    
    
    public void testEncryptTwoKeys () {
        String encrypted = "Top ncmy qkff vi vguv vbg ycpx";
        int key1 = 2;
        int key2 = 20;
        String decrypted = encryptTwoKeys(encrypted, 26-key1, 26-key2);
        System.out.println(decrypted);
    }
}


/**
 * Write a description of CaesarCipherAssignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipherAssignment {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        
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
    
    public String encryptTwoKeys(String inputString, int key1, int key2) {
        StringBuilder newString = new StringBuilder(inputString);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
    
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    public void testEncryptTwoKeys() {
        String teststring = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key1 = 8;
        int key2 = 21;
        String twoKeyTest = encryptTwoKeys(teststring, key1, key2);
        System.out.println(twoKeyTest);
    }
}

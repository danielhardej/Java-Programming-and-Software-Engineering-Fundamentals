
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
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
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}

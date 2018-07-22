
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Tester {
    public void testSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        String testSlice = vb.sliceString("abcdefghijklm", 4, 5);
        System.out.println(testSlice);
    }
    
    public void testTryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource encryptedFile = new FileResource("messages/secretmessage2.txt");
        String encryptedStr = encryptedFile.asString();
        int testKeyLen = 38;
        int[] testKey = vb.tryKeyLength(encryptedStr, testKeyLen, 'e');
        
        VigenereCipher newVC = new VigenereCipher(testKey);
        String testDecrypted = newVC.decrypt(encryptedStr);
        
        System.out.println(Arrays.toString(testKey));
        
        FileResource dict = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(dict);
        System.out.println("Number of valid words: "+ vb.countWords(testDecrypted,dictionary));
        
    }
    
    
    
    public void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
    
    public void testBreakVigenereAnyLang () {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenereAnyLang();
    }
}

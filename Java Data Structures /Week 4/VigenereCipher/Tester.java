
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
        FileResource encryptedFile = new FileResource("messages/secretmessage1.txt");
        String encryptedStr = encryptedFile.asString();
        int testKeyLen = 4;
        int[] testKey = vb.tryKeyLength(encryptedStr, testKeyLen, 'e');
        
        VigenereCipher newVC = new VigenereCipher(testKey);
        String testDecrypted = newVC.decrypt(encryptedStr);
        String firstLineDecrypted = newVC.decrypt("Hhdiu LVXNEW uxh WKWVCEW, krg k wbbsqa si Mmwcjiqm");
        //System.out.println(testDecrypted.substring(20));
        System.out.println(firstLineDecrypted);
        System.out.println(Arrays.toString(testKey));
    }
    
    
    
    public void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}

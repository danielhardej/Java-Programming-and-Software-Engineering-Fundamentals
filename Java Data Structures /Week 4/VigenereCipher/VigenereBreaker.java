import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    // Part I
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            slice.append(message.substring(i,i+1));
        }
        
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        CaesarCracker cc = new CaesarCracker(mostCommon);
        
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slice);
        }
        
        return key;
    }
    
    /*
    public void breakVigenere () {
        FileResource encryptedFile = new FileResource("athens_keyflute.txt");
        String encryptedStr = encryptedFile.asString();
        int keyLen = 5;
        int[] key = tryKeyLength(encryptedStr, keyLen, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decryptedStr = vc.decrypt(encryptedStr);
        System.out.println(decryptedStr);
    }
    */
    
    // Part II
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        
        for (String word : fr.lines()) {
            dictionary.add(word.toLowerCase());
        }
        
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int realWordCount = 0;
        
        String[] messageWords = message.split("\\W+");
        
        for (String word : messageWords) {
            if (dictionary.contains(word.toLowerCase())) {
                realWordCount++;
            }
        }
        
        return realWordCount;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        // This one was quite tricky...
        // Create an array to store the word counts that correspond to each
        // key length.
        int[] wordCount = new int[100];
        
        // Find the key length that yields the highest count of real words
        for (int keyLen = 1; keyLen < 100; keyLen++) {
            int[] key = tryKeyLength(encrypted, keyLen, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            //System.out.println(decrypted);
            wordCount[keyLen] = countWords(decrypted, dictionary);
        }
        
        // Find the index of the highest count in the wordCount array
        int largestWordCount = 0;
        int idxOfLargestCount = 0;
        for (int i = 0; i < wordCount.length; i++) {
            if (wordCount[i] > largestWordCount) {
                largestWordCount = wordCount[i];
                idxOfLargestCount = i;
            }
        }
        
        System.out.println("The largest word count is: "+largestWordCount);
        
        int realKeyIdx = idxOfLargestCount;
        int[] realKey = tryKeyLength(encrypted, realKeyIdx, 'e');
        
        System.out.println("The key is: " + Arrays.toString(realKey));
        System.out.println("The key length is: " + realKeyIdx);
        
        VigenereCipher vc = new VigenereCipher(realKey);
        String decrypted = vc.decrypt(encrypted);
        
        return decrypted;
    }
    
    public void breakVigenere () {
        FileResource encryptedFile = new FileResource();
        String encryptedStr = encryptedFile.asString();
        
        FileResource dict = new FileResource();
        HashSet<String> dictionary = readDictionary(dict);
        
        String decryptedStr = breakForLanguage(encryptedStr, dictionary);
        System.out.println(decryptedStr);
    }
}

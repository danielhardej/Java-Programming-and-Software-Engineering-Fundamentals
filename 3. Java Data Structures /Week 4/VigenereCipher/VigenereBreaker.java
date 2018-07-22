import java.util.*;
import edu.duke.*;
import java.io.*;

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
        
        // find the most common char in that dictionary -> pass to tryKeyLength
        char mostCommonCharacter = mostCommonChar(dictionary);
        
        // Find the key length that yields the highest count of real words
        for (int keyLen = 1; keyLen < 100; keyLen++) {
            int[] key = tryKeyLength(encrypted, keyLen, mostCommonCharacter);
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
        int[] realKey = tryKeyLength(encrypted, realKeyIdx, mostCommonCharacter);
        
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
    
    
    public char mostCommonChar(HashSet<String> words) {
        char mostCommonChar = ' ';
        
        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
        
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if ( !(charCount.containsKey(c)) ) {
                    charCount.put(c, 1);
                }
                else {
                    charCount.put(c, (charCount.get(c) + 1));
                }
            }
        }
        
        int highestCharCount = 0;
        for (char c : charCount.keySet()) {
            if (charCount.get(c) > highestCharCount) {
                highestCharCount = charCount.get(c);
                mostCommonChar = c;
            }
        }
        
        return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        
        HashMap<String, Integer> realWordsInLang = new HashMap<String, Integer>();
        
        
        // try with each language in the HashSet languages - use breakForLanguage
        for (String language : languages.keySet()) {
            HashSet<String> currentDict = languages.get(language);
            String decryptedMessage = breakForLanguage(encrypted, currentDict);
            
            int realWordCountForLang = countWords(decryptedMessage, currentDict);
            
            realWordsInLang.put(language, realWordCountForLang);
            System.out.println("Number of words for " + language + ": " + realWordsInLang.get(language));
            
        }

        // Pick the best language
        int highestWordCount = 0;
        String bestLang = null;
        for (String lang : realWordsInLang.keySet()) {
            if (realWordsInLang.get(lang) > highestWordCount) {
                highestWordCount = realWordsInLang.get(lang);
                bestLang = lang;
            }
        }
        
        // get decrypted message
        String decryptedMessage = breakForLanguage(encrypted, languages.get(bestLang));
        System.out.println(decryptedMessage);
        
        System.out.println("Language: " + bestLang);
        
    }
    
    
    
    public void breakVigenereAnyLang () {
        FileResource encryptedFile = new FileResource();
        String encryptedStr = encryptedFile.asString();
        
        HashMap<String, HashSet<String>> allDicts = new HashMap<String, HashSet<String>>();
        
        // for each dict in a set of dicts
        DirectoryResource dr  = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            // open the dict
            FileResource dict = new FileResource(f);
            HashSet<String> dictionary = readDictionary(dict);
            // add dict to the HashMap dicts
            String languageName = f.getName();
            allDicts.put(languageName, dictionary);
        }
        
        
        
        breakForAllLangs(encryptedStr, allDicts);
        }
    }



/**
 * Week 2 Using and Improving GladLibs
 * Assignment 2: Words In Files
 * 
 * @author Daniel Hardej 
 * @version v1.0
 */
import java.util.*;
import java.io.*;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsInFiles;
    
    public WordsInFiles() {
        wordsInFiles = new HashMap<String, ArrayList<String>>();
        
    }
    
    private void addWordsFromFile (File f) {
        String fName = f.getName();
        FileResource fr = new FileResource("data/"+fName);
        
        ArrayList<String> fileList = new ArrayList<String>();
        for (String word : fr.words()) {
            if (!wordsInFiles.keySet().contains(word)) {
                fileList = new ArrayList<String>();
                fileList.add(fName);
                wordsInFiles.put(word, fileList);
            }
            else {
                fileList = wordsInFiles.get(word);
                if (!fileList.contains(fName)) {
                    fileList.add(fName);
                }
            }
        }
    }
    
    public void buildWordFileMap () {
        wordsInFiles.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber () {
        int maxNumFiles = 0;
        for (String word : wordsInFiles.keySet()) {
            ArrayList<String> fileList = wordsInFiles.get(word);
            int numFiles = fileList.size();
            if (numFiles > maxNumFiles) {
                maxNumFiles = numFiles;
            }
        }
        return maxNumFiles;
    }
    
    public ArrayList<String> wordsInNumFiles (int number) {
        ArrayList<String> wordsInNumFiles = new ArrayList<String>();
        for (String word : wordsInFiles.keySet()) {
            ArrayList<String> fileList = wordsInFiles.get(word);
            int numFiles = fileList.size();
            if (numFiles == number) {
                wordsInNumFiles.add(word);
            }
        }
        return wordsInNumFiles;
    }
    
    public void printFilesIn (String word) {
        for (String currentWord : wordsInFiles.keySet()) {
            if (currentWord.toLowerCase().equals(word)) {
                ArrayList<String> fileList = wordsInFiles.get(currentWord);
                for (String fileName : fileList) {
                    System.out.println(fileName);
                }
            }
        }
    }
    
    public void tester () {
        buildWordFileMap();
        int maxNumFiles = maxNumber();
        
        System.out.println("Max number of files a word is in: "+maxNumFiles);
        ArrayList<String> wordsInNumFiles = wordsInNumFiles(4);
        System.out.println("Number of words appearing in 4 files " + wordsInNumFiles.size());
        
        for (String word : wordsInNumFiles) {
            //System.out.println(word + "\t" + "In files: ");
            //printFilesIn(word);
        }
        
        printFilesIn("tree");
        /*
        if (wordsInFiles.size() < 20) {
            System.out.println("All words and files: ");
            for (String word : wordsInFiles.keySet()) {
                System.out.println(word  + "\t" + wordsInFiles.get(word));
            }
        }
        */
    }
    
}


/**
 * Week 2 Using And Improving Glad Libs
 * Assignment 3: Maps Version of Mad Libs
 * 
 * @author Daniel Hardej
 * @version v1.0
 */

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    
    private HashMap<String, ArrayList<String>> myMap;
    
    private ArrayList<String> usedWords;
    private ArrayList<String> catagoriesUsed;
    private ArrayList<String> currentWordList;
    
    private int replacedWordCount;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap () {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        catagoriesUsed = new ArrayList<String>();
        currentWordList = new ArrayList<String>();
        replacedWordCount = 0;
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String [] labels = {"country", "noun", "color", "adjective", "name",
                             "animal", "timeframe", "verb", "fruit"};
        for (String s : labels ) {
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String category = w.substring(first+1,last);
        String sub = getSubstitute(category);
        
        if (!catagoriesUsed.contains(category)) {
            catagoriesUsed.add(category);
        }
        
        while (usedWords.contains(sub)) {
            sub = getSubstitute(w.substring(first+1,last));
        }
        //System.out.println(sub);
        usedWords.add(sub);
        replacedWordCount++;
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap () {
        int mapWordCount = 0;
        for (String label : myMap.keySet()) {
            for (String word : myMap.get(label)) {
                mapWordCount++;
            }
        }
        return mapWordCount;
    }
    
    public int totalWordsConsidered () {
        int totalWordsConsidered = 0;
        
        for (String label : myMap.keySet()) {
            if (catagoriesUsed.contains(label)) {
                for (String word : myMap.get(label)) {
                    totalWordsConsidered++;
                }
            }
        }
        
        return totalWordsConsidered;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        
        int mapWordCount = totalWordsInMap ();
        int totalWordsConsidered = totalWordsConsidered ();
        
        System.out.println("\n\nNumber of words replaced: "+replacedWordCount);
        System.out.println("Total number of words in HashMap: " + mapWordCount);
        System.out.println("Numer of words in categories used: " + totalWordsConsidered);
        
    }
    

}

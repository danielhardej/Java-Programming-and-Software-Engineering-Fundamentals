
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> nameFreqs;
    
    public CharactersInPlay () {
        characterNames = new ArrayList<String>();
        nameFreqs = new ArrayList<Integer>();
    }
    
    
    public void update (String person) {
        int idx = characterNames.indexOf(person);
        if (idx == -1) {
            characterNames.add(person);
            nameFreqs.add(1);
        }
        else {
            int value = nameFreqs.get(idx);
            nameFreqs.set(idx, value+1);
        }
    }
    
    public void findAllCharacters () {
        FileResource resource = new FileResource();
        for (String line : resource.lines()) {
            int periodIdx = line.indexOf(".");
            if (periodIdx != -1) {
                String characterName = line.substring(0, periodIdx);
                if (isUpperCase(characterName)) {
                    //System.out.println(line);
                    //System.out.println(characterName);
                    if (characterName.toLowerCase().indexOf("scene") == -1) {
                        update(characterName);
                    }
                }
            }
        }
    }
    
    public void charactersWithNumParts (int num1, int num2) {
        System.out.println("Characters with more than " + num1 + " parts and less than " + num2 + " parts:");
        for (int numParts : nameFreqs) {
            if ((numParts >= num1) && (numParts < num2)) {
                int idx = nameFreqs.indexOf(numParts);
                String characterName = characterNames.get(idx);
                System.out.println(characterName + " Number of parts: " + numParts);;
            }
        }
    }
    
    public int findIndexOfMax () {
        int maxIndex = 0;
        int maxVal = 0;
        for (int k = 0; k<nameFreqs.size(); k++) {
            int currentVal = nameFreqs.get(k);
            if (currentVal > maxVal) {
                maxVal = currentVal;
                maxIndex = k;
            }
            if (currentVal == maxVal) {
                // nothing!
            }
        }
        return maxIndex;
    }
    
    public int mostSpeakingParts (int maxIdx) {
        int mostSpeakingParts = nameFreqs.get(maxIdx);
        return mostSpeakingParts;
    }
    
    public String characterWithMostParts (int maxIdx) {
        String characterMost = characterNames.get(maxIdx);
        return characterMost;
    }
    
    public boolean isUpperCase (String s) {
        // An extra class to check if the string is all in upper case,
        // since there is no method in java utils library for this!
        for (int i=0; i<s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public void tester () {
        findAllCharacters();
        for (String character : characterNames) {
            int numSpeakingParts = nameFreqs.get(characterNames.indexOf(character));
            if (numSpeakingParts > 1) {
                System.out.println(character + " Number of parts: " + numSpeakingParts);
            }
        }
        
        int maxIdx = findIndexOfMax();
        System.out.println("Character with most parts: " + characterWithMostParts(maxIdx));
        System.out.println("Number of most parts:      " + mostSpeakingParts(maxIdx));
        
        charactersWithNumParts(10,15);
    }
}

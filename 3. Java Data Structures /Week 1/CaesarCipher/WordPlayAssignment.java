
/**
 * Write a description of WordPlayAssignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordPlayAssignment {
    public boolean isVowel(char ch) {
        boolean isVowel = false;
        String vowels = "AEIOU";
        if (vowels.indexOf(Character.toUpperCase(ch)) != -1) {
            isVowel = true;
        }
        return isVowel;
    }
    public String replaceVowels(String inputString, char newChar) {
        StringBuilder newString = new StringBuilder(inputString);
        for (int i=0; i<newString.length(); i++) {
            if ( isVowel(newString.charAt(i)) ) {
                newString.setCharAt(i, newChar);
            }
        }
        return newString.toString();
    }
    public String emphasize(String inputString, char ch) {
        StringBuilder newString = new StringBuilder(inputString);
        for (int i=0; i<inputString.length(); i++) {
            char currChar = inputString.charAt(i);
            if (isVowel(currChar)) {
                if (i % 2 == 0) {
                    newString.setCharAt(i, '*');
                }
                if (i % 2 == 1) {
                    newString.setCharAt(i, '+');
                }
            }
        }
        return newString.toString();
    }
    
    public void testReplaceVowels() {
        String str = "Hello World";
        char ch = '+';
        String newStr = replaceVowels(str, ch);
        System.out.println(str);
        System.out.println(newStr);
    }
    
    public void testEmphasize() {
        String str = "dna ctgaaactga";
        char ch = 'a';
        String newStr = emphasize(str, ch);
        System.out.println(str);
        System.out.println(newStr);
        String otherStr = "Mary Bella Abracadabra";
        String otherNewStr = emphasize(otherStr, ch); 
        System.out.println(str);
        System.out.println(otherNewStr);
    }
}

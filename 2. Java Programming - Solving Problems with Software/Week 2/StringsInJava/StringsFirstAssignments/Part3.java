
/**
 * Write a description of Part3 here.
 * 
 * @author Daniel Hardej
 * @version 1.0 April 2018
 */
public class Part3 {
    public boolean twoOccurrences (String stringA, String stringB) {
        // Counts number of occurrences of stringA in stringB
        int count = 0;
        int lastIndex = 0;
        while (lastIndex != -1) {
            lastIndex = stringB.indexOf(stringA, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += stringA.length();
            }
        }
        if (count >= 2) {
            return true;
        }
        else {
            return false;
        }
    }
    public String lastPart (String stringA, String stringB) {
        String lastString = "";
        int stringStartIndex = stringB.indexOf(stringA);
        if (stringStartIndex != -1) {
            lastString = stringB.substring(stringStartIndex+stringA.length(), stringB.length());
        }
        else {
            lastString = stringB;
        }
        return lastString;
    }
    public void testing () {
        System.out.println(twoOccurrences("tit", "thisisastringwithtitoccurringonlyonce, so should return false"));
        System.out.println(twoOccurrences("word", "the word word has already occurred twice, so this should be true! One more time: word"));
        System.out.println(lastPart("this ", "so does this work? Tell me. It should just print the words after this"));
        System.out.println(lastPart("wow", "wowthis works"));
        System.out.println(lastPart("an","banana"));
        System.out.println(lastPart("zoo","forest"));
    }
}

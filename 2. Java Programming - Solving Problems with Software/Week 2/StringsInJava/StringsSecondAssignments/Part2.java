
/**
 * Write a description of Part2 here.
 * 
 * @author Daniel Hardej
 * @version 1.0 April 2018
 */
public class Part2 {
    public int howMany(String subString, String mainString) {
        // Counts how many times subString appears in mainString;
        // Returns the number of occurrences.
        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1) {
            lastIndex = mainString.indexOf(subString, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex =  mainString.indexOf(subString, lastIndex)
                             + subString.length();
            }
        }
        return count;
    }
    public void testHowMany() {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC")); // 3
        System.out.println(howMany("AA", "ATAAAA"));            // 2
        System.out.println(howMany("DAN", "DAN DAN THE NISSAN MAN IS DAN")); // 3
    }
}


/**
 * Part4 - Finding Web Links
 * A program that reads the lines from the file at a URL location,then prints
 * each URL on the page that is a link to youtube.com. Assume that a link to youtube.com 
 * has no spaces in it and would be in the format (where [stuff] represents 
 * characters that are not verbatim): “http:[stuff]youtube.com[stuff]”
 * 
 * @author Daniel Hardej 
 * @version 1.0 April 2018
 */
import edu.duke.*;

public class Part4 {
    String SEARCH_TERM = "youtube.com";
    String URLres = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
    public boolean hasString (String mainString, String subString) {
        mainString = mainString.toLowerCase();
        subString = subString.toLowerCase();
        if (mainString.indexOf(subString) != -1) {
            return true;
        }
        else {
           return false; 
        }
    }
    public void runCheck () {
        URLResource res = new URLResource(URLres);
        for (String word : res.words()) {
            if (hasString(word, SEARCH_TERM)) {
                int firstQuoteMarkIndex = word.indexOf("\"");
                int lastQuoteMarkIndex = word.indexOf("\"", firstQuoteMarkIndex+1);
                String url = word.substring(firstQuoteMarkIndex+1, lastQuoteMarkIndex);
                System.out.println(url);
            }
        }
    }
}


/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    
    public PhraseFilter (String wh, String phr) {
        where = wh;
        phrase = phr;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        boolean ans = false;
        
        String title = qe.getInfo();
        if (where.toLowerCase().equals("any")) {
            if (title.contains(phrase)) {
                ans = true;
            }
        }
        else if (where.toLowerCase().equals("start")) {
            if (title.startsWith(phrase)) {
                ans = true;
            }
        }
        else if (where.toLowerCase().equals("end")) {
            if (title.endsWith(phrase)) {
                ans = true;
            }
        }
        else {
            ans = false;
        }
        
        return ans;
    }
    
    public String getName() {
        return "Phrase";
    }
}

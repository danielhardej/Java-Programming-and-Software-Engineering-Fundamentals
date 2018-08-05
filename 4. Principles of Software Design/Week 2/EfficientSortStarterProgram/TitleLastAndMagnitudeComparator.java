
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author Daniel Hardej
 * @version 1.0
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    
    public TitleLastAndMagnitudeComparator () {
        
    }
    
    // Assignment III: Last Word in Title Comparator
    
    
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String qe1Title = qe1.getInfo();
        String qe1LastWord = qe1Title.substring(qe1Title.lastIndexOf(" ")+1);
        String qe2Title = qe2.getInfo();
        String qe2LastWord = qe2Title.substring(qe2Title.lastIndexOf(" ")+1);        
        
        if (qe1LastWord.compareTo(qe2LastWord) == 0) {
            //return qe1.compareTo(qe2);
            double q1Mag = qe1.getMagnitude();
            double q2Mag = qe2.getMagnitude();
            
            if (q1Mag < q2Mag) {
                return -1;
            }
            else if (q1Mag > q2Mag) {
                return 1;
            }
        }
        else {
            return qe1LastWord.compareTo(qe2LastWord);
        }
        
        return 0;
    }
}

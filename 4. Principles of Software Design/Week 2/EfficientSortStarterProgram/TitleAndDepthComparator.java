
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author D Hardej
 * @version 1.0
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    TitleAndDepthComparator () {
        
    }
    
    
    // Assignment II: Title Comparator
    
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String qe1Title = qe1.getInfo();
        String qe2Title = qe2.getInfo();
        double qe1Depth = qe1.getDepth();
        double qe2Depth = qe2.getDepth();
        
        int ret = qe1Title.compareTo(qe2Title);
        
        if (ret != 0) {
            return ret;
        }
        if (ret == 0) {
            if (qe1Depth < qe2Depth) {
                return -1;
            }
            else if (qe1Depth > qe2Depth) {
                return 1;
            }
            else {
                return 0;
            }
        }
        
        return 0;
    }
    
    
}

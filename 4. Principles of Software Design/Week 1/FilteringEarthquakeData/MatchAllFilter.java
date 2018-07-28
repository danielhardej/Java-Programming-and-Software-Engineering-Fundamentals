
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author Daniel Hardej
 * @version v1.0
 */

import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    
    MatchAllFilter() {
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filters) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        StringBuilder filtersUsed = new StringBuilder("Filters used: ");
	
	for (Filter f : filters) {
		filtersUsed.append(f.getName()+ " ");
	}
	
	return filtersUsed.toString();
    }
}


/**
 * Write a description of LargestQuakes here.
 * 
 * @author Daniel Hardej
 * @version 1.0
 */

import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size() + " quakes.");
        
        ArrayList<QuakeEntry> largestQuakes = getLargest(list, 50);
        
        for (QuakeEntry qe : largestQuakes) {
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int largestIdx = 0;
        for (int i=0; i<data.size(); i++) {
            double currentMagnitude = data.get(i).getMagnitude();
            if (currentMagnitude > data.get(largestIdx).getMagnitude()) {
                largestIdx = i;
            }
        }
        return largestIdx;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        if (copy.size() >= howMany) {
            for (int i=0; i<howMany; i++) {
                int currentMaxIdx = indexOfLargest(copy);
                answer.add(copy.get(currentMaxIdx));
                copy.remove(currentMaxIdx);
            }
        }
        else {
            for (int i=0; i<copy.size(); i++) {
                int currentMaxIdx = indexOfLargest(copy);
                answer.add(copy.get(currentMaxIdx));
                copy.remove(currentMaxIdx);
            }
        }
        return answer;
    }
}


/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author Daniel Hardej
 * @version 1.0
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
       }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSortTester(list);
        //sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
    }
    
    // Assignment I: Sort by Depth
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int idx = from;
        
        for (int i = from +1; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(idx).getDepth()) {
                idx = i;
            }
        }
        
        return idx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for(int i = 0; i < 70; i++) {
            
            int maxIdx = getLargestDepth(in, i);
            
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            
            in.set(i, qmax);
            in.set(maxIdx, qi);
        }
    }
    
    // Assignment II: Bubble Sort
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i=1; i < quakeData.size()-numSorted; i++) {
            QuakeEntry quake1 = quakeData.get(i-1);       // first quake
            QuakeEntry quake2 = quakeData.get(i);     // second quake
            double quakeMag1 = quake1.getMagnitude();
            double quakeMag2 = quake2.getMagnitude();
            
            // if the first is bigger then the second, swap them, putting the
            // first in the place of the second and second in the place of the first
            if (quakeMag1 > quakeMag2) {
                quakeData.set(i, quake1);
                quakeData.set(i-1, quake2);
            }
            else {
                // nothing?
            }
            
        }
    }
        
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        int N = in.size();          // number of elements in array list
        for (int n=0; n<N-1; n++) {
            onePassBubbleSort(in, n);
        }
    }
    
    /*
    public void sortByMagnitudeWithBubbleSortTester(ArrayList<QuakeEntry> in) {
        int N = in.size();          // number of elements in array list
        for (QuakeEntry qe : in) {
            System.out.println(qe);
        }
        for (int n=0; n<N-1; n++) {
            onePassBubbleSort(in, n);
            System.out.println("Quakes after pass "+n);
            for (QuakeEntry qe : in) {
                System.out.println(qe);
            }
        }
    }
    */
    
    // Assignment 3: Check for Completion
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i=1; i<quakes.size(); i++) {
            if (quakes.get(i-1).getMagnitude() > quakes.get(i).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int N = in.size();          // number of elements in array list
        for (int n=0; n<N-1; n++) {
            if (checkInSortedOrder(in)) {
                System.out.println("Number of passes needed: "+(n));
                break;
            }
            else {
                onePassBubbleSort(in, n);
            }
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            if (checkInSortedOrder(in)) {
                System.out.println("Number of passes needed: "+(i));
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
       }
    }
}

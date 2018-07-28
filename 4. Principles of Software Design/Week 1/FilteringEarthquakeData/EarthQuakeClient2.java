import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        // Filter f = new MinMagFilter(4.0);
        Filter mag_fil = new MagnitudeFilter(3.5, 4.5);
        Filter dep_fil = new DepthFilter(-55000.0, -20000.0);
        
        // ArrayList<QuakeEntry> m7  = filter(list, f); 
        
        
        ArrayList<QuakeEntry> bigQuakes = filter(list, mag_fil);
        ArrayList<QuakeEntry> bigDeepQuakes = filter(bigQuakes, dep_fil);
        for (QuakeEntry qe: bigDeepQuakes) { 
            System.out.println(qe);
        } 
        
        
        //Filter jap_fil = new PhraseFilter("end", "Japan");
        //ArrayList<QuakeEntry> japQuakes = filter(list, jap_fil);
         
        
        System.out.println("found "+bigDeepQuakes.size()+" quakes");
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
    
    
    public void testMatchAllFilter () {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter mf = new MagnitudeFilter(1.0, 4.0);
        Filter df = new DepthFilter(-180000.0, -30000.0);
        Filter pf = new PhraseFilter("any", "o");
        maf.addFilter(mf);
        maf.addFilter(df);
        maf.addFilter(pf);
        
        ArrayList<QuakeEntry> quakesThatMatch = filter(list, maf);
        
        for (QuakeEntry qe : quakesThatMatch) {
            System.out.println(qe);
        }
        
        System.out.println("found "+quakesThatMatch.size()+" quakes that match");
        System.out.println("filters used: " + maf.getName());
    }
    
    public void testMatchAllFilter2 () {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter mf = new MagnitudeFilter(0.0, 5.0);
        Location billund = new Location(55.7308, 9.1153);
        Filter df = new DistanceFilter(billund, 3000000);
        Filter pf = new PhraseFilter("any", "e");
        maf.addFilter(mf);
        maf.addFilter(df);
        maf.addFilter(pf);
        
        ArrayList<QuakeEntry> quakesThatMatch = filter(list, maf);
        
        for (QuakeEntry qe : quakesThatMatch) {
            System.out.println(qe);
        }
        
        System.out.println("found "+quakesThatMatch.size()+" quakes that match");
        System.out.println(maf.getName());
    }
    
    public void quakesWithFilter2() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Location denver = new Location(39.7392, -104.9903);
        
        Filter df = new DistanceFilter(denver, 1000000);
        Filter pf = new PhraseFilter("end", "a");
        
        ArrayList<QuakeEntry> denverQuakes = filter(list, df);
        ArrayList<QuakeEntry> endQuakes = filter(denverQuakes, pf);
        for (QuakeEntry qe: endQuakes) { 
            System.out.println(qe);
        } 
        
        System.out.println("found "+endQuakes.size()+" quakes");
    }
    
    
}

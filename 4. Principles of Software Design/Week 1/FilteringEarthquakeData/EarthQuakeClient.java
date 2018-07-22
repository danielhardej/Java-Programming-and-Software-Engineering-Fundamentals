import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    
    // Part I
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from)<distMax) {
                answer.add(qe);
            }
        }
        
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> bigQuakes = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : bigQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found " + bigQuakes.size() + " that match criteria.");
        
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list, 1000000.0, city);
        
        for (QuakeEntry qe : closeQuakes) {
            System.out.println(qe.getLocation().distanceTo(city) + " " + qe.getInfo());
        }
        
        System.out.println("Found " + closeQuakes.size() + " that match criteria.");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    
    // Part II
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth) {
        System.out.println("Finding quakes between " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if ((qe.getDepth() < minDepth) && (qe.getDepth() > maxDepth)) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Read data for "+list.size()+" quakes");
        
        
        ArrayList<QuakeEntry> deepQuakes = filterByDepth(list, -8000.0, -10000.0);
        for (QuakeEntry qe : deepQuakes) {
            System.out.println(qe);
        }
        
        System.out.println("Found " + deepQuakes.size() + " that match criteria.");
    }
    
    
    // Part III
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String title = qe.getInfo();
            if (where.toLowerCase().equals("any")) {
                if (title.contains(phrase)) {
                    answer.add(qe);
                }
            }
            else if (where.toLowerCase().equals("start")) {
                if (title.startsWith(phrase)) {
                    answer.add(qe);
                }
            }
            else if (where.toLowerCase().equals("end")) {
                if (title.endsWith(phrase)) {
                    answer.add(qe);
                }
            }
            else {
                System.out.println("Invalid location");
            }
        }
        return answer;
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Read data for "+list.size()+" quakes");
        
        String where = "end";         // location to match to
        String phrase = "California";    // phrase being searched for    
        ArrayList<QuakeEntry> phraseQuakes = filterByPhrase(list, where, phrase);
        
        for (QuakeEntry qe : phraseQuakes) {
            System.out.println(qe);
        }
        
        System.out.println("Found " + phraseQuakes.size() + " that match " + phrase + " at " + where);
    }
}

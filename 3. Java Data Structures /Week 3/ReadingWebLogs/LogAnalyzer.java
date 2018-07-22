
/**
 * LogAnalyzer analyzes logs.
 * 
 * @author Daniel J Hardej
 * @version v1.0 July 2018
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
        }
    }
    
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> visitCounts  = new HashMap<String, Integer>();
        
        for (LogEntry le : records) {
            String currentIP = le.getIpAddress();
            if (! visitCounts.containsKey(currentIP)) {
                visitCounts.put(currentIP, 1);
            }
            else {
               visitCounts.put(currentIP, visitCounts.get(currentIP) + 1); 
            }
        }
        
        return visitCounts;
    }
    
    // Part I
    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        
        for (LogEntry le : records) {
            
            String ipAddress = le.getIpAddress();
            if (!(uniqueIPs.contains(ipAddress))) {
                uniqueIPs.add(ipAddress);
            }
            
        }
        return uniqueIPs.size();
    }
    
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    
    public void printAllHigherThenNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le.getStatusCode());
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> ipArr = new ArrayList<String>();
        
        for (LogEntry le : records) {
            String currentDateWhole = le.getAccessTime().toString();
            String day = currentDateWhole.substring(8,10);
            String month = currentDateWhole.substring(4,7);
            if (someday.substring(0,3).equals(month) && someday.substring(4,6).equals(day)) {
                String ipAddress = le.getIpAddress();
                if (!(ipArr.contains(ipAddress))) {
                    ipArr.add(ipAddress);
                }
            }
        }
        return ipArr;
    }
    
    public ArrayList<String> allIPVisitsOnDay(String someday) {
        ArrayList<String> ipArr = new ArrayList<String>();
        
        for (LogEntry le : records) {
            String currentDateWhole = le.getAccessTime().toString();
            String day = currentDateWhole.substring(8,10);
            String month = currentDateWhole.substring(4,7);
            if (someday.substring(0,3).equals(month) && someday.substring(4,6).equals(day)) {
                String ipAddress = le.getIpAddress();
                ipArr.add(ipAddress);
            }
        }
        return ipArr;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
        for (LogEntry le : records) {
            int currentStat = le.getStatusCode();
            String ipAddress = le.getIpAddress();
            if (currentStat >= low && currentStat <= high) {
                if (!(uniqueIPsInRange.contains(ipAddress))) {
                    uniqueIPsInRange.add(ipAddress);
                }
            }
        }
        return uniqueIPsInRange.size();
    }
    
    
    // Part II
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int maxCount = 0;
        for (Integer count : counts.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        
        return maxCount;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> maxIPs = new ArrayList<String>();
        int maxCount =  mostNumberVisitsByIP(counts);
        
        for (String ip : counts.keySet()) {
            if (counts.get(ip).equals(maxCount)) {
                maxIPs.add(ip);
            }
        }
        
        return maxIPs;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        // String is a date
        // ArrayList is a list of unique IP addresses of the visitors on that day
        // Make the HashMap...
        HashMap<String, ArrayList<String>> IPsOnDays = new HashMap<String, ArrayList<String>>();
        
        //cycle thru records
        for (LogEntry le : records) {
            // get the date of the current LogEntry
            String currentDateWhole = le.getAccessTime().toString();
            // convert date into format MMM DD
            String day = currentDateWhole.substring(8,10);
            String month = currentDateWhole.substring(4,7);
            String newDate = month + " " + day;
            
            // if the current date is not already in the HashMap
            if ( !(IPsOnDays.containsKey(newDate)) ) {
                // find the IP adresses of visitors on that day
                ArrayList<String> visitorIPsOnDate = allIPVisitsOnDay(newDate);
                // use .put() to add the date and array list to the HashMap
                IPsOnDays.put(newDate, visitorIPsOnDate);
            }
            else {
                // do nothing? 
            }
        }
        
        return IPsOnDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        int maxIPCount = 0;
        String dayWithMaxCounts = null;
        
        for (String date : map.keySet()) {
            ArrayList<String> currentIPList = map.get(date);
            int currentCount = currentIPList.size();
            if (currentCount > maxIPCount) {
                maxIPCount = currentCount;
                dayWithMaxCounts = date;
            }
        }
        return dayWithMaxCounts;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String targetDate) {
        // NB use mostNumberVisitsByIP
        
        // initialize a HashMap to keep count of number of visits for each IP
        HashMap<String, Integer> ipVisitCounts  = new HashMap<String, Integer>();
        
        // create a list of all IPs on that date
        ArrayList<String> ipList = map.get(targetDate);
        
        // create an Array for the IPs with the most vists
        ArrayList<String> ipMostVisits = new ArrayList<String>();
        
        // find the number of visits for each IP; add to HashMap
        for (String ip : ipList) {
            if (! ipVisitCounts.containsKey(ip)) {
                ipVisitCounts.put(ip, 1);
            }
            else {
               ipVisitCounts.put(ip, ipVisitCounts.get(ip) + 1); 
            }
        }
        
        // find the max number of visits on the target date
        int mostVisits = mostNumberVisitsByIP(ipVisitCounts);
        
        // find the IP or IPs with the max number of vists on the target date
        for (String ip : ipVisitCounts.keySet()) {
            if (ipVisitCounts.get(ip) == mostVisits) {
                ipMostVisits.add(ip);
            }
        }
        
        return ipMostVisits;
    }
}

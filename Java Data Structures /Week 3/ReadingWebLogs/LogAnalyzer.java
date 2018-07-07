
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
        // initialize object - initialize records to an empty ArrayList
        records = new ArrayList<LogEntry>();
        // 
    }
    
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
        }
    }
    
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
                System.out.println(le);
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
}


/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log.txt");
        la.printAll();
        
    }
    
    public void testCountUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log.txt");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " unique IPs");
        
    }
    
    public void testPrintAllHigherThenNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log.txt");
        la.printAllHigherThenNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log.txt");
        ArrayList<String> arr1 = la.uniqueIPVisitsOnDay("Sep 27");
        
        System.out.println(arr1.size() + " unique IPs");
        System.out.println("Unique IPs: ");
        for (String IP : arr1) {
            System.out.println(IP);
        }
        /*
        ArrayList<String> arr2 = la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Unique IPs on Sep 30: ");
        for (String IP : arr2) {
            System.out.println(IP);
        }
        */
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log.txt");
        System.out.println(la.countUniqueIPsInRange(400,499));
        
    }
}

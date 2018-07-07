
/**
 * Write a description of CountTester here.
 * 
 * @author Daniel J Hardej 
 * @version v1.0
 */

import java.util.*;

public class CountTester {
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log.txt");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log.txt");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int maxCount = la.mostNumberVisitsByIP(counts);
        System.out.println(maxCount);
    }
    
    public void testIPsMostVisits () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log.txt");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> ips = la.iPsMostVisits(counts);
        System.out.println(ips);
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log.txt");
        
        HashMap<String, ArrayList<String>> IPs4days = la.iPsForDays();
        
        System.out.println(IPs4days);
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log.txt");
        
        HashMap<String, ArrayList<String>> IPs4days = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(IPs4days));
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log.txt");
        
        HashMap<String, ArrayList<String>> IPs4days = la.iPsForDays();
        ArrayList<String> ipsMostVisits = la.iPsWithMostVisitsOnDay(IPs4days, "Sep 29");
        
        for (String ip : ipsMostVisits) { 
            System.out.println(ip);
        }
    }
}

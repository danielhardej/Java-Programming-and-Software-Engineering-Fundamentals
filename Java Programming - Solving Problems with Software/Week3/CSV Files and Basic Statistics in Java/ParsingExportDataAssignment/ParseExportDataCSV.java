
/**
 * Parses data from a CSV file containg export data for a list of
 * countries, prints a list of countries that export a certain product
 * 
 * @author Daniel Hardej
 * @version 1.0 April 2019
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ParseExportDataCSV {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public String countryInfo(CSVParser parser, String country) {
        String exports = "";
        String exportsValue = "";
        for (CSVRecord record : parser) {
            String currentCountry = record.get("Country");
            if (currentCountry.toLowerCase().contains(country.toLowerCase())) {
                exports = record.get("Exports");
                exportsValue = record.get("Value (dollars)");
            }
            else { 
                // do nothing!
            }
        }
        if (exports != "" || exportsValue != "") {
            return country + ": " + exports + ": " + exportsValue;
        }
        else {
            return "NOT FOUND";
        }
    }
    public void listExportersTwoProducts (CSVParser parser, String exportitem1, String exportitem2) {
        for (CSVRecord record : parser) {
            String currentCountry = record.get("Country");
            String exports = record.get("Exports");
            if (exports.toLowerCase().contains(exportitem1.toLowerCase()) && exports.toLowerCase().contains(exportitem2.toLowerCase())) {
                System.out.println(currentCountry);
            }
        }
    }
    public int numberOfExporters (CSVParser parser, String exportItem) {
        int numExporters = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.toLowerCase().contains(exportItem.toLowerCase())){
                numExporters++;
            }
        }
        return numExporters;
    }
    public void bigExporters (CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String exportsValue = record.get("Value (dollars)");
            String country = record.get("Country");
            if (exportsValue.length() > amount.length()) {
                System.out.println(country + ": " + exportsValue);
            }
        }
    }
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.println("Testing countryInfo:");
        parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Germany"));
        parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "bitch land"));
        
        System.out.println("Testing listExportersTwoProducts:");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "petroleum", "fish");
        
        System.out.println("Testing numberOfExporters:" );
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "coffee"));
        
        System.out.println("Testing bigExporters: ");
        parser = fr.getCSVParser();
        bigExporters(parser, "$400,000,000");
    }
    public void findQuizAnswers() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        //System.out.println(countryInfo(parser, "Nauru"));
        bigExporters(parser, "$999,999,999,999");
    }
    public void coffeeExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
}

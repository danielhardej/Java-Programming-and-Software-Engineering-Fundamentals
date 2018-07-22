
/**
 * Write a description of WeatherCSVAssignment here.
 * 
 * @author Daniel Hardej
 * @version 1.0 April 2018
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class WeatherCSVAssignment {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser) {
            //coldestSoFar = getSmallestOfTwo(currentRow, largestSoFar);
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            else {
               double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
               double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF")); 
               if (currentTemp < coldestTemp && currentTemp != -9999) {
                   coldestSoFar = currentRow;
                }
            }
        }
        return coldestSoFar;
    }
    public File fileWithColdestTemp() {
        CSVRecord coldestSoFar = null;
        File coldestFile = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            //System.out.println("Coldest temperature on: " + currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
                coldestFile = f;
            }
            else {
               double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
               double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF")); 
               if (currentTemp < coldestTemp) {
                   coldestSoFar = currentRow;
                   coldestFile = f;
                }
            }
        }
        return coldestFile;
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            else {
                try {
                   double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                   double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity")); 
                   if (currentHumidity < lowestHumidity) {
                       lowestSoFar = currentRow;
                   }
                }
                catch(Exception e) {
                }
            }
        }
        return lowestSoFar;
    }
    public CSVRecord lowestHumidityInMultipleFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            else {
               double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
               double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity")); 
               if (currentHumidity < lowestHumidity) {
                   lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord coldestSoFar) {
        if (coldestSoFar == null) {
            coldestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if (currentTemp < coldestTemp) {
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }
    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0;
        int numTemps = 0;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            totalTemp = totalTemp + currentTemp;
            numTemps++;
        }
        double averageTemp = totalTemp / numTemps;
        
        return averageTemp;
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemp = 0;
        int numTemps = 0;
        for (CSVRecord currentRow : parser) {
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            if (currentHumidity >= value) {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                totalTemp = totalTemp + currentTemp;
                numTemps++;
            }
            else {
                // do nothing!
            }
        }
        double avgTemp = totalTemp / numTemps;
        return avgTemp;
    }
    public void testColdestHourInFile () {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEDT"));
    }
    public void testColdestFile() {
        File coldestFile = fileWithColdestTemp();
        FileResource fr = new FileResource(coldestFile);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file: " + coldestFile.getName());
        System.out.println("The coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
        System.out.println("All temperatures on this day were: ");
        for (CSVRecord currentRow : fr.getCSVParser()) {
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
        }
    }
    public void testLowestHumidity () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidityCSV = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + lowestHumidityCSV.get("Humidity") + " at " + lowestHumidityCSV.get("DateUTC"));
    }
    public void testLowestHumidityInMultipleFiles() {
        CSVRecord lowestHumidityCSV = lowestHumidityInMultipleFiles();
        System.out.println("Lowest humidity was " + lowestHumidityCSV.get("Humidity") + " at " + lowestHumidityCSV.get("DateUTC"));
    }
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temp in file: " + avgTemp);
    }
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgT = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avgT > 0) {
            System.out.println("Average Temp when high Humidity is " + avgT);
        }
        else {
            System.out.println("No temperatures with that humidity");
        }
    }
}


/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
            System.out.println("Name: " + rec.get(0) + 
                                " Gender: " + rec.get(1) + 
                                " Num born: " + rec.get(2));
            }
        }
    }
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int totalGirlsNames = 0;
        int totalBoysNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames++;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoysNames++;
            }
            if (rec.get(1).equals("F")) {
                totalGirls += numBorn;
                totalGirlsNames++;
            }
        }
        System.out.println("Total births: " + totalBirths);
        System.out.println("Total boys: " + totalBoys);
        System.out.println("Total girls: " + totalGirls);
        System.out.println("Total names: " + totalNames);
        System.out.println("Total boys names: " + totalBoysNames);
        System.out.println("Total girls names: " + totalGirlsNames);
        System.out.println("---------------------------");
    }
    
    public int getRank (int year, String name, String gender) {
        int rank = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank++;
                if (rec.get(0).equalsIgnoreCase(name)) {
                    break;
                }
            }
            else {
                // 
            }
        }
        return rank;
    }
    
    public String getName(int year, int rank, String gender) {
        int rankCountSoFar = 0;
        String name = "";
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rankCountSoFar++;
                if (rankCountSoFar == rank) {
                    name = rec.get(0);
                }
            }
            else { 
                //name = "NO NAME";
            }
        }
        return name;
    }
    
    public String whatIsNameInYear(int year, String name, int newYear, String gender) {
        int ranking = getRank(year, name, gender);
        String nameInYear = getName(newYear, ranking, gender);
        return nameInYear;
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int yearOfHighestRank = 0;
        int highestRanking = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int currentYear = Integer.parseInt(fileName.substring(3,7));
            int currentRanking = getRank(currentYear, name, gender);
            if (highestRanking == 0) {
                highestRanking = currentRanking;
                yearOfHighestRank = currentYear;
                System.out.println(highestRanking + " " + currentYear);
            }
            if ((currentRanking < highestRanking) && (currentRanking > 0)) {
                highestRanking = currentRanking;
                yearOfHighestRank = currentYear;
                System.out.println(highestRanking + " " + currentYear);
            }
            else { 
                // nothing
            }
        }  
        return yearOfHighestRank;
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double rankSum = 0;
        int rankingCount = 0;
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int currentYear = Integer.parseInt(fileName.substring(3,7));
            int currentRanking = getRank(currentYear, name, gender);
            if (currentRanking > 0) {
                rankingCount++;
                rankSum += currentRanking;
            }
        }
        double averageRanking = rankSum / rankingCount;
        return averageRanking;
    }
    
    public int totalBirthsRankedHigher (int year, String name, String gender) {
        int totalBirthsRankedHigher = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                String currentName = rec.get(0);
                int currentRank = getRank(year, currentName, gender);
                if (currentRank < getRank(year, name, gender)) {
                    int numBorn = Integer.parseInt(rec.get(2));
                    System.out.println(numBorn);
                    totalBirthsRankedHigher += numBorn;
                }
                else { break; }
            }
        }
        return totalBirthsRankedHigher;
    }
    
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testGetRank () {
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        int rank = getRank(year, name, gender);
        if (rank > 0) {
            System.out.println("Rank of " + name + " in " + year + ": " + rank);
        }
        else {
            System.out.println("The name " + name + " does not appear in ranking.");
        }
    }
    
    public void testGetName () {
        int year = 1982;
        int rank = 450;
        String gender = "M";
        String name = getName(year, rank, gender);
        System.out.println("Name with ranking of " + rank + ": " + name);
    }
    
    public void testNameInYear() {
        int year = 1974;
        int newYear = 2014;
        String gender = "M";
        String name = "Owen";
        String newName = whatIsNameInYear(year, name, newYear, gender);
        System.out.println("A child named " + name + " born in the year " + 
                        year + " would be called " + newName + 
                        " if they were born in " + newYear);
    }
    
    public void testYearOfHighestRank() {
        String name = "Mich";
        String gender = "M";
        int highestRankYr = yearOfHighestRank(name, gender);
        System.out.println("Highest ranking year for " + name + ": " + highestRankYr);
    }
    
    public void testAverageRank () {
        String name = "Robert";
        String gender = "M";
        double avgRank = getAverageRank(name, gender);
        System.out.println("Average ranking for the name " + name + 
                        " over the years selected: " + avgRank);
    }
    
    public void testTotalBirthsRankedHigher() {
        String name = "Drew";
        String gender = "M";
        int year = 1990;
        int tot = totalBirthsRankedHigher(year, name, gender);
        System.out.println("Total births ranked higher than " + name + 
                            " in " + year + ": " + tot);
    }
}


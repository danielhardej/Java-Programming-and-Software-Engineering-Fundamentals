import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numPoints = 0;
        for (Point point : s.getPoints()) {
            numPoints = numPoints + 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double totalPerimeter = getPerimeter(s);
        double numSides = getNumPoints(s);
        double avgLength = totalPerimeter / numSides;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0;
        double currDist = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            currDist = prevPt.distance(currPt);
            if (currDist > largestSide) {
                largestSide = currDist;
            }
            else {
                // Do nothing!;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0;
        for (Point point : s.getPoints()) {
            if (point.getX() > largestX) {
                largestX = point.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = 0;
        double currentPerimeter = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            currentPerimeter = getPerimeter(s);
            if (currentPerimeter > largestPerimeter) {
                largestPerimeter = currentPerimeter;
            }
            else {
                // Do nothing!
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0;
        String largestFilename = "";
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape tempShape = new Shape(fr);
            if (getPerimeter(tempShape) >= largestPerimeter){
                largestPerimeter = getPerimeter(tempShape);
                largestFilename = f.getName();
            }
        }
        return largestFilename;
        //File temp = ;    // replace this code
        //return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter: " + length);
        System.out.println("Num points: " + getNumPoints(s));
        System.out.println("Average side len: " + getAverageLength(s));
        System.out.println("Largest side len: " + getLargestSide(s));
        System.out.println("Largest X: " + getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("Largest perimeter: " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("File with largest perimeter: " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testFileWithLargestPerimeter();
    }
}

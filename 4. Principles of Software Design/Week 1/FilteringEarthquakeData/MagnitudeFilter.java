
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author Daniel Hardej
 * @version v1.0
 */


public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    
    public MagnitudeFilter (double minMag, double maxMag) {
        this.minMag = minMag;
        this.maxMag = maxMag;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        boolean ans = (qe.getMagnitude() >= minMag) && (qe.getMagnitude() <= maxMag);
        return ans; 
    }
    
    public String getName() {
        return "Magnitude";
    }
}

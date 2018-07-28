
/**
 * Write a description of DistanceFilter here.
 * 
 * @author Daniel Hardej 
 * @version v1.0
 */
public class DistanceFilter implements Filter{
    
    private Location loc;
    private double maxDistance;
    
    public DistanceFilter (Location loc, double maxDistance) {
        this.loc = loc;
        this.maxDistance = maxDistance;
    }
    
    public boolean satisfies(QuakeEntry qe) {
         double distance = loc.distanceTo(qe.getLocation());
         return (distance < maxDistance);
    }
    
    public String getName() {
        return "Distance";
    }
}

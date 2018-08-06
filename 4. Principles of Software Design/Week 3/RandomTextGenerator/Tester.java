
/**
 * Write a description of Tester here.
 * 
 * @author Daniel J Hardej
 * @version 1.0
 */

import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne mkv = new MarkovOne();
        mkv.setTraining("this is a test yes this is a test.");
        System.out.println(mkv.getFollows("i"));
        System.out.println(mkv.getFollows("."));
        System.out.println(mkv.getFollows("t."));
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne mkv = new MarkovOne();
        FileResource fr = new FileResource();
        String str = fr.asString();
        str = str.replace('\n', ' ');
        mkv.setTraining(str);
        mkv.setRandom(42);
        ArrayList<String> ar = mkv.getFollows("th");
        System.out.println("Size of array: " + ar.size());
        for (int k=0; k<3; k++) {
            String text = mkv.getRandomText(100);
            System.out.println(text);
        }
    }
}

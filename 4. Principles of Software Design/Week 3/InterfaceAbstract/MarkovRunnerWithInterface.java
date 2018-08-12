
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.1
 */

import edu.duke.*;
import java.lang.Object.*;
import java.lang.System.*;


public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("Running with " + markov);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 42;
        
        
        MarkovZero mZero = new MarkovZero();
        runModel(mZero, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }
    
    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        
        int size = 50;
        int seed = 615;
        
        EfficientMarkovModel effMarkovTwo = new EfficientMarkovModel(5);
        runModel(effMarkovTwo, st, size, seed);
        effMarkovTwo.printHashMapInfo();
        
    }
    
    public void compareMethods() {
        
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        int size = 1000;
        int seed = 42;
        int order  = 2;
        
        long start1 = System.nanoTime();
        EfficientMarkovModel effMarkovTwo = new EfficientMarkovModel(order);
        runModel(effMarkovTwo, st, size, seed);
        long end1 = System.nanoTime();
        double time1 = (end1 - start1)/1e9;
        //effMarkovTwo.printHashMapInfo();
        
        long start2 = System.nanoTime();
        MarkovModel markovTwo = new MarkovModel(order);
        runModel(markovTwo, st, size, seed);
        long end2 = System.nanoTime();
        double time2 = (end2 - start2)/1e9;
        
        System.out.println("---------------------------------------");
        System.out.println("Time for normal Markov [s]:    " + time2);
        System.out.println("Time for efficient Markov [s]: " + time1);
        System.out.println("---------------------------------------");
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}

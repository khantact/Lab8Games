//A abstraction of a "Bucket" utilized by a LearningAIPlayer
//in the Matchsticks game
//Each bucket stores some number of Marbles which correspond to a number

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.RuntimeException;

public class Bucket {

    // The number associated with the bucket
    // (i.e. this is bucket #7)
    private int bucketNum;
    private Random rand = new Random();
    // stores all the marbles in the bucket;
    private ArrayList<Integer> marbles; // Uncomment for part 3.3.1!!

    public Bucket(int bucketNum) {
        this.bucketNum = bucketNum;
        // initialize the starting marbles in the bucket here!
        this.marbles = new ArrayList<Integer>();
    }

    // Adds a marble of the argument number
    public void addMarble(int marbleNum) {
        marbles.add(marbleNum);
        // Finish me for Part 3.3!
    }

    // removes a random marble from the bucket. Returns the
    // number associated with the removed marble.
    // Throws an IllegalArgumentException if the bucket is empty
    public int takeRandomMarble() {
        int remove = marbles.remove(rand.nextInt(marbles.size()));
        // if (marbles.size() == 0) {
        // throw new IllegalArgumentException("Bucket is Empty");
        // }
        return remove;
    }

    // returns the number of marbles in the bucket
    public int getMarbleCount() {
        return marbles.size();
    }

    // Should report the bucket number and what marbles are inside
    // Will be utilized for debug text!
    // Ex: "Bucket #7: 1, 2, 2, 3"
    public String toString() {
        return "Bucket #" + bucketNum + ": " + marbles; // placeholder
    }

}
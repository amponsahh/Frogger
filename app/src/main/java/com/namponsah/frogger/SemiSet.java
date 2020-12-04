package com.namponsah.frogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * SemiSet Class:
 *  Stores:
 *      A list of Semi objects
 *  Sets up a Semi object's XY coordinate points
 *  Handle:
 *      the call to each Semi object's advance call
 *      object collision of each Semi object
 */
public class SemiSet {
    /**
     * Holds Semis objects
     */
    public List<Semi> Semis;

    /**
     * Randomizer for the Semi object's X coordinate
     */
    private static Random random = new Random();

    /**
     * Constructor:
     *  Initializes Semis with a list of designated Car objects
     *  Handles the randomization of each Semi object's X coordinate
     *  @param numSemis   designated number of Semis in the list of SemiSet
     *  @param y         specified static y coordinate
     */
    public SemiSet(int numSemis, int y){
        Semis = new ArrayList<>();
        while (Semis.size() != numSemis){
            Semis.add(new Semi());
            randomizeXBounds(y);
        }
    }

    /**
     * @return size of the Semis list
     * Used in GameScreen.java for looping
     */
    public int size() {
        return Semis.size();
    }

    /**
     * Calls each Semis object's advance function
     */
    public void advance(){
        for(Semi semis : Semis){
            semis.advance();
        }
    }

    /**
     * Handles the Randomization of each Semi object's X coordinate
     * @param y specified static y coordinate
     */
    public void randomizeXBounds(int y){
        for(Semi semis : Semis){
            semis.POS_Y = y;

            do{
                semis.POS_X = random.nextInt(320);
            }while (semis.POS_X % 40 == 0);
            // ^ ensures the x coordinate is divisible by 40 [height]
        }

        // if we get a point that is on top of each other separate them
        objectCollision();
    }

    /**
     * Handles the Semi object collision so Semi don't spawn on top of each other
     * or aren't right close to each other.
     */
    public void objectCollision(){
        // again, regualr for loop here due to circumstances
        for(int i = 0; i < Semis.size() - 1; i += 1){
            Semi semiA = Semis.get(i);
            Semi semiB = Semis.get(i + 1);

            // [https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection]
            if (semiA.POS_X < semiB.POS_X + 240 &&
                    semiA.POS_X + 240 > semiB.POS_X) {
                // collision detected!
                // separate the Semi object by the width of two semis, spice things up
                semiA.POS_X += 240;
            }
        }
    }

    /**
     * Handles the collision of fr3d and a semi object
     * @param x fr3d's x coordinate
     * @param y fr3d's y coordinate
     * @return  true/false if a collision between fr3d and a Semi object was detected or not.
     */
    public boolean collision(int x, int y){
        for(Semi semis : Semis){
            if(semis.POS_X < x + 40 &&
                    semis.POS_X + 120 > x &&
                    semis.POS_Y < y + 40 &&
                    semis.POS_Y + 40 > y){
                return true;
            }
        }
        return false;
    }
}

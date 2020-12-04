package com.namponsah.frogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * LimoSet Class:
 *  Stores:
 *      A list of Limo objects
 *  Sets up a Limo object's XY coordinate points
 *  Handle:
 *      the call to each Limo object's advance call
 *      object collision of each Limo object
 */
public class LimoSet {
    /**
     * Randomizer for the Limo object's X coordinate
     */
    private static Random random = new Random();

    /**
     * Holds Limo objects
     */
    public List<Limo> Limos;

    /**
     * Constructor:
     *  Initializes Limos with a list of designated Limos objects
     *  Handles the randomization of each Limo object's X coordinate
     *  @param numLimos   designated number of Limos in the list of LimoSet
     *  @param y         specified static y coordinate
     */
    public LimoSet(int numLimos, int y){
        Limos = new ArrayList<>();
        while (Limos.size() != numLimos){
            Limos.add(new Limo());
            randomizeXBounds(y);
        }
    }

    /**
     * Calls each Limo object's advance function
     */
    public void advance(){
        for(Limo limos : Limos){
            limos.advance();
        }
    }

    /**
     * @return size of the Limo list
     * Used in GameScreen.java for looping
     */
    public int size() {
        return Limos.size();
    }

    /**
     * Handles the Randomization of each Limo object's X coordinate
     * @param y specified static y coordinate
     */
    public void randomizeXBounds(int y){
        for (Limo limos : Limos){
            limos.POS_Y = y;

            do{
                limos.POS_X = random.nextInt(320);
            }while (limos.POS_X % 40 == 0);
            //^ ensures the x coordinate is divisible by 40 [height]
        }

        // if we get a point that is on top of each other separate them
        objectCollision();
    }

    /**
     * Handles the Limo object collision so limos don't spawn on top of each other
     * or aren't right close to each other.
     */
    public void objectCollision(){
        // since we need to touch more than one limo object, regular for loop
        for(int i = 0; i < Limos.size() - 1; i += 1){
            Limo limoA = Limos.get(i);
            Limo limoB = Limos.get(i + 1);

            // [https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection]
            if (limoA.POS_X < limoB.POS_X + 160 &&
                    limoA.POS_X + 160 > limoB.POS_X) {
                // collision detected!
                // separate the limo object by the width of fr3d
                limoA.POS_X -= 40; // should have been limoB instead I realize now
            }
        }
    }

    /**
     * Handles the collision of fr3d and a Limo object
     * @param x fr3d's x coordinate
     * @param y fr3d's y coordinate
     * @return  true/false if a collision between fr3d and a Limo object was detected or not.
     */
    public boolean collision(int x, int y){
        for(Limo limos : Limos){
            // [https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection]
            if(limos.POS_X < x + 40 &&
                    limos.POS_X + 80 > x &&
                    limos.POS_Y < y + 40 &&
                    limos.POS_Y + 40 > y){
                return true;
            }
        }
        return false;
    }
}

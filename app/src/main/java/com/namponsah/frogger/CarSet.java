package com.namponsah.frogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * CarSet Class:
 *  Stores:
 *      A list of Car objects
 *  Sets up a Car object's XY coordinate points
 *  Handle:
 *      the call to each Car object's advance call
 *      object collision of each Car object
 */
public class CarSet {
    /**
     * Randomizer for the Car object's X coordinate
     */
    private static Random random = new Random();

    /**
     * Holds Car objects
     */
    public List<Car> Cars;

    /**
     * Constructor:
     *  Initializes Cars with a list of designated Car objects
     *  Handles the randomization of each Car object's X coordinate
     *  @param numCars   designated number of cars in the list of CarSet
     *  @param y         specified static y coordinate
     */
    public CarSet(int numCars, int y){
        Cars = new ArrayList<>();
        while (Cars.size() != numCars){
            Cars.add(new Car());
            randomizeXBounds(y);
        }
    }

    /**
     * Calls each Car object's advance function
     */
    public void advance(){
        for(Car cars : Cars){
            cars.advance();
        }
    }

    /**
     * @return size of the Car list
     * Used in GameScreen.java for looping
     */
    public int size() {
        return Cars.size();
    }

    /**
     * Handles the Randomization of each Car object's X coordinate
     * @param y specified static y coordinate
     */
    public void randomizeXBounds(int y){
        for(Car cars : Cars){
            cars.POS_Y = y;

            do {
                cars.POS_X = random.nextInt(320);
            }while (cars.POS_X % 40 == 0); // ensures the x coordinate is divisible by 40 [height]
        }

        // if we get a point that is on top of each other separate them
        objectCollision();
    }

    /**
     * Handles the Car object collision so cars don't spawn on top of each other
     * or aren't right close to each other.
     */
    public void objectCollision(){
        // since we need to get two Car objects, normal for loop
        for(int i = 0; i < Cars.size() - 1; i += 1){
            Car carA = Cars.get(i);
            Car carB = Cars.get(i + 1);

            // [https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection]
            if (carA.POS_X < carB.POS_X + 80 &&
                    carA.POS_X + 80 > carB.POS_X) {
                // collision detected!
                // separate the car object by the width of fr3d
                carA.POS_X += 40;  // should have been carB instead I realize now
            }
        }
    }

    /**
     * Handles the collision of fr3d and a car object
     * @param x fr3d's x coordinate
     * @param y fr3d's y coordinate
     * @return  true/false if a collision between fr3d and a Car object was detected or not.
     */
    public boolean collision(int x, int y){
        for(Car cars : Cars){
            // [https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection]
            if(cars.POS_X < x + 40 &&
                    cars.POS_X + 40 > x &&
                    cars.POS_Y < y + 40 &&
                    cars.POS_Y + 40 > y){
                return true;
            }
        }
        return false;
    }
}

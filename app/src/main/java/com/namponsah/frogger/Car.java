package com.namponsah.frogger;

import java.util.Random;

/**
 * Car class:
 *  Stores:
 *      the X and Y Coordinates of a given car
 *      the type of car asset Pixmap carPixmap in GameScreen.java
 *      will need to draw one of two types of cars
 *  Handles the advancement of a given car from left to right (LR)
 */
public class Car {
    /**
     * XY Coordinate Points and type of asset to draw
     */
    public int POS_X;
    public int POS_Y;
    public int type;

    /**
     * Increment value for the car to advance
     * Techniques for increasing efficiency and reducing power usage:
     *  Option two: Prefer static over virtual
     *  Invocations will be about 15%-20% faster [https://bit.ly/2REOEUJ]
     */
    private static final int increment = 20;

    /**
     * Randomizer for one of two types of assets to draw in GameScreen.java
     */
    private static Random random = new Random();

    /**
     * Constructor:
     *  Initializes the type of car to draw
     */
    public Car(){
        this.type = Math.abs(random.nextInt(2));
    }

    /**
     * Advance function
     *  Handles the advancement of the car from left to right (x axis) in
     *  20 incremental pixels per tickTime and wraps around after designated coordinates
     *  if the car advances off the playable area [320] + 10, reinitialize the car's
     *  x coordinates vice versa
     */
    public void advance(){
        // LR
        this.POS_X += increment;

        if(this.POS_X > 330){
            this.POS_X = -30;
        }

        if(this.POS_X < -30){
            this.POS_X = 330;
        }
    }
}


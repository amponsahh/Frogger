package com.namponsah.frogger;

/**
 * Limo class:
 *  Stores:
 *      the X and Y Coordinates of a given Limo
 *  Handles the advancement of a given Limo from right to left (RL)
 */
public class Limo {
    /**
     * XY Coordinate Points
     */
    public int POS_X;
    public int POS_Y;

    /**
     * Increment value for the Limo to advance
     * Techniques for increasing efficiency and reducing power usage:
     *  Option two: Prefer static over virtual
     *  Invocations will be about 15%-20% faster [https://bit.ly/2REOEUJ]
     */
    private static final int increment = 40;

    // We don't really need a custom constructor so I didn't make one
    // especially to fulfil static final for increment, java's default is just fine
    //public Limo(){
    //    this.increment = 40;
    //}

    /**
     * Advance function
     *  Handles the advancement of the Limo right to left (x axis) in
     *  40 incremental pixels per tickTime and wraps around after designated coordinates
     *  if the Limo advances off the playable area [320] + 80, reinitialize the Limo's
     *  x coordinates and vice versa
     */
    public void advance(){
        // RL
        this.POS_X -= increment;

        if(this.POS_X < -40){
            this.POS_X = 400;
        }

        if(this.POS_X > 400){
            this.POS_X = -40;
        }
    }
}
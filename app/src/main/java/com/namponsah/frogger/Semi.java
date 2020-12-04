package com.namponsah.frogger;

/**
 * Semi class:
 *  Stores:
 *      the X and Y Coordinates of a given Semi
 *  Handles the advancement of a given Semi from left to right (LR)
 */
public class Semi {
    /**
     * XY Coordinate Points
     */
    public int POS_X;
    public int POS_Y;

    /**
     * Increment value for the Semi object to advance
     * Techniques for increasing efficiency and reducing power usage:
     *  Option two: Prefer static over virtual
     *  Invocations will be about 15%-20% faster [https://bit.ly/2REOEUJ]
     */
    private static final int increment = 60;

    // We don't really need a custom constructor so I didn't make one
    // especially to fulfil static final for increment, java's default is just fine
    //public Semi(){
    //   this.increment = 120; // change to 120 if problem exits
    //    this.increment = 60;
    //}

    /**
     * Advance function
     *  Handles the advancement of the Semi object from left to right (x axis) in
     *  60 incremental pixels per tickTime and wraps around after designated coordinates
     *  if the Semi advances off the playable area [320] + 60, reinitialize the Semi's
     *  x coordinates vice versa
     */
    public void advance(){
        // LR
        this.POS_X += increment;

        if(this.POS_X > 380){
            this.POS_X = -120;
        }

        if(this.POS_X < -120){
            this.POS_X = 380;
        }
    }
}

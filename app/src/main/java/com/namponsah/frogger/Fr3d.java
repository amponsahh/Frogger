package com.namponsah.frogger;

import java.util.Vector;

/**
 * Fr3d class:
 *  Stores:
 *      the X and Y Coordinates of fr3d and his lives
 *      the X and Y Coordinates of each lily pad
 *  Handles the advancement of a Fr3d object
 */
public class Fr3d {
    /**
     * Fr3d object's X coordinate
     */
    public int POS_X;

    /**
     * Fr3d object's Y coordinate
     */
    public int POS_Y;

    /**
     * Fr3d object's live count
     */
    public int lives;

    /**
     * coordinate points for lily pad
     */
    public int[][] lilypad; // doesn't need to be a standalone class

    /**
     * Vector that stores the order in which a lily pad is step on
     * for drawing in GameScreen.java
     */
    public Vector<Integer> lilypadRecord; // we'll need to remember fr3d's lily lands

    /**
     * Constructor:
     *  Sets up Fr3d's and lily pad's XY coordinates, live count and vector that stores
     *  landing order of a collision between Fr3d and lily pad
     */
    public Fr3d(){
        this.POS_X = 160;
        this.POS_Y = 440;
        this.lives = 4;

        this.lilypad = new int[4][2];

        // there's no need for a foreach when the values won't change
        this.lilypad[0][0] = 0;
        this.lilypad[0][1] = 40;
        this.lilypad[1][0] = 80;
        this.lilypad[1][1] = 40;
        this.lilypad[2][0] = 160;
        this.lilypad[2][1] = 40;
        this.lilypad[3][0] = 240;
        this.lilypad[3][1] = 40;

        // stores in the order in which fr3d lands on a lily pad
        // for GameScreen.java to draw
        this.lilypadRecord = new Vector<>();
    }

    /***
     * Resets Fr3d object's XY coordinates back to starting position
     */
    public void reposition(){
        this.POS_X = 160;
        this.POS_Y = 440;
    }

    /**
     * Essentially a collision function that checks
     * if a Fr3d object landed on a lily pad
     * @param x Fr3d object's X coordinate
     * @param y Fr3d object's Y coordinate
     * @return  true/false based whether or not a Fr3d object hops onto either lilly pad
     */
    public boolean lilypadLand(int x, int y){
        // check if every lilypad is near a frog
        if(this.lilypad[0][0] < x + 40 &&
                this.lilypad[0][0] + 40 > x &&
                this.lilypad[0][1] < y + 40 &&
                this.lilypad[0][1] + 40 > y){
            this.lilypadRecord.add(0);
            return true;
        }

        if(this.lilypad[1][0] < x + 40 &&
                this.lilypad[1][0] + 40 > x &&
                this.lilypad[1][1] < y + 40 &&
                this.lilypad[1][1] + 40 > y){
            this.lilypadRecord.add(1);
            return true;
        }

        if(this.lilypad[2][0] < x + 40 &&
                this.lilypad[2][0] + 40 > x &&
                this.lilypad[2][1] < y + 40 &&
                this.lilypad[2][1] + 40 > y){
            this.lilypadRecord.add(2);
            return true;
        }

        if(this.lilypad[3][0] < x + 40 &&
                this.lilypad[3][0] + 40 > x &&
                this.lilypad[3][1] < y + 40 &&
                this.lilypad[3][1] + 40 > y){
            this.lilypadRecord.add(3);
            return true;
        }
        return false;
    }

    /**
     * Handles the advancement of a Fr3d object
     * switch case to handle varying direction instructions
     * also handles the case wherein s Fr3d object cannot leave the playable
     * area or out of view
     * @param direction 1 = UP / 2 = DOWN / 3 = LEFT / 4 = RIGHT
     */
    public void advance(int direction){
        switch (direction){
            case 1: // up
                POS_Y -= 40;
                if(POS_Y <= 40){
                    POS_Y = 40;
                }
                break;

            case 2: // down
                POS_Y += 40;
                if(POS_Y >= 400){
                    POS_Y = 400;
                }
                break;

            case 3: // left
                POS_X -= 40;
                if(POS_X <= 0){
                    POS_X = 0;
                }
                break;

            case 4: // right
                POS_X += 40;
                if(POS_X >= 280){
                    POS_X = 280;
                }
                break;
        }
    }
}


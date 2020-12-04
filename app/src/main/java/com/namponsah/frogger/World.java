package com.namponsah.frogger;

import java.util.LinkedHashSet;
import java.util.Set;

/**
        * principle of good game design:
        *  I'm going for lenses:
        *       Lens #32: The Lens of Meaningful Choices
        *       Lens #33: The Lens of Triangularity
        *  The player can either choose to play it safe and finish the game with
        *  guaranteed low points [Low risk/ low reward] or shoot for the stars and
        *  keep playing for a higher score but risk loosing 20 points per life [High risk/ high reward]
        *  they could also just start out by loosing 3 lives and then playing. It's of a chance thing and
        *  more so a skill hence choices are very meaningful I believe, at least I hope.
        */
public class World {
    // arena parameters
    /**
     * Techniques for increasing efficiency and reducing power usage:
     *  Option two: Prefer static over virtual
     *  Invocations will be about 15%-20% faster [https://bit.ly/2REOEUJ]
     */
    private static final int SCORE_INCREMENT = 20;
    private static final float TICK_INITIAL = 0.2f;
    public boolean gameOver = false;
    public boolean gameWon = false;
    public int score = 0;
    float tickTime = 0;
    float tick = TICK_INITIAL;
    static int yincrement;
    static int ycord;

    // setup requisite objects
    public Fr3d fr3d;
    public CarSet[] carSets;
    public LimoSet[] limoSets;
    public SemiSet[] semiSets;

    // Set that counts if there no more that 4 unique lily pad lands
    // to win the game
    // [https://stackoverflow.com/a/8698791]
    Set<Integer> unique = new LinkedHashSet<>(); // can't have duplicates


    /**
     * Constructor:
     *  Sets up the number of vehicle per lane [stored in an array] as well
     *  as the coordinate points for each vehicle
     */
    public World(){
        fr3d = new Fr3d();

        carSets = new CarSet[2]; // row of cars
        limoSets = new  LimoSet[2]; // row of limos
        semiSets = new  SemiSet[1]; // row of semi

        // cars
        yincrement = 80;
        ycord = 320;
        for(int i = 0; i < carSets.length; i += 1){
            if(i == 0){
                carSets[i] = new CarSet(3, ycord);
            }else {
                ycord += yincrement;
                carSets[i] = new CarSet(3, ycord);
            }
        }

        // limos
        ycord = 160;
        for (int i = 0; i < limoSets.length; i += 1){
            if(i == 0){
                limoSets[i] = new LimoSet(2, ycord);
            }else {
                ycord += yincrement;
                limoSets[i] = new LimoSet(2, ycord);
            }
        }

        // semi
        ycord = 80;
        for (int i = 0; i < semiSets.length; i += 1){
            if(i == 0){
                semiSets[i] = new SemiSet(2, ycord);
            }else {
                ycord += yincrement;
                semiSets[i] = new SemiSet(2, ycord);
            }
        }

    }

    /**
     * Handles advancement and collision functions between fr3d and vehicle objects
     * Also checks for a win
     * @param deltaTime  difference in time
     */
    public void update(float deltaTime) {
        tickTime += deltaTime;
        if (gameOver) {
            return;
        }

        while (tickTime > tick) {
            tickTime -= tick;
            // advances
            for(CarSet carsets: carSets){  //public CarSet[] carSets;
                carsets.advance();
                carsets.objectCollision();
            }
            for(LimoSet limosets: limoSets){
                limosets.advance();
                limosets.objectCollision();
            }
            for(SemiSet semiSet: semiSets){
                semiSet.advance();
                semiSet.objectCollision();
            }
        }
        collisions();
        checkWon();
    }

    /**
     * Handles all collisions in the world
     */
    public void collisions(){
        // lilypad "Collision"
        // if fr3d touches lilly pad, reposition him and increment score
        if(fr3d.lilypadLand(fr3d.POS_X, fr3d.POS_Y)){
            fr3d.reposition();
            score += SCORE_INCREMENT;
        }

        // if fr3d hits a vehicle, decrement live count and score until 0
        // and then game over
        for(int i = 0; i < carSets.length; i += 1){
            if(carSets[i].collision(fr3d.POS_X, fr3d.POS_Y)){
                fr3d.lives -= 1;
                score -= SCORE_INCREMENT;
                if(fr3d.lives <= 0){
                    fr3d.lives = 0;
                    gameOver = true;
                }
                if(score <= 0){
                    score = 0;
                }
            }
        }

        // if fr3d hits a vehicle, decrement live count and score until 0
        // and then game over
        for(int i = 0; i < semiSets.length; i += 1){
            if(semiSets[i].collision(fr3d.POS_X, fr3d.POS_Y)){
                fr3d.lives -= 1;
                score -= SCORE_INCREMENT;
                if(fr3d.lives <= 0){
                    fr3d.lives = 0;
                    gameOver = true;
                }
                if(score <= 0){
                    score = 0;
                }
            }
        }

        // if fr3d hits a vehicle, decrement live count and score until 0
        // and then game over
        for(int i = 0; i < limoSets.length; i += 1){
            if(limoSets[i].collision(fr3d.POS_X, fr3d.POS_Y)){
                fr3d.lives -= 1;
                score -= SCORE_INCREMENT;
                if(fr3d.lives <= 0){
                    fr3d.lives = 0;
                    gameOver = true;
                }
                if(score <= 0){
                    score = 0;
                }
            }
        }

    }

    /**
     * if there are for unique ids of lily pads landed on, gamewon.
     * the set type of unique allows this happen automajically.
     */
    public void checkWon(){
        unique.clear();
        unique.addAll(fr3d.lilypadRecord);
        gameWon = (unique.size() == 4);
    }
}


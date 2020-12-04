package com.namponsah.frogger;

import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Sound;

/**
 * Assets class file
 *  Contains public static
 */
public class Assets {
    // Landing page
    public static Pixmap background;
    public static Pixmap logo;
    public static Pixmap mainmenu;
    public static Pixmap MiscSXP; // houses (un)mute, X and Pause button graphics

    // Game Screen
    public static Pixmap gamebackground;
    public static Pixmap up;
    public static Pixmap down;
    public static Pixmap left;
    public static Pixmap right;
    public static Pixmap numbers;

    // Game Screen: Vehicles
    public static Pixmap carARight;
    public static Pixmap carBRight;
    public static Pixmap limoLeft;
    public static Pixmap semiRight;

    // Game Screen: Immovable assets, Lily pad and lives
    public static Pixmap lilypad;
    public static Pixmap lives;

    // Transitions
    public static Pixmap ready;
    public static Pixmap paused;
    public static Pixmap gameover;
    public static Pixmap gamewon;

    // Sounds
    public static Sound lily;
    public static Sound click;
    public static Sound OST;  // Original Frogger Soundtrack plays: every 100 points gained
    public static Sound lost;
    public static Sound pause;
}

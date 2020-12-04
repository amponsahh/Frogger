package com.namponsah.frogger;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class Fr3dgger extends AndroidGame {

    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}

package com.namponsah.frogger;

import android.graphics.PixelFormat;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();

        // Landing page
        Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.mainmenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
        Assets.MiscSXP = g.newPixmap("(un)mute,X,Pause.png", PixmapFormat.ARGB4444);

        // Game Screen
        Assets.gamebackground = g.newPixmap("gamebkground.png", PixmapFormat.ARGB4444);
        Assets.up = g.newPixmap("FrogUp.png", PixmapFormat.ARGB4444);
        Assets.down = g.newPixmap("FrogDown.png", PixmapFormat.ARGB4444);
        Assets.left = g.newPixmap("FrogLeft.png", PixmapFormat.ARGB4444);
        Assets.right = g.newPixmap("FrogRight.png", PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);

        // Game Screen: Vehicles
        Assets.carARight = g.newPixmap("Car1-Right.png", PixmapFormat.ARGB4444);
        Assets.carBRight = g.newPixmap("Car2-Right.png", PixmapFormat.ARGB4444);
        Assets.limoLeft = g.newPixmap("Limo-Left.png", PixmapFormat.ARGB4444);
        Assets.semiRight = g.newPixmap("Semi-Right.png", PixmapFormat.ARGB4444);

        // Game Screen: Immovable assets, Lily pad and lives
        Assets.lilypad = g.newPixmap("lilyPad.png", PixmapFormat.ARGB4444);
        Assets.lives = g.newPixmap("FrogLife.png", PixmapFormat.ARGB4444);

        // Transitions
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
        Assets.paused = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
        Assets.gameover = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
        Assets.gamewon = g.newPixmap("gamewon.png", PixmapFormat.ARGB4444);

        // Sounds
        Assets.lily = game.getAudio().newSound("lily.ogg");
        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.OST = game.getAudio().newSound("ForggerOST.ogg");
        Assets.lost = game.getAudio().newSound("gameover.ogg");
        Assets.pause = game.getAudio().newSound("paused.ogg");

        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    public void present(float deltaTime) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }
}


package com.namponsah.frogger;

import android.content.res.AssetManager;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver,
        GameWon
    }

    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";
    Pixmap headPixmap = Assets.up; // gotta start somewhere
    Pixmap carPixmap = Assets.carARight; // gotta start somewhere

    public GameScreen(Game game) {
        super(game);
        world = new World();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if (state == GameState.Ready) {
            updateReady(touchEvents);
        }
        if (state == GameState.Running) {
            updateRunning(touchEvents, deltaTime);
        }
        if (state == GameState.Paused) {
            updatePaused(touchEvents);
        }
        if (state == GameState.GameOver) {
            updateGameOver(touchEvents);
        }
        if(state == GameState.GameWon){
            updateGameWon(touchEvents);
        }
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0) {
            state = GameState.Running;
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y < 64) {
                    if (Settings.soundEnabled) {
                        Assets.pause.play(1);
                    }
                    state = GameState.Paused;
                    return;
                }
            }

            if(event.type == TouchEvent.TOUCH_DOWN){
                if(event.x <= 40 && event.y > 440){
                    if(Settings.soundEnabled){
                        Assets.click.play(1);
                    }
                    world.fr3d.advance(3); // left
                    headPixmap = Assets.left;
                }
                if (event.x >= 40 && event.x < 80 && event.y > 440) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    world.fr3d.advance(4); // right
                    headPixmap = Assets.right;
                }
                if (event.x > 280 && event.y > 440) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    world.fr3d.advance(1); // up
                    headPixmap = Assets.up;
                }
                if (event.x >= 240 && event.x < 280 && event.y > 440) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    world.fr3d.advance(2); // down
                    headPixmap = Assets.down;
                }
            }
        }
        world.update(deltaTime);
        if (world.gameOver) {
            if (Settings.soundEnabled) {
                Assets.lost.play(1);
            }
            state = GameState.GameOver;
        }
        if (oldScore != world.score) {
            oldScore = world.score;
            score = "" + oldScore;
            if (Settings.soundEnabled) {
                Assets.lily.play(1);
                if(oldScore == 100){ // easter egg :)
                    Assets.OST.play(1);
                }
            }
        }
        if(world.gameWon){
            state = GameState.GameWon;
            if (Settings.soundEnabled) {
                Assets.lily.play(1);
            }
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 80 && event.x <= 240) {
                    if (event.y > 100 && event.y <= 148) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        state = GameState.Running;
                        return;
                    }
                    if (event.y > 148 && event.y < 196) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        Settings.addScore(world.score);
                        Settings.save(game.getFileIO());
                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    private void updateGameWon(List<TouchEvent> touchEvents){
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(world);
        if (state == GameState.Ready) {
            drawReadyUI();
        }
        if (state == GameState.Running) {
            drawRunningUI();
        }
        if (state == GameState.Paused) {
            drawPausedUI();
        }
        if (state == GameState.GameOver) {
            drawGameOverUI();
        }
        if(state == GameState.GameWon){
            drawGameWonUI();
        }

        drawText(g, score, g.getWidth() - score.length() * 20, 0);
    }


    private void drawWorld(World world) {
        Graphics g = game.getGraphics();

        Fr3d thisfr3d = world.fr3d;

        // draw game background
        g.drawPixmap(Assets.gamebackground, 0, 0);
        // draw lilypad
        g.drawPixmap(Assets.lilypad, thisfr3d.lilypad[0][0], thisfr3d.lilypad[0][1], 0,0, 40, 40);
        g.drawPixmap(Assets.lilypad, thisfr3d.lilypad[1][0], thisfr3d.lilypad[1][1], 0,0, 40, 40);
        g.drawPixmap(Assets.lilypad, thisfr3d.lilypad[2][0], thisfr3d.lilypad[2][1], 0,0, 40, 40);
        g.drawPixmap(Assets.lilypad, thisfr3d.lilypad[3][0], thisfr3d.lilypad[3][1], 0,0, 40, 40);

        // draw lilypad lands
        for (Integer i : world.unique){  // using unique here saves a ton of memory as opposed to fr3d's lilypad histry
            switch (i){
                case 0:
                    g.drawPixmap(Assets.down, thisfr3d.lilypad[0][0], thisfr3d.lilypad[0][1], 0,0, 40, 40);
                    break;
                case 1:
                    g.drawPixmap(Assets.down, thisfr3d.lilypad[1][0], thisfr3d.lilypad[1][1], 0,0, 40, 40);
                    break;
                case 2:
                    g.drawPixmap(Assets.down, thisfr3d.lilypad[2][0], thisfr3d.lilypad[2][1], 0,0, 40, 40);
                    break;
                case 3:
                    g.drawPixmap(Assets.down, thisfr3d.lilypad[3][0], thisfr3d.lilypad[3][1], 0,0, 40, 40);
                    break;
            }
        }


        // draw fr3d's lives
        int xincrement = 17;
        int xposition = 125;
        for(int i = 0; i < thisfr3d.lives; i += 1){
            if(i == 0){
                g.drawPixmap(Assets.lives, xposition, 10, 0, 0, 17,16);
            }else{
                xposition += xincrement;
                g.drawPixmap(Assets.lives, xposition, 10, 0, 0, 17,16);
            }
        }

        // draw fr3d
        g.drawPixmap(headPixmap, thisfr3d.POS_X, thisfr3d.POS_Y);

        // draw moving obstacles
        // cars LR
        for(int i = 0; i < 2; i += 1){
            for (int j = 0; j < world.carSets[i].size(); j += 1){
                Car incar = world.carSets[i].Cars.get(j);

                switch (incar.type){
                    case 0:
                        carPixmap = Assets.carBRight;
                        break;
                    case 1:
                        carPixmap = Assets.carARight;
                        break;
                }
                g.drawPixmap(carPixmap, incar.POS_X, incar.POS_Y);
            }
        }

        //limos RL
        for(int i = 0; i < 2; i += 1){
            for (int j = 0; j < world.limoSets[i].size(); j += 1){
                Limo inlimo = world.limoSets[i].Limos.get(j);
                g.drawPixmap(Assets.limoLeft, inlimo.POS_X, inlimo.POS_Y);
            }
        }

        // semis LR
        for(int i = 0; i < 1; i += 1) {
            for (int j = 0; j < world.semiSets[i].size(); j += 1) {
                Semi insemi = world.semiSets[i].Semis.get(j);
                g.drawPixmap(Assets.semiRight, insemi.POS_X, insemi.POS_Y);
            }
        }

    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.ready, 47, 100);
    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

        // pause
        g.drawPixmap(Assets.MiscSXP, 0,0,32,32,32,32);

        // left
        g.drawPixmap(Assets.left, 0, 440,0,0,40,40);

        // right
        g.drawPixmap(Assets.right, 40, 440,0,0,40,40);

        // up
        g.drawPixmap(Assets.up, 280, 440,0,0,40,40);

        // down
        g.drawPixmap(Assets.down, 240, 440,0,0,40,40);
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.paused, 80, 100);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.gameover, 62, 100);
        g.drawPixmap(Assets.MiscSXP, 150, 200, 0, 32, 32, 32);
    }

    private void drawGameWonUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.gamewon, 62, 150);
        g.drawPixmap(Assets.MiscSXP, 155, 200, 0, 32, 32, 32);
    }


    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i += 1) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    @Override
    public void pause() {
        if (state == GameState.Running) {
            state = GameState.Paused;
        }
        if (world.gameOver) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
        if (world.gameWon) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}

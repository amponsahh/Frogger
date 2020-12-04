package com.namponsah.frogger;

import com.badlogic.androidgames.framework.FileIO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Settings {
    public static boolean soundEnabled = true;
    public static int[] highscores = new int[] {100, 80, 60, 40, 20};


    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    files.readFile(".fr3dgger")));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            for (int i = 0; i < 5; i += 1) {
                highscores[i] = Integer.parseInt(in.readLine());
            }
        } catch (IOException e) {
            // :( It's ok we have defaults
        } catch (NumberFormatException e) {
            // :/ It's ok, defaults save our day
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(".fr3dgger")));
            // fix bug for NumberFormatException
            out.write(Boolean.toString(soundEnabled) + "\n");
            for (int i = 0; i < 5; i += 1) {
                // fix bug for NumberFormatException
                out.write(Integer.toString(highscores[i]) + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void addScore(int score) {
        for (int i = 0; i < 5; i += 1) {
            if (highscores[i] < score) {
                for (int j = 4; j > i; j -= 1) {
                    highscores[j] = highscores[j - 1];
                }
                highscores[i] = score;
                return;
            }
        }
    }

}

package com.example.leon.cannonball;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;

public class CannonBallActivity extends Activity {

    SpriteView view;
    GameModel model;
    GameThread runner;
    static String tag = "Bubble: ";     //TODO: TAGs
    static SoundPool SP;           //SoundPool better than MediaPlayer for sound effects as it can overlap and is faster
    static int cannonSound;
    static int ouchSound;
    static int brickSmashSound;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new GameModel();
        view = new SpriteView(this);
        SP = new SoundPool.Builder().setMaxStreams(5).build();
        cannonSound = SP.load( this, R.raw.cannon_fire, 1 );
        ouchSound = SP.load( this, R.raw.ouch, 1);
        brickSmashSound = SP.load( this, R.raw.bricksmash, 1);
        setContentView(view);
        System.out.println(tag + model);
        System.out.println(tag + view);
    }

    public GameModel getModel() {
        return model;
    }

    public void onResume() {
        super.onResume();
        System.out.println("Bubble: onResume: ");
        rect = new Rect(0, 0, view.getWidth(), view.getHeight());
        System.out.println("Bubble: onResume: " + rect);
        runner = new GameThread();
        runner.start();
    }

    public void onPause() {
        super.onPause();
        runner.running = false;
        try {
            runner.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    Rect rect;


    class GameThread extends Thread {
        // have
        boolean running = true;

        public void run() {
            System.out.println(tag + "Running thread ...");
            while (running) {
                try {
                    rect = new Rect(0, 0, view.getWidth(), view.getHeight());
                    getModel().update(rect, Constants.delay);
                    view.postInvalidate();
                    Thread.sleep(Constants.delay);
                } catch (Exception e) {
                    System.out.println("CannonBallActivity Thread: " + e);
                    e.printStackTrace();
                }
            }
        }
    }
}
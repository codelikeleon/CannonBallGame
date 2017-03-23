package com.example.leon.cannonball;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.SoundPool;
import android.os.Bundle;

public class CannonBallActivity extends Activity {

    SpriteView view;
    GameModel model;
    GameThread runner;
    Rect rect;
    static String TAG = "CannonBallActivity: ";

    static SoundPool SP;    //SoundPool better than MediaPlayer for sound effects as it can overlap and is faster
    static int CANNON_SOUND;
    static int OUCH_SOUND;
    static int BRICK_SMASH_SOUND;
    static int TWINKLE_SOUND;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        model = new GameModel();
        view = new SpriteView( this );
        setContentView( view );
        System.out.println( TAG + model );
        System.out.println( TAG + view );

        loadSound();
    }

    public GameModel getModel() {
        return model;
    }

    public void onResume() {
        super.onResume();
        System.out.println( TAG + "onResume: " );
        rect = new Rect( 0, 0, view.getWidth(), view.getHeight() );
        System.out.println( TAG + "onResume: " + rect );
        runner = new GameThread();
        runner.start();
    }

    public void onPause() {
        super.onPause();
        runner.running = false;
        try {
            runner.join();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private void loadSound() {
        SP = new SoundPool.Builder().setMaxStreams( 5 ).build();
        CANNON_SOUND = SP.load( this, R.raw.cannon_fire, 1 );
        OUCH_SOUND = SP.load( this, R.raw.ouch, 1 );
        BRICK_SMASH_SOUND = SP.load( this, R.raw.bricksmash, 1 );
        TWINKLE_SOUND = SP.load( this, R.raw.twinkle, 1 );
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    class GameThread extends Thread {
        // have
        boolean running = true;

        public void run() {
            System.out.println( TAG + "Running thread ..." );
            while ( running ) {
                try {
                    rect = new Rect( 0, 0, view.getWidth(), view.getHeight() );
                    getModel().update( rect, Constants.delay );
                    view.postInvalidate();
                    Thread.sleep( Constants.delay );
                } catch ( Exception e ) {
                    System.out.println( "CannonBallActivity Thread: " + e );
                    e.printStackTrace();
                }
            }
        }
    }
}
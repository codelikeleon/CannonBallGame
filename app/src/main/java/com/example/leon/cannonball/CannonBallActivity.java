package com.example.leon.cannonball;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.SoundPool;
import android.os.Bundle;

/**
 * The Controller activity. This is the activity where the game is played. Contains static variables
 * to access sound effects, device screen dimensions and a thread for the game.
 *
 * @author 028016
 */
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

    /**
     * Provides the model.
     *
     * @return the model.
     */
    public GameModel getModel() {
        return model;
    }

    /**
     * This method is executed upon resuming the app, e.g. returning from the home screen
     */
    public void onResume() {
        super.onResume();
        System.out.println( TAG + "onResume: " );
        rect = new Rect( 0, 0, view.getWidth(), view.getHeight() );
        System.out.println( TAG + "onResume: " + rect );
        runner = new GameThread();
        runner.start();
    }

    /**
     * This method is executed upon pausing the app, e.g. going to the home screen.
     */
    public void onPause() {
        super.onPause();
        runner.running = false;
        try {
            runner.join();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a static SoundPool and loads up all sound effects stored in resources and used for
     * the game. They can be easily accessed elsewhere due to their static context.
     */
    private void loadSound() {
        SP = new SoundPool.Builder().setMaxStreams( 5 ).build();
        CANNON_SOUND = SP.load( this, R.raw.cannon_fire, 1 );
        OUCH_SOUND = SP.load( this, R.raw.ouch, 1 );
        BRICK_SMASH_SOUND = SP.load( this, R.raw.bricksmash, 1 );
        TWINKLE_SOUND = SP.load( this, R.raw.twinkle, 1 );
    }

    /**
     * Provides the screen width of the device currently running the application.
     *
     * @return The width of the device's screen as an integer
     */
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * Provides the screen height of the device currently running the application.
     *
     * @return The height of the device's screen as an integer
     */
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * A thread extension class where the game graphics are created and constantly updated throught
     * a continuous loop.
     */
    private class GameThread extends Thread {

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
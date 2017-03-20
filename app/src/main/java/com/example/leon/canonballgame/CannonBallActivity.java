package com.example.leon.canonballgame;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CannonBallActivity extends AppCompatActivity {

    SpriteView view;
    GameModel model;
    GameThread thread;

    static String TAG = "Cannonball: ";

    Rect rectangle;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        model = new GameModel();
        view = new SpriteView( this );

        setContentView( view );
        System.out.println( TAG + view );
        System.out.println( TAG + model );
        setContentView(R.layout.activity_cannon_ball);
    }

    public GameModel getModel() { return model; }
    public SpriteView getView() { return view; }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    class GameThread extends Thread {
        boolean running = true;

        public void run() {
            System.out.println( TAG + "Thread running..." );
            while ( running ) {
                try {
                    rectangle = new Rect( 0, 0, view.getWidth(), view.getHeight() );
                    // System.out.println("Bubble Thread: " + rect);
                    getModel().update( rectangle, Constants.delay );
                    view.postInvalidate();
                    Thread.sleep( Constants.delay );
                } catch ( Exception e ) {
                    System.out.println( "BubbleThread: " + e );
                    e.printStackTrace();
                }
            }
        }
    }
}


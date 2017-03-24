package com.example.leon.cannonball;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A View class for initially drawing objects and responding to touch events. High scores are saved
 * and displayed using shared preferences, as well as a win/loss alert dialog.
 */
public class SpriteView extends View {

    CannonBallActivity controller;
    private Context context;

    static String TAG = "SpriteView: ";

    public Cannon cannon;
    public Blocker blocker;
    public Cannonball cannonball;
    public Drawable background;

    private Paint textPaint;
    private boolean dialogCreated = false;

    /**
     * Draws the initial objects onto the canvas and keeps a list of all active Sprites. Also checks
     * for game ending conditions
     *
     * @param g The canvas to be drawn on
     */
    public void onDraw( Canvas g ) {

        //Background image
        background = getResources().getDrawable( R.drawable.beach_background );
        background.setBounds( 0, 0, CannonBallActivity.getScreenWidth(), CannonBallActivity.getScreenHeight() );
        background.draw( g );

        List<Sprite> sprites = controller.getModel().sprites;
        for ( Sprite sprite : sprites ) {
            sprite.draw( g );
        }

        blocker.draw( g );
        cannonball.draw( g );
        cannon.draw( g );

        textPaint = new Paint();
        textPaint.setTextSize( 40 );
        textPaint.setAntiAlias( true ); // smooth the text
        textPaint.setARGB( 255, 255, 0, 0 );

        g.drawText( getResources().getString(
                R.string.time_remaining_format,
                controller.getModel().timeRemaining / 1000 ), 200, 30, textPaint );
        g.drawText( getResources().getString(
                R.string.score_format, controller.getModel().score ), 10, 30,
                textPaint );

        if ( controller.getModel().hasLost() ) displayLoserDialog( context );
        if ( controller.getModel().hasWon() ) displayWinnerDialog( context );

    }

    public SpriteView( Context context ) {
        super( context );
        this.context = context;
        controller = ( CannonBallActivity ) context;

        controller.getModel().blocker = new Blocker( context );
        blocker = controller.getModel().blocker;

        controller.getModel().cannonball = new Cannonball( context );
        cannonball = controller.getModel().cannonball;

        controller.getModel().cannon = new Cannon( context );
        cannon = controller.getModel().cannon;
    }

    public SpriteView( Context context, AttributeSet attrs ) {
        super( context, attrs );
        this.context = context;
        controller = ( CannonBallActivity ) context;

        controller.getModel().blocker = new Blocker( context );
        blocker = controller.getModel().blocker;

        controller.getModel().cannonball = new Cannonball( context );
        cannonball = controller.getModel().cannonball;

        controller.getModel().cannon = new Cannon( context );
        cannon = controller.getModel().cannon;
    }

    public SpriteView( Context context, AttributeSet attrs, int defStyle ) {
        super( context, attrs, defStyle );
        this.context = context;
        controller = ( CannonBallActivity ) context;

        controller.getModel().blocker = new Blocker( context );
        blocker = controller.getModel().blocker;

        controller.getModel().cannonball = new Cannonball( context );
        cannonball = controller.getModel().cannonball;

        controller.getModel().cannon = new Cannon( context );
        cannon = controller.getModel().cannon;
    }

    /**
     * Gets the coordinates of the position the screen was touched and causes the cannon and
     * cannonball to move and fire. A sound effect is also played.
     *
     * @param event The event captured, in this case upon touching the screen
     * @return A boolean corresponding to the touch event
     */
    @Override
    public boolean onTouchEvent( MotionEvent event ) {
        float x = event.getX();
        float y = event.getY();
        if ( !cannonball.firing )
            CannonBallActivity.SP.play( CannonBallActivity.CANNON_SOUND, 1, 1, 0, 0, 1 );
        controller.getModel().click( x );
        if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
            cannon.moveCannon( x );
            cannonball.moveCannonBall( x );
            cannonball.fireCannon();
        }
        return super.onTouchEvent( event );
    }

    /**
     * Creates an alert dialog to signify the loss of a game. Sets a flag to 'true' when a dialog
     * has been created in order to prevent new dialogs constantly being created as this method
     * is executed within the game loop. The dialog then redirects the user to the main menu by
     * finishing CannonBallActivity.
     *
     * @param context
     */
    void displayLoserDialog( Context context ) {
        if ( !dialogCreated ) {
            dialogCreated = true;
            new AlertDialog.Builder( context )
                    .setTitle( "Game Over" )
                    .setMessage( "You have run out of time!" )
                    .setNeutralButton( R.string.return_to_menu, new DialogInterface.OnClickListener() {
                        public void onClick( DialogInterface dialog, int which ) {
                            controller.finish();
                        }
                    })
                    .setIcon( android.R.drawable.ic_dialog_alert )
                    .show();
        }
    }

    /**
     * Creates an alert dialog to signify a win of a game. Sets a flag to 'true' when a dialog
     * has been created in order to prevent new dialogs constantly being created as this method
     * is executed within the game loop. The dialog then redirects the user to the main menu by
     * finishing CannonBallActivity. The top 5 high scores are displayed in the dialog box as well
     * as the user's score.
     *
     * @param context
     */
    void displayWinnerDialog( Context context ) {
        if ( !dialogCreated ) {
            dialogCreated = true;
            List<Integer> scores = getScores();
            new AlertDialog.Builder( context )
                    .setTitle( "Game Over" )
                    .setMessage( "You have won with a score of " + controller.getModel().score + "!"
                              + scoresToString( scores )
                    )
                    .setNeutralButton( R.string.return_to_menu, new DialogInterface.OnClickListener() {
                        public void onClick( DialogInterface dialog, int which ) {
                            controller.finish();
                        }
                    })
                    .setIcon( android.R.drawable.ic_dialog_alert )
                    .show();
        }
    }

    /**
     * Produces a string that can be displayed in the winner's dialog box to show the top 5 high
     * scores saved in the preferences file. If there are less than 5 high scores saved, an
     * IndexOutOfBoundsException is caught. The scores are read from a List.
     *
     * @param scores The List containing all high scores
     * @return A string that will show the top 5 high scores
     */
    public String scoresToString( List<Integer> scores ) {
        String string = "\nHigh Scores:\n";
        for ( int i = 0; i < 5; i++ ) {
            try {
                string += "\n" + ( i+1 ) + ": " + scores.get( i );
            } catch ( IndexOutOfBoundsException e ) {
                System.out.println( "Too few high scores have been stored to display 5." );
            }
        }
        return string;
    }

    /**
     * Saves the user's score in the Shared Preferences, and gets all scores previously recorded.
     * Scores are saved with the date/time they were created as well as the level.
     * The scores are then returned as a list.
     *
     * @return A list of all scores saved in Shared Preferences
     */
    public List<Integer> getScores() {
        SharedPreferences prefs = getContext().getSharedPreferences( "scores", Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt( new java.util.Date() + " (Level " + MainMenuActivity.LEVEL
                     + ")", controller.getModel().score );

        editor.apply();
        Map<String, ?> m = prefs.getAll();

        System.out.println( m );

        final List<Integer> scores = new ArrayList<>();
        for ( Map.Entry<String, ?> e : m.entrySet() )
            scores.add( ( Integer ) e.getValue() ) ;
        Collections.sort( scores );
        Collections.reverse( scores );

        return scores;
    }
}
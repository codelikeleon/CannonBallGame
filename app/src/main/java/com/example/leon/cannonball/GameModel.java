package com.example.leon.cannonball;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

import static com.example.leon.cannonball.Constants.blockerScore;
import static com.example.leon.cannonball.Constants.greyTimeBonus;
import static com.example.leon.cannonball.Constants.magentaScore;
import static com.example.leon.cannonball.Constants.magentaTimeBonus;
import static com.example.leon.cannonball.Constants.yellowScore;
import static com.example.leon.cannonball.Constants.yellowTimeBonus;

/**
 * Created by Leon on 18/03/2017.
 */

public class GameModel {

    static String TAG = "GameModel: ";
    ArrayList<Sprite> sprites;
    Random rand = new Random();
    int score;
    int timeRemaining = 10000;
    int numSprites = 5 * MainMenuActivity.LEVEL;     //Max numSprites = 50

    static Paint paintLightGrey, paintMagenta, paintYellow;
    public Cannon cannon;
    public Blocker blocker;
    public Cannonball cannonball;

    static {
        paintLightGrey = new Paint();
        paintLightGrey.setColor( Color.GRAY );
        paintLightGrey.setStyle( Paint.Style.STROKE );
        paintLightGrey.setStrokeWidth( 5 );
        paintLightGrey.setAntiAlias( true );

        paintMagenta = new Paint();
        paintMagenta.setColor( Color.MAGENTA );
        paintMagenta.setStyle( Paint.Style.STROKE );
        paintMagenta.setStrokeWidth( 5 );
        paintMagenta.setAntiAlias( true );

        paintYellow = new Paint();
        paintYellow.setColor( Color.YELLOW );
        paintYellow.setStyle( Paint.Style.STROKE );
        paintYellow.setStrokeWidth( 5 );
        paintYellow.setAntiAlias( true );
    }

    public GameModel() {
        System.out.println( TAG + "GameModel()" );
        initSprites();
        score = 0;
        System.out.println( TAG + "Finished initSprites()" );
    }

    public void update( Rect rectangle, int delay ) {

        // check that the drawing rectangle is valid
        if ( rectangle.width() <= 0 || rectangle.height() <= 0 ) return;
        System.out.println( "PLAYING LEVEL " + MainMenuActivity.LEVEL );
        if ( hasWon() ) {

        } else if ( hasLost() ) {
            
        } else {
            if ( blocker.contains( cannonball.getCentreXPos(), cannonball.getCentreYPos() ) ) {
                cannonball.hitBlocker();
                CannonBallActivity.SP.play( CannonBallActivity.OUCH_SOUND, 1, 1, 0, 0, 1 );
                score += blockerScore * MainMenuActivity.LEVEL / 2;
            }
            for ( Sprite sprite : sprites ) {
                sprite.update();
                if ( sprite.contains( cannonball.getCentreXPos(), cannonball.getCentreYPos() ) ) {
                    sprites.remove( sprite );
                    if ( sprite.getScore() == yellowScore ) {
                        CannonBallActivity.SP.play(CannonBallActivity.TWINKLE_SOUND, 1, 1, 0, 0, 1);
                        timeRemaining += yellowTimeBonus;
                    }
                    else {
                        timeRemaining += sprite.getScore() == magentaScore ? magentaTimeBonus : greyTimeBonus;
                        CannonBallActivity.SP.play( CannonBallActivity.BRICK_SMASH_SOUND, 1, 1, 0, 0, 1 );
                    }
                    score += sprite.getScore() * MainMenuActivity.LEVEL;
                    cannonball.reset();
                }
            }
            blocker.update();
            cannonball.shootCannonBall();
            timeRemaining -= delay;
        }
    }

    public boolean hasWon() {
        return sprites.isEmpty() && timeRemaining > 0;
    }

    public boolean hasLost() {
        return timeRemaining <= 0;
    }

    public void click( float x ) {
        cannon.moveCannon( x );
        cannonball.moveCannonBall( x );
        cannonball.fireCannon();
    }

    void initSprites() {
        sprites = new ArrayList<>();
        for ( int i = 0; i < numSprites; i++ ) {

            // Random sprite generation
            int selector = rand.nextInt( 10 );
            Paint p;
            if ( selector < 1 ) p = paintYellow;
            else if ( selector < 4 ) p = paintMagenta;
            else p = paintLightGrey;
            Sprite sprite = new Sprite( p );

            if ( i >= 20 ) {
                sprite.setPos( ( i - 20 ) * sprite.width, sprite.height * 5 );
                sprites.add( sprite );
            } else if ( i >= 15 ) {
                sprite.setPos( ( i - 15 ) * sprite.width + CannonBallActivity.getScreenWidth() / 2, sprite.height * 4 );
                sprites.add( sprite );
            } else if ( i >= 10 ) {
                sprite.setPos( ( i - 10 ) * sprite.width, sprite.height * 3 );
                sprites.add( sprite );
            } else if ( i >= 5 ) {
                sprite.setPos( ( i - 5 ) * sprite.width + CannonBallActivity.getScreenWidth() / 2, sprite.height * 2 );
                sprites.add( sprite );
            } else {
                sprite.setPos( i * sprite.width, sprite.height );
                sprites.add( sprite );
            }
        }
    }
}
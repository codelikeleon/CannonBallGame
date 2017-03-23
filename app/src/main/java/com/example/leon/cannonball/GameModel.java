package com.example.leon.cannonball;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

import static com.example.leon.cannonball.Constants.blockerScore;
import static com.example.leon.cannonball.Constants.yellowScore;

/**
 * Created by Leon on 18/03/2017.
 */

public class GameModel {

    static String TAG = "GameModel: ";

    ArrayList<Sprite> sprites;
    Random rand = new Random();
    int numSprites = 30;        //Max numSprites = 50
    int score;
    int timeRemaining = 10000;

    static Paint paintLightGrey, paintMagenta, paintYellow;
    public Cannon cannon;
    public Blocker blocker;
    public Cannonball cannonball;

    static {
        paintLightGrey = new Paint();
        paintLightGrey.setColor( Color.GRAY );
        paintLightGrey.setStyle( Paint.Style.FILL_AND_STROKE );
        paintLightGrey.setAntiAlias( true );

        paintMagenta = new Paint();
        paintMagenta.setColor( Color.MAGENTA );
        paintMagenta.setStyle( Paint.Style.FILL_AND_STROKE );
        paintMagenta.setAntiAlias( true );

        paintYellow = new Paint();
        paintYellow.setColor( Color.YELLOW );
        paintYellow.setStyle( Paint.Style.FILL_AND_STROKE );
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

        if ( !gameOver() ) {
            if ( blocker.contains( cannonball.getCentreXPos(), cannonball.getCentreYPos() ) ) {
                cannonball.hitBlocker();
                CannonBallActivity.SP.play( CannonBallActivity.OUCH_SOUND, 1, 1, 0, 0, 1 );
                score += blockerScore;
            }
            for ( Sprite sprite : sprites ) {
                if ( sprite.contains( cannonball.getCentreXPos(), cannonball.getCentreYPos() ) ) {
                    sprites.remove( sprite );
                    if ( sprite.getScore() == yellowScore )
                        CannonBallActivity.SP.play( CannonBallActivity.TWINKLE_SOUND, 1, 1, 0, 0, 1 );
                    else CannonBallActivity.SP.play( CannonBallActivity.BRICK_SMASH_SOUND, 1, 1, 0, 0, 1 );
                    score += sprite.getScore();
                    cannonball.reset();
                }
            }
//            timeRemaining -= delay;
            blocker.update( rectangle );
            cannonball.shootCannonBall();
        }
    }

    public boolean gameOver() {
        return timeRemaining <= 0;
    }

    public void click( float x, float y ) {
        cannon.moveCannon( x );
        cannonball.moveCannonBall( x );
        cannonball.fireCannon();
    }

    void initSprites() {
        sprites = new ArrayList<>();
        for ( int i = 0; i < numSprites; i++ ) {

            int selector = rand.nextInt( 10 );
            Paint p;
            if ( selector < 1 ) p = paintYellow;
            else if ( selector < 4 ) p = paintMagenta;
            else p = paintLightGrey;
            Sprite sprite = new Sprite( p );

            if ( i >= 40 ) {
                sprite.setPos( ( i - 40 ) * sprite.width, sprite.height * 5 );
                sprites.add( sprite );
            } else if ( i >= 30 ) {
                sprite.setPos( ( i - 30 ) * sprite.width, sprite.height * 4 );
                sprites.add( sprite );
            } else if ( i >= 20 ) {
                sprite.setPos( ( i - 20 ) * sprite.width, sprite.height * 3 );
                sprites.add( sprite );
            } else if ( i >= 10 ) {
                sprite.setPos( ( i - 10 ) * sprite.width, sprite.height * 2 );
                sprites.add( sprite );
            } else {
                sprite.setPos( i * sprite.width, sprite.height );
                sprites.add( sprite );
            }
        }
    }
}
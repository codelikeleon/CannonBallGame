package com.example.leon.canonballgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

import static com.example.leon.canonballgame.Constants.*;

/**
 * Created by Leon on 20/03/2017.
 */

public class Sprite {

    Vector2d s, v;
    float rad;
    Paint fg;

    static Random random = new Random();

    public Sprite() {
        s = new Vector2d();
        v = new Vector2d();
        respawn();
    }

    public Sprite( Paint fg ) {
        this();
        this.fg = fg;
    }

    public void respawn() {
        //rad = minRadius + random.nextInt(randSize);
        rad = ( float ) CannonBallActivity.getScreenHeight() / proportion +
                random.nextInt( 40 );
        s.set( 0, 0 );
        v.set( velocityScale * ( float ) random.nextGaussian(),
                velocityScale * ( float ) random.nextGaussian());
    }

    public int getScore() {
        return fg == GameModel.paintLightGrey ? greyScore : magentaScore;
    }

    public void update( Rect rect ) {
        s.add( v );
        s.wrap( rect.width(), rect.height() );
    }

    public boolean contains( float x, float y ) {
        return s.dist( x,y ) < rad;
    }

    public void draw( Canvas c ) {
        c.drawRect( s.x, s.y, s.x + rad, s.y + rad, fg );
    }
}

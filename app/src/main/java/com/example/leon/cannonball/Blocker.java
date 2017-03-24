package com.example.leon.cannonball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import static com.example.leon.cannonball.Constants.velocityScale;

/**
 * A class for the blocker that moves horizontally on screen, causing the Cannonball to rebound
 * if it collides, and the player to lose points.
 *
 * @author 028016
 */
public class Blocker {

    Drawable blocker;
    Bitmap bmp;
    float width = CannonBallActivity.getScreenWidth() / 3;
    float height = CannonBallActivity.getScreenHeight() / 14;
    Vector2d pos, v;

    public Blocker( Context context ) {
        pos = new Vector2d( width * 3/2  - width / 2, height * 14/3 - height );
        v = new Vector2d( 3/2 * velocityScale * MainMenuActivity.LEVEL, 0 );
        bmp = BitmapFactory.decodeResource( context.getResources(), R.drawable.crab_blocker );
        this.blocker = new BitmapDrawable( context.getResources(), bmp );
    }

    /**
     * Sets the boundaries of the Blocker to its initial position. The blocker is then drawn
     * on to the canvas.
     *
     * @param c The canvas to be drawn on
     */
    public void draw( Canvas c ) {
        blocker.setBounds( ( int ) pos.x,
                           ( int ) pos.y,
                           ( int ) ( pos.x + width ),
                           ( int ) ( pos.y + height ) );
        blocker.draw( c );
    }

    /**
     * Called in GameModel's update() function, this method changes the position of the blocker
     * by adding its velocity. Since it is run in GameModel's loop, it will cause the blocker to
     * move on screen. A check to see if the blocker has intersected the edge of the screen
     * determines whether or not the blocker's velocity should be reversed, causing it to appear
     * as though it is bouncing off the edge of the screen.
     */
    public void update() {
        pos.add( v );
        if ( pos.x + width >= CannonBallActivity.getScreenWidth() ) v.x *= -1;
        else if ( pos.x <= 0 ) v.x *= -1;
    }

    /**
     * Checks to see if the input coordinates (x, y) intersect with the boundaries of the blocker.
     *
     * @param x x-coordinate to be checked for intersection with the Blocker.
     * @param y y-coordinate to be checked for intersection with the Blocker.
     * @return A Boolean true if (x, y) intersects the blocker, false otherwise.
     */
    public boolean contains( float x, float y ) {
        return x >= pos.x && x <= pos.x + width && y >= pos.y && y <= pos.y + height;
    }
}

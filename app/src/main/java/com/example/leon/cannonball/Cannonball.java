package com.example.leon.cannonball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import static com.example.leon.cannonball.Constants.cannonShootSpeed;

/**
 * A class for the cannonball that shoots out of the cannon on the user's touch input. When not in
 * use, the cannonball resides at the bottom of the screen hidden underneath the cannon. Upon
 * firing, the cannonball will move according to its velocity vector and reset to its original
 * position when its position is off-screen or intersecting with a Sprite (target). It will also
 * rebound off the Blocker, i.e. its velocity will be reversed.
 *
 * @author 028016
 */
public class Cannonball {

    Drawable cannonball;
    Bitmap bmp;
    float width = CannonBallActivity.getScreenWidth() / 25;
    float height = width;
    Vector2d pos, v;

    public boolean firing = false;
    public boolean hitBlocker = false;

    public Cannonball( Context context ) {
        pos = new Vector2d( width * 20/2  - width / 2, CannonBallActivity.getScreenHeight() );
        v = new Vector2d( 0, - cannonShootSpeed );
        bmp = BitmapFactory.decodeResource( context.getResources(), R.drawable.cannonball );
        this.cannonball = new BitmapDrawable( context.getResources(), bmp );
    }

    /**
     * Sets the boundaries of the Cannonball to its initial position. The Cannonball is then drawn
     * on to the canvas.
     *
     * @param c The canvas to be drawn on.
     */
    public void draw( Canvas c ) {
        cannonball.setBounds( ( int ) pos.x,
                              ( int ) pos.y,
                              ( int ) ( pos.x + width ),
                              ( int ) ( pos.y + height ) );
        cannonball.draw( c );
    }

    /**
     * If the cannon is not already firing, this method shifts the cannonball to the tip of the
     * cannon and sets the firing flag to true.
     */
    public void fireCannon() {
        if ( !firing ) {
            firing = true;
            pos.y = CannonBallActivity.getScreenHeight() * 4/5;
        }
    }

    /**
     * First checks to see if the cannonball has moved off-screen, if so the cannonball's state is
     * reset with reset(). If the cannonball has hit the Blocker, it will move with a negated
     * velocity. This will cause the cannon to 'rebound' until it is moved off-screen. If the
     * Blocker has not been hit, the cannonball will move with its normal velocity. This method is
     * called in GameModel's update() function.
     */
    public void shootCannonBall() {
        if ( pos.y <= 0 || pos.y > CannonBallActivity.getScreenHeight() ) {
            reset();
        } else if ( hitBlocker ) {
            pos.subtract( v );
        } else if ( firing ) {
            pos.add( v );
        }
    }

    /**
     * Sets the horizontal position of the Cannonball to a specified x-coordinate. The Cannonball's
     * centre is placed on this coordinate.
     *
     * @param x The x-coordinate to set the position of the cannon to.
     */
    public void moveCannonBall( float x ) {
        if ( !firing ) pos.x = x - width / 2;
    }

    /**
     * Sets the hitBlocker flag to true if the cannon is currently firing and has not already hit
     * the Blocker.
     */
    public void hitBlocker() {
        if ( firing && !hitBlocker ) hitBlocker = true;
    }

    /**
     * Resets the cannonball to its initial state, moving it back to its original position,
     * resetting velocity, and any related boolean flags.
     */
    public void reset() {
        v.y = - cannonShootSpeed;
        firing = false;
        pos.y = CannonBallActivity.getScreenHeight();
        hitBlocker = false;
    }

    /**
     * Provides the x-coordinate of the centre of the Cannonball
     *
     * @return The x-coordinate of the centre of the Cannonball
     */
    public float getCentreXPos() {
        return pos.x + width / 2;
    }

    /**
     * Provides the y-coordinate of the centre of the Cannonball
     *
     * @return The y-coordinate of the centre of the Cannonball
     */
    public float getCentreYPos() {
        return pos.y + height / 2;
    }
}

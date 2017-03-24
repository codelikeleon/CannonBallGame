package com.example.leon.cannonball;

import android.graphics.Canvas;
import android.graphics.Paint;

import static com.example.leon.cannonball.Constants.*;

/**
 * Class for the targets displayed at the top of the screen. The will move horizontally, bouncing
 * off the edge of the screen and disappearing when colliding with the cannonball. Yellow sprites
 * yield the largest score/time bonus, and grey sprites the smallest. The size of the sprites scale
 * to the screen dimensions of the device being used. Their movement speed adjusts to the level
 * selected.
 */
public class Sprite {
    Vector2d pos, v;
    Paint fg;

    float width = CannonBallActivity.getScreenWidth() / 10;
    float height = CannonBallActivity.getScreenHeight() / 25;

    public Sprite( Paint fg ) {
        this();
        this.fg = fg;
    }

    public Sprite() {
        pos = new Vector2d();
        v = new Vector2d( velocityScale * MainMenuActivity.LEVEL, 0 );
    }

    /**
     * Sets the position of the sprite to the specified (x, y) coordinates.
     *
     * @param x x-coordinate of the position the sprite should be set to
     * @param y y-coordinate of the position the sprite should be set to
     */
    public void setPos( float x, float y ) {
        pos.set( x, y );
    }

    /**
     * Called in GameModel's update() function, this method changes the position of the sprite
     * by adding its velocity. Since it is run in GameModel's loop, it will cause the sprite to
     * move on screen. A check to see if the sprite has intersected the edge of the screen
     * determines whether or not the sprite's velocity should be reversed, causing it to appear
     * as though it is bouncing off the edge of the screen.
     */
    public void update() {
        pos.add( v );
        if ( pos.x + width > CannonBallActivity.getScreenWidth() ) v.x *= -1;
        else if ( pos.x < 0 ) v.x *= -1;
    }

    /**
     * Provides the score corresponding to the colour of the sprite when hit by the cannonball.
     * Yellow sprites yield the highest score, followed by magenta, and grey yields the smallest.
     *
     * @return The score provided by the sprite, as an integer.
     */
    public int getScore() {
        if ( fg == GameModel.paintLightGrey ) return greyScore;
        else if ( fg == GameModel.paintMagenta ) return magentaScore;
        else return yellowScore;
    }

    /**
     * Checks to see if the input coordinates (x, y) intersect with the boundaries of the sprite.
     *
     * @param x x-coordinate to be checked for intersection with the Sprite.
     * @param y y-coordinate to be checked for intersection with the Sprite.
     * @return A Boolean true if (x, y) intersects the Sprite, false otherwise.
     */
    public boolean contains( float x, float y ) {
        return x >= pos.x && x <= pos.x + width && y >= pos.y && y <= pos.y + height;
    }

    /**
     * Draws a round rectangle on to the canvas, corresponding to the Sprites position and size.
     * The radius of the rectangle's rounded corners are set to 5.
     *
     * @param c The canvas to be drawn on
     */
    public void draw( Canvas c ) {
        c.drawRoundRect( pos.x, pos.y, pos.x + width, pos.y + height, 5, 5, fg );
    }
}

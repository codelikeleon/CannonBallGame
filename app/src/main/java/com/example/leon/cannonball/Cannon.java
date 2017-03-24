package com.example.leon.cannonball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * A class for the cannon drawn at the bottom of the screen. It moves horizontally corresponding
 * to the user's touch.
 *
 * @author 028016
 */

public class Cannon {

    private Drawable cannon;
    private Bitmap bmp;
    private float width, height;
    private Vector2d pos;

    public Cannon( Context context ) {
        width = CannonBallActivity.getScreenWidth() / 3;
        height = CannonBallActivity.getScreenHeight() / 4;
        pos = new Vector2d( CannonBallActivity.getScreenWidth() / 2  - width / 2,
                            CannonBallActivity.getScreenHeight() - height - 20 );

        bmp = BitmapFactory.decodeResource( context.getResources(), R.drawable.cannon );
        this.cannon = new BitmapDrawable( context.getResources(), bmp );
    }

    /**
     * Sets the boundaries of the Cannon to its initial position. The Cannon is then drawn
     * on to the canvas.
     *
     * @param c The canvas to be drawn on
     */
    public void draw( Canvas c ) {
        cannon.setBounds( ( int ) pos.x,
                          ( int ) pos.y,
                          ( int ) ( pos.x + width ),
                          ( int ) ( pos.y + height ) );
        cannon.draw( c );
    }

    /**
     * Sets the horizontal position of the Cannon to a specified x-coordinate. The Cannon's
     * centre is placed on this coordinate.
     *
     * @param x The x-coordinate to set the position of the cannon to
     */
    public void moveCannon( float x ) { pos.x = x - width / 2; }
}

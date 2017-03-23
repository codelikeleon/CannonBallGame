package com.example.leon.cannonball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import static com.example.leon.cannonball.Constants.cannonShootSpeed;

/**
 * Created by Leon on 23/03/2017.
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

    public void draw( Canvas c ) {
        cannonball.setBounds( ( int ) pos.x,
                              ( int ) pos.y,
                              ( int ) ( pos.x + width ),
                              ( int ) ( pos.y + height ) );
        cannonball.draw( c );
    }

    public void fireCannon() {
        if ( !firing ) {
            firing = true;
            pos.y = CannonBallActivity.getScreenHeight() * 4/5;
        }
    }

    public void shootCannonBall() {
        if ( pos.y <= 0 || pos.y > CannonBallActivity.getScreenHeight() ) {
            firing = false;
            hitBlocker = false;
            pos.y = CannonBallActivity.getScreenHeight();
        } else if ( hitBlocker ) {
            pos.subtract( v );
        } else if ( firing ) {
            pos.add( v );
        }
    }

    public void moveCannonBall( float x ) {
        if ( !firing ) pos.x = x - width / 2;
    }

    public void hitBlocker() {
        if ( firing && !hitBlocker ) hitBlocker = true;
    }

    public void reset() {
        v.y = - cannonShootSpeed;
        firing = false;
        pos.y = CannonBallActivity.getScreenHeight();
    }

    public float getCentreXPos() {
        return pos.x + width / 2;
    }

    public float getCentreYPos() {
        return pos.y + height / 2;
    }
}

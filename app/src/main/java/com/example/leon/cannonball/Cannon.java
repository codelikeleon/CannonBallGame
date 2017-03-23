package com.example.leon.cannonball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

/**
 * Created by Leon on 22/03/2017.
 */

public class Cannon {

    CannonBallActivity controller;
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

    public void draw( Canvas c ) {
        cannon.setBounds( ( int ) pos.x,
                          ( int ) pos.y,
                          ( int ) ( pos.x + width ),
                          ( int ) ( pos.y + height ) );
        cannon.draw( c );
    }

    public void moveCannon( float x ) { pos.x = x - width / 2; }
}

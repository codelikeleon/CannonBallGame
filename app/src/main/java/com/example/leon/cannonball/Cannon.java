package com.example.leon.cannonball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by Leon on 22/03/2017.
 */

public class Cannon {

    CannonBallActivity controller;

    private Drawable cannon;
    private Bitmap bmp;
    private int x, y, width, height;

    public Cannon( Context context ) {
        this.setWidth( CannonBallActivity.getScreenWidth() / 3 );
        this.setHeight( CannonBallActivity.getScreenHeight() / 4 );
        this.x = CannonBallActivity.getScreenWidth()/2  - getWidth()/2;
        this.y = CannonBallActivity.getScreenHeight() - getHeight() - 20;

        bmp = BitmapFactory.decodeResource( context.getResources(), R.drawable.cannon );
        this.cannon = new BitmapDrawable(context.getResources(), bmp);
    }

    public void draw(Canvas c) {
        this.cannon.setBounds(x, y, x + this.getWidth(), y + this.getHeight());
        this.cannon.draw(c);
    }

    public int getHeight() { return this.height; }
    public int getWidth() { return this.width; }
    public int getX() { return x; }
    public int getY() { return y; }

    public void setHeight( int height ) { this.height = height; }
    public void setWidth( int width ) { this.width = width; }
    public void setX( int x ) { this.x = x; }
    public void setY( int y ) { this.y = y; }
}

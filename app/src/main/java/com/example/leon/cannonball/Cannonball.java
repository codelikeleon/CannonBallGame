package com.example.leon.cannonball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import static com.example.leon.cannonball.Constants.velocityScale;

/**
 * Created by Leon on 23/03/2017.
 */

public class Cannonball {

    Drawable cannonball;
    Bitmap bmp;
    float width = CannonBallActivity.getScreenWidth() / 20;
    float height = width;
    Vector2d pos, v;

    public Cannonball( Context context ) {
        pos = new Vector2d( width*20/2  - width / 2, CannonBallActivity.getScreenHeight() );
        v = new Vector2d(0, velocityScale);
        bmp = BitmapFactory.decodeResource( context.getResources(), R.drawable.cannonball );
        this.cannonball = new BitmapDrawable( context.getResources(), bmp );
    }

    public void draw(Canvas c) {
        cannonball.setBounds( (int) pos.x, (int) pos.y, (int) pos.x + (int) width, (int) pos.y + (int) height );
        cannonball.draw( c );
    }


}

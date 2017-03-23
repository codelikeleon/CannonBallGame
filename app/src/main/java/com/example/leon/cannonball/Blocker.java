package com.example.leon.cannonball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import static com.example.leon.cannonball.Constants.velocityScale;

/**
 * Created by Leon on 23/03/2017.
 */

public class Blocker {

    Drawable blocker;
    Bitmap bmp;
    float width = CannonBallActivity.getScreenWidth() / 3;
    float height = CannonBallActivity.getScreenHeight() / 14;
    Vector2d pos, v;

    public Blocker( Context context ) {
        pos = new Vector2d( width*3/2  - width / 2, height*14/3 - height );
        v = new Vector2d(velocityScale, 0);
        bmp = BitmapFactory.decodeResource( context.getResources(), R.drawable.crab_blocker );
        this.blocker = new BitmapDrawable( context.getResources(), bmp );
    }

    public void draw(Canvas c) {
        blocker.setBounds( (int) pos.x, (int) pos.y, (int) pos.x + (int) width, (int) pos.y + (int) height );
        blocker.draw( c );
    }

    public void update( Rect rect ) {
        pos.add(v);
        if ( pos.x + width >= CannonBallActivity.getScreenWidth() ) v.x *= -1;
        else if ( pos.x <= 0 ) v.x *= -1;
    }
}

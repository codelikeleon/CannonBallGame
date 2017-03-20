package com.example.leon.canonballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * Created by Leon on 20/03/2017.
 */

public class SpriteView extends View {

    private Paint textPaint;
    CannonBallActivity controller;

    static String TAG = "Cannonball Sprite View: ";

    public SpriteView( Context context ) {
        super( context );
        this.controller = ( CannonBallActivity ) context;
    }

    public SpriteView( Context context, AttributeSet attrs ) {
        super( context, attrs );
        this.controller = ( CannonBallActivity ) context;
    }

    public SpriteView( Context context, AttributeSet attrs, int defStyle ) {
        super( context, attrs, defStyle );
        this.controller = ( CannonBallActivity ) context;
    }

    public void onDraw( Canvas g ) {

        List<Sprite> sprites = controller.getModel().sprites;
        for ( Sprite sprite : sprites ) {
            sprite.draw( g );
        }

        textPaint = new Paint();
        textPaint.setTextSize(( int ) (50));
        textPaint.setAntiAlias( true ); // smooth the text
        textPaint.setARGB( 255, 255, 0, 0 );

        g.drawText( getResources().getString(
                R.string.time_remaining,
                controller.getModel().timeRemaining/1000 ), 50, 40, textPaint );
        g.drawText( getResources().getString(
                R.string.game_score, controller.getModel().score ), 50, 90,
                textPaint );
    }

    @Override
    public boolean onTouchEvent( MotionEvent event ) {

        float x = event.getX();
        float y = event.getY();
        controller.getModel().click( x, y );
        List<Sprite> sprites = controller.getModel().sprites;

        if ( event.getAction() ==
                MotionEvent.ACTION_DOWN ) {
            for ( Sprite s: sprites ) {
                if ( s.contains( x, y ) ) {
                    sprites.remove( s );
                    break;
                }
            }
        }

        return super.onTouchEvent( event );
    }
}

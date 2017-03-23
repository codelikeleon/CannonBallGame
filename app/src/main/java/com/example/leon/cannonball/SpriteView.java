package com.example.leon.cannonball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class SpriteView extends View {
    private Paint textPaint;
    CannonBallActivity controller;
    public Cannon cannon;
    public Blocker blocker;
    public Cannonball cannonball;
    public Drawable background;

    static String tag = "Bubble Sprite View: ";
    public void onDraw(Canvas g) {

        //Background
        background = getResources().getDrawable(R.drawable.beach_background);
        background.setBounds( 0, 0, CannonBallActivity.getScreenWidth(), CannonBallActivity.getScreenHeight() );
        background.draw( g );

        List<Sprite> sprites = controller.getModel().sprites;
        for (Sprite sprite : sprites) {
            sprite.draw(g);
        }

        blocker.draw( g );
        cannonball.draw( g );
        cannon.draw( g );

        textPaint = new Paint();
        textPaint.setTextSize((int) (30));
        textPaint.setAntiAlias(true); // smooth the text
        textPaint.setARGB(255, 255, 0, 0);

        g.drawText(getResources().getString(
                R.string.time_remaining_format,
                controller.getModel().timeRemaining/1000), 200, 30, textPaint);
        g.drawText(getResources().getString(
                R.string.score_format, controller.getModel().score), 10, 30,
                textPaint);

    }

    public SpriteView(Context context) {
        super(context);
        this.controller = (CannonBallActivity) context;
        controller.getModel().blocker = new Blocker( context );
        this.blocker = controller.getModel().blocker;
        controller.getModel().cannonball = new Cannonball( context );
        this.cannonball = controller.getModel().cannonball;
        controller.getModel().cannon = new Cannon( context );
        this.cannon = controller.getModel().cannon;
    }

    public SpriteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.controller = (CannonBallActivity) context;
        controller.getModel().blocker = new Blocker( context );
        this.blocker = controller.getModel().blocker;
        controller.getModel().cannonball = new Cannonball( context );
        this.cannonball = controller.getModel().cannonball;
        controller.getModel().cannon = new Cannon( context );
        this.cannon = controller.getModel().cannon;
    }

    public SpriteView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.controller = (CannonBallActivity) context;
        controller.getModel().blocker = new Blocker( context );
        this.blocker = controller.getModel().blocker;
        controller.getModel().cannonball = new Cannonball( context );
        this.cannonball = controller.getModel().cannonball;
        controller.getModel().cannon = new Cannon( context );
        this.cannon = controller.getModel().cannon;
    }

    @Override
    public boolean onTouchEvent( MotionEvent event ) {
        float x = event.getX();
        float y = event.getY();
        if ( !cannonball.firing ) CannonBallActivity.SP.play(CannonBallActivity.cannonSound, 1, 1, 0, 0, 1);
        controller.getModel().click( x, y );
        if (event.getAction() ==
                MotionEvent.ACTION_DOWN) {
            cannon.moveCannon( x );
            cannonball.moveCannonBall( x );
            cannonball.fireCannon();
        }
        return super.onTouchEvent(event);
    }
}

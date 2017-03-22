package com.example.leon.cannonball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class SpriteView extends View {
    private Paint textPaint;
    CannonBallActivity controller;

    static String tag = "Bubble Sprite View: ";
    public void onDraw(Canvas g) {

        // get the model
        List<Sprite> sprites = controller.getModel().sprites;
        // System.out.println(tag + "onDraw: " + sprites.get(0).v + " : " + sprites.get(0).s);
        for (Sprite sprite : sprites) {
            sprite.draw(g);
        }

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
    }

    public SpriteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.controller = (CannonBallActivity) context;
    }

    public SpriteView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.controller = (CannonBallActivity) context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        controller.getModel().click(x, y);
        List<Sprite> sprites = controller.getModel().sprites;
        if (event.getAction() ==
                MotionEvent.ACTION_DOWN) {
            for (Sprite s: sprites) {
                if (s.contains(x, y)) {
                    sprites.remove(s);
                    break;
                }
            }
        }
        return super.onTouchEvent(event);
    }
}

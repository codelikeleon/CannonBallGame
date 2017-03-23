package com.example.leon.cannonball;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

import static com.example.leon.cannonball.Constants.*;


public class Sprite {
    Vector2d s, v;
    float rad;
    Paint fg;

    static Random random = new Random();
    float width = CannonBallActivity.getScreenWidth() / 10;
    float height = CannonBallActivity.getScreenHeight() / 40;

    public Sprite(Paint fg) {
        this();
        this.fg = fg;
    }

    public Sprite() {
        s = new Vector2d();
        v = new Vector2d();
        reSpawn();
    }

    public void setPos( float x, float y ) {
        s.set( x, y );
    }

    public void reSpawn() {
//        rad = minRadius + random.nextInt(randSize);
        rad = (float) CannonBallActivity.getScreenHeight()/proportion +
                random.nextInt(40);
        s.set(0,0);
        v.set(velocityScale * (float) random.nextGaussian(),
                velocityScale * (float) random.nextGaussian());
    }

    public int getScore() {
        return fg == GameModel.paintMagenta ? magentaScore : greyScore;
    }

    public void update(Rect rect) {
//        s.add(v);
//        s.wrap(rect.width(), rect.height());
    }

    public boolean contains( float x, float y ) {
        return x >= s.x && x <= s.x + width && y >= s.y && y <= s.y + height;
    }

    public void draw(Canvas c) {
        c.drawRect(s.x, s.y, s.x + width, s.y + height, fg);
    }
}

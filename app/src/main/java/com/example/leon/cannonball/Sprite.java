package com.example.leon.cannonball;

import android.graphics.Canvas;
import android.graphics.Paint;

import static com.example.leon.cannonball.Constants.*;


public class Sprite {
    Vector2d s, v;
    Paint fg;

    float width = CannonBallActivity.getScreenWidth() / 10;
    float height = CannonBallActivity.getScreenHeight() / 40;

    public Sprite(Paint fg) {
        this();
        this.fg = fg;
    }

    public Sprite() {
        s = new Vector2d();
        v = new Vector2d();
    }

    public void setPos( float x, float y ) {
        s.set( x, y );
    }


    public int getScore() {
        if ( fg == GameModel.paintLightGrey ) return greyScore;
        else if (fg == GameModel.paintMagenta ) return magentaScore;
        else return yellowScore;
    }

    public boolean contains( float x, float y ) {
        return x >= s.x && x <= s.x + width && y >= s.y && y <= s.y + height;
    }

    public void draw( Canvas c ) {
        c.drawRect( s.x, s.y, s.x + width, s.y + height, fg );
    }
}

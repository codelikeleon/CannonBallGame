package com.example.leon.cannonball;

import android.graphics.Canvas;
import android.graphics.Paint;

import static com.example.leon.cannonball.Constants.*;

public class Sprite {
    Vector2d pos, v;
    Paint fg;

    float width = CannonBallActivity.getScreenWidth() / 10;
    float height = CannonBallActivity.getScreenHeight() / 25;

    public Sprite( Paint fg ) {
        this();
        this.fg = fg;
    }

    public Sprite() {
        pos = new Vector2d();
        v = new Vector2d( velocityScale * MainMenuActivity.LEVEL, 0 );
    }

    public void setPos( float x, float y ) {
        pos.set( x, y );
    }

    public void update() {
        pos.add( v );
        if ( pos.x + width > CannonBallActivity.getScreenWidth() ) v.x *= -1;
        else if ( pos.x < 0 ) v.x *= -1;
    }

    public int getScore() {
        if ( fg == GameModel.paintLightGrey ) return greyScore;
        else if ( fg == GameModel.paintMagenta ) return magentaScore;
        else return yellowScore;
    }

    public boolean contains( float x, float y ) {
        return x >= pos.x && x <= pos.x + width && y >= pos.y && y <= pos.y + height;
    }

    public void draw( Canvas c ) {
        c.drawRoundRect( pos.x, pos.y, pos.x + width, pos.y + height, 5, 5, fg );
    }
}

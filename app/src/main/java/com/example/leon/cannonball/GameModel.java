package com.example.leon.cannonball;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Leon on 18/03/2017.
 */

public class GameModel {

    ArrayList<Sprite> sprites;
    int numSprites = 10;
    int score;
    int timeRemaining = 10000;

    static Paint paintLightGrey, paintMagenta, paintBlack;

    static {
        paintLightGrey = new Paint();
        paintLightGrey.setColor( Color.GRAY );
        paintLightGrey.setStyle( Paint.Style.FILL );
        paintLightGrey.setAntiAlias( true );

        paintMagenta = new Paint();
        paintMagenta.setColor( Color.MAGENTA );
        paintMagenta.setStyle( Paint.Style.FILL );
        paintMagenta.setAntiAlias( true );

        paintBlack = new Paint();
        paintBlack.setColor( Color.BLACK );
        paintBlack.setStyle( Paint.Style.FILL );
        paintBlack.setAntiAlias( true );
    }

    public GameModel() {
        System.out.println( "GameModel: GameModel()" );
        initSprites();
        score = 0;
        System.out.println( "GameModel:  finished init()" );
    }

    public void update( Rect rectangle, int delay ) {

        // check that the drawing rectangle is valid
        if ( rectangle.width() <= 0 || rectangle.height() <= 0 ) return;

        if ( !gameOver() ) {
            for (Sprite sprite : sprites) sprite.update(rectangle);
            timeRemaining -= delay;
        }
    }

    public boolean gameOver() {
        return timeRemaining <= 0;
    }

    public void click( float x, float y ) {
        for ( Sprite s : sprites ) {
            if ( s.contains( x, y ) ) {
                score += s.getScore();
//                s.setPos(0, 0);
                sprites.remove( s );
//                s.respawn();
                return;
            }
        }
    }

    void initSprites() {
        sprites = new ArrayList<Sprite>();
        for ( int i = 0; i < numSprites; i++ ) {
            Paint p = i % 3 == 0 ? paintMagenta : paintLightGrey;
            Sprite sprite = new Sprite( p );
            //^Sets width according to screen width
                sprite.s.x = i + i * getSpacing();
                sprite.s.y = 30;
                sprites.add( sprite );
        }
    }

    float getSpacing() {
        return CannonBallActivity.getScreenWidth() / 10;
    }
}


/**
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class GameModel {
    ArrayList<Sprite> sprites;
    int nSprites = 20;
    int score;
    int timeRemaining = 10000;

    static Paint paintBlue, paintGreen;

    static {
        paintBlue = new Paint();
        paintBlue.setColor(Color.BLUE);
        paintBlue.setStyle(Paint.Style.STROKE);
        paintBlue.setAntiAlias(true);

        paintGreen = new Paint();
        paintGreen.setColor(Color.GREEN);
        paintGreen.setStyle(Paint.Style.FILL);
        paintGreen.setAntiAlias(true);
    }

    public void update(Rect rect, int delay) {
        // check that the drawing rectangle is valid
        if (rect.width() <= 0 || rect.height() <= 0) return;

        if (!gameOver()) {
            for (Sprite sprite : sprites) sprite.update(rect);
            timeRemaining -= delay;
        }
    }

    public boolean gameOver() {
        return timeRemaining <= 0;
    }

    public GameModel() {
        System.out.println("Bubble GameModel: GameModel()");
        initSprites();
        score = 0;
        System.out.println("Bubble GameModel:  finished in ()");
    }

    public void click(float x, float y) {
        for (Sprite s : sprites) {
            if (s.contains(x, y)) {
                score += s.getScore();
                s.reSpawn();
                return;
            }
        }
    }

    void initSprites() {
        sprites = new ArrayList<Sprite>();
        for (int i = 0; i < nSprites; i++) {
            Paint p = i % 2 == 0 ? paintBlue : paintGreen;
            Sprite sprite = new Sprite( p );
            sprite.s.x = i + i * getSpacing();
            sprite.s.y = 30;
            sprites.add( sprite );
            sprites.add( sprite );
        }
    }

    float getSpacing() {
        return CannonballActivity.getScreenWidth() / 10;
    }
}
**/
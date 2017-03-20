package com.example.leon.canonballgame;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

//import java.util.ArrayList;

/**
 * Created by Leon on 18/03/2017.
 */

public class GameModel {

    ArrayList<Sprite> sprites;
    int numSprites;
    int score;
    int timeRemaining = 10000;

    static Paint paintLightGrey, paintMagenta, paintBlack;

    static {
        paintLightGrey = new Paint();
        paintLightGrey.setColor(Color.LTGRAY);
        paintLightGrey.setStyle(Paint.Style.FILL);
        paintLightGrey.setAntiAlias(true);

        paintMagenta = new Paint();
        paintLightGrey.setColor(Color.MAGENTA);
        paintLightGrey.setStyle(Paint.Style.FILL);
        paintLightGrey.setAntiAlias(true);

        paintBlack = new Paint();
        paintBlack.setColor(Color.BLACK);
        paintBlack.setStyle(Paint.Style.FILL);
        paintBlack.setAntiAlias(true);
    }

    public GameModel() {
        System.out.println("GameModel: GameModel()");
        initSprites();
        score = 0;
        System.out.println("GameModel:  finished in ()");
    }

    public void update(Rect rectangle, int delay) {

        // check that the drawing rectangle is valid
        if (rectangle.width() <= 0 || rectangle.height() <= 0) return;

        if (!gameOver()) {
//            for (Sprite sprite : sprites) sprite.update(rectangle);
            timeRemaining -= delay;
        }
    }

    public boolean gameOver() {
        return timeRemaining <= 0;
    }

    public void click(float x, float y) {
        for (Sprite s : sprites) {
            if (s.contains(x, y)) {
                score += s.getScore();
                s.respawn();
                return;
            }
        }
    }

    void initSprites() {
        sprites = new ArrayList<Sprite>();
        for (int i = 0; i < numSprites; i++) {
            Paint p = i % 2 == 0 ? paintLightGrey : paintMagenta;
            sprites.add(new Sprite(p));
        }
    }
}

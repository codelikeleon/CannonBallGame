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
    int numSprites = 50;
    int score;
    int timeRemaining = 10000;

    static Paint paintLightGrey, paintMagenta, paintBlack;
    public Cannon cannon;

    static {
        paintLightGrey = new Paint();
        paintLightGrey.setColor(Color.GRAY);
        paintLightGrey.setStyle(Paint.Style.FILL_AND_STROKE);
        paintLightGrey.setAntiAlias(true);

        paintMagenta = new Paint();
        paintMagenta.setColor(Color.MAGENTA);
        paintMagenta.setStyle(Paint.Style.FILL_AND_STROKE);
        paintMagenta.setAntiAlias(true);

        paintBlack = new Paint();
        paintBlack.setColor(Color.BLACK);
        paintBlack.setStyle(Paint.Style.FILL);
        paintBlack.setAntiAlias(true);
    }

    public GameModel() {
        System.out.println("GameModel: GameModel()");
        initSprites();
        score = 0;
        System.out.println("GameModel:  finished init()");
    }

    public void update(Rect rectangle, int delay) {

        // check that the drawing rectangle is valid
        if (rectangle.width() <= 0 || rectangle.height() <= 0) return;

        if (!gameOver()) {
            for (Sprite sprite : sprites) sprite.update(rectangle);
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
//                s.setPos(0, 0);
                sprites.remove(s);
//                s.respawn();
                return;
            }
        }
    }

    void initSprites() {
        sprites = new ArrayList<Sprite>();
        for (int i = 0; i < numSprites; i++) {
            Paint p = i % 3 == 0 ? paintMagenta : paintLightGrey;   //TODO randomise block generation + add multiple colours?
            Sprite sprite = new Sprite(p);
            if (i >= 40) {
                sprite.setPos((i - 40) * sprite.width, 40 + sprite.height * 4);
                sprites.add(sprite);
            } else if (i >= 30) {
                sprite.setPos((i - 30) * sprite.width, 40 + sprite.height * 3);
                sprites.add(sprite);
            } else if (i >= 20) {
                sprite.setPos((i - 20) * sprite.width, 40 + sprite.height * 2);
                sprites.add(sprite);
            } else if (i >= 10) {
                sprite.setPos((i - 10) * sprite.width, 40 + sprite.height);
                sprites.add(sprite);
            } else {
                sprite.setPos(i * sprite.width, 40);
                sprites.add(sprite);
            }
        }
    }
}

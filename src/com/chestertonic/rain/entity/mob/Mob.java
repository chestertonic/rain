package com.chestertonic.rain.entity.mob;

import com.chestertonic.rain.entity.Entity;
import com.chestertonic.rain.graphics.Screen;
import com.chestertonic.rain.graphics.Sprite;

/**
 * Created by slinkee on 11/5/16.
 */
public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0;  // 0 = North, 1 = East, 2 = South, 3 = West
    protected boolean moving = false;

    public void move(int xa, int ya) {
        if (xa > 0) dir = 1;  // East
        if (xa < 0) dir = 3;  // West
        if (ya > 0) dir = 2;  // South
        if (ya < 0) dir = 0;  // North

        if (!collision()) {
            x += xa;
            y += ya;
        }

    }
    @Override
    public void update() {

    }
    @Override
    public void render(Screen screen) {

    }
    private boolean collision() {
        return false;
    }
}

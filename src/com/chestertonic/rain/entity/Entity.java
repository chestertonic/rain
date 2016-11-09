package com.chestertonic.rain.entity;

import com.chestertonic.rain.graphics.Screen;
import com.chestertonic.rain.level.Level;

import java.util.Random;

/**
 * Created by slinkee on 11/5/16.
 */
public abstract class Entity {

    public int x, y;
    private boolean removed = false;    // removed from level?
    protected Level level;
    protected final Random random = new Random();

    public void update() {

    }

    public void render(Screen screen) {

    }

    public void remove() {
        // remove entity
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }
}

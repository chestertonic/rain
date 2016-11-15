package com.chestertonic.rain.entity.projectile;

import com.chestertonic.rain.entity.Entity;
import com.chestertonic.rain.graphics.Sprite;

/**
 * Created by Chester on 11/12/2016.
 */
public abstract class Projectile extends Entity {

    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double x, y, nx, ny;
    protected double speed, range, damage;

    public Projectile(int x, int y, double dir) {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {}

    protected void move() {}
}

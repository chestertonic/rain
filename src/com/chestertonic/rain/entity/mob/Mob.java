package com.chestertonic.rain.entity.mob;

import com.chestertonic.rain.entity.Entity;
import com.chestertonic.rain.entity.particle.Particle;
import com.chestertonic.rain.entity.projectile.Projectile;
import com.chestertonic.rain.entity.projectile.WizardProjectile;
import com.chestertonic.rain.graphics.Screen;
import com.chestertonic.rain.graphics.Sprite;

/**
 * Created by slinkee on 11/5/16.
 */

abstract class Mob extends Entity {

    Sprite sprite;
    int dir = 0;  // 0 = North, 1 = East, 2 = South, 3 = West
    protected boolean moving = false;


    void move(int xa, int ya) {
        if (xa > 0) dir = 1;  // East
        if (xa < 0) dir = 3;  // West
        if (ya > 0) dir = 2;  // South
        if (ya < 0) dir = 0;  // North

        if (!collision(xa, ya)) {
            x += xa;
            y += ya;
        }
    }

    @Override
    public void update() {}

    @Override
    public void render(Screen screen) {}

    void shoot(int x, int y, double dir) {
        Projectile p = new WizardProjectile(x, y, dir);
        level.add(p);
    }

    private boolean collision(int xa, int ya) {
        for (int c = 0; c < 4; c++) {
            if (level.getTile((((x + xa) + c % 2 * 12 - 7) / 16), (((y + ya) + c / 2 * 12 + 3) / 16)).isSolid())
                return true;
        }
        return false;
    }

    public int getDir() {
        return dir;
    }
}

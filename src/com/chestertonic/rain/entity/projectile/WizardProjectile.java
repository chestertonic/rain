package com.chestertonic.rain.entity.projectile;

import com.chestertonic.rain.graphics.Screen;
import com.chestertonic.rain.graphics.Sprite;

/**
 * Created by Chester on 11/12/2016.
 */
public class WizardProjectile extends Projectile {

    public static final double FIRE_RATE = 10;

    public WizardProjectile(int x, int y, double dir) {
        super(x, y, dir);
        range = 200;
        speed = 4;
        damage = 20;
        sprite = Sprite.projectile_wizard;

        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    @Override
    public void update() {
        move();
    }

    @Override
    protected void move() {
        if (collision(nx, ny) || distance() > range) {
            remove();
        } else {
            x += nx;
            y += ny;
        }
        //System.out.println("Distance: " + distance());
    }

    @Override
    public void render(Screen screen) {
        screen.render((int) x, (int) y, sprite);
    }


    private boolean collision(double xa, double ya) {
        for (int c = 0; c < 4; c++) {
            if (level.getTile((((int) (x + xa) + c % 2 * sprite.width() / 2 + 4) / 16),
                              (((int) (y + ya) + c / 2 * sprite.height() / 2 + 4) / 16)).isSolid())
                return true;
        }
        return false;
    }

    private double distance() {
        return Math.sqrt((x - xOrigin) * (x - xOrigin) + (yOrigin - y) * (yOrigin - y));
    }
}

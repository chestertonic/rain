package com.chestertonic.rain.entity.particle;

import com.chestertonic.rain.entity.Entity;
import com.chestertonic.rain.graphics.Screen;
import com.chestertonic.rain.graphics.Sprite;

/**
 * Created by Chester on 11/15/2016.
 */
public class Particle extends Entity {

    private Sprite sprite;

    private int life;

    protected double xx, yy, xa, ya;

    public Particle(int x, int y, int life) {
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;
        this.life = life;
        sprite = Sprite.particles_normal;

        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
    }

    @Override
    public void update() {
        this.xx += xa;
        this.yy += ya;
    }

    @Override
    public void render(Screen screen) {
        screen.render((int) xx, (int) yy, sprite);
    }
}

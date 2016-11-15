package com.chestertonic.rain.entity.mob;

import com.chestertonic.rain.entity.projectile.Projectile;
import com.chestertonic.rain.entity.projectile.WizardProjectile;
import com.chestertonic.rain.graphics.Screen;
import com.chestertonic.rain.graphics.Sprite;
import com.chestertonic.rain.input.KeyInput;
import com.chestertonic.rain.input.MouseInput;
import com.chestertonic.rain.level.Level;

import java.awt.event.MouseEvent;


/**
 * Created by slinkee on 11/5/16.
 */

public class Player extends Mob {

    private KeyInput input;
    private int anim = 0;
    private boolean walking = false;
    private double fireRate;

    public Player(KeyInput input) {
        this.input = input;
        sprite = Sprite.player_back0;
    }

    public Player(int x, int y, KeyInput input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_back0;
        this.fireRate = WizardProjectile.FIRE_RATE;
    }

    
    /*todo add mouse event update for projectiles*/

    public void update(int windowWidth, int windowHeight) {
        if (fireRate > 0) fireRate--;
        int xa = 0, ya = 0;
        anim = (anim < 120) ? anim + 1 : 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;


        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
        } else if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else
            walking = false;
        if (MouseInput.isDown(MouseEvent.BUTTON1) && (fireRate <= 0))
            readyProjectile(windowWidth, windowHeight);
    }

    private void readyProjectile(int windowWidth, int windowHeight) {
        double dx = MouseInput.getX() - windowWidth / 2;
        double dy = MouseInput.getY() - windowHeight / 2;
        double dir = Math.atan2(dy, dx);
        if (this.dir == 3 || this.dir == 2)
            shoot(x - 15, y, dir);
        else
            shoot(x, y, dir);
        fireRate = WizardProjectile.FIRE_RATE;
    }

    public void render(Screen screen) {
        if (dir == 0) {
            if (walking) {
                if (anim % 40 > 30)
                    sprite = Sprite.player_forward1;
                else if (anim % 40 > 20)
                    sprite = Sprite.player_forward0;
                else if (anim % 40 > 10)
                    sprite = Sprite.player_forward2;
                else
                    sprite = Sprite.player_forward0;
            } else
                sprite = Sprite.player_forward0;
            sprite.setFlip(0);
        }
        if (dir == 1) {
            if (walking) {
                if (anim % 40 > 30)
                    sprite = Sprite.player_side1;
                else if (anim % 40 > 20)
                    sprite = Sprite.player_side0;
                else if (anim % 40 > 10)
                    sprite = Sprite.player_side2;
                else
                    sprite = Sprite.player_side0;
            } else
                sprite = Sprite.player_side0;
            sprite.setFlip(0);
        }
        if (dir == 2) {
            if (walking) {
                if (anim % 40 > 30)
                    sprite = Sprite.player_back1;
                else if (anim % 40 > 20)
                    sprite = Sprite.player_back0;
                else if (anim % 40 > 10)
                    sprite = Sprite.player_back2;
                else
                    sprite = Sprite.player_back0;
            } else
                sprite = Sprite.player_back0;
            sprite.setFlip(0);
        }
        if (dir == 3) {
            if (walking) {
                if (anim % 40 > 30)
                    sprite = Sprite.player_side1;
                else if (anim % 40 > 20)
                    sprite = Sprite.player_side0;
                else if (anim % 40 > 10)
                    sprite = Sprite.player_side2;
                else
                    sprite = Sprite.player_side0;
            } else
                sprite = Sprite.player_side0;
            sprite.setFlip(1);
        }
        screen.render(x - 16, y - 16, sprite);
    }
}

package com.chestertonic.rain.entity.mob;


import com.chestertonic.rain.graphics.Screen;
import com.chestertonic.rain.graphics.Sprite;
import com.chestertonic.rain.input.KeyInput;

/**
 * Created by slinkee on 11/5/16.
 */
public class Player extends Mob {
    private KeyInput input;
    private int anim = 0;
    private boolean walking = false;

    public Player(KeyInput input) {
        this.input = input;
        sprite = Sprite.player_back0;
    }

    public Player(int x, int y, KeyInput input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_back0;
    }

    @Override
    public void update() {
        int xa = 0, ya = 0;
        anim = (anim < 120) ? anim + 1 : 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else
            walking = false;
    }

    public void render(Screen screen) {
        int flip = 0;
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
            flip = 1;
        }
        /*switch (dir) {
            case 0:
                sprite = Sprite.player_forward0;
                break;
            case 1:
                sprite = Sprite.player_side0;
                break;
            case 2:
                sprite = Sprite.player_back0;
                break;
            case 3:
                sprite = Sprite.player_side0;
                flip = 1;
                break;
        }*/
        screen.render(x - 16, y - 16, sprite, flip);

    }
}

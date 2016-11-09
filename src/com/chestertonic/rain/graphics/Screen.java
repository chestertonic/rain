package com.chestertonic.rain.graphics;

import com.chestertonic.rain.entity.mob.Player;
import com.chestertonic.rain.level.tile.Tile;



/**
 * Created by slinkee on 11/1/16.
 */
public class Screen {
    public int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 8;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;

    public int xOffset, yOffset;



    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

    }

    public void clear() {
        /*int len = pixels.length;
        if (len > 0)
            pixels[0] = 0;
        for (int i = 1; i < len; i += i) {
            System.arraycopy(pixels, 0, pixels, i, ((len - i) < i) ? (len - i) : i);
        }*/
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

   /* public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -tile.sprite.SIZE || xa >= width  || ya < 0 || ya >= height) break; // only render tiles that are visible on screen
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }*/

    public void render(int xp, int yp, Sprite sprite, int flip) {
        xp -= xOffset;
        yp -= yOffset;
        int ys, xs;
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            ys = (flip == 2 || flip == 3) ? (sprite.SIZE - 1) - y : y;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp;
                xs = (flip == 1 || flip == 3) ? (sprite.SIZE - 1) - x : x;
                if (xa < -sprite.SIZE || xa >= width  || ya < 0 || ya >= height) break; // only render tiles that are visible on screen
                if (xa < 0) xa = 0;
                int col = sprite.pixels[xs + ys * sprite.SIZE];
                if (col != 0xffff00ff)
                    pixels[xa + ya * width] = col;
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}

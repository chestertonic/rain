package com.chestertonic.rain.graphics;


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
        int len = pixels.length;
        if (len > 0)
            pixels[0] = 0;
        for (int i = 1; i < len; i += i) {
            System.arraycopy(pixels, 0, pixels, i, ((len - i) < i) ? (len - i) : i);
        }
        /*for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }*/
    }


    public void render(int xp, int yp, Sprite sprite) {
        if (!sprite.isFixed()) {
            xp -= xOffset;
            yp -= yOffset;
        }
        int ys, xs;
        for (int y = 0; y < sprite.height(); y++) {
            int ya = y + yp;
            ys = (sprite.flip() == 2 || sprite.flip() == 3) ? (sprite.height() - 1) - y : y;
            for (int x = 0; x < sprite.width(); x++) {
                int xa = x + xp;
                xs = (sprite.flip() == 1 || sprite.flip() == 3) ? (sprite.width() - 1) - x : x;
                if (xa < -sprite.width() || xa >= width || ya < 0 || ya >= height)
                    break; // only render tiles that are visible on screen
                if (xa < 0) xa = 0;
                //int col = sprite.pixels()[xs + ys * sprite.size()];
                int col = sprite.getColorAt(xs, ys);
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

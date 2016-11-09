package com.chestertonic.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by slinkee on 11/2/16.
 */
public class SpriteSheet {

    private String path;
    public final int SIZE;
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
    public static SpriteSheet spawn_tiles = new SpriteSheet("/textures/sheets/spawn_level.png", 48);

    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

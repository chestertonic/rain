package com.chestertonic.rain.graphics;

/**
 * Created by slinkee on 11/2/16.
 */
public class Sprite {
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    // Main Sprite Sheet
    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite path = new Sprite(16, 4, 1, SpriteSheet.tiles);
    public static Sprite pathL = new Sprite(16, 3, 1, SpriteSheet.tiles);
    public static Sprite pathR = new Sprite(16, 5, 1, SpriteSheet.tiles);
    public static Sprite pathT = new Sprite(16, 4, 0, SpriteSheet.tiles);
    public static Sprite pathTL = new Sprite(16, 3, 0, SpriteSheet.tiles);
    public static Sprite pathTR = new Sprite(16, 5, 0, SpriteSheet.tiles);
    public static Sprite pathB = new Sprite(16, 4, 2, SpriteSheet.tiles);
    public static Sprite pathBL = new Sprite(16, 3, 2, SpriteSheet.tiles);
    public static Sprite pathBR = new Sprite(16, 5, 2, SpriteSheet.tiles);
    public static Sprite water = new Sprite(16, 7, 1, SpriteSheet.tiles);
    public static Sprite waterL = new Sprite(16, 6, 1, SpriteSheet.tiles);
    public static Sprite waterR = new Sprite(16, 8, 1, SpriteSheet.tiles);
    public static Sprite waterT = new Sprite(16, 7, 0, SpriteSheet.tiles);
    public static Sprite waterTL = new Sprite(16, 6, 0, SpriteSheet.tiles);
    public static Sprite waterTR = new Sprite(16, 8, 0, SpriteSheet.tiles);
    public static Sprite waterB = new Sprite(16, 7, 2, SpriteSheet.tiles);
    public static Sprite waterBL = new Sprite(16, 6, 2, SpriteSheet.tiles);
    public static Sprite waterBR = new Sprite(16, 8, 2, SpriteSheet.tiles);

    // Spawn Level Sprites
    public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_tiles);
    public static Sprite spawn_hedge = new Sprite(16, 1, 0, SpriteSheet.spawn_tiles);
    public static Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_tiles);
    public static Sprite spawn_wall1 = new Sprite(16, 0, 1, SpriteSheet.spawn_tiles);
    public static Sprite spawn_wall2 = new Sprite(16, 0, 2, SpriteSheet.spawn_tiles);
    public static Sprite spawn_floor1 = new Sprite(16, 1, 1, SpriteSheet.spawn_tiles);
    public static Sprite spawn_floor2 = new Sprite(16, 1, 2, SpriteSheet.spawn_tiles);
    public static Sprite spawn_water_floor = new Sprite(16, 2, 1, SpriteSheet.spawn_tiles);

    // Void Sprite
    public static Sprite voidSprite = new Sprite(16, 0x6280ff);

    // Player Sprite 32x32 grabs 5th 32x32 sprite down == 10th 16x16 sprite down
    public static Sprite player_back0 = new Sprite(32, 2, 5, SpriteSheet.tiles);
    public static Sprite player_back1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
    public static Sprite player_back2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
    public static Sprite player_forward0 = new Sprite(32, 0, 5, SpriteSheet.tiles);
    public static Sprite player_forward1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
    public static Sprite player_forward2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
    public static Sprite player_side0 = new Sprite(32, 1, 5, SpriteSheet.tiles);
    public static Sprite player_side1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
    public static Sprite player_side2 = new Sprite(32, 1, 7, SpriteSheet.tiles);


    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    public Sprite(int size, int color) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = color;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }
}

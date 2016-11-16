package com.chestertonic.rain.graphics;


/**
 * The Sprite class holds the sub-image of a {@link SpriteSheet}. This is a basic class to be used to represent any image
 * to be rendered in the game. Currently only supports images of equal x/y dimensions.
 * <p>
 * Created by slinkee on 11/2/16.
 */
public class Sprite {

    public enum Flip {
        NONE, VERTICAL, HORIZONTAL, BOTH
    }

    /**
     * Width of {@code Sprite}.
     */
    private final int WIDTH;

    /**
     * Height of {@code Sprite}.
     */
    private final int HEIGHT;

    /**
     * The x coordinate of {@code Sprite} in {@code sheet}.
     */
    private int x;

    /**
     * The y coordinate of {@code Sprite} int {@code sheet}.
     */
    private int y;

    /**
     * Determines the transformation of the rendered {@code Sprite}.
     * <p>
     * 0 no transformation
     * 1 render {@code Sprite} with pixels flipped over Y axis
     * 2 render {@code Sprite} with pixels flipped over X axis
     * 3 render {@code Sprite} with pixels flipped over X and Y axis
     */
    private int flip;

    /**
     * Determines if {@code Sprite} is fixed on {@link Screen}.
     * <p>
     * true {@code Sprite} is fixed.
     * false {@code Sprite} is not fixed.
     */
    private boolean fixed;

    /**
     * Array of {@code pixels} representing the {@code Sprite} image.
     */
    private int[] pixels;

    /**
     * The SpriteSheet in which the sprite belongs to.
     */
    private SpriteSheet sheet;

    // todo create a SpriteSheet loader class to manage SpriteSheet resources.

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
    //public static Sprite voidSprite = new Sprite(16, 0x6280ff);
    public static Sprite voidSprite = new Sprite(16, 0xff00ff);

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

    // Projectiles
    public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);

    // Particles
    public static Sprite particles_normal = new Sprite(2, 0xaaffaa);

    /**
     * Creates a new instance of {@code Sprite} with equal x/y dimensions.
     *
     * @param size  the x/y dimensions of the {@code Sprite}.
     * @param x     the x coordinate of the {@code Sprite} in {@code sheet}.
     * @param y     the y coordinate of the {@code Sprite} in {@code sheet}.
     * @param sheet the {@link SpriteSheet} that owns image.
     */
    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        WIDTH = size;
        HEIGHT = size;
        pixels = new int[WIDTH * HEIGHT];
        this.x = x * WIDTH;
        this.y = y * HEIGHT;
        this.sheet = sheet;
        flip = 0;
        fixed = false;
        load();
    }

    /**
     * Creates a new instance of {@code Sprite}.
     *
     * @param width  the width of the {@code Sprite}.
     * @param height the height of the {@code Sprite}.
     * @param x     the x coordinate of the {@code Sprite} in {@code sheet}.
     * @param y     the y coordinate of the {@code Sprite} in {@code sheet}.
     * @param sheet the {@link SpriteSheet} that owns image.
     */
    public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
        WIDTH = width;
        HEIGHT = height;
        pixels = new int[WIDTH * HEIGHT];
        this.x = x * WIDTH;
        this.y = y * HEIGHT;
        this.sheet = sheet;
        flip = 0;
        fixed = false;
        load();
    }

    /**
     * Creates a new instance of {@code Sprite} of a single color with equal x/y dimensions.
     *
     * @param size  the x/y dimensions of the {@code Sprite}.
     * @param color the hex color value of the {@code Sprite}.
     */
    public Sprite(int size, int color) {
        WIDTH = size;
        HEIGHT = size;
        pixels = new int[WIDTH * HEIGHT];
        flip = 0;
        fixed = false;
        setColor(color);
    }

    /**
     * Creates a new instance of {@code Sprite} of a single color.
     *
     * @param width  the width dimensions of the {@code Sprite}.
     * @param height the height dimensions of the {@code Sprite}.
     * @param color the hex color value of the {@code Sprite}.
     */
    public Sprite(int width, int height, int color) {
        WIDTH = width;
        HEIGHT = height;
        pixels = new int[WIDTH * HEIGHT];
        flip = 0;
        fixed = false;
        setColor(color);
    }

    /**
     * Sets color of entire {@code Sprite}
     *
     * @param color the hex color value
     */
    public void setColor(int color) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = color;
        }
    }

    /**
     * Sets color of pixel.
     *
     * @param x     the x coordinate of the pixel
     * @param y     the y coordinate of the pixel
     * @param color the hex color value
     */
    public void setColorAt(int x, int y, int color) {
        pixels[x + y * WIDTH] = color;
    }

    /**
     * Gets color of pixel.
     *
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @return hex color value
     */
    public int getColorAt(int x, int y) {
        return pixels[x + y * WIDTH];
    }

    /**
     * Loads image from {@code sheet} into {@code pixels}.
     */
    private void load() {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                pixels()[x + y * width()] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }

    /**
     * Returns the {@code WIDTH} of the {@code Sprite}.
     *
     * @return the width of {@code Sprite}
     */
    public int width() {
        return WIDTH;
    }

    /**
     * Returns the {@code HEIGHT} of the {@code Sprite}.
     *
     * @return the height of {@code Sprite}
     */
    public int height() {
        return HEIGHT;
    }

    /**
     * Returns the pixels array of the {@code Sprite}.
     *
     * @return the pixels array
     */
    public int[] pixels() {
        return pixels;
    }

    /**
     * Sets value of {@code flip} variable to determine transformation of {@code Sprite}.
     *
     * @param flip integer value
     */
    public void setFlip(int flip) {
        this.flip = flip;
    }

    /**
     * Returns value of {@code flip} variable to determine transformation of {@code Sprite}.
     *
     * @return the value of flip
     */
    public int flip() {
        return flip;
    }

    /**
     * Sets value of {@code fixed} variable to determine position of {@code Sprite}.
     *
     * @param fixed integer value
     */
    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    /**
     * Returns value of {@code fixed} variable to determine position of {@code Sprite}.
     *
     * @return the value of fixed
     */
    public boolean isFixed() {
        return fixed;
    }
}

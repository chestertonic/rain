package com.chestertonic.rain.level.tile;

import com.chestertonic.rain.graphics.Screen;
import com.chestertonic.rain.graphics.Sprite;

/**
 * Created by slinkee on 11/4/16.
 */
public class Tile {

    public int x, y;
    public Sprite sprite;
    private boolean solid;

    // todo create a Tile loader (or combined resource loader for Sprites as well) to handle Tile resources

    // Main Sprite Sheet Tiles
    public static Tile grass = new Tile(Sprite.grass, false);
    public static Tile flower = new Tile(Sprite.flower, false);
    public static Tile rock = new Tile(Sprite.rock, true);
    public static Tile path = new Tile(Sprite.path, false);
    public static Tile pathL = new Tile(Sprite.pathL, false);
    public static Tile pathR = new Tile(Sprite.pathR, false);
    public static Tile pathT = new Tile(Sprite.pathT, false);
    public static Tile pathTL = new Tile(Sprite.pathTL, false);
    public static Tile pathTR = new Tile(Sprite.pathTR, false);
    public static Tile pathB = new Tile(Sprite.pathB, false);
    public static Tile pathBL = new Tile(Sprite.pathBL, false);
    public static Tile pathBR = new Tile(Sprite.pathBR, false);
    public static Tile water = new Tile(Sprite.water, false);
    public static Tile waterL = new Tile(Sprite.waterL, false);
    public static Tile waterR = new Tile(Sprite.waterR, false);
    public static Tile waterT = new Tile(Sprite.waterT, false);
    public static Tile waterTL = new Tile(Sprite.waterTL, false);
    public static Tile waterTR = new Tile(Sprite.waterTR, false);
    public static Tile waterB = new Tile(Sprite.waterB, false);
    public static Tile waterBL = new Tile(Sprite.waterBL, false);
    public static Tile waterBR = new Tile(Sprite.waterBR, false);
    public static Tile voidTile = new Tile(Sprite.voidSprite, false);

    // Spawn Level Tiles
    public static Tile spawn_grass = new Tile(Sprite.spawn_grass, false);
    public static Tile spawn_hedge = new Tile(Sprite.spawn_hedge, true);
    public static Tile spawn_water = new Tile(Sprite.spawn_water, false);
    public static Tile spawn_wall1 = new Tile(Sprite.spawn_wall1, true);
    public static Tile spawn_wall2 = new Tile(Sprite.spawn_wall2, true);
    public static Tile spawn_floor1 = new Tile(Sprite.spawn_floor1, false);
    public static Tile spawn_floor2 = new Tile(Sprite.spawn_floor2, false);
    public static Tile spawn_water_floor = new Tile(Sprite.spawn_water_floor, false);

    public static final int col_spawn_grass = 0xFF00FF00;
    //public static final int col_spawn_hedge = 0;
    //public static final int col_spawn_water = 0;
    public static final int col_spawn_wall1 = 0xFF060606;
    public static final int col_spawn_wall2 = 0xFF4B4B4B;
    public static final int col_spawn_floor2 = 0xFF888844;


    public Tile(Sprite sprite, boolean solid) {
        this.sprite = sprite;
        this.solid = solid;
    }

    public void render(int x, int y, Screen screen) {
        screen.render(x * 16, y * 16, this.sprite);
    }

    public boolean isSolid() {
        return solid;
    }
}

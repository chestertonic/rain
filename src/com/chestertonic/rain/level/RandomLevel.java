package com.chestertonic.rain.level;

import com.chestertonic.rain.level.tile.Tile;

import java.util.Random;

/**
 * Created by slinkee on 11/4/16.
 */
public class RandomLevel extends Level {

    private static final Random random = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    @Override
    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = random.nextInt(4);
            }
        }
    }

    @Override
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        else if (tiles[x + y * width] == 0) return Tile.grass;
        else if (tiles[x + y * width] == 1) return Tile.flower;
        else if (tiles[x + y * width] == 2) return Tile.rock;
        else return Tile.voidTile;
    }

}

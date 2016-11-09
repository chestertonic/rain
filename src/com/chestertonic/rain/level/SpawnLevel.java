package com.chestertonic.rain.level;

import com.chestertonic.rain.level.tile.Tile;

/**
 * Created by slinkee on 11/6/16.
 */
public class SpawnLevel extends Level {

    public SpawnLevel(String path) {
        super(path);
    }

    @Override
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        switch (tiles[x + y * width]) {
            case Tile.col_spawn_grass:
                return Tile.spawn_grass;
            case Tile.col_spawn_wall1:
                return Tile.spawn_wall1;
            case Tile.col_spawn_wall2:
                return Tile.spawn_wall2;
            case Tile.col_spawn_floor2:
                return Tile.spawn_floor2;
            default:
                return Tile.voidTile;
        }
    }
}


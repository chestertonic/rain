package com.chestertonic.rain.level;

import com.chestertonic.rain.graphics.Screen;
import com.chestertonic.rain.level.tile.Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by slinkee on 11/4/16.
 */
public class Level {

    protected int width, height;
    protected int[] tiles;

    public static Level spawn = new SpawnLevel("/levels/spawn.png");
    public static Level level1 = new Level("/levels/map1.png");

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    protected void generateLevel() {

    }

    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(Level.class.getResource(path));
            this.width = image.getWidth();
            this.height = image.getHeight();
            tiles = new int[width * height];
            image.getRGB(0, 0, width, height, tiles, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Exception! Could not load level files!");
        }
    }

    public void update() {

    }

    private void time() {

    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        switch (tiles[x + y * width]) {
            case 0xFF00FF00:
                return Tile.grass;
            case 0xFFFFFF00:
                return Tile.flower;
            case 0xFF7F7F00:
                return Tile.rock;
            case 0xFF000000:
                return Tile.path;
            case 0xFF111111:
                return Tile.pathL;
            case 0xFF222222:
                return Tile.pathR;
            case 0xFF333333:
                return Tile.pathT;
            case 0xFF444444:
                return Tile.pathTL;
            case 0xFF555555:
                return Tile.pathTR;
            case 0xFF666666:
                return Tile.pathB;
            case 0xFF777777:
                return Tile.pathBL;
            case 0xFF888888:
                return Tile.pathBR;
            case 0xFF0000FF:
                return Tile.water;
            case 0xFF1111FF:
                return Tile.waterL;
            case 0xFF2222FF:
                return Tile.waterR;
            case 0xFF3333FF:
                return Tile.waterT;
            case 0xFF4444FF:
                return Tile.waterTL;
            case 0xFF5555FF:
                return Tile.waterTR;
            case 0xFF6666FF:
                return Tile.waterB;
            case 0xFF7777FF:
                return Tile.waterBL;
            case 0xFF9999FF:
                return Tile.waterBR;
            default:
                return Tile.voidTile;
        }
    }
}

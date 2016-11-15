package com.chestertonic.rain.level;


import javafx.beans.NamedArg;

/**
 * The PointTile class represents the coordinates of a "tile" in 2D coordinate space. This allows the user to represent
 * the coordinates of a tile in the game level rather than the coordinates of an individual pixel.
 * <p>
 * Created by Chester on 11/11/2016
 */
public class PointTile {

    /**
     * Tile resolution of a tile in pixels.
     */
    private static final int SIZE = 16;

    /**
     * The x coordinate of the tile.
     */
    private int x;

    /**
     * The x coordinate of the tile.
     *
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * The y coordinate of the tile.
     */
    private int y;

    /**
     * The y coordinate of the tile.
     *
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }


    /**
     * Creates a new instance of {@code PointTile}.
     *
     * @param x the x coordinate of the point in "tile" precision.
     * @param y the y coordinate of the point in "tile" precision.
     */
    public PointTile(@NamedArg("x") int x, @NamedArg("y") int y) {
        this.x = x * SIZE;
        this.y = y * SIZE;
    }

}


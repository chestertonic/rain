package com.chestertonic.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by slinkee on 11/1/16.
 */
public class KeyInput implements KeyListener {
    private static final int NUM_KEYS = 256;
    private static final boolean[] keys = new boolean[NUM_KEYS];
    private static final boolean[] lastKeys = new boolean[NUM_KEYS];

    public boolean up, down, left, right;

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            lastKeys[i] = keys[i];
        }
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];

        /*for (int i = 0; i < keys.length; i++) {
            if (keys[i])
                System.out.println("KEY: " + i);
        }*/
    }

    public static boolean isDown(int keyCode) {
        return keys[keyCode];
    }

    public static boolean wasPressed(int keyCode) {
        return isDown(keyCode) && !lastKeys[keyCode];
    }

    public static boolean wasReleased(int keyCode) {
        return !isDown(keyCode) && lastKeys[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = false;
    }
}

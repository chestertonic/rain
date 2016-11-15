package com.chestertonic.rain.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Chester on 11/11/2016.
 */
public class MouseInput implements MouseListener, MouseMotionListener {

    private static final int NUM_BUTTONS = 8;
    private static final boolean[] buttons = new boolean[NUM_BUTTONS];
    private static final boolean[] lastButtons = new boolean[NUM_BUTTONS];
    private static int x = -1;
    private static int y = -1;
    //private static int button = -1;
    public boolean button1, button2, button3;

    public void update() {
        System.arraycopy(buttons, 0, lastButtons, 0, NUM_BUTTONS);

        button1 = buttons[MouseEvent.BUTTON1];
        button2 = buttons[MouseEvent.BUTTON2];
        button3 = buttons[MouseEvent.BUTTON3];
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static boolean isDown(int button) {
        return buttons[button];
    }

    public static boolean wasPressed(int button) {
        return isDown(button) && !lastButtons[button];
    }

    public static boolean wasReleased(int button) {
        return !isDown(button) && lastButtons[button];
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //button = e.getButton();
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //button = e.getButton();
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}

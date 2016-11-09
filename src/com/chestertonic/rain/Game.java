package com.chestertonic.rain;

import com.chestertonic.rain.entity.mob.Player;
import com.chestertonic.rain.graphics.Screen;
import com.chestertonic.rain.input.KeyInput;
import com.chestertonic.rain.level.Level;
import com.chestertonic.rain.level.SpawnLevel;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

/**
 * Created by slinkee on 10/31/16.
 */
public class Game extends Canvas implements Runnable {


    private static int width = 300;
    private static int height = (int)((double)width / 16 * 9);  // aspect ratio 16:9
    private static int scale = 3;    // scale window to 3 times rendered width/height
    private String TITLE = "Rain";

    private Thread thread;
    private Screen screen;
    private JFrame frame;
    private KeyInput keyInput;
    private Level level;
    private Player player;
    private BufferedImage image; // rendered image to disply on screen
    private int[] pixels;        // manipulate pixel on buffered image

    private boolean running = false;

    private Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        keyInput = new KeyInput();
        addKeyListener(keyInput);
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        level = Level.spawn;
        player = new Player(keyInput);
    }

    private synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    private synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        requestFocus();
        double target = 60.0;                        // target ups limit
        double nsPerUpdate = 1000000000.0 / target;  // updates per second (ns)
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;                            // time taken between each loop
        int fps = 0;                                 // fps counter
        int ups = 0;                                 // ups counter
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerUpdate;
            lastTime = now;

            while (delta >= 1) {
                update(); // also called tick() ... updates game code
                ups++;
                delta--;
            }
            render();       // renders changes to screen
            fps++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + fps + " | " + "UPS: " + ups);
                frame.setTitle(TITLE + " | FPS: " + fps + " | " + "UPS: " + ups);
                fps = 0;
                ups = 0;
            }
        }
        stop();
    }



    private void update() {
        keyInput.update();
        player.update();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();    // get current canvas buffer strategy
        if (bs == null) {// if no current buffer strategy
            try {
                createBufferStrategy(3);    // create triple buffering strategy
                return;
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        /*System.out.println("Page flipping: " + bs.getCapabilities().isPageFlipping());
        System.out.println("Multiple Buffers: " + bs.getCapabilities().isMultiBufferAvailable());
        System.out.println("FSEM Required: " + bs.getCapabilities().isFullScreenRequired());
        System.out.println("Backbuffer accelerated: " + bs.getCapabilities().getBackBufferCapabilities().isAccelerated());
        System.out.println("Frontbuffer accelerated: " + bs.getCapabilities().getFrontBufferCapabilities().isAccelerated());
*/
        screen.clear();  // clears previous screen image

        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;
        level.render(xScroll, yScroll, screen);
        player.render(screen);

        //System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();  // get drawing context for current buffer


        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        //g.setColor(Color.WHITE);
        //g.setFont(new Font("Verdana", 0, 25));
        //g.drawString("X: " + player.x + ", Y: " + player.y, 475 , 425);

        g.dispose(); // clear drawing context
        bs.show();   // display buffer
        //Toolkit.getDefaultToolkit().sync();
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);                             // don't allow resizing window
        game.frame.setTitle(game.TITLE);
        game.frame.add(game);                                       // add game (canvas) to frame
        game.frame.pack();                                          // set window frame to size of canvas
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // close app with close button
        game.frame.setLocationRelativeTo(null);                     // center window on screen
        game.frame.setVisible(true);                                // make frame visible

        game.start();
    }
}

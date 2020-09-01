package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    private Thread thread;
    private boolean running;
    private Handler handler;
    Random r = new Random();
    public static final int height = 480;
    public static final int width = 640;

    public static void main(String[] args) {
	Game game = new Game();
    }

    public Game() {
        handler = new Handler(this);

        this.addKeyListener(new KeyInput(this, handler));
        handler.addObject(new Snake(width/2, height/2, ID.SNAKE, handler));
        handler.addObject(new Food(r.nextInt(Game.width - 40) + 20, r.nextInt(Game.height - 50) + 20, ID.FOOD, handler));

        new Window(width, height, "Snake", this);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running) {
                render();
                frames++;}

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        this.setVisible(true);
        stop();
    }

    private void tick() {
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static int kill(int var, int min, int max, Snake s) {
        if (var >= max) {
            s.tailLength = 0.1;
            return max;
        }
        else if (var <= min) {
            s.tailLength = 0.1;
            return min;
        }
        else return var;
    }
}

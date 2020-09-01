package com.company;

import java.awt.*;

public class Tail extends GameObject {

    private float alpha = 1;
    private Handler handler;
    private Color color;
    private Snake snake;

    private int width, height;
    private double life;

    public Tail(int x, int y, ID id, Color color, int width, int height, double life, Handler handler, Snake snake) {
        super (x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
        this.snake = snake;
    }

    @Override
    public void tick() {

        if (alpha > life) {
            alpha -= life - 0.001f;
        } else {
            handler.removeObject(this);
        }

    }

    @Override
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect( x, y, width, height);
        g2d.setComposite(makeTransparent(1));

    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    @Override
    public Rectangle getBounds() {
        if (snake.velX>0) return new Rectangle(x-snake.width, y, width, height);
        else if (snake.velX<0) return new Rectangle(x+snake.width, y, width, height);
        else if (snake.velY>0) return new Rectangle(x, y-snake.height, width, height);
        else return new Rectangle(x, y+snake.height, width, height);
    }
}

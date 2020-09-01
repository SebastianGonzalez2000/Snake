package com.company;

import java.awt.*;
import java.util.Random;

public class Snake extends GameObject {
    public int width = 20;
    public int height = 20;
    Random r = new Random();
    private Handler handler;
    public double tailLength = 0.1;

    public Snake(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.velY = -5;
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        x = Game.kill(x, 0, Game.width - width, this);
        y += velY;
        y = Game.kill(y, 0, Game.height - height-22, this);

        handler.addObject(new Tail(x, y, ID.TAIL, Color.red, 20, 20, tailLength, handler, this));
        collision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void collision() {
        for (int i = 0 ; i < handler.objects.size() ; i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.TAIL) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    tailLength = 0.1;

                }
            }
            else if (tempObject.getId() == ID.FOOD) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (tailLength>0.002) tailLength = tailLength - 0.001;
                    else System.out.println("reached limit");
                    handler.removeObject(tempObject);
                    handler.addObject(new Food(r.nextInt(Game.width - 40) + 20, r.nextInt(Game.height - 60) + 20, ID.FOOD, handler));
                }
            }
        }
    }
}

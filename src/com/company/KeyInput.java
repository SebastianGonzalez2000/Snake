package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    private Game game;

    public KeyInput(Game game, Handler handler) {
        this.handler = handler;
        this.game = game;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0 ; i < handler.objects.size() ; i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.SNAKE) {
                if (key == KeyEvent.VK_LEFT) {
                    if (tempObject.getVelX() != 4) {
                        tempObject.setVelX(-4);
                        tempObject.setVelY(0);
                        keyDown[0] = true;
                    }
                }
                if (key == KeyEvent.VK_RIGHT) {
                    if (tempObject.getVelX() != -4) {
                        tempObject.setVelX(4);
                        tempObject.setVelY(0);
                        keyDown[1] = true;
                    }
                }
                if (key == KeyEvent.VK_UP) {
                    if (tempObject.getVelY() != 4) {
                        tempObject.setVelX(0);
                        tempObject.setVelY(-4);
                        keyDown[2] = true;
                    }
                }
                if (key == KeyEvent.VK_DOWN) {
                    if (tempObject.getVelY() != -4) {
                        tempObject.setVelX(0);
                        tempObject.setVelY(4);
                        keyDown[3] = true;
                    }
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0 ; i < handler.objects.size() ; i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.SNAKE) {
                if (key == KeyEvent.VK_LEFT) {
                    keyDown[0] = false;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    keyDown[1] = false;
                }
                if (key == KeyEvent.VK_UP) {
                    keyDown[2] = false;
                }
                if (key == KeyEvent.VK_DOWN) {
                    keyDown[3] = false;
                }
                /*
                // vertical movement
                if (!keyDown[0] && !keyDown[1]) {
                    tempObject.setVelX(0);
                }
                // horizontal movement
                if (!keyDown[2] && !keyDown[3]) {
                    tempObject.setVelY(0);
                }

                 */
            }
        }
    }
}

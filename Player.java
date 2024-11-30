package spaceBlasters;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    private int x, y;
    private int width = 50, height = 50; // size of the spaceship
    private int screenWidth = 800;
    private int screenHeight = 600;
    private int health; // Player's health

    public Player(int startX, int startY) {
        x = startX; // starting position
        y = startY;
        health = 3; // Starting health
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);

        // Draw health bar
        g.setColor(Color.RED);
        for (int i = 0; i < health; i++) {
            g.fillRect(x + i * 20, y - 10, 15, 5);
        }
    }

    public void update() {
    }

    public void moveToMouse(int mouseX, int mouseY) {
        // Horizontal movement
        x = mouseX - width / 2;
        if (x < 0) x = 0;
        if (x > screenWidth - width) x = screenWidth - width;

        // Vertical movement
        y = mouseY - height / 2;
        if (y < 0) y = 0;
        if (y > screenHeight - height) y = screenHeight - height;
    }

    public void autoShoot(ArrayList<Bullet> bullets) {
        bullets.add(new Bullet(x + width / 2 - Bullet.WIDTH / 2, y, true));
    }

    public void takeDamage() {
        health--;
    }

    public void restoreHealth() {
        if (health < 3) { // Max health is 3
            health++;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getHealth() { return health; }
}

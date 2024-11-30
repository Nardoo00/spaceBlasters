package spaceBlasters;

import java.awt.*;
import java.util.ArrayList;

public class Enemy {
    int x;
	int y;
    private int health;

    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 3;  // Start with 3 health
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, WIDTH, HEIGHT);
        
        // Draw health bar above the enemy
        g.setColor(Color.GREEN);
        g.fillRect(x, y - 10, WIDTH, 5); // Background bar
        g.setColor(Color.RED);
        g.fillRect(x, y - 10, WIDTH * health / 3, 5); // Foreground (Health)
    }

    public void update() {
        y += 2;  // Move the enemy down
    }

    public boolean isOffScreen() {
        return y > 600;
    }

    public void shoot(ArrayList<Bullet> enemyBullets) {
        enemyBullets.add(new Bullet(x + WIDTH / 2 - Bullet.WIDTH / 2, y + HEIGHT, false));
    }

    public boolean collidesWith(Player player) {
        return x < player.getX() + player.getWidth() && x + WIDTH > player.getX() &&
               y < player.getY() + player.getHeight() && y + HEIGHT > player.getY();
    }

    public void takeDamage() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

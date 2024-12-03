package spaceBlasters;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Enemy {
    int x;
    int y;
    private int health;
    private ImageIcon enemy;

    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 3;  // Start with 3 health
        
        try {
            // Load the alien01.gif for the enemy
            enemy = new ImageIcon(getClass().getResource("/enemy/alien01.gif"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (enemy != null) {
            // Draw the animated GIF at the enemy's position, scaling it to the enemy's size
            g.drawImage(enemy.getImage(), x, y, WIDTH, HEIGHT, null);
        } else {
            // Fallback if the GIF fails to load
            g.setColor(Color.RED);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }

        // Draw health bar
        g.setColor(Color.RED);
        for (int i = 0; i < health; i++) {
            g.fillRect(x + i * 20, y - 10, 15, 5);
        }
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

package spaceBlasters;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {

    private int x, y;
    private int width = 50, height = 50; // size of the spaceship
    private int screenWidth = 800;
    private int screenHeight = 600;
    private int health; // Player's health
    private ImageIcon player; // Holds the animated GIF

    public Player(int startX, int startY) {
        x = startX; // starting position
        y = startY;
        health = 3; // Starting health

        // Load the animated GIF only once in the constructor
        try {
            player = new ImageIcon(getClass().getResource("/ships/ship01.gif"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (player != null) {
            // Draw the animated GIF at the player's position, scaling it to the player's size
            g.drawImage(player.getImage(), x, y, width, height, null);
        } else {
            // Fallback if the GIF fails to load
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }

        // Draw health bar
        g.setColor(Color.GREEN);
        for (int i = 0; i < health; i++) {
            g.fillRect(x + i * 20, y - 10, 15, 5);
        }
    }

    public void update() {
        // Update player-related logic if needed
    }

    public void moveToMouse(int mouseX, int mouseY) {
        x = mouseX - width / 2;
        x = Math.max(0, Math.min(x, screenWidth - width));

        y = mouseY - height / 2;
        y = Math.max(0, Math.min(y, screenHeight - height));
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHealth() {
        return health;
    }
}

package spaceBlasters;

import java.awt.*;

public class PowerUp {
    private int x, y;
    private String type;

    public PowerUp(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Graphics g) {
        if (type.equals("health")) {
            g.setColor(Color.GREEN);
            g.fillOval(x, y, 20, 20);  // Green circle for health power-up
        }
        // You can add more types of power-ups here if needed
    }

    public void update() {
        y += 2;  // Power-up falls downwards
    }

    public boolean isOffScreen() {
        return y > 600;
    }

    public boolean isCollected(Player player) {
        return player.getX() < x + 20 && player.getX() + player.getWidth() > x &&
               player.getY() < y + 20 && player.getY() + player.getHeight() > y;
    }

    public String getType() {
        return type;
    }
}

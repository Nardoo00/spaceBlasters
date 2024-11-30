package spaceBlasters;

import java.awt.*;

public class Bullet {
    public static final int WIDTH = 5;
    public static final int HEIGHT = 10;

    private int x, y;
    private boolean isPlayerBullet; 

    public Bullet(int x, int y, boolean isPlayerBullet) { 
        this.x = x; 
        this.y = y; 
        this.isPlayerBullet = isPlayerBullet;
    }

    public void draw(Graphics g) { 
        g.setColor(isPlayerBullet ? Color.YELLOW : Color.RED); 
        g.fillRect(x, y, WIDTH, HEIGHT);  
    }

    public void update() {  
        if (isPlayerBullet) {
            y -= 5; // move up for player bullets
        } else {
            y += 5; // move down for enemy bullets
        }
    } 

    public boolean isOffScreen() {  
        return y < -HEIGHT || y > 600; 
    } 

    public boolean collidesWith(Enemy enemy) {  
        return x < enemy.x + 40 && x + WIDTH > enemy.x &&
               y < enemy.y + 40 && y + HEIGHT > enemy.y; 
    } 

    public boolean collidesWith(Player player) {  
        return x < player.getX() + player.getWidth() && x + WIDTH > player.getX() &&
               y < player.getY() + player.getHeight() && y + HEIGHT > player.getY();  
    }  
}
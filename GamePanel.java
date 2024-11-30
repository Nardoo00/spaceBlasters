package spaceBlasters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {

    private Timer timer;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> enemyBullets;
    private String mode;

    private int score; // Variable to track the score
    private long startTime; // Time when the game started
    private int highScore; // Variable to track the high score

    public GamePanel(String mode) {
        this.mode = mode;
        player = new Player(375, 500);
        enemies = new ArrayList<>();
        powerUps = new ArrayList<>();
        bullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();

        score = 0;
        highScore = 0; // Initialize the high score to 0
        startTime = System.currentTimeMillis(); // Start tracking time

        setFocusable(true);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                player.moveToMouse(e.getX(), e.getY());
            }
        });

        timer = new Timer(16, this);
    }

    public void startGame() {
        timer.start();
        spawnEnemies();

        // Automatic shooting
        new Timer(150, e -> player.autoShoot(bullets)).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawStars(g);

        draw(g);

        // Display player health
        g.setColor(Color.WHITE);
        g.drawString("Health: " + player.getHealth(), 10, 40);

        // Display score and high score in the upper right corner
        g.setColor(Color.WHITE);
        String scoreText = "Score: " + score;
        String highScoreText = "High Score: " + highScore;

        // Right-align the text based on the width of the panel
        int scoreWidth = g.getFontMetrics().stringWidth(scoreText);
        int highScoreWidth = g.getFontMetrics().stringWidth(highScoreText);

        g.drawString(scoreText, getWidth() - scoreWidth - 10, 25); // 10px padding from right
        g.setColor(Color.YELLOW);
        g.drawString(highScoreText, getWidth() - highScoreWidth - 10, 45); // 10px padding from right
    }

    private void draw(Graphics g) {
        player.draw(g);

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }

        for (Bullet bullet : enemyBullets) {
            bullet.draw(g);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        for (PowerUp powerUp : powerUps) {
            powerUp.draw(g);
        }

        g.setColor(Color.WHITE);
        g.drawString("Mode: " + mode, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    private void updateGame() {
        if (!player.isAlive()) {
            System.out.println("Game Over!");
            updateHighScore(); // Update high score if needed
            timer.stop();
            return;
        }

        // Update the score based on time
        score = (int) ((System.currentTimeMillis() - startTime) / 1000); // Time-based score (seconds)

        player.update();

        // Update player bullets
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            if (bullet.isOffScreen()) {
                bullets.remove(i);
                i--;
            }

            // Check for bullet collisions with enemies
            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);

                if (bullet.collidesWith(enemy)) {
                    System.out.println("Enemy hit by bullet!");
                    enemy.takeDamage();
                    bullets.remove(i); // Remove the bullet
                    i--;  // Adjust index due to removal
                    if (!enemy.isAlive()) {
                        enemies.remove(j); // Remove the enemy if it's dead
                        j--;  // Adjust index due to removal
                    }
                    break;  // Exit inner loop after a collision
                }
            }
        }

        // Update enemy bullets
        for (int i = 0; i < enemyBullets.size(); i++) {
            Bullet bullet = enemyBullets.get(i);
            bullet.update();
            if (bullet.isOffScreen()) {
                enemyBullets.remove(i);
                i--;
            } else if (bullet.collidesWith(player)) {
                System.out.println("Player hit by bullet!");
                player.takeDamage();
                enemyBullets.remove(i);
                i--;
            }
        }

        // Update enemies and handle their shooting logic
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.update();

            // Random chance for enemies to shoot
            if (Math.random() < 0.02) {
                enemy.shoot(enemyBullets);
            }

            // Check if enemy collides with player
            if (enemy.collidesWith(player)) {
                System.out.println("Player hit by enemy!");
                player.takeDamage();
                enemies.remove(i);
                i--;
            }

            // Remove enemies that go off-screen
            if (enemy.isOffScreen()) {
                enemies.remove(i);
                i--;
            }
        }

        // Update power-ups
        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp powerUp = powerUps.get(i);
            powerUp.update();

            // Check if power-up is collected
            if (powerUp.isCollected(player)) {
                if (powerUp.getType().equals("health")) {
                    // Restore health if health power-up is collected
                    player.restoreHealth();
                    System.out.println("Health Restored!");
                }
                powerUps.remove(i);  // Remove the power-up after it’s collected
                i--; // Adjust index due to removal
            }

            // Remove off-screen power-ups
            if (powerUp.isOffScreen()) {
                powerUps.remove(i);
                i--;
            }
        }

        // Spawn new enemies and power-ups
        if (Math.random() < 0.02) spawnEnemies();
        if (Math.random() < 0.01) spawnPowerUps();  // Spawn power-ups periodically
    }

    private void spawnEnemies() {
        int x = (int) (Math.random() * 750);
        enemies.add(new Enemy(x, 0));
    }

    private void spawnPowerUps() {
        int x = (int) (Math.random() * 750);  // Random x position
        powerUps.add(new PowerUp(x, 0, "health")); // Create a health power-up
    }

    private void drawStars(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * getHeight());
            g.fillRect(x, y, 2, 2);
        }
    }

    // Update the high score if the current score exceeds the high score
    private void updateHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }
}

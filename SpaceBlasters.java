package spaceBlasters;

import javax.swing.*;

public class SpaceBlasters extends JFrame {
    private MainMenu mainMenu;
    private GamePanel gamePanel;

    public SpaceBlasters() {
        setTitle("Space Blasters");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);

        mainMenu = new MainMenu(this);
        add(mainMenu);

        setVisible(true);
    }

    public void startGame(String mode) {
        remove(mainMenu);
        gamePanel = new GamePanel(mode);
        add(gamePanel);
        revalidate();
        repaint();
        gamePanel.startGame();
    }

    public static void main(String[] args) {
        new SpaceBlasters();
    }
}
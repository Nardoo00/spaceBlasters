package spaceBlasters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener {
    private JButton startButton;
    private JButton modesButton;
    private JButton shopButton;
    private JButton optionsButton;
    private JButton exitButton;

    private SpaceBlasters game;

    public MainMenu(SpaceBlasters game) {
        this.game = game;

        // background color and layout
        setBackground(Color.BLACK);
        setLayout(new GridLayout(5, 1));

        // create buttons
        startButton = createButton("Start Game");
        modesButton = createButton("Game Modes");
        shopButton = createButton("Shop");
        optionsButton = createButton("Options");
        exitButton = createButton("Exit");

        startButton.addActionListener(this);
        modesButton.addActionListener(this);
        shopButton.addActionListener(this);
        optionsButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(startButton);
        add(modesButton);
        add(shopButton);
        add(optionsButton);
        add(exitButton);

        // center buttons
        for (Component component : getComponents()) {
            ((JComponent) component).setAlignmentX(Component.CENTER_ALIGNMENT);
            ((JComponent) component).setPreferredSize(new Dimension(300, 50));
            ((JComponent) component).setFont(new Font("Arial", Font.BOLD, 20));
            ((JComponent) component).setForeground(Color.WHITE);
            ((JComponent) component).setBackground(Color.DARK_GRAY);
            ((JComponent) component).setBorder(BorderFactory.createLineBorder(Color.WHITE));
            ((JComponent) component).setOpaque(true); 
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false); 
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            String[] modes = {"Challenge Mode", "Endless Mode"};
            String mode = (String) JOptionPane.showInputDialog(
                this,
                "Select Game Mode:",
                "Game Modes",
                JOptionPane.QUESTION_MESSAGE,
                null,
                modes,
                modes[0]
            );
            if (mode != null) {
                game.startGame(mode);
            }
        } else if (e.getSource() == shopButton) {
            JOptionPane.showMessageDialog(this, "awan py");
        } else if (e.getSource() == optionsButton) {
            JOptionPane.showMessageDialog(this, "awan py");
        } else if (e.getSource() == exitButton) {
            System.exit(0); 
        }
    }
}

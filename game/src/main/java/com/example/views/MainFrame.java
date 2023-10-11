package com.example.views;


import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import com.example.models.Hero;

public class MainFrame extends JFrame {

    private GamePanel gamePanel;

    public MainFrame(double screenWidth, double screenHeight, Hero hero) {
        initProperties(screenWidth, screenHeight);
        initComponents(hero);
    }

    private void initProperties(double screenWidth, double screenHeight) {
        initSize(screenWidth, screenHeight);
        setLocation((int) (screenWidth / 2 - getWidth() / 2), (int) (screenHeight / 2 - getHeight() / 2));
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initSize(double screenWidth, double screenHeight) {
        setSize((int) screenWidth, (int) screenHeight);
    }

    private void initComponents(Hero hero) {
        gamePanel = new GamePanel(hero);
        add(gamePanel);
    }

    public void refresh(Hero hero) {
        gamePanel.refresh(hero);
    }
}

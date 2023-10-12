package com.example.views;

import javax.swing.JFrame;
import com.example.models.GameManager;

public class MainFrame extends JFrame {

    private GamePanel gamePanel;

    public MainFrame(GameManager gm) {
        initProperties(gm.getScreenWidth(), gm.getScreenHeight());
        initComponents(gm);
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

    private void initComponents(GameManager gm) {
        gamePanel = new GamePanel(gm);
        add(gamePanel);
    }

    public void refresh() {
        gamePanel.refresh();
    }
}
